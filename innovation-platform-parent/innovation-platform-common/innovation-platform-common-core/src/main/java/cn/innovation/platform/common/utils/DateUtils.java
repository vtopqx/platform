package cn.innovation.platform.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;


/**
 * 日期工具类
 */
public class DateUtils {

	private static SimpleDateFormat sdf = null;

	public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";
	public static final String DATE_FORMAT_YYYYMMDD_MIDDLE_LINE = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMMDD_MAOHAO = "YYYYMMDD";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";
	public static final String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

	public static Date getCurrentDate() {
		return new Date(new java.util.Date().getTime());
	}
	
	/**
	 * 生成时间戳
	 * 从2000-01-01 00:00:00到该请求时的间隔秒数，再加上60秒的延时
	 * 用于广东oa的跳转校验
	 * @return
	 */
	public static long getTimeStampForGDOA(){
		long ss = 0;
		try {
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date2000 = sdf.parse("20000101000000");
			Date now = new Date();
			ss = (now.getTime()-date2000.getTime())/1000 + 60;//得到秒数
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return ss;
	}
	
	/**
	 * 生成当前系统的时间，格式为yyyyMMddHHmmssSSS
	 * 用于统一认证验证token的接口(杭州研发中心的接口)
	 * @return 17位的系统当前时间
	 */
	public static String getSystemTime(){
		sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}
	/**
	 * 生成当前系统的时间，格式为yyyyMMddHHmmssSSS
	 * 用于统一认证验证token的接口(杭州研发中心的接口)
	 * @return 17位的系统当前时间
	 */
	public static String getCompanyRightsTime(Date date){
		sdf = new SimpleDateFormat("yyyyMM");
		return sdf.format(date);
	}
	
	/**
	 * 生成当前系统的时间，格式为yyyyMMddHHmmss
	 * 用于负一屏单点到139邮箱首页中的请求
	 * @return
	 */
	public static String getReqTime(){
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}
	
	public static String getSystemTime14Bit(){
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 获取两小时之后的时间点
	 * 
	 * @return
	 */
	public static java.util.Date getTwoHourAfter() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 2);
		return now.getTime();
	}

	/**
	 * 是否晚于当前时间
	 * 
	 * @param time
	 *            输入时间
	 * @return
	 */
	public static boolean isAfterNowTime(java.util.Date time) {
		return time.after(new java.util.Date());
	}

	// 是否是指定日期
	public static boolean isTheDay(final Date date, final Date day) {
		return date.getTime() >= DateUtils.dayBegin(day).getTime()
				&& date.getTime() <= DateUtils.dayEnd(day).getTime();
	}

