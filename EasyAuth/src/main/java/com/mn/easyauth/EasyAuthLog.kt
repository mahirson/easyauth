package com.mn.easyauth

object EasyAuthLog {
    var logType: LogType? = null
}

enum class LogType {
    DEBUG, RELEASE, ALL
}