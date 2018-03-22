package com.estar.civilPlaint;

import java.io.File;
import java.io.IOException;

import com.estar.common.util.HtmlUtil;

public class test {

	public static void main(String[] args) throws IOException {
			String articleContent = getFilestr();
			String[] array = articleContent.split("？");
			System.out.println(array.length);
	}
	
	private static String getFilestr() throws IOException{
		String path = "C:/Users/Administrator/Desktop/2"; // 路径
		String content ="";
		File f = new File(path);
		if (!f.exists()) {
		    System.out.println(path + " not exists");
		    return content;
		}
		
		File fa[] = f.listFiles();
		for (int i = 0; i < fa.length; i++) {
		    File fs = fa[i];
		    if (fs.isDirectory()) {
		        System.out.println(fs.getName() + " [目录]");
		    } else {
		    	content = HtmlUtil.removeTagAndSurplusSpace(TxtReader.read(fs));
		   }
		}
		
		return content;
	}
}
