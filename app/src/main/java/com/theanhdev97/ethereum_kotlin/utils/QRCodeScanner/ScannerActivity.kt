package com.theanhdev97.ethereum_kotlin.utils.QRCodeScanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.zxing.Result
import com.theanhdev97.ethereum_kotlin.module.product.ProductActivity
import com.theanhdev97.ethereum_kotlin.utils.Const
import me.dm7.barcodescanner.zxing.ZXingScannerView

/**
 * Created by DELL on 08/04/2018.
 */
class ScannerActivity : Activity(), ZXingScannerView.ResultHandler {
    var mScannerView: ZXingScannerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)
    }

    override fun handleResult(p0: Result?) {
        var hash = p0!!.text
        var i = Intent(this, ProductActivity::class.java)
        i.putExtra(Const.HASH_INTENT, hash)
        startActivity(i)
        finish()
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera()          // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera()           // Stop camera on pause
    }

}