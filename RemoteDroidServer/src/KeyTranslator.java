import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * this class is responsible for 
 * traslating the pressed key on the client side
 * to the real kayboard keys and mouse buttons
 *
 */
public class KeyTranslator {

	public HashMap codes;
	private int[] modifiers;
	private int[] shifts;
	private int[] ctrls;
	private int[] leftClicks;

	protected Document myDoc;

	/**
	 * default constructor
	 */
	public KeyTranslator() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String sPath = "config.xml";
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			if (AppFrame.jar == null) {
				this.myDoc = builder.parse(new File((AppFrame.basePath + sPath).replace('\\', '/')));// ensure
																										// seperator
																										// is
																										// '/'
																										// as
																										// linux
																										// is
																										// picky
			} else {
				this.myDoc = builder.parse(AppFrame.jar.getInputStream(AppFrame.jar.getJarEntry(sPath)));
			}
		} catch (SAXException saxEx) {
			// Error generated during parsing
			Exception excep = saxEx;
			if (saxEx.getException() != null)
				excep = saxEx.getException();
			excep.printStackTrace();

		} catch (ParserConfigurationException parserConfigEx) {
			// Parser with specified options can't be built
			parserConfigEx.printStackTrace();

		} catch (IOException ioEx) {
			// I/O error
			ioEx.printStackTrace();
		}
		Element config = this.myDoc.getDocumentElement();
		// how many modifier nodes?
		NodeList mods = config.getElementsByTagName("modifier");
		int modLength = mods.getLength();
		int i;
		this.modifiers = new int[modLength];
		for (i = 0; i < modLength; i++) {
			this.modifiers[i] = Integer.parseInt(((Element) mods.item(i)).getAttribute("code"));
		}
		// shift keys
		mods = config.getElementsByTagName("shift");
		modLength = mods.getLength();
		this.shifts = new int[modLength];
		for (i = 0; i < modLength; i++) {
			this.shifts[i] = Integer.parseInt(((Element) mods.item(i)).getAttribute("code"));
		}
		// ctrl keys
		mods = config.getElementsByTagName("ctrl");
		modLength = mods.getLength();
		this.ctrls = new int[modLength];
		for (i = 0; i < modLength; ++i) {
			this.ctrls[i] = Integer.parseInt(((Element) mods.item(i)).getAttribute("code"));
		}
		// fill the keycodedata hashmap
		this.codes = new HashMap();
		KeyCodeData data;
		int keycode;
		Element keydata;
		mods = config.getElementsByTagName("key");
		modLength = mods.getLength();
		for (i = 0; i < modLength; ++i) {
			data = new KeyCodeData();
			keydata = (Element) mods.item(i);
			data.name = keydata.getAttribute("name");
			data.modshifted = "1".compareTo(keydata.getAttribute("modshift")) == 0;
			data.localcode = Integer.parseInt(keydata.getAttribute("localcode"));
			data.modifiedcode = Integer.parseInt(keydata.getAttribute("modified"));
			data.shifted = "1".compareTo(keydata.getAttribute("shifted")) == 0;
			data.shiftedcode = Integer.parseInt(keydata.getAttribute("shiftedcode"));
			keycode = Integer.parseInt(keydata.getAttribute("code"));

			this.codes.put(new Integer(keycode), data);
		}
		// simulated mouse button
		mods = config.getElementsByTagName("leftclick");
		modLength = mods.getLength();
		this.leftClicks = new int[modLength];
		for (i = 0; i < modLength; ++i) {
			this.leftClicks[i] = Integer.parseInt(((Element) mods.item(i)).getAttribute("code"));
		}
	}

	/**
	 * checks if the key sent from client
	 * is a mouse click or keyboard
	 * @param keycode
	 * @return true if is not mouse click
	 */
	public boolean isModifier(int keycode) {

		int modLength = this.modifiers.length;
		for (int i = 0; i < modLength; ++i) {
			if (keycode == this.modifiers[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is shift key pressed?
	 * @param keycode
	 * @return true if so
	 */
	public boolean isShift(int keycode) {

		int shiftLength = this.shifts.length;
		for (int i = 0; i < shiftLength; ++i) {
			if (keycode == this.shifts[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * is control key pressed?
	 * @param keycode
	 * @return true if so
	 */
	public boolean isCtrl(int keycode) {

		int ctrlLength = this.ctrls.length;
		for (int i = 0; i < ctrlLength; ++i) {
			if (keycode == this.ctrls[i]) {
				return true;
			}
		}
		return false;
	}

	/**
	 * check if it isn't a mouse click 
	 * (trackpad "enter" = left button)
	 * @param keycode
	 * @return true if so
	 */
	public boolean isLeftClick(int keycode) {

		int leftClickLength = this.leftClicks.length;
		for (int i = 0; i < leftClickLength; ++i) {
			if (keycode == this.leftClicks[i]) {
				return true;
			}
		}
		return false;
	}

}
