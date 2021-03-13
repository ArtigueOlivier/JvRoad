/*
 * Created on Feb 21, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.panels;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.border.EtchedBorder;
import javax.swing.table.TableColumn;

import names.JrFontSize;

import book.JrBook;

import application.dialogs.gadgets.JrPropertiesTable;
import application.dialogs.gadgets.JrPropertiesTableModel;
import application.dialogs.gadgets.JrProperty;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrIdentityPanel extends JrPanel {
	private static String[] cbnTxtSens  = null;
	private static String[] cbnFontSize = null;
	private static String[] cbnScalePath = null;
	private static String[] cbnAutoParaph = null;
	private static String[] cbnMeters = null;
	private JrPropertiesTableModel modelProp;
	private JrPropertiesTable tableProp;
	
	//---- Identity data
	private String titleBook = new String();
	private String copyrightBook = new String();
	//---- Options data
	private boolean showIndex= false;
	private boolean hautToBas = true;
	private int casePerColumn = 5;
	private int fontSize = JrFontSize.FONT_NORMAL_SIZE;
	private int scalePath = 1; 
	private int autoParaph;
	private int displayMeters = 0;
	
	public JrIdentityPanel(JrBook bk) {	
		super(bk,new BorderLayout());
		if (cbnTxtSens == null) {
			cbnTxtSens = new String [2];
			cbnTxtSens[0] = GetWord("TxtBasVersHaut");
			cbnTxtSens[1] = GetWord("TxtHautVersBas");
		}
		if (cbnFontSize == null) {
			cbnFontSize = new String [3];
			cbnFontSize[0] = GetWord("TxtPetite");
			cbnFontSize[1] = GetWord("TxtNormale");
			cbnFontSize[2] = GetWord("TxtGrande");
		}
		if (cbnScalePath == null) {
			cbnScalePath = new String [7];
			cbnScalePath[0] = GetWord("90%");
			cbnScalePath[1] = GetWord("100%");
			cbnScalePath[2] = GetWord("120%");
			cbnScalePath[3] = GetWord("140%");
			cbnScalePath[4] = GetWord("160%");
			cbnScalePath[5] = GetWord("180%");
			cbnScalePath[6] = GetWord("200%");
		}
		if (cbnAutoParaph == null) {
			cbnAutoParaph = new String [2];
			cbnAutoParaph[0] = GetWord("TxtParaphBasic");
			cbnAutoParaph[1] = GetWord("TxtParaphAuto");
		}
		if (cbnMeters == null) {
			cbnMeters = new String [3];
			cbnMeters[0] = GetWord("TxtKm");
			cbnMeters[1] = GetWord("TxtM");
			cbnMeters[2] = GetWord("TxtPartielMTotalKm");
		}
		
		if (book != null) {
			titleBook = book.getTitle();
			copyrightBook = book.getCopyright();
			showIndex = book.isShowIndex();
			hautToBas = book.isHautVersBas();
			casePerColumn = book.getCasePerColumn();
			fontSize = book.getFontSize();
			autoParaph = (book.isAutoParaph() == true)? 1 : 0;
			displayMeters = book.getDisplayMeters();
			switch(book.getScalePath()) {
			case 90 : scalePath = 0; break;
			case 120: scalePath = 2; break;
			case 140: scalePath = 3; break;
			case 160: scalePath = 4; break;
			case 180: scalePath = 5; break;
			case 200: scalePath = 6; break;
			default : scalePath = 1; break;
			}
		}
		modelProp = new JrPropertiesTableModel(true);
		modelProp.defineProperty("Identity","TxtIdentite");
		modelProp.defineProperty("Titre","TxtTitre",titleBook,40,"",false);
		modelProp.defineProperty("Copyright","TxtCopyright",copyrightBook,40,"",false);
		modelProp.defineProperty("MiseEnPage","TxtMiseEnPage");
		modelProp.defineProperty("NumCase","TxtNumeroCases",showIndex,"",false);
		modelProp.defineProperty("Sens","TxtSensLecture",((hautToBas)? 1 : 0),
				cbnTxtSens,"",false);
		modelProp.defineProperty("CasesCol","TxtCasesColonne",casePerColumn,1,10,1,"",false);
		modelProp.defineProperty("FontSize","TxtTailleFont",fontSize,cbnFontSize,"",false);
		modelProp.defineProperty("ScalePath","TxtScale",scalePath,cbnScalePath,"",false);
		modelProp.defineProperty("AutoParaph","TxtAutoParaph",autoParaph,cbnAutoParaph,"",false);
		modelProp.defineProperty("Meters","TxtAffichageDistance",displayMeters,cbnMeters,"",false);
		
		tableProp = new JrPropertiesTable(modelProp);
		tableProp.setBorder(new EtchedBorder(2));
		TableColumn column = null;
		column = tableProp.getColumnModel().getColumn(0);
		column.setPreferredWidth(120);
		column.setMinWidth(120);
		column.setMaxWidth(120);
		column.setResizable(false);
		column = tableProp.getColumnModel().getColumn(2);
		column.setPreferredWidth(5);
		column.setMinWidth(5);
		column.setMaxWidth(5);
		column.setResizable(false);
		tableProp.setPreferredSize(new Dimension(320,360));		
		
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		add(tableProp,BorderLayout.CENTER);
	}
	
	public boolean validatePage() {		
		JrProperty prop = modelProp.getProperty(1);
		titleBook = prop.getStringValue();
		prop = modelProp.getProperty(2);
		copyrightBook = prop.getStringValue();
		prop = modelProp.getProperty(4);
		showIndex = prop.getBooleanValue();
		prop = modelProp.getProperty(5);
		hautToBas = prop.getBooleanValue();
		prop = modelProp.getProperty(6);
		casePerColumn = prop.getIntegerValue();
		prop = modelProp.getProperty(7);
		fontSize = prop.getIntegerValue();
		prop = modelProp.getProperty(8);
		scalePath = prop.getIntegerValue();
		prop = modelProp.getProperty(9);
		autoParaph = prop.getIntegerValue();
		prop = modelProp.getProperty(10);
		displayMeters = prop.getIntegerValue();
		if ((titleBook.length() < 1) || (copyrightBook.length() < 1))
			return false;
		if (book != null) {
			book.setTitle(titleBook);
			book.setCopyright(copyrightBook);
			book.setShowIndex(showIndex);
			book.setHautVersBas(hautToBas);
			book.setCasePerColumn(casePerColumn);
			book.setFontSize(fontSize);
			book.setAutoParaph((autoParaph > 0)? true : false);
			book.setDisplayMeters(displayMeters);
			switch(scalePath) {
			case 0 : book.setScalePath(90); break;
			case 2 : book.setScalePath(120); break;
			case 3 : book.setScalePath(140); break;
			case 4 : book.setScalePath(160); break;
			case 5 : book.setScalePath(180); break;
			case 6 : book.setScalePath(200); break;
			default: book.setScalePath(100); break;
			}
		}
		return true;
	}	
}
