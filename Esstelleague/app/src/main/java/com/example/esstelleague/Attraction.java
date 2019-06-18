package com.example.esstelleague;

import java.io.Serializable;

public class Attraction implements Serializable {

    private String mName, mImageUrl;
    private int mDetails;

    public Attraction(String mName, String imageUrl, int detailStringRecource) {
        this.mName = mName;
        this.mImageUrl = imageUrl;
        this.mDetails = detailStringRecource;
    }

    public String getmName() {
        return mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public int getmDetails() {
        return mDetails;
    }

    @Override
    public String toString() {
        return "Attraction{" +
                "mName='" + mName + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }
}
