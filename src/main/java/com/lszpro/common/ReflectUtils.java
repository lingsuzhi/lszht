package com.lszpro.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JiangZuoWei
 * @createTime 2015年11月26日 下午 15:44:32
 * @description 
 */
public class ReflectUtils {

	protected static Logger _log = LoggerFactory.getLogger(ReflectUtils.class);
	
	public static <T> Class<T> getClassGenricType(final Class clazz) {
		return getClassGenricType(clazz, 0);
	}

	
	public static Class getClassGenricType(final Class clazz, final int index) {
		Type genType = clazz.getGenericSuperclass();
		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}
		Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}

	public static Map<String, String> getObjFieldValue(Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Map<String, String> map = new HashMap<String, String>();
		Class<?> clazz = obj.getClass() ;  
        for(; clazz != Object.class ; clazz = clazz.getSuperclass()) {
        	Field[] fields = clazz.getDeclaredFields();
    		for (int i = 0; i < fields.length; i++) {
    			String keyValue = "";
    			String fieldName = fields[i].getName();
    			String fieldType = fields[i].getGenericType().toString();
    			String methodName = fieldName.substring(0, 1).toUpperCase()
    					+ fieldName.substring(1);
    			if (fieldType.equals("class java.lang.String")) {
    				Method m = obj.getClass().getMethod("get" + methodName);
    				String value = (String) m.invoke(obj);
    				keyValue = value == null ? "" : value;
    				map.put(fieldName, keyValue);
    			}
    			if (fieldType.equals("class java.lang.Integer")) {
    				Method m = obj.getClass().getMethod("get" + methodName);
    				Integer value = (Integer) m.invoke(obj);
    				keyValue = value == null ? "" : value.toString();
    				map.put(fieldName, keyValue);
    			}
    			
    			//2017-06-19
    			if (fieldType.equals("class java.lang.Boolean")) {
    				Method m = obj.getClass().getMethod("get" + methodName);
    				Boolean value = (Boolean) m.invoke(obj);
    				map.put(fieldName, value == null?"":String.valueOf(value));
    			}
    		}
        }
		return map;
	}
	
	public static Map<String,String> getDtoFieldValue(Object obj) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Map<String,String> map = new HashMap<String,String>();
		try{
			Field[] fields = obj.getClass().getDeclaredFields();
			for(int i=0; i<fields.length; i++){
				String keyValue = "";
				String fieldName = fields[i].getName();
				String fieldType = fields[i].getGenericType().toString();
				String methodName = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1); 
				if(fieldType.equals("class java.lang.String")){
					Method m = obj.getClass().getMethod("get"+methodName);
					String value = (String) m.invoke(obj);
					keyValue = value == null ? "" : value;
					map.put(fieldName, keyValue);
				}else if(fieldType.equals("class java.lang.Short")){    
	                Method m = obj.getClass().getMethod("get"+methodName);
	                Short value = (Short) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }else if(fieldType.equals("class java.lang.Double")){    
	                Method m = obj.getClass().getMethod("get"+methodName);
	                Double value = (Double) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }else if(fieldType.equals("class java.lang.Boolean")){
	                Method m = obj.getClass().getMethod("get"+methodName);   
	                Boolean value = (Boolean) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }else if(fieldType.equals("class java.util.Date")){
	                Method m = obj.getClass().getMethod("get"+methodName);                   
	                Date value = (Date) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }else if(fieldType.equals("class java.math.BigDecimal")){
	                Method m = obj.getClass().getMethod("get"+methodName);                   
	                BigDecimal value = (BigDecimal) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }else if(fieldType.equals("class java.lang.Long")){
	                Method m = obj.getClass().getMethod("get"+methodName);                   
	                Long value = (Long) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }else if(fieldType.equals("class java.lang.Integer")){
	                Method m = obj.getClass().getMethod("get"+methodName);                   
	                Integer value = (Integer) m.invoke(obj);
	                keyValue = value == null ? "" : value.toString();
					map.put(fieldName, keyValue);
	            }
			}
		}catch(Exception e){
			_log.error(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}
	
	
}
