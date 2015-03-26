package com.home.autoparts.test.listener;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.RunListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.home.autoparts.test.listener.api.MongoManager;

public class MongoTestListener extends RunListener {
	
	private MongoManager mongoManager;
	
	
	public MongoTestListener() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("app-test.xml");
		mongoManager = appContext.getBean("mongoManager", MongoManager.class);
	}

	@Override
	public void testRunStarted(Description description) throws Exception {
		mongoManager.startDatabase();
	}

	@Override
	public void testRunFinished(Result result) throws Exception {
		mongoManager.stopDatabase();
		mongoManager.cleanup();
	}
}
