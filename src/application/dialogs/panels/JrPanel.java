/*
 * Created on 24 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.LayoutManager;

import javax.swing.JPanel;

import application.JrApplicationOption;
import book.JrBook;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPanel extends JPanel {
	protected JrBook book = null;
	
	public JrPanel(JrBook bk) {
		book = bk;
	}

	public JrPanel(JrBook bk,LayoutManager layout) {
		super(layout);
		book = bk;
	}
	
	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
}
