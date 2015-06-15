package com.omgtallmonster.game.game.bouncer;

import com.omgtallmonster.game.game.InputProvider;
import com.omgtallmonster.game.game.State;

public class BouncerState implements State<Object> {
	int t = 0;
	int tMax;
	
	public BouncerState(int tMax) { this.tMax = tMax; }

	public boolean isFinished() {
		return t >= tMax;
	}

	@Override
	public void advance(InputProvider<Object> input) {
		t++;
	}

}
