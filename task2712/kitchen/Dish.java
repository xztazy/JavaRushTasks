package com.javarush.task.task27.task2712.kitchen;

import java.util.ArrayList;
import java.util.Arrays;

public enum Dish {
    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    Dish(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public static String allDishesToString(){
        StringBuilder sb = new StringBuilder();
        ArrayList<Dish> allDishes = new ArrayList<>(Arrays.asList(Dish.values()));
        allDishes.stream().forEach((d) -> sb.append(d + " "));
        return sb.toString().trim();
    }
}
