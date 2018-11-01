package org.pw.planewars.game.view;

import org.pw.planewars.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameActivity extends Activity {
	private GameView mGameView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//…Ë÷√»´∆¡
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		
        mGameView = new GameView(this, new int[] {
        		R.drawable.bg_01, 
        		R.drawable.myplane,
        		R.drawable.bullet,
        		R.drawable.small,
        		R.drawable.middle,
        		R.drawable.big,			
        });
        mGameView.start();
        
		setContentView(mGameView);
	}

}
