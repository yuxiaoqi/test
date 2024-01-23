
 ## java.util.concurrent.ExecutorService 类中各方法解读
 

* **void shutdown()**：这种方法将开始一个平滑的关闭，在此关闭过程中，将不再接受新任务，但将继续处理已提交的任务(包括那些尚未开始执行的任务)。只有当所有任务都已完成后，才可以关闭 ExecutorService。
* **List<Runnable> shutdownNow()**：这个方法试图停止所有正在执行的活动任务，暂停处理那些等待的任务，**并返回等待执行的任务列表**。这个方法并不保证能够停止正在处理的任务，但是会**尽力尝试**。
* **boolean isShutdown()**：如果此执行程序已被关闭，则返回 true。
* **boolean isTerminated()**：如果**关闭**后所有**任务都已完成**，则返回 true。注意，除非首先调用 shutdown 或 shutdownNow，否则 isTerminated 永不为 true。
* **boolean awaitTermination(long timeout, TimeUnit unit)**：该方法将阻塞，直到所有任务都已完成执行后的关闭， 或者发生超时， 或者当前线程被中断， 其中之一发生。
* **<T> Future<T> submit(Callable<T> task)**：提交一个返回值的任务进行执行，返回一个表示任务的未决结果的 Future。该 Future 的 get 方法在成功完成时将返回任务的结果。
* **<T> Future<T> submit(Runnable task, T result)**：执行 Runnable 任务，并在完成后返回给定的结果。
* **Future<?> submit(Runnable task)**：提交一个 Runnable 任务进行执行并返回一个表示该任务的 Future。
* **invokeAll(Collection<? extends Callable<T>> tasks)** 方法是一种执行给定collection中所有的callable任务的方法，然后当所有任务完成时，返回一个保含它们的结果的list。列表的迭代器与给定任务集合的迭代器在处理所有的完成任务时产生**相同的顺序**。如果在所有任务完成之前已关闭此执行器，则返回的列表的迭代器将抛出CancellationException。