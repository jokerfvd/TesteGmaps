package com.example.testegmaps;

import com.example.testegmaps.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import android.app.Activity;
import android.os.Bundle;


public class MainActivity extends Activity {
	/**
	 * para funcionar no emulador, o target precisa ser Google API
	 */

	static final String EXTRA_MAP = "Teste.GMAPS";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final TextView t = (TextView) findViewById(R.id.textView1);

		Handler mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				t.setText((String) msg.obj);
			}
		};

		new TRequest(mHandler, this).start();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	//gmaps https://developers.google.com/maps/documentation/android/marker#customize_a_marker
	public void displayMap(View view) {
		Intent intent = new Intent(this, MapActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MAP, message);
		startActivity(intent);
		
		
	}

}
