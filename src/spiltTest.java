/**
 * Created by admin on 2016/4/12.
 */
public class spiltTest {

    public  static  void main(String[] args){


        String[] aa="1_2_trtertert_43423".split("_");
        System.out.println(aa[0]);
        for(String s : aa)
        {
            System.out.println(s);
        }
    }

}
