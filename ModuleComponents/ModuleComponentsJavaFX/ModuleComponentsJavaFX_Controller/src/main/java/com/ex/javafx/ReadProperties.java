package com.ex.javafx;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import javafx.scene.image.Image;

public class ReadProperties {
	public static String fxmlcav;
	public static String fxmlchv;
	public static String fxmlpcv;
	public static String fxmllvx;
	public static String fxmlccw;
	public static String fxmlmnu;
	public static String fxmlrgw;
	public static String fxmllgw;
	public static String parentFolder_voucher;
	public static String parentFolder_cheque;
	public static String printConfCheque;
	public static String printInfoCheque;
	
	private static InputStream inputStream;
	
	static {
		try {
			Properties prop = new Properties();
			final String propFileName = "config.properties";
			inputStream = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			
			fxmlcav = prop.getProperty("fxmlcav");
			fxmlchv = prop.getProperty("fxmlchv");
			fxmlpcv = prop.getProperty("fxmlpcv");
			fxmllvx = prop.getProperty("fxmllvx");
			fxmlccw = prop.getProperty("fxmlccw");
			fxmlmnu = prop.getProperty("fxmlmnu");
			fxmlrgw = prop.getProperty("fxmlrgw");
			fxmllgw = prop.getProperty("fxmllgw");
			parentFolder_voucher = prop.getProperty("spring.voucher.parentFolder");
			parentFolder_cheque = prop.getProperty("spring.cheque.parentFolder");
			printConfCheque = prop.getProperty("printConfCheque");
			printInfoCheque = prop.getProperty("printInfoCheque");
		} catch (Exception e) {
			
		}
	}
}
