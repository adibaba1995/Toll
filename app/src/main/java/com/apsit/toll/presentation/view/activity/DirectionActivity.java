package com.apsit.toll.presentation.view.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.apsit.toll.R;
import com.apsit.toll.data.network.pojo.autocomplete.Prediction;
import com.apsit.toll.data.network.pojo.toll.Toll;
import com.apsit.toll.presentation.application.TollApplication;
import com.apsit.toll.presentation.presenter.DirectionPresenter;
import com.apsit.toll.presentation.utility.Utility;
import com.apsit.toll.presentation.view.DirectionSelectView;
import com.apsit.toll.presentation.view.viewmodel.TollCarrier;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.PolyUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by adityathanekar on 22/03/17.
 */

public class DirectionActivity extends AppCompatActivity implements DirectionSelectView {

    public static final String EXTRA_SOURCE_CO_ORDINATES = "com.apsit.toll.EXTRA_LOCATION_CO_ORDINATES";
    public static final String EXTRA_DESTINATION_CO_ORDINATES = "com.apsit.toll.EXTRA_DESTINATION_CO_ORDINATES";

    private static final int PICK_LOCATION_REQUEST_CODE = 10;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.source)
    FloatingSearchView source;
    @BindView(R.id.destination)
    FloatingSearchView destination;

    private Unbinder unbinder;

    @Inject
    DirectionPresenter presenter;

    private boolean sourcedest;
    private LatLng sourcepos, destinationpos;
    private String sourcestr, destinationstr;

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direction);
        ((TollApplication) getApplication()).createSelectLocationComponent().inject(this);
        unbinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        toolbar.setTitle("Select Route");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        source.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                presenter.getAutocompleteList(newQuery);
                sourcedest = true;
            }
        });
        source.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                sourcestr = searchSuggestion.getBody();
                locationSelected();
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });
        source.setOnSuggestionsListHeightChanged(new FloatingSearchView.OnSuggestionsListHeightChanged() {
            @Override
            public void onSuggestionsListHeightChanged(float newHeight) {
                destination.setTranslationY(newHeight);
            }
        });
        source.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.place:
                        Intent intent = new Intent(DirectionActivity.this, PickLocationActivity.class);
                        intent.putExtra(PickLocationActivity.EXTRA_SOURCE_DESTINATION, true);
                        startActivityForResult(intent, PICK_LOCATION_REQUEST_CODE);
                }
            }
        });

        destination.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {
                presenter.getAutocompleteList(newQuery);
                sourcedest = false;
            }
        });
        destination.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                destinationstr = searchSuggestion.getBody();
                locationSelected();
            }

            @Override
            public void onSearchAction(String currentQuery) {

            }
        });
        destination.setOnSuggestionsListHeightChanged(new FloatingSearchView.OnSuggestionsListHeightChanged() {
            @Override
            public void onSuggestionsListHeightChanged(float newHeight) {

            }
        });
        destination.setOnMenuItemClickListener(new FloatingSearchView.OnMenuItemClickListener() {
            @Override
            public void onActionMenuItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.place:
                        Intent intent = new Intent(DirectionActivity.this, PickLocationActivity.class);
                        intent.putExtra(PickLocationActivity.EXTRA_SOURCE_DESTINATION, false);
                        startActivityForResult(intent, PICK_LOCATION_REQUEST_CODE);
                }
            }
        });
    }

    private void locationSelected() {

        Single.create(new SingleOnSubscribe<Intent>() {
            @Override
            public void subscribe(SingleEmitter<Intent> e) throws Exception {
                try {
                    Geocoder geocoder = new Geocoder(DirectionActivity.this, Locale.getDefault());
                    Address source = null, destination = null;
                    if (sourcestr != null && destinationstr != null) {
                        source = geocoder.getFromLocationName(sourcestr, 1).get(0);
                        destination = geocoder.getFromLocationName(destinationstr, 1).get(0);
                    }
                    if (source != null && destination != null) {
                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_SOURCE_CO_ORDINATES, new LatLng(source.getLatitude(), source.getLongitude()));
                        intent.putExtra(EXTRA_DESTINATION_CO_ORDINATES, new LatLng(destination.getLatitude(), destination.getLongitude()));
                        e.onSuccess(intent);
                    }
                } catch (Exception ex) {

                }
            }
        }).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Intent>() {
                    @Override
                    public void accept(Intent intent) throws Exception {
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
    }

    @Override
    public void setAutocompleteList(List<Prediction> predictionList) {
        if (sourcedest)
            source.swapSuggestions(predictionList);
        else
            destination.swapSuggestions(predictionList);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PICK_LOCATION_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    try {
                        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                        LatLng location = data.getParcelableExtra(PickLocationActivity.EXTRA_POSITION);
                        Log.d("Aditya", location.latitude + " " + location.longitude);
                        Address address = geocoder.getFromLocation(location.latitude, location.longitude, 1).get(0);
                        boolean sourcedest = data.getBooleanExtra(PickLocationActivity.EXTRA_SOURCE_DESTINATION, true);
                        if (sourcedest)
                            source.setSearchText(address.getFeatureName());
                        else
                            destination.setSearchText(address.getFeatureName());
                    } catch (Exception e) {
                        Log.d("Aditya", e.getMessage());
                    }
                }
                break;
        }
    }
}