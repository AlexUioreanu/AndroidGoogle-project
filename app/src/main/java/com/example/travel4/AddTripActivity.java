package com.example.travel4;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.travel4.dialog.AddUrl;
import com.example.travel4.model.Trip;
import com.example.travel4.model.TripDao;
import com.example.travel4.model.TripDataBase;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AddTripActivity extends AppCompatActivity implements AddUrl.TripDialogListener {

    public static final int CAMERA_REQUEST_CODE = 101;
    public static final int GALLERY_REQUEST_CODE = 102;
    TripDataBase dataBase;
    private ImageView openGallery;
    private ImageView uploadedPhotoImageView;
    private EditText tripName;
    private EditText tripDestination;
    private Button addUrl;
    private Uri uriGallery;
    private Button b1;
    private Button b2;
    private Button b3;
    private RadioGroup tripTypeRadioGroup;
    private TextView showPriceEUR;
    private TextView startDateTextView;
    private TextView endDateTextView;
    private String pathToFile;
    private String photoURL;
    private SeekBar priceSeekBar;
    private RatingBar tripRating;
    private TripDao tripDao;

    private Trip editTrip;

    private Bundle extras;

    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        extras = getIntent().getExtras();
        Objects.requireNonNull(this.getSupportActionBar()).hide();

        setContentView(R.layout.activity_new_trip);

        dataBase = TripDataBase.getInstance(getApplicationContext());
        tripDao = dataBase.getTripDao();

        initialazeComponents();

        if (extras != null) {
            long editId = extras.getLong(TripDataBase.DATABASE_NAME);
            editTrip = tripDao.getTrip(editId);

            tripName.setText(editTrip.getName());
            tripDestination.setText(editTrip.getDestination());
            switch (editTrip.getTripType()) {
                case "City Break":
                    tripTypeRadioGroup.check(R.id.radioButtonCityBreak);
                    break;
                case "Sea Side":
                    tripTypeRadioGroup.check(R.id.radioButtonSeaSide);
                    break;
                case "Mountains":
                    tripTypeRadioGroup.check(R.id.radioButtonMountain);
                    break;
            }

            startDateTextView.setText(editTrip.getStartDate());
            endDateTextView.setText(editTrip.getEndDate());

            int updatePriceInt = editTrip.getPrice();
            priceSeekBar.setProgress(updatePriceInt);

            tripRating.setRating((float) editTrip.getRating());
            photoURL = editTrip.getImage();
            if (editTrip.getImage() instanceof String) {
                photoURL = editTrip.getImage();
                Picasso.get().load(photoURL).into(uploadedPhotoImageView);
            } else {
                if (uriGallery != null) {
                    uriGallery = Uri.parse(editTrip.getImage());
                }
            }
        }
    }

    public void addTripOnClick(View view) {
        if (!validations()) {
            String tripName = this.tripName.getText().toString().trim();
            String destination = tripDestination.getText().toString().trim();

            int selectType = tripTypeRadioGroup.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selectType);
            String radioButtonToString = (String) radioButton.getText().toString().trim();

            int priceTrip = priceSeekBar.getProgress();
            float rating = tripRating.getRating();

            String startDate = startDateTextView.getText().toString().trim();
            String endDate = endDateTextView.getText().toString().trim();
            String url;
            if (uriGallery == null) {
                url = photoURL;
            } else {
                url = uriGallery.toString().trim();
            }
            Trip trip = new Trip(tripName, destination, radioButtonToString, priceTrip, rating, startDate, endDate, url);

            if (extras != null) {
                trip.setTripId(editTrip.getTripId());
                tripDao.updateTrip(trip);
            } else {
                tripDao.addTrip(trip);
            }
            Toast.makeText(getApplicationContext(), "Succesfully added", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private boolean validations() {
        boolean hasErrors = false;

        if (tripName.getText().toString().isEmpty()) {
            tripName.setError(getString(R.string.error_trip_name));
            hasErrors = true;
        }
        if (tripDestination.getText().toString().isEmpty()) {
            tripDestination.setError(getString(R.string.error_destination));
            hasErrors = true;
        }

        if (tripTypeRadioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please select a Trip Type", Toast.LENGTH_SHORT).show();
        }

        if (startDateTextView.getText().toString().isEmpty()) {
            startDateTextView.setError(getString(R.string.error_datepick));
            hasErrors = true;
        } else {
            startDateTextView.setError(null);
        }

        if (endDateTextView.getText().toString().isEmpty()) {
            endDateTextView.setError(getString(R.string.error_datepick));
            hasErrors = true;
        } else {
            endDateTextView.setError(null);
        }
        return hasErrors;
    }


    private void initialazeComponents() {

        openGallery = findViewById(R.id.openGalleryImageView);

        b1 = findViewById(R.id.radioButtonCityBreak);
        b2 = findViewById(R.id.radioButtonSeaSide);
        b3 = findViewById(R.id.radioButtonMountain);

        uploadedPhotoImageView = findViewById(R.id.view_finder);
        addUrl = findViewById(R.id.AddUrltrip);

        tripName = findViewById(R.id.tripNameEditText);
        tripDestination = findViewById(R.id.destinationEditText);
        tripTypeRadioGroup = findViewById(R.id.tripTypeRadioGroup);
        showPriceEUR = findViewById(R.id.showPriceEUR);
        startDateTextView = findViewById(R.id.startDateTextView);
        endDateTextView = findViewById(R.id.endDateTextView);
        priceSeekBar = findViewById(R.id.priceSeekBar);
        tripRating = findViewById(R.id.ratingBarTrip);

        priceSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                showPriceEUR.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        openGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(gallery, GALLERY_REQUEST_CODE);
            }
        });

        Dialog dialog = new Dialog(AddTripActivity.this);
        dialog.setContentView(R.layout.dialog);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background));
        }
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;


        addUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUrl addUrl = new AddUrl();
                addUrl.show(getSupportFragmentManager(), "trip add url dialog");
            }
        });
    }

    @Override
    public void applyUrl(String url) {
        photoURL = url;
        Picasso.get().load(url).into(uploadedPhotoImageView);

    }


    public void onClickPickStartDate(View view) {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.getTime();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                startDateTextView.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    public void onClickPickEndDate(View view) {
        final Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.getTime();

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                endDateTextView.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                File f = new File(pathToFile);
                uploadedPhotoImageView.setImageURI(Uri.fromFile(f));
                Log.d("tag", "Photo URL: " + Uri.fromFile(f));
                Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(f);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);

            }
        }

        if (requestCode == GALLERY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                uriGallery = data.getData();
                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String imageFileName = "TravelJournal_" + timeStamp + "." + getFileExt(uriGallery);
                Log.d("tag", "Gallery URI:  " + imageFileName);
                uploadedPhotoImageView.setImageURI(uriGallery);
            }
        }
    }

    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "TravelJournal_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        pathToFile = image.getAbsolutePath();
        return image;
    }
}
