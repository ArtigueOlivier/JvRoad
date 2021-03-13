/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import tools.files.JrFileRead;
import tools.files.JrFileFilter;

import application.actions.JrActionMenu;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookOpen extends JrActionMenu {
	public JrActionBookOpen() {
		super("MenuLivreOuvrir",KeyEvent.VK_O,
				KeyEvent.VK_F3,0,"MenuLivreOuvrirHlp","images/open.gif");
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter("Road book (Road et Road 32: *.liv)","liv"));		
		fc.addChoosableFileFilter(new JrFileFilter("Road book (Road 3000: *.boo)","boo"));
		fc.addChoosableFileFilter(new JrFileFilter("Road book (JRoad: *.jrb)","jrb"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(GetMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            JrFileRead file = new JrFileRead(fil.toString());
            try {
            	file.read();
            	GetBiblio().openBook(file);
    			refreshAllActions();

            } catch (IOException e) {
            	System.out.println(GetWord("ErrEchecLecture"));
            }
		}
	}

	public boolean computeEnableAction(JrBook book) {
		return true;
	}
}
