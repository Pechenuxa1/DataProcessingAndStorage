import java.util.LinkedList;
import java.util.List;

public class Sorter implements Runnable {
    List<String> list;

    Sorter(List<String> list) {
        this.list = list;
    }

    private final int sleep_time = 5000;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(sleep_time);
                synchronized (list) {
                    bubbleSort();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void bubbleSort() {
        int n = list.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                String element1 = list.get(j);
                String element2 = list.get(j + 1);
                int result = element1.compareTo(element2);
                if (result > 0) {
                    list.set(j, element2);
                    list.set(j + 1, element1);
                    swapped = true;
                }
            }
            if(!swapped) {
                break;
            }
        }
    }
}
