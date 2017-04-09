package top.mkswqi.hander.base_class;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by FrankFLY on 2017/4/9.
 */

class ModifingMobileData extends ExecuteAsRootBase {

    private boolean state = false;
    private static final String TAG = "ModifingMobileData";
    @Override
    protected ArrayList<String> getCommandsToExecute() {

        final String CMD_ENABLE_MOBILE_DATA = "svc data enable";
        final String CMD_DISABLE_MOBILE_DATA = "svc data disable";

        ArrayList<String> list = new ArrayList<>();
        if (state) {
            list.add(CMD_ENABLE_MOBILE_DATA);
            return list;
        } else {
            list.add(CMD_DISABLE_MOBILE_DATA);
            return list;
        }
    }

    private void switchState(boolean state) {
        this.state = state;
    }


    /**
     * Set mobile data state
     * Notice: only rooted devices supported
     *
     * @param mobileDataEnabled Data state to set
     */
    static void setMobileDataState(boolean mobileDataEnabled)
    {
        try
        {
            ModifingMobileData exer = new ModifingMobileData();

            if (ExecuteAsRootBase.canRunRootCommands()) {
                exer.switchState(mobileDataEnabled);
                exer.execute();
            }
            /*
            TelephonyManager telephonyService = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
            }*/
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error setting mobile data state", ex);
        }
    }
}
