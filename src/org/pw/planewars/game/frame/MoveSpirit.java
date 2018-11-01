package org.pw.planewars.game.frame;

import org.pw.planewars.game.view.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class MoveSpirit extends Spirit {
	
	@Override
	public void draw(Canvas canvas, GameView view) {
		
		move(0, mSpeed);
		
		super.draw(canvas, view);
	}

	
	
	public int getSpeed() {
		return mSpeed;
	}

	public void setSpeed(int mSpeed) {
		this.mSpeed = mSpeed;
	}

	public MoveSpirit(Bitmap bitmap) {
		super(bitmap);
	}
	
	private int mSpeed;
}
