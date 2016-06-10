package com.abc;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateProvider {
    private static DateProvider instance = null;

    public static DateProvider getInstance() {
        if (instance == null)
            instance = new DateProvider();
        return instance;
    }

    public Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public long getDateDiff(Date date1, Date date2) {
        long diffInMillis = date2.getTime() - date1.getTime();
        long diff = TimeUnit.MILLISECONDS.convert(diffInMillis,TimeUnit.DAYS);
        return diff;
    }
}
