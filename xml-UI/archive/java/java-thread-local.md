***

## Ways to use threadlocal
- [ThreadLocal简介](https://mp.weixin.qq.com/s/y_-o8pl7kbkNykPFIrKmEA)

### 存储变量
```java 
ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
newFixedThreadPool.submit(() -> {
    threadLocal.set(111);
    System.out.println("thread name: " + Thread.currentThread().getName() + " set value: " + 111);
});
newFixedThreadPool.submit(() -> {
    threadLocal.set(222);
    System.out.println("thread name: " + Thread.currentThread().getName() + " set value: " + 222);
});
for (int i = 0; i < 10; i++) {
    newFixedThreadPool.submit(() -> {
        System.out.println(String.format("thread name: %s, value: %s", Thread.currentThread().getName(), threadLocal.get()));
    });
}
newFixedThreadPool.shutdown();
``` 
执行结果
```
thread name: pool-1-thread-1 set value: 111
thread name: pool-1-thread-2 set value: 222
thread name: pool-1-thread-3, value: null
thread name: pool-1-thread-2, value: 222
thread name: pool-1-thread-3, value: null
thread name: pool-1-thread-1, value: 111
thread name: pool-1-thread-2, value: 222
thread name: pool-1-thread-3, value: null
thread name: pool-1-thread-1, value: 111
thread name: pool-1-thread-2, value: 222
thread name: pool-1-thread-3, value: null
thread name: pool-1-thread-1, value: 111
```
