## ScheduledThreadPoolExecutor 源码解读
  ### 基础逻辑
 * ScheduledThreadPoolExecutor 会通过一个 DelayedWorkQueue 来存储和调度任务。这个 DelayedWorkQueue 实际上是一个 PriorityBlockingQueue，也就是说这是一个优先级队列，里面存放的任务按照任务的下一次执行时间来排序，下一次执行时间最早的任务优先级最高。
   每一个提交到 ScheduledThreadPoolExecutor 的任务都会被封装为一个 ScheduledFutureTask 对象。ScheduledFutureTask 对象实现了 RunnableScheduledFuture 接口，RunnableScheduledFuture 接口扩展自 RunnableFuture 和 Delayed 接口，也因此具备了可以被执行和获取执行结果以及约定的延迟执行时间的能力。所有的 ScheduledFutureTask 对象会被添加到 DelayedWorkQueue 并按照其下一次执行时间排序。
   当 ScheduledThreadPoolExecutor 开始调度任务时，它会从 DelayedWorkQueue 中获取队首的 ScheduledFutureTask，即下一次执行时间最早的任务。然后判断该任务是否已经到达或者超过了约定的执行时间，如果是，那么执行该任务，否则等待到任务约定的执行时间再执行。
   对于周期性的任务，ScheduledThreadPoolExecutor 在任务执行完后，会重新计算下一次的执行时间，并把任务再次添加到 DelayedWorkQueue 中，这样任务就会周期性的执行。
 #### **来看重点实现逻辑**
**ScheduledFutureTask**

**DelayedWorkQueue** 
