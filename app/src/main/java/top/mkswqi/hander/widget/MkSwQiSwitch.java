package top.mkswqi.hander.widget;

import android.content.Context;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.util.Log;

import top.mkswqi.hander.base_class.Configure;

/**
 * Created by FrankFLY on 2017/4/6.
 */

public class MkSwQiSwitch extends SwitchCompat {

    private static final String TAG = "MkSwQiSwitch";

    /**
     * Construct a new Switch with default styling.
     *
     * @param context The Context that will determine this widget's theming.
     */
    public MkSwQiSwitch(Context context) {
        super(context);
    }

    /**
     * Construct a new Switch with default styling, overriding specific style
     * attributes as requested.
     *
     * @param context The Context that will determine this widget's theming.
     * @param attrs   Specification of attributes that should deviate from default styling.
     */
    public MkSwQiSwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * Construct a new Switch with a default style determined by the given theme attribute,
     * overriding specific style attributes as requested.
     *
     * @param context      The Context that will determine this widget's theming.
     * @param attrs        Specification of attributes that should deviate from the default styling.
     * @param defStyleAttr An attribute in the current theme that contains a
     *                     reference to a style resource that supplies default values for
     */
    public MkSwQiSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * Directly call any attached OnClickListener.  Unlike {@link #performClick()},
     * this only calls the listener, and does not do any associated clicking
     * actions like reporting an accessibility event.
     *
     * Automatically set check state after calling this method
     *
     * @return True there was an assigned OnClickListener that was called, false
     * otherwise is returned.
     */
    @Override
    public boolean callOnClick() {
        setChecked(!isChecked());
        return super.callOnClick();
    }
}
