public class Level2 extends GameLevel {

    public Level2() {
        super(15, 25);
    }

    @Override
    public int getLevelNumber() {
        return 2;
    }

    @Override
    public int getSpawnRate() {
        return 800;
    }

    @Override
    public double getHazardChance() {
        return 0.4;
    }
}