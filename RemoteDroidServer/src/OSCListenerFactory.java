import com.illposed.osc.OSCListener;
import com.illposed.osc.OSCMessage;

public class OSCListenerFactory {
	static OSCListener listener;
	
	
	public static OSCListener getListener(String aAddress, OSCWorld world) {
		
		if(aAddress.equals("/mouse"))
			return mouse(world);
		else if(aAddress.equals("/leftbutton"))
			return leftButton(world);
		else if(aAddress.equals("/rightbutton"))
			return rightButton(world);
		else if(aAddress.equals("/keyboard"))
			return keyboard(world);
		else if(aAddress.equals("/wheel"))
			return wheel(world);
		
		return orient(world);
	}
	
	private static OSCListener mouse(final OSCWorld world) {
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				Object[] args = message.getArguments();
				if (args.length == 3) {
					world.mouseEvent(Integer.parseInt(args[0].toString()), Float.parseFloat(args[1].toString()),
							Float.parseFloat(args[2].toString()));
				}
			}
		};
		return listener;
	}
	
	private static OSCListener leftButton(final OSCWorld world) {
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				Object[] args = message.getArguments();
				if (args.length == 1) {
					world.buttonEvent(Integer.parseInt(args[0].toString()), 0);
				}
			}
		};
		return listener;
	}
	
	private static OSCListener rightButton(final OSCWorld world) {
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				Object[] args = message.getArguments();
				if (args.length == 1) {
					world.buttonEvent(Integer.parseInt(args[0].toString()), 2);
				}
			}
		};
		return listener;
	}
	
	private static OSCListener keyboard(final OSCWorld world) {
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				Object[] args = message.getArguments();
				if (args.length == 3) {
					world.keyboardEvent(Integer.parseInt(args[0].toString()), Integer.parseInt(args[1].toString()),
							args[2].toString());
				}
				if (args.length == 2) { // handle raw keyboard event, no
										// translations
					world.keyboardEvent(Integer.parseInt(args[0].toString()), Integer.parseInt(args[1].toString()));
				}
			}
		};
		return listener;
	}
	
	private static OSCListener wheel(final OSCWorld world) {
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				Object[] args = message.getArguments();
				if (args.length == 1) {
					world.scrollEvent(Integer.parseInt(args[0].toString()));
				}
			}
		};
		return listener;
	}
	
	private static OSCListener orient(final OSCWorld world) {
		OSCListener listener = new OSCListener() {
			public void acceptMessage(java.util.Date time, OSCMessage message) {
				Object[] args = message.getArguments();
				if (args.length == 6) {
					world.orientEvent(Float.parseFloat(args[0].toString()), Float.parseFloat(args[1].toString()),
							Float.parseFloat(args[2].toString()), Float.parseFloat(args[3].toString()),
							Float.parseFloat(args[4].toString()), Float.parseFloat(args[5].toString()));
				}
			}
		};
		return listener;
	}
	
}
