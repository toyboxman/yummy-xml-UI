package king.law.calculate;

import java.util.BitSet;
import java.util.Random;

public class BitPercentage {
    private BitSet bitSet;

    private Float percentage;
    private int scope;
    private long start;
    private long stop;

    public BitPercentage(int range) {
        bitSet = new BitSet(range);
        this.scope = range;
    }

    private void setPercentage(float setPercentage) {
        start = System.currentTimeMillis();
        this.percentage = setPercentage;
        int value = (int) (this.percentage * scope);
        System.out.println("percentage value: " + value);
        Random random = new Random();
        for (int i = 0; i < value; i++) {
            int index = random.nextInt(scope);
            while (bitSet.get(index)) {
//                System.out.println("duplicated random index: " + index);
                index = random.nextInt(scope);
            }
//            System.out.println("random index: " + index);
            bitSet.set(index);
        }
        stop = System.currentTimeMillis();
    }

    private void setPercentage1(float setPercentage) {
        start = System.currentTimeMillis();
        bitSet.clear();
        this.percentage = setPercentage;
        int value = (int) (this.percentage * scope);
        System.out.println("percentage value: " + value);
        bitSet.set(0, value);
        Random random = new Random();
        int hit = 0;
        for (int i = 0; i < scope; i++) {
            if (bitSet.get(random.nextInt(scope))) {
                hit++;
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("hit number is : " + hit);
        System.out.println("hit percentage is : " + ((float) hit / (float) scope));
    }

    private void setPercentage2(float setPercentage) {
        start = System.currentTimeMillis();
        this.percentage = setPercentage;
        int value = (int) (this.percentage * scope);
        System.out.println("percentage value: " + value);
        Random random = new Random();
        int hit = 0;
        for (int i = 0; i < scope; i++) {
            if (random.nextInt(scope) < value) {
                hit++;
            }
        }
        stop = System.currentTimeMillis();
        System.out.println("hit number is : " + hit);
        System.out.println("hit percentage is : " + ((float) hit / (float) scope));
    }

    void printTime() {
        System.out.println("execution duration is : " + (stop - start));
    }

    public static void main(String[] args) {
        BitPercentage percentage = new BitPercentage((int) 1E5);
        percentage.setPercentage(0.5f);
        percentage.printTime();
        System.out.println("BitSet value: " + percentage.bitSet.stream().count());
        System.out.println("");
        System.out.println("-------------------------------------------------------------");
        System.out.println("");

        percentage.setPercentage1(0.3f);
        percentage.printTime();

        System.out.println("");
        System.out.println("-------------------------------------------------------------");
        System.out.println("");

        percentage.setPercentage1(0.71f);
        percentage.printTime();
    }
}
