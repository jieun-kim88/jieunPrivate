package com.example.naver.testtelephonyinfo;

import android.content.Context;
import android.telephony.TelephonyManager;

/**
 * Created by jieun.kim on 2016. 7. 22..
 */
public class TelephonyUtil {
	public static final String NULL_TEXT = "NULL";

	private TelephonyUtil() {
	}

	public static String getSimOperator() {
		TelephonyManager tm = findTelephonyManager();
		String operation = NULL_TEXT;
		if (tm != null) {
			operation = tm.getSimOperator();
		}
		return operation;
	}

	public static String getSimCountry() {
		TelephonyManager tm = findTelephonyManager();
		String country = NULL_TEXT;
		if (tm != null) {
			String countryIso = tm.getSimCountryIso();
			if (countryIso != null) {
				country = countryIso.toUpperCase();
			}
		}
		return country;
	}

	public static String getNetworkCountry() {
		TelephonyManager tm = findTelephonyManager();
		String country = NULL_TEXT;
		if (tm != null) {
			String countryIso = tm.getNetworkCountryIso();
			if (countryIso != null) {
				country = countryIso.toUpperCase();
			}
		}
		return country;
	}

	public static String getUsimType() {
		TelephonyManager tm = findTelephonyManager();

		int simState = tm.getSimState();
		String type = NULL_TEXT;
		switch (simState) {
			case 0:
				type = "SIM_STATE_UNKNOWN";
				break;
			case 1:
				type = "SIM_STATE_ABSENT";
				break;
			case 2:
				type = "SIM_STATE_PIN_REQUIRED";
				break;
			case 3:
				type = "SIM_STATE_PUK_REQUIRED ";
				break;
			case 4:
				type = "SIM_STATE_NETWORK_LOCKED";
				break;
			case 5:
				type = "SIM_STATE_READY";
				break;

		}
		return type;
	}

	private static TelephonyManager findTelephonyManager() {
		Context appContext = TestApplication.getAppContext();
		return (TelephonyManager)appContext.getSystemService(Context.TELEPHONY_SERVICE);
	}

}

