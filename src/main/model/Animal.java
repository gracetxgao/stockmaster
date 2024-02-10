package model;

import model.Position;

public abstract class Animal {
    protected Position pos;
    protected int speed;
    protected int timeAlive;
    protected int lifeSpan;
    protected int rank;

    public Animal(Position pos, int speed, int lifeSpan, int rank) {
        this.pos = pos;
        this.speed = speed;
        this.timeAlive = 0;
        this.lifeSpan = lifeSpan;
        this.rank = rank;
    }

    public abstract Position move();

}
