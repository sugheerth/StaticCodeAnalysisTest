//$Id$
package com.zoho.rmi.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class CommonUtil {
	private static String err = "";
	/**
	 * To send mail 
	 */
	public static void sendMail(String fromAddress,String toAddress,String subject,String message) {
		SendMail sd = new SendMail(fromAddress,toAddress, subject,message);
		try {
			sd.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * creates  a random number
	 * @return String
	 */
	public static String createRandomString(int length) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		while (sb.length() < length) {
			sb.append(Integer.toHexString(random.nextInt()));
		}
		return sb.toString();
	}

	/**
	 * To cat the file content 
	 */
	public static String getFileContent(File file) {
		String line = "", fileContent = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			while((line = reader.readLine()) != null){
				fileContent += line + "\r\n";
			}
			reader.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return fileContent;
	}

	/**
	 * To calcualte difference between the start time and endtime 
	 * HH converts hour in 24 hours format (0-23), day calculation
	 * Converting the difference of start and stop time (milliseconds) to seconds,minutes,hours,days
	 * @return String
	 */
	public static String calculateDuration(String startTime,String stopTime) {
		String duration=null;
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(startTime);
			d2 = format.parse(stopTime);
			long diffMSecs = d2.getTime() - d1.getTime();
			long diffSeconds = diffMSecs / 1000 % 60;
			long diffMinutes = diffMSecs / (60 * 1000) % 60;
			long diffHours = diffMSecs / (60 * 60 * 1000) % 24;
			long diffDays = diffMSecs / (24 * 60 * 60 * 1000);
			duration = "Days:"+diffDays+" Hours:"+diffHours+" Mins:"+diffMinutes+" Secs:"+diffSeconds;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return duration;
	}
	
	/**
	 * To convert milliSec to date time
	 * @param timeInMS
	 * @return
	 */
	public static String getResultDate(long timeInMS){
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        Date resultdate = new Date(timeInMS);
        return sdf.format(resultdate);
	}
	
	/**
	 * To convert milli seconds to minutes 
	 */
	public static long convertMillisToMinutes(long value) {
		return (value / 60000);
	}
	
	
	/**
	 * Returns the ip address of the local host
	 * @return String
	 */
	public static String getHostAddress(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			LogManager.info("Error in fetching host address");
		}
		return err;
	}
	
	/**
	 * Gets the fully qualified domain name
	 * @return String
	 */
	public static String getCanonicalHostName(){
		try {
			return InetAddress.getLocalHost().getCanonicalHostName();
		} catch (UnknownHostException e) {
			LogManager.info("Error in fetching host address");
		}
		return err;
	}
	
	/**
	 * Returns the host name address of the local host
	 * @return String
	 */
	public static String getHostName(){
		try {
			return InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			LogManager.info("Error in fetching host address");
		}
		return err;
	}

	
}