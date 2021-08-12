package com.graduation.softskillsmeter.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SharedPreferenceUtils {

    private static SharedPreferenceUtils instance;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String USER_ID = "user_id";
    private static final String USER_NAME = "user_name";
    private static final String ANSWER1 = "answer1";
    private static final String ANSWER2 = "answer2";
    private static final String ANSWER3 = "answer3";
    private static final String ANSWER4 = "answer4";

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

    public String getUserName() {
        return sharedPreferences.getString(USER_NAME, "");
    }

    public void setUserName(String username){
        editor.putString(USER_NAME, username);
        editor.commit();
    }

    public List<String> getAnswers() {
        List<String> answers = new ArrayList<>();
        answers.add(sharedPreferences.getString(ANSWER1, ""));
        answers.add(sharedPreferences.getString(ANSWER2, ""));
        answers.add(sharedPreferences.getString(ANSWER3, ""));
        answers.add(sharedPreferences.getString(ANSWER4, ""));

        return answers;
    }

    public void setAnswer1(String answer) {
        editor.putString(ANSWER1, answer);
        editor.commit();
    }

    public void setAnswer2(String answer) {
        editor.putString(ANSWER2, answer);
        editor.commit();
    }

    public void setAnswer3(String answer) {
        editor.putString(ANSWER3, answer);
        editor.commit();
    }

    public void setAnswer4(String answer) {
        editor.putString(ANSWER4, answer);
        editor.commit();
    }
}
