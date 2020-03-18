package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {

    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        this.dishes = ConsoleHelper.getAllDishesForOrder();
    }

    public int getTotalCookingTime(){
        return dishes.stream().mapToInt(value -> value.getDuration()).sum();
    }

    public boolean isEmpty(){
        if (dishes.isEmpty()) return true;
        return false;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder("You order: [");
            dishes.stream().forEach((v) -> sb.append(v + ", "));
            sb.replace(sb.length() - 2, sb.length(), "]");
            sb.append(String.format(" of %s", tablet.toString()));
//            sb.append(", cooking time " + this.getTotalCookingTime() + "m");
            return sb.toString();
        }
    }
}
