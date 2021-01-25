package com.mn.easyauth;

import android.util.Log;

public class EasyAuthPrinter {

    void print(String tag,String message){
        switch (EasyAuthLog.getLogType()){
            case RELEASE: printRelease(tag,message);
            case DEBUG: printDebug(tag,message);
            case ALL: printAll(tag,message);
        }
    }

    private void printDebug(String tag,String message){
        Log.d(tag,message);
    }

    private void printRelease(String tag,String message){
        Log.d(tag,message);
    }

    private void printAll(String tag,String message){
        Log.d(tag,message);
    }

}
