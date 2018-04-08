package com.gzz.createcode.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.common.collect.Lists;
import com.gzz.createcode.mvc.model.Field;

/**
 * @功能描述:代码生成辅助类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */

public class Utils {
	private static Log logger = LogFactory.getLog(Utils.class);
	private static String time;

	public static void setTime() {
		time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * @param list字段列表
	 * @param prefix前缀
	 * @param suffix后缀
	 * @param noId不包括主键
	 * @param isUp首字母大写
	 */
	public static StringBuilder add(List<Field> list, String prefix, String suffix, boolean noId, String wrap) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Field field = list.get(i);
			sb.append((i != 0 && i % 10 == 0) ? "\"); \r\n\t\t".concat(wrap).concat(".append(\"") : "");
			sb.append(prefix.concat(field.getName()).concat(suffix));
		}
		return noId ? sb.delete(0, sb.indexOf(",") + 1).delete(sb.length() - 1, sb.length()) : sb.delete(sb.length() - 1, sb.length());
	}

	public static StringBuilder add(List<Field> list, String prefix, String suffix, boolean noId) {
		StringBuilder sb = new StringBuilder();
		list.forEach(item -> sb.append(prefix.concat(Utils.firstUpper(item.getName())).concat(suffix)));
		return noId ? sb.delete(0, sb.indexOf(",") + 1).delete(sb.length() - 1, sb.length()) : sb.delete(sb.length() - 1, sb.length());
	}

	/**
	 * @方法说明:生成指个数问号两边加括号
	 */
	public static StringBuilder add(List<Field> list) {
		StringBuilder sb = new StringBuilder("(");
		list.forEach(item -> sb.append("?,"));
		return sb.delete(sb.length() - 1, sb.length()).append(")");
	}

	/**
	 * @方法说明: 类注释
	 */
	public static String classNote(String auth, String name) {
		return String.format("\r\n\r\n/**\r\n * @类说明:%s\r\n * @author:%s\r\n * @date:%s\r\n **/", name, auth, time);
	}

	/**
	 * @方法说明: 方法注释
	 */
	public static String methodNote(String name) {
		return String.format("\r\n\r\n	/**\r\n	 * @方法说明:%s\r\n	 **/", name);
	}

	/**
	 * @方法说明: 页面注释
	 */
	public static String pageNote(String cName, String auth) {
		return String.format("/*%s,作者:%s,日期:%s*/", cName, auth, time);
	}

	/**
	 * @方法说明: 首字母大写
	 */
	public static String firstUpper(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
	}

	/**
	 * @方法说明: 首字母小写
	 */
	public static String firstLower(String word) {
		return word.substring(0, 1).toLowerCase() + word.substring(1, word.length());
	}

	/**
	 * @方法说明: 写文件
	 */
	public static void write(String path, StringBuilder sb) {
		try {
			Files.createDirectories(Paths.get(path).getParent());
			Files.write(Paths.get(path), sb.toString().getBytes("UTF-8"));
		} catch (IOException e) {
			logger.info("写入文件时出现异常 " + path);
			e.printStackTrace();
		}
	}

	/**
	 * @方法说明: 实体类文件中是否增加java.util.Date的导入
	 */
	public static String dateImport(List<Field> list) {
		return list.parallelStream().filter(i -> i.getType().equals("Date")).count() > 0 ? "\r\nimport java.util.Date;" : "";
	}

	/**
	 * @方法说明: 去掉第一个单词
	 */
	public static String delFirWord(String tName) {
		return tName.substring(tName.indexOf("_") + 1);
	}

	/**
	 * @方法说明: 实体类文件中是否增加java.math.BigDecimal的导入
	 */
	public static String bigImport(List<Field> list) {
		return list.parallelStream().filter(i -> i.getType().equals("BigDecimal")).count() > 0 ? "\r\nimport java.math.BigDecimal;" : "";
	}

	/**
	 * @方法说明: 主键数据类型
	 */
	public static String keyType(List<Field> list) {
		return list.get(0).getType();
	}

	public static boolean isLinux() {
		return !System.getProperty("os.name").toLowerCase().startsWith("windows");
	}

	public static String path() {
		return isLinux() ? "/data/samba_root/code/" : "d:/";
	}

	public static void chmod() {
		try {
			if (isLinux())
				Runtime.getRuntime().exec("chmod 777 -R " + path());
		} catch (IOException e) {
			logger.info("设置权限时出现异常 !");
			e.printStackTrace();
		}
	}
	//////////////////////////////////////////////
	
	public static boolean isEmptyString(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	public static <T> boolean isEmptyList(final List<T> list) {
		return list == null || list.size() == 0;
	}

	public static boolean notEmptyString(final CharSequence cs) {
		return cs != null && cs.length() >= 0;
	}

	public static <T> boolean notEmptyList(final List<T> list) {
		return list != null && list.size() >= 0;
	}

	public static void main(String[] args) {
		logger.info(isEmptyString(""));
		logger.info(isEmptyString(null));
		List<Integer> list = Lists.newArrayList();
		logger.info(isEmptyList(null));
		logger.info(isEmptyList(list));
		list.add(1);
		logger.info(isEmptyList(list));
		logger.info(notEmptyList(list));
	}
	/////////////////////////////////
	public static void createZip(String sourcePath, String zipPath) {
		try {
			FileOutputStream fos = new FileOutputStream(zipPath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
			zos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void delDir(File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				delDir(subFile);
			}
		}
		file.delete();
	}

	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
		try {
			if (file.exists()) {
				if (file.isDirectory()) {
					parentPath = parentPath + file.getName() + File.separator;
					File[] files = file.listFiles();
					if (files.length != 0) {
						for (File f : files) {
							writeZip(f, parentPath, zos);
						}
					} else {
						zos.putNextEntry(new ZipEntry(parentPath));
					}
				} else {
					FileInputStream fis = null;
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}
					fis.close();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
