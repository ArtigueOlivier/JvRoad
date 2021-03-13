/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.BorderLayout;

import book.JrBook;

import application.JrApplicationOption;
import application.actions.JrActionCenter;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCaseWindow extends JrWindow {
	//private JrListObjects listObjects = new JrListObjects(); 
	public JrCaseWindow(JrBook bk) {
		super(bk);
		add(new JrDrawWindow(book),BorderLayout.CENTER);
		if (JrApplicationOption.IsReaderMode() < 1) {
			add(JrActionCenter.GetActionMenuCenter().createCaseToolBar(),
					BorderLayout.PAGE_END);
		}
	}
}
