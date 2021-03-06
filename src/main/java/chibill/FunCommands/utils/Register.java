package chibill.FunCommands.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.command.SimpleCommandMap;

public class Register {
	private Plugin plugin;
	 
	public void setPlugin(Plugin plug){
		plugin = plug;
	}
	
	public void registerCommand(String commands,String Description,String permission) {
		PluginCommand command = getCommand(commands, plugin);
		command.setExecutor(plugin);
		command.setPermission(permission);
		command.setDescription(Description);
		getCommandMap().register(plugin.getDescription().getName(), command);
	}
	public void UnregisterCommand(String Name) {
		PluginCommand command = getCommand(Name, plugin);
		command.unregister(getCommandMap());
	}
	 
	private static PluginCommand getCommand(String name, Plugin plugin) {
		PluginCommand command = null;
	 
		try {
			Constructor<PluginCommand> c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
			c.setAccessible(true);
	 
			command = c.newInstance(name, plugin);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
	 
		return command;
	}
	
	public void removeCommand(String name) {
		try{
		CommandMap commandMap = getCommandMap();
	 	Field f = SimpleCommandMap.class.getDeclaredField("knownCommands");
		f.setAccessible(true);
		
		Map<String, Command> knownCommands = (Map<String, Command>)f.get(commandMap);
		knownCommands.remove(name);
		
		f.setAccessible(false);
	}catch(Exception e){
		e.printStackTrace();
	}}
	 
	private static CommandMap getCommandMap() {
		CommandMap commandMap = null;
	 
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);
				
				commandMap = (CommandMap) f.get(Bukkit.getPluginManager())	;}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	 
		return commandMap;
	}
}
