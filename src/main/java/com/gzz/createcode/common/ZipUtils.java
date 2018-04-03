package com.gzz.createcode.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ZipUtils {
	private static Log logger = LogFactory.getLog(ZipUtils.class);

	public static void createZip(String sourcePath, String zipPath) {
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		try {
			fos = new FileOutputStream(zipPath);
			zos = new ZipOutputStream(fos);
			writeZip(new File(sourcePath), "", zos);
			return;
		} catch (FileNotFoundException e) {
			logger.error("创建ZIP文件失败", e);
		} finally {
			try {
				if (zos != null) {
					zos.close();
				}
			} catch (IOException e) {
				logger.error("创建ZIP文件失败", e);
			}
		}
	}

	private static void writeZip(File file, String parentPath, ZipOutputStream zos) {
		if (file.exists()) {
			if (file.isDirectory()) {
				parentPath = parentPath + file.getName() + File.separator;
				File[] files = file.listFiles();
				if (files.length != 0) {
					for (File f : files) {
						writeZip(f, parentPath, zos);
					}
				} else {
					try {
						zos.putNextEntry(new ZipEntry(parentPath));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else {
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(file);
					ZipEntry ze = new ZipEntry(parentPath + file.getName());
					zos.putNextEntry(ze);
					byte[] content = new byte[1024];
					int len;
					while ((len = fis.read(content)) != -1) {
						zos.write(content, 0, len);
						zos.flush();
					}
				} catch (FileNotFoundException e) {
					logger.error("创建ZIP文件失败", e);
				} catch (IOException e) {
					logger.error("创建ZIP文件失败", e);
				} finally {
					try {
						if (fis != null) {
							fis.close();
						}
					} catch (IOException e) {
						logger.error("创建ZIP文件失败", e);
					}
				}
			}
		}
	}
}
