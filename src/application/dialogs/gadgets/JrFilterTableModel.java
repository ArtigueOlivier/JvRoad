/*
 * Created on Feb 7, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import application.JrApplicationOption;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterTableModel extends AbstractTableModel {
	private Vector filters = new Vector();
	private boolean active = true;
	
	public JrFilterTableModel(Vector fil) {
		filters = fil;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	public int getColumnCount() {
		return 4;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		return filters.size();
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	public Object getValueAt(int row, int col) {
		JrFilterGadget item = (JrFilterGadget)(filters.get(row));
		return item;
	}
	
	public String getColumnName(int col) {
		switch(col) {
		case 1 : return JrApplicationOption.GetWord("TxtPage");
		case 2 : return JrApplicationOption.GetWord("TxtOrdreCases");
		case 3 : return JrApplicationOption.GetWord("TxtDessin");
		}
		return " ";
    }

    public Class getColumnClass(int c) {
        return Integer.class;
    }
    
    public JrFilterGadget getFilter(int row) {
    	return (JrFilterGadget)(filters.get(row));
    }
    
	/* (non-Javadoc)
	 * @see javax.swing.table.TableModel#isCellEditable(int, int)
	 */
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return active;
	}


	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
