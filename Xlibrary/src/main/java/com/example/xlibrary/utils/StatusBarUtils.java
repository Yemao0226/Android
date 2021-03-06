package com.example.xlibrary.utils;

import android.app.Activity;
import androidx.fragment.app.Fragment;
import com.gyf.immersionbar.ImmersionBar;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/27  15:20
 * 状态栏工具类
 * ***************************************
 */
public class StatusBarUtils {
  /**
   * 设置Activity中的状态栏
   * @param activity  当前活动
   * @param colorValue 颜色值
   * @param isDark true黑色字体,false白色字体
   */
  public static ImmersionBar setStatusBarWithActivity(Activity activity,int colorValue, boolean isDark){
    ImmersionBar immersionBar = ImmersionBar.with(activity);
    immersionBar.statusBarColor(colorValue)
      .statusBarDarkFont(isDark)
      .fitsSystemWindows(true)
      .init();
    return immersionBar;
  }

  /**
   * 设置Fragment中的状态栏
   * @param fragment 当前碎片
   * @param colorValue 颜色值
   * @param isDark true黑色字体,false白色字体
   */
  public static void setStatusBarWithFragment(Fragment fragment, int colorValue, boolean isDark){
    ImmersionBar.with(fragment)
      .statusBarColor(colorValue)
      .statusBarDarkFont(isDark)
      .fitsSystemWindows(true)
      .init();
  }
}
