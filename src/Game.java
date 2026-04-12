import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game {

    private GameLevel world;
    private GameView view;
    private JFrame frame;
    private CarController controller;

    public Game() {
        startGame(new Level1());

        Timer gameTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGameState();
                view.repaint();
            }
        });
        gameTimer.start();
    }

    private void startGame(GameLevel level) {
        world = level;

        if (view == null) {
            view = new GameView(world, 800, 600);

            frame = new JFrame("Car Coin Game");
            frame.add(view);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);

            view.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_R && world.isGameOver()) {
                        restartGame();
                    }
                }
            });
        } else {
            view.setWorld(world);
        }

        if (controller != null) {
            view.removeKeyListener(controller);
        }

        controller = new CarController(world.getCar());
        view.addKeyListener(controller);

        view.setFocusable(true);
        view.requestFocusInWindow();

        world.start();
    }

    private void checkGameState() {
        if (world.isGameOver()) {
            world.stopLevel();
        } else if (world.isComplete()) {
            if (world.getLevelNumber() == 1) {
                goToLevel(new Level2());
            } else if (world.getLevelNumber() == 2) {
                goToLevel(new Level3());
            } else if (world.getLevelNumber() == 3) {
                world.stopLevel();
            }
        }
    }

    private void goToLevel(GameLevel newLevel) {
        world.stopLevel();
        startGame(newLevel);
    }

    private void restartGame() {
        world.stopLevel();
        startGame(new Level1());
    }

    public static void main(String[] args) {
        new Game();
    }
}