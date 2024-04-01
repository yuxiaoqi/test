package 各种测试随记;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        // 获取本地当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前时间: " + now);

        // 获取当天开始时间
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        System.out.println("当天开始时间: " + startOfDay.toString());

        // 获取当天结束时间
        LocalDateTime endOfDay = startOfDay.plusDays(1).minusNanos(1);
        System.out.println("当天结束时间: " + endOfDay.toString());
    }
}
