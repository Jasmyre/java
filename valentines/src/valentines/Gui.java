package valentines;

import Util.RoundedBorder;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Gui extends JFrame {
  private static final int IMAGE_WIDTH = 450;
  private static final int IMAGE_HEIGHT = 450;

  private final Map<String, Slide> slidesById = new LinkedHashMap<>();
  private final Map<String, String> imagePaths = new LinkedHashMap<>();
  private final GridBagConstraints rootGbc = new GridBagConstraints();

  private JLabel imageLabel;
  private JLabel messageLabel;
  private JPanel buttonPanel;

  private String currentSlideId;

  private static class Choice {
    private final String text;
    private final String nextSlideId;

    private Choice(String text, String nextSlideId) {
      this.text = text;
      this.nextSlideId = nextSlideId;
    }
  }

  private static class Slide {
    private final String id;
    private final String message;
    private final String imageKey;
    private final Choice[] choices;
    private final boolean clearThenExit;
    private final int exitDelayMs;

    private Slide(String id, String message, String imageKey,
                  Choice[] choices) {
      this(id, message, imageKey, choices, false, 0);
    }

    private Slide(String id, String message, String imageKey, Choice[] choices,
                  boolean clearThenExit, int exitDelayMs) {
      this.id = id;
      this.message = message;
      this.imageKey = imageKey;
      this.choices = choices;
      this.clearThenExit = clearThenExit;
      this.exitDelayMs = exitDelayMs;
    }
  }

  public Gui(String title) {
    this.setTitle(title);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(1200, 1000);
    this.setLocationRelativeTo(null);

    buildUiShell();
    buildSlides();
    showSlide("intro_1");
  }

  private void buildUiShell() {
    this.setLayout(new GridBagLayout());

    imageLabel = new JLabel();
    imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    imageLabel.setBorder(new RoundedBorder(20));
    rootGbc.gridx = 0;
    rootGbc.gridy = 0;
    rootGbc.insets = new Insets(40, 0, 20, 0);
    rootGbc.anchor = GridBagConstraints.CENTER;
    this.add(imageLabel, rootGbc);

    messageLabel = new JLabel("", SwingConstants.CENTER);
    messageLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
    rootGbc.gridy = 1;
    rootGbc.insets = new Insets(10, 10, 10, 10);
    this.add(messageLabel, rootGbc);

    buttonPanel = new JPanel(new GridBagLayout());
    rootGbc.gridy = 2;
    rootGbc.insets = new Insets(20, 0, 20, 0);
    this.add(buttonPanel, rootGbc);
  }

  private void buildSlides() {
    buildImagePaths();

    String[][] introSlides = {
        {"intro_1", "Hi Darlene!", "TAP ME", "intro_2"},
        {"intro_2", "Alam mo naman na i really really really like u right?",
         "RIGHT?!", "intro_3"},
        {"intro_3", "Soooooooo.....", "SO WHAT!", "ask_main"}};

    for (String[] row : introSlides) {
      addSlide(row[0], row[1], row[0], new Choice(row[2], row[3]));
    }

    addBinarySlide("ask_main", "Will you be my Girl?", "YES!", "accepted_intro",
                   "NO", "no_1");
    addBinarySlide("no_1", "WILL YOU PLEASE BE MY GIRL????", "YES!",
                   "accepted_intro", "NO", "no_2");
    addBinarySlide("no_2", "SIGE NA PLS", "OO NA!", "accepted_intro", "AYOKO",
                   "no_3");
    addBinarySlide("no_3", "AYAW MO PA RIN BA", "GUSTO NA", "accepted_intro",
                   "AYOKO", "no_4");
    addBinarySlide("no_4", "Ah ayaw mo", "GUSTO NA", "accepted_intro", "TALAGA",
                   "no_5");
    addBinarySlide("no_5", "ayaw mo parin ah", "GUSTO NA", "accepted_intro",
                   "TALAGA", "no_6");
    addBinarySlide("no_6", "Ah tamo talaga oh", "GUSTO NA", "accepted_intro",
                   "TALAGA", "no_7");

    addSlide("no_7", "Wala na tinanggal ko na", "no_7",
             new Choice("WOW", "no_8"));
    addSlide("no_8", "Tanungin kita ulit ah hehehhehe", "no_8",
             new Choice("E ANO PA BA", "no_9"));
    addSlide("no_9", "Darlene, will you be my girl?", "no_9",
             new Choice("YES", "accepted_intro"));

    addSlide("accepted_intro", "YAY SO ANG PLANS SO FAR AYYYYYY",
             "accepted_intro", new Choice("ANO", "plans"));

    String plansText =
        "1. Unli kwentuhan hanggang madaling araw.\n"
        + "2. Random food trips.\n"
        + "3. Study dates\n"
        + "4. Good morning & good night messages everyday.\n"
        + "5. Supporting each other sa goals.\n"
        + "6. Making memories na pwede nating balikan balang araw.\n\n"
        + "And pinaka important:\n"
        + "I'll always choose you. 🫶";

    addSlide("plans", plansText, "plans", new Choice("hmmm", "ending"));
    addSlide(
        "ending",
        "THAT'S ALL Darlene!!! THANK YOU SO MUCHIE!!!!!! HOPE YOU'RE HAPPY MWAAAAA",
        "ending", new Choice("MWAAAAA", "love_u"));
    addSlide("love_u", "LOVE U", "love_u",
             new Choice("I LOVE YOU SO MUCHIE", "exit_clear"));

    slidesById.put("exit_clear", new Slide("exit_clear", "", "exit_clear",
                                           new Choice[0], true, 2000));

    addSlide("flow_error", "Flow error: missing slide.", "flow_error",
             new Choice("CLOSE", "__CLOSE__"));
  }

  private void buildImagePaths() {
    imagePaths.put("intro_1", "lib/assets/goobers.jpg");
    imagePaths.put("intro_2", "lib/assets/cat_1.jpg");
    imagePaths.put("intro_3", "lib/assets/shy.jpg");
    imagePaths.put("ask_main", "lib/assets/blush.jpg");
    imagePaths.put("no_1", "lib/assets/sad.jpg");
    imagePaths.put("no_2", "lib/assets/;((.jpg");
    imagePaths.put("no_3", "lib/assets/bananana.jpg");
    imagePaths.put("no_4", "lib/assets/disappointed.jpg");
    imagePaths.put("no_5", "lib/assets/sus.jpg");
    imagePaths.put("no_6", "lib/assets/pls.jpg");
    imagePaths.put("no_7", "lib/assets/wala_na.jpg");
    imagePaths.put("no_8", "lib/assets/cat_1.jpg");
    imagePaths.put("no_9", "lib/assets/chill_cat.jpg");
    imagePaths.put("accepted_intro", "lib/assets/blushing_love_cat.jpg");
    imagePaths.put("plans", "lib/assets/plan.jpg");
    imagePaths.put("ending", "lib/assets/genuine.jpg");
    imagePaths.put("love_u", "lib/assets/2_flowers.jpg");
  }

  private void addBinarySlide(String id, String message, String firstText,
                              String firstNext, String secondText,
                              String secondNext) {
    addSlide(id, message, id, new Choice(firstText, firstNext),
             new Choice(secondText, secondNext));
  }

  private void addSlide(String id, String message, String imageKey,
                        Choice... choices) {
    slidesById.put(id, new Slide(id, message, imageKey, choices));
  }

  private void showSlide(String slideId) {
    Slide slide = slidesById.get(slideId);
    if (slide == null) {
      slide = slidesById.get("flow_error");
    }

    currentSlideId = slide.id;
    if (slide.clearThenExit) {
      clearAndExitAfterDelay(slide.exitDelayMs);
      return;
    }

    BufferedImage image = loadSlideImage(slide);
    Image scaledImage =
        image.getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(scaledImage));

    messageLabel.setText(toHtml(slide.message));
    renderButtons(slide.choices);

    this.revalidate();
    this.repaint();
  }

  private void renderButtons(Choice[] choices) {
    buttonPanel.removeAll();

    Choice[] safeChoices = choices;
    if (safeChoices == null || safeChoices.length == 0) {
      safeChoices = new Choice[] {new Choice("CLOSE", "__CLOSE__")};
    }

    GridBagConstraints buttonGbc = new GridBagConstraints();
    buttonGbc.insets = new Insets(8, 8, 8, 8);

    for (int i = 0; i < safeChoices.length; i++) {
      Choice choice = safeChoices[i];
      JButton button = createChoiceButton(choice);

      if (safeChoices.length == 1) {
        buttonGbc.gridx = 0;
        buttonGbc.gridy = 0;
        buttonGbc.gridwidth = 2;
      } else {
        buttonGbc.gridx = i % 2;
        buttonGbc.gridy = i / 2;
        buttonGbc.gridwidth = 1;
      }

      buttonPanel.add(button, buttonGbc);
    }
  }

  private JButton createChoiceButton(Choice choice) {
    JButton button = new JButton(choice.text);
    button.setFocusable(false);
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    button.addActionListener(e -> onChoice(choice));
    return button;
  }

  private void onChoice(Choice choice) {
    if (choice == null) {
      return;
    }

    if ("__CLOSE__".equals(choice.nextSlideId)) {
      this.dispose();
      return;
    }

    showSlide(choice.nextSlideId);
  }

  private BufferedImage loadSlideImage(Slide slide) {
    String relativePath = imagePaths.get(slide.imageKey);
    if (relativePath == null || relativePath.trim().isEmpty()) {
      return createPlaceholderImage(slide.imageKey, slide.id);
    }

    String normalizedRelativePath = relativePath.replace("/", "\\");
    File file = new File(System.getProperty("user.dir") + "\\.."
                         + "\\" + normalizedRelativePath);

    try {
      BufferedImage fileImage = ImageIO.read(file);
      if (fileImage != null) {
        return fileImage;
      }
    } catch (IOException ignored) {
      // Fallback to generated placeholder when file is unreadable.
    }

    return createPlaceholderImage(slide.imageKey, slide.id);
  }

  private BufferedImage createPlaceholderImage(String imageKey,
                                               String slideTitle) {
    BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT,
                                            BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = image.createGraphics();

    g2d.setColor(new java.awt.Color(255, 228, 236));
    g2d.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);

    g2d.setColor(new java.awt.Color(214, 106, 140));
    g2d.drawRoundRect(8, 8, IMAGE_WIDTH - 16, IMAGE_HEIGHT - 16, 30, 30);

    g2d.setFont(new Font("Verdana", Font.BOLD, 20));
    g2d.drawString("Placeholder", 140, 200);

    g2d.setFont(new Font("Verdana", Font.PLAIN, 14));
    g2d.drawString("Slide: " + slideTitle, 150, 235);
    g2d.drawString("Image Key: " + imageKey, 135, 260);

    g2d.dispose();
    return image;
  }

  private String toHtml(String plainText) {
    String safeText = plainText;
    if (safeText == null || safeText.isEmpty()) {
      safeText = " ";
    }

    safeText = safeText.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\n", "<br>");

    return "<html><div style='text-align: center; width: 820px;'>" + safeText +
        "</div></html>";
  }

  private void clearAndExitAfterDelay(int delayMs) {
    this.getContentPane().removeAll();
    this.getContentPane().revalidate();
    this.getContentPane().repaint();

    Timer exitTimer = new Timer(delayMs, e -> {
      this.dispose();
      System.exit(0);
    });

    exitTimer.setRepeats(false);
    exitTimer.start();
  }

  public void showFrame() { this.setVisible(true); }
}
