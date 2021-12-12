package com.example.travel4.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.travel4.MainActivity;
import com.example.travel4.R;
import com.example.travel4.adapter.TripAdapter;
import com.example.travel4.model.Trip;
import com.example.travel4.model.TripDao;
import com.example.travel4.model.TripDataBase;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerViewTrips;
    private RecyclerView.Adapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    public TripDataBase tripDataBase;
    public TripDao tripDao;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        tripDataBase = TripDataBase.getInstance(getContext());
        tripDao = tripDataBase.getTripDao();


        swipeRefreshLayout = view.findViewById(R.id.swiperLayoutFav);

        recyclerViewTrips = view.findViewById(R.id.recyclerViewTripsssFav);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TripAdapter((ArrayList<Trip>) tripDao.getAllFavoriteTrips());
        recyclerViewTrips.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }

    private void refreshList() {
        adapter = new TripAdapter((ArrayList<Trip>) tripDao.getAllFavoriteTrips());
        recyclerViewTrips.setAdapter(adapter);
    }

}