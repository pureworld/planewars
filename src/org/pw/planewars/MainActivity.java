package org.pw.planewars;

import org.pw.planewars.game.view.GameActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {	
				startActivity(new Intent(MainActivity.this, GameActivity.class));
			}
		});
		
		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				finish();		
			}
		});
	}
}
