package com.mn.easyauth

import android.util.Log

class EasyAuthPrinter {
    fun print(tag: String, message: String?) {
        when (EasyAuthLog.getLogType()) {
            LogType.RELEASE -> {
                printRelease(tag, message)
                printDebug(tag, message)
                printAll(tag, message)
            }

            LogType.DEBUG -> {
                printDebug(tag, message)
                printAll(tag, message)
            }

            LogType.ALL -> printAll(tag, message)
        }
    }

    private fun printDebug(tag: String, message: String?) {
        Log.d(tag, message!!)
    }

    private fun printRelease(tag: String, message: String?) {
        Log.d(tag, message!!)
    }

    private fun printAll(tag: String, message: String?) {
        Log.d(tag, message!!)
    }
}