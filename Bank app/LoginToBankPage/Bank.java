package LoginToBankPage;
import java.util.Scanner;

public class Bank {
    public static void open() {
        Scanner peanut = new Scanner(System.in);
        boolean running = true;
        
        while (running) {
            System.out.println("Good day (username), how can we help you today?");  //(username) would be replaced with the username entered in the log in page
            System.out.println("Find below the options for you to choose from:");
            menu();
            int input = peanut.nextInt();
        
            switch(input) {
                case 1:
                    newBankBalance(peanut);  
                    break;
                case 2:
                    expenses(peanut);
                    break;
                case 3:
                    exit();
                    running = false;
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

    private static void exit() {
        System.out.println("Good day (username)"); //(username) would be replaced with the username entered in the log in page
        LoginPage.logPage();
    }
}
