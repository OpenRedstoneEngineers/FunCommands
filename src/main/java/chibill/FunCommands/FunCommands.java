package chibill.FunCommands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;





public class FunCommands {
		
	private static HashMap<String,String> CommandstoOutput = new HashMap<String,String>();
	
	private static HashMap<String,String> CommandstoDiscription = new HashMap<String,String>();
	
    public static void ReadCommands(String file){
    	   
    	System.out.println("Reading FunCommands from Commands.json!");
    	
   	   FileReader fileReader = null;

   	   JSONParser parser = new JSONParser();
   	   
   	   try {
   		   
   		   fileReader = new FileReader(file);
		
   	   }catch (FileNotFoundException e) {
   	
   		   e.printStackTrace();
	
   	   }
   	   
   	JSONArray jsonObject = (JSONArray) null;
   	   
   	   try {
   		
   		   jsonObject = (JSONArray) parser.parse(fileReader);
   	   
   	   } catch (Exception e) {
	
		e.printStackTrace();
	
   	   }
   	   
   	   @SuppressWarnings("unchecked")
   	   Iterator<JSONObject> iterator = jsonObject.iterator();
   	   
   	   int  temp2 = 0;
   	   
   	   while (iterator.hasNext()) {
   		
   		   JSONObject temp = iterator.next();
   		
   		   CommandstoOutput.put((String)temp.get("Command"),(String) temp.get("Output"));
   		
   		   CommandstoDiscription.put((String)temp.get("Command"),(String) temp.get("Description"));
   	   
   		   temp2++;
   		   
   	   }
   	System.out.println(temp2 + "Commands loaded!");
   	try {
		fileReader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
        }
    public static void SaveCommands(String file){
    	System.out.println("Saving FunCommands to " + file);
    	JSONArray Array = new JSONArray();
    	 Iterator<String> temp = CommandstoDiscription.keySet().iterator();
    	 
    	 while(temp.hasNext()){
    		 String command = temp.next();
    		 
    		 
    		 JSONObject temp2 = new JSONObject();
    		 
    		 temp2.put("Command", command);
    		 temp2.put("Output", CommandstoOutput.get(command));
    		 temp2.put("Description", CommandstoDiscription.get(command));
    		 
    		 Array.add(temp2);
    		 
    	 }
    	
    	 try {
    		 
    			FileWriter file2 = new FileWriter(file);
    		
    			file2.write(Array.toJSONString());
    		
    			file2.flush();
    		
    			file2.close();
    	 
    		} catch (IOException e) {
    		
    			e.printStackTrace();
    		
    		}
    	 
    }
        
    public  static boolean onCommand(CommandSender sender, String cmd, String[] args) {
    	
    	if(CommandstoOutput.containsKey(cmd)){
    		String Out = CommandstoOutput.get(cmd);
    	
    	Out = 	Out.replace("<c-","§");
 
    		Out=Out.replace("<n>",sender.getName());
    	
    		try{
    		Out=Out.replace("<a-0>", args[0]);
   
    		}catch(Exception e){
    			
    		}
    		try{
        		Out=Out.replace("<a-1>", args[1]);
     
        		}catch(Exception e){
        			
        		}
    		
    		
    		if(Out.contains("<r>")){
    			try{
    				
    			if(Out.startsWith("<r>")){
    				if(sender instanceof Player){
    				Bukkit.dispatchCommand(sender, Out.replace(">",""));
    				}else{
    					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Out.replace(">",""));
    				}}else{
    					
    			String[] temp = Out.split("<r>");
    		
    			sender.getServer().broadcastMessage(temp[0].replace(">",""));
    			if(sender instanceof Player){
    			Bukkit.dispatchCommand(sender,temp[1].replace(">",""));
    			
    			}else{
    				Bukkit.dispatchCommand(sender, temp[1].replace(">",""));
    				
    			}}}catch(Exception e){sender.sendMessage("§c[Error] Something went wrong with this command! "+e.toString());
    			}
    			return true;
    			}
    			
    			
    		
    			
    		if(Out.contains("<s>")){
    			
    			try{
    				
        			if(Out.startsWith("<s>")){
        				
        					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Out.replace(">",""));
        				}else{
        					
        			String[] temp = Out.split("<s>");
      
        			sender.getServer().broadcastMessage(temp[0].replace(">",""));
        			
        				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), temp[1].replace(">",""));
        			
        			}}catch(Exception e){sender.sendMessage("§c[Error] Something went wrong with this command! "+e.toString());
        			}
        			return true;
        			}
    		if(Out.contains("<t>")){
    			sender.sendMessage(Out.replace("<t>", "").replace(">",""));
    			
    			
    			return true;
    		}
    			Out = Out.replace(">","");
    		sender.getServer().broadcastMessage(Out.replace(">",""));
    		
    		return true;
    	}
    	
    	return false;
    	
    }


	public static void list(CommandSender sender) {
		
		Iterator<String> temp = CommandstoDiscription.keySet().iterator();
		sender.sendMessage("List of Fun Commands use /fun then there name to run");
		while(temp.hasNext()){
			String temp2 = temp.next();
			sender.sendMessage(temp2+" : " +CommandstoDiscription.get(temp2));
			
		}
		
	}


	public static void registerCommands() {	
		Iterator<String> temp = CommandstoDiscription.keySet().iterator();
		
		while(temp.hasNext()){
			String temp2 = temp.next();
			
			Main.register.registerCommand(temp2,CommandstoDiscription.get(temp2),"/"+temp2);
			
		}
		
		
	}
	
	public static boolean AddCommand(String command,CommandSender sender,String file){

   	   FileReader fileReader = null;

   	   JSONParser parser = new JSONParser();
   	   
   	   try {
   		   
   		   fileReader = new FileReader(file);
		
   	   }catch (FileNotFoundException e) {
   	
   		   e.printStackTrace();
	
   	   }
   	   
   	JSONArray jsonObject = (JSONArray) null;
   	   
   	   try {
   		
   		   jsonObject = (JSONArray) parser.parse(fileReader);
   	   
   	   } catch (Exception e) {
	
		e.printStackTrace();
	
   	   }
   	JSONObject temp = null;
		try{
			
		
		temp =(JSONObject) JSONValue.parse(command);
		
		if(!temp.containsKey("Command")){
			throw new Exception();
		}
		if(!temp.containsKey("Output")){
			throw new Exception();
		}
		if(!temp.containsKey("Description")){
			throw new Exception();
		}
		
		if(CommandstoOutput.containsKey(temp.get("Command"))){
			sender.sendMessage("That command is already registered!");
			return true;
		}
		
		
		}catch(Exception e){
			
			sender.sendMessage("§c[Error] You inputted some JSON incorrectly... ");
			
		}

		
		jsonObject.add(temp);
   	
   	 try {
   		 
   			FileWriter file2 = new FileWriter(file);
   		
   			file2.write(jsonObject.toJSONString());
   		
   			file2.flush();
   		
   			file2.close();
   	 
   		} catch (IOException e) {
   			sender.sendMessage("§c[Error] Something went wrong internally with this command report this to the Staff and Chibill. Error writng to the file!");
   			e.printStackTrace();
   		}
		
   	 	Iterator<? extends Player> Temp = Bukkit.getServer().getOnlinePlayers().iterator();
   	 
   	 	while(Temp.hasNext()){
   	 		
   	 		Player Temp2 = Temp.next();
   	 		
   	 		if(Temp2.hasPermission("FunCommands.fun.aprove")|Temp2.hasPermission("FunCommands.fun.deny")){
   	 			
   	 			Aprove(Temp2, file);
   	 			return true;
   	 			
   	 		}
   	 		
   	 	}
   	 	
		return true;

		
		
    	}
		
	public static boolean ApproveCommand(CommandSender sender,String command,String unaproved,String commands) throws Exception, ParseException{
		FileReader fileReader = null;

	   	   JSONParser parser = new JSONParser();
	   	   
	   	   try {
	   		   
	   		   fileReader = new FileReader(unaproved);
			
	   	   }catch (FileNotFoundException e) {
	   	
	   		   e.printStackTrace();
		
	   	   }
	   	   
	   	
	   	   
	   	 
	   		 JSONArray  jsonObject = (JSONArray) parser.parse(fileReader);
	   	   
	   	
	   	   
	   	   @SuppressWarnings("unchecked")
	   	   Iterator<JSONObject> iterator = jsonObject.iterator();
	   	JSONArray Array = new JSONArray();
	   	   while (iterator.hasNext()) {
	   		
	   		   JSONObject temp = iterator.next();
	   		
	   		   if(temp.get("Command").equals(command)){
	   		   
	   		 CommandstoOutput.put((String)temp.get("Command"),(String) temp.get("Output"));
	   		sender.sendMessage((String)temp.get("Command")+ " was APROVED!");
	   		   CommandstoDiscription.put((String)temp.get("Command"),(String) temp.get("Description"));
	   		   }else{
	   			   Array.add(temp);
	   		   }
	   		   
	   	   }
	   	try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
   		 
			FileWriter file2 = new FileWriter(unaproved);
		
			file2.write(Array.toJSONString());
		
			file2.flush();
		
			file2.close();
	 
		} catch (IOException e) {
		
			e.printStackTrace();
		
		}
	    
	    SaveCommands(commands);
	    unregisterCommands();
	    registerCommands();
	    
				return true;
		
				
				
				
	}
	
	
	public static void unregisterCommands() {	
		Iterator<String> temp = CommandstoDiscription.keySet().iterator();
		
		while(temp.hasNext()){
			String temp2 = temp.next();
			
			Main.register.UnregisterCommand(temp2);
			
		}
		
		
	}
	public static void removeCommand(String string,CommandSender sender) {
		try{
		Main.register.UnregisterCommand(string);
		
		CommandstoDiscription.remove(string);
		
		CommandstoOutput.remove(string);
		sender.sendMessage("§cYou have removed " + string);
		}catch(Exception e){
			sender.sendMessage("§c[ERROR] That command does not exist so it could not be removed!");
		}
	}
	public static void Aprove(Player player,String file) {
		
		
		FileReader fileReader = null;

	   	   JSONParser parser = new JSONParser();
	   	   
	   	   try {
	   		   
	   		   fileReader = new FileReader(file);
			
	   	   }catch (FileNotFoundException e) {
	   	
	   		   e.printStackTrace();
		
	   	   }
	   	   
	   	JSONArray jsonObject = (JSONArray) null;
	   	   
	   	   try {
	   		
	   		   jsonObject = (JSONArray) parser.parse(fileReader);
	   	   
	   	   } catch (Exception e) {
		
			e.printStackTrace();
		
	   	   }
	   	   
	   	   @SuppressWarnings("unchecked")
	   	   Iterator<JSONObject> iterator = jsonObject.iterator();
	   	   
	   	   int  temp2 = 0;
	   	 
	   	   while (iterator.hasNext()) {
	   		
	   		   JSONObject temp = iterator.next();
	   		
	   		  player.sendMessage("Command: "+(String) temp.get("Command"));
	   		  player.sendMessage("Output: "+(String) temp.get("Output"));
	   		  player.sendMessage("Description: "+(String) temp.get("Description"));
	   		   		   temp2++;
	   		   
	   	   }
	   	   if(temp2!=0){
	   	   player.sendMessage("Run command aprove followed by the command to aprove! OR aproveall to aprove all of them!");
	   	   }
	        }
	public static boolean ApproveALL(CommandSender sender, String string,String string2) {
		

		FileReader fileReader = null;

	   	   JSONParser parser = new JSONParser();
	   	   
	   	   try {
	   		   
	   		   fileReader = new FileReader(string);
			
	   	   }catch (FileNotFoundException e) {
	   	
	   		   e.printStackTrace();
		
	   	   }
	   	   
	   	JSONArray jsonObject = (JSONArray) null;
	   	   
	   	   try {
	   		
	   		   jsonObject = (JSONArray) parser.parse(fileReader);
	   	   
	   	   } catch (Exception e) {
		
			e.printStackTrace();
		
	   	   }
	   	   
	   	   @SuppressWarnings("unchecked")
	   	   Iterator<JSONObject> iterator = jsonObject.iterator();
	   	   
	   	   while (iterator.hasNext()) {
	   		
	   		   JSONObject temp = iterator.next();
	   		
	   		 CommandstoOutput.put((String)temp.get("Command"),(String) temp.get("Output"));
	    		sender.sendMessage((String)temp.get("Command")+ " was APROVED!");
	   		   CommandstoDiscription.put((String)temp.get("Command"),(String) temp.get("Description"));
	   	   
	   		   
	   	   }
	   	try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				return true;
		
		
	}
	public static boolean deny(CommandSender sender,String string, String string2) {
	
		FileReader fileReader = null;

	   	   JSONParser parser = new JSONParser();
	   	   
	   	   try {
	   		   
	   		   fileReader = new FileReader(string2);
			
	   	   }catch (FileNotFoundException e) {
	   	
	   		   e.printStackTrace();
		
	   	   }
	   	   
	   	JSONArray jsonObject = (JSONArray) null;
	   	   
	   	   try {
	   		
	   		   jsonObject = (JSONArray) parser.parse(fileReader);
	   	   
	   	   } catch (Exception e) {
		
			e.printStackTrace();
		
	   	   }
	   	   
	   	   @SuppressWarnings("unchecked")
	   	   Iterator<JSONObject> iterator = jsonObject.iterator();
	   	JSONArray Array = new JSONArray();
	   	   while (iterator.hasNext()) {
	   		
	   		   JSONObject temp = iterator.next();
	   		
	   		   if(temp.get("Command").equals(string)){
	   			sender.sendMessage((String)temp.get("Command")+ " was DENIED!");
	   		 }else{
	   			   Array.add(temp);
	   		   }
	   		   
	   	   }
	   	try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
		 
			FileWriter file2 = new FileWriter(string2);
		
			file2.write(Array.toJSONString());
		
			file2.flush();
		
			file2.close();
	 
		} catch (IOException e) {
		
			e.printStackTrace();
		
		}
	    
		
		return true;
		
	}
	public static boolean denyAll(String string, String string2) {
		
		File temp3 = new File(string2);
	   	
	   	temp3.delete();
	   	
	   	try {
			temp3.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
		}
	
    
    }
    

