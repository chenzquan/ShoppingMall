package com.example.shopping_ec.icon;

import com.joanzapata.iconify.Icon;
import com.joanzapata.iconify.IconFontDescriptor;
/**
 * Created by 权 on 2018/8/7.
 */

public class FontECMedule implements IconFontDescriptor {
    @Override
    public String ttfFileName() {
        return "weixin.ttf";
    }

    @Override
    public Icon[] characters() {
        return EcIcons.values();
    }
}
