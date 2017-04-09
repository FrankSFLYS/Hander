package top.mkswqi.hander.base_class;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by FrankFLY on 2017/4/7.
 */

public class Configure {

    /**
     * Log Tag
     */
    private static final String TAG = "Configure";

    /**
     * Application context
     */
    private static Context appContext;
    /**
     * Application SharedPreference to save configurations
     */
    private static SharedPreferences configPref;
    /**
     * Application SharedPreference to save data
     */
    private static SharedPreferences dataPref;

    /**
     * Name of configuration SharedPreference
     */
    public static final String CONFIG = "config";
    /**
     * Name of data SharedPreference
     */
    public static final String DATA = "data";
    /**
     * Tag name of music volume in SharedPreference
     */
    public static final String TAG_M_VOL = "music_volume";
    /**
     * Tag name of Ringer Mode Changed Switch State in SharedPreference
     */
    public static final String TAG_RMC_STATE = "rmc_switch_state";

    /**
     * Returns Application Context
     * @return Application context
     */
    public static Context getAppContext() {
        return appContext;
    }

    /**
     * Get ini application context
     * @param appContext Application context
     */
    public static void setAppContext(Context appContext) {
        Configure.appContext = appContext;
    }

    /**
     * Init Configure Class
     * BETTER CALL IT IN APPLICATION
     *
     * @param appContext A context of application is needed for Configure
     */
    public static void init(Context appContext) {
        Log.d(TAG, "init: ");
        setAppContext(appContext);
        configPref = appContext.getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        dataPref = appContext.getSharedPreferences(DATA, Context.MODE_PRIVATE);
    }

    /**
     * Returns a configuration of this application
     * The configuration is saved as SharedPreferences, and its file name
     * is "config.xml"
     *
     * @see #getSharedPref(String, Object, String)
     *
     * @param key The tag of an item to save, it is also the tag of xml tag
     * @param defaultVal If the item has not been saved yet or something wrong happened,
     *                   defaultVal will be returned
     * @param <T> Type of defaultVal
     * @return Saved value, defaultVal will be returned if key has not been saved
     *  Notice: You have to cast return value
     */
    public static <T> Object getConfig(String key, T defaultVal) {
        return getSharedPref(key, defaultVal, CONFIG);
    }

    /**
     * Returns a data set of this application
     * The data is saved as SharedPreferences, and its file name
     * is "data.xml"
     *
     * @see #getSharedPref(String, Object, String)
     *
     * @param key The tag of an item to save, it is also the tag of xml tag
     * @param defaultVal If the item has not been saved yet or something wrong happened,
     *                   defaultVal will be returned
     * @param <T> Type of defaultVal
     * @return Saved value, defaultVal will be returned if key has not been saved
     *  Notice: You have to cast return value
     */
    public static <T> Object getData(String key, T defaultVal) {
        return getSharedPref(key, defaultVal, DATA);
    }

    /**
     * Save a configuration item into config.xml
     *
     * @see #editSharedPref(String, Object, String)
     *
     * @param key The tag of configuration item, it is also the tag of xml tag
     * @param value The value to save
     * @param <T> Type of value
     */
    public static <T> void setConfig(String key, T value) {
        editSharedPref(key, value, CONFIG);
    }

    /**
     * Save a data item into config.xml
     *
     * @see #editSharedPref(String, Object, String)
     *
     * @param key The tag of data item, it is also the tag of xml tag
     * @param value The value to save
     * @param <T> Type of value
     */
    public static <T> void setData(String key, T value) {
        editSharedPref(key, value, DATA);
    }

