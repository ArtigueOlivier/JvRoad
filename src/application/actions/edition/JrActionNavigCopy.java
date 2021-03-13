/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import names.JrClipboardOpeName;

import application.actions.JrActionMenu;
import application.window.JrNavigDrawWindow;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionNavigCopy extends JrActionMenu {
	private int copyType = JrClipboardOpeName.CLIPBOARD_CASE;
	private JrNavigDrawWindow window = null;
	
	public JrActionNavigCopy(String txt,int mnemo,String hlp,int ope,JrNavigDrawWindow wnd) {
		super(txt,mnemo,hlp);
		copyType = ope;
		window = wnd;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		if (window != null)
			window.copy(copyType);
	}
	
	public static JrActionNavigCopy CreateAction(int ope,JrNavigDrawWindow wnd) {
		switch(ope) {
		case JrClipboardOpeName.CLIPBOARD_MAPS :
			return new JrActionNavigCopy("MenuEditCopyMaps",KeyEvent.VK_T,
					"MenuEditCopyMapsHlp",ope,wnd);
		case JrClipboardOpeName.CLIPBOARD_MAP1 :
			return new JrActionNavigCopy("MenuEditCopyMap",KeyEvent.VK_F,
					"MenuEditCopyMapHlp",ope,wnd);
		default : break;
		}
		return new JrActionNavigCopy("MenuEditCopyCase",KeyEvent.VK_E,
				"MenuEditCopyCaseHlp",JrClipboardOpeName.CLIPBOARD_CASE,wnd);
	}
}
