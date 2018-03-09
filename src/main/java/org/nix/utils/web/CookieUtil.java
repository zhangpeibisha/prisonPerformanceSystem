package org.nix.utils.web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.nix.utils.sign.aes.Base64;

public class CookieUtil {
    /**
     * 保存cookie时的cookieName
     */
    private static String cookieDomainName = "ycxcLogin";
    /**
     * 加密cookie时的网站自定码
     */
    private final static String webKey = "ycxc";
    /**
     * 设置cookie有效期是一个星期，根据需要自定义
     */
    private final static long cookieMaxAge = 60 * 60 * 24 * 7 * 1;


    public CookieUtil(String cookieDomainName) {
        CookieUtil.cookieDomainName = cookieDomainName;
    }


    /**
     * 传递进来的map对象中封装了在登陆时填写的用户名与密码
     *
     * @param map      mapvalue  userName:登录用户名， password：登录密码，loginType 登錄類型
     * @param response
     */
    public static void saveCookie(Map<String, Object> map, HttpServletResponse response) {

        //cookie的有效期
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
        //MD5加密用户详细信息
        String cookieValueWithMd5 = getMD5(map.get("userName").toString() + ":" + map.get("password").toString() + ":" + map.get("loginType").toString() + ":" + validTime + ":" + CookieUtil.webKey);
        //将要被保存的完整的Cookie值
        String cookieValue = map.get("userName").toString() + ":" + map.get("loginType").toString() + ":" + validTime + ":" + cookieValueWithMd5;
        //再一次对Cookie的值进行BASE64编码
        String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
        //开始保存Cookie
        Cookie cookie = new Cookie(cookieDomainName, cookieValueBase64);

        //存一周(这个值应该大于或等于validTime)
        cookie.setMaxAge(60 * 60 * 24 * 7 * 2);
        //cookie有效路径是网站根目录
        cookie.setPath("/");
        //向客户端写入
        response.addCookie(cookie);

    }

    /**
     * 登录用户已存在cookie  修改coolie中的值
     *
     * @param autoCookie 已存在的登陆cookie
     * @param map        mapvalue  userName:登录用户名， password：登录密码，loginType 登錄類型
     * @param response
     */
    public static void updateCookie(Cookie autoCookie, Map<String, Object> map, HttpServletResponse response) {
        //cookie的有效期
        long validTime = System.currentTimeMillis() + (cookieMaxAge * 1000);
        //MD5加密用户详细信息
        String cookieValueWithMd5 = getMD5(map.get("userName").toString() + ":" + map.get("password").toString() + ":" + map.get("loginType").toString() + ":" + validTime + ":" + webKey);
        //将要被保存的完整的Cookie值
        String cookieValue = map.get("userName").toString() + ":" + map.get("loginType").toString() + ":" + validTime + ":" + cookieValueWithMd5;
        //再一次对Cookie的值进行BASE64编码
        String cookieValueBase64 = new String(Base64.encode(cookieValue.getBytes()));
        //开始保存Cookie
        autoCookie.setValue(cookieValueBase64);
        //存一周(这个值应该大于或等于validTime)
        autoCookie.setMaxAge(60 * 60 * 24 * 7 * 2);
        //cookie有效路径是网站根目录
        autoCookie.setPath("/");
        //向客户端写入
        response.addCookie(autoCookie);
    }


    /**
     * 用户注销时,清除Cookie
     *
     * @param response
     */
    public static void clearCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieDomainName, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static String getMD5(String value) {
        String result = null;
        try {
            byte[] valueByte = value.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(valueByte);
            result = toHex(md.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
        }
        return result;
    }

    //将传递进来的字节数组转换成十六进制的字符串形式并返回
    private static String toHex(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
}
