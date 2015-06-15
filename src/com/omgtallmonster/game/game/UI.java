package com.omgtallmonster.game.game;

public interface UI<StateType extends State<?>> {
	public void init(StateType state);
	public void display(StateType state);
	public void finish(StateType state);
}
