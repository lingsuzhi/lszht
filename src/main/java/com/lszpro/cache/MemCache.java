package com.lszpro.cache;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MemCache {
    private static List<MemCacheBO> cacheList = new LinkedList<>();

//    public static void main(String[] args) {
//        MemCache.put("1","111",2);
//        MemCache.put("1","112",2);
//        MemCache.put("2","222",1);
//
//        Object o1 = MemCache.get("1");
//        MemCache.put("1","112",2);
////sleep 1
//        Object o2 = MemCache.get("2");
//        System.out.println(o1);
//        System.out.println(o2);
//    }
    public static LocalDateTime put(String key, Object data){
        return put(key,data,10,LocalDateTime.now().toString());
    }
    /**
     * 设置缓存
     * @param key
     * @param data
     * @param endMinute 结束的时间，分钟为单位
     * @return
     */
    public static LocalDateTime put(String key, Object data, Integer endMinute,String tag) {
        if (StringUtils.isEmpty(key)){
            return null;
        }
        refreshDo();
        LocalDateTime laggingDate = LocalDateTime.now().plusMinutes(endMinute);
        MemCacheBO memCacheBO = new MemCacheBO(laggingDate, data, key,tag);
        for (MemCacheBO mem :cacheList){
            if (key.equals(mem.getKey())){
                cacheList.remove(mem);
                break;
            }
        }
        cacheList.add(memCacheBO);
        return laggingDate;
    }
    public static Object get(String key){
        //即使过期的对象，也是可能返回的
        for (MemCacheBO mem :cacheList){
            if (key.equals(mem.getKey())){
                return  mem.getData();
            }
        }
        return null;
    }
    //删除过期的对象
    private static void refreshDo() {
        Iterator<MemCacheBO> it = cacheList.iterator();
        while (it.hasNext()) {
            MemCacheBO memCacheBO = it.next();

            if (LocalDateTime.now().isAfter(memCacheBO.laggingDate)) {
                it.remove();
            }
        }
    }

    public static class MemCacheBO {
        private LocalDateTime laggingDate;
        private Object data;
        private String key;
        //备注
        private String tag;
        public MemCacheBO(LocalDateTime laggingDate, Object data, String key,String tag) {
            this.laggingDate = laggingDate;
            this.data = data;
            this.key = key;
            this.tag=tag;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public LocalDateTime getLaggingDate() {
            return laggingDate;
        }

        public void setLaggingDate(LocalDateTime laggingDate) {
            this.laggingDate = laggingDate;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }


}
