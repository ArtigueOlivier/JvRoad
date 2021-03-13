/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.gadgets;

import java.awt.Component;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterRenderer extends AbstractCellEditor implements TableCellEditor,TableCellRenderer {
	private JrFilterSelectEditor selEditor = null;
	private JrFilterPageEditor pageEditor = null;
	private JrFilterOrderEditor orderEditor = null;
	private JrFilterDrawEditor drawEditor = null;
		
	public JrFilterRenderer() {
		super();
		
		selEditor = new JrFilterSelectEditor();
		pageEditor = new JrFilterPageEditor();
		orderEditor = new JrFilterOrderEditor();
		drawEditor = new JrFilterDrawEditor();
	}
		
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellEditor#getTableCellEditorComponent(javax.swing.JTable, java.lang.Object, boolean, int, int)
	 */
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean selected, int rowno, int colno) {
		JrFilterGadget filter = (JrFilterGadget)value;
		Component cmp;
		switch (colno) {
		case 1 : cmp = pageEditor; break;
		case 2 : cmp = orderEditor; break;
		case 3 : cmp = drawEditor; break;
		default: cmp = selEditor; break;
		}
		cmp.setFocusable(true);
		((JrFilterComponent)cmp).connectFilter(filter);
		
		return cmp;
	}

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component cmp = getTableCellEditorComponent(table,value,isSelected,row,column);
		cmp.setFocusable(false);
		return cmp;
	}

	/* (non-Javadoc)
	 * @see javax.swing.CellEditor#getCellEditorValue()
	 */
	public Object getCellEditorValue() {
		return null;
	}	
}
