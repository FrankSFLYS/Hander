package top.mkswqi.hander.base_class;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by FrankFLY on 2017/4/6.
 *
 * A base activity to be extended.
 */

public class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //print this name
        Log.d(TAG, "onCreate: " + getClass().getSimpleName());
        //Add this into activity list
        ActivityManager.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: " + getClass().getSimpleName());
        ActivityManager.removeActivity(this);
    }
}
