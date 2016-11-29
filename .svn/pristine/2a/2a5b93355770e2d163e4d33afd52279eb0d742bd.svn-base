package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StatusCodeUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in = null;
		in = ConfigUtil.class.getResourceAsStream("status_code.properties");
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
	public static String getStatus(String key){
		return p.getProperty(key);
	}
	public static String getStatus(int key){
		return getStatus(key+"");
	}
	public static void main(String[] args) {
		System.out.println(StatusCodeUtil.getStatus(404));
	}
}
