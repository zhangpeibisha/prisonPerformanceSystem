package org.nix.utils.datetime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.nix.utils.StringUtil;
import org.nix.utils.datetime.DateEnums.*;

/**
 * 日期、时间处理工具类
 *
 * @author user
 */
public class DateUtil {

    public static void main(String[] args) throws InterruptedException {



        Date now = new Date();

        long nowTime = now.getTime();
        Thread.sleep(1000);
        long newTime = new Date().getTime();



        //获取年月的值
        System.out.println(nowTime/1000/60/60/24/30.0);
        System.out.println(newTime/1000/60/60/24/30.0);
        //获取日
        System.out.println(nowTime/1000/60/60/24);
        System.out.println(newTime/1000/60/60/24);
        //获取小时
        System.out.println(nowTime/1000/60/60);
        System.out.println(newTime/1000/60/60);



        System.out.println(format(now, "yyyyMMdd"));
        System.out.println(format(now, "yyyy-MM-dd"));
        System.out.println(format(now, "yyyyMMddHHmmss"));
        System.out.println(format(now, "yyyy-MM-dd HH:mm:ss"));
        System.out.println(format(now, "yyyy/MM/dd E HH:mm:ss:S"));
        System.out.println(format(now, "yyMMddHHmmss"));

        String format = format(now, "yyyy-MM-dd HH:mm:ss");
        System.out.println(parse(format, "yyyy-MM-dd HH:mm:ss"));

        System.out.println(getCurrentTimeMillis());
        System.out.println(getNanoTime());

        System.out.println(formatDateTime(getToday()));
        System.out.println(formatDateTime(getNow()));

        System.out.println(format(parse(formatDateTime(getNow())),
                "yyyy-MM-dd HH:mm:ss"));

        System.out.println(getDateStyle("06:45").getValue());
        System.out.println(addYear("2017", 5));

        System.out.println(daysBetween(getNow(), parse("2017-06-22")));
        System.out.println(daysBetween("2017-01-12", "2017-08-22"));

        Date addDay = addDay(getNow(), -39);
        System.out.println(formatDate(addDay));

        System.out.println(check("2017-12-12"));
        System.out.println(check("12-12"));

        System.out.println("First day of week is : "
                + formatDateTime(getFirstDateByWeek(new Date())));
        System.out.println("Last day of week is : "
                + formatDateTime(getLastDateByWeek(new Date())));
        System.out.println("First day of month is : "
                + formatDateTime(getFirstDateByMonth(new Date())));
        System.out.println("First day of month is : " + getFirstDateByMonth());
        System.out.println("First day of month is : "
                + getFirstDateByMonth(2017, 8));
        System.out.println("Last day of month is : "
                + formatDateTime(getLastDateByMonth(new Date())));
        System.out.println("Last day of month is : " + getLastDateByMonth());
        System.out.println("Last day of month is : "
                + getLastDateByMonth(2017, 8));
        System.out.println("First day of year is : "
                + formatDateTime(getFirstDateByYear()));
        System.out.println("First day of year is : "
                + formatDateTime(getFirstDateByYear(2017)));
        System.out.println("First day of year is : "
                + formatDateTime(getFirstDateByYear(getNow())));
        System.out.println("Last day of year is : "
                + formatDateTime(getLastDateByYear(getNow())));
        System.out.println("Last day of year is : "
                + formatDateTime(getLastDateByYear()));
        System.out.println("Last day of year is : "
                + formatDateTime(getLastDateByYear(2017)));

    }

    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * 缺省的日期格式 yyyy-MM-dd
     */
    private static final String DAFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 默认日期类型格试.
     */
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            DAFAULT_DATE_FORMAT);

    /**
     * 缺省的日期时间格式 yyyy-MM-dd HH:mm:ss
     */
    private static final String DAFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            DAFAULT_DATETIME_FORMAT);

    /**
     * 缺省的时间格式 HH:mm:ss
     */
    private static final String DAFAULT_TIME_FORMAT = "HH:mm:ss";

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            DAFAULT_TIME_FORMAT);

    private DateUtil() {
        // 私用构造主法.因为此类是工具类.
    }

    /**
     * 获得指定时间的年
     *
     * @param date
     * @return 一个有字符串组成的年月 列如201803
     */
    public static int getDateYear(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        return year;
    }

    public static int getDateMonth(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int month = now.get(Calendar.MONTH) + 1; // 0-based!
        return month;
    }

    public static int getDateDay(Date date){
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int day = now.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取某个时间点前N天的时间点。
     *
     * @param currentDate
     * @param days
     * @return
     */
    public static Date getDateBefore(Date currentDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, -days);

        return cal.getTime();
    }

    /**
     * 获取某个时间点后N天的时间点。
     *
     * @param currentDate
     * @param days
     * @return
     */
    public static Date getDateAfter(Date currentDate, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_YEAR, days);

        return cal.getTime();
    }

    /**
     * 根据生日计算年龄。
     *
     * @param birthday
     * @return int
     */
    public static int countAge(Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            return 0;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * 检查字符串是否是日期
     *
     * @param dateString 字符串
     * @return 任意格式[DateStyle] 只要有一个转换成功就返回true，否则返回false
     */
    public static boolean check(String dateString) {
        for (DateStyle style : DateStyle.values()) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat(style.getValue());
            try {
                date = (Date) sdf.parse(dateString);
                if (date != null && dateString.equals(sdf.format(date))) {
                    return true;
                }
            } catch (ParseException e) {

            }
        }
        return false;
    }

    /**
     * 计算日期差
     *
     * @param now        日期一
     * @param returnDate 日期二
     * @return 日期差[绝对值]
     * @author hezhao
     * @Time 2016年8月26日 上午9:48:50
     */
    public static int daysBetween(Date now, Date returnDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(now);
        cReturnDate.setTime(returnDate);
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = returnMs - todayMs;
        return Math.abs(millisecondsToDays(intervalMs));
    }

    /**
     * 计算日期差
     *
     * @param now        日期一
     * @param returnDate 日期二
     * @return 日期差[绝对值]
     * @author hezhao
     * @Time 2016年8月26日 上午9:48:50
     */
    public static int daysBetween(String now, String returnDate) {
        Calendar cNow = Calendar.getInstance();
        Calendar cReturnDate = Calendar.getInstance();
        cNow.setTime(parse(now));
        cReturnDate.setTime(parse(returnDate));
        setTimeToMidnight(cNow);
        setTimeToMidnight(cReturnDate);
        long todayMs = cNow.getTimeInMillis();
        long returnMs = cReturnDate.getTimeInMillis();
        long intervalMs = returnMs - todayMs;
        return Math.abs(millisecondsToDays(intervalMs));
    }

    private static int millisecondsToDays(long intervalMs) {
        return (int) (intervalMs / (1000 * 86400));
    }

    private static void setTimeToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }

    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String timeStampToDateTime(long timeStamp) {
        String date = datetimeFormat.format(timeStamp * 1000);
        return date;
    }

    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp 时间戳
     * @return yyyy-MM-dd
     */
    public static String timeStampToDate(long timeStamp) {
        String date = dateFormat.format(timeStamp * 1000);
        return date;
    }

    /**
     * 时间戳转化为时间格式
     *
     * @param timeStamp 时间戳
     * @return HH:mm:ss
     */
    public static String timeStampToTime(long timeStamp) {
        String time = null;
        String date = timeFormat.format(timeStamp * 1000);
        String[] split = date.split("\\s");
        if (split.length > 1) {
            time = split[1];
        }
        return time;
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，如刚刚，1秒前
     *
     * @param timeStamp
     * @return
     */
    public static String convertTimeToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;

        if (time < 60 && time >= 0) {
            return "刚刚";
        } else if (time >= 60 && time < 3600) {
            return time / 60 + "分钟前";
        } else if (time >= 3600 && time < 3600 * 24) {
            return time / 3600 + "小时前";
        } else if (time >= 3600 * 24 && time < 3600 * 24 * 30) {
            return time / 3600 / 24 + "天前";
        } else if (time >= 3600 * 24 * 30 && time < 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 + "个月前";
        } else if (time >= 3600 * 24 * 30 * 12) {
            return time / 3600 / 24 / 30 / 12 + "年前";
        } else {
            return "刚刚";
        }
    }

    /**
     * 将一个时间戳转换成提示性时间字符串，(多少分钟)
     *
     * @param timeStamp
     * @return
     */
    public static String timeStampToFormat(long timeStamp) {
        long curTime = System.currentTimeMillis() / (long) 1000;
        long time = curTime - timeStamp;
        return time / 60 + "";
    }

    /**
     * 返回当前日期的前一个时间日期，amount为正数 当前时间后的时间 为负数 当前时间前的时间 默认日期格式yyyy-MM-dd
     *
     * @param field  日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
     * @param amount 数量
     * @return 一个日期
     */
    public String getPreDate(String field, int amount) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Date());
        if (field != null && !field.equals("")) {
            if (field.equals("y")) {
                calendar.add(calendar.YEAR, amount);
            } else if (field.equals("M")) {
                calendar.add(calendar.MONTH, amount);
            } else if (field.equals("d")) {
                calendar.add(calendar.DAY_OF_MONTH, amount);
            } else if (field.equals("H")) {
                calendar.add(calendar.HOUR, amount);
            }
        } else {
            return null;
        }
        return formatDate(calendar.getTime());
    }

    /**
     * 某一个日期的前一个日期
     * <p>
     * ,某一个日期
     *
     * @param field  日历字段 y 年 M 月 d 日 H 时 m 分 s 秒
     * @param amount 数量
     * @return 一个日期
     */
    public String getPreDate(Date date, String field, int amount) {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        if (field != null && !field.equals("")) {
            if (field.equals("y")) {
                calendar.add(calendar.YEAR, amount);
            } else if (field.equals("M")) {
                calendar.add(calendar.MONTH, amount);
            } else if (field.equals("d")) {
                calendar.add(calendar.DAY_OF_MONTH, amount);
            } else if (field.equals("H")) {
                calendar.add(calendar.HOUR, amount);
            }
        } else {
            return null;
        }
        return formatDate(calendar.getTime());
    }

    /**
     * 某一个时间的前一天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public String getPreDate(String date) throws ParseException {
        Date d = parse(date);
        String preD = getPreDate(d, "d", 1);
        Date preDate = new SimpleDateFormat().parse(preD);
        return dateFormat.format(preDate);
    }

    /**
     * 格式化Calendar
     *
     * @param calendar
     * @return
     */
    public static String formatCalendar(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return dateFormat.format(calendar.getTime());
    }

    /**
     * 格式化日期时间，默认日期格式yyyy-MM-dd HH:mm:ss
     *
     * @return
     * @author hezhao
     * @Time 2017年7月27日 下午7:12:17
     * @Description 无
     * @Version V 1.0
     */
    public static String formatDateTime(Date datetime) {
        if (datetime == null) {
            return "";
        }
        return datetimeFormat.format(datetime);
    }

    /**
     * 日期格式化，默认日期格式yyyy-MM-dd
     *
     * @param date
     * @return
     * @author hezhao
     * @Time 2017年7月27日 下午7:08:49
     * @Description 无
     * @Version V 1.0
     */
    public static String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        return dateFormat.format(date);
    }

    /**
     * 格式化时间，默认日期格式HH:mm:ss
     *
     * @return
     */
    public static String formatTime(Date time) {
        if (time == null) {
            return "";
        }
        return timeFormat.format(time);
    }

    /**
     * 格式化整数型日期
     *
     * @param intDate
     * @return
     */
    public static String formatIntDate(Integer intDate) {
        if (intDate == null) {
            return "";
        }
        Calendar c = newCalendar(intDate);
        return formatCalendar(c);
    }

    /**
     * 根据指定格式化来格式日期.
     *
     * @param date  待格式化的日期.
     * @param style 日期风格
     * @return 字符串型日期.
     */
    public static String format(Date date, DateStyle style) {
        return format(date, style.getValue());
    }

    /**
     * 根据指定格式化来格式日期.
     *
     * @param date    待格式化的日期.
     * @param pattern 格式化样式或分格,如yyMMddHHmmss
     * @return 字符串型日期.
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (StringUtil.isBlank(pattern)) {
            return formatDate(date);
        }
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat(pattern);
        } catch (Exception e) {
            logger.error(e.toString(), e);
            return formatDate(date);
        }
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date     旧日期字符串
     * @param parttern 新日期格式
     * @return 新日期字符串
     */
    public static String changeFormat(String date, String parttern) {
        return changeFormat(date, null, parttern);
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddParttern 旧日期格式
     * @param newParttern  新日期格式
     * @return 新日期字符串
     */
    public static String changeFormat(String date, String olddParttern,
                                      String newParttern) {
        String dateString = null;
        Date myDate = parse(date, olddParttern);
        dateString = format(myDate, newParttern);
        return dateString;
    }

    /**
     * 将日期字符串转化为另一日期字符串。失败返回null。
     *
     * @param date         旧日期字符串
     * @param olddDteStyle 旧日期风格
     * @param newDateStyle 新日期风格
     * @return 新日期字符串
     */
    public static String changeFormat(String date, DateStyle olddDteStyle,
                                      DateStyle newDateStyle) {
        String dateString = null;
        dateString = changeFormat(date, olddDteStyle.getValue(),
                newDateStyle.getValue());
        return dateString;
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateString 字符串
     * @return yyyyMMdd
     */
    public static Date parseDateChar(String dateString) {
        return parse(dateString, "yyyyMMdd");
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateString 字符串
     * @return yyyy-MM-dd
     */
    public static Date parseDate(String dateString) {
        return parse(dateString, "yyyy-MM-dd");
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateString 字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date parseDateTime(String dateString) {
        return parse(dateString, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateString 字符串
     * @return 任意格式[DateStyle] 只要有一个转换成功就返回转换结果，否则返回null
     */
    public static Date parse(String dateString) {
        for (DateStyle style : DateStyle.values()) {
            Date date = null;
            SimpleDateFormat sdf = new SimpleDateFormat(style.getValue());
            try {
                date = (Date) sdf.parse(dateString);
                if (date != null && dateString.equals(sdf.format(date))) {
                    return new Date(date.getTime());
                }
            } catch (ParseException e) {

            }
        }
        return null;
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateString 字符串
     * @param pattern    格式
     * @return
     */
    public static Date parse(String dateString, String pattern) {
        if ("".equals(dateString))
            return null;

        Date date = null;
        SimpleDateFormat sdf = getFormatInstance(pattern);
        try {
            date = (Date) sdf.parse(dateString);
            if (date == null || !dateString.equals(sdf.format(date))) {
                logger.error("日期格式错误");
                return null;
            }
        } catch (ParseException e) {
            logger.error(e.toString(), e);
        }
        return new Date(date.getTime());
    }

    /**
     * 把字符串转换为日期
     *
     * @param dateString 字符串
     * @param style      格式
     * @return
     */
    public static Date parse(String dateString, DateStyle style) {
        return parse(dateString, style.getValue());
    }

    /**
     * 把字符串转换为时间
     *
     * @param dateString 字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Timestamp parseTime(String dateString) {
        return parseTime(dateString, DAFAULT_DATETIME_FORMAT);
    }

    /**
     * 把字符串转换为时间
     *
     * @param dateString 字符串
     * @param pattern    格式
     * @return
     */
    public static Timestamp parseTime(String dateString, String pattern) {
        if ("".equals(dateString))
            return null;
        if (pattern == null)
            pattern = DAFAULT_DATETIME_FORMAT;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = (Date) simpleDateFormat.parse(dateString);
            if (date == null
                    || !dateString.equals(simpleDateFormat.format(date))) {
                logger.error("日期时间格式错误");
                return null;
            }
        } catch (ParseException e) {
            logger.error(e.toString(), e);
        }
        return new Timestamp(date.getTime());
    }

    /**
     * 当前时间与协调世界时 1970 年 1 月 1 日午夜之间的时间差（以毫秒为单位测量）。
     *
     * @return
     * @author hezhao
     * @Time 2017年8月1日 上午10:56:21
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 系统计时器的当前值，以毫微秒为单位。
     *
     * @return
     * @author hezhao
     * @Time 2017年8月1日 上午10:56:12
     */
    public static long getNanoTime() {
        return System.nanoTime();
    }

    /**
     * 返回unix时间戳 (1970年至今的秒数)
     *
     * @return
     */
    public static long getUnixStamp() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 得到昨天的日期，时间格式yyyy-MM-dd
     *
     * @return
     */
    public static String getYestoryDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        String yestoday = dateFormat.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 得到明天的日期，时间格式yyyy-MM-dd
     *
     * @return
     */
    public static String getTomorrowDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        String yestoday = dateFormat.format(calendar.getTime());
        return yestoday;
    }

    /**
     * 得到当前的时间，时间格式yyyy-MM-dd
     *
     * @return
     */
    public String getCurrentDate() {
        return dateFormat.format(new Date());
    }

    /**
     * 得到当前的时间,自定义时间格式
     *
     * @param pattern 输出显示的时间格式
     * @return
     */
    public String getCurrentDate(String pattern) {
        SimpleDateFormat sdf = getFormatInstance(pattern);
        return sdf.format(new Date());
    }

    /**
     * 取得Date型的当前日期
     *
     * @return
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 取得Date型的当前日期
     *
     * @return
     */
    public static Date getToday() {
        return getDate(getIntNow());
    }

    /**
     * 取得Integer型的当前日期
     *
     * @return
     */
    public static Integer getIntNow() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的当前年份
     *
     * @return
     */
    public static Integer getIntYearNow() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得Integer型的当前月份
     *
     * @return
     */
    public static Integer getIntMonthNow() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    public static String getStringToday() {
        return getIntDate(getNow()) + "";
    }

    /**
     * 根据年月日获取整型日期
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Integer getIntDate(int year, int month, int day) {
        return getIntDate(newCalendar(year, month, day));
    }

    /**
     * 获取某年第一天
     *
     * @param year
     * @return
     */
    public static Date getFirstDateByYear(Integer year) {
        if (year == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(year, 0, 1, 0, 0, 0);
        return c.getTime();
    }

    /**
     * 获取某年第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDateByYear(Date date) {
        return getFirstDateByYear(getYear(date));
    }

    /**
     * 获取今年第一天
     *
     * @return
     */
    public static Date getFirstDateByYear() {
        return getFirstDateByYear(getNow());
    }

    /**
     * 获取某年最后一天
     *
     * @param date
     * @return 年份的最后一天
     */
    public static Date getLastDateByYear(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + 1);
        now.set(Calendar.MONTH, 0);
        now.set(Calendar.DATE, 0);
        now.set(Calendar.HOUR, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    /**
     * 获取今年最后一天
     *
     * @return 年份的最后一天
     */
    public static Date getLastDateByYear() {
        return getLastDateByYear(getNow());
    }

    /**
     * 获取某年最后一天
     *
     * @return 年份的最后一天
     */
    public static Date getLastDateByYear(int year) {
        return getLastDateByYear(getDate(year));
    }

    /**
     * 某年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Integer getFirstDateByMonth(int year, int month) {
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 本月第一天
     *
     * @return
     */
    public static Integer getFirstDateByMonth() {
        Integer year = getIntYearNow();
        Integer month = getIntMonthNow();
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 获得所在月份的第一天
     *
     * @return 月份的第一天
     */
    public static Date getFirstDateByMonth(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, 0);
        now.set(Calendar.HOUR, 24);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    /**
     * 获得所在月份的最后一天
     *
     * @return 月份的最后一天
     */
    public static Date getLastDateByMonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
        now.set(Calendar.DATE, 1);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
        now.set(Calendar.HOUR, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    /**
     * 本月最后一天
     *
     * @return
     */
    public static Integer getLastDateByMonth() {
        Integer year = getIntYearNow();
        Integer month = getIntMonthNow();
        return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
    }

    /**
     * 某年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Integer getLastDateByMonth(int year, int month) {
        return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
    }

    /**
     * 获得所在星期的第一天
     */
    public static Date getFirstDateByWeek(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        now.set(now.DATE, first_day_of_week);
        now.set(Calendar.HOUR, 0);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    /**
     * 获得所在星期的最后一天
     */
    public static Date getLastDateByWeek(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        int last_day_of_week = first_day_of_week + 6; // 星期日
        now.set(now.DATE, last_day_of_week);
        now.set(Calendar.HOUR, 23);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    /**
     * 根据Calendar获取整型年份
     *
     * @param c
     * @return
     */
    public static Integer getIntYear(Calendar c) {
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 根据Calendar获取整型日期
     *
     * @param c
     * @return
     */
    public static Integer getIntDate(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year * 10000 + month * 100 + day;
    }

    /**
     * 根据Date获取整型年份
     *
     * @param d
     * @return
     */
    public static Integer getIntYear(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntYear(c);
    }

    /**
     * 根据Date获取整型日期
     *
     * @param d
     * @return
     */
    public static Integer getIntDate(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntDate(c);
    }

    /**
     * 根据Integer获取Date日期
     *
     * @param n
     * @return
     */
    public static Date getDate(Integer n) {
        if (n == null) {
            return null;
        }
        // Calendar c = Calendar.getInstance();
        // c.set(n / 10000, n / 100 % 100 - 1, n % 100);
        // return c.getTime();
        return parse(String.valueOf(n));
    }

    /**
     * 根据年月日生成Calendar
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Calendar newCalendar(int year, int month, int day) {
        Calendar ret = Calendar.getInstance();
        if (year < 100) {
            year = 2000 + year;
        }
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 根据整型日期生成Calendar
     *
     * @param date
     * @return
     */
    public static Calendar newCalendar(int date) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        Calendar ret = Calendar.getInstance();
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 整数型日期的加法
     *
     * @param date
     * @param days
     * @return
     */
    public static Integer intDateAdd(int date, int days) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        day += days;

        return getIntDate(year, month, day);
    }

    /**
     * 整数型日期的减法
     *
     * @param date
     * @param days
     * @return
     */
    public static Integer intDateSub(int date, int days) {
        return intDateAdd(date, -days);
    }

    // /**
    // * 国际化。
    // */
    // public static String formatI18nDate(Date date){
    // if(date == null){
    // return "";
    // }
    // actionSupport actionSupport = new ActionSupport();
    // SimpleDateFormat sdf = new
    // SimpleDateFormat(actionSupport.getText("date.i18n.format"));
    // return sdf.format(date);
    // }

    /**
     * 获取SimpleDateFormat实例.
     *
     * @param pattern 日期格式 如果为空使用DAFAULT_DATE_FORMAT
     * @return
     */
    public static SimpleDateFormat getFormatInstance(String pattern) {
        if (pattern == null || pattern.length() == 0) {
            pattern = DAFAULT_DATE_FORMAT;
        }
        return new SimpleDateFormat(pattern);
    }

    /**
     * 获取日期字符串的日期风格。失敗返回null。
     *
     * @param date 日期字符串
     * @return 日期风格
     */
    public static DateStyle getDateStyle(String date) {
        for (DateStyle style : DateStyle.values()) {
            Date dateTmp = null;
            SimpleDateFormat sdf = new SimpleDateFormat(style.getValue());
            try {
                dateTmp = (Date) sdf.parse(date);
                if (dateTmp != null && date.equals(sdf.format(dateTmp))) {
                    return style;
                }
            } catch (ParseException e) {
            }
        }
        return null;
    }

    /**
     * 获取日期中的某数值。如获取月份
     *
     * @param date     日期
     * @param dateType 日期格式
     * @return 数值
     */
    private static int getInteger(Date date, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(dateType);
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期字符串
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期字符串
     */
    private static String addInteger(String date, int dateType, int amount) {
        String dateString = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = parse(date, dateStyle);
            myDate = addInteger(myDate, dateType, amount);
            dateString = format(myDate, dateStyle);
        }
        return dateString;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期
     *
     * @param date     日期
     * @param dateType 类型
     * @param amount   数值
     * @return 计算后日期
     */
    private static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 判断字符串是否为日期字符串
     *
     * @param date 日期字符串
     * @return true or false
     */
    public static boolean isDate(String date) {
        boolean isDate = false;
        if (date != null) {
            if (parse(date) != null) {
                isDate = true;
            }
        }
        return isDate;
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期字符串
     */
    public static String addYear(String date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的年份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加年份后的日期
     */
    public static Date addYear(Date date, int yearAmount) {
        return addInteger(date, Calendar.YEAR, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期字符串
     */
    public static String addMonth(String date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }

    /**
     * 增加日期的月份。失败返回null。
     *
     * @param date       日期
     * @param yearAmount 增加数量。可为负数
     * @return 增加月份后的日期
     */
    public static Date addMonth(Date date, int yearAmount) {
        return addInteger(date, Calendar.MONTH, yearAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期字符串
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期字符串
     */
    public static String addDay(String date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的天数。失败返回null。
     *
     * @param date      日期
     * @param dayAmount 增加数量。可为负数
     * @return 增加天数后的日期
     */
    public static Date addDay(Date date, int dayAmount) {
        return addInteger(date, Calendar.DATE, dayAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期字符串
     *             增加数量。可为负数
     * @return 增加小时后的日期字符串
     */
    public static String addHour(String date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的小时。失败返回null。
     *
     * @param date 日期
     *             增加数量。可为负数
     * @return 增加小时后的日期
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期字符串
     *             增加数量。可为负数
     * @return 增加分钟后的日期字符串
     */
    public static String addMinute(String date, int hourAmount) {
        return addInteger(date, Calendar.MINUTE, hourAmount);
    }

    /**
     * 增加日期的分钟。失败返回null。
     *
     * @param date 日期
     *             增加数量。可为负数
     * @return 增加分钟后的日期
     */
    public static Date addMinute(Date date, int hourAmount) {
        return addInteger(date, Calendar.MINUTE, hourAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期字符串
     *             增加数量。可为负数
     * @return 增加秒钟后的日期字符串
     */
    public static String addSecond(String date, int hourAmount) {
        return addInteger(date, Calendar.SECOND, hourAmount);
    }

    /**
     * 增加日期的秒钟。失败返回null。
     *
     * @param date 日期
     *             增加数量。可为负数
     * @return 增加秒钟后的日期
     */
    public static Date addSecond(Date date, int hourAmount) {
        return addInteger(date, Calendar.SECOND, hourAmount);
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期字符串
     * @return 年份
     */
    public static int getYear(String date) {
        return getYear(parse(date));
    }

    /**
     * 获取日期的年份。失败返回0。
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        return getInteger(date, Calendar.YEAR);
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期字符串
     * @return 月份
     */
    public static int getMonth(String date) {
        return getMonth(parse(date));
    }

    /**
     * 获取日期的月份。失败返回0。
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        return getInteger(date, Calendar.MONTH);
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期字符串
     * @return 天
     */
    public static int getDay(String date) {
        return getDay(parse(date));
    }

    /**
     * 获取日期的天数。失败返回0。
     *
     * @param date 日期
     * @return 天
     */
    public static int getDay(Date date) {
        return getInteger(date, Calendar.DATE);
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期字符串
     * @return 小时
     */
    public static int getHour(String date) {
        return getHour(parse(date));
    }

    /**
     * 获取日期的小时。失败返回0。
     *
     * @param date 日期
     * @return 小时
     */
    public static int getHour(Date date) {
        return getInteger(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 分钟
     */
    public static int getMinute(String date) {
        return getMinute(parse(date));
    }

    /**
     * 获取日期的分钟。失败返回0。
     *
     * @param date 日期
     * @return 分钟
     */
    public static int getMinute(Date date) {
        return getInteger(date, Calendar.MINUTE);
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期字符串
     * @return 秒钟
     */
    public static int getSecond(String date) {
        return getSecond(parse(date));
    }

    /**
     * 获取日期的秒钟。失败返回0。
     *
     * @param date 日期
     * @return 秒钟
     */
    public static int getSecond(Date date) {
        return getInteger(date, Calendar.SECOND);
    }

    /**
     * 获取日期。默认yyyy-MM-dd格式。失败返回null。
     *
     * @param date 日期
     * @return 日期
     */
    public static String getDate(Date date) {
        return format(date, DateStyle.yyyy_MM_dd);
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期字符串
     * @return 时间
     */
    public static String getTime(String date) {
        return getTime(parse(date));
    }

    /**
     * 获取日期的时间。默认HH:mm:ss格式。失败返回null。
     *
     * @param date 日期
     * @return 时间
     */
    public static String getTime(Date date) {
        return format(date, DateStyle.HH_mm_ss);
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期字符串
     * @return 星期
     */
    public static Week getWeek(String date) {
        Week week = null;
        DateStyle dateStyle = getDateStyle(date);
        if (dateStyle != null) {
            Date myDate = parse(date, dateStyle);
            week = getWeek(myDate);
        }
        return week;
    }

    /**
     * 获取日期的星期。失败返回null。
     *
     * @param date 日期
     * @return 星期
     */
    public static Week getWeek(Date date) {
        Week week = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekNumber = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        switch (weekNumber) {
            case 0:
                week = Week.SUNDAY;
                break;
            case 1:
                week = Week.MONDAY;
                break;
            case 2:
                week = Week.TUESDAY;
                break;
            case 3:
                week = Week.WEDNESDAY;
                break;
            case 4:
                week = Week.THURSDAY;
                break;
            case 5:
                week = Week.FRIDAY;
                break;
            case 6:
                week = Week.SATURDAY;
                break;
        }
        return week;
    }
}
