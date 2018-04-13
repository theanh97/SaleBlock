package com.theanhdev97.ethereum_kotlin.module.product

import android.graphics.Color
import android.opengl.Visibility
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.*

import com.theanhdev97.ethereum_kotlin.R
import com.theanhdev97.ethereum_kotlin.data.api.ethereum.EthClient
import com.theanhdev97.ethereum_kotlin.data.model.normal.Product
import com.theanhdev97.ethereum_kotlin.data.model.response.FunctionType
import com.theanhdev97.ethereum_kotlin.data.model.response.ListenerResponse
import com.theanhdev97.ethereum_kotlin.data.model.response.ResponseStatus
import com.theanhdev97.ethereum_kotlin.utils.*

class ProductActivity : AppCompatActivity(), ListenerResponse {
    var imageViewPhoto: ImageView? = null
    var textViewProductName: TextView? = null
    var textViewProductDescription: TextView? = null
    var textviewProductPrice: TextView? = null
    var textviewProductDateCreated: TextView? = null
    var mContractAddress: String? = null;
    var swipeRefresh: SwipeRefreshLayout? = null
    var toolbar: Toolbar? = null

    var layoutContent: ScrollView? = null
    var layoutNodData: RelativeLayout? = null
    var textViewError: TextView? = null
    var imageViewNodata: ImageView? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        findView()
        getDataFromIntent()
        repairUI()
        setEventListener()
        getProduct(mContractAddress!!)
    }

    fun findView() {
        toolbar = findViewById(R.id.toolbar)
        imageViewPhoto = findViewById(R.id.imv_photo)
        textViewProductName = findViewById(R.id.tv_product_name)
        textViewProductDescription = findViewById(R.id.tv_product_description)
        textviewProductPrice = findViewById(R.id.tv_product_price)
        textviewProductDateCreated = findViewById(R.id.tv_product_date_created)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        layoutNodData = findViewById(R.id.layout_nodata)
        layoutContent = findViewById(R.id.layout_content)
        textViewError = findViewById(R.id.tv_error)
        imageViewNodata = findViewById(R.id.imv_nodata)
    }

    fun repairUI() {
        setSupportActionBar(toolbar!!)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setTitle("Product")
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setEventListener() {
        toolbar!!.setNavigationOnClickListener({
            finish()
        })

        swipeRefresh!!.setOnRefreshListener({
            getProduct(mContractAddress!!)
        })
    }

    fun getDataFromIntent() {
        mContractAddress = intent.getStringExtra(Const.HASH_INTENT)
    }

    fun getProduct(contractID: String) {
        if (!NetworkHelper.isNetworkAvailable(this)) {
            showViewError(ViewErrorType.NETWORK_ERROR)
            return
        }
        swipeRefresh!!.isRefreshing = true
        EthClient.instance.getProductSmartContractByAddress(
                MyApplication.getInstance().getAccount().getCredentials(),
                contractID,
                this)
    }

    fun showViewError(viewErrorType: ViewErrorType) {
        var error: String? = null
        when (viewErrorType) {
            ViewErrorType.NETWORK_ERROR -> {
                error = getString(R.string.network_error)
            }
            ViewErrorType.PRODUCT_IS_NOT_EXIST -> {
                error = getString(R.string.product_is_not_exist)

            }
            else -> {
            }
        }
        T.toast(this, error!!)
        textViewError!!.text = error
        imageViewNodata!!.setImageResource(R.drawable.no_data)

        layoutNodData!!.visibility = View.VISIBLE
        layoutContent!!.visibility = View.GONE

        swipeRefresh!!.isRefreshing = false
    }

    override fun onResponse(response: Any?, status: ResponseStatus, functionType: FunctionType) {
        if (functionType == FunctionType.GET_SMART_CONTRACT) {
            if (status == ResponseStatus.SUCCESS) {
                var product = response as Product
                loadProductToUI(product)
            } else if (status == ResponseStatus.FAILURE) {
                var errorMessage = response as String
                L.d("Error message : ${errorMessage}")
                showViewError(ViewErrorType.PRODUCT_IS_NOT_EXIST)
            }
            swipeRefresh!!.isRefreshing = false
        }
    }

    fun loadProductToUI(product: Product) {
        layoutNodData!!.visibility = View.GONE
        layoutContent!!.visibility = View.VISIBLE

        ImageHelper.loadImage(this,
                imageViewPhoto!!,
                product.image,
                R.drawable.ic_information)
        textViewProductName!!.setText(product.name)
        textViewProductDescription!!.setText(product.description)
        TextViewSpannableHelper.span(
                textviewProductPrice!!,
                "Price : ${product!!.price}",
                Color.DKGRAY,
                0,
                6)

        TextViewSpannableHelper.span(
                textviewProductDateCreated!!,
                "Date created : ${product!!.dateCreated}",
                Color.DKGRAY,
                0,
                12)
    }
}
