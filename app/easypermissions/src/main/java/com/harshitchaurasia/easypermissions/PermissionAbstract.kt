package com.harshitchaurasia.easypermissions
import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

abstract class PermissionAbstract : ActivityCompat.OnRequestPermissionsResultCallback {
    private val TAG = "EasyPermissions"
    private val RECORD_REQUEST_CODE = 101

    fun jugaadAllPermissions_Automatically(activityContext: Context)
    {
        try {


            var allP = allAvailiblePermisssions(activityContext)
            if(allP[0].contains("nan"))
            {
                return
            }
            setupPermissions(activityContext, allP)
        }
        catch (e:java.lang.Exception){Log.d(TAG,"jugaad error send this report to developer[link]\n$e")}
    }
    public fun setupPermissions(activityContext: Context, permissions: Array<String>) {
        try {


            val askPermissions = ArrayList<String>()
            for (permission in permissions) {
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
        }
        catch (e:java.lang.Exception){  Log.i(TAG, "Error Code 3983\n$e")}
    }

    private fun makeRequest(context: Context, permissions: Array<String>) {
        try {


            ActivityCompat.requestPermissions(
                context as Activity,
                permissions,
                RECORD_REQUEST_CODE
            )
        } catch (e:java.lang.Exception){  Log.i(TAG, "Error Code 3984\n$e")}

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
        }catch (e:java.lang.Exception){  Log.i(TAG, "Error Code 3985\n$e")}
    }
    fun allAvailiblePermisssions(activityContext: Context):Array<String>
    {
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
                    val permissions = pm.queryPermissionsByGroup(name, 0) ?: continue
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
        }
        catch (e:java.lang.Exception){Log.d(TAG,"Jugaad Error Code 5488\nPlease Report this to developer [link]\n $e")}
        return arrayOf("nan")
    }
}