package com.example.androidproject.alignedtext;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androidproject.R;
import com.example.xlibrary.alignedtext.AlignTextView;
import com.example.xlibrary.alignedtext.EnglishTextView;
import com.example.xlibrary.base.BaseActivity;
import com.example.xlibrary.utils.ActivityTitleUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * ***************************************
 * Author:yema
 * Time:2019/12/28  11:13
 * 对齐的文本框
 * ***************************************
 */
public class AlignedTextActivity extends BaseActivity {

  @BindView(R.id.alignedText_normal)
  TextView alignedTextNormal;
  @BindView(R.id.alignedText_align)
  AlignTextView alignedTextAlign;
  @BindView(R.id.alignedText_English)
  EnglishTextView alignedTextEnglish;
  @BindView(R.id.normal_title_text)
  TextView normalTitleText;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  protected int getLayoutId() {
    return R.layout.activity_alignedtext;
  }

  protected void initData() {
    ActivityTitleUtils.setActivityTitle(normalTitleText,R.string.app_alignedText_choose_alignedText);
    alignedTextNormal.setText(R.string.app_alignedText_text);
    alignedTextAlign.setText(R.string.app_alignedText_text);
    alignedTextEnglish.setText(R.string.app_alignedText_text);
  }

  @OnClick(R.id.normal_out_img)
  public void onViewClicked() {
    finish();
  }
}
