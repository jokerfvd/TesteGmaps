package com.example.layout;

import android.os.Bundle;
import android.renderscript.Element;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Button radar = (Button) findViewById(R.id.button1);
		radar.setOnClickListener(this);

		Button expandable = (Button) findViewById(R.id.expandable);
		expandable.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button1:
			Intent intent = new Intent(this, Radar.class);
			startActivity(intent);
			break;
		case R.id.expandable:
			this.changeDescriptionHeight(v);
			break;
		default:
			break;
		}
	}

	/**
	 * Variavel para armazenar o valor de altura padrao setado no XML
	 */
	private int expanded;

	/**
	 * Metodo para mudar o tamanho do elemento de descrição
	 * 
	 * @param v
	 */
	private void changeDescriptionHeight(View v) {
		Button bt = (Button) v;
		TextView textview = (TextView) findViewById(R.id.description_text);

		int size = textview.getHeight();

		if (expanded == 0)// seta o valor do XML
			expanded = size;

		Toast.makeText(v.getContext(), String.valueOf(size), Toast.LENGTH_SHORT)
				.show();

		LayoutParams params;

		if (expanded == size) {

			// Seta novo texto para o botao
			bt.setText(v.getContext().getResources()
					.getString(R.string.button_expandable_less));

			// Seta parametros para a aumentar o tamanho
			params = new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.WRAP_CONTENT, 1f);

		} else {
			bt.setText(v.getContext().getResources()
					.getString(R.string.button_expandable_more));
			params = new LayoutParams(LayoutParams.MATCH_PARENT, expanded, 1f);
		}

		textview.setLayoutParams(params);// aplica novo tamanho
	}

}
