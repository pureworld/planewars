package org.pw.planewars.game.frame;

import org.pw.planewars.game.view.GameView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Plane extends Spirit {

	@Override
	public Rect GetDstRect() {
		Rect rc = new Rect();
		rc.left = getX();
		rc.right = rc.left + getBitmap().getWidth() / 2;
		rc.top = getY();
		rc.bottom = rc.top + getBitmap().getHeight();
		return rc;
	}
	
	@Override
	public Rect GetSrcRect() {
		Rect rc = new Rect();
		rc.left = getBitmap().getWidth() / 2 * mCurSec;
		rc.right = getBitmap().getWidth() / 2 * (mCurSec + 1);
		rc.top = 0;
		rc.bottom = getBitmap().getHeight();
		return rc;
	}

	@Override
	public void draw(Canvas canvas, GameView view) {
		super.draw(canvas, view);
		
		//ArrayList<Spirit> enemyPlanes = view.getAllEnemy();
		//for (Spirit enemyPlane : enemyPlanes) {		
		//	if (enemyPlane.isDestory())
		//		continue;
			
		//	if (Rect.intersects(((Plane) enemyPlane).GetDstRect(), GetDstRect())) {
		//		destory();
		//		return;
		//	}
		//}

		
		if (mCurSec == 0) {
			mCurSec = 1;
		} else {
			mCurSec = 0;
		}
		
		if (getFrame() % 14 == 0) {
			Spirit spirit1 =  new Bullet(view.getBitmap(2));
			Spirit spirit2 =  new Bullet(view.getBitmap(2));
			spirit1.moveTo(getX() + 8, getY());
			spirit2.moveTo(getX() + GetWidth() - 8, getY());
			view.AddSpirit(spirit1);
			view.AddSpirit(spirit2);
		}
		
	}

	public Plane(Bitmap bitmap) {
		super(bitmap);
	}
	
	private int mCurSec = 0;
}
