package com.company.Menu;

import java.util.ArrayList;

import static com.company.Frame.MainFrame.menuList;


public class MenuAddOperation extends MenuAbstractOperation{
    @Override
    public ArrayList menuOperate(String menuName, String mapping, int x, int y, int width, int height){
        Menu menu = new Menu(menuName,mapping,x,y,width,height);
        menuList.add(menu);
        return menuList;
    }
}
