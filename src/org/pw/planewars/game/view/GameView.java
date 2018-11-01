package org.pw.planewars.game.view;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

import org.pw.planewars.game.frame.BigPlane;
import org.pw.planewars.game.frame.EnemyPlane;
import org.pw.planewars.game.frame.MidPlane;
import org.pw.planewars.game.frame.Plane;
import org.pw.planewars.game.frame.SmallPlane;
import org.pw.planewars.game.frame.Spirit;
import org.pw.planewars.game.frame.Bullet;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View {
	public Bitmap getBitmap(int index) {
		return mBitmaps.get(index);
	}
	
	public void AddSpirit(Spirit spirit) {
		mNewSpirits.add(spirit);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			mIsDown = true;
			return true;
		}
		else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			if (mIsDown) {
				int x = (int)event.getX();
				int y = (int)event.getY();
				getPlane().moveTo(x, y);
			}
			return true;
		}
		else if(event.getAction() == MotionEvent.ACTION_UP) {
			mIsDown = false;
			return true;
		}
		return super.onTouchEvent(event);
	}


	@Override
	protected void onDraw(Canvas canvas) {
		
		mFrame++;
		
		drawBackgroud(canvas);
		
		for (int i = 0; i < mSpirits.size(); i++) {
			
			//if (mSpirits.get(i) instanceof Plane && mSpirits.get(i).isDestory()) {
			//	AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
			//	builder.setMessage("YOU LOSE").create().show();	
			//	return;
			//}
			
			if (mSpirits.get(i) == null)
				continue;
			
			if (mSpirits.get(i).isDestory()) {
				mSpirits.remove(i);
				i--;
			}
		}
		
		mSpirits.addAll(mNewSpirits);
		mNewSpirits.clear();
		
		for (Spirit spirit:mSpirits) {
			if (spirit != null)
				spirit.draw(canvas, this);
		}
		
		CreateRandomSpirit();
		
		postInvalidate();
	}
	
	public void CreateRandomSpirit() {
		
		if (mFrame % 40 == 0) {
			int type = (mRandom.nextInt() >>> 1) % 3;
			Spirit spirit = null;
			switch(type) {
			case 0:
				spirit = new SmallPlane(getBitmap(3));
				spirit.moveTo((mRandom.nextInt() >>> 1) % mBg.getWidth(), 0);
				AddSpirit(spirit);
				break;
			case 1:
				spirit = new MidPlane(getBitmap(4));
				spirit.moveTo((mRandom.nextInt() >>> 1) % mBg.getWidth(), 0);
				AddSpirit(spirit);
				break;
			case 2:
				spirit = new BigPlane(getBitmap(5));
				spirit.moveTo((mRandom.nextInt() >>> 1) % mBg.getWidth(), 0);
				AddSpirit(spirit);
				break;
			}
		
		}
	}

	private void drawBackgroud(Canvas canvas) {
		if (mFlag)
		{
			mY1 = 0;
			mY2 = -canvas.getHeight();	
			mFlag = false;
		}

		
		Rect srcRect = new Rect();
		Rect dstRect = new Rect();
		
		if (mFrame % mSpeed == 0) {
			mY1++;
			mY2++;
		}
		
		if (mY1 >= canvas.getHeight()) {
			mY1 = 0;
			mY2 = -canvas.getHeight();
		}

			
		
		srcRect.left = 0;
		srcRect.right = mBg.getWidth();
		srcRect.top = 0;
		srcRect.bottom = mBg.getHeight();
		
		dstRect.left = 0;
		dstRect.right = mBg.getWidth();
		dstRect.top = mY1;
		dstRect.bottom = canvas.getHeight() + mY1;
		canvas.drawBitmap(mBg, srcRect, dstRect, mPaint);
		
	
		dstRect.left = 0;
		dstRect.right = mBg.getWidth();
		dstRect.top = mY2;  
		dstRect.bottom = canvas.getHeight() + mY2;
		canvas.drawBitmap(mBg, srcRect, dstRect, mPaint);
	}


	public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
	}

	public GameView(Context context, int[] resid) {
		super(context);
		mContext = context;
		for (int id :resid) {
			mBitmaps.add(BitmapFactory.decodeResource(context.getResources(), id));
		}
		
		mBg = mBitmaps.get(0);	
	}
	
	public Plane getPlane() {
		for (Spirit spirit:mSpirits) {
			if (spirit instanceof Plane)
			 return (Plane)spirit;
		}
		return null;
	}
	
	public ArrayList<Spirit> getAllButtet()
	{
		ArrayList<Spirit> spirits = new ArrayList<Spirit>();
		
		for (Spirit spirit:mSpirits) {
			if (spirit instanceof Bullet)
				spirits.add(spirit);
		}
		return spirits;
	}
	
	public ArrayList<Spirit> getAllEnemy()
	{
		ArrayList<Spirit> spirits = new ArrayList<Spirit>();
		
		for (Spirit spirit:mSpirits) {
			if (spirit instanceof EnemyPlane)
				spirits.add(spirit);
		}
		return spirits;
	}
	
	public void start() {
		mFlag = true;
		
		Spirit plan  = new Plane(mBitmaps.get(1));
		plan.moveTo(mBg.getWidth() / 2 - plan.GetWidth() / 2, 600);
		AddSpirit(plan);	
	}
	
	Paint mPaint = new Paint();
	ArrayList<Bitmap> mBitmaps = new ArrayList<Bitmap>();
	LinkedList<Spirit> mSpirits = new LinkedList<Spirit>();
	LinkedList<Spirit> mNewSpirits = new LinkedList<Spirit>();
	Bitmap mBg;
	int mY1;
	int mY2;
	int mFrame;
	int mSpeed = 1;
	boolean mFlag = false;
	boolean mIsDown = false;
	Random mRandom = new Random();
	Context mContext;
}
