package com.example.androidproject.statusbar;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/26  10:08
 * ***************************************
 */

public class StatusBarUtil {
  public final static int TYPE_MIUI = 0;
  public final static int TYPE_FLYME = 1;
  public final static int TYPE_M = 3;//6.0

  @IntDef({TYPE_MIUI, TYPE_FLYME,TYPE_M})
  @Retention(RetentionPolicy.SOURCE)
  @interface ViewType {
  }


  /**
   * 调用此方法来设置状态栏
   * @param activity
   * @param color
   * @param dark
   */
  public static void setStatusBar(AppCompatActivity activity, String color, boolean dark) {
    Window window = activity.getWindow();
    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      window.setStatusBarColor(Color.TRANSPARENT);
    }
    //设置页面全屏显示
    WindowManager.LayoutParams lp = window.getAttributes();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
      lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
    }
    //设置页面延伸到刘海区显示
    window.setAttributes(lp);
    setStatusBarLightMode(activity, Color.parseColor(color));
    setLightStatusBar(activity,dark);
  }


  /**
   * 修改状态栏颜色，支持4.4以上版本
   *
   * @param colorId 颜色
   */
  public static void setStatusBarColor(AppCompatActivity activity, int colorId) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = activity.getWindow();
      window.setStatusBarColor(colorId);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      //使用SystemBarTintManager,需要先将状态栏设置为透明
      setTranslucentStatus(activity);
          /*  SystemBarTintManager systemBarTintManager = new SystemBarTintManager(activity);
            systemBarTintManager.setStatusBarTintEnabled(true);//显示状态栏
            systemBarTintManager.setStatusBarTintColor(colorId);//设置状态栏颜色*/
    }
  }

  /**
   * 设置状态栏透明
   */
  @TargetApi(19)
  public static void setTranslucentStatus(AppCompatActivity activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      //5.x开始需要把颜色设置透明，否则导航栏会呈现系统默认的浅灰色
      Window window = activity.getWindow();
      View decorView = window.getDecorView();
      //两个 flag 要结合使用，表示让应用的主体内容占用系统状态栏的空间
      int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
      decorView.setSystemUiVisibility(option);
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.TRANSPARENT);
      //导航栏颜色也可以正常设置
      //window.setNavigationBarColor(Color.TRANSPARENT);
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      Window window = activity.getWindow();
      WindowManager.LayoutParams attributes = window.getAttributes();
      int flagTranslucentStatus = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
      attributes.flags |= flagTranslucentStatus;
      //int flagTranslucentNavigation = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
      //attributes.flags |= flagTranslucentNavigation;
      window.setAttributes(attributes);
    }
  }


  /**
   *  代码实现android:fitsSystemWindows
   *
   * @param activity
   */
  public static void setRootViewFitsSystemWindows(AppCompatActivity activity, boolean fitSystemWindows) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      ViewGroup winContent = (ViewGroup) activity.findViewById(android.R.id.content);
      if (winContent.getChildCount() > 0) {
        ViewGroup rootView = (ViewGroup) winContent.getChildAt(0);
        if (rootView != null) {
          rootView.setFitsSystemWindows(fitSystemWindows);
        }
      }
    }

  }


  /**
   * 设置状态栏深色浅色切换
   */
  public static boolean setStatusBarDarkTheme(AppCompatActivity activity, boolean dark) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        setStatusBarFontIconDark(activity, TYPE_M, dark);
      } else if (OSUtils.isMiui()) {
        setStatusBarFontIconDark(activity, TYPE_MIUI, dark);
      } else if (OSUtils.isFlyme()) {
        setStatusBarFontIconDark(activity, TYPE_FLYME, dark);
      } else {//其他情况
        return false;
      }

      return true;
    }
    return false;
  }

  /**
   * 设置 状态栏深色浅色切换
   */
  public static boolean setStatusBarFontIconDark(AppCompatActivity activity, @ViewType int type, boolean dark) {
    switch (type) {
      case TYPE_MIUI:
        return setMiuiUI(activity, dark);
      case TYPE_FLYME:
        return setFlymeUI(activity, dark);
      case TYPE_M:
      default:
        return setCommonUI(activity,dark);
    }
  }

  /**
   *  设置6.0 状态栏深色浅色切换
   */
  public static boolean setCommonUI(AppCompatActivity activity, boolean dark) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      View decorView = activity.getWindow().getDecorView();
      if (decorView != null) {
        int vis = decorView.getSystemUiVisibility();
        if (dark) {
          vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
          vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        }
        if (decorView.getSystemUiVisibility() != vis) {
          decorView.setSystemUiVisibility(vis);
        }
        return true;
      }
    }
    return false;

  }

  /**
   * 设置Flyme 状态栏深色浅色切换
   */
  public static boolean setFlymeUI(AppCompatActivity activity, boolean dark) {
    try {
      Window window = activity.getWindow();
      WindowManager.LayoutParams lp = window.getAttributes();
      Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
      Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
      darkFlag.setAccessible(true);
      meizuFlags.setAccessible(true);
      int bit = darkFlag.getInt(null);
      int value = meizuFlags.getInt(lp);
      if (dark) {
        value |= bit;
      } else {
        value &= ~bit;
      }
      meizuFlags.setInt(lp, value);
      window.setAttributes(lp);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 设置MIUI 状态栏深色浅色切换
   */
  public static boolean setMiuiUI(AppCompatActivity activity, boolean dark) {
    try {
      Window window = activity.getWindow();
      Class<?> clazz = activity.getWindow().getClass();
      @SuppressLint("PrivateApi") Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
      Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
      int darkModeFlag = field.getInt(layoutParams);
      Method extraFlagField = clazz.getDeclaredMethod("setExtraFlags", int.class, int.class);
      extraFlagField.setAccessible(true);
      if (dark) {    //状态栏亮色且黑色字体
        extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
      } else {
        extraFlagField.invoke(window, 0, darkModeFlag);
      }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * 获取状态栏高度
   */
  public static int getStatusBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources().getIdentifier(
      "status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }

  /**
   * 设置状态栏模式
   * @param activity
   * @param color
   */
  public static void setStatusBarLightMode(AppCompatActivity activity, int color) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      //判断是否为小米或魅族手机，如果是则将状态栏文字改为黑色
      if (MIUISetStatusBarLightMode(activity, true)
        || FlymeSetStatusBarLightMode(activity, true)) {
        //设置状态栏为指定颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0
          activity.getWindow().setStatusBarColor(color);
        }
        else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4
          //调用修改状态栏颜色的方法
          setStatusBarColor(activity,color);
        }
      }
      else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //如果是6.0以上将状态栏文字改为黑色，并设置状态栏颜色
        activity.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        activity.getWindow().setStatusBarColor(color);

        //fitsSystemWindow 为 false, 不预留系统栏位置.
        ViewGroup mContentView = (ViewGroup) activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
          mChildView.setFitsSystemWindows(true);
          ViewCompat.requestApplyInsets(mChildView);
        }
      }
    }
  }

  /**
   * 适配小米机型
   * @param activity
   * @param darkmode
   * @return
   */
  private static boolean MIUISetStatusBarLightMode(AppCompatActivity activity, boolean darkmode) {
    boolean result = false;
    Class<? extends Window> clazz = activity.getWindow().getClass();
    try {
      int darkModeFlag = 0;
      Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
      Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
      darkModeFlag = field.getInt(layoutParams);
      Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
      extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 适配魅族机型
   * @param activity
   * @param darkmode
   * @return
   */
  private static boolean FlymeSetStatusBarLightMode(AppCompatActivity activity, boolean darkmode) {
    boolean result = false;
    try {
      WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
      Field darkFlag = WindowManager.LayoutParams.class
        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
      Field meizuFlags = WindowManager.LayoutParams.class
        .getDeclaredField("meizuFlags");
      darkFlag.setAccessible(true);
      meizuFlags.setAccessible(true);
      int bit = darkFlag.getInt(null);
      int value = meizuFlags.getInt(lp);
      if (darkmode) {
        value |= bit;
      } else {
        value &= ~bit;
      }
      meizuFlags.setInt(lp, value);
      activity.getWindow().setAttributes(lp);
      result = true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  /**
   * 设置状态栏字体为白色还是黑色
   * @param activity
   * @param dark
   */
  public static void setLightStatusBar(final AppCompatActivity activity, final boolean dark) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      switch (RomUtils.getLightStatusBarAvailableRomType()) {
        case RomUtils.AvailableRomType.MIUI:
          MIUISetStatusBarLightMode(activity, dark);
          break;

        case RomUtils.AvailableRomType.FLYME:
          FlymeSetStatusBarLightMode(activity, dark);
          break;

        case RomUtils.AvailableRomType.ANDROID_NATIVE:
          AndroidNativeLightStatusBar(activity, dark);
          break;

      }
    }
  }

  private static void AndroidNativeLightStatusBar(AppCompatActivity activity, boolean dark) {
    View decor = activity.getWindow().getDecorView();
    if (dark) {
      decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    } else {
      decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    }
  }
}
