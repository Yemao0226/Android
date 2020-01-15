package com.example.androidproject.recycleView.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.R;
import java.util.List;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/30  10:23
 * 常规使用适配器
 * ***************************************
 */
public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.ViewHolder>{
  private List<String> list;
  private OnItemClickListener mItemClickListener;
  /**
   * 初始化列表数据
   */
  public NormalAdapter(List<String> list) {
    this.list = list;
  }

  /**
   * 定义组件
   */
  static class ViewHolder extends RecyclerView.ViewHolder {

    TextView normalText;
    RelativeLayout normalLayout;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      normalText = itemView.findViewById(R.id.recycleView_normal_text);
      normalLayout = itemView.findViewById(R.id.recycleView_normal_layout);
    }
  }

  /**
   * 创建布局
   * @param viewGroup
   * @param viewType
   * @return
   */
  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_recycleview_normal_item, viewGroup, false);
    return new ViewHolder(view);
  }

  /**
   * 绑定数据
   * @param viewHolder
   * @param position
   */
  @Override
  public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
    viewHolder.normalText.setText(list.get(position));

    //如果设置了回调，则设置点击事件
    if(mItemClickListener != null){
      viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          mItemClickListener.onItemClick(viewHolder.itemView, position);
        }
      });
    }
  }

  /**
   * 获得子项item的数量
   * @return
   */
  @Override
  public int getItemCount() {
    return list == null ? 0 : list.size();
  }

  //item的回调接口
  public interface OnItemClickListener {
    void onItemClick(View view, int Position);
  }

  //定义一个设置点击监听器的方法
  public void setOnItemClickListener(OnItemClickListener itemClickListener) {
    this.mItemClickListener = itemClickListener;
  }
}
