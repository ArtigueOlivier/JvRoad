/*
 * Created on Jul 12, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import book.filter.JrFilter;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterGadget extends JrFilter {
	private boolean selected = false;
	
	public JrFilterGadget() {
		super();
	}

	public JrFilterGadget(JrFilter filter) {
		super(filter);
	}
				
    public static String GetOrderTypeText(int val) {
    	switch(val) {
    	case JrFilter.ORDER_INVERSE : return "TxtInverse";
    	case JrFilter.ORDER_SHAKER : return "TxtMelange";
    	default : break;    	
    	}
    	return "TxtNormal";
    }
    
    public static String GetDrawModeText(int val) {
    	switch(val) {
    	case JrFilter.DRAW_ROADTOPATH : return "TxtRouteEnChemin";
    	default : break;
    	}
    	return "TxtNormal";
    }
       
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
