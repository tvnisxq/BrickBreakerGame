package BrickBreakerGame.src;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Arrays;



// In Mapgenerator class
public class Mapgenerator {
    public int[][] map;
    public int brickwidth;
    public int brickheight;

    public Mapgenerator(int row, int col) {
        map = new int[row][col];
        brickwidth = 540 / col; // Adjust as necessary
        brickheight = 150 / row; // Adjust as necessary
        for (int[] rowArray : map) {
            Arrays.fill(rowArray, 1); // Fill with bricks
        }
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] > 0) {
                    int brickX = j * brickwidth + 80;
                    int brickY = i * brickheight + 50;

                    // Create a gradient paint for the brick
                    GradientPaint gradient = new GradientPaint(brickX, brickY, Color.CYAN.brighter(), 
                    brickX, brickY + brickheight, Color.BLUE.darker());
                    g.setPaint(gradient);
                    g.fillRect(brickX, brickY, brickwidth, brickheight);

                    // Draw the shadow
                    g.setColor(new Color(0, 0, 0, 100)); // Semi-transparent black for shadow
                    g.fillRect(brickX + 5, brickY + 5, brickwidth, brickheight);

                    // Optional: Draw a border
                    g.setColor(Color.black);
                    g.setStroke(new BasicStroke(2));
                    g.drawRect(brickX, brickY, brickwidth, brickheight);
                }
            }
        }
    }

    public void setBrickValue(int value, int row, int col) {
        map[row][col] = value;
    }
}







































// public class Mapgenerator {
//     public int map[][];
//     public int brickwidth;
//     public int brickheight;

//     public Mapgenerator(int row, int col, int screenWidth, int screenHeight) {
//         map = new int[row][col];
        
//         // Dynamically calculate the brick size based on the screen dimensions
//         brickwidth = screenWidth / (col + 2); // Adjust to allow some padding
//         brickheight = screenHeight / 15;      // Adjust the divisor based on the desired brick height

//         for (int[] rowArray : map) {
//             Arrays.fill(rowArray, 1); // Fill with bricks
//         }
//     }

//     public void draw(Graphics2D g, int screenWidth) {
//         // Calculate the starting X position to center the bricks
//         int startingX = (screenWidth - (map[0].length * brickwidth)) / 2;

//         for (int i = 0; i < map.length; i++) {
//             for (int j = 0; j < map[0].length; j++) {
//                 if (map[i][j] > 0) {
//                     int brickX = j * brickwidth + startingX;
//                     int brickY = i * brickheight + 50;

//                     // Create a gradient paint for the brick
//                     GradientPaint gradient = new GradientPaint(brickX, brickY, Color.cyan.brighter(), 
//                         brickX, brickY + brickheight, Color.blue.darker());

//                     g.setPaint(gradient);
//                     g.fillRect(brickX, brickY, brickwidth, brickheight);

//                     // Draw the shadow
//                     g.setColor(new Color(128, 255, 128, 255)); // Semi-transparent black for shadow
//                     g.fillRect(brickX + 5, brickY + 5, brickwidth, brickheight);

//                     g.setColor(Color.BLACK);
//                     g.setStroke(new BasicStroke(3));
//                     g.drawRect(brickX, brickY, brickwidth, brickheight);
//                 }
//             }
//         }
//     }

//     public void setBrickValue(int value, int row, int col) {
//         map[row][col] = value;
//     }
// }



// import java.awt.BasicStroke;
// import java.awt.Color;
// import java.awt.GradientPaint;
// import java.awt.Graphics2D;
// import java.util.Arrays;

// public class Mapgenerator 
// {
//     public int map[][];
//     public int brickwidth;
//     public int brickheight;


//     public Mapgenerator(int row, int col)
//     {
//         map = new int[row][col];
//         brickwidth = 540/col;
//         brickheight = 150/row;
//         for (int[] rowArray : map) 
//         {
//             Arrays.fill(rowArray, 1); // Fill with bricks
//         }

//         // for(int i = 0; i<row; i++)
//         // {
//         //     for(int j = 0; j < col; j++)
//         //     {
//         //         map[i][j] = 1;
//         //     }
//         // }

//     }

//     public void draw(Graphics2D g)
//     {
//         for(int i = 0; i<map.length; i++)
//         {
//             for(int j = 0; j < map[0].length; j++)
//             {
//                 if(map[i][j] > 0)
//                 {
//                     int brickX = j * brickwidth + 80;
//                     int brickY = i * brickheight + 50;

//                     // Create a gradient paint for the brick
//                     GradientPaint gradient = new GradientPaint(brickX, brickY, Color.cyan.brighter(), 
//                     brickX, brickY + brickheight, Color.blue.darker());

//                     g.setPaint(gradient);
//                     g.fillRect(brickX, brickY, brickwidth, brickheight);

//                     // Draw the shadow
//                     g.setColor(new Color(128, 255, 128, 255)); // Semi-transparent black for shadow
//                     g.fillRect(brickX + 5, brickY + 5, brickwidth, brickheight);

//                     g.setColor(Color.BLACK);
//                     g.setStroke(new BasicStroke(3));
//                     g.drawRect(brickX, brickY, brickwidth, brickheight);


//                     //to draw bricks
//                     // g.setColor(Color.WHITE);
//                     // g.fillRect(j*brickwidth + 80, i*brickheight + 50, brickwidth, brickheight);
                    
//                     //this is to draw brick borders
//                     // g.setStroke(new BasicStroke(3));
//                     // g.setColor(Color.BLACK);
//                     // g.drawRect(j*brickwidth + 80, i*brickheight + 50, brickwidth, brickheight);
//                 }
//             }
//         }
//     }

//     public void setBrickValue(int value, int row, int col)
//     {
//         map[row][col] = value;
//     }
// }
