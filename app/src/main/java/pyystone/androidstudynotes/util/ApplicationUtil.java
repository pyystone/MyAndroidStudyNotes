package pyystone.androidstudynotes.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by pyysotne on 2015/08/27/0027.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class ApplicationUtil {
    private Context mContext;
    private static ApplicationUtil mInstance;
    public static void init(Context context) {
        mInstance = new ApplicationUtil();
        mInstance.mContext = context;
    }

    public static ApplicationUtil getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return getInstance().mContext;
    }

    public static void toast(String str) {
        Toast.makeText(getInstance().mContext,str,Toast.LENGTH_SHORT).show();
    }
}
