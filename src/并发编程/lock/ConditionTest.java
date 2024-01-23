package 并发编程.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2024/1/2 23:14
 */
public class ConditionTest {
    /*唤醒     AbstractQueuedSynchronizer.ConditionObject.doSignalAll() 源码解读*/

    //AAAAAAAAAAAAAAAAAAAAAAAAAAAAA
    /*
    * lastWaiter = firstWaiter = null; 这是为什么
    *
    * AbstractQueuedSynchronizer.ConditionObject 的 doSignalAll 方法用于唤醒等待队列上的所有线程。
    * 在这个方法开始时，它首先将 lastWaiter 和 firstWaiter 设置为 null。
    * 这样做的原因是：在唤醒等待队列上的所有线程后，理应不再有任何等待线程，即等待队列应当为空。
    *在这种情况下，等待队列的首尾节点 naturally 应当为 null。
     这是一种常见的设计思想，当清空一个队列或列表时，通常会将其首尾节点设置为null。
     * 这样做不仅可以防止因为意外访问到 "僵尸" 节点（已不再使用、但还有引用指向它的节点），
     * 还有利于垃圾回收——如果首尾节点不被设为 null，即使它们已不存在等待队列中，
     * 但仍然有引用指向它们，这将会阻止垃圾回收器回收这些节点占用的内存。
    * */



}
