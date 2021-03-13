/*
 * Created on Mar 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.event.MouseListener;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;

import application.window.util.JrTableLangModel;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrLangContentWindow extends JScrollPane {
	private JTable table = null;
	private JrTableLangModel model = null;
	
	public JrLangContentWindow(JrTableLangModel mdl, MouseListener ms) {
		model = mdl;
		table = new JTable(model);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.addMouseListener(ms);
		//table.setShowGrid(false);

		TableColumn column = null;
		column = table.getColumnModel().getColumn(0);
		column.setWidth(200);
		column.setMaxWidth(200);
		column.setPreferredWidth(200);
		column.setResizable(false);
		
		setViewportView(table);
	}

	public void newFile(String filename,boolean keep) {
		model.setFilename(filename,true,keep);
		model.fireTableDataChanged();		
	}
	
	public int getSelectedItem() {
		return table.getSelectedRow();
	}
	
}
