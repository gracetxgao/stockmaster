package ui;

import model.*;
//import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

// Console based stock market simulator
public class ConsoleApp {
    private Scanner input;
    private static final String JSON_STORE_PROFILE = "./data/profile.json";
    private static final String JSON_STORE_STOCKS = "./data/stocks.json";
    private Profile profile;
    private List<Stock> stocks;
    private Boolean stop;
    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;

    // EFFECTS: runs console app
    public ConsoleApp() {
        start();
    }

    // MODIFIES: this
    // EFFECTS: initializes all fields
    private void init() {
        stop = false;
        input = new Scanner(System.in);
        Stock apple = new Stock("AAPL", BigDecimal.valueOf(150.00));
        Stock google = new Stock("GOOG", BigDecimal.valueOf(125.00));
        Stock nvidia = new Stock("NVDA", BigDecimal.valueOf(720.00));
        Stock amazon = new Stock("AMZN", BigDecimal.valueOf(175.00));
        Stock rivian = new Stock("RIVN", BigDecimal.valueOf(20.00));
        Stock tesla = new Stock("TSLA", BigDecimal.valueOf(30.00));
        stocks = new ArrayList<>(Arrays.asList(apple, google, nvidia, amazon, rivian, tesla));
        profile = new Profile(stocks);
        input.useDelimiter("\n");
        jsonWriter = new JsonWriter(JSON_STORE_PROFILE);
//        jsonReader = new JsonReader();
    }

    // MODIFIES: this
    // EFFECTS: starts app and processes user input
    private void start() {
        init();
        showUserStatus();
        showMarketStatus();
        while (!stop) {
            showOpeningOptions();
            String userInput = input.nextLine().toLowerCase();
            handleOpeningOptions(userInput);
        }
        System.out.println("\tFunds: $" + profile.getFunds());
        System.out.println("\tProfit: $" + profile.getProfit());
        System.out.println("\tNet Worth: $" + profile.getNetWorth());
        System.out.println("Simulator ended.");
    }

    // EFFECTS: shows current market status
    private void showMarketStatus() {
        System.out.println("Current stock prices:");
        for (Stock s : stocks) {
            System.out.println("\t" + s.getCompany() + " - $" + s.getPrice());
        }
    }

    // EFFECTS: shows current user status
    private void showUserStatus() {
        System.out.println("Current user status:");
        System.out.println("\tFunds: $" + profile.getFunds());
        System.out.println("\tProfit: $" + profile.getProfit());
        System.out.println("\tNet Worth: $" + profile.getNetWorth());
        System.out.println("\tShares owned: " + profile.getOwnedStocks());
    }

    // EFFECTS: shows user options
    private void showOpeningOptions() {
        System.out.println("Select from:");
        System.out.println("\t1 - Buy stocks");
        System.out.println("\t2 - Sell stocks");
        System.out.println("\t3 - View transaction history");
        System.out.println("\t4 - View stock price history");
        System.out.println("\t5 - Advance to next day");
        System.out.println("\t6 - View profile information");
        System.out.println("\t7 - Save simulation status to file");
        System.out.println("\t8 - Load simulation status from file");
        System.out.println("\tq - Quit");
    }

    // MODIFIES: this
    // EFFECTS: handles user input for opening options
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
            showMarketStatus();
        } else if (userInput.equals("6")) {
            showUserStatus();
        } else if (userInput.equals("7")) {
            saveStatus();
        } else {
            stop = true;
        }
    }

    // EFFECTS: displays stock options
    private void showChooseStock() {
        System.out.println("Choose from:");
        for (int i = 0; i < stocks.size(); i++) {
            System.out.println("\t" + i + " - " + stocks.get(i).getCompany() + ", " + stocks.get(i).getPrice());
        }
    }

    // MODIFIES: this
    // EFFECTS: allow user to purchase X amounts of the stock
    private void handleBuyStock(String userInput) {
        Stock chosenStock = stocks.get(Integer.parseInt(userInput));
        if (userInput.equals("q")) {
            stop = true;
        } else {
            showChooseAmount();
            int amount = Integer.parseInt(input.nextLine());
            profile.buyStock(chosenStock, amount);
        }
        showContinue();
        userInput = input.nextLine().toLowerCase();
        handleContinue(userInput);
    }

    // MODIFIES: this
    // EFFECTS: allow user to sell X amounts of the stock
    private void handleSellStock(String userInput) {
        Stock chosenStock = stocks.get(Integer.parseInt(userInput));
        if (userInput.equals("q")) {
            stop = true;
        } else {
            showChooseAmount();
            int amount = (-1 * Integer.parseInt(input.nextLine()));
            profile.sellStock(chosenStock, amount);
        }
        showContinue();
        userInput = input.nextLine().toLowerCase();
        handleContinue(userInput);
    }

    // EFFECTS: shows user transaction history
    private void handleViewTransactionHistory() {
        for (String s : profile.getTransactionHistory().getTransactionHistory()) {
            System.out.println(s);
        }
    }

    // MODIFIES: this
    // EFFECTS: shows price history for given stock
    private void handleViewStockPriceHistory(String userInput) {
        if (userInput.equals("q")) {
            stop = true;
        } else {
            System.out.println(stocks.get(Integer.parseInt(userInput)).viewHistory());
        }
    }

    // MODIFIES: this
    // EFFECTS: generates new prices for each stock and updates net worth accordingly
    private void handleNextDay() {
        for (Stock s : stocks) {
            BigDecimal prevPrice = s.getPrice();
            s.getNewPrice(getPercentageChange());
            BigDecimal newPrice = s.getPrice();
            BigDecimal change = newPrice.subtract(prevPrice);
            int amountOwned = profile.getOwnedStocks().get(s.getCompany());
            for (int i = 0; i < amountOwned; i++) {
                profile.changeNetWorth(change);
            }
        }
    }

    // EFFECTS: generates value for percentage change (as decimal, eg 0.01)
    //          if value is odd, turn negative (arbitrary choice so that value does not only grow)
    private double getPercentageChange() {
        double change = Math.random() / 10;
        if (Math.round(change * 1000) % 2 == 1) {
            change = -change;
        }
        return change;
    }

    private void showContinue() {
        System.out.println("Continue? (y/n)");
    }

    private void showChooseAmount() {
        System.out.println("Enter amount:");
    }

    // MODIFIES: this
    // EFFECTS: quits application according to input
    private void handleContinue(String userInput) {
        if (userInput.equals("n")) {
            stop = true;
        }
    }

    // EFFECTS: saves profile and market status to file
    private void saveStatus() {
        try {
            jsonWriter.open();
            jsonWriter.write(profile);
            jsonWriter.close();
            System.out.println("Saved profile and market status to " + JSON_STORE_PROFILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_PROFILE);
        }
    }

//    // MODIFIES: this
//    // EFFECTS: loads profile and market status from file
//    private void loadStatus() {
//        profile = jsonReader.read(profile);
//        stocks = jsonReader.read(stocks);
//        System.out.println("Loaded profile and market status from " + JSON_STORE);
//    }
}
