package org.eclipse.gemoc.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

public class ExtensibleInputStream extends InputStream {

	LinkedBlockingQueue<InputStream> messages = new LinkedBlockingQueue<>();

	@Override
	public int read() throws IOException {
		
//		System.out.println("[DEBUG] Read InputStream");
		
		waitForMessage();
		
		InputStream current = messages.peek();
		int c = current.read();
		if(c != -1) {
			return c;
		}
		else {
//			System.out.println("[DEBUG] End InputStream 2");
			messages.poll();
			
			waitForMessage();
			
			current = messages.peek();
			return current.read();
		}
	}
	
	public void addMessage(String message) {
//		System.out.println("[DEBUG] Fill InputStream");
		messages.add(new ByteArrayInputStream(message.getBytes()));
		synchronized (this) {
			this.notify();
		}
	}
	
	public void waitForMessage() {
		if(messages.isEmpty()) {
			try {
				synchronized (this) {
					this.wait();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			System.out.println("[DEBUG] Wait");
		}
	}
}
