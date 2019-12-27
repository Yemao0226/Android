package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import com.example.androidproject.statusbar.StatusBar;
import com.example.androidproject.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private LinearLayout immersiveStatusBar;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //设置当前状态栏
    StatusBarUtils.setStatusBarWithActivity(this,R.color.colorTitle,false);

    initView();
    initListener();
  }

  /**
   * 初始化组件信息
   * */
  private void initView() {
    immersiveStatusBar = findViewById(R.id.main_immersiveStatusBar);
  }

  /**
   * 初始化各个组件的侦听
   * */
  private void initListener() {
    immersiveStatusBar.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.main_immersiveStatusBar:
        startActivity(new Intent(this, StatusBar.class));
        break;
    }
  }
}

