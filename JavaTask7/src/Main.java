public class Main {
  public static void main(String[] args) {
    int numOfThreads = Integer.parseInt(args[0]);
    Logic logic = new Logic(numOfThreads);
    logic.start();
  }
}