package com.server;

import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servertest {
	public static void main(String[] args){
		try {
			System.out.println("连接开始。。。。!");
			ServerSocket ss=new ServerSocket(9999);
			Socket s=ss.accept();
			System.out.println("连接成功!");
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			File file=new File("F:\\source\\tool_soft");
			File[] files=file.listFiles();
			oos.writeObject(files);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
