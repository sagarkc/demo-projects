package com.gs.jprompt.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import com.gs.jprompt.common.SystemInfo;

public final class ProcessBuilderFactory {

	
	private static ProcessBuilderFactory factory;
	
	private ProcessBuilderFactory() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public static ProcessBuilderFactory getFactory() {
		synchronized (ProcessBuilderFactory.class) {
			if(null == factory)
				factory = new ProcessBuilderFactory();
		}
		return factory;
	}



	public final ProcessBuilder getProcessBuilder(String commandLine){
		if(SystemInfo.OS_TYPE_WIN.equals(SystemInfo.OS_TYPE)){
			return getCommandPromptBuilder(commandLine);
		} else if(SystemInfo.OS_TYPE_UNIX.equals(SystemInfo.OS_TYPE)){
			return getBashPromptBuilder(commandLine);
		} else if(SystemInfo.OS_TYPE_MAC.equals(SystemInfo.OS_TYPE)){
			return getMacPromptBuilder(commandLine);
		} if(SystemInfo.OS_TYPE_SOLARIS.equals(SystemInfo.OS_TYPE)){
			return getSolarisPromptBuilder(commandLine);
		} 
		
		return getCommandPromptBuilder(commandLine);
	}
	
	/**
	 * @param commandLine
	 * @return
	 */
	private final ProcessBuilder getSolarisPromptBuilder(String commandLine) {
		String[] command = new String[3];
		command[0] = "/bin/bash";
		command[1] = "-c";
		command[2] = commandLine;
		final ProcessBuilder processBuilder = new ProcessBuilder(command);
		return processBuilder;
	}



	/**
	 * @param commandLine
	 * @return
	 */
	private final ProcessBuilder getMacPromptBuilder(String commandLine) {
		String[] command = new String[3];
		command[0] = "/bin/bash";
		command[1] = "-c";
		command[2] = commandLine;
		final ProcessBuilder processBuilder = new ProcessBuilder(command);
		return processBuilder;
	}



	/**
	 * @param commandLine
	 * @return
	 */
	private final ProcessBuilder getBashPromptBuilder(String commandLine) {
		String[] command = new String[3];
		command[0] = "/bin/bash";
		command[1] = "-c";
		command[2] = commandLine;
		final ProcessBuilder processBuilder = new ProcessBuilder(command);
		return processBuilder;
	}



	private final ProcessBuilder getCommandPromptBuilder(String commandLine){
		String[] command = new String[3];
		command[0] = "cmd.exe";
		command[1] = "/C";
		command[2] = commandLine;
		final ProcessBuilder processBuilder = new ProcessBuilder(command);
		return processBuilder;
	}
	
	
	
	public static void main(String args[]) throws InterruptedException,
			IOException {
		String line;
		OutputStream stdin = null;
		InputStream stderr = null;
		InputStream stdout = null;

		String osName = System.getProperty("os.name");
		System.out.println(osName);
		String[] cmd = new String[3];
		System.out.println("" + System.currentTimeMillis());
		if (osName.equals("Windows NT") || osName.equals("Windows XP")) {
			cmd[0] = "cmd.exe";
		} else {
			cmd[0] = "command.com";
		}
		cmd[1] = "/C";
		cmd[2] = "dir";

		ProcessBuilder builder = new ProcessBuilder(cmd);

		Map<String, String> environ = builder.environment();
		builder.directory(new File("D:\\"));

		System.out.println("Directory : " + System.getenv("temp"));
		
		final Process process = builder.start();
		System.out.println("" + System.currentTimeMillis());
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("Program terminated!");

	}

}
