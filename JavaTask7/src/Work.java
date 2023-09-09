import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Work {
  List<Runnable> workers;
  int numOfThreads;
  List<Calculation> calculations = new ArrayList<>();
  private final CyclicBarrier BARRIER;
  private double result = 0;

  Work(int numOfThreads) {
    workers = new ArrayList<>(numOfThreads);
    this.numOfThreads = numOfThreads;
    BARRIER = new CyclicBarrier(numOfThreads, () -> {
      for (Calculation calculation : calculations) {
        result += calculation.getPartResult();
      }
      result *= 4;
      System.out.println(result);
    });
  }

  private void initCalc() {
    int denominator = 1;
    for (int i = 1; i <= numOfThreads; i++) {
      calculations.add(new Calculation(denominator, i, numOfThreads));
      denominator += 2;
    }
  }

  private void initWorkers() {
    for (int i = 0; i < numOfThreads; i++) {
      int finalI = i;
      workers.add(() -> {
        calculations.get(finalI).run();
        try {
          BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

  public void startCalc() {
    initCalc();
    initWorkers();
    for (Runnable worker : workers) {
      new Thread(worker).start();
    }
  }
}
