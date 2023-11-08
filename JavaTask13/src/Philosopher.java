public class Philosopher implements Runnable {
    private final Object leftFork;
    private final Object rightFork;
    private final int name;
    private boolean isLeftFork;
    private boolean isRightFork;

    Philosopher(Object leftFork, Object rightFork, int name, boolean isLeftFork, boolean isRightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.name = name;
        this.isLeftFork = isLeftFork;
        this.isRightFork = isRightFork;
    }

    @Override
    public void run() {
        while (true) {
            try {
                think();
                synchronized (leftFork) {
                    isLeftFork = false;
                    tookFork("left");
                    while (!isRightFork) {
                        isLeftFork = true;
                        putDownFork("left");
                        leftFork.wait();
                        tookFork("left");
                    }
                    synchronized (rightFork) {
                        isRightFork = false;
                        tookFork("right");
                        eating();
                        putDownFork("right");
                        isRightFork = true;
                    }
                    putDownFork("left");
                    isLeftFork = true;
                    leftFork.notify();
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