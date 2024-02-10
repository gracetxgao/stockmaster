package model;

public class Coyote extends Animal {

    public Coyote(Position pos, int speed, int lifeSpan, int rank) {
        super(pos, speed, lifeSpan, rank);
    }

    @Override
    public Position move() {
        return null;
    }
}
