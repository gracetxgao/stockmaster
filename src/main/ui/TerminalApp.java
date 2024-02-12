package ui;

import model.*;

import java.util.Random;
import java.util.Scanner;

public class TerminalApp {
    private Scanner input;
    private Profile profile;
    private Boolean stop;
    private String userInput;
    private Random rand;

    private Stock apple;
    private Stock google;
    private Stock nvidia;
    private Stock amazon;
    private Stock rivian;
    private Stock tesla;

    public TerminalApp() {
        start();
    }

    private void init() {
        profile = new Profile("name");
        stop = false;
        input = new Scanner(System.in);
        rand = new Random();
        apple = new Apple("AAPL", 150);
        google = new Google("GOOG", 125);
        nvidia = new Nvidia("NVDA", 720);
        amazon = new Amazon("AMZN", 175);
        rivian = new Rivian("RIVN", 20);
        tesla = new Tesla("TSLA", 30);
        input.useDelimiter("\n");
    }

    private void start() {
        init();
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
        System.out.println("Current prices:\n");
        System.out.println("\tApple - " + apple.getPrice());
        System.out.println("\tGoogle - " + google.getPrice());
        System.out.println("\tNvidia - " + nvidia.getPrice());
        System.out.println("\tAmazon - " + amazon.getPrice());
        System.out.println("\tRivian - " + rivian.getPrice());
        System.out.println("\tTesla - " + tesla.getPrice());
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
            int amount = Integer.parseInt(input.nextLine());
            for (int i = 0; i < amount; i++) {
                if (userInput.equals("1")) {
                    profile.buyStock(apple);
                } else if (userInput.equals("2")) {
                    profile.buyStock(google);
                } else if (userInput.equals("3")) {
                    profile.buyStock(nvidia);
                } else if (userInput.equals("4")) {
                    profile.buyStock(amazon);
                } else if (userInput.equals("5")) {
                    profile.buyStock(rivian);
                } else if (userInput.equals("6")) {
                    profile.buyStock(tesla);
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    private void handleSellStock(String userInput) {
        if (userInput.equals("q")) {
            stop = true;
        } else {
            showChooseAmount();
            int amount = Integer.parseInt(input.nextLine());
            for (int i = 0; i < amount; i++) {
                if (userInput.equals("1")) {
                    profile.sellStock(apple);
                } else if (userInput.equals("2")) {
                    profile.sellStock(google);
                } else if (userInput.equals("3")) {
                    profile.sellStock(nvidia);
                } else if (userInput.equals("4")) {
                    profile.sellStock(amazon);
                } else if (userInput.equals("5")) {
                    profile.sellStock(rivian);
                } else if (userInput.equals("6")) {
                    profile.sellStock(tesla);
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    private void showChooseAmount() {
        System.out.println("\nenter amount:");
    }

    private void handleViewTransactionHistory() {
        profile.viewTransactionHistory();
    }

    private void handleViewStockPriceHistory(String userInput) {
        if (userInput.equals("q")) {
            stop = true;
        } else {
            showChooseAmount();
            int amount = Integer.parseInt(input.nextLine());
            for (int i = 0; i < amount; i++) {
                if (userInput.equals("1")) {
                    apple.viewHistory();
                } else if (userInput.equals("2")) {
                    google.viewHistory();
                } else if (userInput.equals("3")) {
                    nvidia.viewHistory();
                } else if (userInput.equals("4")) {
                    amazon.viewHistory();
                } else if (userInput.equals("5")) {
                    rivian.viewHistory();
                } else if (userInput.equals("6")) {
                    tesla.viewHistory();
                } else {
                    System.out.println("Invalid input");
                }
            }
        }
    }

    private void handleNextDay() {
        int change = rand.nextInt(100);
        apple.getNewPrice(change);
        google.getNewPrice(change);
        nvidia.getNewPrice(change);
        amazon.getNewPrice(change);
        rivian.getNewPrice(change);
        tesla.getNewPrice(change);
    }

}
