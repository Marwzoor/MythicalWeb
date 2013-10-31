package net.daedwin.mythicalweb;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class MythicalWeb extends JavaPlugin
{	
	private static Set<String> disallowedIps = new HashSet<String>();
	private static WebServer wb;
	
	public void onEnable()
	{
		File folder = this.getDataFolder();
		
		if(!folder.exists())
		{
			if(!folder.mkdirs())
				return;
		}
		
		File file = new File(folder + "/config.yml");
		
		if(!file.exists())
		{
			try
			{
				file.createNewFile();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}
		}
		
		FileConfiguration config = new YamlConfiguration();
		
		try
		{
			config.load(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return;
		}
		
		if(!config.contains("server-port"))
			config.set("server-port", 25545);
		
		if(!config.contains("disallowed-ips"))
			config.set("disallowed-ips", new ArrayList<String>());
		
		try
		{
			config.save(file);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		int port = config.getInt("server-port");
				
		disallowedIps.addAll(config.getStringList("disallowed-ips"));
		
		wb = new WebServer(port);
	}
	
	public static String[] getDisallowedIps()
	{
		return disallowedIps.toArray(new String[disallowedIps.size()]);
	}
	
	public static void allowIp(String ip)
	{
		disallowedIps.remove(ip);
	}
	
	public static void disallowIp(String ip)
	{
		disallowedIps.remove(ip);
	}
	
	public static boolean isDisallowedIp(String ip)
	{
		return disallowedIps.contains(ip);
	}
	
	public static WebServer getWebServer()
	{
		return wb;
	}
}
