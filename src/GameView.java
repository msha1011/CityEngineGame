import city.cs.engine.UserView;

import java.awt.*;

public class GameView extends UserView {

    private GameLevel world;

    public GameView(GameLevel world, int width, int height) {
        super(world, width, height);
        this.world = world;
    }

    public void setWorld(GameLevel world) {
        super.setWorld(world);
        this.world = world;
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        g.drawString("Score: " + world.getCar().getScore(), 20, 40);
        g.drawString("Lives: " + world.getCar().getLives(), 20, 70);
        g.drawString("Shield: " + (world.getCar().isShieldActive() ? "ON" : "OFF"), 20, 100);
        g.drawString("Level: " + world.getLevelNumber(), 20, 130);
        g.drawString("Time: " + world.getTimeRemaining(), 20, 160);
        g.drawString("Target: " + world.getTargetScore(), 20, 190);

        if (world.isGameOver()) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 250, 300);
            g.setFont(new Font("Arial", Font.BOLD, 20));
            g.drawString("Press R to Restart", 300, 340);
        } else if (world.isComplete() && world.getLevelNumber() == 3) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("YOU WIN!", 280, 300);
        }
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        if (world.getLevelNumber() == 1) {
            g.setColor(new Color(230, 230, 230));
        } else if (world.getLevelNumber() == 2) {
            g.setColor(new Color(210, 220, 235));
        } else {
            g.setColor(new Color(235, 210, 210));
        }

        g.fillRect(0, 0, getWidth(), getHeight());
    }
}