package book;

import java.util.ArrayList;
import java.util.ListIterator;

import tools.files.JrFileRead;

/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBiblio {
	private ArrayList books = new ArrayList();
	private JrBook currentBook = null;
	private ArrayList listeners = new ArrayList();
	private JrInternalBookListener listenBook = new JrInternalBookListener();
	
	public JrBiblio() {
	}

	public void setCurrentBook(int ndx) {
		if ((ndx >= 0) && (ndx != books.indexOf(currentBook)))
			if (ndx < books.size())
				setCurrentBook((JrBook)books.get(ndx));
	}
	
	private void setCurrentBook(JrBook book) {
		if (currentBook != book) {
			if (currentBook != null)
				currentBook.removeListener(listenBook);
			currentBook = book;
			if (currentBook != null)
				currentBook.addListener(listenBook);
			fireCurrentBookChanged(books.indexOf(currentBook));						
		}
	}
	
	public JrBook getCurrentBook() {
		return currentBook;
	}
	
	public void newBook(JrBook book) {
		int ndx = books.indexOf(book);
		if (ndx == -1) {
			books.add(book);
			fireBookAdded(book);
			setCurrentBook(book);
		}
		else {
			setCurrentBook((JrBook)books.get(ndx));
		}
	}
		
	public void openBook(JrFileRead file) {
		JrBook book = new JrBook(file);
		int ndx = books.indexOf(book);
		if (ndx == -1) {
			books.add(book);
			fireBookAdded(book);
			setCurrentBook(book);
		}
		else {
			setCurrentBook((JrBook)books.get(ndx));
		}
		
	}
	
	public void closeBook() {
		if (currentBook == null)
			return;
		int ndx = books.indexOf(currentBook);
		if (currentBook.isModified()) {
			
		}
		if (ndx > 0) {			
			books.remove(ndx);
			currentBook = (JrBook)books.get(ndx-1);
			fireBookClosed();
			fireCurrentBookChanged(ndx-1);
			return;
		}
		if (ndx == 0) {
			books.remove(0);
			fireBookClosed();
			if (books.size() > 0) {
				currentBook = (JrBook)books.get(0);
				fireCurrentBookChanged(0);				
			}
			else
				currentBook = null;
		}
	}

	public boolean hasModifiedBook() {
		ListIterator iter = books.listIterator();
		while(iter.hasNext()) {
			if(((JrBook)(iter.next())).isModified())
				return true;
		}		
		return false;
	}
	
	
	public void addListener(JrBiblioListener lst) {
		listeners.add(lst);
	}

	public void removeListener(JrBiblioListener lst) {
		listeners.remove(lst);
	}

	public void fireBookAdded(JrBook book) {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookAdded(book);
		}		
	}
	
	public void fireCurrentBookChanged(int ndx) {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).currentBookChanged(ndx);
		}				
	}

	public void fireBookSaved() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookSaved();
		}				
	}
	
	public void fireBookClosed() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookClosed();
		}				
	}

	public void fireBookTitleChanged() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookTitleChanged(currentBook);
		}				
	}

	public void fireBookCopyrightChanged() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookCopyrightChanged(currentBook);
		}				
	}
	
	public void fireBookPositionChanged() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookPositionChanged(currentBook);
		}				
	}

	public void fireBookCaseAdded() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookCaseAdded(currentBook);
		}				
	}
	
	public void fireBookCaseDeleted() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookCaseDeleted(currentBook);
		}				
	}
	
	public void fireBookCurrentViewChanged() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookCurrentViewChanged(currentBook);
		}				
	}
	
	public void fireBookCurrentMapChanged() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBiblioListener)(iter.next())).bookCurrentMapChanged(currentBook);
		}				
	}

	private class JrInternalBookListener implements JrBookListener {

		/* (non-Javadoc)
		 * @see book.JrBookListener#titleChanged()
		 */
		public void titleChanged() {
			fireBookTitleChanged();
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#copyrightChanged()
		 */
		public void copyrightChanged() {
			fireBookCopyrightChanged();
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#positionChanged()
		 */
		public void positionChanged() {
			fireBookPositionChanged();			
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#caseAdded()
		 */
		public void caseAdded() {
			fireBookCaseAdded();			
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#caseDeleted()
		 */
		public void caseDeleted() {
			fireBookCaseDeleted();			
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#currentViewChanged()
		 */
		public void currentViewChanged() {
			fireBookCurrentViewChanged();			
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#currentMapChanged()
		 */
		public void currentMapChanged() {
			fireBookCurrentMapChanged();
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#saved()
		 */
		public void saved() {
			fireBookSaved();
			// TODO Auto-generated method stub
			
		}

		/* (non-Javadoc)
		 * @see book.JrBookListener#showIndexChanged()
		 */
		public void showIndexChanged() {
			// TODO Auto-generated method stub
			
		}	
	}
}
