import java.util.ArrayList;
import java.util.Arrays;

public class Task3 {
  static ArrayList<String> strings1 = new ArrayList<>(Arrays.asList("a", "b", "c"));
  static ArrayList<String> strings2 = new ArrayList<>(Arrays.asList("d", "e", "f"));
  static ArrayList<String> strings3 = new ArrayList<>(Arrays.asList("g", "h", "i"));
  static ArrayList<String> strings4 = new ArrayList<>(Arrays.asList("j", "k", "l"));

  public static void main(String[] args) {
    ArrayList<Thread> childThreads = new ArrayList<>();
    childThreads.add(new Thread(new Worker(strings1)));
    childThreads.add(new Thread(new Worker(strings2)));
    childThreads.add(new Thread(new Worker(strings3)));
    childThreads.add(new Thread(new Worker(strings4)));
    for (Thread childThread : childThreads) {
      childThread.start();
    }
  }

  public static class Worker implements Runnable {

    ArrayList<String> strings;

    Worker(ArrayList<String> strings) {
      this.strings = strings;
    }

    @Override
    public void run() {
      print(strings);
    }
  }

  private static void print(ArrayList<String> strings) {
    for (String letter : strings) {
      System.out.print(letter + " ");
    }
    System.out.println();
  }
}