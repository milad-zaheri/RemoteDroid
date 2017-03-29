import com.illposed.osc.OSCListener;
import jsera.util.World;

public class OSCWorldBuilder {
	
	private World world;
	
	public  OSCWorldBuilder(World newWorld) {
		world = newWorld;
	}
	
	public boolean addListener(String anAdderess){
		world.getReceiver().addListener(anAdderess, createListener(anAdderess));
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
