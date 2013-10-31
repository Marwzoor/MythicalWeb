package net.daedwin.mythicalweb;

import java.net.Socket;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class WebConnectionEvent extends Event implements Cancellable
{
	private boolean cancel = false;
	private static final HandlerList handlers = new HandlerList();
	private final Socket con;
	private final String path;
	
	public WebConnectionEvent(Socket con, String path)
	{
		this.con=con;
		this.path=path;
	}
	
	public Socket getConnection()
	{
		return this.con;
	}
	
	public String getPath()
	{
		return this.path;
	}

	public static HandlerList getHandlerList()
	{
		return handlers;
	}
	
	@Override
	public boolean isCancelled()
	{
		return this.cancel;
	}
	
	@Override
	public void setCancelled(boolean bool)
	{
		this.cancel=bool;
	}
	
	@Override
	public HandlerList getHandlers()
	{
		return handlers;
	}
}
