public class Level1 extends GameLevel {

    public Level1() {
        super(10, 30);
    }

    @Override
    public int getLevelNumber() {
        return 1;
    }

    @Override
    public int getSpawnRate() {
        return 1000;
    }

    @Override
    public double getHazardChance() {
        return 0.2;
    }
}