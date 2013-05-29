package com.example.testegmaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

public class TRequest {
	Handler handler;
	Activity activity;

	public TRequest(Handler textview, Activity activity) {
		this.handler = textview;
		this.activity = activity;
	}

	public void start() {
		if(checkConnection()){
			this.changeText("Buscando IP...");
			new Thread(new Remote(), "HTTP").start();
		}else{
			this.changeText("sem conexão");
		}
	}

	private void changeText(String text) {
		Message msg = new Message();
		msg.obj = text;
		this.handler.sendMessage(msg);
	}
	
	private boolean checkConnection() {
		boolean connected = false;

		final ConnectivityManager conMgr =  (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
		final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
		    connected = true;
		}
		return connected;
	}
	private class Remote implements Runnable {

		@Override
		public void run() {
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet("http://wtfismyip.com/text");
			HttpResponse response;
			try {
				response = client.execute(request);

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				StringBuilder sb = new StringBuilder();
				String line = "";

				while ((line = rd.readLine()) != null) {
					sb.append(line);
				}

				changeText(sb.toString());
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
