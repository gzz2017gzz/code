package com.gzz.createcode.common;

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

/**
 * @功能说明 代码生成辅助类
 * @author gzz_gzz@163.com
 * @date 2018-02-15
 */

public class Utils {

	/**
	 * @param list   字段列表
	 * @param prefix 前缀
	 * @param suffix 后缀
	 * @param noId   不包括主键
	 * @param wrap   换行
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
	 * @功能说明 生成指定个数问号两边加括号
	 */
	public static StringBuilder add(List<Field> list) {
		StringBuilder sb = new StringBuilder("(");
		list.forEach(item -> sb.append("?,"));
		return sb.delete(sb.length() - 1, sb.length()).append(")");
	}

	/**
	 * @方法说明 首字母大写
	 */
	public static String firstUpper(String word) {
		return word.substring(0, 1).toUpperCase() + word.substring(1, word.length());
	}

	/**
	 * @方法说明 首字母小写
	 */
	public static String firstLower(String word) {
		return word.substring(0, 1).toLowerCase() + word.substring(1, word.length());
	}

	/**
	 * @方法说明 实体类文件中是否增加java.util.Date的导入
	 */
	public static String dateImport(List<Field> list) {
		return list.parallelStream().filter(i -> i.getType().equals("Date")).count() > 0 ? "\r\nimport java.util.Date;" : "";
	}

	/**
	 * @方法说明 去掉第一个单词
	 */
	public static String delFirWord(String tName) {
		return tName.substring(tName.indexOf("_") + 1);
	}

	/**
	 * @方法说明 实体类文件中是否增加java.math.BigDecimal的导入
	 */
	public static String bigImport(List<Field> list) {
		return list.parallelStream().filter(i -> i.getType().equals("BigDecimal")).count() > 0 ? "\r\nimport java.math.BigDecimal;" : "";
	}

	/**
	 * @方法说明 主键数据类型
	 */
	public static String keyType(List<Field> list) {
		return list.get(0).getType();
	}

	/**
	 * @方法说明 判断操作系统
	 */
	public static boolean isLinux() {
		return !System.getProperty("os.name").toLowerCase().startsWith("windows");
	}

	/**
	 * @方法说明 不同系统使用不同路径
	 */
	public static String path() {
		return isLinux() ? "/data/samba_root/code/" : "d:/";
//		return System.getProperty("user.dir") + "/src/main/java/";
	}

	/**
	 * @方法说明 Linux时为目录授权
	 */
	public static void chmod() {
		try {
			if (isLinux())
				Runtime.getRuntime().exec("chmod 777 -R " + path());
		} catch (IOException e) {
//			logger.info("设置权限时出现异常 !");
			e.printStackTrace();
		}
	}

	/**
	 * @方法说明 压缩目录
	 */
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

	/**
	 * @方法说明 递规删除目录
	 */
	public static void delDir(File file) {
		if (file.isDirectory()) {
			for (File subFile : file.listFiles()) {
				delDir(subFile);
			}
		}
		file.delete();
	}

	/**
	 * @方法说明 压缩目录
	 */
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

	/**
	 * @方法说明 扫描模板
	 */
	public static List<String> scanTemplate(String packageName) {
		List<String> list = new ArrayList<>();
		try {
			URL baseURL = Thread.currentThread().getContextClassLoader().getResource(packageName);
			if ("file".equals(baseURL.getProtocol())) {
				list = scanFile(baseURL);
			} else if ("jar".equals(baseURL.getProtocol())) {
				list = scanJar(baseURL, packageName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * @方法说明 扫描jar包
	 */
	private static List<String> scanJar(URL baseURL, String packageName) throws IOException {
		List<String> classList = new ArrayList<>();
		JarFile jar = ((JarURLConnection) baseURL.openConnection()).getJarFile();
		Enumeration<JarEntry> entries = jar.entries();
		while (entries.hasMoreElements()) {
			String urlName = entries.nextElement().getName();
			if (urlName.startsWith(packageName) && (urlName.endsWith("java") || urlName.endsWith("vue"))) {
				classList.add(urlName.replace(packageName + "/", ""));
			}
		}
		return classList;
	}

	/**
	 * @方法说明 扫描源文件
	 */
	private static List<String> scanFile(URL path) throws Exception {
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
			} else if (son.getName().endsWith("java") || son.getName().endsWith("vue")) {
				fileList.add(son.getAbsolutePath().substring(dir.getAbsolutePath().length() + 1).replace("\\", "/"));
			}
		}
		return fileList;
	}
}
