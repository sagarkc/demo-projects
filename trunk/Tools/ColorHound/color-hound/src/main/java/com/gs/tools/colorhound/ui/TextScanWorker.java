/** ---------------------------------------------------------------------------*
 * Copyright Sabuj Das | sabuj.das@gmail.com all rights reserved.
 * <br/>
 * This document cannot be copied, modified or re-distributed without prior 
 * permission from the author.
 *  ---------------------------------------------------------------------------* 
 * Type     : com.gs.tools.colorhound.ui.TextScanWorker
 * Date     : May 30, 2013
 */

package com.gs.tools.colorhound.ui;

import com.gs.tools.colorhound.ColorPalette;
import com.gs.tools.colorhound.WorkerTaskConstants;
import static com.gs.tools.colorhound.WorkerTaskConstants.TASK_STATUS_START;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.SwingWorker;

/**
 *
 * @author Sabuj Das | sabuj.das@gmail.com
 */
public class TextScanWorker extends SwingWorker<Void, Void>
    implements WorkerTaskConstants{

    private final String sourceText;
    private final String paletteName;
    private final ColorPalette colorPalette = new ColorPalette();
        
    public TextScanWorker(String sourceText, String paletteName) {
        this.sourceText = sourceText;
        this.paletteName = paletteName;
        colorPalette.setName(paletteName);
    }

    @Override
    protected Void doInBackground() throws Exception {
        firePropertyChange(TASK_STATUS_START, null, null);
		
		try {
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6,6}",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher match = pattern.matcher(sourceText);
            while(match.find()){
                colorPalette.add(sourceText.substring(
                        match.start(), match.end()));
                
            }
            pattern = Pattern.compile("color:rgb\\([0-9]{1,3},[0-9]{1,3},[0-9]{1,3}\\);",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            match = pattern.matcher(sourceText);
            while(match.find()){
                int g = match.groupCount();
                match.group(1);
            }
		} catch (Exception e) {
			firePropertyChange(TASK_STATUS_FAILED, null, e.getMessage());
			return null;
		}
		firePropertyChange(TASK_STATUS_DONE, null, colorPalette);
		return null;
	}
	
	public void stop() {
		cancel(true);
		firePropertyChange(TASK_STATUS_ABORT, null, colorPalette);
	}

    
    public static void main(String[] a){
        String s = "hello color:#3423aa; hello:qwewre; #AA3456 ldfkjdlkfjg";
        List<String> ss = new ArrayList<String>();
        try {
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6,6}",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
            Matcher match = pattern.matcher(s);
            while(match.find()){
                ss.add(s.substring(
                        match.start()+1, match.end()));
                
            }
        } catch (Exception e) {
        }
        System.out.println(ss.size());
    }
    
}
