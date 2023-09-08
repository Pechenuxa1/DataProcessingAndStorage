import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Workers {
  List<Runnable> workers;
  int numOfThreads;
  List<Calculate> calculate;
  private final CyclicBarrier BARRIER;
  private double result = 0;
  Workers(int numOfThreads, List<Calculate> calculate) {
    workers = new ArrayList<>(numOfThreads);
    this.numOfThreads = numOfThreads;
    this.calculate = calculate;
    BARRIER = new CyclicBarrier(numOfThreads, () -> {
      for (Calculate calculate1 : calculate) {
        result += calculate1.getPartResult();
      }
      result *= 4;
      //result = calculate.stream().map(Calculate::getPartResult).reduce(Double::sum).orElse((double) -1);
      System.out.println(result);
    });
  }
  private void init() {
    for (int i = 0; i < numOfThreads; i++) {
      int finalI = i;
      workers.add(() -> {
        calculate.get(finalI).run();
        try {
          BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }
  public void start() {
    init();
    for (Runnable worker : workers) {
      new Thread(worker).start();
    }
  }
}
