package com.company.Food;

import java.util.ArrayList;

import static com.company.Frame.MainFrame.orderList;

public class FoodGetOperation extends FoodAbstractOperation{
    @Override
    public ArrayList foodOperate(String foodName, String mapping, int price, int x, int y, int width, int height){
        return orderList;
    }
}
