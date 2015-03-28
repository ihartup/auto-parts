package com.home.autoparts.test.listener.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ResourceArchiver {
	private static final Logger logger = LoggerFactory.getLogger(ResourceArchiver.class);
	
	public void unzip(String zipResource, String destDir) {
		File dir = new File(destDir);
		// create output directory if it doesn't exist
		if (!dir.exists()) {
			dir.mkdirs();
		}

		FileInputStream fis = null;
		ZipInputStream zis = null;
		// buffer for read and write data to file
		byte[] buffer = new byte[1024];
		try {
			fis = new FileInputStream(zipResource);
			zis = new ZipInputStream(fis);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				String fileName = ze.getName();
				File newFile = new File(destDir + File.separator + fileName);
				// create directories for sub directories in zip
				new File(newFile.getParent()).mkdirs();
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				// close this ZipEntry
				zis.closeEntry();
				ze = zis.getNextEntry();
			}
			// close last ZipEntry
			zis.closeEntry();
		} catch (IOException e) {
			logger.error("Error was caught while unzipping file: " + zipResource, e);
		} finally {
			IOUtils.closeQuietly(zis);
			IOUtils.closeQuietly(fis);
		}
	}
}
