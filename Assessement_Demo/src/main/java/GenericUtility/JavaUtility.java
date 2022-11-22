package GenericUtility;

import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	/**
	 * generate integer random number with boundary of 0 to 10000
	 * @return int
	 */
	public int getRandomNum()
	{
	    Random random=new Random();
	    int ranNum= random.nextInt(1000);
	    return ranNum;
	}
	
	
	/**
	 * used to get system date and time in IST format
	 * @return String
	 */
	public String getSystemDateAndTime()
	{
		Date date=new Date();
		return date.toString().replace(':','-');
	}
	
	

}
