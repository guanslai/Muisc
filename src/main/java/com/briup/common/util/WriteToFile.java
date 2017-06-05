package com.briup.common.util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteToFile {
	public static void write(String str){
		String writeFile = "F:/MavenWorkspace/Music/src/main/resources/a.txt";
		  try {  
	            FileOutputStream out = new FileOutputStream(writeFile,true);  
	            OutputStreamWriter outWriter = new OutputStreamWriter(out, "UTF-8");  
	            BufferedWriter bufWrite = new BufferedWriter(outWriter);  
	            bufWrite.write(str+"\r\n");
	            bufWrite.close();  
	            outWriter.close();  
	            out.close();  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }         
	}
}
