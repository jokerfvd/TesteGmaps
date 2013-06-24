package com.example.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

public class Radar extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_radar);

		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		// TODO Auto-generated method stub
	}

	@Override
	/**
	 * Adicionado para voltar a tela anterior pelo icone da barra
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();// encerra a activity
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}
