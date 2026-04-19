import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Game {

    private GameLevel world;
    private GameView view;
    private JFrame frame;
    private CarController controller;
    private JButton soundButton;

    private boolean waitingForNextLevel = false;
    private GameLevel nextLevel = null;
    private boolean gameWon = false;

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
            frame.setLayout(new BorderLayout());
            frame.add(view, BorderLayout.CENTER);

            soundButton = new JButton("Sound: ON");
            soundButton.addActionListener(e -> {
                SoundManager.toggleMute();

                if (SoundManager.isMuted()) {
                    soundButton.setText("Sound: OFF");
                } else {
                    soundButton.setText("Sound: ON");
                }

                view.requestFocusInWindow();
            });

            frame.add(soundButton, BorderLayout.SOUTH);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);

            view.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_R && (world.isGameOver() || gameWon)) {
                        restartGame();
                    }

                    if (e.getKeyCode() == KeyEvent.VK_SPACE && waitingForNextLevel && nextLevel != null) {
                        waitingForNextLevel = false;
                        view.setLevelCompleteScreen(false, false);
                        goToLevel(nextLevel);
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

        waitingForNextLevel = false;
        gameWon = false;
        view.setLevelCompleteScreen(false, false);

        world.start();
        SoundManager.playBackgroundMusic("data/background.wav");
    }

    private void checkGameState() {
        if (world.isGameOver()) {
            world.stopLevel();
            SoundManager.stopBackgroundMusic();
        } else if (world.isComplete() && !waitingForNextLevel && !gameWon) {
            SoundManager.playSound("data/levelcomplete.wav");
            world.stopLevel();
            SoundManager.stopBackgroundMusic();

            if (world.getLevelNumber() == 1) {
                nextLevel = new Level2();
                waitingForNextLevel = true;
                view.setLevelCompleteScreen(true, false);
            } else if (world.getLevelNumber() == 2) {
                nextLevel = new Level3();
                waitingForNextLevel = true;
                view.setLevelCompleteScreen(true, false);
            } else if (world.getLevelNumber() == 3) {
                gameWon = true;
                nextLevel = null;
                view.setLevelCompleteScreen(true, true);
            }
        }
    }

    private void goToLevel(GameLevel newLevel) {
        world.stopLevel();
        SoundManager.stopBackgroundMusic();
        startGame(newLevel);
    }

    private void restartGame() {
        world.stopLevel();
        SoundManager.stopBackgroundMusic();
        waitingForNextLevel = false;
        gameWon = false;
        nextLevel = null;
        startGame(new Level1());
    }

    public static void main(String[] args) {
        new Game();
    }
}