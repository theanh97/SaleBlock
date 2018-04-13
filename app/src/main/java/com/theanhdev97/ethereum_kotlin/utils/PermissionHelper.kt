package com.theanhdev97.ethereum_kotlin.utils

import android.app.Activity
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.util.jar.Manifest

/**
 * Created by DELL on 13/04/2018.
 */
object PermissionHelper {
    fun request(activity: Activity) {
        Dexter.withActivity(activity)
                .withPermissions(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.CAMERA,
                        android.Manifest.permission.ACCESS_NETWORK_STATE)
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                        if (!report!!.areAllPermissionsGranted()) {
                            activity.finish()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: MutableList<PermissionRequest>?, token: PermissionToken?) {
                        token!!.continuePermissionRequest()
                    }
                }
                )
                .onSameThread()
                .check();

    }
}