package com.gs.jprompt.core;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CommandExecutioner {

	private File workingDirectory;
	private String[] command = new String[3];
	

	@Deprecated
	public CommandExecutioner(String directory) {
		
	}


	public CommandExecutioner(String commandLine, File workingDirectory) {
		this.workingDirectory = workingDirectory;
		String osName = System.getProperty("os.name");
		if (osName.equals("Windows NT") || osName.equals("Windows XP")) {
			command[0] = "cmd.exe";
		} else {
			command[0] = "command.com";
		}
		command[1] = "/C";
		command[2] = commandLine;
	}


	public String[] getCommand() {
		return command;
	}


	public void setCommand(String[] command) {
		this.command = command;
	}

	
	@SuppressWarnings("unused")
	public final Process createProcess() throws IOException{
		ProcessBuilder builder = new ProcessBuilder(getCommand());
		Map<String, String> environ = builder.environment();
		builder.directory(workingDirectory);
		return builder.start();
	}
	
	
	
}
