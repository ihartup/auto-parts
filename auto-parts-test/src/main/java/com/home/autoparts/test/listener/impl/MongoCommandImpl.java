package com.home.autoparts.test.listener.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.home.autoparts.test.listener.api.MongoCommand;

@Service
public class MongoCommandImpl implements MongoCommand{
	private static final Logger logger = LoggerFactory.getLogger(MongoCommandImpl.class);
	
	private static final String START_COMMAND_TEMPLATE = "cmd /c start %s/mongod.exe --noauth --dbpath %s --port %d";
	private static final String STOP_COMMAND_TEMPLATE = "%s/mongo.exe admin --eval \"db.shutdownServer()\"";
	private boolean initialized = false;
	private int port;
	private String dbPath;
	private String mongoInstancePath;
	
	@Override
	public void startMongo() {
		String startCommand = String.format(START_COMMAND_TEMPLATE, mongoInstancePath, dbPath, port);
		executeCommand(startCommand);
	}
	
	@Override
	public void stopMongo() {
		String shutdownCommand = String.format(STOP_COMMAND_TEMPLATE, mongoInstancePath);
		executeCommand(shutdownCommand);
	}
	
	private void executeCommand(String command) {
		if(!initialized)  {
			throw new IllegalStateException("The mongo command client was not initialized. Call the init method first");
		}
		
		File dir = new File(mongoInstancePath);
		try {
			Process pr = Runtime.getRuntime().exec(command, null, dir);
		} catch (IOException e) {
			logger.error("Error was caught while trying to execute command : " + command, e);
		}
	}

	@Override
	public void init(String dbPath, int port, String dbInstancePath) {
		this.dbPath = dbPath;
		this.port  = port;
		this.mongoInstancePath = dbInstancePath;
		initialized = true;
	}
}
