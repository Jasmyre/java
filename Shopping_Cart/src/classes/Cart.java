package classes;

import java.util.ArrayList;
import java.util.List;

public class Cart {
  private static List<Product> products = new ArrayList<>();

  public void addProduct(Product product) {
    products.add(product);
    System.out.println("Product added: " + product.getName() + " - "
                       + "₱" + product.getPrice());
  }

  public boolean removeProduct(String name) {
    System.out.println();
    System.out.println("Removing product: " + name);
    boolean removeSuccess =
        products.removeIf(product -> product.getName().equals(name));

    if (removeSuccess) {
      System.out.println("Product removed successfully.");
    } else {
      System.err.println("Unable to remove product.");
    }

    return removeSuccess;
  }

  public double getTotalPrice() {
    double total = products.stream().mapToDouble(Product::getPrice).sum();
    System.out.println();
    System.out.println("Total Price: " + total);
    return total;
  }

  public void showProducts() {
    System.out.println();
    System.out.println("--- Cart Items ---");
    for (int i = 0; i < products.size(); i++) {
      System.out.println((i+1) + ". " + products.get(i).getName() +
                         ", Price: " + products.get(i).getPrice());
    }
  }
}
