package com.gs.jprompt.common;

public class Command {

	public static final String CMD_TYPE_CD = "CMD_TYPE_CD";
	public static final String CMD_TYPE_CHANGE_DRIVE = "CMD_TYPE_CHANGE_DRIVE";
	public static final String CMD_TYPE_SHELL = "CMD_TYPE_SHELL";
	
	private String commandLine;
	private String commandType;
	private String workingDir;
	
	@Deprecated
	public Command() {
		// TODO Auto-generated constructor stub
	}
	
	public Command(String cmd) {
		this.commandLine = cmd;
		prepareCommand();
	}
	
	private void prepareCommand(){
		commandType = CMD_TYPE_SHELL;
		if(commandLine.endsWith(":")){
			commandType = CMD_TYPE_CHANGE_DRIVE;
			commandLine = commandLine+"\\";
			workingDir = commandLine;
		} else {
			String[] s = commandLine.split(" ");
			if(s.length >= 1){
				if("cd".equalsIgnoreCase(s[0])){
					commandType = CMD_TYPE_CD;
					if(s.length == 1){
						workingDir = "";
					} else {
						workingDir = s[1];
					}
				} else {
					
				}
			}
		}
	}

	public String getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}

	public String getCommandType() {
		return commandType;
	}

	public void setCommandType(String commandType) {
		this.commandType = commandType;
	}

	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}
	
	
}
