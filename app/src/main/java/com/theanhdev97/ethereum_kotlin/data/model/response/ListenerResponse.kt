package com.theanhdev97.ethereum_kotlin.data.model.response

/**
 * Created by DELL on 08/04/2018.
 */
interface ListenerResponse {
    fun onResponse(response: Any?, status: ResponseStatus, functionType: FunctionType)
}