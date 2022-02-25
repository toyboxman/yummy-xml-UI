package king.law.calculate;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCalc {
    public static void main(String[] args) {
        String s0 = "1/2/3/4/5";
        String[] s0_splits = s0.split("/");
        String s1 = "3/4/5/6/7";
        String[] s1_splits = s1.split("/");
        System.out.println(Arrays.stream(s0_splits).collect(Collectors.toList()));
        System.out.println(Arrays.stream(s1_splits).collect(Collectors.toList()));
        System.out.println(String.join("/",
                Arrays.stream(s1_splits).collect(Collectors.toList()))  );

        String[][] flatMap = new String[][]{s0_splits, s1_splits};
        System.out.println(Arrays.stream(flatMap)
                .flatMap(r -> Arrays.stream(r))
//                .collect(Collectors.toList()));
                .collect(Collectors.toSet()));
    }
}
