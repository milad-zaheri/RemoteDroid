import com.illposed.osc.OSCListener;
import jsera.util.World;

/**
 * a builder class for worlds
 * @author Milad
 *
 */
public class OSCWorldBuilder {
	
	private World world;
	
	public  OSCWorldBuilder(World newWorld) {
		world = newWorld;
	}
	/**
	 * add listener to the world receiver
	 * @param anAdderess
	 * @return
	 */
	public boolean addListener(String anAdderess){
		world.getReceiver().addListener(anAdderess, createListener(anAdderess));
		return true;
	}
	
	/**
	 * get listener from the factory class
	 * @param listenerName
	 * @return
	 */
	private OSCListener createListener(String listenerName) {
		OSCListener listener = OSCListenerFactory.getListener(listenerName, world);
		return listener;
	}
	
	/**
	 * begin the world to listen 
	 */
	public void startWorld() {
		world.start();

	}

}
