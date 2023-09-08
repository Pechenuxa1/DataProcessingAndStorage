import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Logic {
  final int numOfThreads;
  List<Calculate> calc;


  Logic(final int numOfThreads) {
    this.numOfThreads = numOfThreads;
    calc = new ArrayList<>(numOfThreads);
  }

  public void start() {
    init();
    new Workers(numOfThreads, calc).start();
  }

  private void init() {
    int denominator = 1;
    for (int i = 1; i <= numOfThreads; i++) {
      calc.add(new Calculate(denominator, i, numOfThreads));
      denominator += 2;
    }
  }


}
