package 算法;

/**
 * Created by admin on 2016/4/12.
 */
public class spiltTest {

    public  static  void main(String[] args){

        //String message ="3个月内身份证关联多个申请信息:[\"3个月身份证关联邮箱数：0\",\"3个月身份证关联手机号数：2\"]<br>3个月内申请信息关联多个身份证:[\"3个月手机号关联身份证数：3\"]<br>3个月内申请人主要借贷信息在多个网贷平台进行借款申请<br>3个月内手机号在多个网贷平台进行借款申请:[\"一般消费分期平台:3\"]<br>1个月内设备或身份证或手机号申请次数过多:[\"1个月内手机号申请次数>=5：53\"]<br>";
        String message = "3个月内身份证关联多个申请信息:[\"3个月身份证关联邮箱数：0\",\"3个月身份证关联手机号数：5\"]<br>3个月内申请信息关联多个身份证:[\"3个月手机号关联身份证数：3\"]<br>3个月内申请人主要借贷信息在多个网贷平台进行借款申请<br>3个月内身份证在多个网贷平台进行借款申请:[\"一般消费分期平台:3\",\"P2P网贷:1\"]<br>3个月内手机号在多个网贷平台进行借款申请:[\"一般消费分期平台:3\"]<br>1个月内设备或身份证或手机号申请次数过多:[\"1个月内手机号申请次数>=5：56\"]<br>";
        message = message.replaceAll("：",":");
        StringBuilder messageTemp = new StringBuilder();
        messageTemp.append("{");
        String[] valueListA =message.split("<br>");
        for(String value : valueListA)
        {
            String[] valueListB =value.split(":\\[");
            if (valueListB.length == 1){
                messageTemp.append("\""+ valueListB[0]+"\":");
                messageTemp.append("[]");
                messageTemp.append(",");
            }else
            {
                messageTemp.append("\""+ valueListB[0]+"\":[");
                for (int i = 1; i< valueListB.length ; i++){
                    messageTemp.append(valueListB[i]);
                }
                messageTemp.append(",");
            }

        }
        messageTemp.append("}");
        String result = messageTemp.toString();
        result = result.replaceAll(",}","}");
        System.out.println(result);
    }

}
