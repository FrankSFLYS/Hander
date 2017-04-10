package top.mkswqi.hander.activity;

import android.app.NotificationManager;
import android.content.Intent;
import android.support.v7.widget.*;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import top.mkswqi.hander.R;
import top.mkswqi.hander.base_class.BaseActivity;
import top.mkswqi.hander.base_class.Configs;
import top.mkswqi.hander.base_class.Configure;
import static top.mkswqi.hander.base_class.Configure.getConfig;
import static top.mkswqi.hander.base_class.Configure.setConfig;

import top.mkswqi.hander.service.MDataChangeService;
import top.mkswqi.hander.widget.MkSwQiSwitch;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private static boolean isMDOn;

    AppCompatButton mChangeMDataState;
    MkSwQiSwitch mDataSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout rMCSwitchHolder = (LinearLayout) findViewById(R.id.holder_switch_listen_ring_service);
        MkSwQiSwitch rMCSwitch = (MkSwQiSwitch) findViewById(R.id.switch_listen_ring_service);
        Boolean rMCSwitchState = (Boolean) getConfig(Configure.TAG_RMC_STATE, true);
        rMCSwitch.setChecked(rMCSwitchState);
        rMCSwitch.setOnClickListener((view) -> {
            setConfig(Configure.TAG_RMC_STATE, rMCSwitch.isChecked());
        });
        rMCSwitchHolder.setOnClickListener((view) -> {
            rMCSwitch.callOnClick();
        });

        LinearLayout mDataSwitchHolder = (LinearLayout) findViewById(R.id.holder_switch_mdata_by_notifi);
        mDataSwitch = (MkSwQiSwitch) findViewById(R.id.switch_mdata_by_notifi);
        initMDataSwitch();
        mDataSwitchHolder.setOnClickListener((view) ->{
            mDataSwitch.callOnClick();
        });

        mChangeMDataState = (AppCompatButton) findViewById(R.id.enable_disable_mobile_data);
        initChangeMDataStateButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isMDOn = Configure.getMobileDataState();
        setChangeMDataStateText();
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private void initChangeMDataStateButton() {
        setChangeMDataStateText();
        mChangeMDataState.setOnClickListener((view) -> {
            new Thread() {
                @Override
                public void run() {
                    Configure.switchMobileDataState();
                    mChangeMDataState.post(() -> {
                        setChangeMDataStateText();
                    });
                }
            }.start();
        });
    }

    private void setChangeMDataStateText() {
        isMDOn = Configure.getMobileDataState();
        if (isMDOn) {
            //Mobile Data is on
            //Set button text to "Disable"
            mChangeMDataState.setText(getString(R.string.disable_mobile_data));
        } else {
            //Mobile Data is off
            //Set button text to "Enable"
            mChangeMDataState.setText(getString(R.string.enable_mobile_data));
        }
    }

    private void initMDataSwitch() {
        boolean isServiceOn = (Boolean) Configure.getConfig(Configs.SWITCH_MDATA, false);
        mDataSwitch.setChecked(isServiceOn);
        mDataSwitch.setOnClickListener((view) -> {
            boolean newState = mDataSwitch.isChecked();
            Configure.setConfig(Configs.SWITCH_MDATA, newState);
            if (!newState) {
                Intent stopIntent = new Intent(this, MDataChangeService.class);
                stopService(stopIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent startIntent = new Intent(this, MDataChangeService.class);
        startService(startIntent);
    }
}
