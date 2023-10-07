import java.util.concurrent.Semaphore;

public class ManufacturerOfModuleAB implements ManufacturerOfWidgetDetails {
  Semaphore semaphore;
  int numOfWidgets;

  ManufacturerOfModuleAB(Semaphore semaphore, int numOfWidgets) {
    this.semaphore = semaphore;
    this.numOfWidgets = numOfWidgets;
  }

  @Override
  public void createDetail() {
    try {
      Thread.sleep(1000);
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  @Override
  public void run() {
    for (int i = 0; i < numOfWidgets; i++) {
      createDetail();
      semaphore.release();
    }
  }
}
