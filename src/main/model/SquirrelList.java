package model;

import java.util.ArrayList;

public class SquirrelList implements AnimalList {
    private ArrayList<Squirrel> sqList;

    public SquirrelList() {
        sqList = new ArrayList<Squirrel>();
    }

    // MODIFIES: this
    // EFFECTS: adds new squirrel to end of current list generated at random position within screen
    @Override
    public void addAnimal() {
        int newX = (int) Math.round(Math.random() * width);
        int newY = (int) Math.round(Math.random() * height);
        Position pos = new Position(newX, newY);
        Squirrel newSq = new Squirrel(pos);
        sqList.add(newSq);
    }

    // REQUIRES: list contains at least one squirrel
    // MODIFIES: this
    // EFFECTS: removes first squirrel in list
    @Override
    public void removeAnimal() {
        sqList.remove(0);
    }

    // EFFECTS: prints out positions of all squirrels
    @Override
    public void viewList() {
        ArrayList<ArrayList<Integer>> sqListPos = new ArrayList<ArrayList<Integer>>();
        for (Squirrel sq : sqList) {
            sqListPos.add(sq.getPos());
        }
        System.out.println(sqListPos);
    }
}
