package com.estar.civilPlaint;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class TxtReader {
	public static String  read(File file) throws IOException{
		return readtext(file);
	}
	public static String readTxt(File file) throws IOException{
		FileInputStream fi = null;
		String str = "";
		try {
			fi = new FileInputStream(file);
			byte[] buffer = new byte[1024];
			int len = 0;
			while((len = fi.read(buffer))> 0){
				str += new String(buffer,0,len,"gb2312");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fi != null){
				fi.close();
			}
		}
		return str;
	}
	
	public static String readtext(File file){
		String fileContent ="";
		try {
			if(file.isFile() && file.exists()){
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"gbk");
				BufferedReader reader = new BufferedReader(read);
				String line;
				while ((line = reader.readLine()) != null) {
					line = line +"";
					fileContent +=line;
				}
				read.close();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}	
		return fileContent;
	}
	
	public static void main(String[] args) throws IOException {
		String path	 = "C:\\Users\\Administrator\\Desktop\\12\\1.txt";
		String content =read(new File(path));
		System.out.println(content);
	}
}
