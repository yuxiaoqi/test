package 并发编程.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public  class ReadWriteLockExample {
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void readData() {
        rwl.readLock().lock();

        try {
            System.out.println("Reading data.");
            Thread.sleep(1000); // Simulate the time-consuming operation
            System.out.println("Read data done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    public void writeData() {
        rwl.writeLock().lock();

        try {
            System.out.println("Writing data.");
            Thread.sleep(1000); // Simulate the time-consuming operation
            System.out.println("Write data done.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLockExample example = new ReadWriteLockExample();
        for (int i = 0; i < 5; i++) {
            new ReadThread(example).start();
            new WriteThread(example).start();
        }
    }
}

class ReadThread extends Thread {
    private ReadWriteLockExample example;

    public ReadThread(ReadWriteLockExample example) {
        this.example = example;
    }

    @Override
    public void run() {
        example.readData();
    }
}

class WriteThread extends Thread {
    private ReadWriteLockExample example;

    public WriteThread(ReadWriteLockExample example) {
        this.example = example;
    }

    @Override
    public void run() {
        example.writeData();
    }
}
