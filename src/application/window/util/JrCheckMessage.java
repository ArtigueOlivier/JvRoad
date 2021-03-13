/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window.util;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCheckMessage {
	private int indexCase = 0;
	private boolean warning = true;
	private String texte = "";
	
	public JrCheckMessage(int index,String txt) {
		indexCase = index;
		texte = txt;
	}
	public JrCheckMessage(int index,String txt,boolean wrn) {
		indexCase = index;
		texte = txt;
		warning = wrn;
	}
	
	public int getIndex() {
		return indexCase;
	}
	
	public boolean isWarning() {
		return warning;		
	}
	
	public String getTexte() {
		return texte;
	}
	
}
