import city.cs.engine.*;

public class Car extends DynamicBody {

    private int score;

    private static final Shape carShape = new BoxShape(1.5f, 0.75f);
    private static final BodyImage carImage = new BodyImage("data/car.png", 2f);

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
    }
}