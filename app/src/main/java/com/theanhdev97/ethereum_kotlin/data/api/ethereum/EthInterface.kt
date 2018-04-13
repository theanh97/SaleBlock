package com.theanhdev97.ethereum_kotlin.data.api.ethereum

import com.theanhdev97.ethereum_kotlin.data.model.normal.Product
import com.theanhdev97.ethereum_kotlin.data.model.response.ListenerResponse
import org.web3j.crypto.Credentials
import org.web3j.protocol.core.methods.response.Transaction
import java.math.BigInteger

/**
 * Created by DELL on 09/04/2018.
 */
interface EthInterface {
    fun getBalance(accountID: String): BigInteger

    fun getTransactionInformation(transactionHash: String): Transaction?

    fun sendEthereum(toAddress: String, credentials: Credentials, amount: Int)

    fun sendProductSmartContract(product: Product, credentials: Credentials, listener:
    ListenerResponse)

    fun getProductSmartContractByAddress(credentials: Credentials, SCAddress: String, listener: ListenerResponse)
}