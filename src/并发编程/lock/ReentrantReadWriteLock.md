# ReentrantReadWriteLock 源码分析

### 简介

ReentrantReadWriteLock 是一个读写锁，它支持多个线程同时读共享变量，同一个线
程在写共享变量的时候不能被其他线程读和写。

### 先来看读锁：java.util.concurrent.locks.ReentrantReadWriteLock.Sync#tryAcquireShared 源码逻辑
**基础**

tryAcquireShared是ReentrantReadWriteLock内部同步类Sync的一个关键方法，用于试图获取读锁。这是一个**使用AQS（AbstractQueuedSynchronizer）框架**的例子，框架用int state表示锁的状态。下面是其原始代码

1. Thread current = Thread.currentThread(); 
                    `获取当前执行的线程。`
2. int c = getState();：`获取当前锁的状态，其中写锁状态在高16位，读锁在低16位`。
3. if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) return -1; 
   
      <span style="color: red;">补充</span>：getExclusiveOwnerThread 该方法获取的是AQS中独占锁的线程，相对应的setExclusiveOwnerThread：设置当前线程作为**独占写锁的所有者**，**独占是排他的**，也就是是有**写锁**获取成功才会去设置。读锁在定义上**允许多个线程**同时持有。因此，**无需设置**一个独占的所有者
     
       检查写锁是否被其他线程持有。如果是并且当前线程不是写锁的持有者，则返回-1，表示获取读锁失败。
4. int r = sharedCount(c);：`从获得的锁状态中提取读锁的数量。`
5. if (!readerShouldBlock() && r < MAX_COUNT && compareAndSetState(c, c + acquires))：
  `判断是否可以获取读锁，有三个条件：读锁的请求者不应被阻塞；读锁的数量未达最大值；将状态从当前值改为当前值+请求数量（通常为1）的CAS操作成功。`
6. if (r == 0) { firstReader = current; firstReaderHoldCount = 1; }：
`如果读锁数量为0（即还没有线程持有读锁），将当前线程设置为第一个读取者，并将首读者的读锁持有数量设置为1。`
7. else if (firstReader == current) { firstReaderHoldCount++; }：
 `如果当前线程是首读者，首读者的读锁持有数量加1。`
8. else { ... }：
 `对于其他情况（即当前线程不是首读者），获取缓存的HoldCounter（记录了线程ID，和该线程持有读锁的数量）。如果缓存不存在或者与当前线程不匹配，则通过ThreadLocal变量（就是为每一个获取读锁的线程记录一个私有的读锁数量）获取一个新的，并更新缓存。如果计数为0，还需要更新ThreadLocal变量。然后增加计数。`
9. return 1;：
 `前面的分支中，如果成功获取读锁，则返回1，表明成功。`
10. return fullTryAcquireShared(current);：
11. 如果上述都未成功，这会进入fullTryAcquireShared，这剩余的条件全面考察后，进行获取操作，并返回结果。这里的结果如果为负值，则代表获取失败。
* <span style="color: red;">最后</span> 根据 **state锁状态** **CAS操作** 和 **exclusiveOwnerThread** 独占线程 来完成读读共享 读写阻塞 写写阻塞
   当读锁或写锁成功都会改变 **state锁状态态**。那么自然下个写锁就不会成功。 写锁成功后，读锁自然也不会成功。当写锁先成功，通过if (exclusiveCount(c) != 0 && getExclusiveOwnerThread() != current) 判断**独占数量不为0**，且**当前线程不是独占线程**，那么读锁自然也不会成功。

