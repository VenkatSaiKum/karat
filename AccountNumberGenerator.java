package util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountNumberGenerator {

    public static long generateAccountNumber() {

        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmssSSS");

        String timestamp = sdf.format(new Date());

        return Long.parseLong(timestamp);
    }
}