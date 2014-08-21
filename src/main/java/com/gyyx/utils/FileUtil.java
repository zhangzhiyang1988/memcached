package com.gyyx.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
	public static void writeTofile(String s){
		File file = new File("d:/zzy_test.txt");
		FileWriter writer =null ;
		try {
		    writer =  new FileWriter(file, true);
			writer.append(s);
			writer.append("\r\n");
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
}
