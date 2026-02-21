import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles keyboard input for the Car.
 * Encapsulation: input logic is kept OUTSIDE Car, in its own class.
 */
public class CarController extends KeyAdapter {

    private final Car car;          // the car we control
    private float moveSpeed = 6f;    // how fast we move left/right

    public CarController(Car car) {
        this.car = car;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // Keep existing vertical speed, only change horizontal speed
        Vec2 v = car.getLinearVelocity();

        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            car.setLinearVelocity(new Vec2(-moveSpeed, v.y));
        }

        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            car.setLinearVelocity(new Vec2(moveSpeed, v.y));
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        Vec2 v = car.getLinearVelocity();

        // When you release left/right, stop horizontal movement
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT ||
                code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            car.setLinearVelocity(new Vec2(0, v.y));
        }
    }
}