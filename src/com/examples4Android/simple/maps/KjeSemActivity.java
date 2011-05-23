package com.examples4Android.simple.maps;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.examples4Android.simple.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class KjeSemActivity extends MapActivity {
	private static final String TAG = "KjeSemActivity";

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	//debug
	//keytool -list -alias androiddebugkey -keystore /Users/matej/.android/debug.keystore -storepass android -keypass android
	//8D:22:34:2A:C0:70:9C:0C:B4:A1:AC:B3:C7:12:2D:1C
	//http://code.google.com/android/maps-api-signup.html
	MapController mapController;
	MyPositionOverlay positionOverlay;

	@Override   
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("Delo!");
		setContentView(R.layout.maps_main);

		MapView myMapView = (MapView)findViewById(R.id.myMapView);
		mapController = myMapView.getController();

		myMapView.setSatellite(true);
		myMapView.setStreetView(true);
		myMapView.displayZoomControls(false);

		mapController.setZoom(17);

		LocationManager locationManager;
		String context = Context.LOCATION_SERVICE;
		locationManager = (LocationManager)getSystemService(context);
		// Add the MyPositionOverlay
		positionOverlay = new MyPositionOverlay();
		List<Overlay> overlays = myMapView.getOverlays();
		overlays.add(positionOverlay);

		Criteria criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setAltitudeRequired(false);
		criteria.setBearingRequired(false);
		criteria.setCostAllowed(true);
		criteria.setPowerRequirement(Criteria.POWER_LOW);
		String provider = locationManager.getBestProvider(criteria, true);

		Location location = locationManager.getLastKnownLocation(provider);
		if (location!=null)
		  my_updateWithNewLocation(location);

		locationManager.requestLocationUpdates(provider, 2000, 10,   
				locationListener);

	}

	private final LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			my_updateWithNewLocation(location);
		}

		public void onProviderDisabled(String provider){
			my_updateWithNewLocation(null);
		}

		public void onProviderEnabled(String provider){ }
		public void onStatusChanged(String provider, int status, 
				Bundle extras){ }
	};
	
    private String getLocationInfo(double latitude, double longitude) {
        Geocoder gc = new Geocoder(this, Locale.getDefault());
        //http://developer.android.com/reference/android/location/Geocoder.html
        
        try {
          List<Address> addresses = gc.getFromLocation(latitude, 
                                                       longitude, 1);
          //obstaja tudi obratno iz imena latatude ... getFromLocationName
          StringBuilder sb = new StringBuilder();
          if (addresses.size() > 0) {
            Address address = addresses.get(0);

            for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
              sb.append(address.getAddressLine(i)).append("\n");
              sb.append(address.getLocality()).append("\n");
              sb.append(address.getPostalCode()).append("\n");
              sb.append(address.getCountryName());
          }
          return  sb.toString();
        } catch (IOException e) {
        	Log.w(TAG,e.toString());
        }
        return "No location found";
    }
	private void my_updateWithNewLocation(Location location) {
		String latLongString;
		TextView myLocationText;
		myLocationText = (TextView)findViewById(R.id.myLocationText);

		if (location != null) {
			positionOverlay.setLocation(location);

			Double geoLat = location.getLatitude()*1E6;
			Double geoLng = location.getLongitude()*1E6;
			GeoPoint point = new GeoPoint(geoLat.intValue(), 
					geoLng.intValue());

			mapController.animateTo(point);

			double lat = location.getLatitude();
			double lng = location.getLongitude();
			String geodata=getLocationInfo(lat, lng);
			latLongString = "Long:" + lng+"\n"+"Lat:" + lat +"\n"+geodata;

			myLocationText.setText(latLongString); 
		}
	}
}