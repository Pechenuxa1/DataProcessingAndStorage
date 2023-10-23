import java.util.concurrent.atomic.AtomicInteger;

public class Philosopher implements Runnable{
    private final Object leftFork;
    private final Object rightFork;
    private final int name;

    Philosopher(Object leftFork, Object rightFork, int name) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
    }
    @Override
    public void run() {
        while(true) {
            try {
                think();
                synchronized (leftFork) {
                    tookFork("left");
                    synchronized (rightFork) {
                        tookFork("right");
                        eating();
                        putDownFork("right");
                    }
                    putDownFork("left");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void think() throws InterruptedException {
        System.out.println("Philosopher " + name + " is thinking");
        Thread.sleep(1000);
    }

    private void tookFork(String fork) {
        System.out.println("Philosopher " + name + " took " + fork + " fork");
    }
    private void putDownFork(String fork) {
        System.out.println("Philosopher " + name + " put down " + fork + " fork");
    }
    private void eating() throws InterruptedException {
        System.out.println("Philosopher " + name + " is eating");
        Thread.sleep(1000);
    }
}
