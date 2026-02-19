import city.cs.engine.*;

// Coin class represents collectible coins in the game
// It uses inheritance by extending StaticBody
// StaticBody means it does not move and is fixed in place
public class Coin extends StaticBody {

    // static = shared by all Coin objects (saves memory)
    // final = cannot be changed after creation
    // CircleShape creates a circular collision shape for the coin
    private static final Shape coinShape = new CircleShape(0.5f);

    // Image used to visually represent the coin
    // All coins share the same image
    private static final BodyImage coinImage =
            new BodyImage("data/coin.png", 1f);

    // Constructor runs when a new Coin object is created
    public Coin(World world) {

        // Calls StaticBody constructor
        // This adds the coin to the physics world
        super(world, coinShape);

        // Adds image so coin is visible on screen
        addImage(coinImage);
    }
}

