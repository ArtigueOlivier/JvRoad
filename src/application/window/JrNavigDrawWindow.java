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
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;
import javax.swing.border.EtchedBorder;

import names.JrCaseDisplayerName;
import names.JrClipboardOpeName;
import names.JrDrawPortName;
import names.JrGotoName;
import names.JrPenName;
import names.JrViewName;

import tools.JrDrawTools;
import tools.JrRegions;

import application.JrApplicationClipboard;
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
public class JrNavigDrawWindow extends JrWindow implements JrBookListener,MouseListener {
	private Rectangle reference = new Rectangle(0,0,0,0);
	private JrRegions regions[] = null;
	private int currentDisplayer = JrCaseDisplayerName.CASE_DISPLAYER_DEFAULT;
	private JrDrawTools drawTools = new JrDrawTools(null,JrDrawPortName.PORT_NAVIGATOR,100,0);
	
	private int caseInView = 0;
	private int fullCaseInView = 0;
	private int currentCase = 0;
	private int lastCaseTop = 0;
	private int caseCount = 0;
	private int heightCase = 1;
	private int selectedCase = -1;
	
	public JrNavigDrawWindow(JrBook bk) {
		super(bk);
		setBorder(new EtchedBorder(2));
		setBackground(Color.white);
		addMouseListener(this);
		if (bk != null) {
			caseCount = bk.getCaseCount();
			bk.addListener(this);
			drawTools.setScale(bk.getScalePath());
			drawTools.setDisplayMeters(bk.getDisplayMeters());
		}		
	}
	
	public void setBook(JrBook bk) {
		if (book != null)
			book.removeListener(this);
		caseInView = 0;		
		currentCase = 0;
		book = bk;
		if (bk == null) {
			caseCount = 0;
			if (drawTools == null)
				drawTools = new JrDrawTools(null,JrDrawPortName.PORT_NAVIGATOR,100,0);
		}
		else {
			if (drawTools == null)
				drawTools = new JrDrawTools(bk.getColorBook(),JrDrawPortName.PORT_NAVIGATOR,100,0);
			else
				drawTools.setColorBook(bk.getColorBook());
			drawTools.setDisplayMeters(bk.getDisplayMeters());
			caseCount = bk.getCaseCount();
			book.addListener(this);
			repaint();
		}
	}
		
	public void goToCase(int ope) {
		switch(ope) {
		case JrGotoName.GOTO_PREVIOUS :
			currentCase--; break;
		case JrGotoName.GOTO_NEXT :
			currentCase++; break;
		case JrGotoName.GOTO_LAST :
			currentCase = lastCaseTop; break;
		default : currentCase = 0; break;
		}		
		repaint();
	}
	
	public boolean isOperatorEnabled(int ope) {
		if (caseCount <= fullCaseInView)
			return false;
		switch(ope) {
		case JrGotoName.GOTO_NEXT :			
		case JrGotoName.GOTO_LAST :
			return (currentCase < lastCaseTop)? true : false; 
		default : break;
		}
		return (currentCase > 0)? true : false;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		drawTools.setDeviceGraphic(g);
		if (computeRegion()) {
			Shape oldClip = g.getClip();
			g.setClip(reference.x,reference.y,reference.width+1,reference.height+1);
			for(int i = 0; i < caseInView; i++) {
				drawTools.setRegions(regions[i]);
				if ((currentCase+i) < caseCount)
					book.drawCasePreview(drawTools,currentCase+i,currentCase+i,JrBook.GetNoFilter());
			}
			drawTools.selectDefinedPen(JrPenName.PEN_MAP_BLACK,true);
			drawTools.drawRectangle(reference.x,reference.y,reference.width,reference.height,false);
			g.setClip(oldClip);
		}
	}
	
