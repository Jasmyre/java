import java.util.Scanner;

public class ATM {
  private static final Scanner scanner = new Scanner(System.in);
  private Account account;

  public ATM(Account account) { this.account = account; }

  public void start() {
    System.out.println("Welcome to simple ATM");
    System.out.print("Enter PIN: ");
    int enteredPin = scanner.nextInt();

    System.out.println();
    if (enteredPin == account.getPin()) {
      System.out.println("Login successful.");
    } else {
      System.out.println("Invalid PIN.");
    }

    showMenu();
  }

  public void showMenu() {
    boolean _continue = true;
    do {

      System.out.println();
      System.out.println("1. Check Balance");
      System.out.println("2. Deposit");
      System.out.println("3. Withdraw");
      System.out.println("4. Exit");
      System.out.print("Select an option: ");
      int choice = scanner.nextInt();

      System.out.println();
      switch (choice) {
      case 1:
        System.out.println("Your balance is: $" + account.getBalance());
        break;
      case 2:
        System.out.print("Enter amount to deposit: ");
        double depositAmount = scanner.nextDouble();
        account.deposit(depositAmount);
        System.out.println("Deposit successful.");
        break;
      case 3:
        System.out.print("Enter amount to withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        boolean withdrawalSuccess = account.withdraw(withdrawAmount);
        if (withdrawalSuccess) {
          System.out.println("Withdrawal successful.");
        } else {
          System.out.println("Insufficient funds.");
        }
        break;
      case 4:
        _continue = false;
        System.out.println("Thank you for using the ATM!");
        break;
      default:
        System.out.println("Invalid option.");
      }
    } while (_continue);
  }
}
