public class App {
  public static void main(String[] args) throws Exception {
    ATM atm = new ATM(new Account("123456789", 1234, 1000.0));
    atm.start();
  }
}