	public boolean computeRegion() {
		int w,h;
		if (book == null) {
			caseInView = 0;
			lastCaseTop = 0;
			return false;
		}

		int disp = book.getCaseDisplayer().getName();
		Rectangle r = new Rectangle(0,0,0,0);
		Rectangle rect = new Rectangle(0,0,0,0);
		computeVisibleRect(rect);
		rect.x += 5;
		rect.y = 5;
		rect.width -= 10;
		rect.height -= 10;
		if ((reference.equals(rect) == false) || (currentDisplayer != disp) || (caseInView == 0)) {
			reference = rect;
			w = rect.width;
			h = (w * 2) / 3;
			heightCase = Math.max(1,h);
			fullCaseInView = (rect.height / h);
			caseInView = fullCaseInView + (((rect.height % h) == 0)? 0 : 1);
			lastCaseTop = Math.max(0,caseCount - caseInView);
			lastCaseTop += ((rect.height % w) == 0)? 0 : 1;
			regions = new JrRegions [caseInView];
			r.setBounds(rect.x,rect.y,w,h);
			for(int i = 0; i < caseInView; i++) {
				regions[i] = new JrRegions();
				book.getCaseDisplayer().apply(r,regions[i],book.isShowIndex());
				r.y += r.height;								
			}
			drawTools.setRegions(regions[0]);
			drawTools.adjustFonts(book.getCaseDisplayer());
			drawTools.adjustPens();
			currentDisplayer = disp;			
			JrActionCenter.RefreshActionsNavigator();
		}
		return true;
	}

	public void copy(int ope) {
		if (selectedCase != -1) {
			JrCase cas = book.getCase(selectedCase);
			int typ = JrClipboardOpeName.CLIPBOARD_MAP1;
			switch(ope) {
			case JrClipboardOpeName.CLIPBOARD_MAPS :
			case JrClipboardOpeName.CLIPBOARD_CASE :
				typ = ope; break;
			case JrClipboardOpeName.CLIPBOARD_MAP1 :
				switch(book.getCurrentView()) {
				case JrViewName.VIEW_TWO : 
					typ = JrClipboardOpeName.CLIPBOARD_MAP2; break;
				case JrViewName.VIEW_THREE :
					typ = JrClipboardOpeName.CLIPBOARD_MAP3; break;
				case JrViewName.VIEW_OBJ :
					typ = JrClipboardOpeName.CLIPBOARD_MAP4; break;
				default : break;
				}
				break;
			}
			JrApplicationClipboard.SetClipboard(cas,typ);
			selectedCase = -1;
		}
	}
	
	public void titleChanged() {
	}

	public void copyrightChanged() {
	}

	public void positionChanged() {
	}

	public void saved() {
	}

	public void caseAdded() {
		caseInView = 0;
		caseCount = book.getCaseCount();		
		repaint();
	}

	public void caseDeleted() {
		caseInView = 0;
		caseCount = book.getCaseCount();
		repaint();
	}

	public void currentViewChanged() {
		drawTools.setScale(book.getScalePath());		
		repaint();
	}

	public void currentMapChanged() {
		repaint();
	}
	public void showIndexChanged() {
		caseInView = 0;
		repaint();
	}

	/* (non-Javadoc)
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	
	public void mouseClicked(MouseEvent arg0) {
		if (JrApplicationOption.GetCode() != 129834765)
			return;
		if (arg0.getClickCount() == 2) {
			int num = currentCase + (arg0.getY() / heightCase);
			if ((book != null) && (num < caseCount)) {
				book.goToCase(num);
				JrActionCenter.RefreshActions();
			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		if (JrApplicationOption.GetCode() != 129834765)
			return;
		if (arg0.isPopupTrigger()) {		
			if (arg0.getButton() == MouseEvent.BUTTON3) {
				int num = currentCase + (arg0.getY() / heightCase);
				if ((book != null) && (num < caseCount)) {
					selectedCase = num;
					JPopupMenu popup = JrActionCenter.GetActionMenuCenter().createPopupNavigator(this);
					popup.show(arg0.getComponent(),
		                       arg0.getX(), arg0.getY());
				}			
			}
		}
	}

	public void mouseReleased(MouseEvent arg0) {
		if (JrApplicationOption.GetCode() != 129834765)
			return;
		if (arg0.isPopupTrigger()) {		
			if (arg0.getButton() == MouseEvent.BUTTON3) {
				int num = currentCase + (arg0.getY() / heightCase);
				if ((book != null) && (num < caseCount)) {
					selectedCase = num;
					JPopupMenu popup = JrActionCenter.GetActionMenuCenter().createPopupNavigator(this);
					popup.show(arg0.getComponent(),
		                       arg0.getX(), arg0.getY());
				}			
			}
		}
	}

}



