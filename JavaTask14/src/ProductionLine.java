import java.util.concurrent.Semaphore;

public class ProductionLine implements Runnable {
  Semaphore semaphoreC = new Semaphore(0);
  Semaphore semaphoreAB = new Semaphore(0);
  int numOfWidgets;

  ManufacturerOfDetailC detailC;
  ManufacturerOfModuleAB moduleAB;
  Thread createC = new Thread(detailC);
  Thread createAB = new Thread(moduleAB);

  ProductionLine(int numOfWidgets) {
    this.numOfWidgets = numOfWidgets;
    detailC = new ManufacturerOfDetailC(semaphoreC, numOfWidgets);
    moduleAB = new ManufacturerOfModuleAB(semaphoreAB, numOfWidgets);
  }

  @Override
  public void run() {
    createC.start();
    createAB.start();
    for (int i = 0; i < numOfWidgets; i++) {
      try {
        semaphoreC.acquire();
        semaphoreAB.acquire();
        System.out.printf("The widget %d is made!\n", i);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
  }
}
