package com.example.travel4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.travel4.R;
import com.example.travel4.adapter.TripAdapter;
import com.example.travel4.model.Trip;
import com.example.travel4.model.TripDao;
import com.example.travel4.model.TripDataBase;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    public TripDataBase tripDataBase;
    public TripDao tripDao;
    private HomeViewModel homeViewModel;
    private RecyclerView recyclerViewTrips;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView.Adapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        tripDataBase = TripDataBase.getInstance(getContext());
        tripDao = tripDataBase.getTripDao();

        //Populate Database just once per session :D
        if (!TripDataBase.getInstance(getContext()).isOpen()) {
            prePopulateData();
        }

        swipeRefreshLayout = view.findViewById(R.id.swiperLayoutHome);

        recyclerViewTrips = view.findViewById(R.id.recyclerViewTripsss);
        recyclerViewTrips.setLayoutManager(new LinearLayoutManager(getActivity()));
        final TripAdapter adapter = new TripAdapter((ArrayList<Trip>) tripDao.getAllTrips());
        recyclerViewTrips.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                tripDao.deleteTrip(adapter.getTripAt(viewHolder.getAdapterPosition()));
                refreshList();
                Toast.makeText(getContext(), "Trip deleted", Toast.LENGTH_LONG).show();
            }
        }).attachToRecyclerView(recyclerViewTrips);


        return view;
    }

    private void prePopulateData() {
        Trip trip1 = new Trip("Honeymoon", "Paris", "City Break", 2500, 5, "13-12-2021", "15-12-2021", "https://www.aerocenter.ro/LocationFirstFileHandler/1200/1200/1023.jpg");
        Trip trip2 = new Trip("Boys Night", "Prague", "City Break", 3800, 4.5, "13-01-2022", "15-12-2022", "https://www.unde-si-cand.net/site/images/illustration/oualler/republique-tcheque-prague_245.jpg");
        Trip trip3 = new Trip("Shopping", "Geneva", "City Break", 5000, 3.5, "20-12-2021", "26-12-2021", "https://s.iw.ro/gateway/g/ZmlsZVNvdXJjZT1odHRwJTNBJTJGJTJG/c3RvcmFnZTA2dHJhbnNjb2Rlci5yY3Mt/cmRzLnJvJTJGc3RvcmFnZSUyRjIwMjAl/MkYwOCUyRjI0JTJGMTIxOTg5NV8xMjE5/ODk1X0dldHR5SW1hZ2VzLTQ3NzE1OTMw/Ni5qcGcmdz0xMjAwJmg9NjMwJnpjPTEm/aGFzaD0yYWMxMWE3YzljMzVkNmE3YmQwYjFhMWUzODZmOGI2Ng==.thumb.jpg");
        Trip trip4 = new Trip("Sky", "Kaprun", "Mountains", 3000, 5, "26-12-2021", "01-01-2022", "https://wallpaperaccess.com/full/3396997.jpg");
        Trip trip5 = new Trip("Sea Party", "Hawaii", "Sea Side", 4500, 5, "16-07-2022", "23-07-2022", "https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fspecials-images.forbesimg.com%2Fimageserve%2F611d773f9569f3956a5e10a0%2FDiamond-Head-State-Park-Aerial%2F960x0.jpg%3Ffit%3Dscale");
        Trip trip6 = new Trip("Visiting Trump", "New York", "City Break", 2000, 1, "12-03-2022", "20-03-2022", "https://i0.wp.com/www.agoda.com/wp-content/uploads/2020/07/Statue-of-Liberty-things-to-do-in-new-york-USA.jpg?ssl=1");

        tripDao.addTrip(trip1);
        tripDao.addTrip(trip2);
        tripDao.addTrip(trip3);
        tripDao.addTrip(trip4);
        tripDao.addTrip(trip5);
        tripDao.addTrip(trip6);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        refreshList();
    }

    private void refreshList() {
        adapter = new TripAdapter((ArrayList<Trip>) tripDao.getAllTrips());
        recyclerViewTrips.setAdapter(adapter);
    }

}
