package com.example.esstelleague;

import java.io.Serializable;

/**
 * @author Daphne
 * @author Lucas
 * The Attraction class is used to store data, such as an image, and parse to the detailed activity.
 */

public class Attraction implements Serializable {

    private String mName, mImageUrl;
    private int mDetails;

    /**
     * @param mName Attaction name
     * @param imageUrl Image URL
     * @param detailStringRecource Detailed String resource
     */

    Attraction(String mName, String imageUrl, int detailStringRecource) {
        this.mName = mName;
        this.mImageUrl = imageUrl;
        this.mDetails = detailStringRecource;
    }

    /**
     * @return Name of the attraction.
     */

    String getmName() {
        return mName;
    }

    /**
     * @return Image URL.
     */

    String getmImageUrl() {
        return mImageUrl;
    }

    /**
     * @return Detailed String.
     */

    int getmDetails() {
        return mDetails;
    }

    /**
     * ToString() method for debugging purposes.
     * @return String for debug purposes.
     */

    @Override
    public String toString() {
        return "Attraction{" +
                "mName='" + mName + '\'' +
                ", mImageUrl='" + mImageUrl + '\'' +
                '}';
    }
}
