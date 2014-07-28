package com.example.group1project;

import java.util.Locale;

import org.achartengine.chartdemo.demo.chart.AverageCubicTemperatureChart;
import org.achartengine.chartdemo.demo.chart.AverageTemperatureChart;
import org.achartengine.chartdemo.demo.chart.BudgetDoughnutChart;
import org.achartengine.chartdemo.demo.chart.BudgetPieChart;
import org.achartengine.chartdemo.demo.chart.CombinedTemperatureChart;
import org.achartengine.chartdemo.demo.chart.IDemoChart;
import org.achartengine.chartdemo.demo.chart.MultipleTemperatureChart;
import org.achartengine.chartdemo.demo.chart.ProjectStatusBubbleChart;
import org.achartengine.chartdemo.demo.chart.ProjectStatusChart;
import org.achartengine.chartdemo.demo.chart.SalesBarChart;
import org.achartengine.chartdemo.demo.chart.SalesComparisonChart;
import org.achartengine.chartdemo.demo.chart.SalesGrowthChart;
import org.achartengine.chartdemo.demo.chart.SalesStackedBarChart;
import org.achartengine.chartdemo.demo.chart.ScatterChart;
import org.achartengine.chartdemo.demo.chart.SensorValuesChart;
import org.achartengine.chartdemo.demo.chart.TemperatureChart;
import org.achartengine.chartdemo.demo.chart.TrigonometricFunctionsChart;
import org.achartengine.chartdemo.demo.chart.WeightDialChart;

import com.example.group1project.DataService;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.speech.tts.TextToSpeech;


public class MainActivity extends Activity  implements TextToSpeech.OnInitListener {
	private IDemoChart[] mCharts = new IDemoChart[] { new AverageTemperatureChart(),
		      new AverageCubicTemperatureChart(), new SalesStackedBarChart(), new SalesBarChart(),
		      new TrigonometricFunctionsChart(), new ScatterChart(), new SalesComparisonChart(),
		      new ProjectStatusChart(), new SalesGrowthChart(), new BudgetPieChart(),
		      new BudgetDoughnutChart(), new ProjectStatusBubbleChart(), new TemperatureChart(),
		      new WeightDialChart(), new SensorValuesChart(), new CombinedTemperatureChart(),
		      new MultipleTemperatureChart() };
	private TextToSpeech tts;
	View report;
	Context context;
	private String texttospeech="";
	String message_thirsty = "Don't Worry, Syamala Will bring some water";
	String message_hungry = "Chaitanya will bring some food for you now";
	String message_emergency="We are coming in 2 minutes";
	String message_report="Have a look over Divya's report about your activities";
private int result=0;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			startService(new Intent(this,DataService.class));
			setContentView(R.layout.activity_main);
		//	setContentView(R.layout.xy_chart);
			Button b1,b2,b3,b4;
			 tts = new TextToSpeech(this, this);
			b1=(Button)findViewById(R.id.button1);
			b2=(Button)findViewById(R.id.button2);
			b3=(Button)findViewById(R.id.button3);
			b4=(Button)findViewById(R.id.button4);
			
			b1.setOnClickListener(new View.OnClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String phoneNumber = "8168096151";
					String message = "I am feeling very Hunger";
					speakOut(message_hungry);
				//	@SuppressWarnings("deprecation")
				//	SmsManager smsManager = SmsManager.getDefault();
				//	smsManager.sendTextMessage(phoneNumber, null, message, null, null);
					
				}
				
			});
			b2.setOnClickListener(new View.OnClickListener() {

				@SuppressWarnings("deprecation")
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String phoneNumber = "9712589945";
					String message = "I am feeling Thirsty Brother";
					speakOut(message_thirsty);
					//@SuppressWarnings("deprecation")
					//SmsManager smsManager = SmsManager.getDefault();
					//smsManager.sendTextMessage(phoneNumber, null, message, null, null);
					
				}
				
			});
			 
			b3.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent in=new Intent(v.getContext(), Game_view.class);
					startActivityForResult(in,0);
					speakOut("Tiru Made some games for you, Have fun, You have two games available to play now, 2,0,4,8, a number puzzle game and The Got2run an Arcade game");
					//speakOut("");
					//speakOut("");
					
					
				}
				
			});
			b4.setOnClickListener(new View.OnClickListener() {
					Intent intent=null;
					
				@Override
				public void onClick(View v) {
					
					// TODO Auto-generated method stub
					 intent = mCharts[14].execute(v.getContext());
					startActivity(intent);
					speakOut(message_report);
				}
				
			});
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
				if ("hungry".equalsIgnoreCase(data)){
					String phoneNumber = "4086806151";
					String message = "I need some food";
					speakOut(message_hungry);
				//	@SuppressWarnings("deprecation")
				//	SmsManager smsManager = SmsManager.getDefault();
				//	smsManager.sendTextMessage(phoneNumber, null, message, null, null);
				
				Toast.makeText(getApplicationContext(), "Don't Worry, Chaitanya will bring some food", Toast.LENGTH_SHORT).show();
				
				}
				else if ("thirsty".equalsIgnoreCase(data)){
					String phoneNumber = "8168105148";
					String message = "I need some water";
					speakOut(message_thirsty);
				//	@SuppressWarnings("deprecation")
				//	SmsManager smsManager = SmsManager.getDefault();
				//	smsManager.sendTextMessage(phoneNumber, null, message, null, null);
				
				Toast.makeText(getApplicationContext(), "Don't Worry, Shyamala will bring some Water", Toast.LENGTH_SHORT).show();
				
				}
				else if ("game".equalsIgnoreCase(data)){
				//	Intent in=new Intent(this, GameActivity.class);
				//	startActivityForResult(in,0);
					speakOut("Tiru Made some games for you, Have fun");
					Intent in=new Intent(context, Game_view.class);
					startActivity(in);
					//Intent intent=null;
				//	intent = mCharts[14].execute(context);
				//	startActivity(intent);
				//	Toast.makeText(getApplicationContext(), "Divya's report on your activities", Toast.LENGTH_SHORT).show();
					speakOut("You have two games available to play now");
					speakOut("2048 a number puzzle game and The Got2run an Arcade game");
					
					Toast.makeText(getApplicationContext(), "Tiru Made some games for you, Have fun", Toast.LENGTH_SHORT).show();
					
				}
			
					//	startActivity(intent);
	//			Toast.makeText(getApplicationContext(), "Divya's report on your activities", Toast.LENGTH_SHORT).show();
				
				
				
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
	    Toast.makeText(getApplicationContext(), "Hi Please enter the right Words......  ", Toast.LENGTH_LONG).show();
	    }else
	    {
	    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	    }
	   }

}
