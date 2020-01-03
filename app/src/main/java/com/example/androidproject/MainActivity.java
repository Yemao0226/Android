package com.example.androidproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.androidproject.alignedtext.AlignedTextActivity;
import com.example.androidproject.base.BaseActivity;
import com.example.androidproject.butterknife.ButterKnifeActivity;
import com.example.androidproject.permission.PermissionActivity;
import com.example.androidproject.recycleView.RecycleViewActivity;
import com.example.androidproject.statusbar.StatusBarActivity;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  protected void initData() {

  }

  @OnClick({R.id.main_butterKnife, R.id.main_statusBar,R.id.main_alignedText,R.id.main_permission,
    R.id.main_recycleView})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.main_butterKnife:
        startActivity(new Intent(this, ButterKnifeActivity.class));
        break;
      case R.id.main_statusBar:
        startActivity(new Intent(this, StatusBarActivity.class));
        break;
      case R.id.main_alignedText:
        startActivity(new Intent(this, AlignedTextActivity.class));
        break;
      case R.id.main_permission:
        startActivity(new Intent(this, PermissionActivity.class));
        break;
      case R.id.main_recycleView:
        startActivity(new Intent(this, RecycleViewActivity.class));
        break;
    }
  }
}

