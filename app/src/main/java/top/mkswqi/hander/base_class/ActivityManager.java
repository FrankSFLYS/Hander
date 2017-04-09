package top.mkswqi.hander.base_class;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FrankFLY on 2017/4/6.
 *
 * Manage activities, to add / remove / finish them through a list
 */
public class ActivityManager {

    /**
     * ArrayList to store activated activities
     */
    private static List<Activity> activityList = new ArrayList<>();

    /**
     * Add an activity to the list
     * @param activity The activity to add, it should be opened before doing this
     */
    public static void addActivity(Activity activity) {
        //add
        activityList.add(activity);
    }

    /**
     * Remove an activity from the list
     * @param activity The activity to remove, it should be finished after doing this
     */
    public static void removeActivity(Activity activity) {
        //remove
        activityList.remove(activity);
    }

    /**
     * Finish all activities in the list, generally it mean the App has ended.
     */
    public static void finishAll() {
        for (Activity activity : activityList) {
            if (!activity.isFinishing()) {
                //not finished
                activity.finish();
            }
        }
        activityList.clear();
    }

}
