package com.gzz.createcode.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.gzz.createcode.mvc.model.Field;

import lombok.extern.slf4j.Slf4j;

/**
 * @功能说明 代码生成辅助类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */
@Slf4j
public final class Utils {

	/**
	 * @方法说明 拼接字段,getter,setter等
	 * @param list    字段列表
	 * @param prefix  前缀
	 * @param suffix  后缀
	 * @param varName 变量名
	 */
	public static StringBuilder addAllFieldWithVar(final List<Field> list, final String prefix, final String suffix, final String varName) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Field field = list.get(i);
			sb.append((i != 0 && i % 10 == 0) ? "\"); \r\n\t\t".concat(varName).concat(".append(\"") : "");
			sb.append(prefix.concat(field.getName()).concat(suffix));
		}
		return sb.delete(sb.length() - 1, sb.length());
	}

	public static StringBuilder addAllField(final List<Field> list, final String prefix, final String suffix) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Field field = list.get(i);
			sb.append((i != 0 && i % 10 == 0) ? "//太长换行 \r\n\t\t" : "");
			sb.append(prefix.concat(Utils.firstUpper(field.getName())).concat(suffix + ","));
		}
		return sb.delete(sb.length() - 1, sb.length());
	}

	public static StringBuilder addUpdateField(final List<Field> list, final String prefix, final String suffix) {
		List<Field> sta = new ArrayList<>(list);
		sta.add(list.get(0));
		sta.remove(0);
		return addAllField(sta, prefix, suffix);
	}

	/** @方法说明 生成指定个数问号两边加括号 */
	public static StringBuilder questionMark(final int size) {// 问号
		StringBuilder sb = new StringBuilder("(?");
		for (int i = 1; i < size; i++) {
			sb.append(",?");
		}
		return sb.append(")");
	}

	/** @方法说明 首字母大写 */
	public static String firstUpper(final String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
	}

	/** @方法说明 首字母小写 */
	public static String firstLower(final String word) {
		return word.substring(0, 1).toLowerCase() + word.substring(1, word.length());
	}

	/** @方法说明 实体类文件中是否增加java.util.Date的导入 */
	public static String dateImport(final List<Field> list) {
		return list.parallelStream().filter(i -> i.getType().equals("Date")).count() > 0 ? "\r\nimport java.util.Date;" : "";
	}

	/** @方法说明 去掉第一个单词 */
	public static String delFirWord(String tName) {
		return tName.substring(tName.indexOf("_") + 1);
	}

	/** @方法说明 实体类文件中是否增加java.math.BigDecimal的导入 */
	public static String bigImport(final List<Field> list) {
		return list.parallelStream().filter(i -> i.getType().equals("BigDecimal")).count() > 0 ? "\r\nimport java.math.BigDecimal;" : "";
	}

	/** @方法说明 主键数据类型 */
	public static String keyType(final List<Field> list) {
		return list.get(0).getType();
	}

	/** @方法说明 判断操作系统 */
	public static boolean isLinux() {
		return !System.getProperty("os.name").toLowerCase().startsWith("windows");
	}

	/** @方法说明 不同系统使用不同路径 */
	public static String path() {
		return isLinux() ? "/data/samba_root/code/" : "d:/";
//		return System.getProperty("user.dir") + "/src/main/java/";
	}

	/** @方法说明 Linux时为目录授权 */
	public static void chmod() {
		try {
			if (isLinux())
				Runtime.getRuntime().exec("chmod 777 -R " + path());
		} catch (IOException e) {
			log.info("设置权限时出现异常...", e);
		}
	}

	/** @方法说明 压缩目录 */
	public static void createZip(final String sourcePath, final String zipPath) {
		try {
			FileOutputStream fos = new FileOutputStream(zipPath);
			ZipOutputStream zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
			zos.close();
		} catch (FileNotFoundException e) {
			log.info("压缩文件...", e);
		} catch (IOException e) {
			log.info("压缩文件...", e);
		}

	}

	/** @方法说明 递规删除目录 */
	public static void delDir(final File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				delDir(subFile);
			}
		}
		file.delete();
	}

	/** @方法说明 压缩目录 */
	private static void writeZip(final File file, String parentPath, final ZipOutputStream zos) {
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
			log.info("压缩文件...", e);
		} catch (IOException e) {
			log.info("压缩文件...", e);
		}
	}

	/** @方法说明 扫描模板 */
	public static List<String> scanTemplate(final String packageName) {
		List<String> list = null;
		try {
			URL baseURL = Thread.currentThread().getContextClassLoader().getResource(packageName);
			if ("file".equals(baseURL.getProtocol())) {
				list = scanFile(baseURL);
			} else if ("jar".equals(baseURL.getProtocol())) {
				list = scanJar(baseURL, packageName);
			}
		} catch (Exception e) {
			log.info("压缩文件...", e);
		}
		return list;
	}

	/** @方法说明 扫描jar包 */
	private static List<String> scanJar(final URL baseURL, final String packageName) throws IOException {
		List<String> classList = new ArrayList<>();
		JarFile jar = ((JarURLConnection) baseURL.openConnection()).getJarFile();
		Enumeration<JarEntry> entries = jar.entries();
		while (entries.hasMoreElements()) {
			String urlName = entries.nextElement().getName();
			if (urlName.startsWith(packageName) && (urlName.endsWith("java") || urlName.endsWith("vue") || urlName.endsWith("js") || urlName.endsWith("xml"))) {
				classList.add(urlName.replace(packageName + "/", ""));
			}
		}
		return classList;
	}

	/** @方法说明 扫描源文件 */
	private static List<String> scanFile(final URL path) throws Exception {
		File dir = new File(URLDecoder.decode(path.getFile(), "UTF-8"));
		if (!dir.exists() || !dir.isDirectory()) {
			throw new Exception("没有找到对应的包");
		}
		List<String> fileList = new ArrayList<>();
		LinkedList<File> dirs = new LinkedList<>();
		dirs.add(dir);
		while (!dirs.isEmpty()) {
			File son = dirs.removeFirst();
			if (son.isDirectory()) {
				for (File childFile : son.listFiles()) {
					dirs.add(childFile);
				}
			} else if (son.getName().endsWith("java") || son.getName().endsWith("vue") || son.getName().endsWith("js") || son.getName().endsWith("xml")) {
				fileList.add(son.getAbsolutePath().substring(dir.getAbsolutePath().length() + 1).replace("\\", "/"));
			}
		}
		return fileList;
	}

	public static StringBuilder addAllSqlFields(final List<Field> list, final String prefix, final String suffix) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			Field field = list.get(i);
			sb.append((i != 0 && i % 10 == 0) ? " \r\n\t\t" : "");
			sb.append(prefix.concat((field.getName())).concat(suffix + ","));
		}
		return sb.delete(sb.length() - 1, sb.length());
	}
}
