package model;

public class Raccoon extends Animal {
    public Raccoon(Position pos, int speed, int lifeSpan, int rank) {
        super(pos, speed, lifeSpan, rank);
    }

    @Override
    public Position move() {
        return null;
    }
}
