package king.law.asm.src;

import java.util.Random;

public class ClassA {
    private int number;

    public ClassA(int index) {
        this.number = index;
    }

    private int index() {
        return this.number;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "number=" + (number) +
                '}';
    }
}
