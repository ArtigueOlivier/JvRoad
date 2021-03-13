/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.TableColumn;

import book.JrBook;

import application.dialogs.gadgets.JrFilterGadget;
import application.dialogs.gadgets.JrFilterTable;
import application.dialogs.gadgets.JrFilterTableModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFilterPanel extends JrPanel implements ActionListener{
	private Vector filters = new Vector();
	private JrFilterTable table = null;
	private JrFilterTableModel model = null;
	
	public JrFilterPanel(JrBook bk) {
		super(bk,new BorderLayout());
	
		for(int i = 0; i < bk.getFilterCount(); i++)
			filters.add(new JrFilterGadget(bk.getFilter(i)));
		
		JButton btn1 = new JButton(new ImageIcon("images/add.gif"));
		btn1.setMnemonic(KeyEvent.VK_A);
	    btn1.setActionCommand("Ajouter");
	    btn1.setToolTipText(GetWord("TxtAjouterFiltre"));
		JButton btn3 = new JButton(new ImageIcon("images/del.gif"));
		btn3.setMnemonic(KeyEvent.VK_S);
	    btn3.setActionCommand("Supprimer");
	    btn3.setToolTipText(GetWord("TxtSupprimerFiltre"));
	    
	    JPanel bar = new JPanel();
	    bar.add(btn1);
	    bar.add(btn3);
	    
	    btn1.addActionListener(this);
	    btn3.addActionListener(this);
	    
		model = new JrFilterTableModel(filters);
	
		table = new JrFilterTable(model);
		TableColumn column = null;
		column = table.getColumnModel().getColumn(0);
		column.setPreferredWidth(25);
		column.setMinWidth(25);
		column.setMaxWidth(25);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(1);
		column.setPreferredWidth(50);
		column.setMinWidth(50);
		column.setMaxWidth(50);
		column.setResizable(false);
		column = table.getColumnModel().getColumn(2);
		column.setPreferredWidth(100);
		column.setMinWidth(100);
		column.setMaxWidth(100);
		column.setResizable(false);
				
		JScrollPane sc = new JScrollPane();
		sc.setViewportView(table);
		sc.getViewport().setBackground(Color.WHITE);	
		sc.getViewport().setPreferredSize(new Dimension(350,360));
		
		model.fireTableDataChanged();

		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(bar,BorderLayout.PAGE_END);
		add(sc,BorderLayout.CENTER);
		
	}
	public boolean validatePage() {
		book.setFilters(filters);
		return true;
	}
	
	public void actionPerformed(ActionEvent e) {
	    if ("Ajouter".equals(e.getActionCommand())) {
	    	filters.add(new JrFilterGadget());
	    	table.invalidate();
	    	model.fireTableDataChanged();
	    } else {
		    if ("Supprimer".equals(e.getActionCommand())) {
		    	JrFilterGadget fil;
		    	boolean refresh = false;
	    		table.stopCellEditing();
		    	for(int i = filters.size() - 1; i >= 0; i--) {		    		
		    		fil = (JrFilterGadget)(filters.get(i));
		    		if ((fil != null) && fil.isSelected()) {
		    			filters.remove(fil);
		    			refresh = true;
		    		}
		    	}
			    if (refresh)
			    	model.fireTableDataChanged();
		    }
	    }
	} 

}
