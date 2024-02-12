package model;

import java.util.ArrayList;

public class BearList implements AnimalList {
    private ArrayList<Bear> bearList;

    public BearList() {
        bearList = new ArrayList<Bear>();
    }

    // MODIFIES: this
    // EFFECTS: adds new bear to end of current list generated at random position within screen
    @Override
    public void addAnimal() {
        int newX = (int) Math.round(Math.random() * width);
        int newY = (int) Math.round(Math.random() * height);
        Position pos = new Position(newX, newY);
        Bear newBear = new Bear(pos);
        bearList.add(newBear);
    }

    // REQUIRES: list contains at least one bear
    // MODIFIES: this
    // EFFECTS: removes first bear in list
    @Override
    public void removeAnimal() {
        bearList.remove(0);
    }

    // EFFECTS: prints out positions of all bears
    @Override
    public void viewList() {
        ArrayList<ArrayList<Integer>> bearListPos = new ArrayList<ArrayList<Integer>>();
        for (Bear b : bearList) {
            bearListPos.add(b.getPos());
        }
        System.out.println(bearListPos);
    }
}