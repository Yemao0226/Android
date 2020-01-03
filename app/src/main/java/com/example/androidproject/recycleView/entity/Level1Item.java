package com.example.androidproject.recycleView.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.androidproject.recycleView.adapter.NodeAdapter;

/**
 * Author:yemao
 * Time:2020/01/03  15:34
 * Illustration: 抽屉式列表一级目录
 */
public class Level1Item extends AbstractExpandableItem<Level2Item> implements MultiItemEntity {
    public String title;


    public Level1Item(String title) {
        this.title = title;
    }

    @Override
    public int getItemType() {
        return NodeAdapter.TYPE_LEVEL_1;
    }

    @Override
    public int getLevel() {
        return 1;
    }
}
