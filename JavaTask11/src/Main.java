import java.util.concurrent.Semaphore;

public class Main {
  private static final Semaphore parentSem = new Semaphore(1);
  private static final Semaphore childSem = new Semaphore(0);

  public static void main(String[] args) throws InterruptedException {
    Runnable task = () -> {
      for (int i = 0; i < 5; i++) {
        try {
          childSem.acquire();
          System.out.printf("ChildThread. String %d\n", i);
          parentSem.release();
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }
    };
    Thread childThread = new Thread(task);
    childThread.start();
    for (int i = 0; i < 5; i++) {
      parentSem.acquire();
      System.out.printf("ParentThread. String %d\n", i);
      childSem.release();
    }
  }
}