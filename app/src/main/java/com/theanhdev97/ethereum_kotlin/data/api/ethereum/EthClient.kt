package com.theanhdev97.ethereum_kotlin.data.api.ethereum

import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.util.Log
import com.theanhdev97.ethereum_kotlin.data.model.normal.Product
import com.theanhdev97.ethereum_kotlin.data.model.response.FunctionType
import com.theanhdev97.ethereum_kotlin.data.model.response.ListenerResponse
import com.theanhdev97.ethereum_kotlin.data.model.response.ResponseStatus
import com.theanhdev97.ethereum_kotlin.data.model.smart_contract.Product_sol_product
import com.theanhdev97.ethereum_kotlin.utils.L
import org.spongycastle.asn1.x500.style.RFC4519Style.description
import org.web3j.abi.EventValues
import org.web3j.crypto.Credentials
import org.web3j.protocol.Web3j
import org.web3j.protocol.Web3jFactory
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.response.Transaction
import org.web3j.protocol.http.HttpService
import org.web3j.tx.Contract
import org.web3j.tx.TransactionManager
import org.web3j.tx.Transfer
import org.web3j.utils.Convert
import java.io.IOException
import java.math.BigDecimal
import java.math.BigInteger

/**
 * Created by DELL on 07/04/2018.
 */


class EthClient : EthInterface {
    companion object {
        private var sInstance: EthClient? = null

        val instance: EthClient
            get() {
                if (sInstance == null)
                    sInstance = EthClient()
                return sInstance!!
            }
    }

    internal var mWeb3j: Web3j

    init {
        mWeb3j = Web3jFactory.build(HttpService(EthConstants.ETHEREUM_INFURA_SERVER_URL))
    }

    override fun getBalance(accountID: String): BigInteger {
        var balance = BigInteger.valueOf(0);
        try {
            var ethGetBalance
                    = mWeb3j.ethGetBalance(accountID,
                    DefaultBlockParameterName.LATEST).send()
            balance = ethGetBalance.balance
        } catch (e: Exception) {
            Log.e(TAG, e.message);
        }
        return balance
    }

    override fun getTransactionInformation(transactionHash: String): Transaction? {
        try {
            val ethTransaction = mWeb3j.ethGetTransactionByHash(transactionHash).send()
            val transaction = ethTransaction.transaction
            L.d("Transaction : " + transactionHash
                    + "\nFrom : " + transaction.from
                    + "\nTo : " + transaction.to
                    + "\nTransaction Index : " + transaction.transactionIndex
                    + "\nValue : " + transaction.value)
            return transaction
        } catch (e: IOException) {
            L.e(e.message!!)
            return null
        }
    }

    override fun sendEthereum(toAddress: String, credentials: Credentials, amount: Int) {
        SendEthereumAsync(toAddress, credentials, amount).execute()
    }

    private inner class SendEthereumAsync(toAddress: String, credentials: Credentials, amount: Int) :
            AsyncTask<Void?, Void?, Void?>() {

        var toAddress: String? = null
        var amount: Int? = null
        var credentials: Credentials? = null

        init {
            this.toAddress = toAddress
            this.amount = amount
            this.credentials = credentials
        }

        override fun doInBackground(vararg p0: Void?): Void? {
            var amount = BigDecimal(this!!.amount!!);
            var transactionReceipt
                    = Transfer.sendFunds(mWeb3j, credentials, toAddress, amount, Convert.Unit.ETHER)
                    .sendAsync().get()
            var transactionHash = transactionReceipt.getTransactionHash();
            L.d("Hash transaction : ${transactionHash}")
            return null
        }

    }


    override fun sendProductSmartContract(product: Product,
                                          credentials: Credentials,
                                          listener: ListenerResponse) {
        sendSmartContractByAddressAsync(
                product,
                credentials,
                listener).execute()
    }

