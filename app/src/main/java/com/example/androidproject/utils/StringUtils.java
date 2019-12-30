package com.example.androidproject.utils;

/**
 * ***************************************
 * Author:yemao
 * Time:2019/12/30  14:23
 * 字符串工具类
 * ***************************************
 */
public class StringUtils {

  /**
   * 获取当前Activity的名称
   * @param longName
   * @return
   */
  public static String getActivityName(String longName){
    while (longName.contains(".")){
      longName = longName.substring(longName.indexOf(".")+1);
    }
    return longName;
  }
}
