import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Car extends Walker {

    private static final Shape carShape = new BoxShape(2f, 1f);
    private static final BodyImage carImage =
            new BodyImage("data/car.png", 4f);

    private int score;
    private int lives;
    private boolean shieldActive;

    public Car(World world) {
        super(world, carShape);
        addImage(carImage);
        score = 0;
        lives = 3;
        shieldActive = false;
    }

    public int getScore() {
        return score;
    }

    public void addScore() {
        score++;
    }

    public void addScore(int amount) {
        score += amount;
    }

    public int getLives() {
        return lives;
    }

    public void loseLife() {
        if (shieldActive) {
            shieldActive = false;
        } else {
            lives--;
            if (lives < 0) {
                lives = 0;
            }
        }
    }

    public boolean isShieldActive() {
        return shieldActive;
    }

    public void activateShield() {
        shieldActive = true;
    }

    public void deactivateShield() {
        shieldActive = false;
    }

    public void resetPosition() {
        setLinearVelocity(new Vec2(0, 0));
        setAngularVelocity(0);
        setPosition(new Vec2(0, -8));
    }
}