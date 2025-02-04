import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1); // Thread pool
        ReentrantLock excelLock = new ReentrantLock(); // Lock for thread-safe Excel writing

        for (int i = 0; i < 1; i++) { // Simulate 10 concurrent requests
            int requestId = i;
            executorService.submit(new ExportTask(requestId, excelLock));
        }

        executorService.shutdown();
    }
}