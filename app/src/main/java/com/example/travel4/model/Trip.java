package com.example.travel4.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Trip implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int tripId;

    private String name;
    private String destination;
    private String TripType;
    private int price;
    private double rating;


    @ColumnInfo(name = "start_date")
    private String startDate;

    @ColumnInfo(name = "end_date")
    private String endDate;

    private String image;
    private boolean isFavorite;


    public Trip(String name, String destination, String TripType, int price, double rating, String startDate, String endDate, String image) {

        this.name = name;
        this.destination = destination;
        this.TripType = TripType;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.rating = rating;
        this.image = image;
    }

    public Trip(int tripId, String name, String destination, String tripType, int price, double rating, String startDate, String endDate, String image, boolean isFavorite) {
        this.tripId = tripId;
        this.name = name;
        this.destination = destination;
        TripType = tripType;
        this.price = price;
        this.rating = rating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.isFavorite = isFavorite;
    }


    public String getTripType() {
        return TripType;
    }

    public int getPrice() {
        return price;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public double getRating() {
        return rating;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getTripId() {
        return tripId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "tripId=" + tripId +
                ", name='" + name + '\'' +
                ", destination='" + destination + '\'' +
                ", TripType=" + TripType +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", isFavorite=" + isFavorite +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", rating=" + rating +
                '}';
    }
}
