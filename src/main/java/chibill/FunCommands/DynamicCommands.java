package chibill.FunCommands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import chibill.FunCommands.utils.Register;
import chibill.FunCommands.Command;

public class DynamicCommands {

	private static List<Command> Commands = new ArrayList<Command>();
	
	public static List<String> CommandNames = new ArrayList<String>();
	
	private static final File CommandFile =  new File(Main.PluginFolder+File.separator+"Commands.json");
	
	public static void ReadCommands() throws IOException{
	
		Main.logger.info("Reading FunCommands from Commands.json!");
    	
	   	   FileReader fileReader = null;

	   	   JSONParser parser = new JSONParser();
	   	   
	   	   try {
	   		   
	   		   fileReader = new FileReader(CommandFile);
			
	   	   }catch (FileNotFoundException e) {
	   	
	   		   Main.logger.severe("Error opening Commands.json Do you have one? "+ e.toString());
		
	   	   }
	   	   
	   	JSONArray jsonObject = (JSONArray) null;
	   	   
	   	   try {
	   		
	   		   jsonObject = (JSONArray) parser.parse(fileReader);
	   	   
	   	   } catch (Exception e) {
		
			Main.logger.warning("Can't read Commands.json. You sure its formated right?");
		
	   	   }
	   	   
	   	   @SuppressWarnings("unchecked")
	   	   Iterator<JSONObject> iterator = jsonObject.iterator();
	   	   
	   	   int  temp2 = 0;
	   	   
	   	   while (iterator.hasNext()) {
	   		
	   		   JSONObject temp = iterator.next();
	   		
	   		   Command command = new Command((String) temp.get("Command"), "FunCommands."+(String) temp.get("Command"), (String) temp.get("Description"), (String) temp.get("Function"));
	   	   
	   		   Commands.add(command);
	   		   CommandNames.add((String) temp.get("Command"));
	   		   
	   		   temp2++;
	   		   
	   	   }
	   Main.logger.info(temp2 + " Commands loaded!");
	   	try {
			fileReader.close();
		} catch (IOException e) {
			Main.logger.severe("Can't close COmmands.json.. Thats not right!");
		}
	}

	public static void RegisterCommands(){
		Register reg = new Register();
		reg.setPlugin(Main.plugin);
		for(Command x:Commands){
			
			reg.registerCommand(x.getCommand(), x.getDescription(),x.getPermission());
			
		}
		
	}

	public static boolean runCommand(CommandSender sender, String cmd,String[] args) {
	
		for(Command x:Commands){
			if(x.getCommand().equals(cmd)){
				
				String function = x.getFuction();
				
				if(function.contains("<arg-8>") & args.length<9){
					sender.sendMessage("This Command reqires 9 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-7") & args.length<8){
					sender.sendMessage("This Command reqires 8 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-6>") & args.length<7){
					sender.sendMessage("This Command reqires 7 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-5>") & args.length<6){
					sender.sendMessage("This Command reqires 6 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-4>") & args.length<5){
					sender.sendMessage("This Command reqires 5 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-3>") & args.length<4){
					sender.sendMessage("This Command reqires 4 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-2>") & args.length<3){
					sender.sendMessage("This Command reqires 3 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-1>") & args.length<2){
					sender.sendMessage("This Command reqires 2 args. "+ x.getDescription());
				}else{
				if(function.contains("<arg-0>") & args.length<1){
					sender.sendMessage("This Command reqires 1 arg. "+ x.getDescription());
				}}}}}}}}}
				function = function.replace("<colour-0>","§0").replace("<colour-1>","§1").replace("<colour-2>", "§2").replace("<colour-3>","§3").replace("<colour-4>","§4").replace("<colour-5>","§5").replace("<colour-6>","§6").replace("<colour-7>","§7").replace("<colour-8>","§8").replace("<colour-9>","§9").replace("<colour-a>","§a").replace("<colour-b>","§b").replace("<colour-c>","§c").replace("<colour-d>","§d").replace("<colour-e>","§e").replace("<colour-f>","§f").replace("<colour-k>","§k").replace("<colour-m>","§m").replace("<colour-n>","§n").replace("<colour-o>","§o").replace("<colour-r>","§r").replace("<colour-l>","§l");
				
				function = function.replace("<name>", sender.getName());
				
				String argall = "";
				
				for(String y:args){
					argall = argall + " " + y;
				}
				
				for(int r = 0;r < args.length;r = r + 1){
					
					function.replace("<arg-"+r+">",args[r]);
					
				}
				
				function = function.replace("<arg-all>", argall);
				
				
				String output = function.split("<run>")[0];
				
				String runable = "";
				
				if(function.split("<run>").length > 1){
					runable = function.split("<run>")[1];
				}
				
				if(!sender.hasPermission(x.getPermission())){
					sender.sendMessage("You do not have permission to run this command!");
					return true;
				}
				if(runable.length() > 0){
				Bukkit.dispatchCommand(sender, runable);
				}
				
				if(function.startsWith("<self>")){
					function.replace("<self>", "");
					sender.sendMessage(output);
				}else{
					Bukkit.broadcastMessage(output);
				}
				
			}
		}
		
		return true;
		
	}
	
}
