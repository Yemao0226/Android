package com.example.androidproject.base;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/28  10:11
 * 活动控制器
 * ***************************************
 */
public class ActivityCollector {

  public static List<Activity> activities = new ArrayList<>();

  /**
   * 添加新的活动
   * @param activity
   */
  public static void addActivity(Activity activity) {
    activities.add(activity);
  }

  /**
   * 移除指定活动
   * @param activity
   */
  public static void removeActivity(Activity activity) {
    activities.remove(activity);
  }

  /**
   * 清理所有活动
   */
  public static void finishAll() {
    for (Activity activity : activities) {
      if (!activity.isFinishing()) {
        activity.finish();
      }
    }
    activities.clear();
  }
}
