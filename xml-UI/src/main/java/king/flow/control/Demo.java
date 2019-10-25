/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author liujin
 */
public class Demo {

    public static void main(String[] args) {
        System.out.println("king.flow.control.Demo.main()");
        HashMap<String, Object> hashMap = new HashMap<>();
        StackOverflowError stackOverflowError = new StackOverflowError();
        hashMap.put("JSESSIONID=BD630DE2614A157CB4BAF8D9698DC899; JSESSIONID=F176DA45D8CB22B2BC5D092A64F42355", stackOverflowError);
        hashMap.put("3c90c6e9-ac12-4624-815a-370dfe85f417", stackOverflowError);
        hashMap.forEach((key, value)
                -> {
            System.out.println(String.format("#xtrace-dumpContext# Key: %s --> Value: %s", key, value));
        });

        String url = "#view=fabric/nodes/transportnodes/editor/monitor";
        System.out.println(StringUtils.ordinalIndexOf(url, "/", 5));
        System.out.println(url.substring(0, StringUtils.ordinalIndexOf(url, "/", 2)));

        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        newFixedThreadPool.submit(() -> {
            threadLocal.set(111);
            System.out.println("thread name: " + Thread.currentThread().getName());
        });
        newFixedThreadPool.submit(() -> {
            threadLocal.set(222);
            System.out.println("thread name: " + Thread.currentThread().getName());
        });
        for (int i = 0; i < 10; i++) {
            newFixedThreadPool.submit(() -> {
                System.out.println(String.format("thread name: %s, value: %s", Thread.currentThread().getName(), threadLocal.get()));
            });
        }
        newFixedThreadPool.shutdown();

        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(3);
        List<node> nodes = ids.stream()
                .map(id -> new node(id).setName("node-" + id))
                .collect(Collectors.toList());
        nodes.forEach(n -> System.out.println(n));
        nodes.forEach(System.out::println);
    }

    static class node {
        private int id;
        private String name;

        public node(int id) {
            this.id = id;
        }

        public node setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return "node{" + "id=" + id + ", name=" + name + '}';
        }
    }
}
