public class Account {
  @SuppressWarnings("unused") private String accountNumber;

  private int pin;
  private double balance;

  public Account(String accountNumber, int pin, double initialBalance) {
    this.accountNumber = accountNumber;
    this.pin = pin;
    this.balance = initialBalance;
  }

  public boolean verifyPin(int inputPin) { return this.pin == inputPin; }

  public double getBalance() { return this.balance; }

  public void deposit(double amount) {
    if (amount > 0) {
      this.balance += amount;
    }
  }

  public boolean withdraw(double amount) {
    // Fixing the condition to properly check for invalid amounts:
    // negative or exceeding balance
    if (amount <= 0 || amount > this.balance) {
      return false;
    }

    this.balance -= amount;
    return true;
  }

  protected int getPin() { return this.pin; }
}
