package LoginToBankPage;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginPage {

    private static Map<String, String> accounts = new HashMap<>();

    public static void main(String[] args) {
        logPage();
    }

    public static void logPage() {
        Scanner peanut = new Scanner(System.in);
        boolean running = true;

        while (running) {
            print();

            int choice = peanut.nextInt();
            peanut.nextLine();

            switch (choice) {
                case 1:
                    login(peanut);
                    break;
                case 2:
                    createLogin(peanut);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        peanut.close();
    }

    public static void print() {
        System.out.println("Press 1 to continue to login: ");
        System.out.println("Press 2 to create a new account: ");
        System.out.println("Press 3 to cancel: ");
        System.out.print("Enter your choice: ");
    }

    private static void login(Scanner peanut) {
        System.out.println("Enter username:");
        String username = peanut.nextLine();
        System.out.println("Enter password:");
        String password = peanut.nextLine();

        if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
            System.out.println("You have logged in successfully");
            open(username);
        } else {
            System.out.println("Error: Account not found or incorrect login details.");
        }
    }

    private static void createLogin(Scanner peanut) {
        System.out.println("Enter username:");
        String username = peanut.nextLine();
        System.out.println("Enter password:");
        String password = peanut.nextLine();

        accounts.put(username, password);
        System.out.println("Account created successfully");
    }

    public static void open(String username) {
        Scanner peanut = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Good day " + username + ", how can we help you today?");
            System.out.println("Find below the options for you to choose from:");
            menu();
            int input = peanut.nextInt();

            switch (input) {
                case 1:
                    newBankBalance(peanut);
                    break;
                case 2:
                    expenses(peanut);
                    break;
                case 3:
                    running = false;
                    exit(username);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        peanut.close();
    }

    public static void menu() {
        System.out.println("Press 1 to calculate your monthly expenses:");
        System.out.println("Press 2 if you need help to calculate your monthly expenses:");
        System.out.println("Press 3 to exit the program:");
    }

    private static void newBankBalance(Scanner peanut) {  
        
        System.out.println("Please enter your current bank balance:");  
        int amount = peanut.nextInt();

        if (amount != 0) {
            System.out.println("Your current bank balance is: " + "R" + amount);  
        } else {
            System.out.println("You have no money in your bank balance");  
        }

        System.out.println("Please enter your expenses:");
        int expenses = peanut.nextInt();
        
        if (amount < expenses) {
            System.out.println("You owe the bank = " + "R" + (expenses - amount));
        } else if (amount > expenses) {
            System.out.println("Congratulations, you saved = " + "R" + (amount - expenses));  
        } else {
            System.out.println("Great news! You have broken even!");  
        }
    }

    private static void expenses(Scanner peanut) {
        System.out.println("Press 1 to start calculating your expenses:");
        System.out.println("Press 0 if you are done");
        int choice = peanut.nextInt();
        
        switch (choice) {
            case 1:
                System.out.println("Keep entering your expenses:");
                int total = 0;
                int calculate;
                
                while ((calculate = peanut.nextInt()) != 0) {
                    total += calculate;
                }
                
                System.out.println("Your total expenses are: " + total);
                break;
            case 0:
                System.out.println("No expenses entered.");
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    private static void exit(String username) {
        System.out.println("Good day (username)"); //(username) would be replaced with the username entered in the log in page
        LoginPage.logPage();
    }
}
