import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameWorld extends World {

    private Car car;
    private final Random rng = new Random();

    // Adjust these if you want wider/narrower play area
    private static final float LEFT_BOUND = -22f;
    private static final float RIGHT_BOUND = 22f;

    // Where coins start (fall from top)
    private static final float COIN_START_Y = 15f;

    // Spawn rate (milliseconds)
    private static final int SPAWN_MS = 900;

    public GameWorld() {
        super();

        // ===== GROUND (long platform) =====
        Shape groundShape = new BoxShape(50f, 0.5f);   // 100 units wide total
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.5f));

        // ===== LEFT WALL (stops car leaving screen) =====
        Shape leftWallShape = new BoxShape(0.5f, 50f);
        StaticBody leftWall = new StaticBody(this, leftWallShape);
        leftWall.setPosition(new Vec2(LEFT_BOUND, 0f));

        // ===== RIGHT WALL
        Shape rightWallShape = new BoxShape(0.5f, 50f);
        StaticBody rightWall = new StaticBody(this, rightWallShape);
        rightWall.setPosition(new Vec2(RIGHT_BOUND, 0f));

        //  PLAYER CAR
        car = new Car(this);
        car.setPosition(new Vec2(0f, -10f));

        // ===== COIN SPAWNER (coins fall from top) =====
        Timer timer = new Timer(SPAWN_MS, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnCoin();
            }
        });
        timer.start();
    }

    // Allow Game.java to access car for controls
    public Car getCar() {
        return car;
    }

    private void spawnCoin() {

        Coin coin = new Coin(this);

        float x = -20f + rng.nextFloat() * 40f;
        coin.setPosition(new Vec2(x, COIN_START_Y));

        coin.addCollisionListener(e -> {

            // If coin hits car → collect coin
            if (e.getOtherBody() == car) {
                car.addScore(1);
                coin.destroy();
            }

            // If coin hits ground → destroy coin
            if (e.getOtherBody() instanceof StaticBody) {
                coin.destroy();
            }

        });
    }
}