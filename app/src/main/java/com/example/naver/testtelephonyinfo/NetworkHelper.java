package com.example.naver.testtelephonyinfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by jieun.kim on 2016. 7. 29..
 */
public class NetworkHelper {
	private static final Logger logger = LoggerFactory.getLogger(NetworkHelper.class);

	public static boolean isWifiNetwork(Context context) {
		ConnectivityManager connectivityManager =
			(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		}

		NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnected() && networkInfo.isAvailable()) {
			switch (networkInfo.getType()) {
				case ConnectivityManager.TYPE_WIFI:
					return true;
				default:
					return false;
			}
		} else {
			logger.debug("no available network");
		}

		return false;
	}
}