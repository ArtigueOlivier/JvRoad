/*
 * Created on Dec 15, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import tools.files.JrFileFilter;
import tools.files.JrFileUtil;
import application.JrApplicationOption;
import book.JrBiblio;
import book.JrBook;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class JrActionMenu extends AbstractAction {
	static private JrBiblio biblio = null;
	static private JFrame mainFrame = null;
	
	private String codeText = "";
	private String codeHelp = "";
	
	public JrActionMenu(String txt,int mnemo,int acckey,int accmask,String hlp,String img) {
		super(GetWord(txt));
		putValue(AbstractAction.MNEMONIC_KEY,new Integer(mnemo));
		putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(acckey, accmask));
		String shlp = GetWord(hlp);
		putValue(AbstractAction.LONG_DESCRIPTION,shlp);
		putValue(AbstractAction.SHORT_DESCRIPTION,shlp);
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource(img));
		putValue(AbstractAction.SMALL_ICON,image);
		codeText = txt;
		codeHelp = hlp;
	}
	public JrActionMenu(String txt,int mnemo,int acckey,int accmask,String hlp) {
		super(GetWord(txt));
		putValue(AbstractAction.MNEMONIC_KEY,new Integer(mnemo));
		putValue(AbstractAction.ACCELERATOR_KEY,KeyStroke.getKeyStroke(acckey, accmask));					
		String shlp = GetWord(hlp);
		putValue(AbstractAction.LONG_DESCRIPTION,shlp);
		putValue(AbstractAction.SHORT_DESCRIPTION,shlp);
		codeText = txt;
		codeHelp = hlp;
	}
	public JrActionMenu(String txt,int mnemo,String hlp,String img) {
		super(GetWord(txt));
		putValue(AbstractAction.MNEMONIC_KEY,new Integer(mnemo));					
		String shlp = GetWord(hlp);
		putValue(AbstractAction.LONG_DESCRIPTION,shlp);
		putValue(AbstractAction.SHORT_DESCRIPTION,shlp);
		URL url = getClass().getClassLoader().getResource(img);
		if (url == null) {
			File file = new File(img);
			if (file.exists() == false) {
				System.out.println("Can't find image file: " + img);
			}
			try {
				url = file.toURI().toURL();
			} catch (MalformedURLException e) {
			}
		}
		ImageIcon image = new ImageIcon(url);
		putValue(AbstractAction.SMALL_ICON,image);
		codeText = txt;
		codeHelp = hlp;
	}	
	public JrActionMenu(String txt,int mnemo,String hlp) {
		super(GetWord(txt));
		putValue(AbstractAction.MNEMONIC_KEY,new Integer(mnemo));					
		String shlp = GetWord(hlp);
		putValue(AbstractAction.LONG_DESCRIPTION,shlp);
		putValue(AbstractAction.SHORT_DESCRIPTION,shlp);
		codeText = txt;
		codeHelp = hlp;
	}	
	
	public boolean computeEnableAction(JrBook book) {
		return (book == null)? false : true;
	}
	
	public void refreshEnable() {
		boolean res = computeEnableAction(biblio.getCurrentBook());
		if (res != isEnabled())
			setEnabled(res);
	}
	
	public void refreshAllActions() {
		JrActionCenter.GetActionMenuCenter().refreshEnableActions();
	}

	public void refreshLanguage() {
		String shlp = GetWord(codeHelp);
		putValue(AbstractAction.LONG_DESCRIPTION,shlp);
		putValue(AbstractAction.SHORT_DESCRIPTION,shlp);
		shlp = GetWord(codeText);
		putValue(AbstractAction.NAME,shlp);
	}
	
	public void refreshAllActionsText() {
		JrActionCenter.GetActionMenuCenter().refreshTextActions();		
	}
	
	public JrBook getCurrentBook() {
		return biblio.getCurrentBook();
	}
	
	public String getAFilename() {
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter("Road book (JRoad: *.jrb)","jrb"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showSaveDialog(GetMainFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            String filename = fil.toString();
            String ext = JrFileUtil.GetExtension(filename);
            if ((ext != null) && (ext.compareTo("jrb") == 0))
            	return filename;
           	return filename + ".jrb";
        }
		return null;
	}
	
	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
	
	static public void SetBiblio(JrBiblio bib) {
		biblio = bib;
	}
	
	static public JrBiblio GetBiblio() {
		return biblio;
	}
	
	static public void SetMainFrame(JFrame frame) {
		mainFrame = frame;
	}
	
	static public JFrame GetMainFrame() {
		return mainFrame;
	}
}
