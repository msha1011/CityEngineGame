import city.cs.engine.*;

public class Hazard extends DynamicBody {

    private static final Shape hazardShape = new CircleShape(0.5f);

    public Hazard(World world) {
        super(world, hazardShape);
    }
}