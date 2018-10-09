package com.gzz.createcode.template.ios;

import java.text.SimpleDateFormat;
import java.util.Date;

public class IOSUtil {

	public static String getType(String type) {
		if (type.equals("String"))
			return "NSString";
		if (type.equals("Integer"))
			return "NSInteger";
		if (type.equals("Date"))
			return "NSDate";
		if (type.equals("Long"))
			return "long";
		if (type.equals("Float"))
			return "CGFloat";
		if (type.equals("Byte"))
			return "Byte";
		if (type.equals("Double"))
			return "double";
		return "NSString";
	}

	public static String getTime() {
		return new SimpleDateFormat("HH:mm:ss").format(new Date());
	}

	public static String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}

	public static StringBuffer getNote(String upp, String auth) {
		StringBuffer sb = new StringBuffer();
		sb.append("\r\n//");
		sb.append("\r\n//  " + upp + ".m");
		sb.append("\r\n//");
		sb.append("\r\n//  Created by " + auth + " on " + getTime() + ".");
		sb.append("\r\n//  Copyright © " + getDate() + "年 " + auth + ". All rights reserved.");
		sb.append("\r\n//");
		return sb;
	}
}
