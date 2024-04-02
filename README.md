# Stock Market Simulator

## Description

### What will the application do?
The application will allow users to learn about the stock market by simulating the buying and selling of stocks, bonds,
and mutual funds. Users will be able to view lists of stocks with their current prices and growth rates, and decide
whether they would like to buy or sell. The user will be able to choose when to move forward in time, and the prices of 
each stock will update as time passes.

### Who will use it?
Anyone who is interested in learning about the stock market might use this application.

### Why is this project of interest to you?
This project is of interest to me because I'd like to learn more about the stock market and maybe pursue a career in
related industries. In terms of programming, I'm interested in implementing this project because I want to 
explore how to perform statistical analysis with Java to include information about growth trends of
each stock and the user's profit.

## User Stories

* Users can buy stocks at a given price
* Users can sell the stocks they own
* Users can choose the amount of shares they would like to buy or sell
* Users can view their transaction histories as a list
* Users can move time forward to generate new prices
* Users can view statistics about the price changes of a particular stock
* Users can view statistics about their net worth, profit, and funds owned
* Users can choose to save the state of all simulation components at any point*
* Users can reload the simulation from when it was last saved and continue from that point*

*Modelled after https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

# Instructions for Grader

- You can generate the first required action related to the user story "adding multiple Xs to a Y" by typing into 
the "filter" box in the transaction panel and clicking enter
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking 
on the "randomize" button in the transaction panel
- You can locate my visual component by looking to each stock panel's graph
- You can save the state of my application by clicking the "save" button in the menu panel
- You can reload the state of my application by clicking the "load" button in the menu panel

# Phase 4: Task 2
Tue Apr 02 16:44:41 PDT 2024 <br>
attempted to buy 4 shares of TSLA but failed due to insufficient funds <br>
Tue Apr 02 16:44:46 PDT 2024 <br>
new transaction added with stock TSLA, amount 1, price 30.0 <br>
Tue Apr 02 16:44:46 PDT 2024 <br>
amount of owned shares of TSLA changed to 1 <br>
Tue Apr 02 16:44:46 PDT 2024 <br>
bought 1 shares of TSLA <br>
Tue Apr 02 16:44:47 PDT 2024 <br>
AAPL price changed to 139.59 <br>
Tue Apr 02 16:44:47 PDT 2024 <br>
GOOG price changed to 113.85 <br>
Tue Apr 02 16:44:47 PDT 2024 <br>
NVDA price changed to 700.81 <br>
Tue Apr 02 16:44:47 PDT 2024 <br>
AMZN price changed to 169.93 <br>
Tue Apr 02 16:44:47 PDT 2024 <br>
RIVN price changed to 20.33 <br> 
Tue Apr 02 16:44:47 PDT 2024 <br>
TSLA price changed to 30.60 <br>
Tue Apr 02 16:44:47 PDT 2024 <br>
net worth changed to  100.60 <br>
Tue Apr 02 16:44:57 PDT 2024 <br>
new transaction added with stock RIVN, amount 1, price 20.33 <br>
Tue Apr 02 16:44:57 PDT 2024 <br>
amount of owned shares of RIVN changed to 1 <br>
Tue Apr 02 16:44:57 PDT 2024 <br>
bought 1 shares of RIVN <br>
Tue Apr 02 16:45:00 PDT 2024 <br>
attempted to sell -3 shares of RIVN but failed due to insufficient amount owned <br>
Tue Apr 02 16:45:02 PDT 2024 <br>
shuffling transaction history <br>
Tue Apr 02 16:45:02 PDT 2024 <br>
new transaction added with stock RIVN, amount 1, price 20.33 <br>
Tue Apr 02 16:45:02 PDT 2024 <br>
new transaction added with stock TSLA, amount 1, price 30.0 <br>
Tue Apr 02 16:45:04 PDT 2024 <br>
filtering transaction history for: rivn <br>
Tue Apr 02 16:45:04 PDT 2024 <br>
new transaction added with stock RIVN, amount 1, price 20.33 <br>
Tue Apr 02 16:45:04 PDT 2024 <br>
filter match: {"amount":1,"price":20.33,"stock name":"RIVN"} <br>
Tue Apr 02 16:45:07 PDT 2024 <br>
new transaction added with stock RIVN, amount -1, price 20.33 <br>
Tue Apr 02 16:45:07 PDT 2024 <br>
amount of owned shares of RIVN changed to -1 <br>
Tue Apr 02 16:45:07 PDT 2024 <br>
sold 1 shares of RIVN

