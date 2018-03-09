package org.nix.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 字符串工具类
 */
public class StringUtil {

	private static final Logger logger = LoggerFactory
			.getLogger(StringUtil.class);

	private StringUtil() {
		// 私用构造主法.因为此类是工具类.
	}

	public static void main(String[] args) {
		String initcap = initcap("sfdkjl");
		System.out.println(initcap);

		System.out.println(isNumeric("54.2"));
		System.out.println(isNumber("54"));

		System.out.println(parseChar("张"));

		System.out.println(moneyUppercase(23542.11));
		System.out.println(moneyUppercase(-2035421240));
		System.out.println(moneyUppercase(0.156));

		System.out.println(replace("abcankanjkaAsdfAsdfgbka", "a", "*", false));

		System.out.println("\"my_var\" is an identifier? "
				+ isJavaIdentifier("my_var"));
		System.out.println("\"my_var.1\" is an identifier? "
				+ isJavaIdentifier("my_var.1"));
		System.out.println("\"$my_var\" is an identifier? "
				+ isJavaIdentifier("$my_var"));
		System.out.println("\"\u0391var\" is an identifier? "
				+ isJavaIdentifier("\u0391var"));
		System.out.println("\"\1$my_var\" is an identifier? "
				+ isJavaIdentifier("\1$my_var"));
		
		System.out.println(substringByByte("abcdefg", 3));
		System.out.println(substringByByte("我的滑板鞋是什么", 3));
		
		System.out.println(getStringLen("我的滑板鞋是什么"));
		
		System.out.println(getLimitLengthString("我的滑板鞋是什么是什么是什么是什么是什么",10));
		System.out.println(getLimitLengthString2("我的滑板鞋是什么是什么是什么是什么是什么",10));
		System.out.println(getLimitLengthString("sfhnasdfjlksdgf",10));
		System.out.println(getLimitLengthString2("sfhnasdfjlksdgf",10));
		
		String str = "12345abcde";  
	    System.out.println("--------------------------------");  
	    System.out.println("正向截取长度为4，结果：\n" + StringUtil.subStr(str, 4));  
	    System.out.println("反向截取长度为4，结果：\n" + StringUtil.subStr(str, -4));  
	    System.out.println("--------------------------------");  
	    System.out.println("正向截取到第4个字符的位置，结果：\n" + StringUtil.subStrStart(str, 4));  
	    System.out.println("反向截取到第4个字符的位置，结果：\n" + StringUtil.subStrEnd(str, 4));  
	    System.out.println("--------------------------------");  
	    System.out.println("从第2个截取到第9个，结果：\n" + StringUtil.subStr(str, 1, 9));  
	    System.out.println("从第2个截取到倒数第1个，结果：\n" + StringUtil.subStr(str, 1, -1));  
	    System.out.println("从倒数第4个开始截取，结果：\n" + StringUtil.subStr(str, -4, 0));  
	    System.out.println("从倒数第4个开始截取，结果：\n" + StringUtil.subStr(str, -4, 10));  
	}

	
	/**
	 * 判断字符串是否是合法的Java标识符
	 * @param s	待判断的字符串
	 * @return
	 */
	public static boolean isJavaIdentifier(String s){
        //如果字符串为空或者长度为0，返回false
		if ((s == null) || (s.length() == 0)) {
            return false;
        }
        //字符串中每一个字符都必须是Java标识符的一部分
        for (int i=0; i<s.length(); i++) {
            if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                return false;
            }
        }
        return true;
	}
	
	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return new String(ch);
	}

	/**
	 * 是否是信件
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkLetter(String str) {
		if (str == null || str.length() < 0) {
			return false;
		}
		Pattern pattern = Pattern.compile("[\\w\\.-_]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 此方法判断输入字符是否为字母a-z或A-Z 是返回true不是返回false
	 * 
	 * @param c
	 *            char
	 * @return boolean
	 */
	public static boolean isAlpha(char c) {
		return ((('a' <= c) && (c <= 'z')) || (('A' <= c) && (c <= 'Z')));
	}

	/**
	 * 判断输入字符是否为字母a-z或A-Z 是返回true不是返回false
	 * 
	 * @author hezhao
	 * @Time 2017年7月31日 下午5:04:35
	 * @param inputStr
	 * @return
	 */
	public static boolean isAlpha(String inputStr) {
		char tempChar;
		for (int i = 0; i < inputStr.length(); i++) {
			tempChar = inputStr.charAt(i);
			if (!isAlpha(tempChar)) { // 如果字符中有一个字符不是字母则返回false
				return false;
			}
		}

		return true;
	}

	/**
	 * 此方法用于检查密码或用户名是否合法，用户名密码只能使用英文字母、数字以及-和_，并且首字符必须为字母或数字 密码首字符必须为字母或数字
	 * 
	 * @param inputStr
	 *            输入
	 * @return boolean
	 */
	public static boolean checkUserNamePassword(String inputStr) {
		for (int nIndex = 0; nIndex < inputStr.length(); nIndex++) {
			char cCheck = inputStr.charAt(nIndex);
			if (nIndex == 0 && (cCheck == '-' || cCheck == '_')) {
				return false;
			}
			if (!(isNumeric(cCheck) || isAlpha(cCheck) || cCheck == '-' || cCheck == '_')) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 此方法检查email有效性 返回提示信息
	 * 
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		// 电子邮件
		String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		boolean isMatched = matcher.matches();

		return isMatched;
	}

	/**
	 * 判断手机号码是否合法
	 * 
	 * @param handset
	 *            手机号
	 * @return 是否合法
	 */
	public static boolean checkMobile(String handset) {
		try {
			if (!handset.substring(0, 1).equals("1")) {
				return false;
			}
			if (handset == null || handset.length() != 11) {
				return false;
			}
			// String check = "^[0123456789]+$";
			String check = "^1[3-8]\\d{9}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(handset);

			return matcher.matches();
		} catch (RuntimeException e) {
			logger.error(e.toString(), e);
			return false;
		}
	}

	/**
	 * 判断大陆地区固话及小灵通 区号：010,020,021,022,023,024,025,027,028,029
	 * 
	 * @param tel
	 *            电话号码
	 * @return 是否合法
	 */
	public static boolean checkPhone(String tel) {
		try {
			String check = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(tel);

			return matcher.matches();
		} catch (RuntimeException e) {
			logger.error(e.toString(), e);
			return false;
		}
	}

	/**
	 * 验证是否包含中文
	 * 
	 * @param str
	 * @return 是否包含中文:含有中文-true，没有中文-false
	 */
	public static boolean containChinese(String str) {
		// String check = "^[\u4e00-\u9fa5]{1,}$";

		String check = "^[\\u4e00-\\u9fa5]+?";

		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(str);

		return matcher.find();
	}

	/**
	 * 验证是否包含空格
	 * 
	 * @param str
	 * @return 是否包含空格
	 */
	public static boolean containBlank(String str) {
		if (str.length() > str.replace(" ", "").length()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 验证用户名是否只含中英文和数字
	 * 
	 * @param userName
	 *            用户名
	 * @return 是否合法
	 */
	public static boolean checkUserName(String userName) {
		String check = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(userName);

		return matcher.matches();
	}

	/**
	 * 功能：判断字符串是否为日期格式
	 * 
	 * @param strDate
	 *            字符串
	 * @return
	 */
	public static boolean checkDate(String strDate) {
		Pattern pattern = Pattern
				.compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
		Matcher m = pattern.matcher(strDate);
		if (m.matches()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 此方法判断输入字符是否为数字0-9 是返回true不是返回false
	 * 
	 * @param c
	 *            char
	 * @return boolean
	 */
	public static boolean isNumeric(char c) {
		return (('0' <= c) && (c <= '9'));
	}

	/**
	 * 是否是数字0-9
	 * 
	 * @author hezhao
	 * @Time 2017年7月28日 下午9:43:45
	 * @param inputStr
	 * @return
	 */
	public static boolean isNumeric(String inputStr) {
		char tempChar;
		for (int i = 0; i < inputStr.length(); i++) {
			tempChar = inputStr.charAt(i);
			// 如果字符中有一个字符不是数字则返回false
			if (!isNumeric(tempChar)) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 校验数字,包括小数和负数
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNumber(String value) {
		Pattern pattern = Pattern.compile("^-?[0-9]*.?[0-9]*$");
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

	/**
	 * 是否是整数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否是小数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d+\\.\\d+$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 检验字符串是否未空, 如果是, 则返回给定的出错信息.
	 * 
	 * @param input
	 *            输入的字符串
	 * @param errorMsg
	 *            出错信息
	 * @return 空串返回出错信息
	 */
	public static String isBlank(String input, String errorMsg) {
		if (isBlank(input)) {
			return errorMsg;
		} else {
			return "";
		}
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 *            null、“ ”、“null”都返回true
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (null == str || isBlank(str.trim()) || "null".equals(str.trim()
				.toLowerCase())) ? true : false;
	}

	/**
	 * 格式化字符串 如果为空，返回“”
	 * 
	 * @param str
	 * @return
	 */
	public static String formatString(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return str;
		}
	}
	
	  /**
     * 判断两个字符串是否相等 如果都为null则判断为相等,一个为null另一个not null则判断不相等 否则如果s1=s2则相等
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean equals(String s1, String s2) {
        if (StringUtil.isEmpty(s1) && StringUtil.isEmpty(s2)) {
            return true;
        } else if (!StringUtil.isEmpty(s1) && !StringUtil.isEmpty(s2)) {
            return s1.equals(s2);
        }
        return false;
    }

	/**
	 * 是否是人名
	 * 
	 * @param x
	 * @return
	 */
	public static boolean isPrime(int x) {
		if (x <= 7) {
			if (x == 2 || x == 3 || x == 5 || x == 7)
				return true;
		}
		int c = 7;
		if (x % 2 == 0)
			return false;
		if (x % 3 == 0)
			return false;
		if (x % 5 == 0)
			return false;
		int end = (int) Math.sqrt(x);
		while (c <= end) {
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 4;
			if (x % c == 0) {
				return false;
			}
			c += 6;
			if (x % c == 0) {
				return false;
			}
			c += 2;
			if (x % c == 0) {
				return false;
			}
			c += 6;
		}
		return true;
	}

	/**
	 * 数字金额大写转换，思想先写个完整的然后将如零拾替换成零 要用到正则表达式
	 */
	public static String moneyUppercase(double n) {
		String fraction[] = { "角", "分" };
		String digit[] = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };
		String unit[][] = { { "元", "万", "亿" }, { "", "拾", "佰", "仟" } };

		String head = n < 0 ? "负" : "";
		n = Math.abs(n);

		String s = "";
		for (int i = 0; i < fraction.length; i++) {
			s += (digit[(int) (Math.floor(n * 10 * Math.pow(10, i)) % 10)] + fraction[i])
					.replaceAll("(零.)+", "");
		}
		if (s.length() < 1) {
			s = "整";
		}
		int integerPart = (int) Math.floor(n);

		for (int i = 0; i < unit[0].length && integerPart > 0; i++) {
			String p = "";
			for (int j = 0; j < unit[1].length && n > 0; j++) {
				p = digit[integerPart % 10] + unit[1][j] + p;
				integerPart = integerPart / 10;
			}
			s = p.replaceAll("(零.)*零$", "").replaceAll("^$", "零") + unit[0][i]
					+ s;
		}
		return head
				+ s.replaceAll("(零.)*零元", "元").replaceFirst("(零.)+", "")
						.replaceAll("(零.)+", "零").replaceAll("^整$", "零元整");
	}

	/**
	 * 删除重复字符
	 * 
	 * @param str
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String removeSameString(String str) {
		Set<String> mLinkedSet = new LinkedHashSet<String>();// set集合的特征：其子集不可以重复
		String[] strArray = str.split(" ");// 根据空格(正则表达式)分割字符串
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < strArray.length; i++) {
			if (!mLinkedSet.contains(strArray[i])) {
				mLinkedSet.add(strArray[i]);
				sb.append(strArray[i] + " ");
			}
		}
		System.out.println(mLinkedSet);
		return sb.toString();
	}

	/**
	 * 截取字符串，字母、汉字都可以，汉字不会截取半
	 * 
	 * @param str
	 *            字符串
	 * @param n
	 *            截取的长度，字母数，如果为汉字，一个汉字等于两个字母数
	 * @return
	 */
	public static String substringByByte(String str, int n) {
		int num = 0;
		try {
			byte[] buf = str.getBytes("GBK");
			if (n >= buf.length) {
				return str;
			}
			boolean bChineseFirstHalf = false;
			for (int i = 0; i < n; i++) {
				if (buf[i] < 0 && !bChineseFirstHalf) {
					bChineseFirstHalf = true;
				} else {
					num++;
					bChineseFirstHalf = false;
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.toString(), e);
		}
		return str.substring(0, num);
	}
	
	/** 
	 * 从头开始截取 
	 *  
	 * @param str 字符串 
	 * @param end 结束位置 
	 * @return 
	 */  
	public static String subStrStart(String str, int end){  
	    return subStr(str, 0, end);  
	}  
	  
	/** 
	 * 从尾开始截取 
	 *  
	 * @param str 字符串 
	 * @param start 开始位置 
	 * @return 
	 */  
	public static String subStrEnd(String str, int start){  
	    return subStr(str, str.length()-start, str.length());  
	}  
	  
	/** 
	 * 截取字符串 （支持正向、反向截取）<br/> 
	 *  
	 * @param str 待截取的字符串 
	 * @param length 长度 ，>=0时，从头开始向后截取length长度的字符串；<0时，从尾开始向前截取length长度的字符串 
	 * @return 返回截取的字符串 
	 * @throws RuntimeException 
	 */  
	public static String subStr(String str, int length) throws RuntimeException{  
	    if(str==null){  
	        throw new NullPointerException("字符串为null");  
	    }  
	    int len = str.length();  
	    if(len<Math.abs(length)){  
	        throw new StringIndexOutOfBoundsException("最大长度为"+len+"，索引超出范围为:"+(len-Math.abs(length)));  
	    }  
	    if(length>=0){  
	        return  subStr(str, 0,length);  
	    }else{  
	        return subStr(str, len-Math.abs(length), len);  
	    }  
	}  
	  
	  
	/** 
	 * 截取字符串 （支持正向、反向选择）<br/> 
	 *  
	 * @param str  待截取的字符串 
	 * @param start 起始索引 ，>=0时，从start开始截取；<0时，从length-|start|开始截取 
	 * @param end 结束索引 ，>=0时，从end结束截取；<0时，从length-|end|结束截取 
	 * @return 返回截取的字符串 
	 * @throws RuntimeException 
	 */  
	public static String subStr(String str, int start, int end) throws RuntimeException{  
	    if(str==null){  
	        throw new NullPointerException("");  
	    }  
	    int len = str.length();  
	    int s = 0;//记录起始索引  
	    int e = 0;//记录结尾索引  
	    if(len<Math.abs(start)){  
	        throw new StringIndexOutOfBoundsException("最大长度为"+len+"，索引超出范围为:"+(len-Math.abs(start)));  
	    }else if(start<0){  
	        s = len - Math.abs(start);  
	    }else if(start<0){  
	        s=0;  
	    }else{//>=0  
	        s = start;  
	    }  
	    if(len<Math.abs(end)){  
	        throw new StringIndexOutOfBoundsException("最大长度为"+len+"，索引超出范围为:"+(len-Math.abs(end)));  
	    }else if (end <0){  
	        e = len - Math.abs(end);  
	    }else if (end==0){  
	        e = len;  
	    }else{//>=0  
	        e = end;  
	    }  
	    if(e<s){  
	        throw new StringIndexOutOfBoundsException("截至索引小于起始索引:"+(e-s));  
	    }  
	  
	    return str.substring(s, e);  
	}  
	
	/**
	 * 截取指定长度的字符串,超出的字符用symbol代替 
	 * 
	 * @param str 字符串
	 * @param length 截取长度 以英文字符为单位，一个汉字算两个
	 * @param symbol 超出代替的字符
	 * @return
	 */
	public static String getLimitLengthString(String str, int length, String symbol) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		try {
			if (str.getBytes("GBK").length <= length) {
				return str;
			}
		} catch (Exception e) {
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= getStringLen(symbol);
		while (length > 0) {
			c = str.charAt(index);
			if (c < 128) {
				length--;
			} else {
				length--;
				length--;
			}
			buff.append(c);
			index++;
		}
		buff.append(symbol);
		return buff.toString();
	}
	
	/**
	 * 截取指定长度的字符串,超出的字符用...代替 
	 * 
	 * @param str 字符串
	 * @param length 截取长度 以英文字符为单位，一个汉字算两个
	 * @return
	 */
	public static String getLimitLengthString(String str, int length) {
		return getLimitLengthString(str, length,"...");
	}
	
	 /**
     * 截取字符串　超出的字符用symbol代替 　　
     *
     * @param length 字符串长度　中文和英文都是一个单位长度
     * @param str
     * @param symbol
     * @return
     */
	public static String getLimitLengthString2(String str, int length, String symbol) {
		if (str == null) {
			return null;
		}
		if (length <= 0) {
			return "";
		}
		StringBuffer buff = new StringBuffer();

		int index = 0;
		char c;
		length -= symbol.length();
		while (length > 0) {
			c = str.charAt(index);
			length--;
			buff.append(c);
			index++;
		}
		buff.append(symbol);
		return buff.toString();
    }
 
	 /**
     * 截取字符串　超出的字符用symbol代替 　　
     *
     * @param length 字符串长度　中文和英文都是一个单位长度
     * @param str
     * @return
     */
    public static String getLimitLengthString2(String str, int length) {
        return getLimitLengthString2(str, length, "...");
    }
	
	/**
     * 取得字符串的实际长度（考虑了汉字的情况）
     *
     * @param SrcStr
     *            源字符串
     * @return 字符串的实际长度
     */
    public static int getStringLen(String SrcStr) {
        int return_value = 0;
        if (SrcStr != null) {
            char[] theChars = SrcStr.toCharArray();
            for (int i = 0; i < theChars.length; i++) {
                return_value += (theChars[i] <= 255) ? 1 : 2;
            }
        }
        return return_value;
    }

	/**
	 * 按照 分隔符 将字符串 拆分成String数组
	 * 
	 * @param str
	 *            字符串
	 * @param splitsign
	 *            分隔符
	 * @return
	 */
	public static String[] split(String str, String splitsign) {
		int index;
		if (str == null || splitsign == null) {
			return null;
		}
		ArrayList<String> al = new ArrayList<String>();
		while ((index = str.indexOf(splitsign)) != -1) {
			al.add(str.substring(0, index));
			str = str.substring(index + splitsign.length());
		}
		al.add(str);
		return (String[]) al.toArray(new String[0]);
	}
	
	/**
     * 自定义的分隔字符串函数 例如: 1,2,3 =>[1,2,3] 3个元素 ,2,3=>[,2,3] 3个元素 ,2,3,=>[,2,3,]
     * 4个元素 ,,,=>[,,,] 4个元素
     *
     * 5.22算法修改，为提高速度不用正则表达式 两个间隔符,,返回""元素
     *
     * @param split
     *            分割字符 默认,
     * @param src
     *            输入字符串
     * @return 分隔后的list
     * @author Robin
     */
    public static List<String> splitToList(String split, String src) {
        // 默认,
        String sp = ",";
        if (split != null && split.length() == 1) {
            sp = split;
        }
        List<String> r = new ArrayList<String>();
        int lastIndex = -1;
        int index = src.indexOf(sp);
        if (-1 == index && src != null) {
            r.add(src);
            return r;
        }
        while (index >= 0) {
            if (index > lastIndex) {
                r.add(src.substring(lastIndex + 1, index));
            } else {
                r.add("");
            }
 
            lastIndex = index;
            index = src.indexOf(sp, index + 1);
            if (index == -1) {
                r.add(src.substring(lastIndex + 1, src.length()));
            }
        }
        return r;
    }
 
    /**
     * 把 名=值 参数表转换成字符串 (a=1,b=2 =>a=1&b=2)
     *
     * @param map
     * @return
     */
    public static String linkedHashMapToString(LinkedHashMap<String, String> map) {
        if (map != null && map.size() > 0) {
            String result = "";
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                String value = map.get(name);
                result += (result.equals("")) ? "" : "&";
                result += String.format("%s=%s", name, value);
            }
            return result;
        }
        return null;
    }
 
    /**
     * 解析字符串返回 名称=值的参数表 (a=1&b=2 => a=1,b=2)
     *
     * @param str
     * @return
     */
    public static LinkedHashMap<String, String> toLinkedHashMap(String str) {
        if (str != null && !str.equals("") && str.indexOf("=") > 0) {
            LinkedHashMap<String,String> result = new LinkedHashMap<String,String>();
 
            String name = null;
            String value = null;
            int i = 0;
            while (i < str.length()) {
                char c = str.charAt(i);
                switch (c) {
                case 61: // =
                    value = "";
                    break;
                case 38: // &
                    if (name != null && value != null && !name.equals("")) {
                        result.put(name, value);
                    }
                    name = null;
                    value = null;
                    break;
                default:
                    if (value != null) {
                        value = (value != null) ? (value + c) : "" + c;
                    } else {
                        name = (name != null) ? (name + c) : "" + c;
                    }
                }
                i++;
 
            }
 
            if (name != null && value != null && !name.equals("")) {
                result.put(name, value);
            }
 
            return result;
 
        }
        return null;
    }
 
    /**
     * 根据输入的多个解释和下标返回一个值
     *
     * @param captions
     *            例如:"无,爱干净,一般,比较乱"
     * @param index
     *            1
     * @return 一般
     */
    public static String getCaption(String captions, int index) {
        if (index > 0 && captions != null && !captions.equals("")) {
            String[] ss = captions.split(",");
            if (ss != null && ss.length > 0 && index < ss.length) {
                return ss[index];
            }
        }
        return null;
    }
 
    /**
     * 数字转字符串,如果num<=0 则输出"";
     *
     * @param num
     * @return
     */
    public static String numberToString(Object num) {
        if (num == null) {
            return null;
        } else if (num instanceof Integer && (Integer) num > 0) {
            return Integer.toString((Integer) num);
        } else if (num instanceof Long && (Long) num > 0) {
            return Long.toString((Long) num);
        } else if (num instanceof Float && (Float) num > 0) {
            return Float.toString((Float) num);
        } else if (num instanceof Double && (Double) num > 0) {
            return Double.toString((Double) num);
        } else {
            return "";
        }
    }
 
    /**
     * 货币转字符串
     *
     * @param money
     * @param style
     *            样式 [default]要格式化成的格式 such as #.00, #.#
     * @return
     */
 
    public static String moneyToString(Object money, String style) {
        if (money != null && style != null
                && (money instanceof Double || money instanceof Float)) {
            Double num = (Double) money;
 
            if (style.equalsIgnoreCase("default")) {
                // 缺省样式 0 不输出 ,如果没有输出小数位则不输出.0
                if (num == 0) {
                    // 不输出0
                    return "";
                } else if ((num * 10 % 10) == 0) {
                    // 没有小数
                    return Integer.toString((int) num.intValue());
                } else {
                    // 有小数
                    return num.toString();
                }
 
            } else {
                DecimalFormat df = new DecimalFormat(style);
                return df.format(num);
            }
        }
        return null;
    }
 

	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
	 * 
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 * @param matchCase
	 *            是否需要按照大小写敏感方式查找
	 */
	public static String replace(String source, String oldStr, String newStr,
			boolean matchCase) {
		if (source == null) {
			return null;
		}
		// 首先检查旧字符串是否存在, 不存在就不进行替换
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
			return source;
		}
		int findStartPos = 0;
		int a = 0;
		while (a > -1) {
			int b = 0;
			String str1, str2, str3, str4, strA, strB;
			str1 = source;
			str2 = str1.toLowerCase();
			str3 = oldStr;
			str4 = str3.toLowerCase();
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = bbuf.replace(a, a + b, newStr) + "";
				// 新的查找开始点位于替换后的字符串的结尾
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}

	/**
	 * 清除字符串结尾的空格.
	 * 
	 * @param input
	 *            String 输入的字符串
	 * @return 转换结果
	 */
	public static String trimTailSpaces(String input) {
		if (isBlank(input)) {
			return "";
		}

		String trimedString = input.trim();

		if (trimedString.length() == input.length()) {
			return input;
		}

		return input.substring(0,
				input.indexOf(trimedString) + trimedString.length());
	}

	/**
	 * 验证字符串
	 * 
	 * @param content
	 * @return
	 */
	public static String parse(String content) {
		String email = null;
		if (content == null || content.length() < 1) {
			return email;
		}
		// 找出含有@
		int beginPos;
		int i;
		String token = "@";
		String preHalf = "";
		String sufHalf = "";

		beginPos = content.indexOf(token);
		if (beginPos > -1) {
			// 前项扫描
			String s = null;
			i = beginPos;
			while (i > 0) {
				s = content.substring(i - 1, i);
				if (checkLetter(s))
					preHalf = s + preHalf;
				else
					break;
				i--;
			}
			// 后项扫描
			i = beginPos + 1;
			while (i < content.length()) {
				s = content.substring(i, i + 1);
				if (checkLetter(s))
					sufHalf = sufHalf + s;
				else
					break;
				i++;
			}
			// 判断合法性
			email = preHalf + "@" + sufHalf;
			if (checkEmail(email)) {
				return email;
			}
		}
		return null;
	}

	/**
	 * 编码
	 * 
	 * @param src
	 * @return
	 */
	public static String encoding(String src) {
		if (src == null)
			return "";
		StringBuilder result = new StringBuilder();
		if (src != null) {
			src = src.trim();
			for (int pos = 0; pos < src.length(); pos++) {
				switch (src.charAt(pos)) {
				case '"':
					result.append("'");
					break;
				case '<':
					result.append("<");
					break;
				case '>':
					result.append(">");
					break;
				case '\'':
					result.append("'");
					break;
				case '&':
					result.append("&");
					break;
				case '%':
					result.append("&pc;");
					break;
				case '_':
					result.append("&ul;");
					break;
				case '#':
					result.append("&shap;");
					break;
				case '?':
					result.append("&ques;");
					break;
				default:
					result.append(src.charAt(pos));
					break;
				}
			}
		}
		return result.toString();
	}

	/**
	 * 解码
	 * 
	 * @param src
	 * @return
	 */
	public static String decoding(String src) {
		if (src == null)
			return "";
		String result = src;
		result = result.replace("'", "'").replace("'", "\'");
		result = result.replace("<", "<").replace(">", ">");
		result = result.replace("&", "&");
		result = result.replace("&pc;", "%").replace("&ul", "_");
		result = result.replace("&shap;", "#").replace("&ques", "?");
		return result;
	}

	/**
	 * 对给定字符进行 URL 编码
	 */
	public static String encode(String value) {
		if (isBlank(value)) {
			return "";
		}

		try {
			value = java.net.URLEncoder.encode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * 对给定字符进行 URL 解码
	 * 
	 * @param value
	 *            解码前的字符串
	 * @return 解码后的字符串
	 */
	public static String decode(String value) {
		if (isBlank(value)) {
			return "";
		}

		try {
			return java.net.URLDecoder.decode(value, "GB2312");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return value;
	}

	/**
	 * 将字符串转换为 int.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static int parseInt(String input) {
		if(isEmpty(input)){
			return 0;
		}
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0;
	}

	/**
	 * 将字符串转换为 double.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static double parseDouble(String input) {
		if(isEmpty(input)){
			return 0.0D;
		}
		try {
			return Double.parseDouble(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0.0D;
	}

	/**
	 * 将字符串转换为 long.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static long parseLong(String input) {
		if(isEmpty(input)){
			return 0L;
		}
		try {
			return Long.parseLong(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0L;
	}

	/**
	 * 将字符串转换为 short.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static short parseShort(String input) {
		if(isEmpty(input)){
			return 0;
		}
		try {
			return Short.parseShort(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0;
	}

	/**
	 * 将字符串转换为 float.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static float parseFloat(String input) {
		if(isEmpty(input)){
			return 0.0F;
		}
		try {
			return Float.parseFloat(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0.0F;
	}

	/**
	 * 将字符串转换为 byte.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果数字
	 */
	public static byte parseByte(String input) {
		if(isEmpty(input)){
			return 0;
		}
		try {
			return Byte.parseByte(input);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return 0;
	}

	/**
	 * 将字符串转换为 char.
	 * 
	 * @param input
	 *            输入的字串
	 * @date 2005-07-29
	 * @return 结果字符
	 */
	public static char parseChar(String input) {
		if(isEmpty(input)){
			return '\u0000';
		}
		try {
			return input.toCharArray()[0];
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return '\u0000';
	}

	/**
	 * 格式化日期到日时分秒时间格式的显示. d日 HH:mm:ss
	 * 
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToDHMSString(Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"d日 HH:mm:ss");

		return dateformat.format(date);

	}

	/**
	 * 格式化日期到时分秒时间格式的显示.
	 * 
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToHMSString(Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"HH:mm:ss");

		return dateformat.format(date);

	}

	/**
	 * 将时分秒时间格式的字符串转换为日期.
	 * 
	 * @param input
	 * @return
	 */
	public static Date parseHMSStringToDate(String input) {
		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"HH:mm:ss");

		try {
			return dateformat.parse(input);
		} catch (ParseException e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	/**
	 * 格式化日期到 Mysql 数据库日期格式字符串的显示.
	 * 
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToMysqlString(Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		return dateformat.format(date);

	}

	/**
	 * 将 Mysql 数据库日期格式字符串转换为日期.
	 * 
	 * @param input
	 * @return
	 */
	public static Date parseStringToMysqlDate(String input) {
		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			return dateformat.parse(input);
		} catch (ParseException e) {
			logger.error(e.toString(), e);
		}

		return null;
	}

	/**
	 * 返回时间字符串, 可读形式的, M月d日 HH:mm 格式. 2004-09-22, LiuChangjiong
	 * 
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToMMddHHmm(Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"M月d日 HH:mm");

		return dateformat.format(date);
	}

	/**
	 * 返回时间字符串, 可读形式的, yy年M月d日HH:mm 格式. 2004-10-04, LiuChangjiong
	 * 
	 * @return - String 格式化后的时间
	 */
	public static String formatDateToyyMMddHHmm(Date date) {
		if (date == null) {
			return "";
		}

		java.text.SimpleDateFormat dateformat = new java.text.SimpleDateFormat(
				"yy年M月d日HH:mm");

		return dateformat.format(date);
	}

	/**
	 * 生成一个 18 位的 yyyyMMddHHmmss.SSS 格式的日期字符串.
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String genTimeStampString(Date date) {
		java.text.SimpleDateFormat df = new java.text.SimpleDateFormat(
				"yyyyMMddHHmmss.SSS");
		return df.format(date);
	}

	/**
	 * Change the null string value to "", if not null, then return it self, use
	 * this to avoid display a null string to "null".
	 * 
	 * @param input
	 *            the string to clear
	 * @return the result
	 */
	public static String clearNull(String input) {
		return isBlank(input) ? "" : input;
	}

	/**
	 * Return the limited length string of the input string (added at:April 10,
	 * 2004).
	 * 
	 * @param input
	 *            String
	 * @param maxLength
	 *            int
	 * @return String processed result
	 */
	public static String limitStringLength(String input, int maxLength) {
		if (isBlank(input))
			return "";

		if (input.length() <= maxLength) {
			return input;
		} else {
			return input.substring(0, maxLength - 3) + "...";
		}

	}

	/**
	 * 
	 * HTML编码格式
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlencode(String str) {
		if (str == null) {
			return null;
		}
		return replace(replace(str, "<", "<", true), "'", "'", true);
	}

	/**
	 * HTML解码格式
	 * 
	 * @param str
	 * @return
	 */
	public static String htmldecode(String str) {
		if (str == null) {
			return null;
		}

		return replace(replace(str, "<", "<", false), "'", "'", false);
	}

	/**
	 * 转HTML格式
	 * 
	 * @param str
	 * @return
	 */
	public static String htmlshow(String str) {
		final String _BR = "";

		if (str == null) {
			return null;
		}

		str = replace(str, "<", "<", false);
		str = replace(str, " ", " ", false);
		str = replace(str, "\r\n", _BR, false);
		str = replace(str, "\n", _BR, false);
		str = replace(str, "\t", "    ", false);
		return str;
	}

	/**
	 * 将字符串转换为一个 javascript 的 alert 调用. eg: htmlAlert("What?"); returns <SCRIPT
	 * language="javascript">alert("What?")</SCRIPT>
	 * 
	 * @param message
	 *            需要显示的信息
	 * @return 转换结果
	 */
	public static String scriptAlert(String message) {
		return "<SCRIPT language=\"javascript\">alert(\"" + message
				+ "\");</SCRIPT>";
	}

	/**
	 * 将字符串转换为一个 javascript 的 document.location 改变调用. eg: htmlAlert("a.jsp");
	 * returns <SCRIPT language="javascript">document.location="a.jsp";</SCRIPT>
	 * 
	 * @param url
	 *            需要显示的 URL 字符串
	 * @return 转换结果
	 */
	public static String scriptRedirect(String url) {
		return "<SCRIPT language=\"javascript\">document.location=\"" + url
				+ "\";</SCRIPT>";
	}

	/**
	 * 返回脚本语句 <SCRIPT language="javascript">history.back();</SCRIPT>
	 * 
	 * @return 脚本语句
	 */
	public static String scriptHistoryBack() {
		return "<SCRIPT language=\"javascript\">history.back();</SCRIPT>";
	}

	/**
	 * 滤除帖子中的危险 HTML 代码, 主要是脚本代码, 滚动字幕代码以及脚本事件处理代码
	 * 
	 * @param content
	 *            需要滤除的字符串
	 * @return 过滤的结果
	 */
	public static String replaceHtmlCode(String content) {
		if (isBlank(content)) {
			return "";
		}
		// 需要滤除的脚本事件关键字
		String[] eventKeywords = { "onmouseover", "onmouseout", "onmousedown",
				"onmouseup", "onmousemove", "onclick", "ondblclick",
				"onkeypress", "onkeydown", "onkeyup", "ondragstart",
				"onerrorupdate", "onhelp", "onreadystatechange", "onrowenter",
				"onrowexit", "onselectstart", "onload", "onunload",
				"onbeforeunload", "onblur", "onerror", "onfocus", "onresize",
				"onscroll", "oncontextmenu" };
		content = replace(content, "<script", "&ltscript", false);
		content = replace(content, "</script", "&lt/script", false);
		content = replace(content, "<marquee", "&ltmarquee", false);
		content = replace(content, "</marquee", "&lt/marquee", false);
		content = replace(content, "/r/n", "<BR>", false);
		// 滤除脚本事件代码
		for (int i = 0; i < eventKeywords.length; i++) {
			content = replace(content, eventKeywords[i],
					"_" + eventKeywords[i], false); // 添加一个"_", 使事件代码无效
		}
		return content;
	}

	/**
	 * 滤除 HTML 代码 为文本代码.
	 */
	public static String replaceHtmlToText(String input) {
		if (isBlank(input)) {
			return "";
		}
		return setBr(setTag(input));
	}

	/**
	 * 滤除 HTML 标记. 因为 XML 中转义字符依然有效, 因此把特殊字符过滤成中文的全角字符.
	 * 
	 * @author beansoft
	 * @param s
	 *            输入的字串
	 * @return 过滤后的字串
	 */
	public static String setTag(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		char ch;
		for (int i = 0; i < j; i++) {
			ch = s.charAt(i);
			if (ch == '<') {
				// stringbuffer.append("<");
				stringbuffer.append("〈");
			} else if (ch == '>') {
				// stringbuffer.append(">");
				stringbuffer.append("〉");
			} else if (ch == '&') {
				// stringbuffer.append("&");
				stringbuffer.append("〃");
			} else if (ch == '%') {
				// stringbuffer.append("%%");
				stringbuffer.append("※");
			} else {
				stringbuffer.append(ch);
			}
		}

		return stringbuffer.toString();
	}

	/** 滤除 BR 代码 */
	public static String setBr(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++) {

			if (s.charAt(i) == '/' + 'n' || s.charAt(i) == '/' + 'r') {
				continue;
			} else {
				stringbuffer.append(s.charAt(i));
			}
		}

		return stringbuffer.toString();
	}

	/** 滤除空格 */
	public static String setNbsp(String s) {
		int j = s.length();
		StringBuffer stringbuffer = new StringBuffer(j + 500);
		for (int i = 0; i < j; i++) {
			if (s.charAt(i) == ' ') {
				stringbuffer.append(" ");
			} else {
				stringbuffer.append(s.charAt(i) + "");
			}
		}
		return stringbuffer.toString();
	}

	/**
	 * 转换由表单读取的数据的内码(从 ISO8859 转换到 gb2312).
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String toChi(String input) {
		try {
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes, "GBK");
		} catch (Exception ex) {
		}
		return input;
	}

	/**
	 * 转换由表单读取的数据的内码到 ISO(从 GBK 转换到ISO8859-1).
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String toISO(String input) {
		return changeEncoding(input, "GBK", "ISO8859-1");
	}

	/**
	 * 转换字符串的内码.
	 * 
	 * @param input
	 *            输入的字符串
	 * @param sourceEncoding
	 *            源字符集名称
	 * @param targetEncoding
	 *            目标字符集名称
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 */
	public static String changeEncoding(String input, String sourceEncoding,
			String targetEncoding) {
		if (input == null || input.equals("")) {
			return input;
		}

		try {
			byte[] bytes = input.getBytes(sourceEncoding);
			return new String(bytes, targetEncoding);
		} catch (Exception ex) {
		}
		return input;
	}

	/**
	 * 将单个的 ' 换成 ''; SQL 规则:如果单引号中的字符串包含一个嵌入的引号,可以使用两个单引号表示嵌入的单引号.
	 */

	public static String replaceSql(String input) {
		return replace(input, "'", "''", false);
	}

	/**
	 * 获得输入字符串的字节长度(即二进制字节数), 用于发送短信时判断是否超出长度.
	 * 
	 * @param input
	 *            输入字符串
	 * @return 字符串的字节长度(不是 Unicode 长度)
	 */
	public static int getBytesLength(String input) {
		if (input == null || input == "") {
			return 0;
		}

		int bytesLength = input.getBytes().length;

		// System.out.println("bytes length is:" + bytesLength);

		return bytesLength;
	}

	/**
	 * Gets the absolute pathname of the class or resource file containing the
	 * specified class or resource name, as prescribed by the current classpath.
	 * 
	 * @param resourceName
	 *            Name of the class or resource name.
	 * @return the absolute pathname of the given resource
	 */
	public static String getPath(String resourceName) {

		if (!resourceName.startsWith("/")) {
			resourceName = "/" + resourceName;
		}

		// resourceName = resourceName.replace('.', '/');

		java.net.URL classUrl = new StringUtil().getClass().getResource(
				resourceName);

		if (classUrl != null) {
			// System.out.println("/nClass '" + className +
			// "' found in /n'" + classUrl.getFile() + "'");
			// System.out.println("/n资源 '" + resourceName +
			// "' 在文件 /n'" + classUrl.getFile() + "' 中找到.");

			return classUrl.getFile();
		} else {
			// System.out.println("/nClass '" + className +
			// "' not found in /n'" +
			// System.getProperty("java.class.path") + "'");
			// System.out.println("/n资源 '" + resourceName +
			// "' 没有在类路径 /n'" +
			// System.getProperty("java.class.path") + "' 中找到");
			return null;
		}
	}

	/**
	 * 将 TEXT 文本转换为 HTML 代码, 已便于网页正确的显示出来.
	 * 
	 * @param input
	 *            输入的文本字符串
	 * @return 转换后的 HTML 代码
	 */
	public static String textToHtml(String input) {
		if (isBlank(input)) {
			return "";
		}

		input = replace(input, "<", "<", true);
		input = replace(input, ">", ">", true);

		input = replace(input, "/n", "<br>/n", true);
		input = replace(input, "/t", "    ", true);
		input = replace(input, "  ", "  ", true);

		return input;
	}
}