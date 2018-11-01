package org.pw.planewars.game.frame;

import org.pw.planewars.game.view.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Spirit {
	
	public Spirit(Bitmap bitmap) {
		mBitmap = bitmap;
	}
	
	public int GetWidth() {
		return mBitmap.getWidth();
	}
	
	public int GetHeight() {
		return mBitmap.getHeight();
	}
	
	public Rect GetSrcRect()
	{
		Rect rc = new Rect();
		rc.left = 0;
		rc.right = mBitmap.getWidth();
		rc.top = 0;
		rc.bottom = mBitmap.getHeight();
		return rc;
	}
	
	public Rect GetDstRect()
	{
		Rect rc = new Rect();
		rc.left = x;
		rc.right = rc.left + mBitmap.getWidth();
		rc.top = y;
		rc.bottom = rc.top + mBitmap.getHeight();
		return rc;
	}
	
	public int getFrame() {
		return mFrame;
	}
	public void setFrame(int frame) {
		mFrame = frame;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void move(int xOffset, int yOffset) {
		this.x += xOffset;
		this.y += yOffset;
	}
	
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean isDestory() {
		return mIsDestory;
	}
	public void destory() {
		mIsDestory = true;
	}
	
	public Bitmap getBitmap() {
		return mBitmap;
	}
	public void setBitmap(Bitmap mBitmap) {
		this.mBitmap = mBitmap;
	}
	
	public void draw(Canvas canvas, GameView view) {
		
		if (y < 0 || y > canvas.getHeight())
			destory();
		
		mFrame++;
		canvas.drawBitmap(mBitmap, GetSrcRect(), GetDstRect(), null);
	}
	
	private int mFrame;
	private int x;
	private int y;
	private boolean mIsDestory;
	private Bitmap mBitmap;
}
