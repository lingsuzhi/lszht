package com.lszpro.common;

import java.io.*;

public class FileUtil {
	
	/**
	 * 将一个文本写到文件，如果已经存在 会先删除
	 * @param file
	 * @param fileStr
	 */
	public static void doFileStr(File file,String fileStr){
		//把字符串写到文件，存在将先删除
		if(file.exists()){
			file.delete();
		}
		OutputStreamWriter osw = null;
		try {
			 osw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
			osw.write(fileStr);
		} catch (UnsupportedEncodingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally {
			if(osw != null)
				try {
					osw.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
	}
	private static void doFile(String fileName,byte[] content){
		File file = new File(fileName);
		if(file.exists()) file.delete();
		try {

			RandomAccessFile raf = new RandomAccessFile(file,"rw");

			raf.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
