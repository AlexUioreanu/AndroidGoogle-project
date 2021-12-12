package com.example.travel4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel4.R;
import com.example.travel4.model.Trip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TripAdapter extends RecyclerView.Adapter<TripViewHolder> {

    private ArrayList<Trip> tripList = new ArrayList<>();

    public TripAdapter(ArrayList<Trip> tripList) {
        this.tripList = tripList;

    }

    @NonNull
    @Override
    public TripViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_items, parent, false);
        return new TripViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TripViewHolder holder, final int position) {

        Trip currentTrip = tripList.get(position);
        holder.setTripId(currentTrip.getTripId());

        if (currentTrip.isFavorite()) {
            holder.getButtonFavorite().setImageResource(R.drawable.ic_baseline_star_24);
        }

        Picasso.get().load(currentTrip.getImage()).into(holder.getTripPicture());

        holder.setFavorite(currentTrip.isFavorite());
        holder.getTextViewName().setText(currentTrip.getName());
        holder.getTextViewDestination().setText(currentTrip.getDestination());
        holder.getNameTripType().setText(currentTrip.getTripType());
        String priceString = String.valueOf(currentTrip.getPrice() + " €");
        String ratingString = String.valueOf(currentTrip.getRating() + "★");

        holder.getNamePrice().setText(priceString);
        holder.getTripRating().setText(ratingString);
    }


    public Trip getTripAt(int position) {
        return tripList.get(position);
    }

    @Override
    public int getItemCount() {

        return tripList.size();
    }
}