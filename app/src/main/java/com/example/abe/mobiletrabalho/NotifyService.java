package com.example.abe.mobiletrabalho;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class NotifyService extends Service {

    private HandlerThread handlerThread;
    private Handler handler;

    //Tempo das notificações
    private final int TEMPO_ENTRE_NOTIFICAÇOES_SEGUNDOS = 100;

    @Override
    public void onCreate() {
        handlerThread = new HandlerThread("HandlerThread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(!handlerThread.isAlive()) {
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    sendNotification();
                    handler.postDelayed(this, 10000 * TEMPO_ENTRE_NOTIFICAÇOES_SEGUNDOS);
                }
            };
            handler.post(runnable);
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handlerThread.quit();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void sendNotification(){
        Intent intent = new Intent (this ,
                MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity
                (this, 0, intent, 0);
        Notification notification = new Notification.Builder (this)
                .setContentTitle("Vamos ao treino!")
                .setContentText("Está na hora de treinar! ^^")
                .setSmallIcon(R.drawable.notification_icon)
                .setContentIntent(pendingIntent)
                .getNotification();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        NotificationManager notificationManager =
                (NotificationManager) getSystemService (NOTIFICATION_SERVICE);
        notificationManager.notify (0, notification);
    }
}
