import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public abstract class GameLevel extends World {

    protected Car car;
    protected StaticBody ground;
    private StaticBody leftWall;
    private StaticBody rightWall;

    private Timer spawnTimer;
    private Timer levelTimer;

    private int timeRemaining;
    private int targetScore;

    public GameLevel(int targetScore, int timeLimitSeconds) {
        super();

        this.targetScore = targetScore;
        this.timeRemaining = timeLimitSeconds;

        setupWalls();
        setupCar();
        startSpawning();
        startLevelTimer();
    }

    private void setupWalls() {
        ground = new StaticBody(this, new BoxShape(20, 0.5f));
        ground.setPosition(new Vec2(0, -10));

        leftWall = new StaticBody(this, new BoxShape(0.5f, 10));
        leftWall.setPosition(new Vec2(-20, 0));

        rightWall = new StaticBody(this, new BoxShape(0.5f, 10));
        rightWall.setPosition(new Vec2(20, 0));
    }

    private void setupCar() {
        car = new Car(this);
        car.setPosition(new Vec2(0, -8));
        car.addCollisionListener(new CarCollision(car));
    }

    private void startSpawning() {
        spawnTimer = new Timer(getSpawnRate(), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spawnCoin();

                if (Math.random() < getHazardChance()) {
                    spawnHazard();
                }

                if (Math.random() < 0.3) {
                    spawnShieldPowerUp();
                }
            }
        });
        spawnTimer.start();
    }

    private void startLevelTimer() {
        levelTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--;
            }
        });
        levelTimer.start();
    }

    public void spawnCoin() {
        Coin coin = new Coin(this);
        float x = (float) (Math.random() * 16 - 8);
        coin.setPosition(new Vec2(x, 9));
        coin.addCollisionListener(new GroundRemover(ground));
    }

    public void spawnHazard() {
        Hazard hazard = new Hazard(this);
        float x = (float) (Math.random() * 16 - 8);
        hazard.setPosition(new Vec2(x, 9));
        hazard.addCollisionListener(new GroundRemover(ground));
    }

    public void spawnShieldPowerUp() {
        ShieldPowerUp shield = new ShieldPowerUp(this);
        float x = (float) (Math.random() * 16 - 8);
        shield.setPosition(new Vec2(x, 9));
        shield.addCollisionListener(new GroundRemover(ground));
    }

    public Car getCar() {
        return car;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public int getTargetScore() {
        return targetScore;
    }

    public boolean isComplete() {
        return car.getScore() >= targetScore;
    }

    public boolean isGameOver() {
        return car.getLives() <= 0 || timeRemaining <= 0;
    }

    public void stopLevel() {
        if (spawnTimer != null) {
            spawnTimer.stop();
        }
        if (levelTimer != null) {
            levelTimer.stop();
        }
        stop();
    }

    public abstract int getLevelNumber();

    public abstract int getSpawnRate();

    public abstract double getHazardChance();
}