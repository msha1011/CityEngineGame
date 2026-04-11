import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.StaticBody;

public class GroundRemover implements CollisionListener {

    private StaticBody ground;

    public GroundRemover(StaticBody ground) {
        this.ground = ground;
    }

    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == ground) {
            e.getReportingBody().destroy();
        }
    }
}