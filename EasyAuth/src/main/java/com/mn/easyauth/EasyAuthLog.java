package com.mn.easyauth;

public class EasyAuthLog {
    private static LogType LOG_TYPE;

    public static void setLogType(LogType logType) {
        LOG_TYPE = logType;
    }

    public static LogType getLogType() {
        return LOG_TYPE;
    }
}

enum LogType {
    DEBUG,RELEASE,ALL
}
