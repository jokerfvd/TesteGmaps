package com.example.gmapsmult;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity implements
		OnMyLocationChangeListener {

	final int RQS_GooglePlayServices = 1;
	private GoogleMap map;
	MapFragment myMapFragment;
	private static final String TAG_MYMAPFRAGMENT = "TAG_MyMapFragment";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);

		FragmentManager myFragmentManager = getFragmentManager();

		myMapFragment = (MapFragment) myFragmentManager
				.findFragmentByTag(TAG_MYMAPFRAGMENT);

		if (myMapFragment == null) {
			myMapFragment = MapFragment.newInstance();

			FragmentTransaction fragmentTransaction = myFragmentManager
					.beginTransaction();
			fragmentTransaction.add(android.R.id.content, myMapFragment,
					TAG_MYMAPFRAGMENT);
			fragmentTransaction.commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.action_settings:
			String LicenseInfo = GooglePlayServicesUtil
					.getOpenSourceSoftwareLicenseInfo(getApplicationContext());
			AlertDialog.Builder LicenseDialog = new AlertDialog.Builder(
					MainActivity.this);
			LicenseDialog.setTitle("Legal Notices");
			LicenseDialog.setMessage(LicenseInfo);
			LicenseDialog.show();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();

		int resultCode = GooglePlayServicesUtil
				.isGooglePlayServicesAvailable(getApplicationContext());

		if (resultCode == ConnectionResult.SUCCESS) {
			// Toast.makeText(getApplicationContext(),"isGooglePlayServicesAvailable SUCCESS",
			// Toast.LENGTH_SHORT).show();

			if (map == null) {
				map = myMapFragment.getMap();
				if (map != null) {

					map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

					map.setMyLocationEnabled(true);

					map.setOnMyLocationChangeListener(this);

				}
			}

		} else {
			GooglePlayServicesUtil.getErrorDialog(resultCode, this,
					RQS_GooglePlayServices);
		}
	}

	private void addmark(double lat,double log,String text, int qt){
		//Bitmap.Config conf = Bitmap.Config.ARGB_8888;
		//Bitmap bmp = Bitmap.createBitmap(58, 80, conf);
		//Canvas canvas1 = new Canvas(bmp);
		 
		// paint defines the text color,
		// stroke width, size
//		Paint color = new Paint();
//		color.setTextSize(18);
//		color.setFakeBoldText(true);
//		color.setColor(Color.BLACK);
//		 
//		//modify canvas
//		canvas1.drawBitmap(BitmapFactory.decodeResource(getResources(),
//		        R.drawable.bar_mark), 0,0, color);
//		canvas1.drawText(String.valueOf(qt), 22, 57, color);
		 
		MarkerOptions mark = new MarkerOptions();
		mark.position(new LatLng(lat,log));
		mark.title(text);
		mark.snippet("pessoas: "+String.valueOf(qt));
		mark.icon(BitmapDescriptorFactory.fromResource(R.drawable.bar_mark));
		map.addMarker(mark);
	}

	private void generateLocations(Location firstloc){
	   double lat = firstloc.getLatitude();
	   double log = firstloc.getLongitude();
	   for(int i = 1; i < 15; i++){
		   this.addmark(lat+0.001*i, log+0.001*i, "Label "+String.valueOf(i), i);
	   }   
   }

	public void onMyLocationChange(Location lastKnownLocation) {
		CameraUpdate myLoc = CameraUpdateFactory
				.newCameraPosition(new CameraPosition.Builder()
						.target(new LatLng(lastKnownLocation.getLatitude(),
								lastKnownLocation.getLongitude())).zoom(16)
						.build());
		map.moveCamera(myLoc);

		map.setOnMyLocationChangeListener(null);
		generateLocations(lastKnownLocation);

	}

}