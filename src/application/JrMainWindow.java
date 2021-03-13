package application;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import book.JrBiblio;

import application.actions.*;
import application.window.JrBiblioWindow;
import application.window.JrCheckWindow;
import application.window.JrNavigatorWindow;

/*
 * Created on Dec 14, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrMainWindow extends JFrame implements WindowListener {
	private JrBiblio biblio = new JrBiblio();
	
	private JSplitPane splitPane2;
	/**
	 * @param string
	 */
	public JrMainWindow(String string) {
		super(string);
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("images/logo.gif"));
		setIconImage(img.getImage());
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		JrActionMenu.SetBiblio(biblio);
		JrActionMenu.SetMainFrame(this);
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) { }
		initializeMenu();
		initializeContainers();
		addWindowListener(this);
	}
		
	private void initializeMenu() {
		JrActionCenter mkAction = JrActionCenter.GetActionMenuCenter(); 

		setJMenuBar(mkAction.createMainMenu());
        getContentPane().add(mkAction.createMainToolBar(), BorderLayout.PAGE_START);
        mkAction.refreshEnableActions();
	}

	public void initializeContainers() {
		Container cont = getContentPane();
				
		JrNavigatorWindow navigatorList = new JrNavigatorWindow(biblio);
		JrBiblioWindow editorList = new JrBiblioWindow(biblio);
		if (JrApplicationOption.IsReaderMode() < 1) {
			JrCheckWindow errorList = new JrCheckWindow(biblio);
			
	        //Create a split pane with the two scroll panes in it.
			JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,editorList,errorList);
	        splitPane.setOneTouchExpandable(true);
	        splitPane.setDividerLocation(430);
	        splitPane.setResizeWeight(1);
	        
	        splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,splitPane,navigatorList);
	        splitPane2.setOneTouchExpandable(true);
	        splitPane2.setDividerLocation(600);
	        splitPane2.setResizeWeight(1);
	        splitPane2.setPreferredSize(new Dimension(600, 200));
	        getContentPane().add(splitPane2);
		}
		else {
	        splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,editorList,navigatorList);
	        splitPane2.setOneTouchExpandable(true);
	        splitPane2.setDividerLocation(600);
	        splitPane2.setResizeWeight(1);
	        splitPane2.setPreferredSize(new Dimension(600, 200));
	        getContentPane().add(splitPane2);			
		}        
	}

	public void windowActivated(WindowEvent arg0) {
	}

	public void windowClosed(WindowEvent arg0) {
	}

	public void windowClosing(WindowEvent arg0) {
		if (biblio.hasModifiedBook()) {
			String msg = JrApplicationOption.GetWord("TxtVousPasSauverModif") + "\n" + JrApplicationOption.GetWord("TxtAnnulerModifications");
			if (JOptionPane.showOptionDialog(JrActionMenu.GetMainFrame(),
				    msg,JrApplicationOption.GetWord("TxtDemandeConfirm"),
					JOptionPane.YES_NO_OPTION,
				    JOptionPane.QUESTION_MESSAGE, null, null, null) == JOptionPane.NO_OPTION)
					return;
			
		}
		dispose();
	}

	public void windowDeactivated(WindowEvent arg0) {
	}

	public void windowDeiconified(WindowEvent arg0) {
	}

	public void windowIconified(WindowEvent arg0) {
	}

	public void windowOpened(WindowEvent arg0) {
	}
}
