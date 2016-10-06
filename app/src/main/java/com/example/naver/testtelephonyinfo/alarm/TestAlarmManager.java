package com.example.naver.testtelephonyinfo.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.example.naver.testtelephonyinfo.MainActivity;
import com.example.naver.testtelephonyinfo.R;
import com.example.naver.testtelephonyinfo.TestApplication;

/**
 * Created by jieun.kim on 2016. 9. 22..
 */
public class TestAlarmManager extends Service {

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d("test TestAlarmManager", "start service!!!!!!!!!!!!!!!!!!!!!!! ");
		Log.d("test interval", "1m");
		long interval = 60000L;
		setTestIsWakeAlarmIfTaskKilled(interval);
		notifyUnknownMessage(interval);

		return super.onStartCommand(intent, flags, startId);
	}

	// 일반 알림.
	public void notifyUnknownMessage(long interval) {
		PendingIntent pendingIntent =
			PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

		Notification.Builder builder = new Notification.Builder(this);
		//Bitmap icon = BitmapFactory.decodeResource(TestApplication.getAppContext().getResources(), R.drawable.cast_ic_notification_0);

		builder.setSmallIcon(R.drawable.cast_ic_notification_0);
		// 알림이 출력될 때 상단에 나오는 문구.
		builder.setTicker("start service");

		// 알림 출력 시간.
		builder.setWhen(System.currentTimeMillis());

		// 알림 제목.
		builder.setContentTitle("INTERVAL : " + interval);

		// 프로그래스 바.
		builder.setProgress(100, 50, false);

		// 알림 내용.
		builder.setContentText("start service time: " + System.currentTimeMillis());

		// 알림시 사운드, 진동, 불빛을 설정 가능.
		builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);

		// 알림 터치시 반응.
		builder.setContentIntent(pendingIntent);

		// 알림 터치시 반응 후 알림 삭제 여부.
		builder.setAutoCancel(true);

		//		// 우선순위.
		//		builder.setPriority(NotificationCompat.PRIORITY_MAX);

		// 고유ID로 알림을 생성.
		NotificationManager nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		nm.notify(123456, builder.build());
	}

	@Override
	public void onDestroy() {
		//setTestIsWakeAlarmIfTaskKilled();

		super.onDestroy();

	}

	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private void setTestIsWakeAlarmIfTaskKilled(long interval) {
		Log.d("TestAlarmManager", "set alarm interval:" + interval);
		Intent intent = new Intent("alarm.TestAlarmManager");
		AlarmManager alarmManager =
			(AlarmManager)TestApplication.getAppContext().getSystemService(Context.ALARM_SERVICE);
		alarmManager.cancel(PendingIntent.getService(TestApplication.getAppContext(), 0, intent, 0));

		PendingIntent registrationPendingIntent =
			PendingIntent.getService(TestApplication.getAppContext(), 0, intent, 0);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			alarmManager.setAndAllowWhileIdle(0, System.currentTimeMillis() + interval, registrationPendingIntent);
		} else {
			alarmManager.set(0, System.currentTimeMillis() + interval, registrationPendingIntent);

		}

	}
}
