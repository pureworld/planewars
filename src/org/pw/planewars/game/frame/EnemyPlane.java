package org.pw.planewars.game.frame;

import java.util.ArrayList;

import org.pw.planewars.game.view.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class EnemyPlane extends MoveSpirit {
	@Override
	public void draw(Canvas canvas, GameView view) {
		
		ArrayList<Spirit> buttets = view.getAllButtet();
		for (Spirit buttet : buttets) {
			if (isDestory())
				continue;
			if (buttet.isDestory())
				continue;
			
			if (Rect.intersects(buttet.GetDstRect(), GetDstRect())) {
				if (mPower != 0) {
					mPower--;
					buttet.destory();
				}
				if (mPower == 0 && mCurSec <= mSection) {	
					if (mCurSec < mSection) {
						mCurSec++;
					} else {
						destory();
					}
					
				
				}
			}
				
		}
		super.draw(canvas, view);
	}

	public int getPower() {
		return mPower;
	}

	public void setPower(int mPower) {
		this.mPower = mPower;
	}
	
	public EnemyPlane(Bitmap bitmap) {
		super(bitmap);
		
		setSpeed(2);
	}
	private int mPower;
	private int mSection;
	private int mCurSec = 0;
	public int getmSection() {
		return mSection;
	}

	public void setmSection(int mSection) {
		this.mSection = mSection;
	}
	
	@Override
	public Rect GetSrcRect() {
		Rect rc = new Rect();
		rc.left = 0;
		rc.right = getBitmap().getWidth();
		rc.top = getBitmap().getHeight() / getmSection() * (mCurSec);
		rc.bottom = getBitmap().getHeight() / getmSection() * (mCurSec + 1);
		return rc;
	}

	@Override
	public Rect GetDstRect() {
		Rect rc = new Rect();
		rc.left = getX();
		rc.right = rc.left + getBitmap().getWidth();
		rc.top = getY();
		rc.bottom = rc.top + getBitmap().getHeight() / getmSection();
		return rc;
	}
}
