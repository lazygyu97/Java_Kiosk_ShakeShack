package com.company.Order;

import java.util.ArrayList;

import static com.company.Frame.MainFrame.orderList;

public class OrderAddOperation extends OrderAbstractOperation {
    @Override
    public ArrayList operate(String foodName, int price, int count){

        Order order=new Order(foodName,price,count);
        orderList.add(order);
        return orderList;
    }
}
