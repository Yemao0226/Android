package com.example.androidproject.base;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;
import com.example.androidproject.utils.StatusBarUtils;
import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/28  10:09
 * ***************************************
 */
public abstract class BaseActivity extends AppCompatActivity {
  private Unbinder mUnbinder;
  private ImmersionBar  mImmersionBar;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityCollector.addActivity(this);
    Log.e("---------------------" , "this is "+getClass().getName());
    setContentView(getLayoutId());//把contentView的其它加入
    bindButterKnife();
    setStatusBar();
  }

  /**
   * 绑定对应的布局
   * @return
   */
  protected abstract int getLayoutId();

  /**
   * 绑定ButterKnife
   */
  private void bindButterKnife() {
    mUnbinder = ButterKnife.bind(this);
  }

  public ImmersionBar getBaseStatusBar(){
    return mImmersionBar;
  }

  /**
   * 设置默认状态栏样式
   */
  protected void setStatusBar() {
    mImmersionBar = StatusBarUtils.setStatusBarWithActivity(this, R.color.colorTitle,false);
  }

  @Override
  protected void onDestroy() {
    mUnbinder.unbind();
    super.onDestroy();
  }
}
