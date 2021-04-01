package king.law.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// Java 8中, 新增加了CompletableFuture，提供了比Future更多扩展功能，函数式编程的能力，
// 而且可以通过回调的方式处理计算结果，以及转换和组合CompletableFuture的方法。
public class CompletableFutureUsage {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //task1 example
        //supplyAsync若不指定Executor,默认使用ForkJoinPool.commonPool()作为它的线程池执行异步代码
        //runAsync方法以Runnable函数式接口类型为参数，计算结果为空CompletableFuture<Void>
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //耗时计算
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "async thread completes";
        });
        System.out.println("main thread starts to read result");
        long start = System.currentTimeMillis();
        //CompletableFuture类实现了CompletionStage和Future接口，
        //所以还是可以通过阻塞或者轮询的方式获得结果，尽管这种方式不推荐使用
        //future.getNow()如果结果已经计算完则返回结果或者抛出异常，否则返回设定的valueIfAbsent值
        System.out.println(future.get());
        long second = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
        System.out.println("main thread takes ".concat(String.valueOf(second)).concat(" seconds to read"));
        System.out.println("task1 completes");
        /*
        // 执行结果
        main thread starts to read result
        async thread completes
        main thread takes 2 seconds to read
        main thread completes
        */


        //task2 example
        //创建的future没有关联任何的Callback、线程池、异步任务等，
        //如果客户端直接调用future.join/get就会一直阻塞等待下去。
        CompletableFuture<String> task2_future = createFuture();
        //可以通过CompletableFuture.complete()/completeExceptionally完成一个计算，返回结果
        new Thread(() -> {
            task2_future.complete("invalid complete");
            //task2_future.completeExceptionally(new NumberFormatException("invalid number"));
        }).start();
        System.out.println("task2 result:".concat(task2_future.join()));
        /*
        // 执行结果
        task2 result:invalid complete
        */


        //task3 example
        //当CompletableFuture的计算结果完成，或者抛出异常的时候，可以执行特定的Action
        //CompletableFuture.whenCompleteAsync(), Action可能会使用其它的线程去执行(如果使用相同的线程池，也可能会被同一个线程选中执行)
        //CompletableFuture.whenComplete()/exceptionally(), Action使用相同的线程执行
        CompletableFuture<String> task3_future = CompletableFuture.supplyAsync(() -> {
            //耗时计算
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task3_future completes computation");
            return "task3 async thread completes";
        });
        //complete返回新的CompletableFuture与前一个future拥有相同的计算结果或异常信息，类似于计算结果透传下去
        //每一个新future都可以再complete下一个future，就像管道一下结果层层透传,任何一级future得到结果都一样
        //但只有上一个future的计算完成或异常发生才会触发下一个future的action
        CompletableFuture<String> task3_complete_future = task3_future.whenComplete((s, throwable) -> {
            System.out.println("task3 result:".concat(s));
            System.out.println("task3 exception:"
                    .concat(throwable == null ? "None" : throwable.toString()));
        });
        //complete/exceptionally方法返回一个新的CompletableFuture
        System.out.println("task3_future relationship to task3_complete_future:"
                .concat("equal:").concat(String.valueOf(task3_future.equals(task3_complete_future)))
                .concat("  ==:").concat(String.valueOf(task3_future == task3_complete_future)));

        //如果此处不调用future的get获取结果，CompletableFuture.supplyAsync, whenComplete关联action都不会执行
        /*
        // 执行结果
        task3_future relationship to task3_complete_future:equal:false  ==:false
        */
        //读取future才是真正开始所有计算
        System.out.println("task3_complete_future result:".concat(task3_complete_future.get()));
        /*
        // 执行结果
        task3_future relationship to task3_complete_future:equal:false  ==:false
        task3_future completes computation
        task3 result:task3 async thread completes
        task3 exception:None
        task3_complete_future result:task3 async thread completes
        */


        //task4 example
        CompletableFuture<String> task4_future = CompletableFuture.supplyAsync(() -> {
            //耗时计算
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task4_future completes computation");
            return "task4 async thread completes";
        });
        //CompletableFuture.handle/handleAsync的作用和example3相同
        //唯一区别是后续future可以得到不同于前面future类型的返回值
        CompletableFuture<Integer> task4_handle_future = task4_future.handle((s, throwable) -> {
            System.out.println("task4 result:".concat(s));
            return 123;
        });
        System.out.println("task4_handle_future result:".concat(task4_future.get()));
        System.out.println("task4_handle_future result:".concat(String.valueOf(task4_handle_future.get())));
        /*
        // 执行结果
        task4_future completes computation
        task4 result:task4 async thread completes
        task4 result:task4 async thread completes
        task4_handle_future result:123
        */


        //task5 example
        CompletableFuture<String> task5_future = CompletableFuture.supplyAsync(() -> {
            //耗时计算
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task5_future completes computation");
            return "task5 async thread completes";
        });
        //CompletableFuture.thenApply/thenApplyAsync的作用和example4相同
        //唯一区别是参数中只有结果而无异常
        CompletableFuture<String> task5_apply_future = task5_future.thenApply(s -> {
            return 100;
        }).thenApplyAsync(integer -> {
            return String.valueOf(integer * 10);
        });
        //thenCompose(Function<? super T,? extends CompletionStage<U>> fn)与之类似,用来连接两个CompletableFuture
        System.out.println("task5_apply_future result:".concat(task5_apply_future.get()));
        /*
        // 执行结果
        task5_future completes computation
        task5_apply_future result:1000
        */


        //task6 example
        CompletableFuture<String> task6_future = CompletableFuture.supplyAsync(() -> {
            //耗时计算
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task6_future completes computation");
            return "task6 async thread completes";
        });
        //CompletableFuture.thenAccept/thenAcceptAsync的作用与前面类似
        //唯一区别是之后future不会再有返回结果
        CompletableFuture<Void> task6_accept_future = task6_future.thenAccept(System.out::println);
        //thenAcceptBoth/thenAcceptBothAsync允许将两个future结果合并一起在action中处理
        CompletableFuture<Void> task6_accept_both_future = task6_accept_future.thenAcceptBoth(
                CompletableFuture.completedFuture(11),
                //由于task6_accept_future本身是返回<Void>，因此第一个参数就没有值
                //第二个参数是整形是因为上面合并进入的future返回整形11
                (unused, integer) -> {
                    System.out.println("task6_accept_both_future result: " + unused);
                    System.out.println("task6_accept_both_future result: " + integer * 10);
                });
        //runAfterBoth(CompletionStage<?> other,  Runnable action)跟上面方法区别
        //runAfterBoth是当两个CompletionStage都正常完成计算的时候,执行一个Runnable并且不使用计算的结果
        //thenRun(Runnable action)也类似会执行一个Runnable并且不使用CompletableFuture计算的结果
        //task6_accept_future没有返回结果，因此get得到null
        System.out.println("task6_accept_future result:" + task6_accept_future.get());
        /*
        // 执行结果
        task6_future completes computation
        task6 async thread completes
        task6_accept_both_future result: null
        task6_accept_both_future result: 110
        task6_accept_future result:null
        */


        //thenCombine(CompletionStage<? extends U> other, BiFunction<? super T,? super U,? extends V> fn)/thenCombineAsync与compose相似
        //合并的两个CompletionStage是并行执行的，它们之间并没有先后依赖顺序，
        //other future并不会等待先前的CompletableFuture执行完毕后再执行。
        //其实从功能上来讲,它们的功能更类似thenAcceptBoth，只不过thenAcceptBoth函数参数没有返回值，而thenCombine的函数参数fn有返回值
        CompletableFuture<Double> futurePrice = CompletableFuture.supplyAsync(() -> 100d);
        CompletableFuture<Double> futureDiscount = CompletableFuture.supplyAsync(() -> 0.8);
        CompletableFuture<Double> futureResult = futurePrice.thenCombine(futureDiscount, (price, discount) -> price * discount);
        System.out.println("最终价格为:" + futureResult.join()); //最终价格为:80.0


        //acceptEither/applyToEither
        //thenAcceptBoth和runAfterBoth是当两个CompletableFuture都计算完成,才做action
        //acceptEither方法是当任意一个CompletionStage完成的时候，action这个消费者就会被执行
        //applyToEither方法是当任意一个CompletionStage完成的时候，fn会被执行，它的返回值会当作新的CompletableFuture<U>的计算结果
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式A获取商品a";
        });
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "通过方式B获取商品a";
        });
        CompletableFuture<String> futureC = futureA.applyToEither(futureB, product -> "结果:" + product);
        System.out.println(futureC.join()); //结果:通过方式A获取商品a


        //allOf 和 anyOf
        //allOf:当所有的CompletableFuture都执行完后执行计算
        //anyOf:最快的那个CompletableFuture执行完之后执行计算
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        start = System.currentTimeMillis();
        futureA = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "商品详情";
        },executorService);

        futureB = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "卖家信息";
        },executorService);

        futureC = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "库存信息";
        },executorService);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "订单信息";
        },executorService);

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
        allFuture.join();

        System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());
        System.out.println("总耗时:" + (System.currentTimeMillis() - start));
        executorService.shutdown();

        //Guava的Futures辅助类提供了很多便利方法，用来处理多个Future，而不像Java的CompletableFuture，只提供了allOf、anyOf两个方法。
        //比如有这样一个需求，将多个CompletableFuture组合成一个CompletableFuture，这个组合后的CompletableFuture的计算结果是个List,
        //它包含前面所有的CompletableFuture的计算结果，guava的Futures.allAsList可以实现这样的功能，
        //但是对于java CompletableFuture，我们需要一些辅助方法, github有多个项目可以实现Java CompletableFuture与其它Future(如Guava ListenableFuture)之间的转换
    }

    //不通过supplyAsync等方法，创建一个CompletableFuture
    static CompletableFuture<String> createFuture() {
        final CompletableFuture<String> future = new CompletableFuture<>();
        return future;
    }
}
