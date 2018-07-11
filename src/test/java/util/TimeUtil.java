package util;

import org.junit.Test;

import java.util.Calendar;

public class TimeUtil {

    @Test
    public void testTimeCalc() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -30);
        System.out.println(calendar.getTime());
    }
}
