package jsera.util;

import java.awt.*;

import com.illposed.osc.OSCPortIn;

public abstract class World implements Updatable {
	protected int ID;
	protected static UIDHandler oUID = new UIDHandler();
	
	public World() {
		this.ID = oUID.getUID();
	}
	
	public void finalize() {
		oUID.releaseUID(this.ID);
	}
	
	public boolean equals(World o) {
		boolean result = false;
		if (o.ID == this.ID) {
			result = true;
		}
		return result;
	}
	
	// update moves anything that needs to be moved, etc.
	
	public void update(float elapsed) {
	}
	
	public void onEnter() {
	}
	
	public void onExit() {
	}
	
	public void onPlay() {
	}
	
	public void onPause() {
	}
	
	// render everything here
	
	public void paint(Graphics g) {
	}
	
	// seperate initialization thing
	public void init() {
	}
	
	public abstract void start();
	public abstract OSCPortIn getReceiver();
	public abstract void mouseEvent(int type, float xOffset, float yOffset);
	public abstract void buttonEvent(int type, int button);
	public abstract void scrollEvent(int dir);
	public abstract void keyboardEvent(int type, int keycode);
	public abstract void keyboardEvent(int type, int keycode, String value);
	public abstract void orientEvent(float z, float x, float y, float rawz, float rawx, float rawy);
}