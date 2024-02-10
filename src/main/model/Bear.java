package model;

public class Bear extends Animal {
    public Bear(Position pos, int speed, int lifeSpan, int rank) {
        super(pos, speed, lifeSpan, rank);
    }

    @Override
    public Position move() {
        return null;
    }
}
