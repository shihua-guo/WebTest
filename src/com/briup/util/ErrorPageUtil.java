package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorPageUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in=null;
		in = ConfigUtil.class.getResourceAsStream("error_page.properties");
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
	static public String getErrorPage(int key){
		return getErrorPage(key+"");
	}
	static public String getErrorPage(String key){
		return p.getProperty(key);
	}
	public static void main(String[] args){
		System.out.println(ErrorPageUtil.getErrorPage(404));
	}
}
