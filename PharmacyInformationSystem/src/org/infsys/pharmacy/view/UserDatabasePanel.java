package org.infsys.pharmacy.view;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.infsys.pharmacy.ApplicationSingleton;
import org.infsys.pharmacy.controller.DeleteUserAction;
import org.infsys.pharmacy.controller.OpenAddUserDialogAction;
import org.infsys.pharmacy.model.User;
import org.infsys.pharmacy.model.UserType;
import org.infsys.pharmacy.table.model.Field;
import org.infsys.pharmacy.table.model.Row;
import org.infsys.pharmacy.table.view.ScrollableTable;
import org.infsys.pharmacy.util.Constants;

import net.miginfocom.swing.MigLayout;

public class UserDatabasePanel extends JPanel {
	
	private static final long serialVersionUID = -1259325517615871958L;
	private JButton addNewUserButton;
	private JButton deleteUserButton;
	private JComboBox<String> sortTypes;
	private JLabel title;
	private ScrollableTable scrollableTable;

	/**
	 * Create the panel for displaying all users in system with option to add new user.
	 */
	public UserDatabasePanel() {
		setLayout(new MigLayout("", "[grow][grow]", "[][][grow]"));
		
		title = new JLabel(Constants.USER_DATABASE);
		title.setFont(Constants.CUSTOM_FONT_BOLD.deriveFont(24f));
		title.setForeground(new Color(0.0f, 0.0f, 0.0f, 0.7f));
		add(title, "cell 0 0 2 1");
		
		sortTypes = new JComboBox<>(Constants.USER_SORT_TYPES);
		sortTypes.setForeground(Constants.LIGHT_BLUE);
		add(sortTypes, "cell 0 1,alignx left, gapy 20 20");
		
		addNewUserButton = new JButton(new OpenAddUserDialogAction(Constants.ADD_NEW_USER));
		addNewUserButton.setBackground(Constants.LIGHT_BLUE);
		addNewUserButton.setForeground(Color.WHITE);
		addNewUserButton.setFont(Constants.CUSTOM_FONT_BOLD);
		addNewUserButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(addNewUserButton, "cell 1 1,alignx right, gapy 20 20");

		scrollableTable = new ScrollableTable(this.extractRowsFromUsersList(Constants.USER_SORT_TYPES[0]));
		
		deleteUserButton = new JButton(new DeleteUserAction(Constants.DELETE_USER, this.scrollableTable));
		deleteUserButton.setBackground(Constants.LIGHT_BLUE);
		deleteUserButton.setForeground(Color.WHITE);
		deleteUserButton.setFont(Constants.CUSTOM_FONT_BOLD);
		deleteUserButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		add(deleteUserButton, "cell 1 1,alignx right, gapy 20 20, gapx 20 0");

		add(scrollableTable, "cell 0 2 2 1,grow");
		
		sortTypes.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          String selectedSortType = (String) e.getItem();
			          scrollableTable.updateModel(extractRowsFromUsersList(selectedSortType));
		       }
			}
		});
	}
	
	public void addRow(User user) {
		this.scrollableTable.addRow(this.convertUserToRow(user));
	}
	
	public void updateRows() {
		this.scrollableTable.updateModel(extractRowsFromUsersList((String) sortTypes.getSelectedItem()));
	}
	
	public List<Row> extractRowsFromUsersList(String sortBy) {
		List<Row> rows = new ArrayList<>();
		List<User> users = new ArrayList<User>(ApplicationSingleton.getInstance().getUsers().values());
		
		users.sort(new Comparator<User>() {

			@Override
			public int compare(User o1, User o2) {
				switch(sortBy) {
					case Constants.NAME:
						return o1.getFirstName().compareTo(o2.getFirstName());
					case Constants.LASTNAME:
						return o1.getLastName().compareTo(o2.getLastName());
					default:
						return o1.getType().compareTo(o2.getType());
				}
			}
		});
		
		for (User user : users) {
			if (user.getType() != UserType.ADMINISTRATOR) {
				rows.add(this.convertUserToRow(user));
			}
		}
		
		return rows;
	}
	
	public Row convertUserToRow(User user) {
		Field nameField = new Field(Constants.NAME, user.getFirstName());
		Field lastNameField = new Field(Constants.LASTNAME, user.getLastName());
		Field usernameField = new Field(Constants.USERNAME, user.getUsername());
		Field userTypeField = new Field(Constants.USER_TYPE, user.getType());
		Field deletedField = new Field(Constants.DELETED, user.isLogicallyDeleted());
		
		Row row = new Row();
		row.getFields().add(nameField);
		row.getFields().add(lastNameField);
		row.getFields().add(usernameField);
		row.getFields().add(userTypeField);
		row.getFields().add(deletedField);		
		
		return row;
	}
}