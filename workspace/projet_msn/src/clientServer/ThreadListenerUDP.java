package clientServer;

import java.net.*;

public class ThreadListenerUDP extends Thread {

	private static int port;
	private final static int size = 2048;
	private static byte buffer[] = new byte[size];
	private DatagramSocket socket;
	
	public ThreadListenerUDP(int port)
	{
		this.port = port;
	}

	public void run() 
	{
		try 
		{
			socket = new DatagramSocket(port);
			while (true) 
			{
				DatagramPacket data = new DatagramPacket(buffer, buffer.length);
				socket.receive(data);
				System.out.println(data.getAddress());
				System.out.println(new String(data.getData()));
				buffer = new byte[size];
			}
		} catch (Exception e) 
		{
			// TODO: handle exception
		}
	}
	
	public static int getPort()
	{
		return port;
	}

}