/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window.util;

import javax.swing.table.AbstractTableModel;

import application.JrApplicationOption;
import application.actions.JrActionCenter;
import book.JrBook;
import book.JrBookListener;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrTableCheckModel extends AbstractTableModel implements JrBookListener {
	private JrBook book = null;
	private Object msg[] = null;
	
	public JrTableCheckModel(JrBook bk) {
		book = bk;
		checkBook();
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	
	public void setBook(JrBook bk) {
		if (book != bk) {
			if (book != null)
				book.removeListener(this);
			book = bk;
			if (book != null) {
				book.addListener(this);
			}
			checkBook();
		}
	}
	
	public int getColumnCount() {
		return 3;
	}

	public int getRowCount() {
		return (msg == null)? 0 : msg.length; 
	}

	public String getColumnName(int col) {
		switch(col) {
		case 0 : return JrApplicationOption.GetWord("TxtCase");
		case 1 : return JrApplicationOption.GetWord("TxtType");
		}
        return JrApplicationOption.GetWord("TxtMessage");
    }
	
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
		if ((msg == null) || (row < 0) || (row > msg.length))
			return "";
		JrCheckMessage m = (JrCheckMessage)(msg[row]);
		switch(col) {
		case 0 : return new Integer(m.getIndex());
		case 1 : return (m.isWarning())? JrApplicationOption.GetWord("TxtRemarque") : JrApplicationOption.GetWord("TxtErreur");		
		}
		return JrApplicationOption.GetWord(m.getTexte());
	}

	public void checkBook() {
		msg = null;
		if (book != null) {
			JrCheckMessageList list = new JrCheckMessageList(); 
			book.checkCases(list);
			if (list.size() > 0)
				msg = list.toArray();
		}
		fireTableDataChanged();		
	}
	
	public void synchronize(int row) {
		if ((msg == null) || (row < 0) || (row > msg.length))
			return;
		JrCheckMessage m = (JrCheckMessage)(msg[row]);
		int num = m.getIndex();
		book.goToCase(num-1);
		JrActionCenter.RefreshActions();
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
		checkBook();		
	}
	public void caseDeleted() {
		checkBook();		
	}
	public void currentViewChanged() {
		checkBook();		
	}
	public void currentMapChanged() {
		checkBook();		
	}
	public void showIndexChanged() {
	}
}
