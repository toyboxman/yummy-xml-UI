package king.law.trace;

import java.util.ArrayList;

public class RateLimiter {
    double thruput;
    private io.jaegertracing.internal.utils.RateLimiter rateLimiter;

    public void setThruput(double maxTracesPerSecond) {
        this.thruput = maxTracesPerSecond;
        double maxBalance = this.thruput < 2.0D ? 1.5D : maxTracesPerSecond;
        this.rateLimiter = new io.jaegertracing.internal.utils.RateLimiter(maxTracesPerSecond, maxBalance);
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        limiter.setThruput(15d);
        ArrayList<Object> list = new ArrayList<>(100);
        long timeMillis = System.currentTimeMillis();
        for (int i = 0; i < 95; i++) {
            Thread.sleep(20);
            System.out.println("one time execution duration: " + (System.currentTimeMillis() - timeMillis));
            if (limiter.rateLimiter.checkCredit(1.5D)) {
                list.add(i);
                System.out.println("permitted by rate limiter: " + i);
            } else {
                System.out.println("forbidden by rate limiter: " + i);
            }
        }
        System.out.println("");
        System.out.println("-------------------------------------------------------------");
        System.out.println("");
        System.out.println("total permitted : " + list.size());
    }
}
