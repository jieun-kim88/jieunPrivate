package com.example.naver.testtelephonyinfo.log;

import org.apache.commons.lang3.StringUtils;

import ch.qos.logback.classic.android.LogcatAppender;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;

/**
 * Created by jieun.kim on 2016. 7. 25..
 */
public class DefaultAppender extends LogcatAppender {
	@Override
	public void append(ILoggingEvent event) {
		if (!isStarted()) {
			return;
		}

	}

	private String getMessage(ILoggingEvent event) {
		String stackTrace = ThrowableProxyUtil.asString(event.getThrowableProxy());

		if (StringUtils.isBlank(stackTrace)) {
			return event.getMessage();
		} else {
			return String.format("%s\n%s", event.getMessage(), stackTrace);

		}
	}
}
