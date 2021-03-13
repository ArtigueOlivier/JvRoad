/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.tools;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import book.JrBook;
import book.JrInversionReport;

import application.JrApplicationOption;
import application.actions.JrActionMenu;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionToolInverse extends JrActionMenu {
	public JrActionToolInverse() {
		super("MenuOutilsInverse",KeyEvent.VK_I,"MenuOutilsInverseHlp","images/inverse.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		//JrBookDialog dlg = new JrBookDialog(GetMainFrame(),book,1);
		if (book != null) {
			JrInversionReport report = book.inverseCases();

			String msg = GetWord("Rapport") + "\n\n";
			if (report.hasRaz() == true) 
				msg = msg.concat("-" + GetWord("TxtInversionRaz") + "\n");
	
			if (report.hasCapMap() == true) 
				msg = msg.concat("-" + GetWord("TxtInversionCap") + "\n");
	
			if (report.hasImgMap() == true) 
				msg = msg.concat("-" + GetWord("TxtInversionImg") + "\n");
			 msg = msg.concat("\n" + GetWord("TxtInversionFait"));
			
			JOptionPane.showMessageDialog(GetMainFrame(),msg,GetWord("TxtInversionRoad"),
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			if (JrApplicationOption.GetCode() != 129834765)
				return false;
			return true;
		}
		return false;
	}

}
