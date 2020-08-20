package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.view.MainFrame;

public class NavigationButtonSelectedAction extends AbstractAction {

	private static final long serialVersionUID = -5824802258410296980L;

	public NavigationButtonSelectedAction(String name) {
		putValue(NAME, name);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String name = (String) this.getValue(NAME);
		MainFrame mainFrame = ApplicationSingleton.getInstance().getMainFrame();
		mainFrame.getSidebar().selectButton(name);
		mainFrame.showCard(name);
	}
}