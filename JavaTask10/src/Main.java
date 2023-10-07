
public class Main {
  private static final Object monitor = new Object();
  private static boolean parentTurn = true;

  public static void main(String[] args) throws InterruptedException {
    Runnable task = () -> {
      for (int i = 0; i < 5; i++) {
        synchronized (monitor) {
          try {
            while (parentTurn) {
              monitor.wait();
            }
            System.out.printf("ChildThread. String %d\n", i);
            parentTurn = true;
            monitor.notify();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
        }
      }
    };
    Thread childThread = new Thread(task);
    childThread.start();
    for (int i = 0; i < 5; i++) {
      synchronized (monitor) {
        while (!parentTurn) {
          monitor.wait();
        }
        System.out.printf("ParentThread. String %d\n", i);
        parentTurn = false;
        monitor.notify();
      }
    }
  }
}