import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/4.
 */
public class StringTest {

    public static  void main(String[] args){
        String[] parm={"1_ddd","2_","4_null"};
        for(String temp:parm ){
            String[] bb=temp.split("_");

            System.out.println(bb.length);
        }

        String orderSn ="testtttttttt";
        List<String> temp= new ArrayList<>();
        StringTest.doSomething(orderSn,temp);
        System.out.println("结果是"+orderSn+"ggg"+temp.toString());
    }

    private static void doSomething(String orderSn,List<String> temp) {
        orderSn = "nininiii";
        temp.add(orderSn);
        temp.add("hhhhhhh");
    }
}
