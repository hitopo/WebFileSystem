package util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 加密算法的实现工具类
 * 以实现将用户密码加密保存在数据库中
 */
public class Md5Util {
    /**
     * 加密算法的实现（MD5加密）
     * 返回的是加密后的字符串
     */
    public static String encrypt(String str) {
        String result = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            result = base64en.encode(md5.digest(str.getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static void main(String[] args) {
        System.out.println(Md5Util.encrypt("admin"));
    }
}
