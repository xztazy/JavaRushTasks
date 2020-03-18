package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.io.IOException;

public class Restaurant {
    public static void main(String[] args) throws IOException {
        Tablet tablet1 = new Tablet(1);
        Cook cook1 = new Cook("Вася the Cook");
        Waiter waiter1 = new Waiter();
        tablet1.addObserver(cook1);
        cook1.addObserver(waiter1);
        tablet1.createOrder();
//        tablet1.createOrder();
//        tablet1.createOrder();
//        tablet1.createOrder();

    }
}
