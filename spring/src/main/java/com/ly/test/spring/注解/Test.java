package com.ly.test.spring.×¢½â;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Test
{
	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, IOException
	{
		Properties prop = new Properties();
		String filePath = "D:\\Work\\Workspace\\scec_dis\\spring\\src\\main\\resources\\anno.properties";
		prop.load(new FileInputStream(new File(filePath)));
		
		AnnotationManager annot = new AnnotationManager(prop);
		UserAction userAction = (UserAction) annot.getBean("userAction");
		userAction.life();
	}
}
