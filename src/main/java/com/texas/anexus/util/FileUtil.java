package com.texas.anexus.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class FileUtil {

	private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);
	private static final String FILE_DIRECTORY = "Users";
	private static final String FILE_SEPARATOR="/";

	/**
	 * Writes file in specified location.
	 * 
	 * @param fileName The file name that can not be null and saves file with this given
	 * name.
	 * @param location The location can not be null and uses to save file in this given
	 * location.
	 * @param value The value that should not be null and must containg Base64 string.
	 * @return <code>true</code> if written successfully otherwise <code>false</code>
	 */
	@Deprecated
	public static File write(String fileName, String location, String value) {
		LOG.info("File Uploading...");
		InputStream is = null;
		OutputStream os = null;
		location += "//" + fileName;
		File file = new File(location);
		File filePath = new File(file.getPath());
		try {
			is = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
			fileName = null;
			value = null;
			os = new FileOutputStream(file);
			int bytesRead = 0;
			int batchLength = 10000000;
			byte[] batch = new byte[batchLength];
			while ((bytesRead = is.read(batch)) > 0) {
				byte[] ba = new byte[bytesRead];

				for (int i = 0; i < ba.length; i++) {
					ba[i] = batch[i];
				}
				ba = Base64.decode(new String(ba));
				os.write(ba, 0, ba.length);
			}
			LOG.info("File uploaded to: " + file.getAbsolutePath());
		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.toString());
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.toString());
			}
			try {
				os.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.toString());
			}
		}
		return filePath;
	}

	/**
	 * Writes file in default location.
	 * 
	 * @param fileName The file name that can not be null and saves file with this given
	 * name.
	 * @param value The value that should not be null and must containg Base64 string.
	 * @return <code>true</code> if written successfully otherwise <code>false</code>
	 */
	public static File write(String fileName, String value) {
		LOG.info("File Uploading...");
		InputStream is = null;
		OutputStream os = null;
		File directory = new File(FILE_DIRECTORY);
		File file = null;
		try {

			if (!directory.exists()) {
				directory.mkdir();
			}
			file = new File(directory.getPath().concat(File.separator).concat(fileName));
			is = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));     //Break-point
			fileName = null;
			value = null;
			os = new FileOutputStream(file);
			int bytesRead = 0;
			int batchLength = 10000000;
			byte[] batch = new byte[batchLength];
			while ((bytesRead = is.read(batch)) > 0) {
				byte[] ba = new byte[bytesRead];

				for (int i = 0; i < ba.length; i++) {
					ba[i] = batch[i];
				}
				ba = Base64.decode(new String(ba));
				if (os != null && ba != null) {
					os.write(ba, 0, ba.length);
				}
			}
			LOG.info("File uploaded to: " + file.getAbsolutePath());

		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.toString());
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.toString());
			}
			try {
				os.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.toString());
			}
		}
		return file;
	}

	@SuppressWarnings("restriction")
	@Deprecated
	public static File write(String fileName, String value, Long customerId, Long userId,
			Date date, UserRole userRole) {
		LOG.info("File Uploading...");
		InputStream is = null;
		OutputStream os = null;
		File directory = new File(FILE_DIRECTORY);
		File file = null;
		try {

			if (!directory.exists()) {
				directory.mkdir();
			}

			String s1 = Long.toString(customerId);
			String s2 = "/";
			s2 = s2.concat(s1);
			String custDirectory = FILE_DIRECTORY.concat(s2);
			File directory1 = new File(custDirectory);
			if (!directory1.exists()) {
				directory1.mkdir();
			}

			// Date date; // your date
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			String months = Month.of(month).name();

			s1 = Integer.toString(year);
			s2 = "/";
			s2 = s2.concat(s1);
			String yearDirectory = custDirectory.concat(s2);
			File directory2 = new File(yearDirectory);
			if (!directory2.exists()) {
				directory2.mkdir();
			}

			// s1=Integer.toString(months);
			s2 = "/";
			s2 = s2.concat(months);
			String monthDirectory = yearDirectory.concat(s2);
			File directory3 = new File(monthDirectory);
			if (!directory3.exists()) {
				directory3.mkdir();
			}

			String userRoles = userRole.toString();
			s2 = "/";
			s2 = s2.concat(userRoles);
			String userRoleDirectory = monthDirectory.concat(s2);
			File directory4 = new File(userRoleDirectory);
			if (!directory4.exists()) {
				directory4.mkdir();
			}

			file = new File(directory4.getPath().concat(File.separator).concat(fileName));
			is = new ByteArrayInputStream(value.getBytes(StandardCharsets.UTF_8));
			fileName = null;
			value = null;
			os = new FileOutputStream(file);
			int bytesRead = 0;
			int batchLength = 10000000;
			byte[] batch = new byte[batchLength];
			while ((bytesRead = is.read(batch)) > 0) {
				byte[] ba = new byte[bytesRead];

				for (int i = 0; i < ba.length; i++) {
					ba[i] = batch[i];
				}
				ba = Base64.decode(new String(ba));
				if (os != null && ba != null) {
					os.write(ba, 0, ba.length);
				}
			}
			LOG.info("File uploaded to: " + file.getAbsolutePath());

		}
		catch (Exception e) {
			e.printStackTrace();
			LOG.info(e.toString());
		}
		finally {
			try {
				is.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.toString());
			}
			try {
				os.close();
			}
			catch (IOException e) {
				e.printStackTrace();
				LOG.info(e.toString());
			}
		}
		return file;

	}

	/**
	 * Returns the file location in the following format. <b> Format:
	 * <code>customer/customerId/yyyy/mm/owner(user/teacher/student)</code>
	 * @param customerId
	 * @param owner
	 * @author Yubaraj
	 * @since 1.0.0
	 */
	public static String getFileLocation(Long customerId, UserRole owner) {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault())
				.toLocalDate();
		int year = localDate.getYear();
		int month = localDate.getMonthValue();
		String finalDirectory = FILE_DIRECTORY.concat(FILE_SEPARATOR)
				.concat(String.valueOf(customerId)).concat(FILE_SEPARATOR)
				.concat(String.valueOf(year)).concat(FILE_SEPARATOR)
				.concat(String.valueOf(month)).concat(FILE_SEPARATOR)
				.concat(owner.name().toLowerCase())
				.concat(FILE_SEPARATOR).concat(UUID.randomUUID().toString());
		LOG.debug("The file directory is {}", finalDirectory);
		return finalDirectory;
	}

	/**
	 * 
	 * <<This methods deletes the file with the url specified>>
	 * @param url
	 * @author Munal
	 * @since 27/03/2018, Modified In: @version, By @author
	 */
	public static void deleteFile(String url) {
		File directory = new File(url);
		if (!directory.exists()) {
			LOG.info("----------------->>>>>>>>Sorry no file found");
			return;
		}
		if (directory.delete()) {
			LOG.info("---------->>>>>>>>>>>>>>>Succesfully Deleted");
			return;
		}
		else
			LOG.info("---------->>>>>>>>>>>>>>>Not Deleted");
	}
}
