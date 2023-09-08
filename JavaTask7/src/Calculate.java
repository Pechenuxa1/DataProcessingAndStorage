import java.util.concurrent.BrokenBarrierException;

public class Calculate {

  double partResult = 0;
  double denominator;
  int sequenceNumber;
  int numOfThreads;

  Calculate(int firstDenominator, int sequenceNumber, int numOfThreads) {
    denominator = firstDenominator;
    this.sequenceNumber = sequenceNumber;
    this.numOfThreads = numOfThreads;
  }

  public void run() {
    while (denominator < 1000000) {
      if (sequenceNumber % 2 == 0) {
        partResult -= 1 / denominator;
      } else {
        partResult += 1 / denominator;
      }
      denominator = (2 * numOfThreads) + denominator;
      sequenceNumber += numOfThreads;
    }
  }

  public double getPartResult() {
    return partResult;
  }
}