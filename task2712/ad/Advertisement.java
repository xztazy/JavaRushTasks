package com.javarush.task.task27.task2712.ad;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Advertisement {
    private Object content;
    private String name;
    private long initialAmount;
    private int hits;
    private int duration;
    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        if (hits !=0) {
            amountPerOneDisplaying = initialAmount / hits;
        }else amountPerOneDisplaying = 0;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public int getHits(){return hits;}

    public void revalidate(){
        if (hits <= 0 ) throw new UnsupportedOperationException();
        else hits--;
    }


}
