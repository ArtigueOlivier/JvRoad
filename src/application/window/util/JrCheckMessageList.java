/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window.util;

import java.util.ArrayList;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrCheckMessageList extends ArrayList {
	public JrCheckMessageList() {		
	}
	
	public void addWarning(int index,String texte) {
		add(new JrCheckMessage(index,texte));
	}
	public void addError(int index,String texte) {
		add(new JrCheckMessage(index,texte,false));
	}
	
}
