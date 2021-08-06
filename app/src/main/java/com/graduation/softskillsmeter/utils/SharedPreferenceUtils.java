package com.graduation.softskillsmeter.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils instance;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String USER_ID = "user_id";

    private SharedPreferenceUtils(Context context) {
        this.context = context;
        if (context != null) {
            sharedPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
            editor = sharedPreferences.edit();
        }
    }

    public static SharedPreferenceUtils getInstance(Context context) {
        if (instance == null)
            return new SharedPreferenceUtils(context);

        return instance;
    }

    public String getUserId() {
        return sharedPreferences.getString(USER_ID, "");
    }

    public void setUserId(String userId){
        editor.putString(USER_ID, userId);
        editor.commit();
    }
}
