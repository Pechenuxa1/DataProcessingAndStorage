import static java.lang.Thread.sleep;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    Runnable task = () -> {
      for (int i = 0;; i++) {
        if(Thread.currentThread().isInterrupted()) {
          System.out.println();
          System.out.println("The end...");
          break;
        }
        if (i % 100 == 0) {
          System.out.println();
        }
        System.out.print("a");
      }
    };
    Thread childThread = new Thread(task);
    childThread.start();
    sleep(2000);
    childThread.interrupt();
  }
}