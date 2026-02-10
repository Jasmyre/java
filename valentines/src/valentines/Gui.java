package valentines;

import Util.RoundedBorder;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui extends JFrame {
  private final GridBagConstraints gbc = new GridBagConstraints();

  public Gui(String title) throws IOException {
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 1000);

    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);

    BufferedImage myPicture = ImageIO.read(new File(
        System.getProperty("user.dir") + "\\..\\lib\\assets\\goobers.gif"));

    Image scaledImage =
        myPicture.getScaledInstance(450, 450, Image.SCALE_SMOOTH);

    JLabel catImage = new JLabel(new ImageIcon(scaledImage));
    catImage.setPreferredSize(new Dimension(450, 450));
    catImage.setBorder(new RoundedBorder(20));
    gbc.insets = new Insets(40, 0, 40, 0);
    this.add(catImage);

    JLabel text = new JLabel("WILL YOU BE MY VALENTINE? :)");
    text.setFont(new Font("Verdana", Font.PLAIN, 18));
    gbc.gridy = 1;
    gbc.insets = new Insets(10, 0, 10, 0);
    this.add(text, gbc);

    JPanel buttonPanel = new JPanel();
    gbc.gridy = 2;
    this.add(buttonPanel, gbc);

    JButton button1 = new JButton("Yes");
    button1.addActionListener(
        (e) -> { System.out.println("Yes, I will be your Valentine!"); });
    button1.setFocusable(false);
    button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    buttonPanel.add(button1);

    JButton button2 = new JButton("No");
    button2.addActionListener(
        (e) -> { System.out.println("No, I won't be your Valentine!"); });
    button2.setFocusable(false);
    button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
    buttonPanel.add(button2, gbc);
  }

  public void showFrame() { this.setVisible(true); }
}
