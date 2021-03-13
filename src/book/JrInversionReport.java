/*
 * Created on 5 déc. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrInversionReport {
	private boolean hasRaz = false;
	private boolean hasImgMap = false;
	private boolean hasCapMap = false;
	
	public JrInversionReport() {		
	}
	
	public void setRaz(boolean raz) {
		if (raz == true)
		hasRaz = raz;
	}

	public boolean hasRaz() {
		return hasRaz;
	}

	public void setImgMap(boolean map) {
		if (map == true)
		hasImgMap = map;
	}

	public boolean hasImgMap() {
		return hasImgMap;
	}

	public void setCapMap(boolean map) {
		if (map == true)
		hasCapMap = map;
	}

	public boolean hasCapMap() {
		return hasCapMap;
	}

}
