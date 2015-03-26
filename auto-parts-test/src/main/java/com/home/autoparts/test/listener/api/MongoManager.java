package com.home.autoparts.test.listener.api;

public interface MongoManager {
	void startDatabase();
	void stopDatabase();
	void cleanup();
}
