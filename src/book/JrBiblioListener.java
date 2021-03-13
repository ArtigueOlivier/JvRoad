/*
 * Created on Dec 16, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public interface JrBiblioListener {
	public void bookAdded(JrBook book);
	public void bookClosed();
	public void bookSaved();
	public void currentBookChanged(int index);
	
	public void bookTitleChanged(JrBook book);
	public void bookCopyrightChanged(JrBook book);
	public void bookPositionChanged(JrBook book);
	
	public void bookCaseAdded(JrBook book);
	public void bookCaseDeleted(JrBook book);
	
	public void bookCurrentViewChanged(JrBook book);
	public void bookCurrentMapChanged(JrBook book);
}
