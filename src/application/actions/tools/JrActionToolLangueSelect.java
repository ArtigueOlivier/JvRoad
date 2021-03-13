/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.tools;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import tools.files.JrFileFilter;
import tools.files.JrFileSave;

import book.JrBook;

import application.JrApplicationOption;
import application.actions.JrActionMenu;


/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionToolLangueSelect extends JrActionMenu {
	public JrActionToolLangueSelect() {
		super("MenuOutilsLangSel",KeyEvent.VK_S,"MenuOutilsLangSelHlp",
				"images/langsel.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter(GetWord("TxtDictionnaire") + " (*.lg)","lg"));		
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(GetMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            JrFileSave fichier = new JrFileSave("jvroad.opt"); 
            fichier.addEntry("Langage",fil.toString());
            fichier.close();
            String s1 = GetWord("TxtRedemarrer1");
			String s2 = GetWord("TxtRedemarrer2");
            JOptionPane.showMessageDialog(GetMainFrame(),s1 + ",\n" + s2); 
		}		
	}

	public boolean computeEnableAction(JrBook book) {
		return (JrApplicationOption.GetCode() != 129834765)? false : true;
	}
}
