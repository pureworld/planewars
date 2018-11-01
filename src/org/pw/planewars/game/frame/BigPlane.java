package org.pw.planewars.game.frame;

import android.graphics.Bitmap;

public class BigPlane extends EnemyPlane {
	
	public BigPlane(Bitmap bitmap) {
		super(bitmap);
		
		setPower(10);
		setmSection(5);
	}

}
