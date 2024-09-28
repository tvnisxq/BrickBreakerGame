package BrickBreakerGame.src;
import javax.swing.JFrame;

public class Main 
{
    public static void main(String[] args) 
    {
        JFrame obj = new JFrame();
        Gameplay game = new Gameplay();
        obj.setBounds(10,10,700,600);
        obj.setLocation(600, 180);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(game);
        
    }

    
}




































// public class Main {

//     public static void main(String[] args) {
//         JFrame frame = new JFrame();

//         // Get the local screen graphics environment
//         GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

//         // Set frame properties
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setResizable(false);
//         frame.setUndecorated(true);  // Remove window borders and title

//         // Add Gameplay
//         Gameplay gameplay = new Gameplay();
//         frame.add(gameplay);

//         // Set full screen
//         device.setFullScreenWindow(frame);

//         // Add listener to close on window exit
//         frame.addWindowListener(new WindowAdapter() {
//             @Override
//             public void windowClosing(WindowEvent e) {
//                 System.exit(0);
//             }
//         });

//         // Start the game
//         frame.setVisible(true);
//     }
// }
