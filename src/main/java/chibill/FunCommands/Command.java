package chibill.FunCommands;

public class Command {

	private String Command = "";
	
	private String Permission = "";
	
	private String Description = "";
	
	private String Fuction = "";

	public Command(String command,String permission,String description,String fuction){
		this.Command = command;
		this.Permission = permission;
		this.Description = description;
		this.Fuction = fuction;
	}
	
	public String getCommand()  {
	    return Command;
	}

	public String getPermission() {
		return Permission;
	}

	public String getDescription() {
		return Description;
	}


	public String getFuction() {
		return Fuction;
	}

	
}
