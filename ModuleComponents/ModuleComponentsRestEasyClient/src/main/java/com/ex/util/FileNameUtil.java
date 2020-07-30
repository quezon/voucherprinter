package com.ex.util;

import org.springframework.stereotype.Service;

public class FileNameUtil {
	public static String getFileName(String parentFolder, String endsWith, Long id) {
		StringBuffer sb = new StringBuffer(endsWith);
	    sb.insert(sb.indexOf("."), String.format("%06d" , 678));
		sb.insert(0, parentFolder);
		return sb.toString();
	}
}
