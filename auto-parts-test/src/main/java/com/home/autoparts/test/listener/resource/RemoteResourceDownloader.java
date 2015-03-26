package com.home.autoparts.test.listener.resource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import com.home.autoparts.test.listener.api.MongoConstants;

@Component
public class RemoteResourceDownloader {
	
	public void downloadArchive(String remotePath) {
		
		InputStream inputStream = null;
		OutputStream output = null;
		try {
			URL url = new URL(remotePath);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			// Check for errors
			int responseCode = con.getResponseCode();
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
			byte[] buffer = new byte[8 * 1024];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) > 0) {
			    output.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(output);
			IOUtils.closeQuietly(inputStream);
		}
	}
}
