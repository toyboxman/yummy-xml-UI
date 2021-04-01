package king.law.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Future是Java 5添加的类，用来描述一个异步计算的结果。
// 可以使用isDone方法检查计算是否完成，或者使用get阻塞住调用线程，直到计算完成返回结果，
// 也可以使用cancel方法停止任务的执行。
public class FutureUsage {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> stringFuture = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                //耗时计算
                Thread.sleep(2000);
                return "async thread completes";
            }
        });
        System.out.println("main thread starts to read result");
        long start = System.currentTimeMillis();
        //虽然Future提供了异步执行任务的能力，但只能通过阻塞或者轮询的方式得到任务的结果。
        //阻塞的方式和异步编程的初衷相违背，轮询的方式又会耗费无谓的CPU资源
        //很多语言，比如Node.js，采用回调的方式实现异步编程。
        //Java的一些框架，比如Netty，自己扩展了Java的 Future接口，提供了addListener等多个扩展方法
        //Google guava也提供了通用的扩展Future:ListenableFuture、SettableFuture以及辅助类Futures等
        System.out.println(stringFuture.get());
        long second = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
        System.out.println("main thread takes ".concat(String.valueOf(second)).concat(" seconds to read"));
        System.out.println("main thread completes");

        //如果不关闭ExecutorService，主线程结束后，jvm的进程不会结束
        if (stringFuture.isDone()) {
            executor.shutdown();
        }
    }
}
//执行结果
//main thread starts to read result
//async thread completes
//main thread takes 2 seconds to read
//main thread completes
