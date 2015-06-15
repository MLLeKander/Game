package com.omgtallmonster.game.game.bouncer;

import com.omgtallmonster.game.game.UI;

public class BouncerUI implements UI<BouncerState> {

	private static String repString(char c, int len) {
		return new String(new char[len]).replace('\0', c);
	}
	
	private final static int LEN = 60;
	
	private final static String delStr = repString('\b',LEN+2);
	
	@Override
	public void display(BouncerState state) {
		// TODO Auto-generated method stub
		int pos = Math.min(Math.abs(state.t % (LEN*2) - LEN), LEN-1);
		String pre = "?"+repString('-',pos);
		String post = repString('-',LEN-pos-1)+">";
		System.out.print(delStr+pre+"|"+post);
		//Logger.i(this, state.t+"");
	}

	@Override
	public void init(BouncerState state) {
		System.out.print(repString(' ',LEN+25));
	}

	@Override
	public void finish(BouncerState state) {
		System.out.println();
	}

}
