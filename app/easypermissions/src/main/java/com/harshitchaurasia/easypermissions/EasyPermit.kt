package com.harshitchaurasia.easypermissions

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

/*
        MIT License

        Copyright (c) 2020 Harshit Chaurasia

        Permission is hereby granted, free of charge, to any person obtaining a copy
        of this software and associated documentation files (the "Software"), to deal
        in the Software without restriction, including without limitation the rights
        to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
        copies of the Software, and to permit persons to whom the Software is
        furnished to do so, subject to the following conditions:

        The above copyright notice and this permission notice shall be included in all
        copies or substantial portions of the Software.

        THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
        IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
        FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
        AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
        LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
        OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
        SOFTWARE.

        We welcome contributer : https://github.com/harshit0209/EasyPermit/
 */
abstract class EasyPermit : ActivityCompat.OnRequestPermissionsResultCallback {
    val TAG = "EasyPermissions"
    private val RECORD_REQUEST_CODE = 101

    fun jugaadAllPermissionsAutomatically(activityContext: Context) {
        try {


            val allP = allAvailiblePermisssions(activityContext)
            if (allP[0].contains("nan")) {
                return
            }
            checkAndGetPermissions(activityContext, allP)
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "jugaad error send this report to developer [https://github.com/harshit0209/EasyPermit]\n$e")
        }
    }

     fun checkAndGetPermissions(
        activityContext: Context,
        arrayOfPermissions: Array<String>
    ) {
        try {


            val askPermissions = ArrayList<String>()
            for (permission in arrayOfPermissions) {
                val permissionState = ContextCompat.checkSelfPermission(
                    activityContext,
                    permission
                )
                if (permissionState != PackageManager.PERMISSION_GRANTED)
                    askPermissions.add(permission)
            }
            if (askPermissions.size > 0) {
                Log.i(TAG, "Asking for Permission...")
                makeRequest(activityContext, askPermissions.toTypedArray())
            }
        } catch (e: java.lang.Exception) {
            Log.i(TAG, "Error Code 3983\n$e")
        }
    }

    private fun makeRequest(context: Context, permissions: Array<String>) {
        try {


            ActivityCompat.requestPermissions(
                context as Activity,
                permissions,
                RECORD_REQUEST_CODE
            )
        } catch (e: java.lang.Exception) {
            Log.i(TAG, "Error Code 3984\n$e")
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        try {


            when (requestCode) {
                RECORD_REQUEST_CODE -> {

                    if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {

                        Log.i(TAG, "Permission has been denied by user")
                    } else {
                        Log.i(TAG, "Permission has been granted by user")
                    }
                }
            }
        } catch (e: java.lang.Exception) {
            Log.i(TAG, "Error Code 3985\n$e")
        }
    }

    private fun allAvailiblePermisssions(activityContext: Context): Array<String> {
        try {


            val askPermissions = ArrayList<String>()
            val pm = activityContext.packageManager
            val groupList = pm.getAllPermissionGroups(0)
            // ungrouped permissions
            groupList.add(null)
            for (permissionGroup in groupList) {
                val name = permissionGroup?.name
//                  Log.d("AppLog", "permission group `$name`")
                try {
                    val permissions = pm.queryPermissionsByGroup(name!!, 0) ?: continue
                    for (permission in permissions) {
                        if (permission.name.contains("android") && permission.loadDescription(pm) != null) {
                            askPermissions.add(permission.name)
//                              Log.d(
//                                  "AppLog",
//                                  "${permission.name} - ${permission.loadDescription(pm)}"
//                              )
                        }
                    }
                } catch (ex: Exception) {
                    //   Log.d(
//                          "AppLog",
//                          "exception while getting permissions of permission group: `$name`"
//                      )
                }
//                  Log.d("AppLog", "-----")
            }
            return askPermissions.toTypedArray()
        } catch (e: java.lang.Exception) {
            Log.d(TAG, "Jugaad Error Code 5488\nPlease Report this to developer [https://github.com/harshit0209/EasyPermit]\n $e")
        }
        return arrayOf("nan")
    }
}