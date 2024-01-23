## java.util.concurrent.Executors 线程池工厂类

### 常用工厂方法
#### java.util.concurrent.Executors#newCachedThreadPool() 
 **源码解读:**
1. **ThreadPoolExecutor**：这是线程池的核心类，它实现了 ExecutorService 接口。它使用给定的初始参数（包括核心线程数、最大线程数、空闲线程存活时间、任务队列等）创建一个新的 ThreadPoolExecutor。
2. **0：**表示 "corePoolSize"，也就是核心线程数。在创建了线程池后，默认情况下，线程池中并没有任何线程。等到有任务来时才创建线程去执行，一旦创建的线程数达到了corePoolSize，那么就会把到达的任务放到缓存队列当中。
3. **Integer.MAX_VALUE：**表示 "maximumPoolSize"，也就是线程池允许创建的最大线程数。线程池的最大数量是Integer.MAX_VALUE（2147483647），这是一个非常大的值，事实上的上限更多依赖于操作系统的能力。
4. **60L：**表示 "keepAliveTime"，也就是空闲线程的存活时间。当前线程池数量超过corePoolSize时，当空闲时间达到keepAliveTime值时，多余线程会被销毁直到只剩下corePoolSize个线程为止。
5. **TimeUnit.SECONDS：**表示 "keepAliveTime" 的时间单位。 
6. **new SynchronousQueue<Runnable>()：**表示任务的等待队列。SynchronousQueue 是一个不存储元素的阻塞队列，每一个插入操作必须等待一个相应的删除操作（或反之亦然）。

**功能详解**
1. **快速响应需求**：Executors.newCachedThreadPool() 创建的线程池具备快速响应外部请求的能力。当有大量的（数目不确定）短时间任务需要快速运行时，缓存线程池可以提供在一定时间之内大量启动线程的能力，从而快速处理程序所提交的任务。
2. **动态添加线程**：只要线程池中的线程无法满足任务执行需求，线程池会动态地添加新的线程，线程总数理论上可以达到 Integer.MAX_VALUE。
3. **线程回收**：当线程完成任务并变为空闲后，如果在 60 秒钟之内没有新的任务，这个线程会被回收。这是一个空闲线程回收机制，可以降低因创建过多线程而导致的资源浪费。
4. **资源合理利用**：由于缓存线程池会根据需要（新任务的到来）创建新的线程，所以可以在多线程处理环境中，合理地利用系统资源，提高系统在大量并发小任务下的性能。
5. **需要注意的是**，虽然 newCachedThreadPool 非常方便，但它仅适用于生命周期很短的任务，如果用于长期运行的任务，可能会消耗较多的系统资源，比如 CPU，内存等，这样可能会导致服务器资源的浪费，因此在使用时需要谨慎考虑。

**?为什么叫缓存线程池?**
1. 它被称为 "缓存线程池"，是因为这个线程池有一个特性，那就是能够在程序执行过程中，根据需要，**动态地缓存（增加或释放）线程。**
2. 在这个上下文中，"缓存" 一词的含义来自于缓存线程池具有的动态创建和回收线程的能力。这种能力让它在需要处理大量短期任务而且需要快速响应时显得尤为有用。
3. 这是因为，当多个任务被提交到线程池时，为了更好地利用系统资源和提高系统性能，系统会尽可能地**复用已有的空闲线程**来执行新的任务，而不是每次都创建新的线程。如果当前的线程无法满足新的任务执行需求，那么新的线程将会被创建并添加到线程池中，这就实现了线程的缓存。
4. 另一方面，当线程完成任务并处于空闲态超过一定的时间（默认60秒）后，这个线程会被销毁，这也算是一种 **"回收缓存"** 的机制。
5. 因此，由于这个线程池具备以上**动态地缓存（新增或移除）线程的能力**，所以被称为 "缓存线程池"。

**缓存线程池中的工作队列 SynchronousQueue**
1. Java 中的 SynchronousQueue 是一个基于阻塞的队列，但它并**不保存任何元素**，也就是说，你不能查看队列的元素或检查队列的大小（始终为零）。它的特殊之处在于，它维护一个**内部的等待线程集**。队列的“元素”实际上**直接**在这些**等待线程中传递**。
2. 其主要方法是 put(E e) 和 take()。当一个线程试图调用 put() 但没有其他线程正在等待接收的时候，这个调用方法的线程会被阻塞，直到另一个线程调用 take()。同样，如果一个线程试图调用 take() 但没有其他线程正在等待插入的时候，这个调用方法的线程会被阻塞，直到另一个线程调用 put()。因此，这个队列中每个 put 必须等待一个 take，反之亦然。
3. 因为 SynchronousQueue 不保存元素，所以它不能从容量、大小或其他类似的角度进行查看。这就意味着像 peek()， contains()，remove() 和 clear() 这样的方法在 SynchronousQueue 上是不支持的。同样，迭代器和 spliterator() 也无法对 SynchronousQueue 的元素进行遍历，因为它没有元素可以遍历。
4. SynchronousQueue 可以以公平和非公平两种方式进行操作。公平操作按照线程在等待的时间顺序进行，也就是说越早等待的线程越先获得服务。非公平操作则无法保证等待线程的顺序。
5. 这个队列非常适合于生产者和消费者模式，其中每个生产者的元素将被其消费者直接接收

#### java.util.concurrent.Executors.PrivilegedThreadFactory 特权线程工厂

**基础**

PrivilegedThreadFactory 是 Java 并发库中的一种带有特权的线程工厂，这个类是 Executors 类的一个静态内部类。其目的是为了在创建新线程时，保留原始的 AccessControlContext。这对于一些安全敏感的代码至关重要，因为这样的代码需要确保它在正确的上下文中执行。

1. **PrivilegedThreadFactory**：这是 Executors 类的一个静态内部类，其继承了 DefaultThreadFactory，主要是用来创建线程的。
2. **newThread**：这是 DefaultThreadFactory 类中定义的方法，由该工厂类具体实现。这个方法将会真正创建出新的 Thread 对象。该方法接收一个 Runnable 对象作为参数，这就是将要被新创建的线程执行的任务。
3. **[AccessController.doPrivileged](AccessController.doPrivileged补充)**这是 Java 中的特权操作，能够运行具有特定权限的代码块。在这个上下文中，它将创建新的线程作为一个特权操作。在创建新的线程时，它会复制当前的 AccessControlContext。这就意味着，新线程可以按照与其创建者相同的安全性协议执行代码。
4. **NewThreadAction**：这也是 Executors 类中的一个私有静态内部类，它实现了 PrivilegedAction 接口，用于定义特权操作。在这个上下文中，这个特权操作就是创建并启动一个新的线程。
5. 注意，PrivilegedThreadFactory 类主要在 JAVA 安全管理器启用的环境中使用，这在一般的应用程序中并不常见，但在某些服务器或者容器等环境中，可能会有安全管理器的存在，这时候就可能会使用到 PrivilegedThreadFactory。

**AccessController.doPrivileged补充**
java 安全模型是以权限为基础的，一般代码（包括你的应用程序和第三方库等）默认是**没有执行特定操作（如读写系统属性）的权限**的。但是有些操作，例如访问敏感的系统资源或执行可能影响系统安全的操作，需要特殊的权限。这就是 AccessController.doPrivileged 的作用，它**可以让你的代码临时拥有执行这些特定操作的权限**。
<pre><code>
String userHome = AccessController.doPrivileged(
   new PrivilegedAction<String>() {
       public String run() {
           return System.getProperty("user.home");
       }
   }
);
</code></pre>
