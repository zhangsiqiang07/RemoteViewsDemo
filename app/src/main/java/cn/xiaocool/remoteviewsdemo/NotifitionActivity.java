package cn.xiaocool.remoteviewsdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

public class NotifitionActivity extends AppCompatActivity {



    private int nid = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifition);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              yuanshengNotifition();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customNotifition();
            }
        });
    }

    private void customNotifition() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//拿到NotificationManager
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);


        RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.remoteview_layout);
        mRemoteViews.setImageViewResource(R.id.rm_img, R.mipmap.ic_launcher);
        mRemoteViews.setTextViewText(R.id.rm_text, "我是自定义的通知");


        Notification notify = mBuilder.setContent(mRemoteViews)
                .setContentIntent(getPendingIntent())
                .setTicker("自定义")
                .setPriority(Notification.PRIORITY_DEFAULT)// 设置该通知优先级
                .setOngoing(false)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        notify.flags = Notification.FLAG_ONGOING_EVENT;
        mNotificationManager.notify(nid++, notify);


    }

    private void yuanshengNotifition() {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setContentTitle("测试标题")//设置通知栏标题
                .setContentText("测试内容") //设置通知栏显示内容
                .setContentIntent(getPendingIntent()) //设置通知栏点击意图
                .setNumber(10) //设置通知集合的数量
                .setTicker("测试通知来啦") //通知首次出现在通知栏，带上升动画效果的
                .setWhen(System.currentTimeMillis())//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
                .setPriority(Notification.PRIORITY_DEFAULT) //设置该通知优先级
                //  .setAutoCancel(true)// 设置这个标志当用户单击面板就可以让通知将自动取消
                .setOngoing(false)//ture，设置他为一个正在进行的通知。他们通常是用来表示一个后台任务,用户积极参与(如播放音乐)或以某种方式正在等待,因此占用设备(如一个文件下载,同步操作,主动网络连接)
                .setDefaults(Notification.DEFAULT_VIBRATE)//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
                //Notification.DEFAULT_ALL  Notification.DEFAULT_SOUND 添加声音 // requires VIBRATE permission
                .setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON

        mNotificationManager.notify(0, mBuilder.build());
    }

    /**
     *  - FLAG_ONE_SHOT                只执行一次
     *  - FLAG_NO_CREATE               若描述的Intent不存在则返回NULL值
     *  - FLAG_CANCEL_CURRENT          如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
     *  - FLAG_UPDATE_CURRENT          总是执行,这个flag用的最多
     * @param flagAutoCancel
     * @return
     */
    private PendingIntent getPendingIntent() {

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pend = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        return pend;
    }
}
