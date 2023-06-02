package com.company.Order;

import java.util.ArrayList;

public class Order {

    private OrderAbstractOperation operation;
    public Order(OrderAbstractOperation operation){
        this.operation=operation;
    }

    public String food;
    public int price;
    public int count;

    public Order(String food, int price, int count){
        this.food=food;
        this.price=price;
        this.count=count;
    }

    public ArrayList Order(String foodName, int price, int count){
        ArrayList result = new ArrayList();
        result =operation.operate(foodName,price,count);
        return result;
    }

}
