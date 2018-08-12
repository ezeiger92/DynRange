package com.chromaclypse.dynrange;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapAPI;
import org.dynmap.markers.MarkerAPI;
import org.dynmap.markers.PlayerSet;

import com.chromaclypse.api.Log;

public class Dynrange extends JavaPlugin {
	
	Plugin dynmapHandle = null;
	DynmapAPI apiHandle = null;
	RangeCommand commandHandler;
	
	DynrangeConfig config = new DynrangeConfig();
	
	private class PluginListener implements Listener {
		@EventHandler(priority=EventPriority.MONITOR)
		public void onPluginEnable(PluginEnableEvent event) {
			String pluginName = event.getPlugin().getDescription().getName();
			
			if(pluginName == "dynmap") {
				Startup();
			}
		}
	}
	
	public DynrangeConfig getConfigObject() {
		return config;
	}
	
	private class NearbyUpdate implements Runnable {
		public void run() {
			for(Player player : getServer().getOnlinePlayers()) {
				
				List<Entity> entities = player.getNearbyEntities(
						config.range.x, 
						config.range.y,
						config.range.z);
				
				Set<String> nearbyPlayers = new HashSet<String>();
				nearbyPlayers.add(player.getName());
				
				for(Entity e : entities)
					if(e instanceof Player) {
						Player p = (Player)e;
						if(!player.canSee(p))
							continue;
						
						nearbyPlayers.add(p.getName());
					}
				
				MarkerAPI markerAPI = apiHandle.getMarkerAPI();
				PlayerSet set = markerAPI.getPlayerSet("dynrange." + player.getName());
				
				if(set == null)
					set = markerAPI.createPlayerSet("dynrange." + player.getName(), true, nearbyPlayers, false);
				else
					set.setPlayers(nearbyPlayers);
			}
		}
	}
	
	@Override
	public void onEnable() {
		applyConfig();
		
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
		dynmapHandle = getServer().getPluginManager().getPlugin("dynmap");
		
		commandHandler = new RangeCommand(this);
        PluginCommand cmd = getCommand("dynrange");
        
        cmd.setExecutor(commandHandler);
        cmd.setTabCompleter(commandHandler);
		
		if(dynmapHandle == null) {
			Log.info("Could not find Dynmap, disabling plugin");
			return;
		}
		
		apiHandle = (DynmapAPI)dynmapHandle;
		
		// onPlugin(String, Runnable);
		
		if(dynmapHandle.isEnabled()) {
			Startup();
		}
	}
	
	public void applyConfig() {
		config.init(this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	private void Startup() {
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new NearbyUpdate(), 40,
				config.range.interval);
	}
}
