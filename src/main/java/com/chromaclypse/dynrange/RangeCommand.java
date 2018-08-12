package com.chromaclypse.dynrange;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class RangeCommand implements CommandExecutor, TabExecutor {
	Dynrange dynrangeHandle;
	
	public RangeCommand(Dynrange dynrange) {
		dynrangeHandle = dynrange;
	}

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		/*Placeholder ph = new Placeholder();
		Player p = (Player)sender;
		
		try {
			Log.info("" + ph.eval("player", new Placeholder.PlayerContext(p)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Log.info("" + ph.eval("test", new Placeholder.PlayerContext(p)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Log.info("" + ph.eval("test", new Placeholder.GenericContext<Player>(p)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Log.info("" + ph.eval("test", new Placeholder.GenericContext<Integer>(12)));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		if(args.length == 0) {
			sender.sendMessage("[DynRange] Version 1.0.0");
			sender.sendMessage("[DynRange] /dynrange reload to reload");
		}
		else if(args[0].equals("reload")) {
			boolean hasPermission = sender.hasPermission("dynrange.reload");
			
			if(!hasPermission) {
				return false;
			}
			
			sender.sendMessage("[DynRange] Reloading config");
			dynrangeHandle.applyConfig();
		}
		
		return true;
	}

	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		List<String> result = new ArrayList<String>();
		result.add("reload");
		
		if(args.length == 0) {
			return result;
		}
		if(args.length == 1) {
			if("reload".startsWith(args[0])) {
				return result;
			}
		}
		
		return null;
	}
	

	
	
}
