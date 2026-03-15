import java.util.Scanner;
import service.BankService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService service = new BankService();

        while (true) {

            System.out.println("\n===== BANK APPLICATION =====");
            System.out.println("1. Create Customer");
            System.out.println("2. Bank Transfer");
            System.out.println("3. Report");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    service.createCustomer();
                    break;

                case 2:
                    service.transferMoney();
                    break;

                case 3:
                    service.report();
                    break;

                case 4:
                    System.out.println("Exiting Application...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}