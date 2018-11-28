package chapter1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description
 * @Author Skye
 * @Date 2018/11/26 21:42
 * @Version 1.0
 **/
public class test {
    public static void main(String[] args) {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd号 HH:mm:ss");
        Date date = new Date();
        String strDate = format.format(date);
        System.out.println(strDate);
        try {
            String strDate2 = "2018年10月15号 12:00:45";
            Date date1 = format.parse(strDate2);
            System.out.println(date1.toString());
        }catch (ParseException e){
            e.printStackTrace();
        }

        double timeStamp = System.currentTimeMillis();
        System.out.println(timeStamp);
        Date date1 = new Date();
        String timeStampStr = format.format(date1);
        System.out.println(timeStampStr);

//        double i = (double)1/3;
//        System.out.println(i);
    }
}
