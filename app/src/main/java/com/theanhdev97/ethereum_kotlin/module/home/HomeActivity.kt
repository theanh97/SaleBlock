package com.theanhdev97.ethereum_kotlin.module.home

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.theanhdev97.ethereum_kotlin.R
import com.theanhdev97.ethereum_kotlin.R.color.background_toolbar_bottom_navigation
import com.theanhdev97.ethereum_kotlin.data.api.ethereum.EthClient
import com.theanhdev97.ethereum_kotlin.data.model.normal.Product
import com.theanhdev97.ethereum_kotlin.data.model.response.FunctionType
import com.theanhdev97.ethereum_kotlin.data.model.response.ListenerResponse
import com.theanhdev97.ethereum_kotlin.data.model.response.ResponseStatus
import com.theanhdev97.ethereum_kotlin.data.model.smart_contract.Product_sol_product
import com.theanhdev97.ethereum_kotlin.utils.*
import com.theanhdev97.ethereum_kotlin.utils.QRCodeScanner.ScannerActivity
import org.web3j.abi.FunctionEncoder
import org.web3j.abi.FunctionReturnDecoder
import org.web3j.abi.TypeReference
import org.web3j.abi.datatypes.Function
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthCall
import java.lang.reflect.Type
import java.util.*


class HomeActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.toolbar)
    var toolbar: android.support.v7.widget.Toolbar? = null

    @BindView(R.id.bottom_navigation)
    var bottomNavigation: BottomNavigationViewEx? = null

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        findView()
        initView()
        setEventListener()
    }

    fun demo() {
//        var function = Function(
//                "getPrice",
//                Arrays.asList(),
//                listOf(object : TypeReference<Type<Any>>() {})
//        )
//
//        var encodedFunction = FunctionEncoder.encode(function)
//        var response = EthClient.instance.mWeb3j.ethCall(
//                Transaction.createEthCallTransaction(
//                        MyApplication.getInstance().getAccount().id,
//                        "0xE551C11fF002F75557127eFb318Baa465d26AA63",
//                        encodedFunction),
//                DefaultBlockParameterName.LATEST)
//                .sendAsync()
//                .get()
//        var type = FunctionReturnDecoder.decode(
//                response.value,
//                function.outputParameters
//        )


        //        var product = Product(
//                "Lắc tay kim cương",
//                "61.245.000 VND",
//                "New description",
//                "http://www.hungphatusa.vn/images/Product/large/61268.jpg",
//                "11-04-2018")
//        EthClient.instance.UpdateSmartContractByAddressAsync(
//                MyApplication.getInstance().getAccount().getCredentials(),
//                "0xE551C11fF002F75557127eFb318Baa465d26AA63",
//                product,
//                object :ListenerResponse{
//                    override fun onResponse(response: Any?, status: ResponseStatus, functionType: FunctionType) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//                }
//        ).execute()
//                        "\n" +
//                        "Nhẫn, chất liệu vàng trắng 14K\n" +
//                        "\n" +
//                        " width=\n" +
//                        "\n" +
//                        "Sản phẩm khi Khách Hàng đặt mua sẽ được báo giá lại với size nhẫn tương ứng mà Khách Hàng chọn.\n" +
//                        "Trường hợp không có size phù hợp sản phẩm sẽ được đặt hàng lại trong khoảng thời gian tối đa khoảng 2 tuần.\n" +
//                        "PNJ Shopping Online sẽ lấy sản phẩm với giá bán thực tế tại cửa hàng tương ứng trọng lượng vàng cụ thể báo lại cho khách hàng và hưởng chính sách ưu đãi trên online.",
//                "http://www.hungphatusa.vn/images/Product/large/61268.jpg",
//                "09-04-2018")
//        EthClient.instance.sendProductSmartContract(
//                product,
//                MyApplication.getInstance().getAccount().getCredentials(),
//                object : ListenerResponse {
//                    override fun onResponse(response: Any?, status: ResponseStatus, functionType: FunctionType) {
//                        L.d("New Smart contract : ${response as String}")
//                    }
//                }
//        )

    }

    fun findView() {
        toolbar = findViewById(R.id.toolbar)
        bottomNavigation = findViewById(R.id.bottom_navigation)
//        ButterKnife.bind(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun initView() {
        setSupportActionBar(toolbar)
        bottomNavigation!!.setIconSizeAt(0, 20f, 20f)
        bottomNavigation!!.setIconSizeAt(1, 40f, 40f)
        bottomNavigation!!.setIconSizeAt(2, 20f, 20f)
        bottomNavigation!!.setIconMarginTop(1, 0)
        bottomNavigation!!.setTextSize(12F)
        bottomNavigation!!.currentItem = 1
    }

    fun setEventListener() {
        bottomNavigation!!.setOnNavigationItemSelectedListener(this)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_contact ->
                T.toast(this, "Contact")
            R.id.action_reply ->
                T.toast(this, "Reply")
            R.id.action_scan ->
                clickContactHandle()
            else -> {
            }
        }
        return true
    }

    fun clickContactHandle() {
        var i = Intent(this, ScannerActivity::class.java)
        startActivity(i)
    }
}
