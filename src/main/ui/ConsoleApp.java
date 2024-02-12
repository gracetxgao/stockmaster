package ui;

import model.BearList;
import model.SquirrelList;

import java.util.Scanner;

public class ConsoleApp {
    private Scanner input;
    private SquirrelList sqList;
    private BearList bearList;
    private Boolean cont;

    public ConsoleApp() {
        start();
    }

    private void init() {
        sqList = new SquirrelList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void start() {
        cont = true;
        String userInput;
        init();
        while (cont) {
            showOpeningOptions();
            userInput = input.nextLine().toLowerCase();
            handleOpeningOptions(userInput);
        }
        System.out.println("finished");
    }

    private void showOpeningOptions() {
        System.out.println("\nselect from:");
        System.out.println("\t1 - view animal information");
        System.out.println("\t2 - edit animal information");
        System.out.println("\tq - quit");
    }

    private void handleOpeningOptions(String userInput) {
        if (userInput.equals("1")) {
            showChooseAnimal();
            userInput = input.nextLine().toLowerCase();
            handleViewAnimal(userInput);
        } else if (userInput.equals("2")) {
            showEditOptions();
            userInput = input.nextLine().toLowerCase();
            handleEditAnimals(userInput);
        } else {
            cont = false;
        }
    }

    private void showChooseAnimal() {
        System.out.println("\nchoose animal:");
        System.out.println("\t1 - squirrel");
        System.out.println("\t2 - bear");
        System.out.println("\ta - all");
        System.out.println("\tq - quit");
    }

    private void handleViewAnimal(String userInput) {
        if (userInput.equals("1")) {
            sqList.viewList();
        } else if (userInput.equals("2")) {
            bearList.viewList();
        } else if (userInput.equals("a")) {
            System.out.println("printing all");
        } else {
            cont = false;
        }
    }

    private void showEditOptions() {
        System.out.println("\nchoose action:");
        System.out.println("\t1 - add animal");
        System.out.println("\t2 - remove animal");
        System.out.println("\tq - quit");
    }

    private void handleEditAnimals(String userInput) {
        if (userInput.equals("1")) {
            showChooseAnimal();
            userInput = input.nextLine().toLowerCase();
            handleAddAnimal(userInput);
        } else if (userInput.equals("2")) {
            showChooseAnimal();
            userInput = input.nextLine().toLowerCase();
            handleRemoveAnimal(userInput);
        } else {
            cont = false;
        }
    }

    private void handleAddAnimal(String userInput) {
        if (userInput.equals("q")) {
            cont = false;
        } else {
            showChooseAmount();
            int amount = Integer.valueOf(input.nextLine());
            for (int i = 0; i < amount; i++) {
                if (userInput.equals("1")) {
                    sqList.addAnimal();
                } else if (userInput.equals("2")) {
                    bearList.addAnimal();
                }
            }
        }
    }

    private void handleRemoveAnimal(String userInput) {
        if (userInput.equals("q")) {
            cont = false;
        } else {
            showChooseAmount();
            int amount = Integer.valueOf(input.nextLine());
            for (int i = 0; i < amount; i++) {
                if (userInput.equals("1")) {
                    sqList.removeAnimal();
                } else if (userInput.equals("2")) {
                    bearList.removeAnimal();
                }
            }
        }
    }

    private void showChooseAmount() {
        System.out.println("\nenter amount:");
    }
}

