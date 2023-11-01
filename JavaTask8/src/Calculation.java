import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Calculation {

  double partResult = 0;
  double denominator;
  int sequenceNumber;
  int numOfThreads;
  private final CyclicBarrier barrier;

  Calculation(int firstDenominator, int sequenceNumber, int numOfThreads, CyclicBarrier barrier) {
    denominator = firstDenominator;
    this.sequenceNumber = sequenceNumber;
    this.numOfThreads = numOfThreads;
    this.barrier = barrier;
  }

  public void run() {
    while (denominator > 0) {
      if (sequenceNumber % 2 == 0) {
        partResult -= (1 / denominator);
      } else {
        partResult += (1 / denominator);
      }
      denominator = (2 * numOfThreads) + denominator;
      sequenceNumber += numOfThreads;
      try {
        barrier.await();
      } catch (InterruptedException | BrokenBarrierException e) {
        throw new RuntimeException(e);
      }
    }
  }

  public double getPartResult() {
    return partResult;
  }
}