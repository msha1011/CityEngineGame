import city.cs.engine.*;
import javax.swing.JFrame;

// Main entry point of the game
public class Game {

    // Encapsulation: private fields
    private GameWorld world;
    private GameView view;

    public Game() {

        // Create world
        world = new GameWorld();

        // Create view
        view = new GameView(world, 800, 600);

        // Create window
        JFrame frame = new JFrame("Car Coin Game");

        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // Start physics
        world.start();
    }

    // Main method (PROGRAM STARTS HERE)
    public static void main(String[] args) {
        new Game();
    }
}