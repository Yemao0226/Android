package com.example.androidproject.recycleView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.R;
import com.example.androidproject.base.BaseActivity;
import com.example.androidproject.recycleView.adapter.NormalAdapter;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/30  10:23
 * 列表展示:RecycleView(ListView的超集)
 * ***************************************
 */
public class RecycleViewActivity extends BaseActivity {

  @BindView(R.id.normal_out_img)
  LinearLayout normalOutImg;
  @BindView(R.id.normal_title_text)
  TextView normalTitleText;
  @BindView(R.id.recycleView_list)
  RecyclerView recycleViewList;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initData();
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_recycleview;
  }

  /**
   * 初始化数据源
   */
  private void initData() {
    normalTitleText.setText(R.string.app_menu_recycleView);
    setRecycleViewData();
  }

  /**
   * 设置list数据
   */
  private void setRecycleViewData() {
    List<String> dataList = new ArrayList<>();
    for(int i=0;i<30;i++){
      dataList.add("item "+i);
    }
    recycleViewList.setLayoutManager(new LinearLayoutManager(this));
    NormalAdapter normalAdapter = new NormalAdapter(dataList);
    recycleViewList.setAdapter(normalAdapter);

    normalAdapter.setOnItemClickListener(new NormalAdapter.OnItemClickListener() {
      @Override
      public void onItemClick(View view, int Position) {
        Toast.makeText(RecycleViewActivity.this,"this is "+Position,Toast.LENGTH_SHORT).show();
      }
    });
  }

  /**
   * 点击事件
   */
  @OnClick(R.id.normal_out_img)
  public void onViewClicked() {
    finish();
  }
}
