package com.home.autoparts.test.listener.resource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.springframework.stereotype.Component;

@Component
public class ResourceHandler {
	private static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	
	public boolean hasResource(String resourceName) {
		URL resURL = this.getClass().getResource(resourceName);
		return resURL != null;
	}
	
	public boolean hasGlobalResource(String dirPath, String resourceName) {
		File dir =  new File(dirPath);
		if(!dir.exists()) {
			throw new IllegalArgumentException("Folder " + dirPath + " does not exist");
		}
		if(!dir.isDirectory()) {
			throw new IllegalArgumentException("Resource at " + dirPath + " is not a folder");
		}
		File resource = new File(dir, resourceName);
		return resource.exists() && resource.isFile();
	}
	
	public boolean hasTempResource(String resourceName){
		return hasGlobalResource(TEMP_DIR, resourceName);
	}
	
	public boolean hasTempResource(String parentFolder, String resourceName){
		File parentDir =  new File(TEMP_DIR, parentFolder);
		return hasGlobalResource(parentDir.getAbsolutePath(), resourceName);
	}
	
	public InputStream getResourceAsStream(String resourceName) {
		return this.getClass().getResourceAsStream(resourceName);
	}
}
