/* $Id$
 * Created on 28.10.2003
 */
package com.illposed.osc.utility;

import com.illposed.osc.*;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * @author cramakrishnan
 *
 * Copyright (C) 2003, C. Ramakrishnan / Auracle
 * All rights reserved.
 * 
 * See license.txt (or license.rtf) for license information.
 * 
 * Dispatches OSCMessages to registered listeners.
 * 
 */

public class OSCPacketDispatcher {
	// use Hashtable for JDK1.1 compatability
	private Hashtable addressToClassTable = new Hashtable();
	
	/**
	 * 
	 */
	public OSCPacketDispatcher() {
		super();
	}

	public void addListener(String address, OSCListener listener) {
		addressToClassTable.put(address, listener);
	}
	
	public void dispatchPacket(OSCPacket packet) {
		if (packet instanceof OSCBundle)
			((OSCBundle) packet).dispatchBundle(this);
		else
			dispatchMessage((OSCMessage) packet);
	}
	
	public void dispatchPacket(OSCPacket packet, Date timestamp) {
		if (packet instanceof OSCBundle)
			((OSCBundle) packet).dispatchBundle(this);
		else
			((OSCMessage) packet).dispatchMessage(timestamp, addressToClassTable);
	}
	
	private void dispatchMessage(OSCMessage message) {
		message.dispatchMessage(null, addressToClassTable);
	}
}
