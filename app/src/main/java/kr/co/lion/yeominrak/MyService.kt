package kr.co.lion.yeominrak

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.IBinder
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.Calendar

class MyService : Service() {
    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var alarmManager: AlarmManager

    override fun onCreate() {
        super.onCreate()

        notificationManager = NotificationManagerCompat.from(this)

        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        createNotificationChannel()

        scheduleNotification()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 알림 띄우는 코드 작성
        showNotification()

        // 서비스가 종료되지 않도록 START_STICKY 반환
        return START_STICKY
    }



    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private fun scheduleNotification() {
        // Calendar 객체를 사용하여 매월 1일 00:00의 시간 설정
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.HOUR_OF_DAY, 20)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)

        // PendingIntent를 사용하여 알림을 띄울 작업 지정
        val intent = Intent(this, MyService::class.java)
        val pendingIntent = PendingIntent.getService(this, 0, intent, 0)

        // AlarmManager를 사용하여 알림 스케줄 설정
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY * 30,  // 30일마다 반복
            pendingIntent
        )
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "my_channel_id"
            val channelName = "My Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance).apply {
                description = "My Channel Description"
            }
            notificationManager.createNotificationChannel(channel)
        }
    }
    private fun showNotification() {
        // 알림 빌더 생성
        val notificationBuilder = NotificationCompat.Builder(this, "my_channel_id")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("알림 제목")
            .setContentText("알림 내용")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // 알림 실행할 액티비티 지정
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        notificationBuilder.setContentIntent(pendingIntent)

        // 알림 표시
        val notificationId = 1
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(notificationId, notificationBuilder.build())
    }

}