package com.afuktju.oona;

import android.content.Context;

/**
 * Created by AfukTju on 26/02/2018.
 */

public class Utils {
    public static String getVideoPathByRaw(Context context, int video_file) {
        String path = "android.resource://" + context.getPackageName() + "/" + video_file;
        return path;
    }
}
