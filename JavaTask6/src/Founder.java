import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
  private final List<Runnable> workers;
  private final Company company;
  private final CyclicBarrier BARRIER;

  public Founder(final Company company) {
    this.workers = new ArrayList<>(company.getDepartmentsCount());
    this.company = company;
    BARRIER = new CyclicBarrier(company.getDepartmentsCount(), new GetResult());
  }

  public void start() {
    initWorkers();
      for (final Runnable worker : workers) {
        new Thread(worker).start();
      }
  }

  private void initWorkers() {
    for (int i = 0; i < company.getDepartmentsCount(); i++) {
      int finalI = i;
      workers.add(() -> {
        company.getFreeDepartment(finalI).performCalculations();
        try {
          BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
          throw new RuntimeException(e);
        }
      });
    }
  }

  private class GetResult implements Runnable {

    @Override
    public void run() {
      company.showCollaborativeResult();
    }
  }
}