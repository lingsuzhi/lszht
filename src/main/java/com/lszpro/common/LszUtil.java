package com.lszpro.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lszpro.exception.ServiceException;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.springframework.http.ResponseEntity;

import java.beans.PropertyDescriptor;
import java.util.*;


public class LszUtil {
public static ResponseEntity returnList(List list,Integer total){
	

	return ResponseEntity.ok( toMap("data", list,"count",total,"msg","","code",0) );
}
/**获取一位随机数 0到9**/
public static int getRand0to9(){
	Random r = new Random();
	//r.setSeed(System.currentTimeMillis());
	return r.nextInt(10);
}
//将javabean实体类转为map类型，然后返回一个map类型的值
public static Map<String, Object> beanToMap(Object obj) { 
        Map<String, Object> params = new HashMap<String, Object>(0); 
        try { 
            PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean(); 
            PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj); 
            for (int i = 0; i < descriptors.length; i++) { 
                String name = descriptors[i].getName(); 
                if (!"class".equals(name)) { 
                    params.put(name, propertyUtilsBean.getNestedProperty(obj, name)); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return params; 
}
    public static Map<String, String> jsonObjToMap(Object obj) {
        String json = JSON.toJSONString(obj);
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String,String> map = new HashMap<String,String>();
        try{
            map = mapper.readValue(json,HashMap.class );
            return map;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
/**
 * 返回Map形式的对象，参数必须为偶数个
 *
 * @param kvs 键值对
 * @return Map
 */
public static Map toMap(Object... kvs) {

    Map<Object, Object> map = new HashMap<>();

    if (kvs.length % 2 != 0) {
        throw new RuntimeException("Params must be key, value pairs.");
    }
    for (int i = 0; i < kvs.length; i += 2) {
    	Object obj = kvs[i + 1];
	    	map.put(kvs[i], obj);
    }
    return map;
}

	public static String createToken(String id) {
	 
	if (id ==null || id.isEmpty()){
		  throw new ServiceException("9999","用户 id 错误");
	}
	Date date = new Date();
	long l = date.getTime() / (1000*60*60*24*7L);
	String tokenStr =  id + String.valueOf(l);

		try {
			tokenStr = Utils.md5(tokenStr);
			return tokenStr;
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
}
}
