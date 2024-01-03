package 算法;

public class AlternatePrinting {
    private static final Object lock = new Object();
    private static boolean isOne = false;

    public static void main(String[] args) {
       // ThreadSafe.Thread tt =new ThreadSafe.Thread(()->printNumbers(3));
        Thread thread1 = new Thread(() -> printNumbers(1));
        Thread thread2 = new Thread(() -> printNumbers(2));

        thread1.start();
        thread2.start();
    }

    private static void printNumbers(int number) {
        for (int i = 0; i < 5; i++) {
            synchronized (lock) {
                try {
                    while ((number == 1 && !isOne) || (number == 2 && isOne)) {
                        lock.wait();
                    }
                    System.out.print(number);
                    isOne = !isOne;
                    lock.notifyAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
