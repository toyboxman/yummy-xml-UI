package king.law.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapReplacement {
    static class ValueObj {
        String name;

        public ValueObj(String name) {
            this.name = name;
        }
    }
    public static void main(String[] args) {
        Map<String, Integer> tracerMap = new ConcurrentHashMap<>();
        tracerMap.put("1", 1);
        tracerMap.put("2", 2);
        tracerMap.put("3", 3);
        tracerMap.entrySet().forEach(System.out::println);
        System.out.println(tracerMap);

        tracerMap.forEach((k, v) -> {
            v = v *  10;
            tracerMap.put(k, v);
        });
        tracerMap.entrySet().forEach(System.out::println);
        System.out.println(tracerMap);

        Map<Integer, ValueObj> vMap = new ConcurrentHashMap<>();
        vMap.put(1, new ValueObj("o1"));
        vMap.put(10, new ValueObj("o10"));
        vMap.put(100, new ValueObj("o100"));
        System.out.println(vMap);
        final ValueObj vo;
        vo = new ValueObj("111");
        vMap.forEach((k,v) -> {
            vMap.put(k,vo);
        });
        System.out.println(vMap);
    }
}
