import city.cs.engine.*;

public class ShieldPowerUp extends DynamicBody {

    private static final Shape powerShape = new CircleShape(0.6f);
    private static final BodyImage shieldImage =
            new BodyImage("data/shield.png", 1.4f);

    public ShieldPowerUp(World world) {
        super(world, powerShape);
        addImage(shieldImage);
    }
}