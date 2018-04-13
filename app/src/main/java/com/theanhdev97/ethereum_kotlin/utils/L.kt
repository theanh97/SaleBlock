package com.theanhdev97.ethereum_kotlin.utils

import android.nfc.Tag
import android.util.Log

/**
 * Created by DELL on 07/04/2018.
 */
object L {
    val TAG = "tag123"

    fun d(messgae : String){
        Log.d(TAG,messgae)
    }

    fun e(messgae : String){
        Log.e(TAG,messgae)
    }
}