    private inner class sendSmartContractByAddressAsync(product: Product,
                                                        credentials: Credentials,
                                                        listener: ListenerResponse) :
            AsyncTask<Void?, Void?, String?>() {
        var credentials: Credentials? = null
        var listener: ListenerResponse? = null
        var product: Product? = null
        var errorMessage: String? = null

        init {
            this.credentials = credentials
            this.listener = listener
            this.product = product
        }

        override fun doInBackground(vararg p0: Void?): String? {
            try {
                var contract
                        = Product_sol_product.deploy(
                        mWeb3j,
                        credentials,
                        Product_sol_product.GAS_PRICE,
                        Product_sol_product.GAS_LIMIT,
                        product!!.name,
                        product!!.price,
                        product!!.description,
                        product!!.image,
                        product!!.dateCreated).sendAsync().get()
                return contract.contractAddress
            } catch (e: Exception) {
                errorMessage = e.message;
                return null
            }
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            if (result == null)
                listener!!.onResponse(errorMessage,
                        ResponseStatus.FAILURE,
                        FunctionType.SEND_SMART_CONTRACTION)
            else
                listener!!.onResponse(result,
                        ResponseStatus.SUCCESS,
                        FunctionType.SEND_SMART_CONTRACTION)
        }
    }

    override fun getProductSmartContractByAddress(credentials: Credentials, SCAddress: String, listener: ListenerResponse) {
        getSmartContractByAddressAsync(credentials, SCAddress, listener).execute()
    }

    private inner class getSmartContractByAddressAsync(credentials: Credentials,
                                                       SCAddress: String,
                                                       listener: ListenerResponse) :
            AsyncTask<Void?, Void?, Any?>() {
        var credentials: Credentials? = null
        var listener: ListenerResponse? = null
        var SCAddress: String? = null

        init {
            this.credentials = credentials
            this.listener = listener
            this.SCAddress = SCAddress
        }

        override fun doInBackground(vararg p0: Void?): Any? {
            try {
                var contract = Product_sol_product
                        .load(SCAddress,
                                mWeb3j,
                                credentials,
                                Product_sol_product.GAS_PRICE,
                                Product_sol_product.GAS_LIMIT)

                if (contract.transactionReceipt != null)
                    L.d("Block hash : ${contract?.transactionReceipt?.blockHash}" +
                            "\nTransaction hash : ${contract?.transactionReceipt?.transactionHash}" +
                            "\nBlock number : ${contract?.transactionReceipt?.blockNumber}")
                else
                    L.d("Transaction Receipt is null ")

                var name = contract.name.send()
                var price = contract.price.send()
                var description = contract.description.send()
                var photo = contract.image.send()
                var dateCreated = contract.dateCreated.send()
                return Product(
                        name,
                        price,
                        description,
                        photo,
                        dateCreated)
            } catch (e: Exception) {
                return e.message
            }
        }

        override fun onPostExecute(result: Any?) {
            super.onPostExecute(result)
            if (result is String)
                listener!!.onResponse(
                        result,
                        ResponseStatus.FAILURE, FunctionType.GET_SMART_CONTRACT)
            else if (result is Product)
                listener!!.onResponse(
                        result,
                        ResponseStatus.SUCCESS,
                        FunctionType.GET_SMART_CONTRACT)
        }
    }

    inner class UpdateSmartContractByAddressAsync(credentials: Credentials,
                                                  SCAddress: String,
                                                  product: Product,
                                                  listener: ListenerResponse) :
            AsyncTask<Void?, Void?, Any?>() {
        var credentials: Credentials? = null
        var listener: ListenerResponse? = null
        var SCAddress: String? = null
        var product: Product? = null

        init {
            this.credentials = credentials
            this.listener = listener
            this.SCAddress = SCAddress
            this.product = product
        }

        override fun doInBackground(vararg p0: Void?): Any? {
            try {
                var contract = Product_sol_product
                        .load(SCAddress,
                                mWeb3j,
                                credentials,
                                Product_sol_product.GAS_PRICE,
                                Product_sol_product.GAS_LIMIT)
                var name = contract.changeName(product!!.name).sendAsync().get()
                return null
            } catch (e: Exception) {
                return e.message
            }
        }

        override fun onPostExecute(result: Any?) {
            super.onPostExecute(result)
//            if (result is String)
//                listener!!.onResponse(
//                        result,
//                        ResponseStatus.FAILURE, FunctionType.GET_SMART_CONTRACT)
//            else if (result is Product)
//                listener!!.onResponse(
//                        result,
//                        ResponseStatus.SUCCESS,
//                        FunctionType.GET_SMART_CONTRACT)
        }
    }
}
