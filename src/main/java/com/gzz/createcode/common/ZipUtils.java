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
	@SuppressWarnings("unused")
	private static Log logger = LogFactory.getLog(ZipUtils.class);

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
