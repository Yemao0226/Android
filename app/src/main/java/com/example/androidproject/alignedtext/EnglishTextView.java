package com.example.androidproject.alignedtext;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.text.Layout;
import android.text.StaticLayout;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androidproject.R;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/28  11:38
 * 保留完整英文型文本框
 * ***************************************
 */
public class EnglishTextView extends TextView {
  private boolean alignOnlyOneLine;

  public EnglishTextView(Context context) {
    this(context, null);
  }

  public EnglishTextView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EnglishTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }


  /**
   * 自定义属性，该属性是在textview只有一行的时候实现左右对齐
   */

  private void init(Context context, AttributeSet attrs) {
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EnglishTextView);
    alignOnlyOneLine = typedArray.getBoolean(R.styleable.EnglishTextView_alignOnlyOneLine, false);
    typedArray.recycle();
  }

  protected void onDraw(Canvas canvas) {
    CharSequence content = getText();
    if (!(content instanceof String)) {
      super.onDraw(canvas);
      return;
    }
    String text = (String) content;
    Layout layout = getLayout();

    for (int i = 0; i < layout.getLineCount(); ++i) {
      int lineBaseline = layout.getLineBaseline(i) + getPaddingTop();
      int lineStart = layout.getLineStart(i);
      int lineEnd = layout.getLineEnd(i);
      if (alignOnlyOneLine && layout.getLineCount() == 1) {//只有一行并且左右对齐
        String line = text.substring(lineStart, lineEnd);
        float width = StaticLayout.getDesiredWidth(text, lineStart, lineEnd, getPaint());
        this.drawScaledText(canvas, line, lineBaseline, width);
      } else if (i == layout.getLineCount() - 1) {//最后一行或者第一行，默认画法不处理
        canvas.drawText(text.substring(lineStart), getPaddingLeft(), lineBaseline, getPaint());
        break;
      } else {//中间行
        String line = text.substring(lineStart, lineEnd);
        float width = StaticLayout.getDesiredWidth(text, lineStart, lineEnd, getPaint());
        this.drawScaledText(canvas, line, lineBaseline, width);
      }
    }

  }

  /**
   * 计算从新绘制每行文本内容
   * StaticLayout处理英文断开换行，有几个好用的方法在代码也体现出来了
   * 1. etLineBaseline可以直接获取到各行的baseline，baseline就是每行的基准线，该行文字就是依据该baseline进行绘制
   * 3. getLineStart，getLineEnd获取每行起始结束的角标
   * 4. getDesiredWidth获取每行的宽度
   */

  private void drawScaledText(Canvas canvas, String line, float baseLineY, float lineWidth) {
    if (line.length() < 1) {
      return;
    }
    float x = getPaddingLeft();
    boolean forceNextLine = line.charAt(line.length() - 1) == 10;
    int length = line.length() - 1;
    if (forceNextLine || length == 0) {
      canvas.drawText(line, x, baseLineY, getPaint());
      return;
    }

    //计算字符之间的宽度
    float d = (getMeasuredWidth() - lineWidth - getPaddingLeft() - getPaddingRight()) / length;

    //重绘所有字符
    for (int i = 0; i < line.length(); ++i) {
      String c = String.valueOf(line.charAt(i));
      float cw = StaticLayout.getDesiredWidth(c, this.getPaint());
      canvas.drawText(c, x, baseLineY, this.getPaint());
      x += cw + d;
    }
  }
}
