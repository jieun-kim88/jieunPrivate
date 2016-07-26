package com.example.naver.testtelephonyinfo.log;

import ch.qos.logback.classic.Level;
import ch.qos.logback.core.PropertyDefinerBase;
import com.example.naver.testtelephonyinfo.BuildConfig;

/**
 * Created by jieun.kim on 2016. 7. 25..
 */
public class FileLogLevelPropertyDefiner extends PropertyDefinerBase {
	@Override
	public String getPropertyValue() {

		if (BuildConfig.DEBUG) {
			return Level.DEBUG.toString();
		} else {
			return Level.OFF.toString();
		}
	}
}
