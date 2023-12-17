package darie.mitulescu;

import javafx.scene.control.Alert;

public class BankAccount {

    private String accountNumber;
    private double balance;
    private String customerName;
    private String email;
    private String phoneNumber;

    public BankAccount() {
        this("-1", 0, "Default name", "Default address", "default phone");
        // System.out.println("Empty constructor call");
    }

    public BankAccount(String accountNumber, double balance, String customerName, String email, String phoneNumber) {
        // System.out.println("Account constructor with parameters called");
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }

    public BankAccount(String customerName, String email, String phoneNumber) {
        this("99999", 100.55, customerName, email, phoneNumber);
    }

    public BankAccount(String accountNumber, String customerName, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = 0;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double deposit(double depositAmount) {
        this.balance += depositAmount;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Transaction");
        alert.setHeaderText(null);
        alert.setContentText("Deposit of " + depositAmount + " made. New balance is " + this.balance);
        alert.showAndWait();
        return this.balance;
    }

    public boolean withdraw(double withdrawalAmount) {
        boolean success = false;
        if (withdrawalAmount > this.balance) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Insufficient funds. Only " + this.balance + " available. Withdrawal not processed");
            alert.showAndWait();
        }

        else {
            this.balance -= withdrawalAmount;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Withdrawal of " + withdrawalAmount + " processed. Remaining balance = " + this.balance);
            alert.showAndWait();
            success = true;
        }

        return success;
    }
}
