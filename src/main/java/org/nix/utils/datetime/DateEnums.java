package org.nix.utils.datetime;

/**
 * 日期枚举
 * @author hezhao
 * @Time   2017年7月31日 上午11:50:45
 */
public class DateEnums {

	/**
	 * 日期类型枚举
	 * 
	 * @author user
	 * 
	 */
	public enum DateStyle {

		/**
		 * MM-dd 例如 03-13
		 */
		MM_DD("MM-dd"),
		/**
		 * yyyy-MM 例如 2017-03
		 */
		yyyy_MM("yyyy-MM"),
		/**
		 * yyyy-MM-dd 例如 2017-03-13
		 */
		yyyy_MM_dd("yyyy-MM-dd"),
		/**
		 * MM-dd HH:mm 例如 03-13 15:16
		 */
		MM_dd_HH_mm("MM-dd HH:mm"),
		/**
		 * MM-dd HH:mm:ss 例如 03-13 15:16:47
		 */
		MM_dd_HH_mm_ss("MM-dd HH:mm:ss"),
		/**
		 * yyyy-MM-dd HH:mm 例如 2017-03-13 15:16
		 */
		yyyy_MM_dd_HH_mm("yyyy-MM-dd HH:mm"),
		/**
		 * yyyy-MM-dd HH:mm:ss 例如 2017-03-13 15:16:47
		 */
		yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),
		/**
		 * yyyy-MM-dd HH:mm:ss 例如 2017-03-13 星期一 15:16:47
		 */
		yyyy_MM_dd_E_HH_mm_ss("yyyy-MM-dd E HH:mm:ss"),
		/**
		 * yyyy-MM-dd HH:mm:ss:S 例如 2017-03-13 15:16:47:356
		 */
		yyyy_MM_dd_HH_mm_ss_S("yyyy-MM-dd HH:mm:ss:S"),
		/**
		 * yyyy-MM-dd HH:mm:ss:S 例如 2017-03-13 星期一 15:16:47:356
		 */
		yyyy_MM_dd_E_HH_mm_ss_S("yyyy-MM-dd E HH:mm:ss:S"),

		/**
		 * MM/dd 例如 03/13
		 */
		MM_dd_EN("MM/dd"),
		/**
		 * yyyy/MM 例如 2017/03
		 */
		yyyy_MM_EN("yyyy/MM"),
		/**
		 * yyyy/MM/dd 例如 2017/03/13
		 */
		yyyy_MM_dd_EN("yyyy/MM/dd"),
		/**
		 * MM/dd HH:mm 例如 03/13 15:16
		 */
		MM_dd_HH_mm_EN("MM/dd HH:mm"),
		/**
		 * MM/dd HH:mm:ss 例如 03/13 15:16:47
		 */
		MM_dd_HH_mm_ss_EN("MM/dd HH:mm:ss"),
		/**
		 * yyyy/MM/dd HH:mm 例如 2017/03/13 15:16
		 */
		yyyy_MM_dd_HH_mm_EN("yyyy/MM/dd HH:mm"),
		/**
		 * yyyy/MM/dd HH:mm:ss 例如 2017/03/13 15:16:47
		 */
		yyyy_MM_dd_HH_mm_ss_EN("yyyy/MM/dd HH:mm:ss"),
		/**
		 * yyyy/MM/dd E HH:mm:ss:S 例如 2017/03/13 星期一 15:16:47
		 */
		yyyy_MM_dd_E_HH_mm_ss_EN("yyyy/MM/dd E HH:mm:ss"),
		/**
		 * yyyy/MM/dd HH:mm:ss:S 例如 2017/03/13 15:16:47:356
		 */
		yyyy_MM_dd_HH_mm_ss_S_EN("yyyy/MM/dd HH:mm:ss:S"),
		/**
		 * yyyy/MM/dd E HH:mm:ss:S 例如 2017/03/13 星期一 15:16:47:356
		 */
		yyyy_MM_dd_E_HH_mm_ss_S_EN("yyyy/MM/dd E HH:mm:ss:S"),

