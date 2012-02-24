/* ******************************************************************************
 * 	
 * 	Name	: ProcessTest.java
 * 	Type	: com.gs.jprompt.app.ProcessTest
 * 
 * 	Created	: Feb 17, 2012
 * 	
 * 	Author	: Sabuj Das [ mailto::sabuj.das@gmail.com ]
 * 
 * -----------------------------------------------------------------------------*
 * 																				*
 * Copyright © Sabuj Das 2010 All Rights Reserved. 								*
 * <br/>No part of this document may be reproduced without written 				*
 * consent from the author.														*
 * 																				*
 ****************************************************************************** */

package com.gs.jprompt.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 * @author sabuj.das
 * @MailTo sabuj.das@gmail.com
 * 
 */
public class ProcessTest {

	public static void main1(String[] args) throws Exception {
		Runtime rt = Runtime.getRuntime();
		Process proc = rt.exec("cmd");
		// output both stdout and stderr data from proc to stdout of this
		// process
		StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream());
		StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream());
		errorGobbler.start();
		outputGobbler.start();
		proc.waitFor();
	}

	public static void main(String[] args) throws Exception {
		String line;
		Scanner scan = new Scanner(System.in);

		ProcessBuilder builder = new ProcessBuilder("cmd.exe");
		builder.redirectErrorStream(true);
		Process process = builder.start();
		
		OutputStream stdin = process.getOutputStream();
		InputStream stderr = process.getErrorStream();
		InputStream stdout = process.getInputStream();

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stdout));
		BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(stdin));

		String input = scan.nextLine();
		input += "\n";
		writer.write(input);
		writer.flush();

		input = scan.nextLine();
		input += "\n";
		writer.write(input);
		writer.flush();

		while ((line = reader.readLine()) != null) {
			System.out.println("Stdout: " + line);
		}

		input = scan.nextLine();
		input += "\n";
		writer.write(input);
		writer.close();

		while ((line = reader.readLine()) != null) {
			System.out.println("Stdout: " + line);
		}
	}

}

class StreamGobbler extends Thread {
	InputStream is;

	// reads everything from is until empty.
	StreamGobbler(InputStream is) {
		this.is = is;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				System.out.println(line);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
