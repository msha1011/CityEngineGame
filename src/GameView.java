import city.cs.engine.UserView;

import javax.swing.ImageIcon;
import java.awt.*;

public class GameView extends UserView {

    private GameLevel world;

    private Image level1Background;
    private Image level2Background;
    private Image level3Background;

    private boolean showLevelComplete = false;
    private boolean showGameWon = false;

    public GameView(GameLevel world, int width, int height) {
        super(world, width, height);
        this.world = world;

        level1Background = new ImageIcon("data/level1bg.png").getImage();
        level2Background = new ImageIcon("data/level2bg.png").getImage();
        level3Background = new ImageIcon("data/level3bg.png").getImage();
    }

    public void setWorld(GameLevel world) {
        super.setWorld(world);
        this.world = world;
    }

    public void setLevelCompleteScreen(boolean showLevelComplete, boolean showGameWon) {
        this.showLevelComplete = showLevelComplete;
        this.showGameWon = showGameWon;
        repaint();
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        drawOutlinedText(g, "Score: " + world.getCar().getScore(), 20, 40, 24);
        drawOutlinedText(g, "Lives:", 20, 80, 24);
        drawHearts(g, world.getCar().getLives(), 100, 80);

        drawOutlinedText(g, "Shield: " + (world.getCar().isShieldActive() ? "ON" : "OFF"), 20, 120, 24);
        drawOutlinedText(g, "Level: " + world.getLevelNumber(), 20, 160, 24);
        drawOutlinedText(g, "Time: " + world.getTimeRemaining(), 20, 200, 24);
        drawOutlinedText(g, "Target: " + world.getTargetScore(), 20, 240, 24);

        if (world.isGameOver()) {
            drawOutlinedText(g, "GAME OVER", 250, 300, 40);
            drawOutlinedText(g, "Press R to Restart", 300, 340, 20);
        }

        if (showLevelComplete) {
            g.setColor(new Color(0, 0, 0, 170));
            g.fillRect(0, 0, getWidth(), getHeight());

            if (showGameWon) {
                drawOutlinedText(g, "YOU WIN!", 280, 300, 40);
                drawOutlinedText(g, "Press R to Restart", 260, 340, 20);
            } else {
                drawOutlinedText(g, "LEVEL COMPLETE!", 200, 280, 40);
                drawOutlinedText(g, "Press SPACE for next level", 210, 330, 20);
            }
        }
    }

    private void drawHearts(Graphics2D g, int lives, int startX, int y) {
        for (int i = 0; i < lives; i++) {
            drawOutlinedHeart(g, "♥", startX + (i * 35), y);
        }
    }

    private void drawOutlinedText(Graphics2D g, String text, int x, int y, int size) {
        g.setFont(new Font("Arial", Font.BOLD, size));

        g.setColor(Color.BLACK);
        g.drawString(text, x - 1, y);
        g.drawString(text, x + 1, y);
        g.drawString(text, x, y - 1);
        g.drawString(text, x, y + 1);

        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }

    private void drawOutlinedHeart(Graphics2D g, String text, int x, int y) {
        g.setFont(new Font("Arial", Font.BOLD, 28));

        g.setColor(Color.BLACK);
        g.drawString(text, x - 1, y);
        g.drawString(text, x + 1, y);
        g.drawString(text, x, y - 1);
        g.drawString(text, x, y + 1);

        g.setColor(Color.RED);
        g.drawString(text, x, y);
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        if (world.getLevelNumber() == 1) {
            g.drawImage(level1Background, 0, 0, getWidth(), getHeight(), this);
        } else if (world.getLevelNumber() == 2) {
            g.drawImage(level2Background, 0, 0, getWidth(), getHeight(), this);
        } else {
            g.drawImage(level3Background, 0, 0, getWidth(), getHeight(), this);
        }
    }
}