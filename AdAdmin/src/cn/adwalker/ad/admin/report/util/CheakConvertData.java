package cn.adwalker.ad.admin.report.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheakConvertData {

    /**
     * 基本模式匹配
     * 
     * @param origin
     * @param pat
     * @return
     */
    public static boolean is(String origin, String pat) {
        Pattern pattern = Pattern.compile(pat);
        Matcher isNum = pattern.matcher(origin);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是Integer数字
     * 
     * @param str
     * @return
     */
    public static boolean isIntegerString(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern.compile("[-]{0,1}[0-9]{1,}");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否为IP地址形式
     * 
     * @param str
     * @return
     */
    public static boolean isIp(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 正则表达式判断是否为POS格式
     * 
     * @param str
     * @return
     */
    public static boolean ispos(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        Pattern pattern = Pattern
                .compile("[0-9]{1}[a-zA-Z]{2}[0-9]{3}[a-z,A-Z]{1}");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * IP转换，将String类型转换成long型IP表示
     * 
     * @param ip
     * @return
     */
    public static long convertIP(String ip) {
        long result = 0;
        String[] temp = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            String current = temp[i];
            result <<= 8;
            result += convertStringToInt(current);
        }
        return result;
    }

    /**
     * 将Long类型转换IP
     * 
     * @param longip
     * @return
     */
    public static String covertLongToIP(long longip) {
        String ip = "";
        long temp = longip;
        for (int i = 0; i < 4; i++) {
            long x = temp & 0X0FF;
            temp >>= 8;
            ip = new Long(x).toString() + "." + ip;
        }
        if (ip.length() >= 1) {
            ip = ip.substring(0, ip.length() - 1);
        }
        return ip;
    }

    /**
     * 将字符串转成int
     * 
     * @param s
     * @return
     */
    public static int convertStringToInt(String s) {
        return Integer.parseInt(s);
    }

    /**
     * 将字符串转成long
     * 
     * @param s
     * @return
     */
    public static long convertStringToLong(String s) {
        return Long.parseLong(s);
    }

    /**
     * 将字符串转成Integer
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static Integer getIntegerFromStr(String str, Integer defaultValue) {
        Integer result = null;
        try {
            result = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            result = defaultValue;
        }

        return result;
    }

    /**
     * 将字符串转成Long
     * 
     * @param str
     * @param defaultValue
     * @return
     */
    public static Long getLongFromStr(String str, Long defaultValue) {
        Long result = null;
        try {
            result = Long.valueOf(str);
        } catch (NumberFormatException e) {
            result = defaultValue;
        }

        return result;
    }
}
