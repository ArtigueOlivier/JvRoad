/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPropertyTextfield extends JrTextfield implements FocusListener {

	/**
	* Constructor forRestrictedTextField.
	* 
	**/
	public JrPropertyTextfield() {
		super();
		addFocusListener(this);
	}

	/**
	* Constructor forRestrictedTextField.
	* @param maxCharacters
	**/
	public JrPropertyTextfield(int maxCharacters) {
		super(maxCharacters);
		addFocusListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	public void focusGained(FocusEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	public void focusLost(FocusEvent arg0) {
		fireActionPerformed();
	}
}
