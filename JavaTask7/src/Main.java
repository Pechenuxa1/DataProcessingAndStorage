public class Main {
  public static void main(String[] args) {
    int numOfThreads = Integer.parseInt(args[0]);
    Work work = new Work(numOfThreads);
    work.startCalc();
  }
}