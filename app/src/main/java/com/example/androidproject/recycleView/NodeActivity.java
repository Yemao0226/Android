package com.example.androidproject.recycleView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.androidproject.R;
import com.example.xlibrary.base.BaseActivity;
import com.example.androidproject.recycleView.adapter.NodeAdapter;
import com.example.androidproject.recycleView.entity.Level1Item;
import com.example.androidproject.recycleView.entity.Level2Item;
import com.example.xlibrary.utils.ActivityTitleUtils;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Author:yemao
 * Time:2020/01/03  15:00
 * Illustration: 抽屉式列表
 */
public class NodeActivity extends BaseActivity {
  @BindView(R.id.normal_out_img)
  LinearLayout normalOutImg;
  @BindView(R.id.normal_title_text)
  TextView normalTitleText;
  @BindView(R.id.recycleView_list)
  RecyclerView recycleViewList;

  private ArrayList<MultiItemEntity> list;
  private NodeAdapter adapter;

  @Override
  protected int getLayoutId() {
    return R.layout.activity_recycleview;
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

  @Override
  protected void initData() {
    ActivityTitleUtils.setActivityTitle(normalTitleText,R.string.app_recycleView_node);
    setRecycleViewData();
  }

  /**
   * 设置列表数据
   */
  private void setRecycleViewData() {
    list = generateData();
    adapter = new NodeAdapter(list);
    //spanCont为每行个数
    final GridLayoutManager manager = new GridLayoutManager(this, 1);
    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
      @Override
      public int getSpanSize(int position) {
        return adapter.getItemViewType(position) == NodeAdapter.TYPE_LEVEL_1 ? 1 : manager.getSpanCount();
      }
    });
    recycleViewList.setLayoutManager(manager);
    recycleViewList.setAdapter(adapter);
    //展开第Position个的列表
    adapter.expand(0);
  }

  private ArrayList<MultiItemEntity> generateData() {
    int lv1Count = 3;
    int lv2Count = 5;

    String[] nameList = {"老大", "二妹", "三弟", "四妹", "五弟"};
    Random random = new Random();

    ArrayList<MultiItemEntity> res = new ArrayList<>();
    for (int i = 0; i < lv1Count; i++) {
      Level1Item lv1 = new Level1Item("第 " + (i+1)+" 家");
      for (int k = 0; k < lv2Count; k++) {
        Level2Item lv2 = new Level2Item("My name is "+nameList[k]);
        lv1.addSubItem(lv2);
      }
      res.add(lv1);
    }
    return res;
  }
}
