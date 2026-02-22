import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// Handles keyboard input and moves the car left/right
public class CarController extends KeyAdapter {

    private final Car car;

    // Speed for left/right movement
    private static final float SPEED = 12f;

    public CarController(Car car) {
        this.car = car;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Move left
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            car.setLinearVelocity(new Vec2(-SPEED, car.getLinearVelocity().y));
        }

        // Move right
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            car.setLinearVelocity(new Vec2(SPEED, car.getLinearVelocity().y));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        // When you release left/right, stop horizontal movement
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT ||
                code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            car.setLinearVelocity(new Vec2(0, car.getLinearVelocity().y));
        }
    }
}