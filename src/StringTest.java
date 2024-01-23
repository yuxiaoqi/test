import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/4.
 */
public class StringTest {

    public static  void main(String[] args){
        //冒泡排序
        int[] aa={1,3,2,4,5,6,7,8,9};
        for(int i=0;i<aa.length-1;i++){
            for(int j=0;j<aa.length-1-i;j++){
                if(aa[j]>aa[j+1]){
                    int temp=aa[j];
                    aa[j]=aa[j+1];
                    aa[j+1]=temp;
                }
        }
            }
        //手机号码格式验证
        String phone="13812345678";
        String regEx="^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(14[5,7]))\\d{8}$";
        if(phone.matches(regEx)){
            System.out.println("手机号码格式正确");
        }else{
            System.out.println("手机号码格式不正确");
        }

        //字符串分割
        String str="1_ddd,2_eee,3_fff,4_null";
        String[] strs=str.split(",");
        for(String temp:strs ){
            String[] bb=temp.split("_");
            System.out.println(bb[0]);
        }

        //字符串分割
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
