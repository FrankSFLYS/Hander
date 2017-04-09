package top.mkswqi.hander.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import top.mkswqi.hander.base_class.Configure;

/**
 * Created by FrankFLY on 2017/4/6.
 */

public class HanderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        Configure.init(context);
    }

}
