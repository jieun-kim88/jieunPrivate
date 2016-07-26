package com.example.naver.testtelephonyinfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	public static final String SEPARATOR = "---------------------------------";
	private Button btn_get;
	private Button btn_clear;
	private TextView txt_info;
	private String teleInfo;
	private Logger LOG = LoggerFactory.getLogger(MainActivity.class);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
				// 현재시간을 msec 으로 구한다.
				long now = System.currentTimeMillis();
				// 현재시간을 date 변수에 저장한다.
				Date date = new Date(now);
				// 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
				SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				// nowDate 변수에 값을 저장한다.
				String formatDate = sdfNow.format(date);
				teleInfo = StringUtils
					.join(teleInfo, formatDate, SEPARATOR, StringUtils.LF
						, InfoMessageHelper.TM_NETWORK_TAG, InfoMessageHelper.getTelephonyNetworkInfo(), StringUtils.LF,
						InfoMessageHelper.TM_SIM_TAG, InfoMessageHelper.getTelephonySimInfo(), StringUtils.LF,
						InfoMessageHelper.TM_ROMMING_TAG, InfoMessageHelper.getRomingInfo(), StringUtils.LF,
						InfoMessageHelper.TM_DATE_STATE_TAG, InfoMessageHelper.getDataState(), StringUtils.LF,
						InfoMessageHelper.GG_SERVICE_TAG, InfoMessageHelper.getStateOfAvailableGooglePlayService(),
						StringUtils.LF);
				teleInfo = StringUtils.join(teleInfo, SEPARATOR, StringUtils.LF);
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
