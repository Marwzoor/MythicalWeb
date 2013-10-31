package net.daedwin.mythicalweb;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.bukkit.Bukkit;

public class WebServer extends Thread 
{
	private ServerSocket ss;
	private int port;
	private boolean run = false;
	private long buffered;
	
	public WebServer(int port)
	{
		this.port=port;
		start();
	}
	
	public void setRunning(boolean bool)
	{
		this.run=bool;
	}
	
	public void run()
	{
		try
		{
			buffered = System.currentTimeMillis();
			
			System.out.println("[MythicalWeb] Starting server on port " + port + "...");
			
			ServerSocket s = new ServerSocket(port, 0, InetAddress.getByName(Bukkit.getIp()));
			
			System.out.println("[MythicalWeb] Server started!");
			
			this.ss=s;
			
			run=true;
			
			while(run)
			{
				if(System.currentTimeMillis() - buffered > 10L)
				{
					Socket con = ss.accept();
				
					if(MythicalWeb.isDisallowedIp(con.getInetAddress().getHostAddress()))
					{
						con.close();
						continue;
					}
					
					new Connection(con);
					
					this.buffered = System.currentTimeMillis();
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
