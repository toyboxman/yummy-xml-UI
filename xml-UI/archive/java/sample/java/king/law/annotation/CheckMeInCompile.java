package king.law.annotation;

public class CheckMeInCompile {
    public static void main(String[] args) {
        A1 a1 = new A1();
        a1.setMethod();
        a1.getMethod();
        System.out.println("A1");
    }
}

class A1 {

    @Checker
    public void setMethod() {

    }

    @Checker
    public void getMethod() {

    }
}
