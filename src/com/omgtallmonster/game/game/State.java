package com.omgtallmonster.game.game;

public interface State<IType> {
	public boolean isFinished();
	public void advance(InputProvider<IType> input);
}
