import java.io.IOException;
import valentines.Gui;

public class App {
  public static void main(String[] args) throws IOException {
    System.out.println(System.getProperty("user.dir"));
    Gui gui = new Gui("BE MY VALENTINE");
    gui.showFrame();
  }
}
