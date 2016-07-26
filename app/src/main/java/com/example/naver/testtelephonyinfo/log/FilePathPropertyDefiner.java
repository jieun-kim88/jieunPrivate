package com.example.naver.testtelephonyinfo.log;

import java.io.File;

import ch.qos.logback.core.PropertyDefinerBase;
import com.example.naver.testtelephonyinfo.TestApplication;

/**
 * Created by jieun.kim on 2016. 7. 25..
 */
public class FilePathPropertyDefiner extends PropertyDefinerBase {
	@Override
	public String getPropertyValue() {
		File externalStorageDirectory = TestApplication.getAppContext().getExternalFilesDir(null);
		if (externalStorageDirectory != null) {
			return String.format("%s/log", externalStorageDirectory.getAbsolutePath());
		} else {
			return "";
		}
	}
}
