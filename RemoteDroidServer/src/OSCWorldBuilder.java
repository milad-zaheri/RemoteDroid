import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCPortIn;

public class OSCWorldBuilder {
	
	private OSCWorld world;
	
	public  OSCWorldBuilder(OSCWorld newWorld) {
		world = newWorld;
	}
	
	public boolean addListener(String anAdderess){
		world.receiver.addListener(anAdderess, createListener(anAdderess));
		return true;
	}
	
	private OSCListener createListener(String listenerName) {
		OSCListener listener = OSCListenerFactory.getListener(listenerName, world);
		return listener;
	}
	
	public void startWorld() {
		world.start();
	}


}
