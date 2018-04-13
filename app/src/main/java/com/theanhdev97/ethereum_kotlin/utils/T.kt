package com.theanhdev97.ethereum_kotlin.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by DELL on 08/04/2018.
 */
object T {
    fun toast(context: Context, message: String) {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
}