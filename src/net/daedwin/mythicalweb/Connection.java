package net.daedwin.mythicalweb;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//import java.io.PrintWriter;
import java.net.Socket;
/*import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.daedwin.mythicalrpg.mythplayer.MythPlayer;
import net.daedwin.mythicalrpg.mythplayer.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;*/

import org.bukkit.Bukkit;

public class Connection extends Thread
{
	private Socket con;
	
	public Connection(Socket con)
	{
		this.con = con;
		start();
	}
	
	public void run()
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));	
		
			String path = "";
		    String str;
		    while (((str = in.readLine()) != null) && (str != ""))
		    {
		        if (str.contains("GET"))
		        {
		        	path = str.split(" ")[1];
		        	break;
		        }
		    }
		    
		    if(path.contains("favicon.ico"))
		    {
		    	con.close();
		    	return;
		    }
		    
		    path = path.replace(" ", "");
		    
		    WebConnectionEvent wce = new WebConnectionEvent(con, path);
		    
		    Bukkit.getPluginManager().callEvent(wce);
		    
		    if(wce.isCancelled())
		    {
		    	con.close();
		    	return;
		    }
					    			
			/*PrintWriter out = new PrintWriter(con.getOutputStream(), true);
			
			out.println("<html>");
			
			path = path.replace(" ", "").replaceFirst("/", "");
			
			Webpage wb = MythicalWeb.getWebpage(path);
			
			if(wb==null)
			{
				out.println("<html><h1>404 Page not found.</h1></html>");
				con.close();
				return;
			}
			
			out.println(wb.getContents());
			
			/*if(path.contentEquals("/"))
			{
				out.println("<head>"
						+ "<title>Myths of Daedwin</title>");
				out.println("<style>"
						+ "body {"
						+ "background: url('http://daedwin.net/files/bgdaedwin.jpg');"
						+ "background-size: 100% 100%;"
						+ "}"
						+ "#header img {"
						+ "width: 65%;"
						+ "height: auto;"
						+ "display: block;"
						+ "margin: auto;"
						+ "}"
						+ "</style>");
				out.println("</head>");
				
				out.println("<div id='header'>"
						+ "<img src='http://daedwin.net/files/datelogo.png'></img>"
						+ "<div style='display: block; margin: auto; border: 2px solid #181818; border-radius: 15px; background-color: #000000; width: 90%; min-height: 400px;' />");
				
				out.println("<table style='border-collapse: seperate; border-spacing: 10px 5px;' border='0'>");
				
				List<MythPlayer> mps = new ArrayList<MythPlayer>();
				
				for(Player p : Bukkit.getOnlinePlayers())
				{
					MythPlayer mp = PlayerManager.getMythPlayer(p);
					
					if(mp==null)
						continue;
					
					mps.add(mp);
				}
				
				Collections.sort(mps, new MythPlayerComparator());
				
				out.println("<tr>"
						+ "<td style='color: #ffffff; padding-right: 500px;'>Name</td>"
						+ "<td style='color: #ffffff; padding-right: 150px;'>Nation</td>"
						+ "<td style='color: #ffffff;'>Class</td>"
						+ "</tr>");
				
				out.println("<tr>");
				
				out.println("<td style='color: #ffffff;'>");
				for(MythPlayer mp : mps)
				{
					out.println("<a style=\"font-family: Cambria; font-size: 16pt; \" href=\"/" + mp.getPlayer().getName() + "\">" + mp.getPlayer().getName() + "</a>");
					out.println("<br>");
				}
				out.println("</td>");
				
				out.println("<td style='color: #ffffff;'>");
				for(MythPlayer mp : mps)
				{
					if(mp.getNation()!=null)
						out.println(mp.getNation().getName());
					out.println("<br>");
				}
				out.println("</td>");
				
				out.println("<td style='color: #ffffff;'>");
				for(MythPlayer mp : mps)
				{
					if(mp.getMythClass()!=null)
						out.println("Level " + mp.getMythLevel() + " " + mp.getMythClass().getName());
					out.println("<br>");
				}
				out.println("</td>");
				
				out.println("</tr>");
				
				out.println("</table>");
				
				out.println("</div>");
			}
			else
			{
				String pName = path.replace("/", "");
				
				Player p = Bukkit.getPlayer(pName);
				
				if(p==null)
				{
					out.println("<h2>That player is not online.</h2>");
				}
				else
				{
					out.println("<div style='padding: 15px; border: 1.5px solid #000000;'><table border='0'><tr><td><img src='https://minotar.net/helm/" + p.getName() + "/100.png' /></td>");
					
					out.println("<td><div style='margin-top: 15px; margin-left: 15px;'><p>Player: " + p.getName() + "</p>");
					MythPlayer mp = PlayerManager.getMythPlayer(p);
					
					int time = (int) mp.getTimeOnline();
					int hours = time / ((1000*60*60));
					time = time%((1000*60*60));
					int minutes = time / ((1000*60));
					time = time%((1000*60));
					int seconds = time / ((1000));
					
					out.println("<p>Online Time: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds.</p>");
					out.println("<p>Class: " + mp.getMythClass().getName() + "</p>");
					out.println("<p>Class-Level: " + mp.getMythLevel() + "</p>");
					out.println("<p>Power: " + mp.getPowerSource().getAmount() + "/" + mp.getPowerSource().getMaxAmount() + "</p></td></div></tr></table></div>");
				}
			}
			
			out.println("</div>");
			
			out.println("</html>");*/
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
