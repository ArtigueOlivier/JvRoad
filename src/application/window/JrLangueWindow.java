/*
 * Created on Mar 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import tools.files.JrFileFilter;
import tools.files.JrFileUtil;

import application.JrApplicationOption;
import application.actions.tools.JrActionLangue;
import application.window.util.JrTableLangModel;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrLangueWindow extends JFrame implements MouseListener{
	private JrLangEditWindow editWindow = null;
	private JrLangContentWindow contentWindow = null;
	private JrActionLangue actLang[] = new JrActionLangue [JrActionLangue.ACTION_COUNT];
	private JTextField fileField = new JTextField();
	
	JrTableLangModel model = new JrTableLangModel();
	public JrLangueWindow() {
		super("JvRoad - " + JrApplicationOption.GetWord("TxtLangues"));
		
		ImageIcon img = new ImageIcon(getClass().getClassLoader().getResource("images/logo.gif"));
		setIconImage(img.getImage());
		
	    try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (Exception e) { }
	    
	    fileField.setEditable(false);
	    fileField.setBackground(Color.WHITE);
	    Dimension siz = fileField.getMaximumSize(); 
	    siz.height = 18;
	    fileField.setMaximumSize(siz);
	    
	    JToolBar bar = createToolbar();
	    bar.add(new JLabel("    " + JrApplicationOption.GetWord("TxtFichier") + ":  "));	    
	    bar.add(fileField);
	    bar.setFloatable(false);
	    
	    getContentPane().add(bar, BorderLayout.PAGE_START);		
	    initializeContainers();
		setSize(600,600);
		refreshEnableActions();
	}

	public JToolBar createToolbar() {
		JToolBar toolBar = new JToolBar();
		actLang[0] = JrActionLangue.CreateAction(this,JrActionLangue.ACTION_NEW); 
		actLang[1] = JrActionLangue.CreateAction(this,JrActionLangue.ACTION_OPEN); 
		actLang[2] = JrActionLangue.CreateAction(this,JrActionLangue.ACTION_SAVE); 
		actLang[3] = JrActionLangue.CreateAction(this,JrActionLangue.ACTION_SAVEAS); 
		toolBar.add(actLang[0]);
		toolBar.add(actLang[1]);
		toolBar.add(actLang[2]);
		toolBar.add(actLang[3]);
		return toolBar;
	}

	public void refreshEnableActions() {
		if (actLang[0] != null) {
			for(int i = 0; i < JrActionLangue.ACTION_COUNT; i++)
				actLang[i].refreshEnable();
		}
	}
	
	public void initializeContainers() {
		Container cont = getContentPane();
		contentWindow = new JrLangContentWindow(model,this);
		editWindow = new JrLangEditWindow(model);
		JPanel panel = new JPanel(new BorderLayout(5,5));
		panel.add(editWindow,BorderLayout.PAGE_START);
		panel.add(contentWindow,BorderLayout.CENTER);
		getContentPane().add(panel);		
	}
	
	public void execute(int action) {	
		switch(action) {
		case JrActionLangue.ACTION_NEW :
			contentWindow.newFile(JrApplicationOption.GetLanguageFile(),false);
			editWindow.clear();
			fileField.setText(JrApplicationOption.GetWord("TxtPasdenom"));
			break;
		case JrActionLangue.ACTION_OPEN :
			open();
			break;
		case JrActionLangue.ACTION_SAVE :
			if (save()) 
				break;
		case JrActionLangue.ACTION_SAVEAS :
			saveAs();
			break;
		}
	}
	
	public void open() {
		String dico = JrApplicationOption.GetWord("TxtDictionnaire");
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter(dico + " (*.lg)","lg"));		
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
			contentWindow.newFile(fil.toString(),true);
			editWindow.clear();
			fileField.setText(fil.toString());
		}		
	}
	
	public boolean save() {
		String str = model.getFilename();
		if (str.length() < 3)
			return false;
		model.save();
		return true;
	}
	
	public void saveAs() {
		String dico = JrApplicationOption.GetWord("TxtDictionnaire");
		JFileChooser fc = new JFileChooser();
		fc.addChoosableFileFilter(new JrFileFilter(dico + " (*.lg)","lg"));
		fc.setAcceptAllFileFilterUsed(false);
		int returnVal = fc.showSaveDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File fil = fc.getSelectedFile();
            String filename = fil.toString();
            String ext = JrFileUtil.GetExtension(filename);
            if ((ext != null) && (ext.compareTo("lg") == 0))
            	model.setFilename(filename,false,true);
            else
            	model.setFilename(filename + ".lg",false,true);
            model.save();
            fileField.setText(model.getFilename());
        }
	}
	
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getClickCount() == 2) {
			editWindow.select(contentWindow.getSelectedItem());
		}
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
	}

	public void mouseReleased(MouseEvent arg0) {
	}
	
}
