/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Dimension;

import javax.swing.JTextField;
import javax.swing.text.*;
//import javax.swing.text.Document;
//import javax.swing.text.PlainDocument;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrTextfield extends JTextField {
	private int maxChars = 10;

	/**
	* Constructor forRestrictedTextField.
	* 
	**/
	public JrTextfield() {
		super();
	}

	/**
	* Constructor forRestrictedTextField.
	* @param maxCharacters
	**/
	public JrTextfield(int maxCharacters) {
		super();
		setMaxCharacters(maxCharacters);
	}

	public void setMaxCharacters(int maxChars) {
		if (maxChars < 0) {
			this.maxChars = 0;
		} 
		else {
			this.maxChars = maxChars;
		}
	}

	public int getMaxChars() {
		return maxChars;
	}

//	insertString overwritten to achieve 'maximum character' goal
	protected Document createDefaultModel() {
		return new PlainDocument() {

	public void insertString (int offs, String str, AttributeSet a) throws BadLocationException {
		if (str == null) {
			return;
		}

//	 Get the length of the text in JTextField
		int len = 0;
		if (JrTextfield.this.getText() == null) {
			len = 0;
		} 
		else {
			len = JrTextfield.this.getText().length();
		}

		if (len + str.length() > JrTextfield.this.maxChars) {
//	 Total chars will be more than max allowed. So, just return
			return;
		} 
		else {
//	 Update the text field
			super.insertString(offs, str, a);
		}
	}
	};
	}

//	overwritten method because textField was too wide
	public Dimension getPreferredSize() {
		return new Dimension(getColumnWidth() * getMaxChars(), super.getPreferredSize().height);
	}

//	overwritten method because textField was too wide
	protected int getColumnWidth() {
		return (int) (super.getColumnWidth() / 1.25);
	}
}
