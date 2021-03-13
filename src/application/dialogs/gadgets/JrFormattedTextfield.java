/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Dimension;

import javax.swing.JFormattedTextField;
import javax.swing.text.*;
//import javax.swing.text.Document;
//import javax.swing.text.PlainDocument;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFormattedTextfield extends JFormattedTextField {
	private int maxChars = 10;


	/**
	* Constructor forRestrictedTextField.
	* @param maxCharacters
	**/
	public JrFormattedTextfield(MaskFormatter format,int max) {
		super(format);
		maxChars = max;
	}

	public int getMaxChars() {
		return maxChars;
	}

//	overwritten method because textField was too wide
	public Dimension getPreferredSize() {
		return new Dimension(getColumnWidth() * getMaxChars(), super.getPreferredSize().height);
	}
//	overwritten method because textField was too wide
	protected int getColumnWidth() {
		return (int) (super.getColumnWidth() / 0.90);
	}
}
