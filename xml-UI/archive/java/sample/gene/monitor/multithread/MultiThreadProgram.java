package gene.monitor.multithread;

import java.util.Random;

public class MultiThreadProgram {
    private int[] array = new int[10];
    private volatile short index = -1;

    public synchronized void writeInto(int value) {
        while (index >= array.length - 1) {
            System.out.println("WriteThread : " + Thread.currentThread().getName() + " Array is full, wait...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        array[++index] = value;
        System.out.println("WriteThread : " + Thread.currentThread().getName() + " write data : " + value);
        notify();
    }

    public synchronized int readOut() {
        while (index < 0) {
            System.out.println("ReadThread : " + Thread.currentThread().getName() + " No data in array, wait...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int result = array[index];
        index--;
        System.out.println("ReadThread : " + Thread.currentThread().getName() + " Read value : " + result);
        notify();
        return result;
    }

    public static void main(String... args) {
        System.out.println("***************************************");
        System.out.println("Run multi-threads program");
        System.out.println("***************************************");

        MultiThreadProgram program = new MultiThreadProgram();
        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    program.readOut();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "ReadThread-" + i);
            thread.start();
        }

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < 6; i++) {
            Thread thread = new Thread(() -> {
                while (true) {
                    int value = random.nextInt();
                    program.writeInto(value);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "WriteThread-" + i);
            thread.start();
        }

    }
}
