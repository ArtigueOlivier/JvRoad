/*
 * Created on Dec 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.border.EtchedBorder;

import names.JrCaseDisplayerName;
import names.JrDrawPortName;
import names.JrRegionName;

import tools.JrDrawTools;
import tools.JrRegions;

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import application.dialogs.JrCaseDialog;
import book.JrBook;
import book.JrBookListener;
import book.JrCase;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */;
public class JrDrawWindow extends JrWindow implements MouseListener {
	private int currentDisplayer = JrCaseDisplayerName.CASE_DISPLAYER_DEFAULT;
	private JrDrawTools drawTools = new JrDrawTools(null,JrDrawPortName.PORT_EDITION,100,0);
	private boolean drag = false;
	private boolean paintOnlyMap = false;
	private JrRegions regions = new JrRegions();
	
	public JrDrawWindow(JrBook bk) {
		super(bk);
		
		setBorder(new EtchedBorder(2));
		setBackground(Color.white);
		addMouseListener(this);
		
		JrBookListener bl = new JrBookListener(){

			public void titleChanged() {
			}

			public void copyrightChanged() {
			}

			public void positionChanged() {
				repaint();
			}

			public void caseAdded() {
				repaint();
			}

			public void caseDeleted() {
				repaint();
			}

			public void currentViewChanged() {
				drawTools.setScale(book.getScalePath());
				repaint();
			}

			public void currentMapChanged() {
				paintOnlyMap = true;
				repaint();
				paintOnlyMap = false;
			}

			public void saved() {
			}

			public void showIndexChanged() {
				currentDisplayer = -1; // To force a region recompute.
				repaint();
			}
		};
		bk.addListener(bl);
		drawTools.setScale(bk.getScalePath());
		drawTools.setDisplayMeters(bk.getDisplayMeters());
		
		MouseMotionAdapter mm = new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent arg0) {
				if (drag) {
					JrRegions regs = drawTools.getRegions();
					int name = regs.whichRegion(arg0.getX(),arg0.getY());
					if (name == JrRegionName.REGION_MAP) {
						JrCase cas = book.getCurrentCase();
						if (cas.handleDrag(arg0,book.getContext(),drawTools))
							book.fireCurrentMapChanged();
					}
				}
			}

			public void mouseMoved(MouseEvent arg0) {
			}			
		};
		addMouseMotionListener(mm);
	}
			
	public void paint(Graphics g) {
		super.paint(g);
		drawTools.setDeviceGraphic(g);
		drawTools.setColorBook(book.getColorBook());
		if (computeRegion())
			book.drawCase(drawTools,book.getCurrentCaseIndex(),-1,JrBook.GetNoFilter());
		else {
			if (paintOnlyMap)
				book.drawCaseMap(drawTools,book.getCurrentCaseIndex(),JrBook.GetNoFilter());
			else
				book.drawCase(drawTools,book.getCurrentCaseIndex(),-1,JrBook.GetNoFilter());
		}
	}
	
	public void setBook(JrBook bk) {
		super.setBook(bk);
		if (book == null) {
			if (drawTools == null) 
				drawTools = new JrDrawTools(null,JrDrawPortName.PORT_EDITION,100,0);
		}
		else {
			if (drawTools == null) 
				drawTools = new JrDrawTools(book.getColorBook(),JrDrawPortName.PORT_EDITION,100,0);
			else 
				drawTools.setColorBook(book.getColorBook());
			drawTools.setDisplayMeters(book.getDisplayMeters());
		}
	}
	
	public boolean computeRegion() {
		int disp = book.getCaseDisplayer().getName();
		Rectangle rect =  getBounds();
		rect.grow(-5,-5);
		if (drawTools.isRegionModified(rect) || (currentDisplayer != disp)) {
			book.getCaseDisplayer().apply(rect,regions,book.isShowIndex());
			drawTools.setRegions(regions);
			drawTools.getRegions().setReference(rect);
			drawTools.adjustFonts(book.getCaseDisplayer());
			drawTools.adjustPens();
			currentDisplayer = disp;
			return true;
		}			
		return false;
	}
	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (JrApplicationOption.IsReaderMode() > 0) 
			return;

		boolean hasChanged = false;
		boolean fullRefresh = false;
		drag = false;
		JrCase cas;
		JrRegions regs = drawTools.getRegions();
		int name = regs.whichRegion(arg0.getX(),arg0.getY()); 
		if (arg0.getButton() == MouseEvent.BUTTON3) {
			switch(name) {
			case JrRegionName.REGION_SYMBOL1 :
			case JrRegionName.REGION_SYMBOL2 :
			case JrRegionName.REGION_SYMBOL3 :
			case JrRegionName.REGION_SYMBOL4 : 
				cas = book.getCurrentCase();
				cas.selectNextSymbol(name - JrRegionName.REGION_SYMBOL1);
				hasChanged = true;
				fullRefresh = true;
				break;
			case JrRegionName.REGION_MAP :
				cas = book.getCurrentCase();
				hasChanged = cas.handleMapEvent(arg0,book.getContext(),drawTools);
				break;
			default : break;
			}
		}
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			if (arg0.getClickCount() == 2) {
				switch(name) {
				case JrRegionName.REGION_SYMBOL1 :
				case JrRegionName.REGION_SYMBOL2 :
				case JrRegionName.REGION_SYMBOL3 :
				case JrRegionName.REGION_SYMBOL4 :
				case JrRegionName.REGION_DISTANCE :
					JrCaseDialog dlg = new JrCaseDialog(JrActionMenu.GetMainFrame(),book,0);
					if (dlg.execute() == true) 
						book.updateTotal(true);
					break;
				case JrRegionName.REGION_TEXT :
					JrCaseDialog dlg2 = new JrCaseDialog(JrActionMenu.GetMainFrame(),book,1);
					if ((dlg2.execute() == true) && (book != null)) {
						book.updateTotal(true);
						hasChanged = true;
						fullRefresh = true;
					}
					break;
				case JrRegionName.REGION_MAP :
					cas = book.getCurrentCase();
					hasChanged = cas.handleMapEvent(arg0,book.getContext(),drawTools);
					break;
				default: break;
				}
			}
			else {
				switch(name) {
				case JrRegionName.REGION_MAP :
					cas = book.getCurrentCase();
					hasChanged = cas.handleMapEvent(arg0,book.getContext(),drawTools);
					break;
				default : break;
				}
				
			}
		}
		if (hasChanged)
			if (fullRefresh)
				book.fireCurrentViewChanged();
			else
				book.fireCurrentMapChanged();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	public void mouseEntered(MouseEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent arg0) {
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	public void mousePressed(MouseEvent arg0) {
		if (arg0.getButton() == MouseEvent.BUTTON1) {
			JrRegions regs = drawTools.getRegions();
			int name = regs.whichRegion(arg0.getX(),arg0.getY());
			if (name == JrRegionName.REGION_MAP) {
				JrCase cas = book.getCurrentCase();
				if (cas.handleBeginDrag(arg0,book.getContext(),drawTools)) {
					drag = true;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	public void mouseReleased(MouseEvent arg0) {
		if (drag) {
			JrRegions regs = drawTools.getRegions();
			int name = regs.whichRegion(arg0.getX(),arg0.getY());
			if (name == JrRegionName.REGION_MAP) {
				JrCase cas = book.getCurrentCase();
				cas.handleEndDrag(arg0,book.getContext(),drawTools);
				book.fireCurrentMapChanged();
			}
		}
		drag = false;
	}
}
