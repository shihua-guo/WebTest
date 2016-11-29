package com.briup.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PortUtil {
	static private Properties p;
	static {
		p = new Properties();
		InputStream in = null;
		in = ConfigUtil.class.getResourceAsStream("port.properties");
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
	public static int getPort(String key){
		return Integer.parseInt(p.getProperty(key));
	}
	public static void main(String[] args) {
		System.out.println(PortUtil.getPort("port2"));
	}
}
