package com.example.travel4.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TripDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTrip(Trip trip);

    @Update
    void updateTrip(Trip trip);

    @Delete
    void deleteTrip(Trip trip);

    @Query("SELECT * FROM Trip WHERE isFavorite = 1")
    List<Trip> getAllFavoriteTrips();

    @Query("DELETE FROM Trip WHERE id = :tripId")
    void deleteTripById(long tripId);

    @Query("UPDATE Trip SET isFavorite = 1 WHERE id = :tripId")
    void setFavorite(long tripId);

    @Query("UPDATE Trip SET isFavorite = 0 WHERE id = :tripId")
    void removeFavorite(long tripId);

    @Query("SELECT * FROM Trip ORDER BY id ")
    List<Trip> getAllTrips();

    @Query("SELECT * FROM Trip WHERE id = :tripId")
    Trip getTrip(long tripId);

}
