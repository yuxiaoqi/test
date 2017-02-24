package Date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author: yyq
 * @Date 2016/8/12 13:19
 * @Vesion V1.0
 */
public class DateTest {

    @Test
    public void test(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(format.format(date));
    }

    @Test
    public void test2() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String ss = "2016-03-14 12:33:33";
        Date date =format.parse(ss);
        System.out.println(format.format(date));

/*        DateFormat df1 = new SimpleDateFormat("MM");
        String dateStringMM = df1.format(new Date());
        String mm= BatchNoEnum.getName(Integer.valueOf(dateStringMM).intValue()).toString();

        DateFormat df = new SimpleDateFormat("ddMMyy");
        String dateString = df.format(new Date());

        dateString= dateString.replace(dateStringMM,mm);
        System.out.println(dateString);*/

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = simpleDateFormat.parse("2016-11-28 17:19:35");
        date1.getTime();
        System.out.printf("date--"+date1.getTime());
        calendar.setTime(date1);
        calendar.getTimeInMillis();
        System.out.printf("时间戳---"+calendar.getTimeInMillis());

        /*时间戳 转为时间*/
        Long aLong = new Long(1480324775);
        String s = simpleDateFormat.format(aLong);
        Date date2 = simpleDateFormat.parse(s);
        System.out.printf("时间是"+date2);

        /*String 转为 时间戳*/
        String s1= "2016-12-12 19:30:30";
        Date date3 = simpleDateFormat.parse(s1);
        date3.getTime();

        /*String 成日历*/
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date3);
        calendar1.getTimeInMillis();
        /*获取 当前的时间戳*/
        System.currentTimeMillis();

   }

}
