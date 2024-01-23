package 并发编程.concurrentUtils.delayQueueTest;

import java.util.Date;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName:
 * @Description: 订单实体信息
 * @Author yuxiaoqi
 * @Create 2018/9/18 15:00
 */
public class Order implements Delayed{


    /**
     * 订单号信息
     */
    private String orderNo;
    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 过期失效时间
     */
    private Date cancelTime;

    public String getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Order setOrderNo(String orderNo) {
        this.orderNo = orderNo;
        return this;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public Order setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
        return this;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(cancelTime.getTime()-System.currentTimeMillis(),TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {

        return this.getCancelTime().compareTo(((Order) o).getCancelTime());
    }
}
