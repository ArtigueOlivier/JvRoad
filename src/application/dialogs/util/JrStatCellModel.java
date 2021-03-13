/*
 * Created on 11 févr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import application.JrApplicationOption;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrStatCellModel extends JLabel implements TableCellRenderer {
	private JrStatModel model;
	public JrStatCellModel(JrStatModel m) {
        setOpaque(true); //MUST do this for background to show up.
        model = m;
    }

	
	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	public Component getTableCellRendererComponent(JTable table, Object arg1, 			
		boolean isSelected, boolean hasFocus, int row, int col) {
		JrStatEntry entry = model.getEntry(row);
		String str = JrApplicationOption.GetWord((String)arg1);		
		JLabel label = new JLabel(" " + str);
		boolean title = ((col == 0) && (entry.isSection() == true))? true : false;
		label.setForeground((title)? Color.BLUE : Color.BLACK);
		if (col == 1)
			label.setHorizontalAlignment(JLabel.RIGHT);
		return label;
	}
}
