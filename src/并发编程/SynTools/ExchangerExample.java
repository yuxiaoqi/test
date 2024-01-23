package 并发编程.SynTools;

import java.util.concurrent.Exchanger;

public class ExchangerExample {
   public static void main(String[] args) {
      Exchanger<String> exchanger = new Exchanger<>();

      Thread t1 = new Thread(() -> {
         try {
            String message = "Message from thread1";
            message = exchanger.exchange(message);
            System.out.println("Thread 1 received: " + message);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      });

      Thread t2 = new Thread(() -> {
         try {
            String message = "Message from thread2";
            message = exchanger.exchange(message);
            System.out.println("Thread 2 received: " + message);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
      });

      t1.start();
      t2.start();
   }
}