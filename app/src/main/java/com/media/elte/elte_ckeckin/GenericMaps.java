package com.media.elte.elte_ckeckin;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class GenericMaps extends ActionBarActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private TextView tvInfo;

    private GoogleMap map;
    MapView mMapView;
    LatLng sourcePosition, destPosition;
    String title, info;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //here we retrieve the intent and bundle that was sent from the listview
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        destPosition = new LatLng(extras.getDouble("LAT"),
                extras.getDouble("LNG"));
        title = extras.getString("TITLE");
        info = extras.getString("INFO");
        // this is used to set the title
        if (title != null)
            toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        tvInfo = (TextView) findViewById(R.id.tvInfo);
        tvInfo.setMovementMethod(LinkMovementMethod.getInstance());
       if (info != null )
        tvInfo.setText(Html.fromHtml(info));

        initiateMap(savedInstanceState);
         createGoogleApi();

    }

    private void createGoogleApi() {
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
    }


    protected void route(LatLng sourcePosition, final LatLng destPosition) {

        final Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                try {
                    Document doc = (Document) msg.obj;
                    GMapV2Direction md = new GMapV2Direction();
                    ArrayList<LatLng> directionPoint = md.getDirection(doc);
                    PolylineOptions rectLine = new PolylineOptions().width(10).color(Color.BLUE);

                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));

                    }

                    map.addPolyline(rectLine);
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(rectLine.getPoints().get(0), 15.0f));
                    if (destPosition!= null)
                    map.addMarker(new MarkerOptions().position(destPosition).title("Destination"));

                    //  tvInfo.setText("DURATION" + md.getDurationText(doc));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            ;
        };

        new GMapV2DirectionAsyncTask(handler, sourcePosition, destPosition, GMapV2Direction.MODE_WALKING).execute();
    }

    private void initiateMap(Bundle savedInstanceState) {
        mMapView = (MapView) findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }


        map = mMapView.getMap();
        map.setMyLocationEnabled(true);
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setZoomControlsEnabled(true);
    }


    @Override
    public void onConnected(Bundle bundle) {
     mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        if (mLastLocation != null) {
        //  tvInfo.setText(mLastLocation.getLatitude() +"   "+String.valueOf(mLastLocation.getLongitude()));
            sourcePosition= new LatLng(mLastLocation.getLatitude(),mLastLocation.getLongitude());
            route(sourcePosition, destPosition);
        }else {
                         //   Intent i = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                         //   startActivity(i);
                map.addMarker(new MarkerOptions().position(destPosition).title("Destination"));
            map.animateCamera(CameraUpdateFactory.newLatLngZoom(destPosition, 15.0f));
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }



    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }
    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }
}
