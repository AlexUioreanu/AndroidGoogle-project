package com.example.travel4;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    public static FloatingActionButton fab;
    private AppBarConfiguration mAppBarConfiguration;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_favorites, R.id.nav_about, R.id.nav_contact, R.id.nav_share)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddTripActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void share(MenuItem item) {
        intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_text));
        Intent intent = this.intent.setType("text/plain");


        Intent shareIntent = Intent.createChooser(this.intent, null);
        startActivity(shareIntent);
    }

    public void openEmailLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_email)));
        startActivity(intent);
    }

    public void openTwitterLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_twitter)));
        startActivity(intent);
    }

    public void openLinkedInLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_linkedin)));
        startActivity(intent);
    }

    public void openGithubLink(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getString(R.string.contact_github)));
        startActivity(intent);
    }
}
