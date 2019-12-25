package com.gzz.createcode.common.base;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.List;

import com.google.common.base.Joiner;

/**
 * @类说明 代码工具
 * @author https://www.jianshu.com/u/3bd57d5f1074
 * @date 2019-12-24 10:50:00
 */
public class SqlUtil {

	/**
	 * @方法说明 用于批操作显示SQL
	 */
	public <T> String getField(Class<T> clazz) {
		StringBuilder sql = new StringBuilder();
		Field[] fields = clazz.getDeclaredFields();

		for (Field field : fields) {
			sql.append("t." + field.getName() + ",");
		}
		sql.deleteCharAt(sql.length() - 1);
		return sql.toString();
	}

	/**
	 * @方法说明 数据库中执行的SQL语句
	 */
	public static String showSql(String sql, Object[] obj) {
		String param;
		for (int j = 0; null != obj && j < obj.length; j++) {
			param = "null";
			if (null != obj[j]) {
				String cname = obj[j].getClass().getName();
				if (cname.contains("Date") || cname.contains("Timestamp")) {
					param = "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(obj[j]) + "'";
				} else if (cname.contains("String")) {
					param = "'" + (String) obj[j] + "'";
				} else {
					param = obj[j].toString();
				}
			}
			sql = sql.replaceFirst("[?]", param);
		}
		return sql;
	}

	/**
	 * @方法说明:把组数拼接成IN语句
	 */
	public static String ArrayToIn(Long ids[]) {
		return " IN (" + Joiner.on(",").join(ids) + ")";
	}

	/**
	 * @方法说明 把组数拼接成IN语句
	 */
	public static String ArrayToIn(Integer ids[]) {
		return " IN (" + Joiner.on(",").join(ids) + ")";
	}

	/**
	 * @方法说明 把组数拼接成IN语句
	 */
	public static String ArrayToIn(String ids[]) {
		return " IN ('" + Joiner.on("','").join(ids) + "')";
	}

	/**
	 * @方法说明 把List拼接成IN语句(数值型)
	 */
	public static String ArrayToIn(List<?> ids) {// 数值IN字符窜
		return " ('" + Joiner.on("','").join(ids) + "')";
	}
}
