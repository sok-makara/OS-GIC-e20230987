import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
public class PoolDemo {
    static class Task implements Runnable {
        private int taskId;
        Task(int id) { this.taskId = id; }
        @Override
        public void run() {
            System.out.printf("Task %d started on thread %s (ID: %d)%n", taskId, Thread.currentThread().getName(), Thread.currentThread().getId());
            try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
            System.out.printf("Task %d completed on thread %s%n", taskId, Thread.currentThread().getName());
        }
    }
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main: creating thread pool with 2 threads for 6 tasks\n");
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 1; i <= 6; i++) { pool.submit(new Task(i)); }
        pool.shutdown();
        pool.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("\nMain: all tasks completed. Pool shut down.");
    }
}
