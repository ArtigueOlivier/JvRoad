/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs;

import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import application.dialogs.gadgets.JrFormattedTextfield;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrLicenceDialog extends JrDialog  {
	private final int FIELD_COUNT = 6;
	private final int FIELD_LENGHT = 6;
	private JrFormattedTextfield fields[] = new JrFormattedTextfield [FIELD_COUNT];
	private String values[] = new String [FIELD_COUNT];
	
	public JrLicenceDialog(JFrame frame) {
		super(frame,GetWord("TxtLicence"));
	}
	
	public JPanel buildPanel() {
		int i;
		
		for(i = 0; i < FIELD_COUNT; i++) {
			try {
				fields[i] = new JrFormattedTextfield(new MaskFormatter("UUUUUU"),6); //JrTextfield(FIELD_LENGHT,true);
			} catch (ParseException e) {}
			values[i] = "";
		}		
        JPanel listPane = new JPanel();
        listPane.setLayout(new FlowLayout());
		for(i = 0; i < FIELD_COUNT; i++) {
			if (i > 0)
				listPane.add(new JLabel("-"));
			listPane.add(fields[i]);
		}                
        return listPane;
	}
	
	public boolean validateData() {
		for(int i = 0; i < FIELD_COUNT; i++) {
			values[i] = fields[i].getText();
		}		
		return true;
	}
	
	public String[] getValues() {
		return values;
	}
}
