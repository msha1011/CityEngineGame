import city.cs.engine.*;
import org.jbox2d.common.Vec2;

// Player car body
public class Car extends DynamicBody {

    // Shared shape and image for all cars
    private static final Shape carShape = new BoxShape(1.5f, 0.75f);
    private static final BodyImage carImage = new BodyImage("data/car.png", 2f);

    // Encapsulation: score is private
    private int score;

    public Car(World world) {
        super(world, carShape);
        addImage(carImage);

        score = 0;


    }

    public int getScore() {
        return score;
    }

    public void addScore(int amount) {
        score += amount;
        System.out.println("Score: " + score);
    }

    // Move left/right (called later)
    public void moveLeft() {
        setLinearVelocity(new Vec2(-8f, getLinearVelocity().y));
    }

    public void moveRight() {
        setLinearVelocity(new Vec2(8f, getLinearVelocity().y));
    }

    public void stopMoving() {
        setLinearVelocity(new Vec2(0f, getLinearVelocity().y));
    }
}