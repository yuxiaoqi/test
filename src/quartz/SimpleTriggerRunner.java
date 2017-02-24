package quartz;

import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.DateBuilder.*;

/**
 * Created by admin on 2016/8/31.
 */
public class SimpleTriggerRunner {
    private Logger log = LoggerFactory.getLogger(SimpleTriggerRunner.class);
    public void run() throws Exception {
        PropertyConfigurator.configure("src/log4j-contrl.properties");
            //获取一个调度器的事例
            Date date = evenMinuteDate(new Date());
            Date endTime = DateBuilder.nextGivenMinuteDate(null, 5);
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            //创建创建一个jobdetail事例
            JobDetail jobDetail = newJob(HelloJob.class).withIdentity("job1", "group1").build();
            //创建一个
            SimpleTrigger trigger = (SimpleTrigger)newTrigger().withIdentity("trigger1", "group1").startAt(date).endAt(endTime).withSchedule(
                    simpleSchedule()
                            .withIntervalInSeconds(2)
                            .withRepeatCount(2)
            ).build();
            //注册
            scheduler.scheduleJob(jobDetail,trigger);
        log.info(jobDetail.getKey()+"开始run"+date);
            scheduler.start();
        log.info("scheduler.start()!!!");
        try{
            Thread.sleep(65000);
        }catch (Exception e){
            e.printStackTrace();
        }
        log.info("------------shut down-----");
        scheduler.shutdown();
        log.info("--------------shut down complete");
    }

    public static void main(String[] args) throws Exception {
        SimpleTriggerRunner runner = new SimpleTriggerRunner();
        runner.run();
    }
}
