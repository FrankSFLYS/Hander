package top.mkswqi.hander.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import top.mkswqi.hander.base_class.Configure;
import top.mkswqi.hander.service.MDataChangeService;

public class MDataStateChangerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        notifiNetworkStateChanges(context);
    }

    private void notifiNetworkStateChanges(Context context) {
        Configure.switchMobileDataState();
    }
}
