/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import application.JrApplicationOption;
import book.JrBook;

import tools.JrContext;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrWindow extends JPanel {
	protected JrContext context = null;
	protected JrBook book = null;
	
	public JrWindow(JrBook bk) {
		super(new BorderLayout());
		this.setBook(bk);
	}

	public JrWindow(JrBook bk,boolean noborderLayout) {
		this.setBook(bk);
	}

	public static String GetWord(String code,String def) {
		return JrApplicationOption.GetWord(code,def);
	}
			
	public void setBook(JrBook bk) {
		book = bk;
		if (book != null)
			context = book.getContext();
	}
	
	public JrBook getBook() {
		return book;
	}

	public JrContext getContext() {
		return context;
	}
}
