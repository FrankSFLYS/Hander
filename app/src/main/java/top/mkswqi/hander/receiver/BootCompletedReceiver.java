package top.mkswqi.hander.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import top.mkswqi.hander.service.MDataChangeService;

public class BootCompletedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, MDataChangeService.class);
        context.startService(serviceIntent);
    }
}
