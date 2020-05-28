package com.br.vxassist.fx.util.iconutil;

import jiconfont.icons.font_awesome.FontAwesome;
import jiconfont.javafx.IconNode;

public class IconUtil {
    public static IconNode getIcon(FontAwesome fontAwesome){
        IconNode iconNode = new IconNode(fontAwesome);
        iconNode.setIconSize(20);
        return iconNode;
    }

    public static IconNode getIcon(FontAwesome fontAwesome, Double size){
        IconNode iconNode = new IconNode(fontAwesome);
        iconNode.setIconSize(size);
        return iconNode;
    }


}
