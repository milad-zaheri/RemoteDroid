
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * RemoteDroidServer V2.0
 * This class is responsible for generating the application frame for the Computer. 
 * 
 */

public class RemoteDroidServer {
	private static AppFrame applicationFrame;

	public static void main(String[] args) {

		applicationFrame = AppFrame.getInstance();
		applicationFrame.setVisible(true);
		applicationFrame.setResizable(false);
		applicationFrame.setTitle("RemoteDroid Server");
		applicationFrame.setLocationRelativeTo(null);
		applicationFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				applicationFrame.setVisible(false);
				applicationFrame.dispose();
				System.exit(0);
			}
		});

		applicationFrame.init();
		System.out.println("Operating System : "+System.getProperty("os.name"));

	}
}