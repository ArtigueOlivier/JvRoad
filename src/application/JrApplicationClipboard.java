/*
 * Created on 4 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application;

import names.JrClipboardOpeName;
import book.JrCase;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrApplicationClipboard {
	private static JrApplicationClipboard clipboard = new JrApplicationClipboard();
	private JrCase theCase = new JrCase();
	private int copyType = JrClipboardOpeName.CLIPBOARD_CASE;
	
	public static void SetClipboard(JrCase cas,int typ) {
		clipboard.theCase = cas.copy();
		clipboard.copyType = typ;
	}
	
	public static JrCase GetClipboard() {
		return clipboard.theCase;
	}
	
	public static int GetClipboardType() {
		return clipboard.copyType;
	}
}
