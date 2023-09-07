import java.util.ArrayList;

public class Task3 {
  static String[] strings1 = new String[3];
  static String[] strings2 = new String[3];
  static String[] strings3 = new String[3];
  static String[] strings4 = new String[3];

  public static void main(String[] args) {
    ArrayList<Runnable> tasks = new ArrayList<>();

    stringsInitializer();
    tasksInitializer(tasks);
    for (int i = 0; i < 4; i++) {
      Thread childThread = new Thread(tasks.get(i));
      childThread.start();
    }
  }

  private static void print(String[] args) {
    for (int i = 0; i < 3; i++) {
      System.out.print(args[i]);
    }
    System.out.println();
  }

  private static void tasksInitializer(ArrayList<Runnable> tasks) {
    tasks.add(() -> {
      print(strings1);
    });
    tasks.add(() -> {
      print(strings2);
    });
    tasks.add(() -> {
      print(strings3);
    });
    tasks.add(() -> {
      print(strings4);
    });
  }

  private static void stringsInitializer() {
    strings1[0] = "a";
    strings1[1] = "b";
    strings1[2] = "c";

    strings2[0] = "d";
    strings2[1] = "e";
    strings2[2] = "f";

    strings3[0] = "g";
    strings3[1] = "h";
    strings3[2] = "i";

    strings4[0] = "j";
    strings4[1] = "k";
    strings4[2] = "l";
  }
}