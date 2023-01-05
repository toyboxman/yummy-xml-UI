package king.law.concurrent;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ExecutorScheduler {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        AtomicLong atomicLong = new AtomicLong();
        Random random = new Random();
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            try {
                int i = random.nextInt(3);
                if(i == 0) i = 1;
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("scheduleAtFixedRate-" + atomicInteger.getAndIncrement()
                    + "@" + Instant.now().getEpochSecond());

        }, 1, 1, TimeUnit.SECONDS);

        executorService.scheduleWithFixedDelay(() -> {
            try {
                int i = random.nextInt(3);
                if(i == 0) i = 1;
                Thread.sleep(i * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("scheduleWithFixedDelay-" + atomicLong.getAndIncrement()
                    + "@" + Instant.now().getEpochSecond());
        }, 2, 1, TimeUnit.SECONDS);

        executorService.execute(() -> {System.out.println("execute...");});
    }
}
