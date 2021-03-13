/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.Component;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import application.actions.JrActionCenter;
import book.JrBiblio;
import book.JrBiblioListener;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBiblioWindow extends JTabbedPane {
	private JrBiblio biblio = null;
	private boolean modified = false;

	public JrBiblioWindow(JrBiblio bib) {
		biblio = bib;
		
		JrBiblioListener bl = new JrBiblioListener(){

			public void bookAdded(JrBook book) {
				//System.out.println("bookAdded");
				JrBookWindow wnd = new JrBookWindow(book);
				addTab(computeBookTitle(book), wnd);
			}

			public void bookClosed() {
				//System.out.println("bookClosed");
				remove(getSelectedIndex());
			}

			public void bookSaved() {
				//System.out.println("bookSaved");
				setTitleAt(getSelectedIndex(),computeBookTitle(biblio.getCurrentBook()));		
			}

			public void currentBookChanged(int index) {
				//System.out.println("currentBookChanged");
				if (getSelectedIndex() != index) {
					setSelectedIndex(index);
				}
			}

			public void bookTitleChanged(JrBook book) {
				//System.out.println("bookTitleChanged");
				if (book.isModified() != modified)
					setTitleAt(getSelectedIndex(),computeBookTitle(book));		
				JrActionCenter.RefreshActions();
			}

			public void bookCopyrightChanged(JrBook book) {
				//System.out.println("bookCopyrightChanged");
				if (book.isModified() != modified)
					setTitleAt(getSelectedIndex(),computeBookTitle(book));		
				JrActionCenter.RefreshActions();
			}

			public void bookPositionChanged(JrBook book) {
				//System.out.println("bookPositionChanged");
				setTitleAt(getSelectedIndex(),computeBookTitle(book));		
			}

			public void bookCaseAdded(JrBook book) {
				//System.out.println("bookCaseAdded");
				if (book.isModified() != modified)
					setTitleAt(getSelectedIndex(),computeBookTitle(book));		
			}

			public void bookCaseDeleted(JrBook book) {
				//System.out.println("bookCaseDeleted");
				if (book.isModified() != modified)
					setTitleAt(getSelectedIndex(),computeBookTitle(book));		
			}

			public void bookCurrentViewChanged(JrBook book) {
				//System.out.println("bookCurrentViewChanged");
				if (book.isModified() != modified)
					setTitleAt(getSelectedIndex(),computeBookTitle(book));		
				JrActionCenter.RefreshActions();
			}

			public void bookCurrentMapChanged(JrBook book) {
				//System.out.println("bookCurrentMapChanged");
				if (book.isModified() != modified)
					setTitleAt(getSelectedIndex(),computeBookTitle(book));		
				JrActionCenter.RefreshActions();
				
			}};
		biblio.addListener(bl);
	
		ChangeListener clst = new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				biblio.setCurrentBook(getSelectedIndex());
				Component cnp = getSelectedComponent();
				if (cnp instanceof JrBookWindow) {
					((JrBookWindow)cnp).checkPalette();
				}
				JrActionCenter.RefreshActions();
			}};
	
			addChangeListener(clst);
	}
	
	public String computeBookTitle(JrBook book) {
		String s = book.getTitle() + " [" + (book.getCurrentCaseIndex() + 1) + "/" + book.getCaseCount() + "]";
		if (book.isModified())
			return "*" + s;
		modified = book.isModified();
		return s;
	}
}
