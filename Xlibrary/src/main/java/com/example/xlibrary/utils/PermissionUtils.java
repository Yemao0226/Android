package com.example.xlibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/28  15:19
 * 权限请求工具类
 * ***************************************
 */
public class PermissionUtils {

  //android6.0之后要动态获取权限
  public static void checkPermission(Activity activity) {

    final int REQUEST_EXTERNAL_STORAGE = 1;

    String[] PERMISSIONS_STORAGE = {
      Manifest.permission.READ_EXTERNAL_STORAGE,
      Manifest.permission.WRITE_EXTERNAL_STORAGE,
      Manifest.permission.READ_PHONE_STATE};

    try {
      //检测是否有写的权限
      int permissionWrite= ActivityCompat.checkSelfPermission(activity,
        "android.permission.WRITE_EXTERNAL_STORAGE");
      int permissionPhone= ActivityCompat.checkSelfPermission(activity,
        "android.permission.READ_PHONE_STATE");
      if (permissionWrite != PackageManager.PERMISSION_GRANTED) {
        // 没有写的权限，去申请写的权限，会弹出对话框
        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
      }
      if(permissionPhone!= PackageManager.PERMISSION_GRANTED) {
        // 没有写的权限，去申请写的权限，会弹出对话框
        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
