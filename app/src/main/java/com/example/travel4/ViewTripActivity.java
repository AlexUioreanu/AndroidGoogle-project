package com.example.travel4;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.travel4.model.Trip;
import com.example.travel4.model.TripDao;
import com.example.travel4.model.TripDataBase;
import com.example.travel4.retrofit.RetrofitClientInstance;
import com.example.travel4.retrofit.Weather;
import com.example.travel4.retrofit.WeatherApi;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewTripActivity extends AppCompatActivity {

    private static final String API_TOKEN = "39faa825fd03798946bc67fe7fb7a0db";

    private TripDao tripDao;
    private Trip trip;
    private ImageButton favoriteButton;
    private TextView tripName, tripDestination, tripPrice, tripType,
            tripStartDate, tripEndDate, tripRating, weatherNow,
            weatherMin, weatherMax, weatherWind, weatherCloud,
            weatherHumidity, weatherTitle;

    private ImageView weatherIcon, tripPicture;
    private ConstraintLayout weatherLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(this.getSupportActionBar()).hide();
        setContentView(R.layout.activity_view_trip);

        TripDataBase dataBase = TripDataBase.getInstance(getApplicationContext());
        tripDao = dataBase.getTripDao();

        Bundle extras = getIntent().getExtras();
        long tripId = extras.getLong(TripDataBase.DATABASE_NAME);
        trip = tripDao.getTrip(tripId);

        initializeComponents();

        if (trip.isFavorite()) {
            favoriteButton.setImageResource(R.drawable.ic_baseline_star_24);
        }

        WeatherApi service = RetrofitClientInstance.getRetrofitInstance().create(WeatherApi.class);
        Call<Weather> call = service.getWeather(trip.getDestination(), API_TOKEN, "metric");
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {
                if (response.isSuccessful()) {
                    Weather main = response.body();

                    assert main != null;
                    if (main.getCurrentTemperature() > 0) {
                        weatherIcon.setImageResource(R.drawable.ic_baseline_wb_sunny);
                    } else {
                        weatherIcon.setImageResource(R.drawable.snowflake);
                    }
                    weatherNow.setText(String.format("%s%s", main.getCurrentTemperature(), getString(R.string.degree_symbol)));
                    weatherMin.setText(String.format("%s%s", main.getMinTemperature(), getString(R.string.degree_symbol)));
                    weatherMax.setText(String.format("%s%s", main.getMaxTemperature(), getString(R.string.degree_symbol)));
                    weatherWind.setText(String.format("%s %s", main.getWind(), getString(R.string.wind_unit)));
                    weatherCloud.setText(String.format("%s%s", main.getClouds(), getString(R.string.cloud_unit)));
                    weatherHumidity.setText(String.format("%s%s", main.getHumidity(), getString(R.string.humidity_unit)));
                    weatherTitle.setText(R.string.the_weather_right_now);
                    weatherLayout.setVisibility(View.VISIBLE);
                } else {
                    weatherTitle.setText(R.string.weather_error);
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                weatherTitle.setText(R.string.weather_error);
            }

        });
    }

    private void initializeComponents() {
        tripName = findViewById(R.id.trip_name);
        tripDestination = findViewById(R.id.trip_destination);
        tripPrice = findViewById(R.id.trip_price);
        tripType = findViewById(R.id.trip_type);
        tripStartDate = findViewById(R.id.trip_start_date);
        tripEndDate = findViewById(R.id.trip_end_date);
        tripRating = findViewById(R.id.trip_rating);
        weatherNow = findViewById(R.id.weather_now);
        weatherMin = findViewById(R.id.weather_min);
        weatherMax = findViewById(R.id.weather_max);
        weatherWind = findViewById(R.id.weather_wind);
        weatherCloud = findViewById(R.id.weather_clouds);
        weatherHumidity = findViewById(R.id.weather_humidity);
        weatherIcon = findViewById(R.id.weather_icon);
        weatherLayout = findViewById(R.id.weather_layout);
        weatherTitle = findViewById(R.id.weather_title);
        favoriteButton = findViewById(R.id.button_favoriteView);
        tripPicture = findViewById(R.id.tripPicView);


        String imageUrl = trip.getImage();
        tripPicture.setImageURI(Uri.parse(imageUrl));
        Picasso.get().load(imageUrl).into(tripPicture);
        tripName.setText(trip.getName());
        tripDestination.setText(trip.getDestination());
        tripPrice.setText(String.valueOf(trip.getPrice()));
        tripType.setText(trip.getTripType());
        tripStartDate.setText(trip.getStartDate());
        tripEndDate.setText(trip.getEndDate());
        tripRating.setText(String.valueOf(trip.getRating()));
    }
}
