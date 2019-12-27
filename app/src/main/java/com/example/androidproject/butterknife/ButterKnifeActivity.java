package com.example.androidproject.butterknife;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidproject.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/27  16:02
 * 注解器:绑定组件,绑定文字,绑定侦听
 * ***************************************
 */
public class ButterKnifeActivity extends AppCompatActivity {

  @BindView(R.id.butterKnife_text)
  TextView butterKnifeText;

  @BindString(R.string.app_butterKnife_str) String butterKnifeStr;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_butterknife);

    //对ButterKnife进行绑定，在Activity中不用解绑，但是在适配器Adapter和碎片Fragment中需要在销毁时解绑
    ButterKnife.bind(this);

    //Unbinder unbinder;  用于解绑  unbinder.unbind();
    initData();
  }

  /**
   * 初始化数据源
   */
  private void initData() {
    butterKnifeText.setText(butterKnifeStr);
  }

  @OnClick(R.id.butterKnife_text)
  public void onViewClicked() {
    Toast.makeText(this,"this is ButterKnifeActivity",Toast.LENGTH_SHORT).show();
  }
}
