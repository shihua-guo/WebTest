package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServletMappingUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in = null;
		in = ConfigUtil.class.getResourceAsStream("servlet_mapping.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public static boolean isContainKey(String key){
		return p.containsKey(key);
	}
	public static String getValue(String key){
		return p.getProperty(key);
	}
	public static void main(String[] args) {
		System.out.println(ServletMappingUtil.isContainKey("/Login"));
		System.out.println(ServletMappingUtil.getValue("/Login"));
	}
}
