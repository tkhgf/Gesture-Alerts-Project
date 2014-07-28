package com.thelikes.thegot2run;




import java.util.Locale;

import com.example.group1project.R;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity_got2run extends Activity implements TextToSpeech.OnInitListener {
	View view;
	private TextToSpeech tts;
	View report;
	Context context;
	private String texttospeech="";
	private int result=0;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_got2run);
		tts = new TextToSpeech(this, this);
	//	startService(new Intent(this,DataService.class));
		
	}

	public void play(View v)
	{
		//startService(new Intent(this,DataService.class));
		Intent i=new Intent(this,Game_got2run.class);
		speakOut("You Chose The Got2run Game");
		startActivity(i);
	}
	
	public void highscore(View v)
	{
		Intent i=new Intent(this,Highscore_got2run.class);
		startActivity(i);
	}
	
	public void setting(View v)
	{
		Intent i=new Intent(this,Setting_got2run.class);
		startActivity(i);
	}
	
	public void exit(View v)
	{
		System.exit(0);
	}
	
	
	@Override
	protected void onResume() {
	//	Gameview.resume();
		
		
		registerReceiver(receiver, new IntentFilter("myproject"));
		
		
		super.onResume();
	}
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
				if ("hungry".equalsIgnoreCase(data)){
					View view1;
					//play(view1);
				Toast.makeText(getApplicationContext(), "Hunger Gesture", Toast.LENGTH_SHORT).show();
				}
				else if ("game".equalsIgnoreCase(data)){
					View view2;
				//	highscore(view2);
					Toast.makeText(getApplicationContext(), "Game/Stomp Gesture", Toast.LENGTH_SHORT).show();
					}
				else if("thirsty".equalsIgnoreCase(data)){
					View view3;
				//	setting(view3);
					Toast.makeText(getApplicationContext(), "Thirsty Gesture", Toast.LENGTH_SHORT).show();
					}
			
				else if("circle".equalsIgnoreCase(data)){
					View view4;
				//	setting(view3);
					Toast.makeText(getApplicationContext(), "Circle Gesture", Toast.LENGTH_SHORT).show();
					}
			
				//Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_SHORT).show();
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


	
