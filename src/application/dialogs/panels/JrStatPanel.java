/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;

import book.JrBook;

import application.dialogs.util.JrStatCellModel;
import application.dialogs.util.JrStatModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrStatPanel extends JrPanel {
	
	public JrStatPanel(JrBook bk) {
		super(bk,new BorderLayout());
		JrStatModel model = new JrStatModel(bk);
	
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultRenderer(String.class, new JrStatCellModel(model));
		table.setShowGrid(false);
		table.getTableHeader().setVisible(false);
		table.setBorder(new EtchedBorder(2));
		TableColumn column = null;
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(50);
		column.setMinWidth(50);
		column.setMaxWidth(50);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(40);
		column.setMinWidth(40);
		column.setMaxWidth(40);
		column.setResizable(false);
		
		table.setPreferredSize(new Dimension(320,360));
		model.fireTableDataChanged();

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(table,BorderLayout.CENTER);
	}

}
