import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Work {
  List<Runnable> workers;
  int numOfThreads;
  List<Calculation> calculations = new ArrayList<>();
  private double result = 0;

  Work(int numOfThreads) {
    workers = new ArrayList<>(numOfThreads);
    this.numOfThreads = numOfThreads;
    Signal.handle(new Signal("INT"), sig -> {
      result = calculations.stream().map(Calculation::getPartResult).reduce(0.0, Double::sum) * 4.0;
      System.out.println(result);
      System.exit(0);
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
