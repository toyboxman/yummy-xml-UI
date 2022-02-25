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

        // Integer object大小不能通过 == 直接比较
        // 这种情况下不会autoboxing成 int数值比较
        // 实际上是在比较对象的引用
        Integer a0 = new Integer(5);
        Integer a1 = Integer.valueOf(5);
        // 这个方法不是用来解析Integer对象的
        // 而是用来获得system properties的整形值
        // 这么调用会直接返回 null
        Integer a2 = Integer.getInteger("5");
        System.out.println(String.format("a0 == a1 ? %s", a0 == a1));
        System.out.println(String.format("a0 equal to a1 ? %s", a0.equals(a1)));
        System.out.println(String.format("a0 intValue equal to a1 ? %s",
                a0.intValue() == a1.intValue()) );
        // 但如果通过autoboxing产生的integer对象是同一个
        a0 = 5;
        a1 = 5;
        System.out.println(String.format("again a0 == a1 ? %s", a0 == a1));
    }
}
