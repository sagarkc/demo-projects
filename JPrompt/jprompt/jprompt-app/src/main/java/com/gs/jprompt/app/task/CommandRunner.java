package com.gs.jprompt.app.task;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import org.apache.log4j.Logger;

import com.gs.jprompt.app.components.ConsoleTextArea;
import com.gs.jprompt.common.Command;
import com.gs.jprompt.common.EnvironmentManager;
import com.gs.jprompt.core.CommandExecutioner;
import com.gs.jprompt.core.ProcessBuilderFactory;
import com.gs.utils.text.StringUtil;

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
	private final JInternalFrame consoleInternalFrame;
	private final EnvironmentManager environmentManager;
	private Process currentProcess;
	
	public CommandRunner(JInternalFrame consoleInternalFrame, Command command, EnvironmentManager manager) {
		this.command = command;
		this.consoleInternalFrame = consoleInternalFrame;
		this.environmentManager = manager;
	}

	public EnvironmentManager getEnvironmentManager() {
		return environmentManager;
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




	@Override
	protected T doInBackground() throws Exception {
		Long startTime = System.currentTimeMillis();
		Long totalTime = 0L;
		firePropertyChange(PROPERTY_PROGRESS, null, TASK_STATUS_START);
		if(null != command){
			ProcessBuilder processBuilder = ProcessBuilderFactory.getFactory().getProcessBuilder(command.getCommandLine());
			Map<String, String> builderEnvMap = processBuilder.environment();
			builderEnvMap.putAll(environmentManager.getUserEnvironment().getAll());
			String currentWorkDirName = command.getWorkingDir();
			
			if(Command.CMD_TYPE_CHANGE_DRIVE.equals(command.getCommandType())){
				File dir = new File(currentWorkDirName);
				if(dir.exists()){
					processBuilder.directory(new File(currentWorkDirName));
					consoleTextArea.setWorkingDirectory(currentWorkDirName);
					try {
						final Process process = processBuilder.start();
						currentProcess = process;
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line = "";
						consoleTextArea.append("\n");
						while ((line  = br.readLine()) != null) {
							consoleTextArea.append(line + "\n");
							consoleTextArea.rePositionCaret();
						}
						
						
					} catch (IOException e) {
						logger.error(e);
						e.printStackTrace();
					}
				}
			}
			else if(Command.CMD_TYPE_CD.equals(command.getCommandType())){
				currentWorkDirName = consoleTextArea.getWorkingDirectory()
					+ File.separator + command.getWorkingDir();
				
				File dir = new File(currentWorkDirName);
				if(dir.exists()){
					consoleTextArea.setWorkingDirectory(currentWorkDirName);
					processBuilder.directory(new File(currentWorkDirName));
					try {
						final Process process = processBuilder.start();
						currentProcess = process;
						InputStream is = process.getInputStream();
						InputStreamReader isr = new InputStreamReader(is);
						BufferedReader br = new BufferedReader(isr);
						String line = "";
						consoleTextArea.append("\n");
						while ((line  = br.readLine()) != null) {
							consoleTextArea.append(line + "\n");
							consoleTextArea.rePositionCaret();
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
				processBuilder.directory(new File(consoleTextArea.getWorkingDirectory()));
				
				try {
					final Process process = processBuilder.start();
					currentProcess = process;
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					consoleTextArea.append("\n");
					while ((line  = br.readLine()) != null) {
						consoleTextArea.append(line + "\n");
						consoleTextArea.rePositionCaret();
					}
					
					InputStream es = process.getErrorStream();
					InputStreamReader ier = new InputStreamReader(es);
					BufferedReader er = new BufferedReader(ier);
					line = "";
					consoleTextArea.append("\n");
					while ((line  = er.readLine()) != null) {
						consoleTextArea.append(line + "\n");
						consoleTextArea.rePositionCaret();
					}
					//consoleTextArea.updateUI();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(Command.CMD_TYPE_SET.equals(command.getCommandType())){
				processBuilder.directory(new File(consoleTextArea.getWorkingDirectory()));
				
				try {
					final Process process = processBuilder.start();
					currentProcess = process;
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					consoleTextArea.append("\n");
					while ((line  = br.readLine()) != null) {
						consoleTextArea.append(line + "\n");
						consoleTextArea.rePositionCaret();
					}
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				environmentManager.getUserEnvironment().set(command.getCommandLine());
			}else if(Command.CMD_TYPE_TITLE.equals(command.getCommandType())){
				if(StringUtil.hasValidContent(command.getCommandLine())){
					consoleInternalFrame.setTitle(command.getCommandLine());
				}
			}else if(Command.CMD_TYPE_PROMPT.equals(command.getCommandType())){
				processBuilder.directory(new File(consoleTextArea.getWorkingDirectory()));
				boolean hasError = false;
				try {
					final Process process = processBuilder.start();
					currentProcess = process;
					InputStream is = process.getInputStream();
					InputStreamReader isr = new InputStreamReader(is);
					BufferedReader br = new BufferedReader(isr);
					String line = "";
					consoleTextArea.append("\n");
					while ((line  = br.readLine()) != null) {
						if(StringUtil.hasValidContent(line)){
							consoleTextArea.append(line + "\n");
						}
					}
					
					InputStream es = process.getErrorStream();
					InputStreamReader ier = new InputStreamReader(es);
					BufferedReader er = new BufferedReader(ier);
					line = "";
					consoleTextArea.append("\n");
					while ((line  = er.readLine()) != null) {
						if(StringUtil.hasValidContent(line)){
							consoleTextArea.append(line + "\n");
							consoleTextArea.rePositionCaret();
						}
						hasError = true;
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(!hasError){
					
					if(command.getCommandLine().indexOf(' ')+1 <= command.getCommandLine().length()
							&& command.getCommandLine().indexOf(' ') >= 0){
						String prompt = command.getCommandLine().substring(command.getCommandLine().indexOf(' ')+1);
						if(StringUtil.hasValidContent(prompt)){
							consoleTextArea.setPrompt(prompt);
						} else {
							consoleTextArea.setPrompt(null);
							//consoleTextArea.setWorkingDirectory(consoleTextArea.getWorkingDirectory());
						}
					} else if(command.getCommandLine().indexOf(' ') < 0){
						consoleTextArea.setPrompt(null);
					}
					
				}
			}
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
		if(null != currentProcess){
			currentProcess.destroy();
			//Runtime.getRuntime().
		}
		cancel(true);
		firePropertyChange(TASK_STATUS_ABORT, null, null);
	}

}