	// 获取指定时间的那天 00:00:00.000 的时间
	private static Date dayBegin(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	// 获取指定时间的那天 23:59:59.999 的时间
	private static Date dayEnd(final Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	/**
	 * 根据传入的时间格式获取当天时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getToday(String format) {
		sdf = new SimpleDateFormat(format);
		return sdf.format(new Date());
	}

	/**
	 * 获取当前时间 yyyyMMddHHmmssSSS
	 * 
	 * @return
	 */
	public static String getUIIDByCurrentTime() {
		return getToday(DATE_FORMAT_YYYYMMDDHHMMSSSSS);
	}

	/**
	 * 根据传入的时间格式获取昨天时间
	 * 
	 * @param format
	 * @return
	 */
	public static String getYesterday(String format) {
		sdf = new SimpleDateFormat(format);
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_MONTH, -1);
		return sdf.format(now.getTime());
	}

	/**
	 * 得到当前年份
	 * 
	 * @return
	 */
	public static Integer getCurrentYear() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return
	 */
	public static Integer getCurrentMonth() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.MONTH)+1;
	}
	
	/**
	 * 得到当前日
	 * 
	 * @return
	 */
	public static Integer getCurrentDay() {
		Calendar now = Calendar.getInstance();
		return now.get(Calendar.DATE);
	}

	/**
	 * 获取系统当前的时间的时间戳
	 * 
	 * @return 返回字符串,如20161014180720
	 */
	public static String getTimeStamp() {
		sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 消息提醒模板日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String getRemindTime(Date date) {
		sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		return sdf.format(date);
	}

	/**
	 * 格式化短日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static String formartShortDate(Date date) {
		sdf = new SimpleDateFormat(DATE_FORMAT_YYYYMMDD_MIDDLE_LINE);
		return sdf.format(date);
	}

	/**
	 * 格式化长日期格式
	 * 
	 * @param date
	 * @return
	 */
	public static Date formatLongDate(Date date) {
		sdf = new SimpleDateFormat(DATE_FORMAT_DATETIME);
		try {
			return sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 按照公文要求显示时间 若是今天，则显示 “今天 HH:mm” 若是昨天，则显示 “昨天 HH:mm” 其他则显示 “MM月DD日”
	 * 
	 * @param value
	 *            传入的时间字符串 20160217150517 type:显示的类型
	 * @return
	 */
	public static String formatDataTime(String value, int type) {
		String resStr = "";
		if (StringUtils.isNotEmpty(value)) {
			String inputDate = value.substring(0, 8);
			String today = getToday(DATE_FORMAT_YYYYMMDD);
			String yesterDay = getYesterday(DATE_FORMAT_YYYYMMDD);
			Integer yearInput = new Integer(value.substring(0, 4));
			Integer monthInput = new Integer(value.substring(4, 6));
			String hour = value.substring(8, 10);
			String minue = value.substring(10, 12);
			String inputTime = hour + ":" + minue;
			if (today.equalsIgnoreCase(inputDate)) {
				if (type == 1) {
					resStr = inputTime;
				} else {
					resStr = "今天 " + inputTime;
				}
			} else if (yesterDay.equalsIgnoreCase(inputDate)) {
				resStr = "昨天 " + inputTime;
			} else {
				String month = value.substring(4, 6);
				String day = value.substring(6, 8);
				if (yearInput < DateUtils.getCurrentYear()) {
					resStr = yearInput.toString();
					resStr += "年";
					resStr += month;
					resStr += "月";
					resStr += day;
					resStr += "日";
				} else {
					if (monthInput < DateUtils.getCurrentMonth()) {
						resStr = month + "月" + day + "日  ";
					} else {
						if (type == 1) {
							resStr = month + "月" + day + "日  ";
						} else {
							resStr = month + "月" + day + "日  " + inputTime;
						}
					}
				}
			}
		}
		return resStr;
	}

	/**
	 * 显示格式：2016年9月10日 18:00
	 * 
	 * @param value
	 *            传入的时间字符串 20160910180017
	 * @return
	 */
	public static String formatTime(String value) {
		String resStr = "";
		Integer year = new Integer(value.substring(0, 4));
		String hour = value.substring(8, 10);
		String minue = value.substring(10, 12);
		String inputTime = hour + ":" + minue;
		Integer month = new Integer(value.substring(4, 6));
		Integer day = new Integer(value.substring(6, 8));
		resStr = year + "年" + month + "月" + day + "日" + "  " + inputTime;
		return resStr;
	}

	/**
	 * 通用的格式化时间
	 * 
	 * @param 传入的时间字符串
	 *            如：20160217150517
	 * @return
	 */
	public static String formatDataTime(String value) {
		return formatDataTime(value, 0);
	}

	/**
	 * 用于新闻公告列表时间格式化
	 * 
	 * @param 传入的时间字符串
	 *            如：20160217150517
	 * @return
	 */
	public static String formatDateTimeForNotice(String value) {
		return formatDataTime(value, 1);
	}

	// 获取指定格式的当前系统日期、时间
	public static String getDateToString() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}

	/**
	 * 显示格式：2016年12月5日 08:00:00 格式化时间
	 * 
	 * @param 2016-12-05 08:00
	 * @return
	 */
	public static String formatDate(String value) {
		String year = value.substring(0, 4);
		String month = value.substring(5, 7);
		String day = value.substring(8, 10);
		String hour = value.substring(11, 13);
		String minute = value.substring(14, 16);
		String second = value.substring(17, 19);
		value = year + month + day + hour + minute + second;
		return formatDataTime(value, 0);
	}

	/**
	 * 计算相隔天数
	 * 
	 * @param time
	 *            (2016-10-11)
	 * @return
	 * @throws ParseException
	 */
	public static long calculateNumberOfDays(String startTime, String endTime)
			throws ParseException {
		long DAY = 24L * 60L * 60L * 1000L;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		startTime = startTime.substring(0, 10);
		Date d2 = null;
		if (endTime == null || endTime == "") {
			d2 = df.parse(df.format(new Date()));
		} else {
			endTime = endTime.substring(0, 10);
			d2 = df.parse(endTime);
		}
		Date d1 = df.parse(startTime);
		long daysApart = (d2.getTime() - d1.getTime()) / DAY;
		return daysApart;
	}
	/**
	 * 验证请求接口服务的时效性
	 * 计算两个时间的秒级别的差
	 * @param startTime   yyyyMMddHHmmssSSS 形式的时间字符串
	 * @param endTime     yyyyMMddHHmmssSSS 形式的时间字符串
	 * @return
	 * @throws ParseException
	 */
	public static long calculateNumberOfSeconds(String startTime, String endTime)
			throws ParseException {
		long second = 1000L;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		Date d2 = null;
		if (endTime == null || endTime == "") {
			d2 = df.parse(df.format(new Date()));
		} else {
			d2 = df.parse(endTime);
		}
		Date d1 = df.parse(startTime);
		long secondApart = (d2.getTime() - d1.getTime()) /second;
		return secondApart;
	}

	/**
	 * Date转换String，模板"YYYYMMDDHHMMSS"
	 * 
	 * @param date
	 * @return 失败返回null
	 */
	public static String datetimeToString(Date date) {
		return dateToString(date, DATE_FORMAT_YYYYMMDDHHMMSS);
	}

	/**
	 * Date转换String，+模板
	 * 
	 * @param date
	 * @param pattern
	 *            yyyyMMddHHmmss格式模板
	 * @return 失败返回null
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} else {
			return null;
		}
	}

	/**
	 * String转换Date，+模板
	 * 
	 * @param str
	 * @return
	 */
	public static Date stringToDate(String str) {
		if (StringUtils.isNotEmpty(str)) {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_DATETIME);
			try {
				return sdf.parse(str);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 获取协同任务中的截止日期当天与当前时间的小时差
	 * 
	 * @author lifeng
	 * @date 2016年12月27日
	 * @param estimateCompleteTime
	 * @return
	 */
	public static String getWarnTime(String estimateCompleteTime) {
		String warnTime = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date res = sdf.parse(estimateCompleteTime);
			Date nowDate = new Date();
			long millisecond = nowDate.getTime() - res.getTime();
			long day = millisecond / (1000 * 60 * 60 * 24);// 天
			long hour = millisecond / (1000 * 60 * 60) - day * 24;// 小时
			long minute = millisecond / (1000 * 60) - hour * 60 - day * 24 * 60;// 分钟
			warnTime = "";
			if (day > 0) {
				warnTime += day + "天";
			}
			if (hour > 0) {
				warnTime += hour + "小时";
			}
			if (minute > 0) {
				warnTime += minute + "分钟";
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return warnTime;
	}

	/**
	 * 获取 当前时间减去协同任务中的 截止日期所得的时间小时数，例如1小时;
	 * 
	 * @author lifeng
	 * @date 2016年12月27日
	 * @param estimateCompleteTime
	 * @return
	 */
	public static Integer getWarnHour(String estimateCompleteTime) {
		Integer warnHour = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date res = sdf.parse(estimateCompleteTime);
			Date nowDate = new Date();
			long millisecond = res.getTime() - nowDate.getTime();
			warnHour = (int) (millisecond / (1000 * 60 * 60));// 小时
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return warnHour;
	}

	/**
	 * 获取 当前时间减去协同任务中的 截止日期所得的时间分钟数，例如10分钟;
	 * 
	 * @author lifeng
	 * @date 2017年01月7日
	 * @param estimateCompleteTime
	 * @return
	 */
	public static Integer getWarnMinute(String estimateCompleteTime) {
		Integer warnMinute = 0;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date res = sdf.parse(estimateCompleteTime);
			Date nowDate = new Date();
			long millisecond = res.getTime() - nowDate.getTime();
			long day = millisecond / (1000 * 60 * 60 * 24);// 天
			long hour = millisecond / (1000 * 60 * 60) - day * 24;// 小时
			warnMinute = (int) (millisecond / (1000 * 60) - hour * 60 - day * 24 * 60);// 分钟
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return warnMinute;
	}
	
	/**
	 * 解析时间
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static Date parseDate(String source, String pattern){
		Date result = null;
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			result = sdf.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 获取 系统日期的开始时间
	 * @return
	 */
	public static Date getTodateStart(){
		return dayBegin(new Date());
	}
	
	/**
	 * 获取 系统日期的结束时间
	 * @return
	 */
	public static Date getTodateEnd(){
		return dayEnd(new Date());
	}
	
	/**
	 * 获取 系统日期前一天的开始时间
	 * @return
	 */
	public static Date getYesterdayStart(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND,0);
		return calendar.getTime();
	}
	
	/**
	 * 获取 系统日期前一天的结束时间
	 * @return
	 */
	public static Date getYesterdayEnd(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND,59);
		return calendar.getTime();
	}

	
	/**
	 * 比较当前时间是否在一个时间范围内
	 * @param startTime 要比较的开始时间
	 * @param minuteNumber	开始时间使加的分钟数=结束时间
	 * @param hourNumber	小时数
	 * @return
	 */
	public static boolean comparisonDateSize(Date startTime, int minuteNumber, int hourNumber){
		boolean success = false;
		Calendar cal = Calendar.getInstance();  
        cal.setTime(startTime);  
        cal.add(Calendar.MINUTE, minuteNumber);
        cal.add(Calendar.HOUR, hourNumber);
        startTime = cal.getTime();

        sdf = new SimpleDateFormat("yyyyMMddHHmmss");;
        //当前系统时间
        Long currentTime = Long.valueOf(getTimeStamp());
        //结束时间
        Long endTime= Long.valueOf(sdf.format(startTime));
        if((currentTime-endTime)>0){
        	success = true;
        }
		return success;
	}
	
	
	public static void main(String[] args) {
	/*//	long warnHour = DateUtils.getWarnHour("2017-01-09 20:00:00");
		String warnTime = DateUtils.getWarnTime("2017-01-09 20:00:00");
		System.out.println(warnHour + "===" + warnTime);*/
		
		
	/*	boolean sss = comparisonDateSize(stringToDate("2018-04-25 16:35:11"), 5, 0);
		System.out.println("结果："+sss);*/
	}

}
