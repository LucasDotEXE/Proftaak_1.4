package com.example.estelinglayouttest;

import java.io.Serializable;

public class Attractie implements Serializable {

    private String name, imageUrl;
    private int details;

    public Attractie(String name, String imageUrl, int detailStringRecource) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.details = detailStringRecource;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getDetails() {
        return details;
    }


    @Override
    public String toString() {
        return "Attractie{" +
                "name='" + name + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
