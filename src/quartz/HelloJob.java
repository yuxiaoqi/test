package quartz;
import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by admin on 2016/8/31.
 */
public class HelloJob implements Job{
    private Logger log = LoggerFactory.getLogger(HelloJob.class);
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        PropertyConfigurator.configure("src/log4j-contrl.properties");
        log.error("hello world!"+new Date());
       // System.out.print("hello world! "+ new Date());
    }
}
