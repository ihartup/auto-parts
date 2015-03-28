package com.home.autoparts.test.listener.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.home.autoparts.test.listener.api.MongoConstants;

@Component
public class RemoteResourceDownloader {
	private static final Logger logger = LoggerFactory.getLogger(RemoteResourceDownloader.class);
	
	public void downloadArchive(String remotePath) {
		
		InputStream inputStream = null;
		OutputStream output = null;
		try {
			URL url = new URL(remotePath);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			// Check for errors
			int responseCode = con.getResponseCode();
			int contentLength = con.getContentLength() / 1024; //in KB
			if (responseCode == HttpURLConnection.HTTP_OK) {
			    inputStream = con.getInputStream();
			} else {
			    inputStream = con.getErrorStream();
			}
			String distribName = System.getProperty(MongoConstants.MONGO_DISTRIB_NAME_PROPERTY);
			File tmpDir = new File(System.getProperty("java.io.tmpdir"), MongoConstants.MONGO_DISTRIB_FOLDER);
			tmpDir.mkdir();
			File mongoZip = new File(tmpDir, distribName);
			output = new FileOutputStream(mongoZip);

			// Process the response
			System.out.println(String.format("Downloading mongo distribution: %d", contentLength));
			byte[] buffer = new byte[8 * 1024];
			int totalBytesRead = 0;
			int bytesRead;
			int i=0;
			while ((bytesRead = inputStream.read(buffer)) > 0) {
			    output.write(buffer, 0, bytesRead);
			    totalBytesRead += bytesRead;
			    if(i++%2 == 0) {
			    	updateProgress(totalBytesRead/1024, contentLength);	
			    }
			}
			updateProgress(contentLength,contentLength);
		} catch (IOException e) {
			logger.error("Error was caught whil downloading mongo distribution", e);
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(inputStream);
		}
	}
	
	private void updateProgress(int read, int totalSize) {

	    System.out.print("\r[");
	    System.out.print(read +  "KB / " + totalSize + " KB");
	    System.out.print("]");
	  }
}
