package com.company.Food;


import java.util.ArrayList;

import static com.company.Frame.MainFrame.drinkList;


public class DrinkAddOperation extends FoodAbstractOperation {
    @Override
    public ArrayList foodOperate(String foodName, String mapping, int price, int x, int y, int width, int height){
        Food food = new Food(foodName,mapping,price,x,y,width,height);
        drinkList.add(food);
        return drinkList;
    }
}