		/**
		 * MM月dd日 例如 07月27日
		 */
		MM_dd_CN("MM月dd日"),
		/**
		 * yyyy年MM月 例如 2017年07月
		 */
		yyyy_MM_CN("yyyy年MM月"),
		/**
		 * yyyy年MM月dd日 例如 2017年07月27日
		 */
		yyyy_MM_dd_CN("yyyy年MM月dd日"),
		/**
		 * MM月dd日 HH:mm 例如 07月27日 13:32
		 */
		MM_dd_HH_mm_CN("MM月dd日 HH:mm"),
		/**
		 * MM月dd日 HH:mm:ss 例如 07月27日 13:32:53
		 */
		MM_dd_HH_mm_ss_CN("MM月dd日 HH:mm:ss"),
		/**
		 * yyyy年MM月dd日 HH:mm 例如 2017年07月27日 13:32
		 */
		yyyy_MM_dd_HH_mm_CN("yyyy年MM月dd日 HH:mm"),
		/**
		 * yyyy年MM月dd日 HH:mm:ss:S 例如 2017年07月27日 13:32:53
		 */
		yyyy_MM_dd_HH_mm_ss_CN("yyyy年MM月dd日 HH:mm:ss"),
		/**
		 * yyyy年MM月dd日 HH:mm:ss:S 例如 2017年07月27日 星期四 13:32:53
		 */
		yyyy_MM_dd_E_HH_mm_ss_CN("yyyy年MM月dd日 E HH:mm:ss"),
		/**
		 * yyyy年MM月dd日 HH:mm:ss:S 例如 2017年07月27日 13:32:53:356
		 */
		yyyy_MM_dd_HH_mm_ss_S_CN("yyyy年MM月dd日 HH:mm:ss:S"),
		/**
		 * yyyy年MM月dd日 HH:mm:ss:S 例如 2017年07月27日 星期四 13:32:53:356
		 */
		yyyy_MM_dd_E_HH_mm_ss_S_CN("yyyy年MM月dd日 E HH:mm:ss:S"),

		/**
		 * yy 例如 17
		 */
		yy("yy"),
		/**
		 * yyyy 例如 2017
		 */
		yyyy("yyyy"),
		/**
		 * MM 例如 07
		 */
		MM("MM"),
		/**
		 * dd 例如 27
		 */
		dd("dd"),
		/**
		 * yyyyMM 例如 201707
		 */
		yyyyMM("yyyyMM"),
		/**
		 * yyyyMMdd 例如 20170727
		 */
		yyyyMMdd("yyyyMMdd"),
		/**
		 * yyyyMMddHHmmss 例如 20170727133253
		 */
		yyyyMMddHHmmss("yyyyMMddHHmmss"),
		/**
		 * yyMMddHHmmss 例如 170727133253
		 */
		yyMMddHHmmss("yyMMddHHmmss"),

		/**
		 * HH:mm 例如 32:45
		 */
		HH_mm("HH:mm"),
		/**
		 * HH:mm:ss 例如 13:32:45
		 */
		HH_mm_ss("HH:mm:ss");

		private String value;

		DateStyle(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 * 星期 枚举
	 * 
	 * @author user
	 * 
	 */
	public enum Week {

		/**
		 * 星期一
		 */
		MONDAY("星期一", "Monday", "Mon.", 1),
		/**
		 * 星期二
		 */
		TUESDAY("星期二", "Tuesday", "Tues.", 2),
		/**
		 * 星期三
		 */
		WEDNESDAY("星期三", "Wednesday", "Wed.", 3),
		/**
		 * 星期四
		 */
		THURSDAY("星期四", "Thursday", "Thur.", 4),
		/**
		 * 星期五
		 */
		FRIDAY("星期五", "Friday", "Fri.", 5),
		/**
		 * 星期六
		 */
		SATURDAY("星期六", "Saturday", "Sat.", 6),
		/**
		 * 星期日
		 */
		SUNDAY("星期日", "Sunday", "Sun.", 7);

		String name_cn;
		String name_en;
		String name_enShort;
		int number;

		Week(String name_cn, String name_en, String name_enShort, int number) {
			this.name_cn = name_cn;
			this.name_en = name_en;
			this.name_enShort = name_enShort;
			this.number = number;
		}

		public String getChineseName() {
			return name_cn;
		}

		public String getName() {
			return name_en;
		}

		public String getShortName() {
			return name_enShort;
		}

		public int getNumber() {
			return number;
		}
	}
	
}
