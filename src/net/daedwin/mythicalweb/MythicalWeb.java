package net.daedwin.mythicalweb;

import org.bukkit.plugin.java.JavaPlugin;

public class MythicalWeb extends JavaPlugin
{	
	public void onEnable()
	{
		new WebServer(25545);
	}
}
