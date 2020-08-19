package org.infsys.pharmacy;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.infsys.pharmacy.util.Constants;
import org.infsys.pharmacy.util.DataLoader;
import org.infsys.pharmacy.view.LoginFrame;

import com.formdev.flatlaf.IntelliJTheme;

/**
 * Contains main method which is entry point of the application.
 */
public class Application {

	/**
	 * Launch application. Show login dialog on application launch.
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				//Apply JetBrain's Arc Light theme 
				IntelliJTheme.install(Application.class.getResourceAsStream("themes/arc-theme.theme.json"));
				
				//Try to create 2 custom fonts from ttf/otf files
				try {
					Constants.setCustomFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/SF-UI-Display-Regular.ttf")).deriveFont(18f));
					Constants.setCustomFontBold(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/SF-UI-Display-Bold.otf")).deriveFont(12f));
				} catch (IOException | FontFormatException e) {
				    JOptionPane.showMessageDialog(null, Constants.RESOURCES_ERROR_MSG, Constants.ERROR_MSG_TITLE, JOptionPane.ERROR_MESSAGE);
				    return;
				}
				
				//If fonts are successfully created, register them in graphics env to be able to use them through application
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Constants.CUSTOM_FONT);
				GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(Constants.CUSTOM_FONT_BOLD);
			    setUIFont(Constants.CUSTOM_FONT);				  
			    
			    //Load data
			    DataLoader.loadData();
			    
			    //Create and display login frame
			    LoginFrame loginFrame = new LoginFrame();
			    ApplicationSingleton.getInstance().setLoginFrame(loginFrame);
			    loginFrame.setVisible(true);
			}
		});
	}
	
	/**
	 * Iterate through all UIManager keys and for each key type of Font, set font given as method parameter.
	 * @param font
	 */
	public static void setUIFont (Font font){
	    Enumeration<Object> keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	    	Object key = keys.nextElement();
	    	Object value = UIManager.get(key);
	    	if (value != null && value instanceof Font) {
	    		UIManager.put(key, font);	    		
	    	}
      	}
	}
}