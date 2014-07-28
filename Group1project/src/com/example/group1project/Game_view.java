package com.example.group1project;

import java.util.Locale;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.thelikes.thegot2run.*;
import com.young.games.game2048.*;

public class Game_view extends Activity implements TextToSpeech.OnInitListener {
View view;
private TextToSpeech tts;
View report;
Context context;
private String texttospeech="";
private int result=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_view);
		ImageButton i1,i2;
		 tts = new TextToSpeech(this, this);
		
		i1=(ImageButton) findViewById(R.id.imageButton1);
	i2=	(ImageButton) findViewById(R.id.imageButton2);

	i1.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			speakOut("You chose 2048 game");
			Intent in=new Intent(v.getContext(), MainActivity_2048.class);
			startActivityForResult(in,0);
			Toast.makeText(getApplicationContext(), "You chose 2048 game", Toast.LENGTH_SHORT).show();
			
		}
		
	});
	i2.setOnClickListener(new View.OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent in=new Intent(v.getContext(), MainActivity_got2run.class);
			startActivityForResult(in,0);
			Toast.makeText(getApplicationContext(), "You chose The Got2run game", Toast.LENGTH_SHORT).show();
			
		}
		
	});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//onresume
	
		@Override
		protected void onResume() {
		//	Gameview.resume();
			registerReceiver(receiver, new IntentFilter("myproject"));
			super.onResume();
		}
		
		//Onpause
		@Override
		protected void onPause() {
			//view.pause();
			
			 unregisterReceiver(receiver);
			super.onPause();
		}
		private BroadcastReceiver receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				Bundle bundle = intent.getExtras();
				if (bundle!=null) {
					String data = bundle.getString("data");
					Log.i("data in main class", data);
					//if ("stomp".equalsIgnoreCase(data)||"stomp2".equalsIgnoreCase(data)||"stomp3".equalsIgnoreCase(data)) {
					if ("game".equalsIgnoreCase(data)){
						Intent in=new Intent(context, MainActivity_2048.class);
						startActivity(in);
						speakOut("You Chose The 2  0  4  8 game");
					Toast.makeText(getApplicationContext(), "You chose 2048 game", Toast.LENGTH_SHORT).show();
					
					}
					else if ("thirsty".equalsIgnoreCase(data)){
						speakOut("You Chose The Got2run game");
						Intent in=new Intent(context, MainActivity_got2run.class);
						startActivity(in);
					Toast.makeText(getApplicationContext(), "The Got2run game chosen", Toast.LENGTH_SHORT).show();
					
					}
					
					}else{
					Log.i("data in main class", "bundle null");
					//Toast.makeText(getApplicationContext(), "not", Toast.LENGTH_SHORT).show();
				}
				//handleResult(bundle);
			}

			
		};
		

@Override
public void onDestroy() {
    if (tts != null) {
        tts.stop();
        tts.shutdown();
    }
    super.onDestroy();
 }
//called when text to speech start
	@Override
	public void onInit(int status) {
		 // TODO Auto-generated method stub
	    if (status == TextToSpeech.SUCCESS) {
	        //set Language
	        result = tts.setLanguage(Locale.US);
	         tts.setPitch(19); // set pitch level
	         tts.setSpeechRate(1); // set speech speed rate
	        if (result == TextToSpeech.LANG_MISSING_DATA
	                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
	        } else {
	           
	            speakOut(texttospeech);
	        }
	    } else {
	        Log.e("TTS", "Initilization Failed");
	    }
	}
	private void speakOut(String text) {
		
	    //String text = txtText.getText().toString();
	    if(result!=tts.setLanguage(Locale.US))
	    {
	   // Toast.makeText(getApplicationContext(), "Hi Please enter the right Words......  ", Toast.LENGTH_LONG).show();
	    }else
	    {
	    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	    }
	   }
}
