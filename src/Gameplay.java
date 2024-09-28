package BrickBreakerGame.src;

import java.util.Random;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.util.ArrayList;

public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
    private ArrayList<Particle> particles = new ArrayList<>();
    private boolean play = false;
    private int score = 0;
    private int totalbricks = 21;


    private Color neonGreen = new Color(0, 255, 128);
    private Color neonPurple = new Color(200, 0, 255);

    private Timer timer;
    private int delay = 5;

    private Random random;
    private int playerX = 310;
    private int ballposX;
    private int ballposY;
    private int ballXdir;
    private int ballYdir;

    private Mapgenerator map;

    public Gameplay() 
    {
        random = new Random();
        map = new Mapgenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(5, this);
        timer.start();

        resetBall();
    }

    // Method to randomize ball direction
    public void resetBall() 
    {
        ballXdir = random.nextInt(2) == 0 ? -4 : 4; // Randomize X direction
        ballYdir = random.nextInt(2) == 0 ? -5 : 5; // Randomize Y direction
        ballposX = 124 + random.nextInt(200); // Random X position
        ballposY = 350; // Fixed Y position to start from
    }

    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Background with a gradient
        GradientPaint bgGradient = new GradientPaint(0, 0, Color.BLACK, 0, getHeight(), Color.DARK_GRAY);
        g2d.setPaint(bgGradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());



        // background
        // g.setColor(Color.BLACK);
        // g.fillRect(1, 1, 692, 592);

        // drawing map
        map.draw((Graphics2D) g2d);

        // borders
        // g.setColor(Color.white);
        // g.fillRect(0, 0, 3, 592); // Left border
        // g.fillRect(0, 0, 692, 3); // Top border
        // g.fillRect(681, 0, 3, 592); // Right border

        // Scores
        g2d.setColor(Color.white);
        g2d.setFont(new Font("Roboto", Font.BOLD, 25));
        g.drawString("Score: " + score, getWidth() - 150, 30);

        // the paddle
        g.setColor(neonGreen);
        g2d.fillRoundRect(playerX, 550, 100, 8, 10, 10);  // Rounded paddle for modern look
        g2d.setColor(new Color(0, 255, 128, 100));  // Glow effect
        g2d.setStroke(new BasicStroke(5));
        // g2d.drawRoundRect(playerX - 5, 545, 110, 18, 10, 10);

        // the ball
        g.setColor(neonPurple);
        g.fillOval(ballposX, ballposY, 20, 20);
        g2d.setColor(new Color(200, 0, 255, 100));  // Glow effect for ball
        // g2d.drawOval(ballposX - 5, ballposY - 5, 30, 30);


        for (Particle p : particles) 
        {
            p.render(g);
        }
    

        if (totalbricks <= 0) 
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Roboto", Font.BOLD, 30));
            g.drawString("YOU WON", 260, 300);

            g.setFont(new Font("Roboto", Font.BOLD, 20));
            g.drawString("Press ENTER to RESTART", 230, 350);
        }

        if (ballposY > 570) 
        {
            play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("Roboto", Font.BOLD, 30));
            g.drawString("GAME OVER, Scores:", 190, 300);

            g.setFont(new Font("Roboto", Font.BOLD, 20));
            g.drawString("Press ENTER to RESTART", 230, 350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        timer.start();
        if (play) 
        {
            if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) 
            {
                ballYdir = -ballYdir;
            }

            // Brick collision detection
            A: for (int i = 0; i < map.map.length; i++) 
            {
                for (int j = 0; j < map.map[0].length; j++) 
                {
                    if (map.map[i][j] > 0) 
                    {
                        int brickX = j * map.brickwidth + 80;
                        int brickY = i * map.brickheight + 50;
                        int brickidth = map.brickwidth;
                        int brickheight = map.brickheight;

                        Rectangle rect = new Rectangle(brickX, brickY, brickidth, brickheight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
                        Rectangle brickRect = rect;

                        if (ballRect.intersects(brickRect)) 
                        {
                            map.setBrickValue(0, i, j);
                            totalbricks--;
                            score += 5;

                            for (int k = 0; k < 10; k++) 
                            {  // Let's say you want to generate 10 particles
                                Color randomColor = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
                                particles.add(new Particle(ballposX, ballposY, randomColor));
                            }
                                if (ballposX + 19 <= brickRect.x || ballposY + 1 >= brickRect.x + brickRect.width) 
                                {
                                    ballXdir = -ballXdir;
                                } else 
                                    ballYdir = -ballYdir;
                                break A;
                        }    
                     }
                }
            }
            

            for (int i = 0; i < particles.size(); i++) {
                Particle p = particles.get(i);
                p.update();

                if (p.isDead()) {
                    particles.remove(i);  // Remove dead particles
                    i--;
                }
            }

            // Ball movement logic
            ballposX += ballXdir;
            ballposY += ballYdir;

            if (ballposX < 0) 
            {
                ballXdir = -ballXdir;
            }
            if (ballposY < 0) 
            {
                ballYdir = -ballYdir;
            }
            if (ballposX > 670) 
            {
                ballXdir = -ballXdir;
            }
            repaint();
        }
        
}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 600) {
                playerX = 600;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX < 10) {
                playerX = 10;
            } else {
                moveLeft();
            }
        }

        // Reset the game on Enter key press
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!play) {
                play = true;
                resetBall(); // Use resetBall method to randomize ball direction
                playerX = 310;
                score = 0;
                totalbricks = 21;
                map = new Mapgenerator(3, 7);
                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }
}