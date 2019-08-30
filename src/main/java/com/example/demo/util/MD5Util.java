package com.example.demo.util;

//import org.springframework.util.StringUtils;

import java.security.MessageDigest;
//123 202cb962ac59075b964b07152d234b70
/**
 * Created by geely
 */
//MD5也有一个字典，随着时间积累越来越强大，很多可以破解
//http://www.cmd5.com/这个网站可以解码一些MD5
//所以我们要在加密后加上自己自定义的字符加大破解难度
public class MD5Util {
    /**
     * 内部使用的加密算法
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    /**
     * 内部使用的加密算法
     * @param b
     * @return
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5
     *
     * @param origin
     * @param charsetname
     * @return
     */
    private static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString.toUpperCase();
    }

    /**
     * 只开放一个public方法给我们访问
     * @param origin
     * @return
     */
    public static String MD5EncodeUtf8(String origin) {
        origin = origin + PropertiesUtil.getProperty("password.salt", "geelysdafaqj23ou89ZXcj@#$@#$#@KJdjklj;D../dSF..");
        return MD5Encode(origin, "utf-8");
    }


    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

}
