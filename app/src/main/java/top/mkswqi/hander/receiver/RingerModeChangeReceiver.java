package top.mkswqi.hander.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;

import top.mkswqi.hander.base_class.RingerModeHandler;

import static top.mkswqi.hander.base_class.Configure.TAG_M_VOL;
import static top.mkswqi.hander.base_class.Configure.TAG_RMC_STATE;
import static top.mkswqi.hander.base_class.Configure.getConfig;
import static top.mkswqi.hander.base_class.Configure.getData;
import static top.mkswqi.hander.base_class.Configure.setData;

public class RingerModeChangeReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean isRMCOn = (Boolean) getConfig(TAG_RMC_STATE, true);
        if (isRMCOn) {
            RingerModeHandler.changeState(context);
        }
    }
}
