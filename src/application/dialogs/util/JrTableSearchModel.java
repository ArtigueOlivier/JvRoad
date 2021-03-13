/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.util;

import javax.swing.table.AbstractTableModel;

import tools.JrMath;

import application.JrApplicationOption;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrTableSearchModel extends AbstractTableModel {
	private JrBook book = null;
	private Object msg[] = null;
	
	public JrTableSearchModel(JrBook bk) {
		book = bk;
		checkBook();
	}
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	
	public int getColumnCount() {
		return 5;
	}

	public int getRowCount() {
		return (msg == null)? 0 : msg.length; 
	}

	public String getColumnName(int col) {
		switch(col) {
		case 0 : return JrApplicationOption.GetWord("TxtCase");
		case 1 : return JrApplicationOption.GetWord("TxtPage");
		case 2 : return JrApplicationOption.GetWord("TxtPartielKm");
		case 3 : return JrApplicationOption.GetWord("TxtTotalKm");
		}
        return JrApplicationOption.GetWord("TxtCommentaire");
    }
	
	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
		if ((msg == null) || (row < 0) || (row > msg.length))
			return "";
		JrSearchMessage m = (JrSearchMessage)(msg[row]);
		switch(col) {
		case 0 : return new Integer(m.getIndex());
		case 1 : return new Integer(m.getPage());
		case 2 : 
			if (m.isInconnu()) return JrApplicationOption.GetWord("TxtInconnue");
			return format(m,true);
		case 3 : return format(m,false);
		}
		return m.getComment();
	}

	public void checkBook() {
		msg = null;
		if (book != null) {
			JrSearchMessageList list = new JrSearchMessageList(); 
			book.resumeCases(list);
			if (list.size() > 0)
				msg = list.toArray();
		}
		fireTableDataChanged();		
	}

	public String format(JrSearchMessage msg,boolean partiel) {
		int dst = (partiel)? msg.getPartiel() : msg.getTotal();		
		String str = JrMath.formatDistance(dst);
		String comp = "";
		if (partiel && (msg.isRaz() || msg.isApprox())) {
			if (msg.isRaz()) {
				if (msg.isApprox())
					comp = " " + JrApplicationOption.GetWord("TxtRAZ") + ", " + JrApplicationOption.GetWord("TxtApprox");
				else
					comp = " " + JrApplicationOption.GetWord("TxtRAZ");
			}
			else
				comp = " " + JrApplicationOption.GetWord("TxtApprox");
		}
		return str + comp;
	}
}
