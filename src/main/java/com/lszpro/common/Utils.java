package com.lszpro.common;

import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {
    public static Integer toInt(String str){
        if(str== null || str.isEmpty())return 0;

        return Integer.parseInt(str);
    }
    public static Double toDouble(String str){
        if(str== null || str.isEmpty())return 0.0;

        return Double.parseDouble(str);
    }
    /**
     * 返回系统唯一UUUID
     *
     * @return uuid
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) throws Exception {
        System.out.println(md5("12345678"));
    }

    /**
     * 返回Map形式的对象，参数必须为偶数个
     *
     * @param kvs 键值对
     * @return Map
     */
    public static Map kv(Object... kvs) {

        Map<Object, Object> map = new HashMap<>();
        map.put("code", 2000);
        try {
            map.put("message", "处理成功" );
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (kvs.length % 2 != 0) {
            throw new RuntimeException("Params must be key, value pairs.");
        }
        for (int i = 0; i < kvs.length; i += 2) {
            map.put(kvs[i], kvs[i + 1]);
        }
        return map;
    }
   
//
//    /**
//     * 返回枚举类型代码
//     *
//     * @param code int
//     * @return BodyStatus
//     */
//    public static BodyStatus bodyStatus(int code) {
//        BodyStatus body = new BodyStatus();
//        body.setCode(String.valueOf(code));
//        try {
//            body.setMessage(Message.getValue(code));
//        } catch (IOException e) {
//            e.printStackTrace();
//            body.setMessage(e.getMessage());
//        }
//        return body;
//    }

    /**
     * 返回第三方错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static BodyStatus bodyStatus(int code, String message) {
        BodyStatus body = new BodyStatus();
        body.setCode(String.valueOf(code));
        body.setMessage(message);
        return body;
    }

    /**
     * 返回第三方错误信息
     *
     * @param code
     * @param message
     * @return
     */
    public static BodyStatus bodyStatus(String code, String message) {
        BodyStatus body = new BodyStatus();
        body.setCode(String.valueOf(code));
        body.setMessage(message);
        return body;
    }


    /**
     * md5字符串
     *
     * @param str 需要计算的字符串
     * @return String
     * @throws Exception Exception
     */
    public static String md5(String str) throws Exception {
        Assert.notNull(str, "MD5 string is not empty");
        MessageDigest digest = MessageDigest.getInstance("md5");
        byte[] bs = digest.digest(str.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : bs) {
            int temp = b & 255;
            if (temp < 16) {
                hexString.append("0").append(Integer.toHexString(temp));
            } else {
                hexString.append(Integer.toHexString(temp));
            }
        }
        return hexString.toString();
    }

    /**
     * Base64 编码
     *
     * @param str 需要编码的字符串
     * @return String
     */
    public static String encode(String str) {
        return Base64.getEncoder().encodeToString(str.getBytes());
    }

    /**
     * Base64 解码
     *
     * @param str 需要解码的字符串
     * @return String
     */
    public static String decode(String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    /**
     * 生成token
     * return String token
     */
    public static String token() throws Exception {
        return md5(uuid());
    }

    public static String token(String str) throws Exception {
        return md5(str);
    }

}
