package com.example.androidproject.recycleView.adapter;

import android.app.Person;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.IExpandable;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.androidproject.R;
import com.example.androidproject.recycleView.entity.Level1Item;
import com.example.androidproject.recycleView.entity.Level2Item;

import java.util.List;

/**
 * Author:yemao
 * Time:2020/01/03  15:34
 * Illustration:
 */
public class NodeAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

  public static final int TYPE_LEVEL_1 = 1;
  public static final int TYPE_LEVEL_2 = 2;
  /**
   * Same as QuickAdapter#QuickAdapter(Context,int) but with
   * some initialization data.
   *
   * @param data A new list is created out of this one to avoid mutable list
   */
  public NodeAdapter(List<MultiItemEntity> data) {
    super(data);
    addItemType(TYPE_LEVEL_1, R.layout.item_node_lv1);
    addItemType(TYPE_LEVEL_2, R.layout.item_node_lv2);
  }

  @Override
  protected void convert(BaseViewHolder helper, MultiItemEntity item) {
    switch (helper.getItemViewType()){
      case TYPE_LEVEL_1:
        final Level1Item lv1 = (Level1Item) item;
        helper.setText(R.id.title, lv1.title)
          .setImageResource(R.id.iv_head, lv1.isExpanded() ? R.drawable.chat_list_fold : R.drawable.chat_list_fold_right);
        helper.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            int pos = helper.getAdapterPosition();
            if (lv1.isExpanded()) {
              collapse(pos);
            } else {
              expand(pos);
            }
          }
        });
        break;
      case TYPE_LEVEL_2:
        final Level2Item lv2 = (Level2Item) item;
        helper.setText(R.id.level2_title, lv2.title);
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//            int pos = holder.getAdapterPosition();
//            // 先获取到当前 item 的父 positon，再移除自己
//            int positionAtAll = getParentPositionInAll(pos);
//            remove(pos);
//            if (positionAtAll != -1) {
//              IExpandable multiItemEntity = (IExpandable) getData().get(positionAtAll);
//              if (!hasSubItems(multiItemEntity)) {
//                remove(positionAtAll);
//              }
//            }
//          }
//        });
        break;
    }
  }
}
