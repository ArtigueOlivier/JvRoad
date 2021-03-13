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
public interface JrBookListener {
	public void titleChanged();
	public void copyrightChanged();
	public void positionChanged();
	public void saved();

	public void caseAdded();
	public void caseDeleted();
	
	public void currentViewChanged();
	public void currentMapChanged();
	public void showIndexChanged();
}
