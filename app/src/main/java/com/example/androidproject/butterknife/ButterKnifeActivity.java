package com.example.androidproject.butterknife;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.androidproject.R;
import com.example.xlibrary.base.BaseActivity;
import com.example.xlibrary.utils.ActivityTitleUtils;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/27  16:02
 * 注解器:绑定组件,绑定文字,绑定侦听
 * ***************************************
 */
public class ButterKnifeActivity extends BaseActivity {

  @BindView(R.id.butterKnife_text)
  TextView butterKnifeText;
  @BindString(R.string.app_butterKnife_str)
  String butterKnifeStr;
  @BindView(R.id.normal_title_text)
  TextView normalTitleText;

  /**
   * 因为已经在BaseActivity中绑定了ButterKnife所以这里我注释了
   *
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //setContentView(R.layout.activity_butterknife);

    //对ButterKnife进行绑定，在Activity中不用解绑，但是在适配器Adapter和碎片Fragment中需要在销毁时解绑
    //ButterKnife.bind(this);

    //Unbinder unbinder;  用于解绑  unbinder.unbind();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_butterknife;
  }

  /**
   * 初始化数据源
   */
  protected void initData() {
    ActivityTitleUtils.setActivityTitle(normalTitleText,R.string.app_butterKnife_title);
    butterKnifeText.setText(butterKnifeStr);
  }

  @OnClick({R.id.normal_out_img, R.id.butterKnife_text})
  public void onViewClicked(View view) {
    switch (view.getId()) {
      case R.id.normal_out_img:
        finish();
        break;
      case R.id.butterKnife_text:
        Toast.makeText(this, "this is ButterKnifeActivity", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
