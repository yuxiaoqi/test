package 并发编程.concurrentUtils;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName:
 * @Description:
 * @Author yuxiaoqi
 * @Create 2018/9/10 下午5:03
 */
public class PriorityBlockingQueueTest {


    /**
     * o1---为新添加的对象
     * o2---是对应的父节点对象
     * 当 o1 > o2 返回 -1 时候得到最大堆
     * 当 o1 > o2 返回 0 ，1 的时候返回最小堆
     */
    private static final PriorityBlockingQueue<Integer> blockingQueue = new PriorityBlockingQueue(10,
            new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (Objects.equals(o1,o2)){
                        return 0;
                    }
                    return o1 > o2 ? -1:1;
                }
            });




    private static final PriorityBlockingQueue<Integer> blockingQueue_no_caopare = new PriorityBlockingQueue(10, null);


    /*
    * 如果存储数组的下标基于0，那么下标为i的节点的子节点是2i+ 1与2i+ 2；
    * 其父节点的下标是⌊floor((i− 1) ∕ 2)⌋。函数floor(x)的功能是“向下取整”，
    * 或者说“向下舍入”，即取不大于x的最大整数（与“四舍五入”不同，
    * 向下取整是直接取按照数轴上最接近要求值的左边值，即不大于要求值的最大的那个值）。
    * 比如floor(1.1)、floor(1.9)都返回1。
    * */
    public static void main(String[] args) throws InterruptedException {


        /**
         * 低层数据存储用的是数组存储，数据关系是用的二叉堆 来实现,添加元素用冒泡的方式，取数据用下层的方式
         */
        for (int  i =1 ;i<12;i++){
            blockingQueue.offer(i);

        }

        System.out.println("dasd");
        for (int  i =1 ;i<12;i++){
            System.out.println(blockingQueue.take());
        }

    }
}
