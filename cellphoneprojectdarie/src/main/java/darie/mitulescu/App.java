package darie.mitulescu;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
    private static MobilePhone phone = new MobilePhone("phone number");
    private static BankAccount billGatesAccount = new BankAccount("1", 1000, "Bill Gates", "iamrich@gmail.com",
            "im too rich for you to know my phonenumber");
    private static double walletMoney = 0;
    private static Pane contactsPane = new Pane();
    private static Pane gamblePane = new Pane();
    private static Pane bankPane = new Pane();
    private static Pane callPane = new Pane();
    private static Scene contactsScene = new Scene(contactsPane, 400, 600);
    private static Scene bankScene = new Scene(bankPane, 400, 600);
    private static Scene gambleScene = new Scene(gamblePane, 400, 600);
    private static Scene callScene = new Scene(callPane, 400, 600);
    private static BorderPane mainLayout = new BorderPane();
    private static Scene mainScene = new Scene(mainLayout, 400, 600);
    private static Text phoneNumber = new Text();

    private static Text money = new Text("Bank Balance: " + String.valueOf(billGatesAccount.getBalance()));
    private static Text currentMoney = new Text("Wallet: " + walletMoney);
    private static Text gambleMoney = new Text("Wallet: " + walletMoney);
    private static Button homeButton = new Button("Home");

    private static ArrayList<Button> contactsButtons = new ArrayList<Button>();

    private static VBox buttonBox = new VBox(10);
    private static VBox buttonBox2 = new VBox(50);

    private static Text noContacts = new Text("No contacts available");

    private static boolean removeModeOn = false, fakeGambler = true, firstTime = true;
    private static double debt = 0;
    private static Text debtMoney = new Text("Debt: " + debt);
    private static Text debtPayoff = new Text("Debt: " + debt);

    private static Stage window;

    public static ImageView apple = new ImageView("/apple.png");
    public static ImageView call = new ImageView("/call.png");

    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        window.setTitle("eyePhone");

        // Create menu bar
        MenuBar menuBar = createMenuBar();

        // Create layout for the main content
        mainLayout.setTop(menuBar);

        // Create buttons for different features
        Button contactsButton = new Button("Contacts");
        Button gambleButton = new Button("Las Vegas");
        Button bankingButton = new Button("Bank");

        contactsButton.setOnAction(e -> handleContactsButton());
        gambleButton.setOnAction(e -> handleGambleButton());
        bankingButton.setOnAction(e -> handleBankingButton());
        homeButton.setOnAction(e -> handleHomeButton());

        // Create a VBox to hold the buttons
        VBox buttonBox2 = new VBox(10, contactsButton, gambleButton, bankingButton);
        buttonBox2.setAlignment(Pos.CENTER);
        mainLayout.setCenter(buttonBox2);

        // Create the main scene
        Text title = new Text("eyePhone");
        title.setFont(new Font(STYLESHEET_CASPIAN, 50));
        mainLayout.getChildren().add(title);
        title.setLayoutX(90);
        title.setLayoutY(100);
        mainLayout.getChildren().add(apple);
        apple.setScaleX(0.2);
        apple.setScaleY(0.2);
        window.setScene(mainScene);
        window.show();

        // create all other scenes
        createBankScene();
        createContactsScene();
        callSceneCreator();
        createGambleScene();
    }

    public void createContactsScene() {

        Button addContactsButton = new Button("Add Contacts");
        Button removeContactsButton = new Button("Remove Contacts");
        addContactsButton.setOnAction(e -> handleAddContactsButton());
        removeContactsButton.setOnAction(e -> handleremoveContactsButton());
        contactsPane.getChildren().add(noContacts);
        contactsPane.getChildren().add(addContactsButton);
        contactsPane.getChildren().add(removeContactsButton);

        homeButton.setLayoutX(180);
        homeButton.setLayoutY(550);

        addContactsButton.setLayoutX(300);
        addContactsButton.setLayoutY(20);
        removeContactsButton.setLayoutX(300);
        removeContactsButton.setLayoutY(50);

        noContacts.setLayoutX(150);
        noContacts.setLayoutY(300);

    }

    public void createBankScene() {
        Button withdrawButton = new Button("Withdraw");
        Button depositButton = new Button("Deposit");
        Button payOffDebtButton = new Button("Pay off debt");
        money.setFont(new Font(STYLESHEET_CASPIAN, 30));
        bankPane.getChildren().add(money);
        money.setLayoutX(30);
        money.setLayoutY(70);
        currentMoney.setFont(new Font(STYLESHEET_CASPIAN, 30));
        bankPane.getChildren().add(currentMoney);
        bankPane.getChildren().add(debtPayoff);
        debtPayoff.setLayoutX(30);
        debtPayoff.setLayoutY(130);
        debtPayoff.setFont(new Font(STYLESHEET_CASPIAN, 30));
        currentMoney.setLayoutX(30);
        currentMoney.setLayoutY(100);
        withdrawButton.setOnAction(e -> handleWithdrawButton());
        depositButton.setOnAction(e -> handleDepositButton());
        payOffDebtButton.setOnAction(e -> handlePayOffDebtButton());
        bankPane.getChildren().add(buttonBox2);
        buttonBox2.setAlignment(Pos.CENTER);
        buttonBox2.setLayoutX(170);
        buttonBox2.setLayoutY(250);
        buttonBox2.getChildren().add(depositButton);
        buttonBox2.getChildren().add(withdrawButton);
        buttonBox2.getChildren().add(payOffDebtButton);
    }

    public void handlePayOffDebtButton() {
        if (walletMoney >= (0 - debt)) {
            walletMoney += debt;
            debt = 0;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Congratulations!");
            alert.setHeaderText(null);
            alert.setContentText("CONGRATULATIONS!!!! YOU ARE DEBT FREE!! YOU CAN GAMBLE WITH YOUR OWN MONEY NOW!!");
            alert.showAndWait();
            debtPayoff.setText(("Debt: " + debt));
            money.setText("Bank Balance: " + String.valueOf(billGatesAccount.getBalance()));
            currentMoney.setText("Wallet: " + String.valueOf(walletMoney));

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("What is wrong with you?");
            alert.setHeaderText(null);
            alert.setContentText(
                    "The FBI has been looking for you... \nSince you were unable to payoff your debt fully, the bank is charging you with the interest incurred on your debt balance.");
            alert.showAndWait();
            walletMoney = 0;
            debt -= 10000;
            debtPayoff.setText(("Debt: " + debt));
            money.setText("Bank Balance: " + String.valueOf(billGatesAccount.getBalance()));
            currentMoney.setText("Wallet: " + String.valueOf(walletMoney));
        }
    }

    public void handleDepositButton() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Deposit");
        dialog.setHeaderText("Deposit");
        dialog.setContentText("Deposit Amount:");
        dialog.showAndWait().ifPresent(deposit -> {
            if (walletMoney >= Double.parseDouble(deposit)) {
                // if deposit is possible (youre not broke)
                billGatesAccount.deposit(Double.parseDouble(deposit));
                walletMoney -= Double.parseDouble(deposit);
                // update text
                money.setText("Bank Balance: " + String.valueOf(billGatesAccount.getBalance()));
                currentMoney.setText("Wallet: " + String.valueOf(walletMoney));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Insufficient funds. Only " + walletMoney + " available. Deposit not processed");
                alert.showAndWait();
            }
        });
    }

    public void handleWithdrawButton() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Withdraw");
        dialog.setHeaderText("Withdraw:");
        dialog.setContentText("Withdrawal Amount:");
        dialog.showAndWait().ifPresent(withdraw -> {
            if (billGatesAccount.withdraw(Double.parseDouble(withdraw))) {
                // if true, increase pocketmoney
                walletMoney += Double.parseDouble(withdraw);
                // update text
                money.setText("Bank Balance: " + String.valueOf(billGatesAccount.getBalance()));
                currentMoney.setText("Wallet: " + String.valueOf(walletMoney));
            }
        });
    }

    public void createGambleScene() {
        ImageView gambler = new ImageView("/gambler.png");
        gamblePane.getChildren().add(gambler);
        gambler.setScaleX(0.8);
        gambler.setScaleY(0.8);
        gambler.setLayoutX(-30);
        gambler.setLayoutY(-10);
        Button gambleButton = new Button("Click Here to Win Big!!");
        gambleButton.setOnAction(e -> handleGamblingButton());
        gambleButton.setPrefWidth(300);
        gambleButton.setPrefHeight(100);
        gambleButton.setStyle("-fx-background-color: #00FF00; -fx-font-size: 20px;");
        gamblePane.getChildren().add(gambleButton);
        gambleButton.setLayoutX(50);
        gambleButton.setLayoutY(400);
        gamblePane.getChildren().add(gambleMoney);
        gambleMoney.setFont(new Font(STYLESHEET_CASPIAN, 20));
        debtMoney.setFont(new Font(STYLESHEET_CASPIAN, 20));
        gambleMoney.setLayoutX(70);
        gambleMoney.setLayoutY(360);
        gamblePane.getChildren().add(debtMoney);
        debtMoney.setLayoutX(70);
        debtMoney.setLayoutY(390);
    }

    public void handleGamblingButton() {
        if (walletMoney <= 5) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Loan??");
            alert.setHeaderText(null);
            alert.setContentText(
                    "It has come to my attention that you are dirt poor. \nIt's ok!!! \nI will give you a bank loan!!! \nJust keep gambling!!");
            alert.showAndWait();
            walletMoney = 1000;
            debt -= 1000;
            gambleMoney.setText(("Wallet: " + walletMoney));
            debtMoney.setText(("Debt: " + debt));
        } else {
            if (fakeGambler) {
                if (walletMoney < 100 && walletMoney > 5) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Brokie");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "You are too broke to gamble. \nWomp Womp. \nLucky for you, I have an offer you can't refuse!! \nThe next gamble will be all or nothing(click gamble button again)!! \nYou can 10X your money, or lose it all! \nRemember: \"99% of gamblers quit before they win big!!\"");
                    alert.showAndWait();
                    // bro is a real gambler
                    fakeGambler = false;
                } else {
                    int luckyNumber = (int) (Math.random() * 100);
                    if (luckyNumber >= 99) {
                        walletMoney *= 1000;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("JACKPOTTTT!!");
                        alert.setHeaderText(null);
                        alert.setContentText(
                                "YOU JUST HIT THE JACKPOT!! GENERATIONAL WEALTH!! \nYOU CAN MAKE EVEN MOREEEE MONEY GAMBLING NOW!");
                        alert.showAndWait();
                        gambleMoney.setText(("Wallet: " + walletMoney));
                    } else if (luckyNumber <= 10) {
                        walletMoney =0;
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Uh Oh");
                        alert.setHeaderText(null);
                        alert.setContentText(
                                "You got caught gambling by Mr. Nucci. \n it's over for you");
                        alert.showAndWait();
                        gambleMoney.setText(("Wallet: " + walletMoney));
                    }
                    else {
                        // random outcome, either 2x your money or lose half
                        boolean win;
                        if (luckyNumber > 60) {
                            // cheeky lil casino edge by choosing 60
                            win = true;
                        } else {
                            win = false;
                        }
                        if (win) {
                            walletMoney = (Math.round((walletMoney * 2) * 100)) / 100.0;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("WINNNER");
                            alert.setHeaderText(null);
                            alert.setContentText("WINNER WINNER CHICKN DINNER! KEEP GAMBLING TO WIN BIG!!");
                            alert.showAndWait();
                            gambleMoney.setText(("Wallet: " + walletMoney));
                        } else {
                            walletMoney = (Math.round((walletMoney / 2.0) * 100)) / 100.0;
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("LOOOOSERRRR :(");
                            alert.setHeaderText(null);
                            alert.setContentText(
                                    "UNLUCKY! You lost, but the next gamble is guarenteed to win!! Double or Nothing!!");
                            alert.showAndWait();
                            gambleMoney.setText(("Wallet: " + walletMoney));
                        }
                    }
                }

            } else {
                // bro is a gambler... all or nothing
                boolean win;
                if ((int) (Math.random() * 100) > 60) {
                    // cheeky lil casino edge by choosing 60
                    win = true;
                } else
                    win = false;

                if (win) {
                    walletMoney *= 10;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("WINNNER");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "WINNER WINNER CHICKN DINNER! You just 10X your money!!!! KEEP GAMBLING TO WIN BIG!!");
                    alert.showAndWait();
                    gambleMoney.setText(("Wallet: " + walletMoney));
                    fakeGambler = true;
                } else {
                    walletMoney = 0;
                    billGatesAccount.setBalance(0);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("LOOOOSERRRR :(");
                    alert.setHeaderText(null);
                    alert.setContentText(
                            "UNLUCKY! You lost! \nWe took your house, car, wife, kids, and the rest of your bank account as collateral!! \nUnfortunately, bro gambled too much. ");
                    alert.showAndWait();
                    gambleMoney.setText(("Wallet: " + walletMoney));
                    fakeGambler = true;
                }
            }
        }
    }

    public void handleHomeButton() {
        contactsPane.getChildren().remove(homeButton);
        callPane.getChildren().remove(homeButton);
        gamblePane.getChildren().remove(homeButton);
        bankPane.getChildren().remove(homeButton);
        window.setScene(mainScene);
        // bro was a pussy and didn't gamble when he could win 10X
        fakeGambler = true;
    }

    public void handleremoveContactsButton() {
        removeModeOn = true;
        phone.printListOfContacts();
        if (removeModeOn) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Remove Contacts");
            alert.setHeaderText(null);
            alert.setContentText("Click on contact you would like to remove");
            alert.showAndWait();
        }
    }

    public void handleAddContactsButton() {
        // make something pop up and let them input a contact
        // Prompt the user for input
        removeModeOn = false;
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Contact");
        dialog.setHeaderText("Enter Contact Name:");
        dialog.setContentText("Contact Name:");
        dialog.showAndWait().ifPresent(name -> {
            TextInputDialog dialog2 = new TextInputDialog();
            dialog2.setTitle("Add Contact");
            dialog2.setHeaderText("Enter Phone Number:");
            dialog2.setContentText("Phone Number:");
            dialog2.showAndWait().ifPresent(number -> {
                if (name.equals("") || number.equals("")) {
                    // do nothing
                } else {
                    phone.addContact(name, number);
                    // if contact was added
                    // create button to access this contact
                    //phone.getContacts gets the arraylist of contacts in the phone class, then get the index of the contact if you can find the name stored.
                    Button button = new Button(phone.getContacts().get(phone.findContact(name)).getName());
                    contactsButtons.add(button);
                    if (firstTime) {
                        // only remove on the first time
                        contactsPane.getChildren().remove(noContacts);
                        contactsPane.getChildren().add(buttonBox);
                        buttonBox.setAlignment(Pos.CENTER);
                        buttonBox.setLayoutX(170);
                        buttonBox.setLayoutY(10);
                        firstTime = false;
                    }
                    buttonBox.setMaxWidth(100);
                    //add to button box using the index of the contacts list in phone class
                    buttonBox.getChildren().add(contactsButtons.get(phone.findContact(name)));
                    // set contact action
                    contactsButtons.get(phone.findContact(name)).setOnAction(new ButtonClickHandler());
                }

            });
        });

    }

    // Event handler class for button clicks
    private static class ButtonClickHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            Button sourceButton = (Button) event.getSource();
            if (removeModeOn) {
                // remove contact from phone list
                phone.removeContact(sourceButton.getText());
                //remove button from button box
                buttonBox.getChildren().remove(sourceButton);
                //remove button from button array
                contactsButtons.remove(sourceButton);
                removeModeOn = false;
            } else {
                // call person
                callPane.getChildren().add(homeButton);
                phoneNumber.setText(phone.getContacts().get(phone.findContact(sourceButton.getText())).getPhoneNumber());
                // phoneNumber.setText(sourceButton.get);
                window.setScene(callScene);
            }
        }
    }

    private void callSceneCreator() {
        callPane.getChildren().add(call);
        callPane.getChildren().add(phoneNumber);
        phoneNumber.setLayoutX(60);
        phoneNumber.setLayoutY(520);
        phoneNumber.setFill(Color.WHITE);
        phoneNumber.setFont(new Font(STYLESHEET_CASPIAN, 50));
    }

    private void handleContactsButton() {
        // Add code to handle the button
        contactsPane.getChildren().add(homeButton);
        window.setScene(contactsScene);
    }

    private void handleGambleButton() {
        // Add code to handle the button
        gamblePane.getChildren().add(homeButton);
        debtMoney.setText("Debt:" + debt);
        gambleMoney.setText(("Wallet: " + walletMoney));
        window.setScene(gambleScene);
    }

    private void handleBankingButton() {
        // Add code to handle the button
        money.setText("Bank Balance: " + String.valueOf(billGatesAccount.getBalance()));
        currentMoney.setText("Wallet: " + String.valueOf(walletMoney));
        debtPayoff.setText("Debt: " + String.valueOf(debt));
        bankPane.getChildren().add(homeButton);
        window.setScene(bankScene);
    }

    private MenuBar createMenuBar() {
        // Create File menu
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().add(exitMenuItem);

        // Create Help menu
        Menu helpMenu = new Menu("Help");
        MenuItem aboutMenuItem = new MenuItem("About");
        aboutMenuItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutMenuItem);

        // Create the menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu);
        return menuBar;
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About eyePhone");
        alert.setHeaderText(null);
        alert.setContentText("eyePhone: better than the \"iPhone\"\n\n"
                + "Version: 1.0\n\n"
                + "Developed by Darie Mitulescu");

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }

}