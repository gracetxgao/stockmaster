package ui;

import model.Apple;
import model.Google;
import model.Profile;
import model.Stock;

import java.util.Scanner;

public class TerminalApp {
    private Scanner input;
    private Profile profile;
    private Boolean stop;
    private String userInput;

    private Stock apple;
    private Stock google;
    private Stock nvidia;
    private Stock rivian;
    private Stock amazon;
    private Stock tesla;

    public TerminalApp() {
        start();
    }

    private void init(String name) {
        profile = new Profile(name);
        input = new Scanner(System.in);
        apple = new Apple("AAPL", 150);
        apple = new Google("GOOG", 125);
        input.useDelimiter("\n");
    }

    private void start() {
        System.out.println("Welcome to the stock market simulator! What's your name?");
        userInput = input.nextLine();
        init(userInput);
        System.out.println("Hi " + profile.getName() + "! Your current net worth is " + profile.getNetWorth() + ".\n");
        System.out.println("Choose an option below to get started!");
        while (!stop) {
            showOpeningOptions();
            userInput = input.nextLine().toLowerCase();
            handleOpeningOptions(userInput);
        }
        System.out.println("Simulator ended.");
    }

    private void showOpeningOptions() {
        System.out.println("Select from:");
        System.out.println("\t1 - buy stocks");
        System.out.println("\t2 - sell stocks");
        System.out.println("\t3 - view transaction history");
        System.out.println("\t4 - view stock price history");
        System.out.println("\t5 - advance to next day");
        System.out.println("\tq - quit");
    }

    private void handleOpeningOptions(String userInput) {
        if (userInput.equals("1")) {
            showChooseStock();
            userInput = input.nextLine().toLowerCase();
            handleBuyStock(userInput);
        } else if (userInput.equals("2")) {
            showChooseStock();
            userInput = input.nextLine().toLowerCase();
            handleSellStock(userInput);
        } else if (userInput.equals("3")) {
            handleViewTransactionHistory();
        } else if (userInput.equals("4")) {
            showChooseStock();
            userInput = input.nextLine().toLowerCase();
            handleViewStockPriceHistory(userInput);
        } else if (userInput.equals("5")) {
            handleNextDay();
        } else {
            System.out.println("Invalid input");
        }
    }

    private void showChooseStock() {
        System.out.println("Choose from:");
        System.out.println("\t1 - AAPL");
        System.out.println("\t2 - GOOG");
        System.out.println("\t3 - NVDA");
        System.out.println("\t4 - AMZN");
        System.out.println("\t5 - RIVN");
        System.out.println("\t6 - TSLA");
        System.out.println("\tq - quit");
    }

    private void handleBuyStock(String userInput) {
        if (userInput.equals("q")) {
            stop = true;
        } else {
            showChooseAmount();
            int amount = Integer.valueOf(input.nextLine());
            for (int i = 0; i < amount; i++) {
                if (userInput.equals("1")) {
                    profile.buyStock(apple);
                } else if (userInput.equals("2")) {
                    profile.buyStock(Google);
                }
            }
            System.out.println(amount + " added");
        }
    }

    private void showChooseAmount() {
        System.out.println("\nenter amount:");
    }

}
