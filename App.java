package msgCase;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy";
//
//        Date testDate = new Date();
////        String dateString = testDate.toString();
        String dateString = "10/25/2022";
//
//        Date date = sdf.parse(dateString);
//
//        System.out.println(date);
        SimpleDateFormat sdf  = new SimpleDateFormat("MM/dd/yyyy");
        Date parse = sdf.parse(dateString);
        Calendar  expectedAnnualReturnUpdated = Calendar.getInstance();
        expectedAnnualReturnUpdated.setTime(parse);
        System.out.println(expectedAnnualReturnUpdated.get(Calendar.MONTH) + expectedAnnualReturnUpdated.get(Calendar.DATE) + expectedAnnualReturnUpdated.get(Calendar.YEAR));
    }
}
