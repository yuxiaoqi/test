## ThreadPoolExecutor 源码解读


### **问题一ThreadPoolExecutor的execute和submit有什么不同**
* **返回值**：execute()方法没有返回值，而submit()方法返回一个Future对象。通过这个Future对象，你可以获取任务的执行结果，检查任务是否执行完毕，或者取消任务的执行。
* **异常处理**：execute()方法在任务执行过程中产生的异常会被内部的UncaughtExceptionHandler捕获，如果没有设置自定义的UncaughtExceptionHandler，那么异常信息会被打印到System.err（标准错误输出）。submit()方法则不同，如果任务在执行过程中产生了异常，submit()方法会捕获这个异常，然后在你调用Future.get()方法来获取任务结果时重新抛出一个ExecutionException，原始的异常将被设为ExecutionException的cause。
* **任务类型**：execute()方法只接受Runnable任务，而submit()方法既接受Runnable任务也接受Callable任务。Callable任务与Runnable任务类似，但是它可以返回一个结果，或者抛出一个异常。
* **总的来说submit是比excute更强大的方法。可以有返回结果。处理异常和接受runnable和callable两种参数**

### java.util.concurrent.ThreadPoolExecutor.awaitTermination 源码解读
**方法的工作原理**
* 首先将输入的等待时间转换成纳秒。 
* 如果线程池已经终止了，那么返回 true。 
* 如果等待超时(纳秒 <= 0)，那么返回 false。 
* 如果线程池还在运行，那么当前线程将在 termination 的条件变量上等待，等待时间为当前剩余的时间(纳秒)。如果在等待期间线程池终止了，那么会收到通知并从等待中返回。
* 
 *源代码注意几点**
* **ReentrantLock:** 这是一个互斥锁，它被用来保护 ThreadPoolExecutor的状态。当我们调用 awaitTermination 时，我们会尝试获取这个锁以安全地检查 ThreadPoolExecutor 的状态。
* **ctl:** 这是一个 AtomicInteger，表示 ThreadPoolExecutor 的运行状态和工作线程数。我们通过调用 runStateAtLeast(ctl.get(), TERMINATED) 来检查 ThreadPoolExecutor是否已经终止。
* **termination**: 这是一个 Condition，可以让线程在某条件满足前一直等待，或者在其他线程通知后唤醒。

### ThreadPoolExecutor 中的 ctl 字段亮点设计
 **基础**
 * AtomicInteger，用来控制线程池的状态，以及记录线程池中的工作线程数。
 其具体原理包含两部分内容：**高3位代表线程池状态**，剩余的**29位则用来存储线程数量**。由于 AtomicInteger 是线程安全的，所以可以在多线程环境下安全地进行操作
   **runStateOf**：来获取线程池状态 **workerCountOf**：来获取工作线程数量

**线程池状态共5种**
* **RUNNING** ：接受新任务，并处理阻塞队列中的任务 
* **SHUTDOWN** ：不接受新任务，但处理阻塞队列中的任务 
* **STOP** ：不接受新任务，不处理阻塞队列中的任务，并中断正在处理的任务 
* **TIDYING** ：所有任务都已终止，workerCount（有效的线程数）为0，线程转到TIDYING状态后将运行termination()钩子方法 
* **TERMINATED**：terminate()运行完毕后，线程池的状态变为TERMINATED

 ### java.util.concurrent.ThreadPoolExecutor.allowCoreThreadTimeOut
 * java.util.concurrent.ThreadPoolExecutor#allowCoreThreadTimeOut(boolean) 是一个用于控制线程池中的核心线程是否可以被超时空闲策略所影响的方法。如果参数为 true，则表示核心线程也可以被超时空闲策略影响；如果为 false，则表示核心线程事实上不会因为空闲超时而被终止。
 * 在默认情况下，线程池中的非核心线程会在空闲超过一定时间（keep alive time）后被终止，而核心线程不会。但通过这个方法可以改变这个行为，让核心线程也会在超过 keep alive time 后被终止。

### java.util.concurrent.ThreadPoolExecutor 中的钩子方法

1. **void beforeExecute(Thread t, Runnable r)**: 在执行任务 Runnable r 之前被调用。Thread t 是执行任务的线程。默认实现为空，你可以重写该方法来进行任务执行前的相关处理，如资源初始化、日志记录、性能监测等。
2. **void afterExecute(Runnable r, Throwable t)**: 在任务 Runnable r 执行结束后被调用。Throwable t 是任务 r 在执行过程中抛出的异常（如果有）。默认实现为空，你可以重写这个方法来进行任务执行后的相关处理，如资源回收、异常处理、日志记录、性能监测等。
3. **void terminated()**: 当线程池完成关闭，即线程池的状态变为 TERMINATED 时，这个方法会被调用。默认实现为空，你可以重写这个方法来进行线程池关闭后的相关处理，如资源回收、状态通知、故障恢复等。
