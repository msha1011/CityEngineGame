import city.cs.engine.*;
import org.jbox2d.common.Vec2;

// GameWorld extends World → inheritance
// This encapsulates everything inside the game world
public class GameWorld extends World {

    // private field = encapsulation
    private Car car;

    // Constructor runs when GameWorld is created
    public GameWorld() {

        // Calls World constructor (inheritance)
        super();

        // Create ground so car doesn't fall
        Shape groundShape = new BoxShape(20, 1);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0, -12));

        // Create car object
        car = new Car(this);

        // Set starting position of car
        car.setPosition(new Vec2(0, -10));
    }

    // Getter method → encapsulation
    public Car getCar() {
        return car;
    }
}