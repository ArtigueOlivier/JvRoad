/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

import application.JrApplicationOption;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPropertyColor extends JrProperty {
	
	private JColorChooser colorChooser = null;
	private JDialog dialog = null;
	private JButton button;
	private Color currentColor = new Color(192,0,0);
	protected static final String EDIT = "edit";
	
	public JrPropertyColor(String n,String l,Color c,boolean ro) {
		super(n,l,c,ro);
        currentColor = c;
    	button = new JrButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setBackground(currentColor);
        if (colorChooser == null) {
        	colorChooser = new JColorChooser();
        	colorChooser.setPreviewPanel(new JPanel());
        }
        if (dialog == null) {
	        dialog = JColorChooser.createDialog(button,
	        									JrApplicationOption.GetWord("TxtCouleurSelection","Sélection d'une couleur"),
	                                        true,  //modal
	                                        colorChooser,
	                                        this,  //OK button handler
	                                        null); //no CANCEL button handler
        }
	}
	
	public Component createComponent(boolean renderer) {
    	button = new JrButton();
        button.setActionCommand(EDIT);
        button.addActionListener(this);
        button.setBorderPainted(false);
        button.setBackground(currentColor);
		return button;
	}
	
	public Object getValueFromEditor(Object editor) {
		return currentColor;
	}
	
	public Color getCurrentColor() {
		return currentColor;
	}
	
	public void actionPerformed(ActionEvent e) {
	      if (EDIT.equals(e.getActionCommand())) {
            //The user has clicked the cell, so
            //bring up the dialog.
            button.setBackground(currentColor);
        	colorChooser = new JColorChooser();
        	colorChooser.setPreviewPanel(new JPanel());
	        dialog = JColorChooser.createDialog(button,
	        		JrApplicationOption.GetWord("TxtCouleurSelection","Sélection d'une couleur"),
                    true,  //modal
                    colorChooser,
                    this,  //OK button handler
                    null); //no CANCEL button handler
            dialog.setVisible(true); 
            dialog = null;
            colorChooser = null;
            //fireEditingStopped(); 
        } else { //User pressed dialog's "OK" button.
        	currentColor = colorChooser.getColor();
            button.setBackground(currentColor);
            setValue(currentColor);
			fireValueChange();
        }
	}
}
