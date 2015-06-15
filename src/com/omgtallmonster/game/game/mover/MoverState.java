package com.omgtallmonster.game.game.mover;

import java.util.List;

import com.googlecode.lanterna.input.Key;
import com.omgtallmonster.game.game.InputProvider;
import com.omgtallmonster.game.game.State;

public class MoverState implements State<List<Key>> {
	int t = 0;
	private final static int FORCE = 64;
	boolean finished = false;
	PhysicalObject o = new PhysicalObject(10,11);
	
	@Override
	public boolean isFinished() {
		return finished;
	}

	@Override
	public void advance(InputProvider<List<Key>> in) {
		for (Key k : in.getInput()) {
			processKey(k);
		}
		o.step();
		t++;
	}
	
	private void processKey(Key k) {
		if (k.getKind() == Key.Kind.Escape) finished = true;
		if (k.getKind() == Key.Kind.NormalKey) {
			switch (k.getCharacter()) {
			case 'w':
				o.forceUp(FORCE); break;
			case 's':
				o.forceDown(FORCE); break;
			case 'a':
				o.forceLeft(FORCE); break;
			case 'd':
				o.forceRight(FORCE); break;
			case 'q':
				finished = true; break;
			}
		}
	}

}
