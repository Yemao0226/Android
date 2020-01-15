package com.example.androidproject.recycleView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.R;
import com.example.xlibrary.base.BaseActivity;
import com.example.androidproject.recycleView.adapter.NormalAdapter;
import com.example.xlibrary.utils.ActivityTitleUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:yemao
 * Time:2020/01/03  10:40
 * Illustration:列表菜单(RecycleView的常规使用)
 */
public class RecycleViewMenuActivity extends BaseActivity {
  @BindView(R.id.normal_out_img)
  LinearLayout normalOutImg;
  @BindView(R.id.normal_title_text)
  TextView normalTitleText;
  @BindView(R.id.recycleView_menu_rv)
  RecyclerView recycleViewMenuRv;

  private static int NodeRecycleCode = 0;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_recycleview_menu;
  }

  @OnClick(R.id.normal_out_img)
  public void onViewClicked() {
    finish();
  }

  @Override
  protected void initData() {
    ActivityTitleUtils.setActivityTitle(normalTitleText,R.string.app_menu_recycleView_menu);
    setRecycleViewData();
  }

  /**
   * 设置list数据
   */
  private void setRecycleViewData() {
    List<String> dataList = new ArrayList<String>(){{add("抽屉式列表");}};
    recycleViewMenuRv.setLayoutManager(new LinearLayoutManager(this));
    NormalAdapter normalAdapter = new NormalAdapter(dataList);
    recycleViewMenuRv.setAdapter(normalAdapter);

    normalAdapter.setOnItemClickListener(new NormalAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(View view, int Position) {
        if(Position==NodeRecycleCode){
          readyGo(NodeActivity.class);
        }
      }
    });
  }


}
