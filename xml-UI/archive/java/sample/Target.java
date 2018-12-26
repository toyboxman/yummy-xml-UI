package cn.gene.debuger;

public class Target {
    private void loop() throws InterruptedException {
        int time = 0;
        int sleepTime = 5000;
        while (true) {
            System.out.println("hit target " + ++time);
            Thread.sleep(sleepTime);
            //System.out.println("just wait " + sleepTime);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("run target program");
        new Target().loop();
    }
}
