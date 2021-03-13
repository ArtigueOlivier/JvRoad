/*
 * Created on Dec 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.BorderLayout;

import javax.swing.JToolBar;

import names.JrPaletteName;

import application.JrApplicationOption;
import application.actions.JrActionCenter;
import book.JrBook;
import book.JrBookListener;
import book.JrCase;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrBookWindow extends JrWindow {
	private int currentPaletteName = JrPaletteName.PALETTE_CASE;
	private JToolBar currentPalette = null;
	
	public JrBookWindow(JrBook bk) {
		super(bk);
		JrCase cas = bk.getCurrentCase();
		currentPaletteName = cas.getMapObjectPalette(bk.getCurrentView());
		
		JrBookListener bl = new JrBookListener(){

			public void titleChanged() {
			}

			public void copyrightChanged() {
			}

			public void positionChanged() {
				checkPalette();
			}

			public void caseAdded() {
				checkPalette();
			}

			public void caseDeleted() {
				checkPalette();
			}

			public void currentViewChanged() {
				checkPalette();
			}

			public void currentMapChanged() {
			}

			public void saved() {
			}

			public void showIndexChanged() {
			}
		};
		bk.addListener(bl);
		
		if (JrApplicationOption.IsReaderMode() < 1) {
			JrActionCenter mkAction = JrActionCenter.GetActionMenuCenter();
			currentPalette = mkAction.getToolbarMap(currentPaletteName);
			
			add(mkAction.createBookToolBar(), BorderLayout.PAGE_START);
			add(currentPalette,BorderLayout.WEST);
			add(new JrCaseWindow(bk),BorderLayout.CENTER);
		}
		else {
			JrActionCenter mkAction = JrActionCenter.GetActionMenuCenter();
			add(mkAction.createBookToolBar(), BorderLayout.PAGE_START);
			add(new JrCaseWindow(bk),BorderLayout.CENTER);			
		}
	}

	public void checkPalette() {
		if (JrApplicationOption.IsReaderMode() < 1) {
			JrCase cas = book.getCurrentCase();
			int curMap = cas.getMapObjectPalette(book.getCurrentView());
			boolean adjust = (currentPaletteName != curMap)? true : false;
			if (currentPalette != null)
				adjust = (currentPalette.getParent() != this)? true : adjust;
			if (adjust) {
				currentPaletteName = curMap;
				remove(currentPalette);
				currentPalette = JrActionCenter.GetActionMenuCenter().getToolbarMap(currentPaletteName);
				add(currentPalette,BorderLayout.WEST);
				validate();
				currentPalette.repaint();
			}
		}
	}
}
