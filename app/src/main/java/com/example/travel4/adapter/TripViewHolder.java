package com.example.travel4.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel4.AddTripActivity;
import com.example.travel4.R;
import com.example.travel4.ViewTripActivity;
import com.example.travel4.model.TripDao;
import com.example.travel4.model.TripDataBase;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class TripViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private final TextView textViewName, textViewDestination, nameTripType, namePrice, tripRating;
    private final ImageButton buttonFavorite;
    private final ImageView tripPicture;
    private TripDao tripDao;

    private long tripId;
    private boolean isFavorite;

    private CardView parentLayout;

    public TripViewHolder(@NonNull View itemView) {
        super(itemView);

        TripDataBase tripDataBase = TripDataBase.getInstance(itemView.getContext());
        tripDao = tripDataBase.getTripDao();

        this.tripPicture = itemView.findViewById(R.id.tripImage);
        textViewName = itemView.findViewById(R.id.textTripName);
        textViewDestination = itemView.findViewById(R.id.textDestination);
        this.buttonFavorite = itemView.findViewById(R.id.button_favorite);
        this.nameTripType = itemView.findViewById(R.id.textTripType);
        this.namePrice = itemView.findViewById(R.id.textPrice);
        this.tripRating = itemView.findViewById(R.id.tripRatingTextView);
        parentLayout = itemView.findViewById(R.id.parent_layout_trip);

        parentLayout.setOnClickListener(this);
        parentLayout.setOnLongClickListener(this);
        buttonFavorite.setOnClickListener(v -> {
            if (!isFavorite) {
                tripDao.setFavorite(tripId);
                setFavorite(true);
                buttonFavorite.setImageResource(R.drawable.ic_baseline_star_24);
                Snackbar.make(v, v.getContext().getString(R.string.favorites_added), BaseTransientBottomBar.LENGTH_SHORT).show();
            } else {
                tripDao.removeFavorite(tripId);
                setFavorite(false);
                buttonFavorite.setImageResource(R.drawable.ic_baseline_star_outline_24);
                Snackbar.make(v, v.getContext().getString(R.string.favorites_removed), BaseTransientBottomBar.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(view.getContext(), ViewTripActivity.class);
        i.putExtra(TripDataBase.DATABASE_NAME, this.getTripId());
        String valueSTRING = String.valueOf(this.getTripId());
        Toast.makeText(view.getContext(), "View  " + valueSTRING, Toast.LENGTH_LONG).show();
        view.getContext().startActivity(i);
    }

    @Override
    public boolean onLongClick(View view) {
        Intent intent = new Intent(view.getContext(), AddTripActivity.class);
        intent.putExtra(TripDataBase.DATABASE_NAME, this.getTripId());
        String valueSTRING = String.valueOf(this.getTripId());
        Toast.makeText(view.getContext(), "Edit " + valueSTRING, Toast.LENGTH_LONG).show();
        view.getContext().startActivity(intent);
        return true;
    }


    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public TextView getTextViewName() {
        return textViewName;
    }

    public TextView getTextViewDestination() {
        return textViewDestination;
    }

    public ImageButton getButtonFavorite() {
        return buttonFavorite;
    }

    public TextView getNameTripType() {
        return nameTripType;
    }

    public TextView getNamePrice() {
        return namePrice;
    }

    public TextView getTripRating() {
        return tripRating;
    }

    public ImageView getTripPicture() {
        return tripPicture;
    }

}