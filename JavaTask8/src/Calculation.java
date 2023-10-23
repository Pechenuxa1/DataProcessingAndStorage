public class Calculation {

  double partResult = 0;
  double denominator;
  int sequenceNumber;
  int numOfThreads;

  Calculation(int firstDenominator, int sequenceNumber, int numOfThreads) {
    denominator = firstDenominator;
    this.sequenceNumber = sequenceNumber;
    this.numOfThreads = numOfThreads;
  }

  public void run() {
    while (denominator > 0) {
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