package com.afuktju.oona;

import android.content.Context;
import android.util.Log;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class Utils {
    public static String getVideoPathByRaw(Context context, int video_file) {
        String path = "android.resource://" + context.getPackageName() + "/" + video_file;
        return path;
    }

    public static void showLog(String message) {
        Log.d("LOGONA",message);
    }
}
