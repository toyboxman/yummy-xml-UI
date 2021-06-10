package king.law.calculate;

public class Calculation {
    public static void main(String[] args) {
        System.out.println(5E3);
        System.out.println(5E2D);
        //Int64.MaxValue   (9223372036854775807 or 9.223372036854775807E+18)
        System.out.println((long)(9.223372036854776E18D * 0.2f));
        System.out.println((long)(1E5 * 1.0f));
        System.out.println((long)(1E5 * 0f));
        System.out.println("1.0 < 1.01 is " + (1.0f < 1.01f));
        System.out.println("1.0 <= 1.01 is " + (1.0f <= 1.01f));
        System.out.println("1.0 <= 1.00 is " + (1.0f <= 1.00f));
        System.out.println("1.0 < 0.99 is " + (1.0f < 0.99f));
        System.out.println("1.0 > -0.99 is " + (1.0f > -0.99f));
        System.out.println(5 % 3);
        System.out.println(5 % 4);
        System.out.println(5 % 5);
        System.out.println(Float.valueOf(0.5f));
        System.out.println(Float.valueOf(0.5f).floatValue() >= 0.5);
    }
}
