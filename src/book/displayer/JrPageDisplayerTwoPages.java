/*
 * Created on Dec 23, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.displayer;

import names.JrPageDisplayerName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrPageDisplayerTwoPages extends JrPageDisplayerMonoColumn {
	public int getName() {
		return JrPageDisplayerName.PAGE_DISPLAYER_TWOPAGES;
	}
	public boolean hasTwoPages() {
		return true;
	}
}
