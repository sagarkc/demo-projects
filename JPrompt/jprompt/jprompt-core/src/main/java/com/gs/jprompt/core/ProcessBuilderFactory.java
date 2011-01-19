package com.gs.jprompt.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Map;

public final class ProcessBuilderFactory {

	
	private static ProcessBuilderFactory factory;
	
	private ProcessBuilderFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public static Process buildProcess(){
		return null;
	}
	
	public static Process buildProcess(String exeName){
		return buildProcess(new File(exeName));
	}
	
	public static Process buildProcess(File exeFile){
		return null;
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
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		System.out.println("Program terminated!");

	}

}
