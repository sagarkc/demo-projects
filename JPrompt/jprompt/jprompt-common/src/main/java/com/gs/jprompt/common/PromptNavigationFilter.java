package com.gs.jprompt.common;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.text.JTextComponent;
import javax.swing.text.NavigationFilter;
import javax.swing.text.Position;


public class PromptNavigationFilter extends NavigationFilter {

	private int prefixLength;
	private Action deletePrevious;
	
	public PromptNavigationFilter(int prefixLength, JTextComponent component)
	{
		this.prefixLength = prefixLength;
		deletePrevious = component.getActionMap().get("delete-previous");
		component.getActionMap().put("delete-previous", new BackspaceAction());
		component.setCaretPosition(prefixLength);
	}

	public void setDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
	{
		fb.setDot(Math.max(dot, prefixLength), bias);
	}

	public void moveDot(NavigationFilter.FilterBypass fb, int dot, Position.Bias bias)
	{
		fb.moveDot(Math.max(dot, prefixLength), bias);
	}

	class BackspaceAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent e)
		{
			JTextComponent component = (JTextComponent)e.getSource();

			if (component.getCaretPosition() > prefixLength)
			{
				deletePrevious.actionPerformed( null );
			}
		}
	}
}
