import classes.Cart;
import classes.Product;

public class App {
    private static Cart cart = new Cart(); 

    public static void main(String[] args) throws Exception {
        System.out.println();
        System.out.println("=== SHOPPING CART ===");

        System.out.println();
        cart.addProduct(new Product("Laptop", 4500));
        cart.addProduct(new Product("Mouse", 500));
        cart.addProduct(new Product("Keyboard", 1500));

        cart.showProducts();

        cart.getTotalPrice();

        cart.removeProduct("Mouse");

        cart.showProducts();

        cart.getTotalPrice();

        System.out.println();
        System.out.println("Thank you for shopping!");

    }
}
