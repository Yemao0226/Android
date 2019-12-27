package com.example.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidproject.butterknife.ButterKnifeActivity;
import com.example.androidproject.statusbar.StatusBarActivity;
import com.example.androidproject.utils.StatusBarUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private CardView butterKnife,statusBar;

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
    statusBar = findViewById(R.id.main_statusBar);
    butterKnife = findViewById(R.id.main_butterKnife);
  }

  /**
   * 初始化各个组件的侦听
   * */
  private void initListener() {
    butterKnife.setOnClickListener(this);
    statusBar.setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.main_butterKnife:
        startActivity(new Intent(this, ButterKnifeActivity.class));
        break;
      case R.id.main_statusBar:
        startActivity(new Intent(this, StatusBarActivity.class));
        break;
    }
  }
}

