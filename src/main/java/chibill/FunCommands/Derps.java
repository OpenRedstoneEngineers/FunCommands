package chibill.FunCommands;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import chibill.FunCommands.utils.FileArrayProvider;

public class Derps {

	private static final File DerpsFile =  new File(Main.PluginFolder+File.separator+"Derps.txt");

	private static String[] derps;
	
	private static Random rand = new Random();
	
	public static void ReadDerps(){
		
		if(!(DerpsFile.exists())) {
			Main.logger.log(Level.WARNING, "No Derps.txt Detected! This is an ERROR on your part!");
		}else{
			try {
				derps = FileArrayProvider.readLines(DerpsFile);
			} catch (IOException e) {
				Main.logger.log(Level.WARNING, "Error reading Derps.txt", e);
			}
		}
		
	}
	
	public static void GetRandomDerp(CommandSender sender,String[] args){
		try{
			sender.getServer().broadcastMessage(ChatColor.DARK_GREEN+" * "+ChatColor.WHITE+sender.getName()+ChatColor.DARK_BLUE+" DERP!  "+ChatColor.LIGHT_PURPLE+derps[Integer.parseInt(args[0])]);
		}catch(Exception e){
			sender.getServer().broadcastMessage(ChatColor.DARK_GREEN+" * "+ChatColor.WHITE+sender.getName()+ChatColor.DARK_BLUE+" DERP!  "+ChatColor.LIGHT_PURPLE+derps[rand.nextInt(derps.length-1) + 1]);

		}
	}
	
	public static void GetDerpList(CommandSender sender){
		
		for(int temp = 0; temp<=derps.length-1;temp++){
			
			 sender.sendMessage(temp+". "+derps[temp]);	
		
		}
		
	}
	
}
