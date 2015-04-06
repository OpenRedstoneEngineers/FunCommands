package chibill.FunCommands;

import java.util.Random;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Derps {

	public static Player player;
	
	static Random rand = new Random();
	
	public static void Derp(int x, CommandSender sender){
		try{
		
		sender.getServer().broadcastMessage("§2 * §f"+sender.getName()+"§1 DERP!  §d"+Main.DerpList[x]);

		}catch(Exception e){
		
			sender.sendMessage("That is not a vaild Derp number!");
		}
	}
	
	public static void Derp(CommandSender sender){
		
		sender.getServer().broadcastMessage("§2 * §f"+sender.getName()+"§1 DERP!  §d"+Main.DerpList[rand.nextInt(Main.DerpList.length-1) + 1]);

		
	}

	public static void Derps(CommandSender sender) {
		
	for(int temp = 0; temp<=Main.DerpList.length-1;temp++){
	 sender.sendMessage(temp+". "+Main.DerpList[temp]);	
	}}
}

