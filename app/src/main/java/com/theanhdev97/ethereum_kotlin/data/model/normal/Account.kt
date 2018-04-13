package com.theanhdev97.ethereum_kotlin.data.model.normal

import org.web3j.crypto.Credentials

/**
 * Created by DELL on 08/04/2018.
 */

data class Account(var id: String, var privateKey: String) {
    fun getCredentials(): Credentials {
        return Credentials.create(privateKey)
    }
}
