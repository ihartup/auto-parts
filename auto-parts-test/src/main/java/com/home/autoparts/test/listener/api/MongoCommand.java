package com.home.autoparts.test.listener.api;

public interface MongoCommand {
	void startMongo();
	void stopMongo();
	void init(String dbPath, int port, String dbInstancePath);
}
