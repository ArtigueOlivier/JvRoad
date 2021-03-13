/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package licence;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrLicence {
	private static JrLicence stLicense = new JrLicence();
	
	private JrLicence() {
		
	}
	
	public static JrLicence GetLicence() {
		return stLicense;
	}
	
	public static int GetLimit() {
		return 20;
	}
}
