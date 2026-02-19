import city.cs.engine.*;
import org.jbox2d.common.Vec2;

// This class represents the player car
// It extends DynamicBody so it can move and interact with physics
public class Car extends DynamicBody {

    // static = shared by all cars
    // final = cannot be changed
    // Shape defines the physical collision shape of the car
    private static final Shape carShape = new BoxShape(1.5f, 0.75f);

    // Image shown on screen for the car
    private static final BodyImage carImage =
            new BodyImage("data/car.png", 2f);

    // score is private → encapsulation (protected from outside modification)
    private int score;

    // Constructor runs when a new Car is created
    public Car(World world) {

        // Calls the DynamicBody constructor to add this body to the world
        super(world, carShape);

        // Adds the image so the car is visible
        addImage(carImage);

        // Start score at 0
        score = 0;
    }

    // Moves car left by reducing x position
    public void moveLeft() {
        setPosition(new Vec2(getPosition().x - 1, getPosition().y));
    }

    // Moves car right by increasing x position
    public void moveRight() {
        setPosition(new Vec2(getPosition().x + 1, getPosition().y));
    }

    // Moves car forward automatically
    public void moveForward() {
        setPosition(new Vec2(getPosition().x, getPosition().y + 0.1f));
    }

    // Adds 1 point to score
    public void addScore() {
        score++;
        System.out.println("Score: " + score);
    }

    // Getter method (encapsulation)
    public int getScore() {
        return score;
    }
}
