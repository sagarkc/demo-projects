package com.gs.jprompt.app.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.gs.jprompt.app.components.ConsoleTextArea;
import com.gs.jprompt.common.Command;
import com.gs.jprompt.core.CommandExecutioner;

public class CommandRunner<T , C extends Command> extends SwingWorker<T, C> {

	public static final String TASK_STATUS_DONE = "TASK_STATUS_DONE";
	public static final String TASK_STATUS_START = "TASK_STATUS_START";
	public static final String TASK_STATUS_ABORT = "TASK_STATUS_ABORT";
	public static final String TASK_STATUS_FAILED = "TASK_STATUS_FAILED";
	
	public static final String PROPERTY_PROGRESS = "PROPERTY_PROGRESS";
	public static final String PROPERTY_MESSAGE = "PROPERTY_MESSAGE";
	
	private static final Logger logger = Logger.getLogger(CommandRunner.class);
	
	private Command command;
	private ConsoleTextArea consoleTextArea;
	private CommandExecutioner commandExecutioner;
	
	
	public CommandRunner(Command command) {
		this.command = command;
	}
	

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	public ConsoleTextArea getConsoleTextArea() {
		return consoleTextArea;
	}

	public void setConsoleTextArea(ConsoleTextArea consoleTextArea) {
		this.consoleTextArea = consoleTextArea;
	}

	public CommandExecutioner getCommandExecutioner() {
		return commandExecutioner;
	}

	public void setCommandExecutioner(CommandExecutioner commandExecutioner) {
		this.commandExecutioner = commandExecutioner;
	}




	@Override
	protected T doInBackground() throws Exception {
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		if(null != command){
			if(Command.CMD_TYPE_CHANGE_DRIVE.equals(command.getCommandType())){
				String currentWorkDirName = command.getWorkingDir();
			
				File dir = new File(currentWorkDirName);
				if(dir.exists()){
					consoleTextArea.setWorkingDirectory(currentWorkDirName);
					CommandExecutioner executioner = new CommandExecutioner(command.getCommandLine(), new File(consoleTextArea.getWorkingDirectory()));
					try {
						final Process process = executioner.createProcess();
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line = "";
						consoleTextArea.append("\n");
						while ((line  = br.readLine()) != null) {
							consoleTextArea.append(line + "\n");
						}
						
						/*InputStream es = process.getErrorStream();
						InputStreamReader ier = new InputStreamReader(es);
						BufferedReader er = new BufferedReader(ier);
						line = "";
						consoleTextArea.append("\n");
						while ((line  = er.readLine()) != null) {
							consoleTextArea.append(line + "\n");
						}*/
						//consoleTextArea.updateUI();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(Command.CMD_TYPE_CD.equals(command.getCommandType())){
				String currentWorkDirName = consoleTextArea.getWorkingDirectory()
					+ File.separator + command.getWorkingDir();
				
				File dir = new File(currentWorkDirName);
				if(dir.exists()){
					consoleTextArea.setWorkingDirectory(currentWorkDirName);
					CommandExecutioner executioner = new CommandExecutioner(command.getCommandLine(), new File(consoleTextArea.getWorkingDirectory()));
					try {
						final Process process = executioner.createProcess();
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line = "";
						consoleTextArea.append("\n");
						while ((line  = br.readLine()) != null) {
							consoleTextArea.append(line + "\n");
						}
						
						/*InputStream es = process.getErrorStream();
						InputStreamReader ier = new InputStreamReader(es);
						BufferedReader er = new BufferedReader(ier);
						line = "";
						consoleTextArea.append("\n");
						while ((line  = er.readLine()) != null) {
							consoleTextArea.append(line + "\n");
						}*/
						//consoleTextArea.updateUI();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if(Command.CMD_TYPE_SHELL.equals(command.getCommandType())){
				CommandExecutioner executioner = new CommandExecutioner(command.getCommandLine(), new File(consoleTextArea.getWorkingDirectory()));
				try {
					final Process process = executioner.createProcess();
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					consoleTextArea.append("\n");
					while ((line  = br.readLine()) != null) {
						consoleTextArea.append(line + "\n");
					}
					
					InputStream es = process.getErrorStream();
					InputStreamReader ier = new InputStreamReader(es);
					BufferedReader er = new BufferedReader(ier);
					line = "";
					consoleTextArea.append("\n");
					while ((line  = er.readLine()) != null) {
						consoleTextArea.append(line + "\n");
					}
					//consoleTextArea.updateUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			
		}
		
		
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				consoleTextArea.append("\n" + consoleTextArea.getPrompt() + consoleTextArea.getPromptCharacter());
		    	//consoleTextArea.setEditable(true);
		    	consoleTextArea.updateEndPromptOffset();
			}
		});

		firePropertyChange(TASK_STATUS_DONE, totalTime, null);
		
		return null;
	}


	@Override
	protected void done() {
		firePropertyChange(TASK_STATUS_DONE, null, null);
	}


	@Override
	protected void process(List<C> chunks) {
		// TODO Auto-generated method stub
		super.process(chunks);
	}
	
	

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	}
	
	public void stop() {
		cancel(true);
		firePropertyChange(TASK_STATUS_ABORT, null, null);
	}

}
