package com.texas.anexus.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Utility class for date.
 * 
 * @author Deependra Karki
 * @version 1.0.0
 * @since 1.0.0, April 24, 2018
 */
@SuppressWarnings("unused")
public class DateUtils {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// private static final DateFormat dateFormat = new
	// SimpleDateFormat(DATE_FORMAT);
	private static final DateTimeFormatter dateFormat8 = DateTimeFormatter.ofPattern(DATE_FORMAT);

	public static Date addMinutes(Date date, int minutes) {
		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
		// LocalDateTime newYearsEve = LocalDateTime.now();
		LocalDateTime dateAfterMinutes = ldt.plusMinutes(minutes);

		Instant instant1 = dateAfterMinutes.toInstant(ZoneOffset.UTC);
		Date newDate = Date.from(instant1);
		return newDate;
	}

	public static Date currentDateTimePlusMinutes(int minutes) {
		// Get current date
		Date currentDate = new Date();
		// System.out.println("date : " + dateFormat.format(currentDate));

		// convert date to localdatetime
		LocalDateTime localDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		// System.out.println("localDateTime : " +
		// dateFormat8.format(localDateTime));

		// plus one
		// localDateTime = localDateTime.plusYears(1).plusMonths(1).plusDays(1);
		// localDateTime =
		// localDateTime.plusHours(1).plusMinutes(2).minusMinutes(1).plusSeconds(1);
		localDateTime = localDateTime.plusMinutes(minutes);

		// convert LocalDateTime to date
		Date currentDatePlusMinutes = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

		return currentDatePlusMinutes;
	}

	public static boolean isCurrentTimeBeforeThanGivenTime(Date when) {
		Date now = new Date();
		if (now.before(when)) {
			return true;
		}
		return false;
	}
}
