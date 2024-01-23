## 基础语义
 * CyclicBarrier 是一个同步辅助类，允许一组线程互相等待，直到所有的线程都到达某个公共屏障点。设定的屏障点到达后，这个屏障会自动重置，以等待下一轮的到达，因此得名"cyclic"（循环的）。

_**重要组件**_ 

* **ReentrantLock lock 和 Condition trip**：这两个成员变量用于同步，它们协同工作来使线程在屏障点等待和通知所有在屏障点等待的线程。
* **int parties**：这个是屏障点的数目，也就是要求多少个线程达到屏障点才能继续执行。
* **Runnable barrierCommand**：这个是达到屏障点之后的一个额外操作，可以在构造函数中指定，也可以不指定。如果指定了，那么当所有线程都到达屏障点，**会有一个线程去执行这个操作**。**这个线程就是await操作屏障点减为0的那个线程会直接调用command.run();**
* **Generation generation**：这个是代表了CyclicBarrier的一个周期。每当屏障点为0时，会生成一个新的Generation实例，代表一个新的周期开始。
     ` 当屏障点数目为0时，会生成一个新的Generation实例，这个过程在 nextGeneration() 方法中实现。生成新的实例后，对旧的Generation实例的引用就被释放了。
  旧的Generation实例没有被其他引用指向，所以会被Java的垃圾收集（Garbage Collection）机制视为垃圾进行回收，这就是Java处理旧的Generation实例的方式。
  这种处理方式避免了内存泄露，并确保了并发线程能在新的周期开始时获取全新、无状态的Generation，从而保证CyclicBarrier的正确性和性能。`

 **重点分析下 await 和 nextGeneration**

### await()
**await()** 方法是线程调用来等待的，首先它会获取锁，然后检查当前generation是否已经被破坏，如果被破坏就抛出BrokenBarrierException。然后将当前等待的线程数count减一，如果count为0，表示所有线程都已到达屏障点，那么进行nextGeneration，然后唤醒所有等待的线程，最后解锁，方法返回。

### nextGeneration
 **nextGeneration()** 方法新建一个Generation对象，然后将屏障点数count重置为parties，然后通过trip.signalAll()唤醒所有等待的线程。





