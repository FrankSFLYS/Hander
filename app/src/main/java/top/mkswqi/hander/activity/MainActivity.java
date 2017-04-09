package top.mkswqi.hander.activity;

import android.support.v7.widget.*;
import android.os.Bundle;
import android.widget.LinearLayout;

import top.mkswqi.hander.R;
import top.mkswqi.hander.base_class.BaseActivity;
import top.mkswqi.hander.base_class.Configure;
import static top.mkswqi.hander.base_class.Configure.getConfig;
import static top.mkswqi.hander.base_class.Configure.setConfig;

import top.mkswqi.hander.widget.MkSwQiSwitch;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    AppCompatButton changeMDataState;

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
        changeMDataState = (AppCompatButton) findViewById(R.id.enable_disable_mobile_data);
        initChangeMDataStateButton();
    }

    private void initChangeMDataStateButton() {
        setChangeMDataStateText();
        changeMDataState.setOnClickListener((view) -> {
            Configure.switchMobileDataState();
            setChangeMDataStateText();
        });
    }

    private void setChangeMDataStateText() {
        boolean isMDOn = Configure.getMobileDataState();
        if (isMDOn) {
            //Mobile Data is on
            //Set button text to "Disable"
            changeMDataState.setText(getString(R.string.disable_mobile_data));
        } else {
            //Mobile Data is off
            //Set button text to "Enable"
            changeMDataState.setText(getString(R.string.enable_mobile_data));
        }
    }

}
