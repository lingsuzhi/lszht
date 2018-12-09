package com.lszpro.soa;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lszpro.cache.MemCache;
import com.lszpro.common.Proper;
import com.lszpro.common.ReflectUtils;
import com.lszpro.exception.SoaInterfaceException;
import com.lszpro.soa.common.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

@Slf4j
public class SoaFactory {
    public static Map<String, String> propertiesMap;
    private static RestTemplate restTemplate;
    private static String url, Version;
    private static HttpHeaders httpHeaders;

    static {
        Proper pps = new Proper("soa.properties");
        propertiesMap = pps.getMap();
        url = propertiesMap.get("url");
        Version = propertiesMap.get("Version");
        httpHeaders = getHead(true);
    }

    static {
        int readTimeout = 30000;
        int connectTimeout = 10000;
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(readTimeout);
        requestFactory.setConnectTimeout(connectTimeout);
        restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);

    }

    private Object parameters;

    private static HttpHeaders getHead(boolean noAdmin) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Version", Version);
        return headers;
    }

    private static HttpEntity<String> exchange(String uri, HttpMethod httpMethod, HttpEntity<Object> httpEntity, Class<String> _class, Object... objArr) {
        return restTemplate.exchange(uri, httpMethod, httpEntity, _class, objArr);
    }

    private static String getUrl(UrlMappingDTO uri) {

        return url + uri.getUrl();
    }

    private static String getUrl(UrlMappingDTO uri, Object obj) {
        if (obj == null) return getUrl(uri);
        StringBuffer returnUrl = new StringBuffer();
        returnUrl.append(url + uri.getUrl() + "?");
        Map<String, String> map = null;
        try {
            if (!(obj instanceof Map)) {
                map = ReflectUtils.getObjFieldValue(obj);
            } else {
                map = (Map<String, String>) obj;
            }
            if (map != null) {
                Set<String> keys = map.keySet();
                if (keys != null) {
                    for (String key : keys) {
                        String val = (String) map.get(key);
                        returnUrl.append("&" + key + "=" + val);
                    }
                }
            }

        } catch (Exception e) {
            String msg = "组装 " + uri.getName() + " 请求参数异常!";
            log.error(msg, e);
            throw new SoaInterfaceException(msg);
        }

        return returnUrl.toString();
    }
    private static  void printLittle(String str){
        if(StringUtils.isEmpty(str)){
            return;
        }
        String tmpStr = str;
        if(tmpStr.length()>333){
            tmpStr = str.substring(0,333) + " ... ";
        }
        log.info("{}", tmpStr.replace("\n"," ").replace("\r","").replace("\t",""));
    }
    private static <T extends ResponseInfo> T todo(HttpMethod httpMethod, UrlMappingDTO uri, Object obj, TypeReference<T> _class, Object... objArr) {
        if (uri == null) {
            return null;
        }
        //1、先查缓存
        if(uri.getCache() != null && uri.getCache()>0){
            Object cacheObj = MemCache.get(uri.getUrl());
            if (cacheObj !=null){
                log.info("从缓存中拿到了！接口： {} {}",uri.getName(),uri.getUrl());
                return (T)cacheObj;
            }
        }
        HttpEntity<Object> httpEntity = new HttpEntity<Object>(obj, httpHeaders);
        HttpEntity<String> json = null;
        try {
            String uriTmp = "";
            if (httpMethod == HttpMethod.GET) {
                uriTmp = getUrl(uri, obj);
            } else {
                uriTmp = getUrl(uri);
            }
            json = exchange(uriTmp, httpMethod, httpEntity, String.class, objArr);
            //	ResponseEntity<String> rs = (ResponseEntity<String>)json;
        } catch (Exception e) {
            log.error("SoaFactory  exchange 异常", e);
        }
        try {
            String jsonStr = json.getBody();
            if(!StringUtils.isEmpty(jsonStr)){
                printLittle(jsonStr);
            }
            T res = jsonToObj(jsonStr, _class);
            if(uri.getCache() != null && uri.getCache()>0){
                MemCache.put(uri.getUrl(),res,uri.getCache(),uri.getName());
            }
            return res;
        } catch (Exception e) {
            log.error("SoaFactory.parseObject()  soa返回json格式异常", e);
            e.printStackTrace();
         }
        return (T) new ResponseInfo("2999", "SOA返回数据格式异常，请联系管理员", null);
    }

    public static <T> T jsonToObj(String json, TypeReference<T> cls) {
        T t = JSONObject.parseObject(json, cls);
        return t;
    }

    public static <T extends ResponseInfo> T get(UrlMappingDTO uri, Object obj, TypeReference<T> _class, Object... objArr) {
        return todo(HttpMethod.GET, uri, obj, _class, objArr);
    }

    public static <T extends ResponseInfo> T put(UrlMappingDTO uri, Object obj, TypeReference<T> _class, Object... objArr) {
        return todo(HttpMethod.PUT, uri, obj, _class, objArr);
    }

    public static <T extends ResponseInfo> T delete(UrlMappingDTO uri, Object obj, TypeReference<T> _class, Object... objArr) {
        return todo(HttpMethod.DELETE, uri, obj, _class, objArr);
    }

    public static <T extends ResponseInfo> T post(UrlMappingDTO uri, Object obj, TypeReference<T> _class, Object... objArr) {
        return todo(HttpMethod.POST, uri, obj, _class, objArr);
    }

    public static <T extends ResponseInfo> T post(String apiKey, Object obj, TypeReference<T> _class, Object... objArr) {
        UrlMappingDTO urlMappingDTO = UrlCache.getUrlMapping(apiKey);
        return todo(HttpMethod.POST, urlMappingDTO, obj, _class, objArr);
    }

    public static <T extends ResponseInfo> T get(String apiKey, Object obj, TypeReference<T> _class, Object... objArr) {
        UrlMappingDTO urlMappingDTO = UrlCache.getUrlMapping(apiKey);
        return todo(HttpMethod.GET, urlMappingDTO, obj, _class, objArr);
    }
}
