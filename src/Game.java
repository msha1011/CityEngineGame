import javax.swing.JFrame;

// Main entry point of the game
public class Game {

    // Encapsulation: keep these private
    private GameWorld world;
    private GameView view;

    public Game() {

        // Create custom world (your level)
        world = new GameWorld();

        // Create view (what you see)
        view = new GameView(world, 800, 600);

        // Put view into a window
        JFrame frame = new JFrame("Car Coin Game");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        // IMPORTANT: make sure view can receive key presses
        view.setFocusable(true);
        view.requestFocusInWindow();

        // Add keyboard controls
        CarController controller = new CarController(world.getCar());
        view.addKeyListener(controller);

        // Start physics simulation
        world.start();
    }

    // Program starts here
    public static void main(String[] args) {
        new Game();
    }
}