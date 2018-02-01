package com.lszpro.common;

import java.io.*;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
 
public class Proper {

    private java.util.Properties properties;

    private String filePath;
     
    public Proper(String filePath) {
        this.filePath = filePath;
        OpenRead(); 
    }
    public static String mapToProperties(Map<String,String> maps){
    	Iterator<Entry<String,String>> i = maps.entrySet().iterator();
        if (! i.hasNext())
            return "";

        StringBuilder sb = new StringBuilder();
        for (;;) {
            Entry<String,String> e = i.next();
            String key = e.getKey();
            String value = e.getValue();
            sb.append(key);
            sb.append('=');
            sb.append(value);
           
            if (! i.hasNext())
                return sb.toString();
            sb.append("\r\n");
        }
    }
//  public static void main(String[] args) {
// 	   String filePath = "head.properties";
// 	  Proper pps = new Proper(filePath);
////    	pps.setValue("Version", "111");
//    	   
//    	Map map = pps.getMap();
// 
//     	System.out.println(map);
//     	String fileStr = mapToProperties(map);
//     	File file =  new File(Proper.class.getClassLoader().getResource(filePath).getFile());
//     	FileUtil.doFileStr(file, fileStr);
//}
  public void saveToFile(){
		Map map =this.getMap(); 
     	String fileStr = mapToProperties(map);
     	File file =  new File(Proper.class.getClassLoader().getResource(filePath).getFile());
     	FileUtil.doFileStr(file, fileStr);
  }
    public void setValue(String pKey,String pValue){
    	 properties.setProperty(pKey, pValue);
    	
    }
    private OutputStreamWriter getWrite(){
        
        OutputStreamWriter osw;
		try {
			URL url = this.getClass().getClassLoader().getResource(filePath);
	 
			
			OutputStream ostream  = new FileOutputStream(new File(url.getFile()));
 
				osw = new OutputStreamWriter(ostream,"UTF-8");
				return osw;
 
	      } catch (UnsupportedEncodingException e) {
 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
		return null;
    }
    private void OpenRead(){
        properties = new java.util.Properties();
        
        InputStreamReader isr;
		try {
			isr = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(filePath),"UTF-8");
		
		      properties.load(isr);
	      } catch (UnsupportedEncodingException e) {
 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}  
    }
    public Map getMap(){
 
        Enumeration enum1 = properties.propertyNames();//得到配置文件的名字
        Map<String,String> map = new HashMap<String,String>();
        while(enum1.hasMoreElements()) {
            String strKey = (String) enum1.nextElement();
            String strValue = properties.getProperty(strKey);
            map.put(strKey, strValue);
       }
        return map;
    }
    public String getValue(String code) throws IOException {
    	
        return properties.getProperty(code);
    }
}
