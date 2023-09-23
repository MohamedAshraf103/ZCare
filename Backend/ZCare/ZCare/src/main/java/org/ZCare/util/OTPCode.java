package org.ZCare.util;

public class OTPCode {
	public static String getCode()
	{
		int randomPin   =(int) (Math.random()*9000)+1000;
        String otp  = String.valueOf(randomPin);
        return otp; //returning value of otp
	}

}
