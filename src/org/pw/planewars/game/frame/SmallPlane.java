package org.pw.planewars.game.frame;


import android.graphics.Bitmap;

public class SmallPlane extends EnemyPlane {

	public SmallPlane(Bitmap bitmap) {
		super(bitmap);
		
		setPower(1);
		setmSection(3);
	}

}

