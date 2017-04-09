package top.mkswqi.hander.base_class;

import android.content.Context;
import android.media.AudioManager;
import android.os.Build;

import static top.mkswqi.hander.base_class.Configure.TAG_M_VOL;
import static top.mkswqi.hander.base_class.Configure.getData;
import static top.mkswqi.hander.base_class.Configure.setData;

/**
 * Created by FrankFLY on 2017/4/9.
 */

public class RingerModeHandler {

    /**
     * Change Media volume according to current ringer mode
     *
     * @param context context off app
     */
    public static void changeState(Context context) {
        AudioManager manager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        switch (manager.getRingerMode()) {
            case AudioManager.RINGER_MODE_SILENT:
            case AudioManager.RINGER_MODE_VIBRATE:
                int currentMusicVolume = manager.getStreamVolume(AudioManager.STREAM_MUSIC);
                if (currentMusicVolume != 0) {
                    setData(TAG_M_VOL, currentMusicVolume);
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, AudioManager.FLAG_VIBRATE);
                } else {
                    manager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_SHOW_UI);
                }
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                int savedMusicVolume = (int) getData(TAG_M_VOL, 2);
                if (Build.VERSION.SDK_INT >= 23) {
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, AudioManager.FLAG_VIBRATE);
                } else {
                    manager.setStreamVolume(AudioManager.STREAM_MUSIC, savedMusicVolume, AudioManager.FLAG_REMOVE_SOUND_AND_VIBRATE);
                }
                break;
        }
    }

}
