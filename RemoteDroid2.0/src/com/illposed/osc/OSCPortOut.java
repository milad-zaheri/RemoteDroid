package com.illposed.osc;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.crypto.spec.SecretKeySpec;
import java.lang.Math; // headers MUST be above the first class
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * OSCPortOut is the class that sends OSC messages to a specific address and port.
 *
 * To send an OSC message, call send().
 * <p>
 * An example based on com.illposed.osc.test.OSCPortTest::testMessageWithArgs() :
 * <pre>
	OSCPort sender = new OSCPort();
	Object args[] = new Object[2];
	args[0] = new Integer(3);
	args[1] = "hello";
	OSCMessage msg = new OSCMessage("/sayhello", args);
 try {
 sender.send(msg);
 } catch (Exception e) {
 showError("Couldn't send");
 }
 * </pre>
 * <p>
 * Copyright (C) 2004-2006, C. Ramakrishnan / Illposed Software.
 * All rights reserved.
 * <p>
 * See license.txt (or license.rtf) for license information.
 *
 * @author Chandrasekhar Ramakrishnan
 * @version 1.0
 */
public class OSCPortOut extends OSCPort {
    
    private static final String TAG = "OSCPortOut";
    protected InetAddress address;
    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    /**
     * Create an OSCPort that sends to newAddress, newPort
     * @param newAddress InetAddress
     * @param newPort int
     */
    public OSCPortOut(InetAddress newAddress, int newPort)
    throws SocketException {
        socket = new DatagramSocket();
        address = newAddress;
        port = newPort;
    }
    
    /**
     * Create an OSCPort that sends to newAddress, on the standard SuperCollider port
     * @param newAddress InetAddress
     *
     * Default the port to the standard one for SuperCollider
     */
    public OSCPortOut(InetAddress newAddress) throws SocketException {
        this(newAddress, defaultSCOSCPort());
    }
    
    /**
     * Create an OSCPort that sends to localhost, on the standard SuperCollider port
     * Default the address to localhost
     * Default the port to the standard one for SuperCollider
     */
    public OSCPortOut() throws UnknownHostException, SocketException {
        this(InetAddress.getLocalHost(), defaultSCOSCPort());
    }
    
    /**
     * Send an osc packet (message or bundle) to the receiver I am bound to.
     * @param aPacket OSCPacket
     */
    public void send(OSCPacket aPacket) throws IOException {
        byte[] byteArray = aPacket.getByteArray();
        byte[] byteArray_new = aPacket.getByteArray();
        Log.e(TAG,String.valueOf(byteArray));
        
        byteArray_new=encrypt(byteArray);
        Log.e(TAG, " Encrypted "+String.valueOf(byteArray_new));
        DatagramPacket packet =
        new DatagramPacket(byteArray_new, byteArray_new.length, address, port);
        socket.send(packet);
    }
    
    
    
    static byte[] encrypt(byte[] or)
    {
        byte[] enc = new byte[or.length];
        for (int i = 0; i < or.length; i++) {
            
            enc[i] = (byte) (or[i]^2);
        }
        
        return enc;
    }
    
}
