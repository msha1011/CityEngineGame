import city.cs.engine.*;

// Falling coin
public class Coin extends DynamicBody {

    private static final Shape coinShape = new CircleShape(0.5f);
    private static final BodyImage coinImage = new BodyImage("data/coin.png", 1f);

    public Coin(World world) {
        super(world, coinShape);
        addImage(coinImage);

        // So it falls nicely
        setGravityScale(1f);
    }
}