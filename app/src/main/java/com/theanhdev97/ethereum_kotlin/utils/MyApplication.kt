package com.theanhdev97.ethereum_kotlin.utils

import android.app.Application
import com.theanhdev97.ethereum_kotlin.data.api.ethereum.EthConstants
import com.theanhdev97.ethereum_kotlin.data.model.normal.Account

/**
 * Created by DELL on 08/04/2018.
 */
class MyApplication : Application() {
    companion object {

        private var mApplication: MyApplication? = null
        fun getInstance(): MyApplication {
            return mApplication!!
        }
    }

    private var mAccount: Account? = null

    override fun onCreate() {
        super.onCreate()
        mApplication = this
        mAccount = Account(EthConstants.ACC_1, EthConstants.ACC_1_PRIVATE_KEY)
    }

    fun getAccount(): Account {
        return mAccount!!
    }
}