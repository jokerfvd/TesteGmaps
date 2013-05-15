package com.example.testegmaps;

import java.util.List;

import com.example.testegmaps.MainActivity;
import com.example.testegmaps.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class MapActivity extends Activity {

	static final int MAP_REQUEST = 1;  // The request code

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);
		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// Get the message from the intent
	    Intent intent = getIntent();
	    String map = intent.getStringExtra(MainActivity.EXTRA_MAP);
	    
	 // Build the intent
	    Uri location = Uri.parse("geo:0,0?q="+map);
	    Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);

	    // Verify it resolves
	    PackageManager packageManager = getPackageManager();
	    List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
	    boolean isIntentSafe = activities.size() > 0;
	      
	    // Start an activity if it's safe
	    if (isIntentSafe) {
	    	startActivityForResult(mapIntent,MAP_REQUEST);
	    }	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		// Check which request we're responding to
	    if (requestCode == MAP_REQUEST) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	        	Uri mapUri = data.getData();
	        }
	    }
	}

}
