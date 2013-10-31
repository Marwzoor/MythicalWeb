package net.daedwin.mythicalweb;

import java.util.Comparator;

import net.daedwin.mythicalrpg.mythplayer.MythPlayer;

public class MythPlayerComparator implements Comparator<MythPlayer>
{
	@Override
	public int compare(MythPlayer mp1, MythPlayer mp2) 
	{
		return mp1.getMythLevel()-mp2.getMythLevel();
	}
}
