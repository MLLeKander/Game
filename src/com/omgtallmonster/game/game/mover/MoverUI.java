package com.omgtallmonster.game.game.mover;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenWriter;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.TerminalSize;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import com.omgtallmonster.game.game.InputProvider;
import com.omgtallmonster.game.game.UI;

public class MoverUI implements UI<MoverState>, InputProvider<List<Key>> {
	Terminal term;
	Screen screen;
	ScreenWriter writer;
	Rectangle visRect;
	
	public void drawStringHoriz(int x, int y, String s) {
		writer.drawString(x, y, s);
	}
	
	public void drawStringHorizBack(int x, int y, String s) {
		drawStringHoriz(x-s.length()+1, y, s);
	}
	
	public void drawStringVert(int x, int y, String s) {
		int len = s.length();
		for (int i = 0; i < len; i++) {
			writer.drawString(x, y+i, ""+s.charAt(i));
		}
	}
	
	public void drawStringVertBack(int x, int y, String s) {
		drawStringVert(x,y-s.length()+1,s);
	}

	@Override
	public void display(MoverState state) {
		if (screen.resizePending()) {
			//Logger.i(this,  "Resize pending");
			screen.refresh();
			TerminalSize ts = screen.getTerminalSize();
			visRect.setSize(ts.getColumns(), ts.getRows());
			//System.err.println(ts+" "+visRect);
		}
		writer.fillScreen(' ');
		//System.err.println(state.o);
		
		recenterWindow(state);
		
		int x = visRect.x, y = visRect.y, c = visRect.width, r = visRect.height;
		
		drawStringHoriz(0, 0, String.format("%d,%d",x,y));
		drawStringHorizBack(c-1, r-1, String.format("%d,%d",x+c,y+r));

		drawStringHoriz(15-visRect.x, 15-visRect.y, "x");
		drawStringHoriz(5-visRect.x, 5-visRect.y, "y");
		drawStringVertBack(state.o.x-visRect.x, state.o.y-visRect.y, "o|^");
		screen.refresh();
	}
	
	private void recenterWindow(MoverState state) {
		while (state.o.x < visRect.x+3) {
			visRect.x -= 5;
		}
		while (state.o.y < visRect.y+3) {
			visRect.y -= 5;
		}
		while (state.o.x > visRect.x+visRect.width-3) {
			visRect.x += 5;
		}
		while (state.o.y > visRect.y+visRect.height-3) {
			visRect.y += 5;
		}
	}

	@Override
	public void init(MoverState state) {
		term = TerminalFacade.createTerminal();
		screen = TerminalFacade.createScreen(term);
		screen.startScreen();
		screen.setCursorPosition(null);
		screen.setPaddingCharacter(' ', Color.WHITE, Color.BLACK);
		writer = new ScreenWriter(screen);
		
		TerminalSize ts = screen.getTerminalSize();
		visRect = new Rectangle(0, 0, ts.getColumns(), ts.getRows());
		
		if (term instanceof SwingTerminal) {
			setupJFrame((SwingTerminal)term);
		}
	}
	
	public void setupJFrame(SwingTerminal sterm) {
		final JFrame frame = sterm.getJFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//TODO: Only allow integer resizes.
//		Font f = TerminalAppearance.DEFAULT_APPEARANCE.getNormalTextFont();
//		FontMetrics fm = frame.getGraphics().getFontMetrics(f);
//		final int cw = fm.charWidth(' ');
//		final int ch = fm.getHeight();
//		
//		frame.addComponentListener(new ComponentAdapter() {
//		    @Override
//		    public void componentResized(ComponentEvent e) {
//		    	int h = frame.getHeight(), w = frame.getWidth();
//		    	int newH = h/ch*ch, newW = w/cw*cw;
//		    	System.out.println(e.getSource() == frame);
//		    	if (Math.abs(h - newH) > 5 || Math.abs(w - newW) > 5) {
//		    		Logger.i("h:%d w:%d nh:%d nw:%d", h,w,newH,newW);
//		    		frame.setSize(new Dimension(newW, newH));
//		    	}
//		        super.componentResized(e);
//		    }
//		});
	}

	@Override
	public void finish(MoverState state) {
		screen.stopScreen();
	}
	
	@Override
	public List<Key> getInput() {
		List<Key> out = new ArrayList<Key>();
		Key k;
		while ((k = screen.readInput()) != null) {
			out.add(k);
		}
		return out;
	}

}
