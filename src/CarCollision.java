import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class CarCollision implements CollisionListener {

    private Car car;

    public CarCollision(Car car) {
        this.car = car;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof Coin) {
            car.addScore();
            e.getOtherBody().destroy();
        }

        if (e.getOtherBody() instanceof Hazard) {
            car.loseLife();
            e.getOtherBody().destroy();
        }
    }
}