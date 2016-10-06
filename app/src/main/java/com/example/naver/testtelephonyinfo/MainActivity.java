package com.example.naver.testtelephonyinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.naver.testtelephonyinfo.alarm.TestAlarmManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String SEPARATOR = "---------------------------------";
	public static final String LONG_SEPARATOR = "------------------------------------------------------------------";

	private Button btn_get;
	private Button btn_clear;
	private TextView txt_info;
	private String teleInfo;
	private Logger LOG = LoggerFactory.getLogger(MainActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Intent intent = new Intent(TestApplication.getAppContext(), TestAlarmManager.class);
		intent.setPackage("com.example.naver.testtelephonyinfo");
		startService(intent);
		findViews();

	}

	private void findViews() {
		btn_get = (Button)findViewById(R.id.btn_get);
		btn_clear = (Button)findViewById(R.id.btn_clear);
		txt_info = (TextView)findViewById(R.id.txt_info);
		btn_get.setOnClickListener(this);
		btn_clear.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_get:
				teleInfo = StringUtils.join(StringUtils.LF, LONG_SEPARATOR, StringUtils.LF, teleInfo);

				// 현재시간을 msec 으로 구한다.
				long now = System.currentTimeMillis();
				// 현재시간을 date 변수에 저장한다.
				Date date = new Date(now);
				// 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
				SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				// nowDate 변수에 값을 저장한다.
				String formatDate = sdfNow.format(date);
				teleInfo = StringUtils
					.join(formatDate, SEPARATOR, StringUtils.LF
						, InfoMessageHelper.TM_NETWORK_TAG, StringUtils.LF, InfoMessageHelper.getTelephonyNetworkInfo(),
						StringUtils.LF,
						InfoMessageHelper.TM_SIM_TAG, StringUtils.LF, InfoMessageHelper.getTelephonySimInfo(),
						StringUtils.LF,
						InfoMessageHelper.TM_ROMMING_TAG, StringUtils.LF, InfoMessageHelper.getRomingInfo(),
						StringUtils.LF,
						InfoMessageHelper.TM_DATE_STATE_TAG, StringUtils.LF, InfoMessageHelper.getDataState(),
						StringUtils.LF,
						InfoMessageHelper.GG_SERVICE_TAG, StringUtils.LF,
						InfoMessageHelper.getStateOfAvailableGooglePlayService(),
						StringUtils.LF,
						InfoMessageHelper.CONNECTED_WIFI_TAG, StringUtils.SPACE,
						InfoMessageHelper.getConnectedWIFIinfo(), teleInfo);
				txt_info.setText(teleInfo);
				LOG.debug(teleInfo);
				LOG.debug("", SEPARATOR);

				break;

			case R.id.btn_clear:
				teleInfo = StringUtils.EMPTY;
				txt_info.setText(teleInfo);
				break;
		}
	}

}
