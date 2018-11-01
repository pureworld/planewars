package org.pw.planewars.game.frame;

import android.graphics.Bitmap;

public class Bullet extends MoveSpirit {

	public Bullet(Bitmap bitmap) {
		super(bitmap);
		setSpeed(-2);
	}

}
