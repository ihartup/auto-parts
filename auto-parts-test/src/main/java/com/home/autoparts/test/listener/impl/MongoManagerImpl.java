package com.home.autoparts.test.listener.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.home.autoparts.test.listener.api.MongoCommand;
import com.home.autoparts.test.listener.api.MongoConstants;
import com.home.autoparts.test.listener.api.MongoManager;
import com.home.autoparts.test.listener.resource.RemoteResourceDownloader;
import com.home.autoparts.test.listener.resource.ResourceArchiver;
import com.home.autoparts.test.listener.resource.ResourceHandler;

public class MongoManagerImpl implements MongoManager {
	
	private static final Logger logger = LoggerFactory.getLogger(MongoManagerImpl.class);

	@Autowired
	private ResourceHandler resFinder;
	
	@Autowired
	private RemoteResourceDownloader resDownloader;
	
	@Autowired
	private ResourceArchiver resArchiver;
	
	@Autowired
	private MongoCommand mongoCommand;
	
	private File mongoDistribDir;
	private File mongoTargetDir;
	private File mongoInstanceDir;
	
	public void init() {
		logger.debug("Initializing MongoManager");
		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		mongoDistribDir = new File(tempDir, MongoConstants.MONGO_DISTRIB_FOLDER);
		mongoTargetDir = new File(tempDir, MongoConstants.MONGO_INSTANCE_FOLDER);
		mongoInstanceDir = new File(mongoTargetDir,System.getProperty(MongoConstants.MONGO_INSTANCE_NAME_PROPERTY) + File.separator + "bin");
		File dbDataFile = new File(mongoTargetDir, MongoConstants.DATA_DB);
		dbDataFile.mkdirs();
		mongoCommand.init(dbDataFile.getAbsolutePath(), 27017, mongoInstanceDir.getAbsolutePath());
	}

	@Override
	public void startDatabase() {
		logger.info("Starting mongo database for tests...");
		// test if mongo distribution is present in resources
		String distribName = System.getProperty(MongoConstants.MONGO_DISTRIB_NAME_PROPERTY);
		boolean hasMongoDistrib = resFinder.hasTempResource(MongoConstants.MONGO_DISTRIB_FOLDER, distribName);
		if (hasMongoDistrib) {
			// check if it is already unzipped and start it
			unzipAndStart(distribName);

		} else {
			// download the archive
			String distribRemoteURL = System.getProperty(MongoConstants.MONGO_REMOTE_URL);
			resDownloader = new RemoteResourceDownloader();
			resDownloader.downloadArchive(distribRemoteURL);
			// unzip and start it
			unzipAndStart(distribName);
		}
		
	}

	private void unzipAndStart(String distribName) {
		File zipResource = new File(mongoDistribDir, distribName);
		resArchiver.unzip(zipResource.getAbsolutePath(), mongoTargetDir.getAbsolutePath());
		mongoCommand.startMongo();
		delay(5);
	}

	@Override
	public void stopDatabase() {
		logger.info("Stopping mongo database...");
		mongoCommand.stopMongo();
		//allow it to shutdown - make this nicer
		delay(3);
	}
	
	private void delay(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			logger.error("Interupted exception was caught", e);
		}
	}

	@Override
	public void cleanup() {
		try {
			FileUtils.deleteDirectory(mongoTargetDir);
		} catch (IOException e) {
			logger.error("The mongo instance folder could not be deleted", e);
		}

	}

}
