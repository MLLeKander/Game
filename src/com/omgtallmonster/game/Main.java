package com.omgtallmonster.game;

import com.omgtallmonster.game.engine.Engine;
import com.omgtallmonster.game.game.mover.MoverState;
import com.omgtallmonster.game.game.mover.MoverUI;
import com.omgtallmonster.game.log.Logger;
import com.omgtallmonster.game.log.StdOutLogger;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.setLogger(new StdOutLogger(System.err));
		MoverState state = new MoverState();
		MoverUI ui = new MoverUI();
		Engine.run(60, state, ui, ui);
	}

}
