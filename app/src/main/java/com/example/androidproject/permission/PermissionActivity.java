package com.example.androidproject.permission;

import android.Manifest;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.androidproject.R;
import com.example.androidproject.base.BaseActivity;
import com.example.androidproject.utils.ActivityTitleUtils;
import com.example.androidproject.utils.PermissionUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/28  14:32
 * 权限请求:以文件读写权限为例
 * ***************************************
 */
public class PermissionActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {

  @BindView(R.id.normal_title_text)
  TextView normalTitleText;
  private String PERMISSION_STORAGE_MSG = "请授予文件读取存储权限，否则影响部分使用功能";
  private final int PERMISSION_STORAGE_CODE = 10001;
  private String[] PERMS = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityTitleUtils.setActivityTitle(normalTitleText,R.string.app_permission);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_permission;
  }

  @Override
  protected void initData() {

  }

  @OnClick({R.id.normal_out_img, R.id.main_permission_normal, R.id.main_permission_third})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.normal_out_img:
        finish();
        break;
      case R.id.main_permission_normal:
        PermissionUtils.checkPermission(this);
        break;
      case R.id.main_permission_third:
        if (EasyPermissions.hasPermissions(this, PERMS)) {
          // 已经申请过权限，做想做的事
          Toast.makeText(this, "你已获得该权限！", Toast.LENGTH_SHORT).show();
        } else {
          // 没有申请过权限，现在去申请
          /**
           *@param host Context对象
           *@param rationale  权限弹窗上的提示语。
           *@param requestCode 请求权限的唯一标识码
           *@param perms 一系列权限
           */
          EasyPermissions.requestPermissions(this, PERMISSION_STORAGE_MSG, PERMISSION_STORAGE_CODE, PERMS);
        }
        break;
    }
  }

  @Override
  public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
    //权限获取成功之后调用
    Toast.makeText(this, "获取该权限成功！", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
    //权限获取失败之后调用
    if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
      new AppSettingsDialog.Builder(this)
        .setTitle("授权访问")
        .setRationale("因为您缺少对应的权限，所以需要您手动开启对应的权限。").build().show();
    }
  }
}
