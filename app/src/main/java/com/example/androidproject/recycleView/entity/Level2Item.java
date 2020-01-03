package com.example.androidproject.recycleView.entity;

import android.app.Person;
import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.androidproject.recycleView.adapter.NodeAdapter;

/**
 * Author:yemao
 * Time:2020/01/03  15:34
 * Illustration: 抽屉式列表二级目录
 */
public class Level2Item extends AbstractExpandableItem<Person> implements MultiItemEntity {
    public String title;

    public Level2Item(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return NodeAdapter.TYPE_LEVEL_2;
    }

    @Override
    public int getLevel() {
        return 2;
    }
}