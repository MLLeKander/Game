package com.omgtallmonster.game.game.mover;

public class PhysicalObject {
	public static int SUB_CUTOFF = 1 << 6;
	int x = 0, y = 0;
	int subX = 0, subY = 0;
	int velX = 0, velY = 0;
	double inertia = 1.1;
	
	public PhysicalObject() {}
	
	public PhysicalObject(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	private void forceX(int amount) {
		velX += amount;
	}
	
	private void forceY(int amount) {
		velY += amount;
	}

	public void forceLeft(int amount) { forceX(-amount); }
	public void forceRight(int amount) { forceX(amount); }
	public void forceUp(int amount) { forceY(-amount); }
	public void forceDown(int amount) { forceY(amount); }

	public void step() {
		updateSub();
		updatePos();
		updateInertia();
	}
	
	public void updateSub() {
		subX += velX;
		subY += velY;
	}
	public void updatePos() {
		// TODO: Verify negative
		x += subX / SUB_CUTOFF;
		subX %= SUB_CUTOFF;
		y += subY / SUB_CUTOFF;
		subY %= SUB_CUTOFF;
	}
	public void updateInertia() {
		velX /= inertia;
		velY /= inertia;
	}
	
	public String toString() {
		return String.format("%d.%d%+d,%d.%d%+d",x,subX,velX,y,subY,velY);
	}
}
