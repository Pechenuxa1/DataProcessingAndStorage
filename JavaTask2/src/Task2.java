public class Task2 {
  public static void main(String[] args) throws InterruptedException {
    Runnable task = () -> {
      for (int i = 0; i < 5; i++) {
        System.out.printf("ChildThread. String %d\n", i);
      }
    };
    Thread childThread = new Thread(task);
    childThread.start();
    childThread.join();
    for (int i = 0; i < 5; i++) {
      System.out.printf("ParentThread. String %d\n", i);
    }
  }
}