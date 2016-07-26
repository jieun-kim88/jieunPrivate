package com.example.naver.testtelephonyinfo;

import android.content.Context;
import android.telephony.TelephonyManager;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class InfoMessageHelper {

	public static final String ROMMING_TEXT = "Romming";
	public static final String NOT_ROMMING_TEXT = "Not Romming";
	public static final String TM_NETWORK_TAG = "TM_NETWORK/ ";
	public static final String TM_SIM_TAG = "TM_SIM/ ";
	public static final String TM_DATE_STATE_TAG = "TM_DATE_STATE/ ";
	public static final String TM_ROMMING_TAG = "TM_ROMMING_STATE/ ";
	public static final String GG_SERVICE_TAG = "GG_SERVICE_AVAILABLE/ ";

	public static String getRomingInfo() {
		TelephonyManager tm = findTelephonyManager();
		boolean isRomming = tm.isNetworkRoaming();
		if (isRomming) {
			return ROMMING_TEXT;
		} else {
			return NOT_ROMMING_TEXT;
		}

	}

	public static TelephonyManager findTelephonyManager() {
		Context appContext = TestApplication.getAppContext();
		return (TelephonyManager)appContext.getSystemService(Context.TELEPHONY_SERVICE);
	}

	public static String getTelephonyNetworkInfo() {
		TelephonyManager tm = findTelephonyManager();
		if (tm == null) {
			return "Exception: TelephonyManager is return NULL";
		}
		return "MCC: " + TelephonyUtil.getNetworkCountry() + " Network operator code: " + tm.getNetworkOperator()
			+ " Network operator name: " + tm
			.getNetworkOperatorName() + " Network type: " + tm.getNetworkType();
	}

	public static String getTelephonySimInfo() {
		TelephonyManager tm = findTelephonyManager();
		if (tm == null) {
			return "Exception: TelephonyManager is return NULL";
		}
		if (tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT) {
			return "SIM_STATE_ABSENT";
		}
		return "MCC: " + TelephonyUtil.getSimCountry() + " operator code: " + TelephonyUtil.getSimOperator()
			+ " operator name: " + tm.getSimOperatorName() + " SIM state: " + TelephonyUtil.getUsimType();

	}

	public static String getDataState() {
		TelephonyManager tm = findTelephonyManager();
		if (tm == null) {
			return "Exception: TelephonyManager is return NULL";
		}
		String type = "UNKNOW";
		switch (tm.getDataState()) {
			case TelephonyManager.DATA_DISCONNECTED:
				type = "DATA_DISCONNECTED";
				break;
			case TelephonyManager.DATA_CONNECTING:
				type = "DATA_CONNECTING";
				break;
			case TelephonyManager.DATA_CONNECTED:
				type = "DATA_CONNECTED";
				break;
			case TelephonyManager.DATA_SUSPENDED:
				type = "DATA_SUSPENDED";
				break;
		}
		return "DataState: " + type;
	}

	public static String getStateOfAvailableGooglePlayService() {
		int resultCode = GoogleApiAvailability.getInstance()
			.isGooglePlayServicesAvailable(TestApplication.getAppContext());
		switch (resultCode) {
			case ConnectionResult.SUCCESS:
				return "Google play service available resultCode: " + resultCode;
			case ConnectionResult.SERVICE_VERSION_UPDATE_REQUIRED:
				return "Google play service version update required resultCode: " + resultCode;
			default:
				return "Google play service UnAvailable resultCode: " + resultCode;

		}

	}
}
