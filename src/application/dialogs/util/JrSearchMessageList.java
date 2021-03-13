/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.util;

import java.util.ArrayList;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSearchMessageList extends ArrayList {
	public JrSearchMessageList() {		
	}
	
	public void addMsg(int index,int page,int partiel,int total,boolean raz,boolean approx,boolean inconnu,String comment) {
		add(new JrSearchMessage(index,page,partiel,total,raz,approx,inconnu,comment));
	}
}
