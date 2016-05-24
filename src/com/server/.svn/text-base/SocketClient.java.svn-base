package com.server;

import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket s = null;
		InputStream is = null;
		OutputStream os = null;
		List files = new ArrayList();

		try {
			s = new Socket("127.0.0.1", 9999);
			is = s.getInputStream();
			ObjectInputStream ois = new ObjectInputStream(is);
			byte[] b = new byte[1024];
			int len;
			File[] files1 = (File[]) ois.readObject();
			System.out.println("len==="+files1.length);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
