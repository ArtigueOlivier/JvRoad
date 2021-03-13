/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions.book;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import book.JrBookPreview;

import application.JrApplicationOption;
import application.actions.JrActionMenu;
import application.dialogs.JrPreviewDialog;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionBookPrintPreview extends JrActionMenu {
	public JrActionBookPrintPreview() {
		super("MenuLivrePreview",KeyEvent.VK_A,"MenuLivrePreviewHlp",
				"images/preview.gif");
	}
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent arg0) {
		JrPreviewDialog dlg = new JrPreviewDialog(GetMainFrame(),
				JrApplicationOption.GetWord("TxtApercu"),
				new JrBookPreview(getCurrentBook()));
		dlg.setModal(true);
		dlg.show();
		dlg.disconnectAll();
	}
}
