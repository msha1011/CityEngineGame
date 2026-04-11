public class Level3 extends GameLevel {

    public Level3() {
        super(20, 20);
    }

    @Override
    public int getLevelNumber() {
        return 3;
    }

    @Override
    public int getSpawnRate() {
        return 600;
    }

    @Override
    public double getHazardChance() {
        return 0.5;
    }
}