import java.util.Scanner;

public class Concept {
  private static final Scanner scanner = new Scanner(System.in);
  private static boolean isRunning = true;

  public static void main(String[] args) {
    do {
      phase1();
      lastPhase();
    } while (isRunning);

    System.out.println("Salamat sa paglalaro <3");
    scanner.close();
  }

  private static int showScene(String[] lines, String[] choices) {
    System.out.println();

    for (String line : lines) {
      System.out.println(line);
    }

    for (int i = 0; i < choices.length; i++) {
      System.out.println("(" + (i + 1) + ") " + choices[i]);
    }

    System.out.print("> ");
    return scanner.nextInt();
  }

  private static void cuddlePath() {
    String[][] scenes = {
        {"lapit ka nga dito <3"},
        {"ang comfy mo pala katabi...", "parang ang tahimik ng mundo :)"},
        {"ano bang gusto mo gawin sa valentines?"},
        {"hala...", "ang sweet mo magsalita ^^"},
        {"kahit ganun...", "kinikilig pa rin ako <3"},
        {"pwede ba akong maging clingy ngayon?"},
        {"salamat sa pag-stay...", "ang calm lang ng feeling :>"},
        {"happy valentines <3", "thank you kasi pinili mo ako today :)"}};

    String[][] choices = {
        {"okay, andito na ako", "sandali lang, kinakabahan ako"},
        {"oo nga, ang gaan sa pakiramdam", "tahimik pero hindi awkward"},
        {"basta ikaw kasama, okay na", "simple lang, walang arte"},
        {"totoo naman sinasabi ko", "hindi ko sinasadya yun"},
        {"medyo kinilig din ako", "hindi ko alam sasabihin ko"},
        {"oo, okay lang", "konti lang ha"},
        {"gusto ko rin yung ganito", "tahimik lang pero okay"},
        {}};

    for (int i = 0; i < scenes.length; i++) {
      showScene(scenes[i], choices[i]);
    }
  }

  private static void accepted() {
    cuddlePath();
  }

  private static void phase1() {
    String[] lines = {"hey...", "napindot mo ako :)"};

    String[] choices = {"hehe sorry", "aksidente lang"};

    int choice = showScene(lines, choices);

    if (choice == 1) {
      phase2();
    } else if (choice == 2) {
      tease1();
    } else {
      System.out.println("hala mali ;_;");
    }
  }

  private static void phase2() {
    System.out.println();
    System.out.println("since nandito ka na...");
    System.out.println("pwede ba kita tanungin? <3");
    System.out.println("(1) sige tanong mo na");
    System.out.println("(2) parang ayoko");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 1)
      valentineAsk();
    else
      tease1();
  }

  private static void valentineAsk() {
    System.out.println();
    System.out.println("will you be my valentine? <3");
    System.out.println("(1) oo naman");
    System.out.println("(2) ha? baliw ka ba");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 1)
      accepted();
    else
      insist1();
  }

  // ----------- INSIST / TSUNDERE PATH -----------

  private static void tease1() {
    System.out.println();
    System.out.println("weh...");
    System.out.println("pero nagbabasa ka pa rin ^^");
    System.out.println("(1) oo nga eh");
    System.out.println("(2) hindi ah");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 1)
      valentineAsk();
    else
      insist1();
  }

  private static void insist1() {
    System.out.println();
    System.out.println("konting lambing lang oh ;_;");
    System.out.println("(1) sige na nga");
    System.out.println("(2) kulit mo");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 1)
      accepted();
    else
      insist2();
  }

  private static void insist2() {
    System.out.println();
    System.out.println("edi wag...");
    System.out.println("pero malungkot ako :(");
    System.out.println("(1) wag ka na sad");
    System.out.println("(2) bahala ka");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 1)
      accepted();
    else
      insist3();
  }

  private static void insist3() {
    System.out.println();
    System.out.println("huling tanong na talaga...");
    System.out.println("valentine kita <3");
    System.out.println("(1) oo na nga");
    System.out.println("(2) ...");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 1)
      accepted();
    else
      rejected();
  }

  // ----------- REJECTED -----------

  private static void rejected() {
    System.out.println();
    System.out.println("okay lang...");
    System.out.println("salamat pa rin sa oras mo ;_;");
  }

  // ----------- LAST -----------

  private static void lastPhase() {
    System.out.println();
    System.out.println("(1) ulit");
    System.out.println("(2) exit");
    System.out.print("> ");

    int choice = scanner.nextInt();
    if (choice == 2)
      isRunning = false;
  }
}
