package msgCase;

import org.junit.jupiter.api.DisplayNameGenerator;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
//        Date now = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//        String strDate = sdf.format(now);
//        System.out.println(strDate);
//        System.out.println(now.toString());
//        Date parsedDate = sdf.parse(now.toString());
//        System.out.print(parsedDate);

        SimpleDateFormat sdf2 = new SimpleDateFormat("MM/dd/yy");
        String testDate = "05/24/22";
        Date parsedDate2 = sdf2.parse(testDate);
        System.out.println(parsedDate2);

        Calendar cal = new GregorianCalendar();
//        cal.setTime();

    }
}