    /**
     * Get SharedPreference settings
     *
     * @param key Tag of an item
     * @param defaultVal Default value of the item, if key cannot be found or other
     *                   error occurs, defaultVal will be the return value
     * @param prefType To show which pref file is the item from
     * @param <T> The type of defaultVal
     * @return Saved value, or defaultVal if the item has not been saved
     */
    private static <T> Object getSharedPref(String key, T defaultVal, String prefType) {
        String className = defaultVal.getClass().getSimpleName();
        Class classOfT = defaultVal.getClass();
        Log.d(TAG, "getConfig: key: " + key
                + "className: " + className);
        SharedPreferences pref;
        if (prefType.equals(CONFIG)) {
            pref = configPref;
        } else {
            pref = dataPref;
        }
        switch (className) {
            case "String":
                String aStr;
                aStr = pref.getString(key, (String) defaultVal);
                try {
                    return classOfT.cast(aStr);
                } catch (ClassCastException e) {
                    Log.w(TAG, "getConfig: Error casting String to T", e);
                    return defaultVal;
                }
            case "Integer":
                Integer anInt;
                anInt = pref.getInt(key, (Integer) defaultVal);
                try {
                    return classOfT.cast(anInt);
                } catch (ClassCastException e) {
                    Log.w(TAG, "getConfig: Error casting Integer to T", e);
                    return defaultVal;
                }
            case "Float":
                Float aFloat;
                aFloat = pref.getFloat(key, (Float) defaultVal);
                try {
                    return classOfT.cast(aFloat);
                } catch (ClassCastException e) {
                    Log.w(TAG, "getConfig: Error casting Float to T", e);
                    return defaultVal;
                }
            case "Boolean":
                Boolean aBoolean;
                aBoolean = pref.getBoolean(key, (Boolean) defaultVal);
                try {
                    return classOfT.cast(aBoolean);
                } catch (ClassCastException e) {
                    Log.w(TAG, "getConfig: Error casting Boolean to T", e);
                    return defaultVal;
                }
            case "Long":
                Long aLong;
                aLong = pref.getLong(key, (Long) defaultVal);
                try {
                    return classOfT.cast(aLong);
                } catch (ClassCastException e) {
                    Log.w(TAG, "getConfig: Error casting Long to T", e);
                    return defaultVal;
                }
            default:
                return defaultVal;
        }
    }

    /**
     * Edit a SharedPreference item
     *
     *
     * @param key Tag of the item
     * @param value Value to save
     * @param prefType To show which pref file will be edit
     * @param <T> Type of value
     */
    private static <T> void editSharedPref(String key, T value, String prefType) {
        String className = value.getClass().getSimpleName();
        SharedPreferences.Editor editor;
        if (prefType.equals(CONFIG)) {
            editor = configPref.edit();
        } else {
            editor = dataPref.edit();
        }
        try {
            switch (className) {
                case "String":
                    editor.putString(key, (String) value);
                    break;
                case "Integer":
                    editor.putInt(key, (Integer) value);
                    break;
                case "Float":
                    editor.putFloat(key, (Float) value);
                    break;
                case "Boolean":
                    editor.putBoolean(key, (Boolean) value);
                    break;
                case "Long":
                    editor.putLong(key, (Long) value);
                    break;
                default:
                    throw new ClassCastException();
            }
        } catch (ClassCastException e) {
            Log.w(TAG, "editSharedPref: Error casting T to " + className, e);
        } finally {
            editor.apply();
        }
    }

    /**
     * Returns Phone's Mobile Data State
     *
     * @return A boolean word shows whether Mobile Data is on
     */
    public static boolean getMobileDataState() {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) appContext.getSystemService(Context.TELEPHONY_SERVICE);

            Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

            if (null != getMobileDataEnabledMethod)
            {
                return (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error getting mobile data state", ex);
        }

        return false;
    }
/*
    public void setMobileDataState(boolean mobileDataEnabled)
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method setMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("setDataEnabled", boolean.class);

            if (null != setMobileDataEnabledMethod)
            {
                setMobileDataEnabledMethod.invoke(telephonyService, mobileDataEnabled);
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error setting mobile data state", ex);
        }
    }

    public boolean getMobileDataState()
    {
        try
        {
            TelephonyManager telephonyService = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

            Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

            if (null != getMobileDataEnabledMethod)
            {
                boolean mobileDataEnabled = (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);

                return mobileDataEnabled;
            }
        }
        catch (Exception ex)
        {
            Log.e(TAG, "Error getting mobile data state", ex);
        }

        return false;
    }
    */
    /*
    public static String getStringConfig(String tag, String defaultVal) {
        return configPref.getString(tag, defaultVal);
    }

    public static int getIntConfig(String tag, int defaultVal) {
        return configPref.getInt(tag, defaultVal);
    }

    public static boolean getBoolConfig(String tag, boolean defaultVal) {
        return configPref.getBoolean(tag, defaultVal);
    }

    public static long getLongConfig(String tag, long defaultVal) {
        return configPref.getLong(tag, defaultVal);
    }

    public static float getFloatConfig(String tag, float defaultVal) {
        return configPref.getFloat(tag, defaultVal);
    }

    public static Set<String> getStringSetConfig(String tag, Set<String> defaultVal) {
        return configPref.getStringSet(tag, defaultVal);
    }
    */

}
