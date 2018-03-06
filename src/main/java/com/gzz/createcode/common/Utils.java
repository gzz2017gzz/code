package com.gzz.createcode.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.gzz.createcode.mvc.model.Field;

/**
 * @功能描述:代码生成辅助类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */

public class Utils {
	private static Log logger = LogFactory.getLog(Utils.class);
	private static String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

	/**
	 * @方法说明:name前后各加一个字符，中间用逗号分隔，去掉最后的逗号.去掉第一个字段(ID)
	 */
	public static StringBuilder add(List<Field> list, String start, String end, int word) {
		StringBuilder sb = null;
		if (word == 1)
			sb = add(list, start, end);
		if (word == 2)
			sb = addLower(list, start, end);
		return sb.delete(0, sb.indexOf(",") + 1);
	}

	public static StringBuilder add(List<Field> list, String start, String end) {
		StringBuilder sb = new StringBuilder();
		list.forEach(item -> sb.append(start + Utils.firstUpper(item.getName().toLowerCase()) + end));
		return sb.delete(sb.length() - 1, sb.length());
	}

	public static StringBuilder addLower(List<Field> list, String start, String end) {
		StringBuilder sb = new StringBuilder();
		list.forEach(item -> sb.append(start + item.getName() + end));
		return sb.delete(sb.length() - 1, sb.length());
	}

	/**
	 * @方法说明:生成指字问号个数中间用豆号分隔两边加括号
	 */
	public static StringBuilder add(int count) {
		StringBuilder sb = new StringBuilder("(");
		for (int i = 0; i < count; i++) {
			sb.append("?,");
		}
		return sb.delete(sb.length() - 1, sb.length()).append(")");
	}

	/**
	 * @方法说明: 类注释
	 */
	public static StringBuilder classNote(String author, String name) {
		return new StringBuilder().append("\r\n").append("\r\n/**").append("\r\n * @类说明:" + name).append("\r\n * @author:" + author).append("\r\n * @date:" + time).append("\r\n **/");
	}

	/**
	 * @方法说明: 方法注释
	 */
	public static StringBuilder methodNote(String method_name) {
		return new StringBuilder().append("\r\n").append("\r\n	/**").append("\r\n	 * @方法说明:" + method_name).append("\r\n	 **/");
	}

	/**
	 * @方法说明: 页面注释
	 */
	public static StringBuilder pageNote(String CNName, String author) {
		return new StringBuilder("/*" + CNName + ",作者:" + author + ",日期:" + time + "*/");
	}

	/**
	 * @方法说明: 首字母大写
	 */
	public static String firstUpper(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
	}

	/**
	 * @方法说明: 首字母小写
	 */
	public static String firstLower(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
	}

	/**
	 * @方法说明: 写文件
	 */
	public static void write(String path, StringBuilder sb) {
		// File file = new File(path);
		// if (file.exists()) {
		// logger.error("文件已经存在:" + path);
		// throw new RuntimeException();
		// }
		createDir(path);
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));
			writer.write(sb.toString());
			writer.close();
		} catch (IOException e) {
			logger.info("写入文件时出现异常 " + path);
			e.printStackTrace();
		}
	}

	/**
	 * @方法说明: 创建文件夹
	 */
	public static void createDir(String path) {
		File file = new File(path);
		File parent = file.getParentFile();
		if (parent != null && !parent.exists()) {
			parent.mkdirs();
		}
	}

	/**
	 * @方法说明: 实体类文件中是否增加java.util.Date的导入
	 */
	public static String dateImport(List<Field> list) {
		String mm = "";
		for (Field field : list) {
			if (field.getType().equals("Date")) {
				mm = "\r\nimport java.util.Date;";
				break;
			}
		}
		return mm;
	}

	/**
	 * @方法说明: 实体类文件中是否增加java.math.BigDecimal的导入
	 */
	public static String bigImport(List<Field> list) {
		String mm = "";
		for (Field field : list) {
			if (field.getType().equals("BigDecimal")) {
				mm = "\r\nimport java.math.BigDecimal;";
				break;
			}
		}
		return mm;
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

	public static String getBasePath() {
		return isLinux() ? "/data/samba_root/code/" : "d:/";
	}

	public static void setPermissions() {
		if (isLinux()) {
			try {
				Runtime.getRuntime().exec("chmod 777 -R " + getBasePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
