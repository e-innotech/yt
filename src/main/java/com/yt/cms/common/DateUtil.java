package com.yt.cms.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public static String getDateStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	public static void main(String[] args) {
		System.out.println(getDateStr(new Date()));
	}
}
