import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class loginPage{

    private static Map<String, String> accounts = new HashMap<>();

    public static void logPage(){
        Scanner  peanut = new Scanner(System.in);
        boolean running = true;

        while(running){
            print();

            
            int choice = peanut.nextInt();
            peanut.nextLine();

            switch(choice){
                case 1:
                    login(peanut);
                    break;
                case 2:
                    createLogin(peanut);
                    break;
                case 3 :
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");                                                               
            }
        } 
        peanut.close();
    }



    public static void print(){
        System.out.println("Press 1 to continue to login: ");
        System.out.println("Press 2 to create a new account: ");
        System.out.println("Press 3 to cancel: ");
        System.out.print("Enter your choice: ");
    }

    private static void login(Scanner peanut){

        System.out.println("Enter username:");
        String username = peanut.nextLine();
        System.out.println("Enter password:");
        String password = peanut.nextLine();

        if(accounts.containsKey(username) && accounts.get(username).equals(password)){
            System.out.println("You have logged in successfully");
            
        }else {
            System.out.println("Error: Account not found or incorrect login details.");
        }
    }   

    private static void createLogin(Scanner peanut){
        System.out.println("Enter username:");
        String username = peanut.nextLine();
        System.out.println("Enter password:");
        String password = peanut.nextLine();

        accounts.put(username, password);
        System.out.println("Account created successfully");
    }

}
