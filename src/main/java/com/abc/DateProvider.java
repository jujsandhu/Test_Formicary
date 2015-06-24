package com.abc;

import java.util.Calendar;
import java.util.Date;

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
    
    public static Date subtractDays(int days) {
    	Date date = DateProvider.getInstance().now();
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.DATE, -days);
    	Date subtractedDate = cal.getTime();
    	return subtractedDate;
    }
    
    public static boolean compareDates(Date subtractedDate, Date tDate) {
     	if(tDate.compareTo(subtractedDate) < 0) {
    		return false;
    	}
    	return true;   	
    }
}
