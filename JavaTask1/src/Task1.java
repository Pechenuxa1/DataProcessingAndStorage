public class Task1 {
  public static void main(String[] args) {
    Runnable task = () -> {
      for (int i = 0; i < 5; i++) {
        System.out.printf("ChildThread. String %d\n", i);
      }
    };
    Thread childThread = new Thread(task);
    childThread.start();
    for (int i = 0; i < 5; i++) {
      System.out.printf("ParentThread. String %d\n", i);
    }
  }
}