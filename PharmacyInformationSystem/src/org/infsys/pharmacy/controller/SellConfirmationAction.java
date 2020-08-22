package org.infsys.pharmacy.controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.JOptionPane;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.model.Bill;
import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.FileUtil;
import org.infsys.pharmacy.view.CartPanel;

public class SellConfirmationAction extends AbstractAction {

	private static final long serialVersionUID = 6072981543029641887L;
	private CartPanel cartPanel ;

	public SellConfirmationAction(String name, CartPanel cartPanel) {
		putValue(NAME, name);
		this.cartPanel = cartPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Map<String, Integer> medicinesWithAmounts = cartPanel.getMedicinesWithAmounts();
		
		if (medicinesWithAmounts.isEmpty()) {
			JOptionPane.showMessageDialog(null, Constants.EMPTY_CART, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Bill bill = new Bill(medicinesWithAmounts, cartPanel.getTotalPrice());
		ApplicationSingleton.getInstance().getBills().add(bill);
		FileUtil.saveObjectInFile(ApplicationSingleton.getInstance().getBills(), new File(Constants.BILLS_PATH));
		cartPanel.resetCart();
	}
}