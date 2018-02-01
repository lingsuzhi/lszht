package com.lszpro.entity.rs;

import com.lszpro.entity.bo.MenuBO;

import java.util.List;

/**
 * @Author liuqi
 * @Date 2017/6/12 18:20
 */
public class MenuNode {

    private MenuBO menu;

    private List<MenuNode> childNode;

    public MenuBO getMenu() {
        return menu;
    }

    public void setMenu(MenuBO menu) {
        this.menu = menu;
    }

    public List<MenuNode> getChildNode() {
        return childNode;
    }

    public void setChildNode(List<MenuNode> childNode) {
        this.childNode = childNode;
    }
}
