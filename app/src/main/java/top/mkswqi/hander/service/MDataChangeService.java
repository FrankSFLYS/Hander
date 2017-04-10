package top.mkswqi.hander.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import top.mkswqi.hander.R;
import top.mkswqi.hander.activity.MainActivity;
import top.mkswqi.hander.base_class.Configs;
import top.mkswqi.hander.base_class.Configure;

public class MDataChangeService extends Service {

    public MDataChangeService() {
    }

    /**
     * Called by the system when the service is first created.  Do not call this method directly.
     */
    @Override
    public void onCreate() {
        super.onCreate();
        boolean isServiceOn = false;

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Configs.BROADCAST_CHANGE_MDATA_STATE);

        isServiceOn = (Boolean) Configure.getConfig(Configs.SWITCH_MDATA, false);
        if (isServiceOn) {
            Intent intent = new Intent(Configs.BROADCAST_CHANGE_MDATA_STATE);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            Notification notification = new NotificationCompat.Builder(this)
                    .setContentTitle(getString(R.string.app_name))
                    .setContentText(getString(R.string.change_mdata_state))
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();
            startForeground(Configs.NOTIFICATION_MDATA, notification);
        } else {
            this.stopSelf();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.  The
     * service should clean up any resources it holds (threads, registered
     * receivers, etc) at this point.  Upon return, there will be no more calls
     * in to this Service object and it is effectively dead.  Do not call this method directly.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
