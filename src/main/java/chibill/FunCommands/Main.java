package chibill.FunCommands;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin {

		private Material[] Foods = {Material.APPLE,Material.MUSHROOM_SOUP,Material.BREAD,Material.PORK,Material.COOKED_FISH,Material.CAKE,Material.COOKIE,Material.MELON,Material.COOKED_BEEF,Material.COOKED_CHICKEN,Material.CARROT,Material.POTATO,Material.PUMPKIN_PIE,Material.RABBIT_STEW,Material.COOKED_RABBIT,Material.COOKED_MUTTON,Material.MILK_BUCKET};
	
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
	    	
	    	case "derp":
	    		if(sender.hasPermission("FunCommmands.derp")){
	    		Derps.GetRandomDerp(sender, args);
	    		}
	    		return true;
	    	case "derps":
	    		if(sender.hasPermission("FunCommmands.derp")){
	    		Derps.GetDerpList(sender);
	    		}
	    		return true;
	    		
	    	case "slap":
	        		if(args.length >0){
	        			Player victim;
	        			try{
	        				victim = Bukkit.getPlayer(args[0]);
	        			}catch(Exception e){
	            			sender.sendMessage("§c[ERROR] No such player.");
	            			return true;
	        			}
	                    String slap = "large trout";
	                    String victimName = "";
	                    try{
	                    	 victimName = victim.getName();
	        			}catch(Exception e){
	            			sender.sendMessage("§c[ERROR] No such player.");
	            			return true;
	        			}
	                    
	                    if(victim.equals(Bukkit.getPlayer(sender.getName()))){
	    					victimName = "themselves";
	    				}
	    				
	                    try{
	        				
	                    	slap = args[1];
	        				
	        			}catch(Exception e){
	        			}
	                    
	                    Bukkit.broadcastMessage("§5" + sender.getName() + "§c slapped §5" + victimName + "§c about a bit with a" + (slap.matches("^[aeiou].*") ? "n" : "") +  "§6" +slap);
	                    
	        		}else{
	        			sender.sendMessage("§c[ERROR] You must specify who you are slapping");

	        		}
	        		return true;

	    	case "foodfight":
	    		if(args.length >0){
	    			Player victim;
	    			try{
	    				victim = Bukkit.getPlayer(args[0]);
	    			}catch(Exception e){
	        			sender.sendMessage("§c[ERROR] No such player.");
	        			return true;
	    			}
	    			
	    			int temp =(int)(Math.random()*Foods.length);
	    			
	    			Bukkit.broadcastMessage("§5" + sender.getName() +"§e threw a" + (Foods[temp].name().matches("^[aeiou].*") ? "n" : "") + " §6 " + Foods[temp].name() +"§c at §5" + victim.getName());
	    		if(victim.getGameMode() != GameMode.SURVIVAL & victim.getGameMode() !=  GameMode.ADVENTURE ){
	    			victim.getInventory().addItem(new ItemStack(Foods[temp]));
	    		}
	    			temp =(int)(Math.random()*5);
	    			
	    			if(temp == 0){
	    				
	    				victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1,true));    				
	    				victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1,true));
	    				Bukkit.broadcastMessage("§5 Headshot!");
	    			}
	    			
	    		}else{
	    			
	    			sender.sendMessage("§c[ERROR] You must specify who you are to throw food at.");
	    		}
	    		return true;
	    	default:
	    		if(DynamicCommands.CommandNames.contains(cmd.getName().toLowerCase())){
	    			return DynamicCommands.runCommand(sender,cmd.getName().toLowerCase(),args);
	    		}else{
	    			return false;
	    		}
	    	}
	    	
	    }
	    
}
