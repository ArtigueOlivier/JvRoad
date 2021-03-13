/*
 * Created on Mar 22, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import application.JrApplicationOption;
import application.dialogs.gadgets.JrTextfield;
import application.window.util.JrLangItem;
import application.window.util.JrTableLangModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrLangEditWindow extends JPanel implements ActionListener{
	private JrTextfield fromTxt = new JrTextfield(80);
	private JrTextfield toTxt = new JrTextfield(80);
	private JButton btn = null;
	private JrTableLangModel model = null;
	private int itemSelected = -1;
	public JrLangEditWindow(JrTableLangModel mdl) {	
		super(new BorderLayout());
		setBorder(new EtchedBorder(2));
		model = mdl;
		btn = new JButton(JrApplicationOption.GetWord("TxtValider"));
		btn.setToolTipText(JrApplicationOption.GetWord("TxtValiderModifDico"));
		btn.setActionCommand("Validate");
		btn.addActionListener(this);
		fromTxt.setEditable(false);
		
		JPanel panel1 = new JPanel(new GridLayout(2,1));
		panel1.add(new JLabel(" " + JrApplicationOption.GetWord("TxtRemplacer") + ":"));
		panel1.add(new JLabel(" " + JrApplicationOption.GetWord("TxtPar") + ":"));

		JPanel panel2 = new JPanel(new GridLayout(2,1));
		panel2.add(fromTxt);
		panel2.add(toTxt);
		
		add(panel1,BorderLayout.WEST);
		add(panel2,BorderLayout.CENTER);
		add(btn,BorderLayout.EAST);
	}
	
	public void clear() {
		fromTxt.setText("");
		toTxt.setText("");	
		itemSelected = -1;
	}
	
	public void select(int index) {
		if (index >= 0) {
			JrLangItem item = model.getItem(index);
			if (item != null) {
				fromTxt.setText(item.getValue());
				toTxt.setText(item.getValue());
				itemSelected = index;
			}
			else {
				clear();
			}
		}
		else
			clear();
	}
	
	public void validate() {
		if (itemSelected != -1) {
			JrLangItem item = model.getItem(itemSelected);
			if (item != null) {
				item.setValue(toTxt.getText());
				model.fireTableRowsUpdated(itemSelected,itemSelected);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
    	if ("Validate".equals(e.getActionCommand())) 
    		validate();		
	}
}
