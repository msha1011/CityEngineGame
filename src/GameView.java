import city.cs.engine.*;
import javax.swing.*;
import java.awt.*;

public class GameView extends UserView {

    private GameWorld world;

    public GameView(GameWorld world, int width, int height) {
        super(world, width, height);
        this.world = world;
    }

    @Override
    protected void paintForeground(Graphics2D g) {

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        int score = world.getCar().getScore();

        g.drawString("Score: " + score, 20, 30);
    }
}