package com.example.naver.testtelephonyinfo;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by jieun.kim on 2016. 7. 22..
 */
public class TestApplication extends MultiDexApplication {
	private static Context context;

	public void onCreate() {
		super.onCreate();
		TestApplication.context = getApplicationContext();
	}

	public static Context getAppContext() {
		return TestApplication.context;
	}

}
