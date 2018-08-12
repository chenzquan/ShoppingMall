package com.example.shopping_ec.icon;
import com.joanzapata.iconify.Icon;
/**
 * Created by 权 on 2018/8/7.
 */

public enum  EcIcons implements Icon{
    icon_weixin('\ue61e');

    private char character;

    EcIcons(char character) {
        this.character = character;

    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}