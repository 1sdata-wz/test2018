import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class test1 {
	
	public static void main(String args[]){
		
		 Calendar start = Calendar.getInstance();  
		    start.set(2016, 3, 10);  
		    Long startTIme = start.getTimeInMillis();  
		  
		    Calendar end = Calendar.getInstance();  
		    end.set(2016, 4, 10);  
		    Long endTime = end.getTimeInMillis();  
		  
		    Long oneDay = 1000 * 60 * 60 * 24l;  
		  
		    Long time = startTIme;  
		    while (time <= endTime) {  
		        Date d = new Date(time);  
		        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
		        System.out.println(df.format(d));  
		        time += oneDay;  
		    }  
		
		
	}
	
}
