package logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Dscription: logback test文件
 * @Created: yyq
 * @Date: 2017/1/24 10:13
 * @version: 1.0.0.0
 */
public class LobackTest {
    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(LobackTest.class);
        log.debug("hello world!");

        //print internal state
        LoggerContext lc =(LoggerContext)LoggerFactory.getILoggerFactory();
        StatusPrinter.print(lc);
    }
}
