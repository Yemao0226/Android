package com.example.androidproject.recycleView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidproject.R;
import com.example.androidproject.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:yemao
 * Time:2020/01/03  10:40
 * Illustration:列表菜单
 */
public class RecycleViewMenuActivity extends BaseActivity {
  @BindView(R.id.normal_out_img)
  LinearLayout normalOutImg;
  @BindView(R.id.normal_title_text)
  TextView normalTitleText;
  @BindView(R.id.recycleView_menu_rv)
  RecyclerView recycleViewMenuRv;

  @Override
  protected int getLayoutId() {
    return R.layout.activity_recycleview_menu;
  }

  @Override
  protected void initData() {
    
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }

  @OnClick(R.id.normal_out_img)
  public void onViewClicked() {
    finish();
  }
}
