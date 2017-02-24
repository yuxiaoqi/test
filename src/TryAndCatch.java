/**
 * Created by admin on 2016/11/16.
 */
public class TryAndCatch {
    public static void main(String[] args) {
        System.out.println(TryAndCatch.getX());
    }

    public static int getX() {
        int x;
        try {
            x =1;
            if(1==1){
                throw new Exception("ceshi");
            }
            return x;
        }catch (Exception e){
            x= 2;
            return x;
        }
        finally{
            x=3;
            return x;
        }
    }
}
