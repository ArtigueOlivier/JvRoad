/*
 * Created on 10 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.tools;

import java.awt.event.KeyEvent;

import application.actions.position.JrActionPositionSearch;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionToolResume extends JrActionPositionSearch {
	public JrActionToolResume() {
		super("MenuOutilsResume",KeyEvent.VK_R, "MenuOutilsResumeHlp","images/resume.gif");		
	}
}
