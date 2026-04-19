import city.cs.engine.*;

public class Hazard extends DynamicBody {

    private static final Shape hazardShape = new CircleShape(0.6f);
    private static final BodyImage hazardImage =
            new BodyImage("data/bomb.png", 1.7f);

    public Hazard(World world) {
        super(world, hazardShape);
        addImage(hazardImage);
    }
}