package com.company.Food;


import java.util.ArrayList;

import static com.company.Frame.MainFrame.sideList;


public class SideAddOperation extends FoodAbstractOperation {
    @Override
    public ArrayList foodOperate(String foodName, String mapping, int price, int x, int y, int width, int height){
        Food food = new Food(foodName,mapping,price,x,y,width,height);
        sideList.add(food);
        return sideList;
    }
}
