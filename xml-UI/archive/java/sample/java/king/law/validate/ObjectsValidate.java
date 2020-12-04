package king.law.validate;

import java.util.ArrayList;
import java.util.Objects;

public class ObjectsValidate {
    public static void main(String[] args) {
        System.out.println(Objects.hashCode("string"));
        System.out.println(Objects.nonNull("string"));
        System.out.println(Objects.hash("string", 1));
        System.out.println(Objects.equals("string", "12345"));
        System.out.println(Objects.equals("string", "string"));

        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(2);
        list2.add(1);
        System.out.println("list deepEquals :" + Objects.deepEquals(list1, list2));

        Integer[] iarray1 = new Integer[]{1, 2};
        Integer[] iarray2 = new Integer[]{1, 2};
        Integer[] iarray3 = new Integer[]{2, 1};
        System.out.println("int-array deepEquals :" + Objects.deepEquals(iarray1, iarray2));
        System.out.println("int-array deepEquals :" + Objects.deepEquals(iarray1, iarray3));

        try {
            String stream = null;
            Objects.requireNonNull(stream, "stream is null");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
