package chibill.FunCommands;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

		private Object[][] Foods = {{Material.APPLE.getId(),"apple"},{Material.MUSHROOM_SOUP.getId(),"mushroom stew"},{Material.BREAD.getId(),"bread"},{Material.PORK.getId(),"pork"},{Material.COOKED_FISH.getId(),"cooked fish"},{Material.CAKE.getId(),"cake"},{Material.COOKIE.getId(),"cookie"},{Material.MELON.getId(),"melon"},{Material.COOKED_BEEF.getId(),"beef"},{Material.COOKED_CHICKEN.getId(),"cooked chicken"},{Material.CARROT.getId(),"carrot"},{Material.POTATO.getId(),"potato"},{Material.PUMPKIN_PIE.getId(),"pumpkin pie"},{Material.RABBIT_STEW.getId(),"rabbit stew"},{Material.COOKED_RABBIT.getId(),"cooked rabbit"},{Material.COOKED_MUTTON.getId(),"cooked mutton"},{Material.MILK_BUCKET.getId(),"milk bucket"}};
	
		public static Logger logger;
	
		public static Plugin plugin;
		
		public static File PluginFolder;
		
	  @Override
	    public void onEnable() {
		  plugin = this;
	      logger = getLogger();
		  PluginFolder = getDataFolder();
		  Derps.ReadDerps();
		  try {
			DynamicCommands.ReadCommands();
		} catch (IOException e) {
			logger.severe("Unable to read commands something is not right");
		}
		  DynamicCommands.RegisterCommands();
	    }
	 
	    @Override
	    public void onDisable() {
	       
	    }
	
	    @Override
	    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	    
	    	switch(cmd.getName().toLowerCase()){
	    	
	    	case "funversion":
	    			sender.sendMessage("FunCommands version: "+this.getDescription().getVersion());
	    		return true;
	    	
	    	case "derp":
	    		Derps.GetRandomDerp(sender, args);
	    		return true;
	    	case "derps":
	    		Derps.GetDerpList(sender);
	    		return true;
	    		
	    	case "slap":
	    		
	        		if(args.length >0){
	        			Player victim;
	        			try{
	        				victim = Bukkit.getPlayer(args[0]);
	        			}catch(Exception e){
	            			sender.sendMessage(ChatColor.COLOR_CHAR+"c[ERROR] No such player.");
	            			return true;
	        			}
	                    String slap = "large trout";
	                    String victimName = "";
	                    try{
	                    	 victimName = victim.getName();
	        			}catch(Exception e){
	            			sender.sendMessage(ChatColor.COLOR_CHAR+"c[ERROR] No such player.");
	            			return true;
	        			}
	                    
	                    if(victim.equals(Bukkit.getPlayer(sender.getName()))){
	    					victimName = "themselves";
	    				}
	    				
	                    try{
	        				
	                    	slap = args[1];
	        				
	        			}catch(Exception e){
	        			}
	                    
	                    Bukkit.broadcastMessage(ChatColor.COLOR_CHAR+"5" + sender.getName() + ChatColor.COLOR_CHAR+"c slapped "+ChatColor.COLOR_CHAR+"5" + victimName + ChatColor.COLOR_CHAR+"c about a bit with a" + (slap.matches("^[aeiou].*") ? "n " : " ") +  ChatColor.COLOR_CHAR+"6" +slap);
	                    
	        		}else{
	        			sender.sendMessage(ChatColor.COLOR_CHAR+"c[ERROR] You must specify who you are slapping");

	        		}
	        		return true;
	    		
	    	case "foodfight":
	    		
	    		if(args.length >0){
	    			Player victim;
	    			try{
	    				victim = Bukkit.getPlayer(args[0]);
	    			}catch(Exception e){
	        			sender.sendMessage(ChatColor.COLOR_CHAR+"c[ERROR] No such player.");
	        			return true;
	    			}
	    			
	    			int temp = (int)(Math.random()*Foods.length);
	    			@SuppressWarnings("deprecation")
					ItemStack item = new ItemStack((int) Foods[temp][0]);
	    			Bukkit.broadcastMessage(ChatColor.COLOR_CHAR+"5" + sender.getName() +ChatColor.COLOR_CHAR+"e threw a" + (((String) Foods[temp][1]).matches("^[aeiou].*") ? "n " : " ") + ChatColor.COLOR_CHAR+"6" + ((String) Foods[temp][1]) +ChatColor.COLOR_CHAR+"c at "+ChatColor.COLOR_CHAR+"5" + victim.getName());
	    		if(victim.getGameMode() != GameMode.SURVIVAL & victim.getGameMode() !=  GameMode.ADVENTURE ){
	    			victim.getInventory().addItem(item);
	    		}
	    			temp =(int)(Math.random()*5);
	    			
	    			if(temp == 0){
	    				
	    				victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1,true));    				
	    				victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1,true));
	    				Bukkit.broadcastMessage(ChatColor.COLOR_CHAR+"5 Headshot!");
	    			}
	    			
	    		}else{
	    			
	    			sender.sendMessage(ChatColor.COLOR_CHAR+"c[ERROR] You must specify who you are to throw food at.");
	    		}
	    		return true;
	    		
	    	default: 
	    		if(DynamicCommands.CommandNames.contains(cmd.getName().toLowerCase())){
	    			if(sender.hasPermission("funcommand."+cmd.getName())){return DynamicCommands.runCommand(sender,cmd.getName().toLowerCase(),args);}else{return false;}
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    }
	    
}
