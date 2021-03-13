/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import book.JrBiblio;
import book.JrBiblioListener;
import book.JrBook;

import application.actions.JrActionCenter;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrNavigatorWindow extends JPanel {
	private JrBiblio biblio = null;
	private JrNavigDrawWindow drawWindow = null;
	
	public JrNavigatorWindow(JrBiblio bib) {		
		super(new BorderLayout());

		biblio = bib;
		drawWindow = new JrNavigDrawWindow(bib.getCurrentBook());
		
		add(JrActionCenter.GetActionMenuCenter().createToolbarNavigator(drawWindow),
				BorderLayout.PAGE_START);		
		add(drawWindow,BorderLayout.CENTER);				
		
		JrBiblioListener bl = new JrBiblioListener(){
			public void bookAdded(JrBook book) {
				drawWindow.setBook(book);
			}

			public void bookClosed() {
				drawWindow.setBook(biblio.getCurrentBook());
			}

			public void bookSaved() {
			}

			public void currentBookChanged(int index) {
				drawWindow.setBook(biblio.getCurrentBook());
			}

			public void bookTitleChanged(JrBook book) {
			}

			public void bookCopyrightChanged(JrBook book) {
			}

			public void bookPositionChanged(JrBook book) {
			}

			public void bookCaseAdded(JrBook book) {
			}

			public void bookCaseDeleted(JrBook book) {
			}

			public void bookCurrentViewChanged(JrBook book) {
			}

			public void bookCurrentMapChanged(JrBook book) {
		}};
		biblio.addListener(bl);
				
	}
	
}
