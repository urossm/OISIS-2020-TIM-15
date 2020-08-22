package org.infsys.pharmacy.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.ChooseMedicationCodeAction;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class ChooseMedicationCodeDialog extends JDialog {

	private static final long serialVersionUID = -6710080185061926115L;
	private final JPanel contentPanel = new JPanel();
	private JLabel chooseCodeLabel;
	private JComboBox<String> codesComboBox;
	private JPanel buttonPane;
	private JButton okButton;

	public ChooseMedicationCodeDialog() {
		super(ApplicationSingleton.getInstance().getMainFrame(), true);
		setTitle(Constants.EDIT_MEDICATION_CODE);
		setSize(Constants.SCREEN_WIDTH / 6, 2 * Constants.SCREEN_HEIGHT / 3);
		setMinimumSize(new Dimension(Constants.SCREEN_WIDTH / 6, 0));
		setLocationRelativeTo(ApplicationSingleton.getInstance().getMainFrame());
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][]"));

		chooseCodeLabel = new JLabel(Constants.CHOOSE_CODE);
		chooseCodeLabel.setFont(chooseCodeLabel.getFont().deriveFont(16f));
		contentPanel.add(chooseCodeLabel, "cell 0 1,alignx center, gapy 20 0");
		
		codesComboBox = new JComboBox<>(ApplicationSingleton.getInstance().getMedications().keySet().toArray(new String[]{}));
		codesComboBox.setFont(codesComboBox.getFont().deriveFont(16f));
		contentPanel.add(codesComboBox, "cell 0 2,alignx center, gapy 10 20");
		
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton(new ChooseMedicationCodeAction(Constants.OK, this));
		okButton.setBackground(Constants.LIGHT_BLUE);
		okButton.setForeground(Color.WHITE);
		okButton.setFont(Constants.CUSTOM_FONT_BOLD);
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		pack();
	}

	public String getChosenCode() {
		return (String) codesComboBox.getSelectedItem();
	}
}