package com.example.shopping_core.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * 作者：Created by 权.
 * 时间：2018/9/12.
 */

public final class ItemBuilder {

    private final LinkedHashMap<BottomTabBean,BottomItemDelegate> ITEMS = new LinkedHashMap<>();

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    public final ItemBuilder addItem(BottomTabBean bottomTabBean,BottomItemDelegate bottomItemDelegate){
        ITEMS.put(bottomTabBean,bottomItemDelegate);
        return this;
    }

    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){
        return ITEMS;
    }

}
