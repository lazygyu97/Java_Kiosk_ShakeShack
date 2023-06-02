package com.company.Menu;


import java.util.ArrayList;

public class Menu {


    private MenuAbstractOperation menuOperation;
    public  Menu(MenuAbstractOperation operation){this.menuOperation=operation;}


    public String menuName;
    public String mapping;
    public int x,y,width,height;

    public Menu(String menuName,String mapping,int x,int y,int width,int height){
        this.menuName=menuName;
        this.mapping=mapping;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public ArrayList Menu(String menuName,String mapping,int x,int y,int width,int height){
        ArrayList result = new ArrayList();
        result =menuOperation.menuOperate(menuName,mapping,x,y,width,height);
        return result;
    }

}
