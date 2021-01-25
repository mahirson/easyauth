package com.mn.easyauth;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class EasyAuth<T extends User> {
    /*
    * this is your authenticated user class
    * must be extended User class
     */
    private T user;

    private final String TAG = getClass().getName();

    /*
     * this is your authenticated user class type
     * must be extended User class
     * type is using for Json Processing
     */
    private final Class<T> type;

    private final Context context;

    private final EasyAuthPrinter printer;

    /*
     * normal constructor class
     */
    public EasyAuth(Context context,Class<T> type) {
        this.type = type;
        this.context = context;
        EasyAuthLog.setLogType(LogType.ALL);
        printer = new EasyAuthPrinter();
    }

    /*
     * type is your authenticated user class type
     * logType is your logging level. Can be Debug,Release and All
     */
    public EasyAuth(Context context,Class<T> type,LogType logType) {
        this.type = type;
        this.context = context;
        EasyAuthLog.setLogType(logType);
        printer = new EasyAuthPrinter();
    }


    private void deleteUser() {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

    /*
     * saving user to shared preferences
     */
    public void saveUser(T user) {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try {
            json = ow.writeValueAsString(user);
            saveUser(json);
            setUser(user);
            printer.print(TAG,"user saved "+json);
        } catch (JsonProcessingException e) {
            printer.print(TAG,e.getMessage());
        }
    }

    public void saveUser(String user) {
        SharedPreferences preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data",user);
        editor.apply();
    }

    /*
     * check if user exists or not
     */
    public boolean hasUser() {
        SharedPreferences preferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        T user;
        try {
            user = new ObjectMapper().readValue(preferences.getString("data",""), getType());
            setUser(user);
            printer.print(TAG,"has saved user"+ user);
            return true;
        } catch (JsonProcessingException e) {
            printer.print(TAG,"there is no user");
        }
        return false;
    }

    /*
     * logs out
     */
    public void logOut() {
        deleteUser();
    }

    /*
     * get current user
     */
    @Nullable
    public T getUser() {
        return user;
    }

    private void setUser(@NonNull T user) {
        this.user = user;
    }

    @NonNull
    private Class<T> getType() {
        return this.type;
    }
}
