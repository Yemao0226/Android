# AndroidJava

#### 介绍
收集平时自己觉得比较好的插件或者功能模块，仅供学习之用

#### 安装教程

直接下载安装即可

#### 已有功能

1.  解决刘海屏，挖孔屏等状态栏问题
2.  引入ButterKnife注解器

#### 已有功能如何使用

状态栏
两种方式（1、自己写的适配 2、引用三方）

 1、自己写适配：
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

  直接调用该方法即可，详细的使用请自行阅读代码理解

 2、引用三方

    //状态栏
    implementation 'com.gyf.immersionbar:immersionbar:3.0.0'


    ImmersionBar.with(this)
    //      .transparentStatusBar()  //透明状态栏，不写默认透明色
    //      .transparentNavigationBar()  //透明导航栏，不写默认黑色(设置此方法，fullScreen()方法自动为true)
    //      .transparentBar()             //透明状态栏和导航栏，不写默认状态栏为透明色，导航栏为黑色（设置此方法，fullScreen()方法自动为true）
    //      .statusBarColor(R.color.colorPrimary)     //状态栏颜色，不写默认透明色
    //      .navigationBarColor(R.color.colorPrimary) //导航栏颜色，不写默认黑色
    //      .barColor(R.color.colorPrimary)  //同时自定义状态栏和导航栏颜色，不写默认状态栏为透明色，导航栏为黑色
    //      .statusBarAlpha(0.3f)  //状态栏透明度，不写默认0.0f
    //      .navigationBarAlpha(0.4f)  //导航栏透明度，不写默认0.0F
    //      .barAlpha(0.3f)  //状态栏和导航栏透明度，不写默认0.0f
    //      .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
    //      .navigationBarDarkIcon(true) //导航栏图标是深色，不写默认为亮色
    //      .autoDarkModeEnable(true) //自动状态栏字体和导航栏图标变色，必须指定状态栏颜色和导航栏颜色才可以自动变色哦
    //      .autoStatusBarDarkModeEnable(true,0.2f) //自动状态栏字体变色，必须指定状态栏颜色才可以自动变色哦
    //      .autoNavigationBarDarkModeEnable(true,0.2f) //自动导航栏图标变色，必须指定导航栏颜色才可以自动变色哦
    //      .flymeOSStatusBarFontColor(R.color.btn3)  //修改flyme OS状态栏字体颜色
    //      .fullScreen(true)      //有导航栏的情况下，activity全屏显示，也就是activity最下面被导航栏覆盖，不写默认非全屏
    //      .hideBar(BarHide.FLAG_HIDE_BAR)  //隐藏状态栏或导航栏或两者，不写默认不隐藏
    //      .addViewSupportTransformColor(toolbar)  //设置支持view变色，可以添加多个view，不指定颜色，默认和状态栏同色，还有两个重载方法
    //      .titleBar(view)    //解决状态栏和布局重叠问题，任选其一
    //      .titleBarMarginTop(view)     //解决状态栏和布局重叠问题，任选其一
    //      .statusBarView(view)  //解决状态栏和布局重叠问题，任选其一
    //      .fitsSystemWindows(true)    //解决状态栏和布局重叠问题，任选其一，默认为false，当为true时一定要指定statusBarColor()，不然状态栏为透明色，还有一些重载方法
    //      .supportActionBar(true) //支持ActionBar使用
    //      .statusBarColorTransform(R.color.orange)  //状态栏变色后的颜色
    //      .navigationBarColorTransform(R.color.orange) //导航栏变色后的颜色
    //      .barColorTransform(R.color.orange)  //状态栏和导航栏变色后的颜色
    //      .removeSupportView(toolbar)  //移除指定view支持
    //      .removeSupportAllView() //移除全部view支持
    //      .navigationBarEnable(true)   //是否可以修改导航栏颜色，默认为true
    //      .navigationBarWithKitkatEnable(true)  //是否可以修改安卓4.4和emui3.x手机导航栏颜色，默认为true
    //      .navigationBarWithEMUI3Enable(true) //是否可以修改emui3.x手机导航栏颜色，默认为true
    //      .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题，默认为false，还有一个重载方法，可以指定软键盘mode
    //      .keyboardMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //单独指定软键盘模式
    //      .setOnKeyboardListener(new OnKeyboardListener() {    //软键盘监听回调，keyboardEnable为true才会回调此方法
    //        @Override
    //        public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
    //          LogUtils.e(isPopup);  //isPopup为true，软键盘弹出，为false，软键盘关闭
    //        }
    //      })
    //      .setOnNavigationBarListener(onNavigationBarListener) //导航栏显示隐藏监听，目前只支持华为和小米手机
    //      .setOnBarListener(OnBarListener) //第一次调用和横竖屏切换都会触发，可以用来做刘海屏遮挡布局控件的问题
    //      .addTag("tag")  //给以上设置的参数打标记
    //      .getTag("tag")  //根据tag获得沉浸式参数
    //      .reset()  //重置所以沉浸式参数
    //      .init();  //必须调用方可应用以上所配置的参数



ButterKnife

  1、在gradle中添加

    android {
      ...
      // Butterknife requires Java 8.
      compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
      }
    }

    dependencies {
      implementation 'com.jakewharton:butterknife:10.2.1'
      annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.1'
    }
  2、在builder中添加

    buildscript {
      repositories {
        mavenCentral()
        google()
       }
      dependencies {
        classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'
      }
    }

  3、在moudle中添加应用

  apply plugin: 'com.android.library'
  apply plugin: 'com.jakewharton.butterknife'

  4、添加一个自动生成代码的插件：zelezny （通过File->settings->Plugins  下载）

#### 附录
如果在使用中有任何疑问，请联系微信Ym02261996，申请时请备注说明理由
