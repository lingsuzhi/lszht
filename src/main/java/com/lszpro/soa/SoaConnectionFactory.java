package com.lszpro.soa;

import com.alibaba.fastjson.JSON;
import com.lszpro.common.Proper;
import com.lszpro.common.ReflectUtils;
import com.lszpro.soa.common.BaseResponse;
import com.lszpro.exception.SoaInterfaceException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.Set;


public class SoaConnectionFactory {
	protected static Logger log = LoggerFactory.getLogger(SoaConnectionFactory.class);

	private static RestTemplate restTemplate;
	// private final static String url = "http://localhost:8088/abc12366_uc/";
	private   static String url,Version ;
	public static Map<String, String> propertiesMap;
	private static HttpHeaders httpHeaders;
	public final static ObjectMapper mapper = new ObjectMapper();
	static{
		Proper pps = new Proper("soa.properties");
		propertiesMap = pps.getMap();
		url= propertiesMap.get("url");
		Version= propertiesMap.get("Version");
		httpHeaders = getHead(true);
		
		//设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		//禁止使用int代表Enum的order()來反序列化Enum,非常危險
		mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	}
	private Object parameters;
	static {
		int readTimeout = 30000;
		int connectTimeout = 10000;
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(readTimeout);
		requestFactory.setConnectTimeout(connectTimeout);
		restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory);

	}

	private static HttpHeaders getHead(boolean noAdmin ) {
		HttpHeaders headers = new HttpHeaders();		 
		headers.add("Version", Version); 
		return headers;
	}
	private static  HttpEntity<String> exchange(String uri,HttpMethod httpMethod,HttpEntity<Object> httpEntity,Class<String> _class,Object...objArr ){
		log.info("uri:" + uri);
		return restTemplate.exchange(uri, httpMethod,httpEntity,_class, objArr);
	}

	public static String getUrl(ConstantsUri uri ){
	
		return url+ uri.uri;
	}
	public static String getUrl(ConstantsUri uri,Object obj){
		if (obj == null)return getUrl(uri);
		StringBuffer returnUrl = new StringBuffer();
		returnUrl.append( url +uri.uri +  "?"  );
		Map<String, String> map = null;
		try {
		if (!(obj instanceof Map)) {
			map = ReflectUtils.getObjFieldValue(obj);
		} else {
			map = (Map<String, String>) obj;
		}
		if(map != null) {
			Set<String> keys = map.keySet();
			if(keys != null){
				for (String key : keys) {
					String val = (String) map.get(key);
					returnUrl.append("&" + key + "=" + val);
				}
			}
		}
 
	} catch (Exception e){
		String msg = "组装" + uri.describe + "请求参数异常!";
		log.error(msg, e);
		throw new SoaInterfaceException(msg);
	}
		
		return returnUrl.toString();
	}
	private static  <T extends BaseResponse> T todo(HttpMethod httpMethod, ConstantsUri uri, Object obj , Class<T> _class, Object...objArr ){
		HttpEntity<Object> httpEntity = new HttpEntity<Object>(obj, httpHeaders);
		HttpEntity<String> json = null;
		try {
			String uriTmp = "";
			if(httpMethod == HttpMethod.GET){
				uriTmp= getUrl(uri,obj);
			}else{
				uriTmp= getUrl(uri);
			}
		 json = exchange(uriTmp, httpMethod, httpEntity, String.class, objArr);
			log.info("json:{}",json);
	//	ResponseEntity<String> rs = (ResponseEntity<String>)json;
		} catch (Exception e) {
			log.error("SoaFactory  exchange 异常", e);
		}
		try {
			T res = jsonToObj(json.getBody(), _class);
			return res;
		} catch (Exception e) {
			log.error("SoaFactory.parseObject()  soa返回json格式异常", e);
			e.printStackTrace();
			return (T) new BaseResponse("-999", "SOA返回数据格式异常，请联系管理员");
		} 
	}
	public static <T>T jsonToObj(String json,Class<T> cls){
 
		try {
			return mapper.readValue(json, cls);
		} catch (JsonParseException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return null;
	}
	public static   <T extends BaseResponse> T get( ConstantsUri uri,Object obj ,Class<T> _class,Object...objArr ){
		return todo(HttpMethod.GET,uri,obj,_class,objArr);
	}
	public static   <T extends BaseResponse> T put( ConstantsUri uri,Object obj ,Class<T> _class,Object...objArr ){
			return todo(HttpMethod.PUT,uri,obj,_class,objArr);
		}
	public static   <T extends BaseResponse> T delete( ConstantsUri uri,Object obj ,Class<T> _class,Object...objArr ){
		return todo(HttpMethod.DELETE,uri,obj,_class,objArr);
	}
	public static   <T extends BaseResponse> T post( ConstantsUri uri,Object obj ,Class<T> _class,Object...objArr ){
		return todo(HttpMethod.POST,uri,obj,_class,objArr);
	}


	public static ResponseEntity<String> goFase(String url, HttpMethod httpMethod, String data, Object... objArr) {
		//记日志


		log.info("url:{}",url);
		log.info("Method:{}",httpMethod.toString());
		log.info("data:{}",data);
		Map maps = null;
		if (data != null && !data.isEmpty()) {
			maps = (Map) JSON.parse(data);
		}

		HttpEntity<Object> httpEntity = new HttpEntity<Object>(maps, httpHeaders);
		HttpEntity<String> json = restTemplate.exchange(url, httpMethod, httpEntity, String.class, objArr);
		return (ResponseEntity<String>) json;
	}
}
