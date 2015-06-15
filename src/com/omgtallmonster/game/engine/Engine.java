package com.omgtallmonster.game.engine;

import com.omgtallmonster.game.game.InputProvider;
import com.omgtallmonster.game.game.State;
import com.omgtallmonster.game.game.UI;
import com.omgtallmonster.game.log.Logger;

public class Engine {
	private final static int NS_PER_SEC = 1000000000;
	private final static long MS_PER_SEC = 1000;
	
	public static <IType, SType extends State<IType>> void run(int maxFps, SType state, UI<SType> ui, InputProvider<IType> in) {
		final long framePeriod = NS_PER_SEC / maxFps;
		
		ui.init(state);
		
		while (!state.isFinished()) {
			long beginTime = System.nanoTime();
			
			state.advance(in);
			ui.display(state);
			
			long timeDiff = System.nanoTime() - beginTime;
			long sleepTime = framePeriod - timeDiff;
			if (sleepTime > 0) {
				try {
					long tmp = sleepTime * MS_PER_SEC;
					long sleepTimeMs = tmp / NS_PER_SEC;
					int sleepTimeNs = (int) (sleepTime % (NS_PER_SEC/MS_PER_SEC));
					Logger.d(Engine.class, "Sleeping for %dms + %dns",sleepTimeMs,sleepTimeNs);
					Thread.sleep(sleepTimeMs, sleepTimeNs);
				} catch (InterruptedException e) {
					Logger.i(Engine.class, "Sleep interrupted? "+e);
				}
			}
			
			for ( ; sleepTime < 0; sleepTime += framePeriod) {
				state.advance(in);
			}
		}
		
		ui.finish(state);
	}
}
