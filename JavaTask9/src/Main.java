import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Object> forks = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            forks.add(new Object());
        }
        for (int i = 0; i < 5; i++) {
            if (i == 4) {
                new Thread(new Philosopher(forks.get(i), forks.get((i + 1) % 5), i + 1)).start();
            } else {
                new Thread(new Philosopher(forks.get((i + 1) % 5), forks.get(i), i + 1)).start();
            }
        }
    }
}