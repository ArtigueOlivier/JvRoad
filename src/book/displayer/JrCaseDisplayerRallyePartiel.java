/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import names.JrCaseDisplayerName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCaseDisplayerRallyePartiel extends JrCaseDisplayerRallye {
	public int getName() {
		return JrCaseDisplayerName.CASE_DISPLAYER_RALLYE_PARTIEL;
	}
	
	public boolean isDrawFrameTotal() {
		return false;
	}
	public boolean isSmallTotal() {
		return true;
	}
}
