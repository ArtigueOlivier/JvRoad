/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.edition;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import tools.files.JrFileFilter;
import tools.files.JrFileRead;

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionEditionInsertBook extends JrActionMenu {
	public JrActionEditionInsertBook() {
		super("MenuEditInsererBook",KeyEvent.VK_L,"MenuEditInsererBookHlp");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrBook book = getCurrentBook();
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter("Road book (JRoad: *.jrb)","jrb"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(GetMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            JrFileRead file = new JrFileRead(fil.toString());
            try {
            	file.read();
            	book.insertBook(file,book.getCurrentCaseIndex());
    			refreshAllActions();

            } catch (IOException e) {
            	System.out.println(GetWord("ErrEchecLecture"));
            }
		}
	}
	
	public boolean computeEnableAction(JrBook book) {
		if (super.computeEnableAction(book) == true) {
			if (JrApplicationOption.GetCode() != 129834765)
				return false;
			return (book.getCaseCount() < book.getMaximumCase())? true : false;
		}
		return false;
	}
}
