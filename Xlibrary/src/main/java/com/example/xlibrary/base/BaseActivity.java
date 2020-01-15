package com.example.xlibrary.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.xlibrary.utils.StatusBarUtils;
import com.example.xlibrary.utils.StringUtils;
import com.example.xlibrary.R;
import com.gyf.immersionbar.ImmersionBar;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/28  10:09
 * 基础Activity
 * ***************************************
 */
public abstract class BaseActivity extends AppCompatActivity {
  private Unbinder mUnbinder;
  private ImmersionBar  mImmersionBar;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActivityCollector.addActivity(this);
    Log.e("---------------------" , "this is "+ StringUtils.getActivityName(getClass().getName()));
    setContentView(getLayoutId());//把contentView的其它加入
    bindButterKnife();
    setStatusBar();
    initData();
  }

  /**
   * 绑定对应的布局
   * @return
   */
  protected abstract int getLayoutId();

  /**
   * 初始化数据
   */
  protected abstract void initData();

  /**
   * 绑定ButterKnife
   */
  private void bindButterKnife() {
    mUnbinder = ButterKnife.bind(this);
  }

  /**
   * 获得默认状态栏设置
   * @return mImmersionBar
   */
  public ImmersionBar getBaseStatusBar(){
    return mImmersionBar;
  }

  /**
   * 设置默认状态栏样式
   */
  protected void setStatusBar() {
    mImmersionBar = StatusBarUtils.setStatusBarWithActivity(this, R.color.colorTitle,false);
  }

  /**
   * 活动销毁时解绑
   */
  @Override
  protected void onDestroy() {
    mUnbinder.unbind();
    super.onDestroy();
  }

  /**
   * 跳转页面
   * @param clz
   * @param <T>
   */
  protected <T extends Activity> void readyGo(Class<T> clz) {
    readyGo(clz, null);
  }

  protected <T extends Activity> void readyGo(Class<T> clz, Bundle bundle) {
    Intent intent = new Intent(this, clz);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    startActivity(intent);
  }

  protected <T extends Activity> void readyGoThenKill(Class<T> clz, Bundle bundle) {
    readyGo(clz, bundle);
    finish();
  }

  protected <T extends Activity> void readyGoThenKill(Class<T> clz) {
    readyGo(clz, null);
    finish();
  }

  protected <T extends Activity> void readyGoForResult(Class<T> clz, Bundle bundle, int code) {
    Intent intent = new Intent(this, clz);
    if (bundle != null) {
      intent.putExtras(bundle);
    }
    startActivityForResult(intent, code);
  }

  protected <T extends Activity> void readyGoForResult(Class<T> clz, int code) {
    readyGoForResult(clz, null, code);
  }
}
