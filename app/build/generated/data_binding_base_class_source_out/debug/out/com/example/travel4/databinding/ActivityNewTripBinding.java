// Generated by view binder compiler. Do not edit!
package com.example.travel4.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.travel4.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityNewTripBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button AddUrltrip;

  @NonNull
  public final FloatingActionButton addTripButton;

  @NonNull
  public final EditText destinationEditText;

  @NonNull
  public final Button endDateButton;

  @NonNull
  public final TextView endDateTextView;

  @NonNull
  public final ImageView openGalleryImageView;

  @NonNull
  public final TextView priceEUR;

  @NonNull
  public final SeekBar priceSeekBar;

  @NonNull
  public final RadioButton radioButtonCityBreak;

  @NonNull
  public final RadioButton radioButtonMountain;

  @NonNull
  public final RadioButton radioButtonSeaSide;

  @NonNull
  public final RatingBar ratingBarTrip;

  @NonNull
  public final TextView showPriceEUR;

  @NonNull
  public final Button startDateButton;

  @NonNull
  public final TextView startDateTextView;

  @NonNull
  public final EditText tripNameEditText;

  @NonNull
  public final RadioGroup tripTypeRadioGroup;

  @NonNull
  public final CircleImageView viewFinder;

  private ActivityNewTripBinding(@NonNull ConstraintLayout rootView, @NonNull Button AddUrltrip,
      @NonNull FloatingActionButton addTripButton, @NonNull EditText destinationEditText,
      @NonNull Button endDateButton, @NonNull TextView endDateTextView,
      @NonNull ImageView openGalleryImageView, @NonNull TextView priceEUR,
      @NonNull SeekBar priceSeekBar, @NonNull RadioButton radioButtonCityBreak,
      @NonNull RadioButton radioButtonMountain, @NonNull RadioButton radioButtonSeaSide,
      @NonNull RatingBar ratingBarTrip, @NonNull TextView showPriceEUR,
      @NonNull Button startDateButton, @NonNull TextView startDateTextView,
      @NonNull EditText tripNameEditText, @NonNull RadioGroup tripTypeRadioGroup,
      @NonNull CircleImageView viewFinder) {
    this.rootView = rootView;
    this.AddUrltrip = AddUrltrip;
    this.addTripButton = addTripButton;
    this.destinationEditText = destinationEditText;
    this.endDateButton = endDateButton;
    this.endDateTextView = endDateTextView;
    this.openGalleryImageView = openGalleryImageView;
    this.priceEUR = priceEUR;
    this.priceSeekBar = priceSeekBar;
    this.radioButtonCityBreak = radioButtonCityBreak;
    this.radioButtonMountain = radioButtonMountain;
    this.radioButtonSeaSide = radioButtonSeaSide;
    this.ratingBarTrip = ratingBarTrip;
    this.showPriceEUR = showPriceEUR;
    this.startDateButton = startDateButton;
    this.startDateTextView = startDateTextView;
    this.tripNameEditText = tripNameEditText;
    this.tripTypeRadioGroup = tripTypeRadioGroup;
    this.viewFinder = viewFinder;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityNewTripBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityNewTripBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_new_trip, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityNewTripBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.AddUrltrip;
      Button AddUrltrip = ViewBindings.findChildViewById(rootView, id);
      if (AddUrltrip == null) {
        break missingId;
      }

      id = R.id.addTripButton;
      FloatingActionButton addTripButton = ViewBindings.findChildViewById(rootView, id);
      if (addTripButton == null) {
        break missingId;
      }

      id = R.id.destinationEditText;
      EditText destinationEditText = ViewBindings.findChildViewById(rootView, id);
      if (destinationEditText == null) {
        break missingId;
      }

      id = R.id.endDateButton;
      Button endDateButton = ViewBindings.findChildViewById(rootView, id);
      if (endDateButton == null) {
        break missingId;
      }

      id = R.id.endDateTextView;
      TextView endDateTextView = ViewBindings.findChildViewById(rootView, id);
      if (endDateTextView == null) {
        break missingId;
      }

      id = R.id.openGalleryImageView;
      ImageView openGalleryImageView = ViewBindings.findChildViewById(rootView, id);
      if (openGalleryImageView == null) {
        break missingId;
      }

      id = R.id.priceEUR;
      TextView priceEUR = ViewBindings.findChildViewById(rootView, id);
      if (priceEUR == null) {
        break missingId;
      }

      id = R.id.priceSeekBar;
      SeekBar priceSeekBar = ViewBindings.findChildViewById(rootView, id);
      if (priceSeekBar == null) {
        break missingId;
      }

      id = R.id.radioButtonCityBreak;
      RadioButton radioButtonCityBreak = ViewBindings.findChildViewById(rootView, id);
      if (radioButtonCityBreak == null) {
        break missingId;
      }

      id = R.id.radioButtonMountain;
      RadioButton radioButtonMountain = ViewBindings.findChildViewById(rootView, id);
      if (radioButtonMountain == null) {
        break missingId;
      }

      id = R.id.radioButtonSeaSide;
      RadioButton radioButtonSeaSide = ViewBindings.findChildViewById(rootView, id);
      if (radioButtonSeaSide == null) {
        break missingId;
      }

      id = R.id.ratingBarTrip;
      RatingBar ratingBarTrip = ViewBindings.findChildViewById(rootView, id);
      if (ratingBarTrip == null) {
        break missingId;
      }

      id = R.id.showPriceEUR;
      TextView showPriceEUR = ViewBindings.findChildViewById(rootView, id);
      if (showPriceEUR == null) {
        break missingId;
      }

      id = R.id.startDateButton;
      Button startDateButton = ViewBindings.findChildViewById(rootView, id);
      if (startDateButton == null) {
        break missingId;
      }

      id = R.id.startDateTextView;
      TextView startDateTextView = ViewBindings.findChildViewById(rootView, id);
      if (startDateTextView == null) {
        break missingId;
      }

      id = R.id.tripNameEditText;
      EditText tripNameEditText = ViewBindings.findChildViewById(rootView, id);
      if (tripNameEditText == null) {
        break missingId;
      }

      id = R.id.tripTypeRadioGroup;
      RadioGroup tripTypeRadioGroup = ViewBindings.findChildViewById(rootView, id);
      if (tripTypeRadioGroup == null) {
        break missingId;
      }

      id = R.id.view_finder;
      CircleImageView viewFinder = ViewBindings.findChildViewById(rootView, id);
      if (viewFinder == null) {
        break missingId;
      }

      return new ActivityNewTripBinding((ConstraintLayout) rootView, AddUrltrip, addTripButton,
          destinationEditText, endDateButton, endDateTextView, openGalleryImageView, priceEUR,
          priceSeekBar, radioButtonCityBreak, radioButtonMountain, radioButtonSeaSide,
          ratingBarTrip, showPriceEUR, startDateButton, startDateTextView, tripNameEditText,
          tripTypeRadioGroup, viewFinder);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}