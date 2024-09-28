package BrickBreakerGame.src;

import java.awt.*;

public class Particle {
    private float x, y;
    private float velX, velY;
    private float life; // Life span of the particle
    private Color color;

    public Particle(float x, float y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;

        // Set random velocity for a burst effect
        velX = (float)(Math.random() * 4 - 2);  // Range: -2 to 2
        velY = (float)(Math.random() * 4 - 2);  // Range: -2 to 2

        // Set lifespan of the particle (this will be reduced over time)
        life = 100;  // Arbitrary value, can be tweaked

        
    }

    public void update() 
    {
        x += velX;
        y += velY;
        life -= 2.5;  // Decrease life for fade effect
    }

    public void render(Graphics g) 
    {
        if (life > 0) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, life / 100));  // Fading effect

            g2d.setColor(color);
            g2d.fillOval((int)x, (int)y, 6, 6);  // Small particles
        }
    }

    public boolean isDead() {
        return life <= 0;  // Check if the particle has faded away
    }
}
