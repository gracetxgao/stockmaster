package model;

import java.util.ArrayList;

public abstract class Animal {
    private Position pos;
    private int timeAlive;

    public Animal(Position pos) {
        this.pos = pos;
        this.timeAlive = 0;
    }

    public ArrayList<Integer> getPos() {
        ArrayList<Integer> posList = new ArrayList<Integer>(2);
        posList.add(this.pos.getX());
        posList.add(this.pos.getY());
        return posList;
    }
}
