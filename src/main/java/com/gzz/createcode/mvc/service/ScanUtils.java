package com.gzz.createcode.mvc.service;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import com.google.common.collect.Lists;

public class ScanUtils {

	public static List<String> scanTemplate(String packageName) {
		List<String> list = Lists.newArrayList();
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

	private static List<String> scanJar(URL baseURL, String packageName) throws IOException {
		List<String> classList = Lists.newArrayList();
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

	private static List<String> scanFile(URL path) throws Exception {
		File dir = new File(URLDecoder.decode(path.getFile(), "UTF-8"));
		if (!dir.exists() || !dir.isDirectory()) {
			throw new Exception("没有找到对应的包");
		}
		List<String> fileList = Lists.newArrayList();
		LinkedList<File> dirs = Lists.newLinkedList();
		dirs.add(dir);
		while (!dirs.isEmpty()) {
			File son = dirs.removeFirst();
			if (son.isDirectory()) {
				for (File childFile : son.listFiles()) {
					dirs.add(childFile);
				}
			} else if (son.getName().toLowerCase().endsWith("java") || son.getName().toLowerCase().endsWith("vue")) {
				fileList.add(son.getAbsolutePath().substring(dir.getAbsolutePath().length() + 1).replace("\\", "/"));
			}
		}
		return fileList;
	}

}
