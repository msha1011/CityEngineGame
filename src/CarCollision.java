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
            SoundManager.playSound("data/coin.wav");
            car.addScore();
            e.getOtherBody().destroy();
        }

        if (e.getOtherBody() instanceof Hazard) {
            SoundManager.playSound("data/hit.wav");
            car.loseLife();
            e.getOtherBody().destroy();
        }

        if (e.getOtherBody() instanceof ShieldPowerUp) {
            SoundManager.playSound("data/shield.wav");
            car.activateShield();
            e.getOtherBody().destroy();
        }
    }
}