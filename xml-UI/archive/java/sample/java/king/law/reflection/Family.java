package king.law.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Family implements Child {
    private String label = "MyFamily";

    @Override
    public String patch() {
        return "patch family";
    }

    @Override
    public String get() {
        return "get family";
    }

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Family instance = new Family();
        Class<?> aClass = Class.forName("king.law.reflection.Family");
        show(instance, aClass);

        Class<?> childClass = Class.forName("king.law.reflection.Child");
        show(instance, childClass);

        Class<?> parentClass = Class.forName("king.law.reflection.Parent");
        show(instance, parentClass);
    }

    private static void show(Family instance, Class<?> aClass)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException, NoSuchFieldException {
        System.out.println(aClass.getSimpleName() + "/"
                + aClass.getName());
        Method patch = aClass.getMethod("patch");
        System.out.println(patch.invoke(instance));
        Method get = aClass.getMethod("get");
        System.out.println(get.invoke(instance));
        System.out.println(aClass.getCanonicalName() + "/"
                + aClass.getTypeName());
        Field field = aClass.getDeclaredField("label");
        field.setAccessible(true);
        System.out.println("label field is : " + field.get(instance));
        System.out.println("----------------------------------------\n");
    }
}
