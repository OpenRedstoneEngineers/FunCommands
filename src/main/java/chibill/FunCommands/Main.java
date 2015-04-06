package chibill.FunCommands;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import chibill.FunCommands.utils.FileArrayProvider;
import chibill.FunCommands.utils.Register;


public class Main extends JavaPlugin implements Listener {

	public File DerpsFile = new File(this.getDataFolder()+File.separator+"Derps.txt");
	public static String[] DerpList;
	public static Register register = new Register();
	private Material[] Foods = {Material.APPLE,Material.MUSHROOM_SOUP,Material.BREAD,Material.PORK,Material.COOKED_FISH,Material.CAKE,Material.COOKIE,Material.MELON,Material.COOKED_BEEF,Material.COOKED_CHICKEN,Material.CARROT,Material.POTATO,Material.PUMPKIN_PIE,Material.RABBIT_STEW,Material.COOKED_RABBIT,Material.COOKED_MUTTON,Material.MILK_BUCKET};
	@Override
    public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		if(!(DerpsFile.exists())) {
			getLogger().log(Level.WARNING, "No Derps.txt Detected! This is an ERROR on your part!");
		}else{
			try {
				DerpList = FileArrayProvider.readLines(DerpsFile);
			} catch (IOException e) {
				getLogger().log(Level.WARNING, "Error reading Derps.txt", e);
			}
		}
		
		register.setPlugin(this);
		FunCommands.ReadCommands(this.getDataFolder()+File.separator+"Commands.json");
		
		FunCommands.registerCommands();
	}
    @Override
    public void onDisable() {
    	FunCommands.SaveCommands(this.getDataFolder()+File.separator+"Commands.json");
    	FunCommands.unregisterCommands();
    	
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	if(cmd.getName().equalsIgnoreCase("derps")){
    		Derps.Derps(sender);
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("funs")){
    		FunCommands.list(sender);
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("derp")){
    	
    		if(args.length < 1){
    			Derps.Derp(sender);
    		}else{
    			try{
    			Derps.Derp(Integer.parseInt(args[0]),sender);
    			}catch(Exception e){
    			sender.sendMessage("That is not a vaild number for a derp.");	
    			sender.sendMessage(e.toString());
    			}
    			}
    		return true;
    	}

    	if(cmd.getName().equalsIgnoreCase("foodfight")){
    		if(args.length >0){
    			Player victim;
    			try{
    				victim = Bukkit.getPlayer(args[0]);
    			}catch(Exception e){
        			sender.sendMessage("§c[ERROR] No such player.");
        			return true;
    			}
    			
    			int temp =(int)(Math.random()*Foods.length);
    			
    			Bukkit.broadcastMessage("§5" + sender.getName() +"§e threw a/an §6 " + Foods[temp].name() +"§c at §5" + victim.getName());
    			
    			victim.getInventory().addItem(new ItemStack(Foods[temp]));
    			
    			temp =(int)(Math.random()*5);
    			
    			if(temp == 0){
    				
    				victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1,true));    				
    				victim.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 40, 1,true));
    				Bukkit.broadcastMessage("§5 Headshot!");
    			}
    			
    		}else{
    			
    			sender.sendMessage("§c[ERROR] You must specify who you are to throw food at.");
    		}
    		return true;}
    	if(cmd.getName().equalsIgnoreCase("remove")){
    		if(args.length >0){
    		FunCommands.removeCommand(args[0],sender);
    		FunCommands.unregisterCommands();
    		FunCommands.registerCommands();
    		return true;
    		}else{
    			sender.sendMessage("§c[ERROR] You must specify the command to remove!");
    			return true;
    		}
    	}
    	if(cmd.getName().equalsIgnoreCase("deny")|cmd.getName().equalsIgnoreCase("deny-all")){
    		if(args.length >0){
    			
    			 FunCommands.deny(sender,args[0],this.getDataFolder()+File.separator+"UnAproved.json");
    			
    		}else{
    			if(cmd.getName().equalsIgnoreCase("deny-all")){
    				 FunCommands.denyAll(args[0],this.getDataFolder()+File.separator+"UnAproved.json");
    			}else{
    			sender.sendMessage("§cYou must give a command to deny!");
    		}}
    		return true;}
    	if(cmd.getName().equalsIgnoreCase("aprove")|cmd.getName().equalsIgnoreCase("aprove-all")){
    		if(args.length >0){
    			try{
    			 FunCommands.ApproveCommand(sender, args[0] ,this.getDataFolder()+File.separator+"UnAproved.json",this.getDataFolder()+File.separator+"Commands.json");
    			}catch(Exception e){
    				
    			}
    		}else{if(cmd.getName().equalsIgnoreCase("aprove-all")){
    			 FunCommands.ApproveALL(sender,this.getDataFolder()+File.separator+"UnAproved.json", this.getDataFolder()+File.separator+"Commands.json");
    		}else{
    			sender.sendMessage("§cYou must give a command to aprove!");
    		}
    		
    		}
    		return true;}
    	if(cmd.getName().equalsIgnoreCase("add")){
    		if(args.length >0){
    			
    			String temp ="";
    			
    			for(int temp2 = 0;temp2 <args.length;++temp2){
    				
    				temp = temp + args[temp2]+" ";
    				
    			}
    			return FunCommands.AddCommand(temp, sender,this.getDataFolder()+File.separator+"UnAproved.json");
    			
    		}else{
    			sender.sendMessage("§c[ERROR] You must specify the command to add in JSON. See the forum post for more info!");
    			return true;
    		}
    	}
    	if(cmd.getName().equalsIgnoreCase("slap")){
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
                
                Bukkit.broadcastMessage("§5" + sender.getName() + "§c slapped §5" + victimName + "§c about a bit with a/an §6" +slap);
                
    		}else{
    			sender.sendMessage("§c[ERROR] You must specify who you are slapping");

    		}
    		return true;}
    	if(sender.hasPermission("FunCommands.fun")){
    		return FunCommands.onCommand(sender, cmd.getName(), args);
    	}
    	return false;
    		
    	
    }
    
    @EventHandler
    public void onLogin(PlayerJoinEvent event) {

    	if(event.getPlayer().hasPermission("FunCommands.fun.aprove")){
    		
    		FunCommands.Aprove(event.getPlayer(),this.getDataFolder()+File.separator+"UnAproved.json");
    		
    	}
    	
    }
	
}
