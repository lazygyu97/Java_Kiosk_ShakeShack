package com.company.Food;

import java.util.ArrayList;

public class Food {

    private FoodAbstractOperation foodOperation;
    public  Food(FoodAbstractOperation operation){this.foodOperation=operation;}

    public String foodName;
    public String mapping;
    public int price;
    public int x,y,width,height;

    public Food(String foodName, String mapping, int price, int x, int y, int width, int height){
        this.foodName=foodName;
        this.mapping=mapping;
        this.price=price;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
    }

    public ArrayList Food(String foodName, String mapping, int price, int x, int y, int width, int height){
        ArrayList result = new ArrayList();
        result =foodOperation.foodOperate(foodName,mapping,price,x,y,width,height);
        return result;
    }

}
