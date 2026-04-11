import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.DynamicBody;

public class DestroyListener implements StepListener {

    private DynamicBody body;

    public DestroyListener(DynamicBody body) {
        this.body = body;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        if (body.getPosition().y < -11) {
            body.destroy();
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}