/*
 * Created on Dec 18, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.actions;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;

import book.map.objets.JrObjects;
import names.*;
import application.JrApplicationOption;
import application.actions.book.*;
import application.actions.data.*;
import application.actions.display.*;
import application.actions.drawing.*;
import application.actions.edition.*;
import application.actions.help.*;
import application.actions.orientation.*;
import application.actions.position.*;
import application.actions.tools.*;
import application.dialogs.JrPreview;
import application.window.JrNavigDrawWindow;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrActionCenter {
	private static JrActionCenter stActionCenter = new JrActionCenter();
	private JrActionBookNew actBookNew = new JrActionBookNew();
	private JrActionBookOpen actBookOpen = new JrActionBookOpen();
	private JrActionBookClose actBookClose = new JrActionBookClose();
	private JrActionBookSave actBookSave = new JrActionBookSave();
	private JrActionBookSaveAs actBookSaveAs = new JrActionBookSaveAs();
	private JrActionBookPrintPreview actBookPrintPreview = new JrActionBookPrintPreview();
	private JrActionBookPrintMarge actBookPrintMarge = new JrActionBookPrintMarge();
	private JrActionBookPrint actBookPrint = new JrActionBookPrint();
	private JrActionBookPrintLegend actBookPrintLegend = new JrActionBookPrintLegend();
	private JrActionBookPrintWhite actBookPrintWhite = new JrActionBookPrintWhite();
	private JrActionBookQuit actBookQuit = new JrActionBookQuit();
	private JrActionEditionPaste actEditionPaste = new JrActionEditionPaste();
	private JrActionEditionPasteInsert actEditionPasteIns = new JrActionEditionPasteInsert();
	private JrActionEditionCopyCase actEditionCopyCase = new JrActionEditionCopyCase();
	private JrActionEditionCopyMaps actEditionCopyMaps = new JrActionEditionCopyMaps();
	private JrActionEditionCopyCurrentMap actEditionCopyCurrentMap = new JrActionEditionCopyCurrentMap();
	private JrActionEditionAdd actEditionAdd = new JrActionEditionAdd();
	private JrActionEditionAddBook actEditionAddBook = new JrActionEditionAddBook();
	private JrActionEditionInsert actEditionInsert = new JrActionEditionInsert();
	private JrActionEditionInsertBook actEditionInsertBook = new JrActionEditionInsertBook();
	private JrActionEditionDel actEditionDel = new JrActionEditionDel();
	private JrActionEditionSelectFirst actEditionSelectFirst = new JrActionEditionSelectFirst();
	private JrActionEditionSelectPrevious actEditionSelectPrevious = new JrActionEditionSelectPrevious();
	private JrActionEditionSelectNext actEditionSelectNext = new JrActionEditionSelectNext();
	private JrActionEditionSelectLast actEditionSelectLast = new JrActionEditionSelectLast();
	private JrActionPositionFirst actPositionFirst = new JrActionPositionFirst();
	private JrActionPositionPrevious actPositionPrevious = new JrActionPositionPrevious();
	private JrActionPositionNext actPositionNext = new JrActionPositionNext();
	private JrActionPositionLast actPositionLast = new JrActionPositionLast();
	private JrActionPositionGoto actPositionGoto = new JrActionPositionGoto();
	private JrActionPositionSearch actPositionSearch = new JrActionPositionSearch();
	private JrActionDataDistance actDataDistance = new JrActionDataDistance();
	private JrActionDataComment actDataComment = new JrActionDataComment();
	private JrActionDataSymbols actDataSymbols = new JrActionDataSymbols();
	private JrActionDataRotation actDataRota = new JrActionDataRotation();
	private JrActionDataAlign actDataAlignLeft = JrActionDataAlign.CreateAction(false);
	private JrActionDataAlign actDataAlignCenter = JrActionDataAlign.CreateAction(true);
	private JrActionEditText actEditTxt[] = new JrActionEditText [5];
	private JrActionEditImg actEditImg[] = new JrActionEditImg [JrImgOperatorName.IMG_OPE_COUNT];
	private JrActionDataTitle actDataTitle = new JrActionDataTitle();
	private JrActionDataCopyright actDataCopyright = new JrActionDataCopyright();
	private JrActionToolStat actToolStat = new JrActionToolStat();
	private JrActionToolInverse actToolInv = new JrActionToolInverse();
	private JrActionToolResume actToolResume = new JrActionToolResume();
	private JrActionToolLangueEdit actToolLangEdt = new JrActionToolLangueEdit();
	private JrActionToolLangueSelect actToolLangSel = new JrActionToolLangueSelect();
	private JrActionPageDisplayer actPageDisplayer[] = new JrActionPageDisplayer[JrPageDisplayerName.PAGE_DISPLAYER_COUNT];
	private JrActionDisplayFilter actDispFilter = new JrActionDisplayFilter(); 
	private JrActionDisplayColors actDispColors = new JrActionDisplayColors(); 
	private JrActionHelpAbout actHelpAbout = new JrActionHelpAbout();
	private JrActionHelpDoc actHelpDoc = new JrActionHelpDoc();
	private JrActionHelpLicence actHelpLicence = new JrActionHelpLicence();
	private JrActionMap actMap[] = new JrActionMap[JrMapName.MAP_NAME_COUNT];
	private JrActionMapView[] actMapView = new JrActionMapView[JrViewName.VIEW_COUNT];
	private JrActionTrait actTrait[] = new JrActionTrait[JrTraitName.TRAIT_COUNT];
	private JrActionCap actCap[] = new JrActionCap[24]; 
	private JrActionClock actClock[] = new JrActionClock[12]; 
	private JrActionArc actArc[] = new JrActionArc[JrArcName.ARC_COUNT];
	private JrActionBorne actBorne[] = new JrActionBorne[JrBorneName.BORNE_COUNT];
	private JrActionClipart actClipart[] = new JrActionClipart[JrClipartName.CLIPART_COUNT];
	private JrActionObject actObject[] = new JrActionObject[JrObjName.OBJ_COUNT];
	private JrActionCaseDisplayer actCaseDisplayer[] = new JrActionCaseDisplayer[JrCaseDisplayerName.CASE_DISPLAYER_COUNT];
	private JrActionOptionsDisplayer actOptDisp = new JrActionOptionsDisplayer(); 
	private JrActionVector actVect[] = new JrActionVector [JrVectorOperatorName.VECTOR_OPE_COUNT];
	private JrActionNavigator actNavig[] = new JrActionNavigator [JrGotoName.GOTO_COUNT];
	private JrActionPreview actPreview[] = new JrActionPreview [JrGotoName.GOTO_COUNT];
	private JrActionNavigCopy actNavCopy[] = new JrActionNavigCopy[3];
	private JrActionMenu actLineText[] = new JrActionMenu[JrActionEditLineText.OPE_LINETEXT_COUNT]; 
	private JrActionPont actPont[] = new JrActionPont[JrPontName.PONT_COUNT];
	private JrActionVille actVille[] = new JrActionVille[JrActionVille.OPE_VILLE_COUNT];
	private ArrayList actionArray = new ArrayList();
	
	private JToolBar toolbars[] = new JToolBar [JrPaletteName.PALETTE_COUNT];
	
	private JrActionCenter() {
		int i;
		
		actionArray.add(actBookNew);
		actionArray.add(actBookOpen);
		actionArray.add(actBookClose);
		actionArray.add(actBookSave);
		actionArray.add(actBookSaveAs);
		actionArray.add(actBookPrintPreview);
		actionArray.add(actBookPrintMarge);
		actionArray.add(actBookPrint);
		actionArray.add(actBookPrintLegend);
		actionArray.add(actBookPrintWhite);
		actionArray.add(actBookQuit);
		actionArray.add(actEditionPaste);
		actionArray.add(actEditionPasteIns);
		actionArray.add(actEditionCopyCase);
		actionArray.add(actEditionCopyMaps);
		actionArray.add(actEditionCopyCurrentMap);
		actionArray.add(actEditionAdd);
		actionArray.add(actEditionAddBook);
		actionArray.add(actEditionInsert);
		actionArray.add(actEditionInsertBook);
		actionArray.add(actEditionDel);
		actionArray.add(actEditionSelectFirst);
		actionArray.add(actEditionSelectPrevious);
		actionArray.add(actEditionSelectNext);
		actionArray.add(actEditionSelectLast);
		actionArray.add(actPositionFirst);
		actionArray.add(actPositionPrevious);
		actionArray.add(actPositionNext);
		actionArray.add(actPositionLast);
		actionArray.add(actPositionGoto);
		actionArray.add(actPositionSearch);
		actionArray.add(actDataDistance);
		actionArray.add(actDataComment);
		actionArray.add(actDataSymbols);
		actionArray.add(actDataTitle);
		actionArray.add(actDataCopyright);
		actionArray.add(actDataRota);
		actionArray.add(actToolResume);
		actionArray.add(actToolStat);
		actionArray.add(actToolInv);
		actionArray.add(actToolLangEdt);
		actionArray.add(actToolLangSel);
		actionArray.add(actDispFilter);
		actionArray.add(actDispColors);
		actionArray.add(actOptDisp);
		actionArray.add(actHelpAbout);
		actionArray.add(actHelpDoc);
		actionArray.add(actHelpLicence);
		
		for(i = 0; i < JrActionEditLineText.OPE_LINETEXT_COUNT; i++) {
			actLineText[i] = JrActionEditLineText.CreateAction(i);
			actionArray.add(actLineText[i]);
		}
		
		for(i = 0; i < JrMapName.MAP_NAME_COUNT; i++) {
			actMap[i] = JrActionMap.CreateAction(i);
			actionArray.add(actMap[i]);
		}		
		for(i = 0; i < JrGotoName.GOTO_COUNT; i++) {
			actNavig[i] = null;
			actPreview[i] = null;
		}		
		for(i = 0; i < JrViewName.VIEW_COUNT; i++) {
			actMapView[i] = JrActionMapView.CreateAction(i);
			actionArray.add(actMapView[i]);
		}
		for(i = 0; i < JrTraitName.TRAIT_COUNT; i++) {
			actTrait[i] = JrActionTrait.CreateAction(i);
			actionArray.add(actTrait[i]);
		}
		for(i = 0; i < 24; i++) {
			actCap[i] = new JrActionCap(i * 15);
			actionArray.add(actCap[i]);
		}
		for(i = 0; i < 12; i++) {
			actClock[i] = new JrActionClock(i + 1);
			actionArray.add(actClock[i]);
		}
		for(i = 0; i < JrArcName.ARC_COUNT; i++) {
			actArc[i] = JrActionArc.CreateAction(i);
			actionArray.add(actArc[i]);
		}
		for(i = 0; i < JrBorneName.BORNE_COUNT; i++) {
			actBorne[i] = JrActionBorne.CreateAction(i);
			actionArray.add(actBorne[i]);
		}
		int objCount = JrObjects.getObjectCount();
		actObject = new JrActionObject[objCount];		
		for(i = 0; i < objCount; i++) {
			actObject[i] = JrActionObject.CreateAction(i);
			actionArray.add(actObject[i]);
		}
		for(i = 0; i < JrPontName.PONT_COUNT; i++) {
			actPont[i] = JrActionPont.CreateAction(i);
			actionArray.add(actPont[i]);
		}
		for(i = 0; i < JrActionVille.OPE_VILLE_COUNT; i++) {
			actVille[i] = JrActionVille.CreateAction(i);
			actionArray.add(actVille[i]);
		}
		for(i = 0; i < JrCaseDisplayerName.CASE_DISPLAYER_COUNT; i++) {
			actCaseDisplayer[i] = JrActionCaseDisplayer.CreateAction(i);
			actionArray.add(actCaseDisplayer[i]);
		}
		for(i = 0; i < JrPageDisplayerName.PAGE_DISPLAYER_COUNT; i++) {
			actPageDisplayer[i] = JrActionPageDisplayer.CreateAction(i);
			actionArray.add(actPageDisplayer[i]);
		}
		for(i = 0; i < JrClipartName.CLIPART_COUNT; i++) {
			actClipart[i] = JrActionClipart.CreateAction(i);
			actionArray.add(actClipart[i]);
		}
		for(i = 0; i < JrImgOperatorName.IMG_OPE_COUNT; i++) {
			actEditImg[i] = JrActionEditImg.CreateAction(i);
			actionArray.add(actEditImg[i]);
		}
		for(i = 0; i < JrVectorOperatorName.VECTOR_OPE_COUNT; i++) {
			actVect[i] = JrActionVector.CreateAction(i);
			actionArray.add(actVect[i]);
		}		
		for(i = 0; i < 3; i++) {
			actNavCopy[i] = null;
		}		

		actEditTxt[0] = JrActionEditText.CreateAction(JrActionEditText.OPE_TEXT_EDIT);
		actEditTxt[1] = JrActionEditText.CreateAction(JrActionEditText.OPE_TEXT_DEL);
		actEditTxt[2] = JrActionEditText.CreateAction(JrActionEditText.OPE_TEXT_LEFT);
		actEditTxt[3] = JrActionEditText.CreateAction(JrActionEditText.OPE_TEXT_CENTER);
		actEditTxt[4] = JrActionEditText.CreateAction(JrActionEditText.OPE_TEXT_RIGHT);
		actionArray.add(actEditTxt[0]);
		actionArray.add(actEditTxt[1]);
		actionArray.add(actEditTxt[2]);
		actionArray.add(actEditTxt[3]);
		actionArray.add(actEditTxt[4]);
				
		toolbars[JrPaletteName.PALETTE_CASE] = createToolbarCase(false);
		toolbars[JrPaletteName.PALETTE_TRAITS] = createToolbarTraits(false);
		toolbars[JrPaletteName.PALETTE_TRAITSROTA] = createToolbarTraits(true);
		toolbars[JrPaletteName.PALETTE_MIL] = createToolbarMillePas();
		toolbars[JrPaletteName.PALETTE_ARCS] = createToolbarArcs();
		toolbars[JrPaletteName.PALETTE_VECTOR] = createToolbarVector();
		toolbars[JrPaletteName.PALETTE_CAP] = createToolbarCap();
		toolbars[JrPaletteName.PALETTE_CLOCK] = createToolbarClock();
		toolbars[JrPaletteName.PALETTE_CLIPART] = createToolbarClipart();
		toolbars[JrPaletteName.PALETTE_GIF] = createToolbarGif();
		toolbars[JrPaletteName.PALETTE_OBJECTS] = createToolbarObject();
		toolbars[JrPaletteName.PALETTE_BORNE] = createToolbarBorne();
		toolbars[JrPaletteName.PALETTE_TEXT] = createToolbarText();
		toolbars[JrPaletteName.PALETTE_LINETEXT] = createToolbarLineText();
		toolbars[JrPaletteName.PALETTE_PONTS] = createToolbarPonts();
		toolbars[JrPaletteName.PALETTE_VILLE] = createToolbarVilles();
		toolbars[JrPaletteName.PALETTE_NAVIGATOR] = null;
	}
	
	public static JrActionCenter GetActionMenuCenter() {
		return stActionCenter;
	}

	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
		
	public static void RefreshActions() {
		stActionCenter.refreshEnableActions();
	}
	public static void RefreshActionsTrait() {
		stActionCenter.refreshEnableActionsTrait();
	}
	public static void RefreshActionsBorne() {
		stActionCenter.refreshEnableActionsBorne();
	}
	public static void RefreshActionsArc() {
		stActionCenter.refreshEnableActionsArc();
	}
	public static void RefreshActionsDisplayer() {
		stActionCenter.refreshEnableActionsDisplayer();
	}
	public static void RefreshActionsObject() {
		stActionCenter.refreshEnableActionsObject();
	}
	public static void RefreshActionsPont() {
		stActionCenter.refreshEnableActionsPont();
	}
	public static void RefreshActionsEditImage() {
		stActionCenter.refreshEnableActionsEditImage();
	}
	public static void RefreshActionsVector() {
		stActionCenter.refreshEnableActionsVector();
	}
	public static void RefreshActionsNavigator() {
		stActionCenter.refreshEnableActionsNavigator();
	}
	public static void RefreshActionsPreview() {
		stActionCenter.refreshEnableActionsPreview();
	}
		
	public void refreshEnableActions() {
		ListIterator iter = actionArray.listIterator();
		while(iter.hasNext()) {
			((JrActionMenu)(iter.next())).refreshEnable();
		}				
	}
	public void refreshTextActions() {
		ListIterator iter = actionArray.listIterator();
		while(iter.hasNext()) {
			((JrActionMenu)(iter.next())).refreshLanguage();
		}				
	}
	
	public void refreshEnableActionsTrait() {
		for(int i = 0; i < JrTraitName.TRAIT_COUNT; i++)
			actTrait[i].refreshEnable();
	}
	public void refreshEnableActionsBorne() {
		for(int i = 0; i < JrBorneName.BORNE_COUNT; i++)
			actBorne[i].refreshEnable();
	}
	public void refreshEnableActionsArc() {
		for(int i = 0; i < JrArcName.ARC_COUNT; i++)
			actArc[i].refreshEnable();
	}
	public void refreshEnableActionsDisplayer() {
		int i;
		for(i = 0; i < JrCaseDisplayerName.CASE_DISPLAYER_COUNT; i++)
			actCaseDisplayer[i].refreshEnable();
		for(i = 0; i < JrPageDisplayerName.PAGE_DISPLAYER_COUNT; i++)
			actPageDisplayer[i].refreshEnable();
	}
	public void refreshEnableActionsObject() {
		for(int i = 0; i < actObject.length; i++)
			actObject[i].refreshEnable();
	}
	public void refreshEnableActionsPont() {
		for(int i = 0; i < JrPontName.PONT_COUNT; i++)
			actPont[i].refreshEnable();
	}
	public void refreshEnableActionsEditImage() {
		for(int i = 0; i < JrImgOperatorName.IMG_OPE_COUNT; i++)
			actEditImg[i].refreshEnable();
	}
	public void refreshEnableActionsVector() {
		for(int i = 0; i < JrVectorOperatorName.VECTOR_OPE_COUNT; i++)
			actVect[i].refreshEnable();
	}
	public void refreshEnableActionsNavigator() {
		if (actNavig[0] != null) {
			for(int i = 0; i < JrGotoName.GOTO_COUNT; i++)
				actNavig[i].refreshEnable();
		}
	}
	public void refreshEnableActionsPreview() {
		if (actPreview[0] != null) {
			for(int i = 0; i < JrGotoName.GOTO_COUNT; i++)
				actPreview[i].refreshEnable();
		}
	}
	
	public JMenuItem createMenuItem(JrActionMenu act) {
		JMenuItem item = new JMenuItem(act);
		item.setIcon(null);
		return item;
	}
	
	public static JButton createButton(JrActionMenu act) {
		JButton btn = new JButton(act);
		btn.setText(null);
		return btn;
	}

	public JMenuBar createMainMenu() {
		int i;
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu(GetWord("MenuLivre"));
		menu.setMnemonic(KeyEvent.VK_L);
		menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuLivreHlp"));
		if (JrApplicationOption.IsReaderMode() < 1) {
			menu.add(createMenuItem(actBookNew));
			menu.add(createMenuItem(actBookOpen));
			menu.add(createMenuItem(actBookClose));
			menu.addSeparator();
			menu.add(createMenuItem(actBookSave));
			menu.add(createMenuItem(actBookSaveAs));
		}
		else {
			menu.add(createMenuItem(actBookOpen));
			menu.add(createMenuItem(actBookClose));			
		}
		menu.addSeparator();
		menu.add(createMenuItem(actBookPrintPreview));
		menu.add(createMenuItem(actBookPrintMarge));
		menu.add(createMenuItem(actBookPrint));
		menu.add(createMenuItem(actBookPrintLegend));
		menu.add(createMenuItem(actBookPrintWhite));
		menu.addSeparator();
		menu.add(createMenuItem(actBookQuit));
		menuBar.add(menu);

		//Menu Edition
		if (JrApplicationOption.IsReaderMode() < 1) {
			menu = new JMenu(GetWord("MenuEdit"));
			menu.setMnemonic(KeyEvent.VK_E);
			menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuEditHlp"));
			menu.add(createMenuItem(actEditionPaste));
			menu.add(createMenuItem(actEditionPasteIns));
			menu.addSeparator();
			menu.add(createMenuItem(actEditionCopyCase));
			menu.add(createMenuItem(actEditionCopyMaps));
			menu.add(createMenuItem(actEditionCopyCurrentMap));
			menu.addSeparator();
			menu.add(createMenuItem(actEditionAdd));
			menu.add(createMenuItem(actEditionInsert));
			menu.add(createMenuItem(actEditionDel));
			menu.addSeparator();
			JMenu submenu = new JMenu(GetWord("MenuEditSelect"));
			submenu.setMnemonic(KeyEvent.VK_N);
			submenu.add(createMenuItem(actEditionSelectFirst));
			submenu.add(createMenuItem(actEditionSelectPrevious));
			submenu.add(createMenuItem(actEditionSelectNext));
			submenu.add(createMenuItem(actEditionSelectLast));
			menu.add(submenu);
			menu.addSeparator();
			menu.add(createMenuItem(actEditionAddBook));
			menu.add(createMenuItem(actEditionInsertBook));
			menuBar.add(menu);
		}
		//Menu Position
		menu = new JMenu(GetWord("MenuPos"));
		menu.setMnemonic(KeyEvent.VK_P);
		menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuPosHlp"));
		menu.add(createMenuItem(actPositionFirst));
		menu.add(createMenuItem(actPositionPrevious));
		menu.add(createMenuItem(actPositionNext));
		menu.add(createMenuItem(actPositionLast));
		menu.add(createMenuItem(actPositionGoto));
		menu.add(createMenuItem(actPositionSearch));
		menuBar.add(menu);

		//Menu Donn�es
		if (JrApplicationOption.IsReaderMode() < 1) {
			menu = new JMenu(GetWord("MenuData"));
			menu.setMnemonic(KeyEvent.VK_D);
			menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuDataHlp"));
			menu.add(createMenuItem(actDataDistance));
			menu.add(createMenuItem(actDataComment));
			menu.add(createMenuItem(actDataSymbols));
			menu.addSeparator();
			menu.add(createMenuItem(actDataTitle));
			menu.add(createMenuItem(actDataCopyright));
			menuBar.add(menu);
		}
		//Menu Cartes
		if (JrApplicationOption.IsReaderMode() < 1) {
			menu = new JMenu(GetWord("MenuCarte"));
			menu.setMnemonic(KeyEvent.VK_C);
			menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuCarte"));
			JMenu submenu = new JMenu(GetWord("MenuCarteType"));
			submenu.setMnemonic(KeyEvent.VK_T);
			for(i = 0; i < JrMapName.MAP_NAME_COUNT; i++)
				if (i != JrMapName.MAP_OBJECTS_NAME)
					submenu.add(new JMenuItem(actMap[i]));
			menu.add(submenu);
			
			submenu = new JMenu(GetWord("MenuCarteChemin"));
			submenu.setMnemonic(KeyEvent.VK_C);
			for(i = 0; i < JrTraitName.TRAIT_COUNT; i++)
				if (JrTraitName.IsClassChemin(i))
					submenu.add(new JMenuItem(actTrait[i]));
			menu.add(submenu);
	
			submenu = new JMenu(GetWord("MenuCarteRoute"));
			submenu.setMnemonic(KeyEvent.VK_R);
			for(i = 0; i < JrTraitName.TRAIT_COUNT; i++)
				if (JrTraitName.IsClassRoute(i))
					submenu.add(new JMenuItem(actTrait[i]));
			menu.add(submenu);
	
			submenu = new JMenu(GetWord("MenuCarteNationale"));
			submenu.setMnemonic(KeyEvent.VK_N);
			for(i = 0; i < JrTraitName.TRAIT_COUNT; i++)
				if (JrTraitName.IsClassNationale(i))
					submenu.add(new JMenuItem(actTrait[i]));
			menu.add(submenu);
			
			submenu = new JMenu(GetWord("MenuCarteVue"));
			submenu.setMnemonic(KeyEvent.VK_V);
			for(i = 0; i < JrViewName.VIEW_COUNT; i++)
				submenu.add(new JMenuItem(actMapView[i]));
			menu.add(submenu);
			menuBar.add(menu);
		}
		//Menu Outils
		menu = new JMenu(GetWord("MenuOutils"));
		menu.setMnemonic(KeyEvent.VK_O);
		menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuOutilsHlp"));
		menu.add(createMenuItem(actToolResume));
		menu.add(createMenuItem(actToolStat));
		menu.addSeparator();
		JMenu submenu = new JMenu(GetWord("MenuOutilsLang"));
		submenu.setMnemonic(KeyEvent.VK_L);
		if (JrApplicationOption.IsReaderMode() < 1) 
			submenu.add(createMenuItem(actToolLangEdt));
		submenu.add(createMenuItem(actToolLangSel));
		menu.add(submenu);
		if (JrApplicationOption.IsReaderMode() < 1) { 
			menu.addSeparator();
			menu.add(createMenuItem(actToolInv));
		}
		menuBar.add(menu);

		//Menu Pr�sentation
		menu = new JMenu(GetWord("MenuDisplay"));
		menu.setMnemonic(KeyEvent.VK_S);
		menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuDisplayHlp"));
		submenu = new JMenu(GetWord("MenuDisplayCase"));
		submenu.setMnemonic(KeyEvent.VK_C);
		for(i = 0; i < JrCaseDisplayerName.CASE_DISPLAYER_COUNT; i++)
			submenu.add(createMenuItem(actCaseDisplayer[i]));
		menu.add(submenu);
		submenu = new JMenu(GetWord("MenuDisplayPage"));
		submenu.setMnemonic(KeyEvent.VK_P);
		for(i = 0; i < JrPageDisplayerName.PAGE_DISPLAYER_COUNT; i++)
			submenu.add(createMenuItem(actPageDisplayer[i]));
		menu.add(submenu);
		menu.add(createMenuItem(actOptDisp));
		if (JrApplicationOption.IsReaderMode() < 1) { 
			menu.add(createMenuItem(actDispFilter));
			menu.add(createMenuItem(actDispColors));
		}
		menuBar.add(menu);

		//Menu Aide		
		menu = new JMenu(GetWord("MenuAide"));
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription(GetWord("MenuAideHlp"));
		menu.add(createMenuItem(actHelpAbout));
		menu.add(createMenuItem(actHelpDoc));
		menu.add(createMenuItem(actHelpLicence));
		menuBar.add(menu);
		return menuBar;
	}
	
	public JPopupMenu createPopupNavigator(JrNavigDrawWindow wnd) {
		int i;
		JPopupMenu popup = new JPopupMenu();
		if (actNavCopy[0] == null) {
			actNavCopy[0] = JrActionNavigCopy.CreateAction(JrClipboardOpeName.CLIPBOARD_CASE,wnd);
			actNavCopy[1] = JrActionNavigCopy.CreateAction(JrClipboardOpeName.CLIPBOARD_MAPS,wnd);
			actNavCopy[2] = JrActionNavigCopy.CreateAction(JrClipboardOpeName.CLIPBOARD_MAP1,wnd);
			for(i = 0; i < 3; i++) 
				actionArray.add(actNavCopy[i]);
		}
		if (actNavig[0] == null) {
			actNavig[0] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_FIRST); 
			actNavig[1] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_PREVIOUS); 
			actNavig[2] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_NEXT); 
			actNavig[3] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_LAST); 
			for(i = 0; i < JrGotoName.GOTO_COUNT; i++) 
				actionArray.add(actNavig[i]);
		}
		for(i = 0; i < 3; i++) 
			popup.add(createMenuItem(actNavCopy[i]));
		popup.addSeparator();
		for(i = 0; i < 4; i++) 
			popup.add(createMenuItem(actNavig[i]));
		return popup;
	}
	
	public JToolBar createMainToolBar() {
		JToolBar toolBar = new JToolBar();
		if (JrApplicationOption.IsReaderMode() < 1) {
			toolBar.add(createButton(actBookNew));
			toolBar.add(createButton(actBookOpen));
			toolBar.add(createButton(actBookSave));
			toolBar.add(createButton(actBookSaveAs));
			toolBar.addSeparator();
			toolBar.add(createButton(actBookPrint));
			toolBar.add(createButton(actBookPrintPreview));
			toolBar.addSeparator();
			toolBar.add(createButton(actDataTitle));
			toolBar.add(createButton(actDataCopyright));
			toolBar.addSeparator();
			toolBar.add(createButton(actToolResume));
			toolBar.add(createButton(actToolStat));
			toolBar.add(createButton(actToolLangEdt));
			toolBar.add(createButton(actToolLangSel));
			toolBar.add(createButton(actToolInv));
		}
		else {
			toolBar.add(createButton(actBookOpen));
			toolBar.addSeparator();
			toolBar.add(createButton(actBookPrint));
			toolBar.add(createButton(actBookPrintPreview));
			toolBar.addSeparator();
			toolBar.add(createButton(actToolResume));
			toolBar.add(createButton(actToolStat));
			toolBar.add(createButton(actToolLangSel));
		}
		return toolBar;
	}

	public JToolBar createBookToolBar() {
		int i;
		JToolBar toolBar = new JToolBar();
		if (JrApplicationOption.IsReaderMode() < 1) {
			toolBar.add(createButton(actEditionAdd));
			toolBar.add(createButton(actEditionInsert));
			toolBar.add(createButton(actEditionDel));
			toolBar.addSeparator();
		}
		toolBar.add(createButton(actPositionFirst));
		toolBar.add(createButton(actPositionPrevious));
		toolBar.add(createButton(actPositionNext));
		toolBar.add(createButton(actPositionLast));
		if (JrApplicationOption.IsReaderMode() < 1) {
			toolBar.addSeparator();
			for(i = 0; i < JrViewName.VIEW_COUNT; i++)
				toolBar.add(createButton(actMapView[i]));
		}
		toolBar.setFloatable(false);
		return toolBar;
	}

	public JToolBar createToolbarNavigator(JrNavigDrawWindow wnd) {
		int i;
		if (actNavig[0] == null) {
			actNavig[0] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_FIRST); 
			actNavig[1] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_PREVIOUS); 
			actNavig[2] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_NEXT); 
			actNavig[3] = JrActionNavigator.CreateAction(wnd,JrGotoName.GOTO_LAST); 
			for(i = 0; i < JrGotoName.GOTO_COUNT; i++) 
				actionArray.add(actNavig[i]);
		}
		JToolBar toolBar = new JToolBar();
		for(i = 0; i < JrGotoName.GOTO_COUNT; i++)
			toolBar.add(createButton(actNavig[i]));
		toolBar.setFloatable(false);
		return toolBar;
	}

	public void freePreviewActions() {
		for(int i = 0; i < JrGotoName.GOTO_COUNT; i++) { 
			actionArray.remove(actPreview[i]);
			actPreview[i] = null;
		}
		
	}
	
	public JToolBar createToolbarPreview(JrPreview wnd) {
		int i;
		if (actPreview[0] == null) {
			actPreview[0] = JrActionPreview.CreateAction(wnd,JrGotoName.GOTO_FIRST); 
			actPreview[1] = JrActionPreview.CreateAction(wnd,JrGotoName.GOTO_PREVIOUS); 
			actPreview[2] = JrActionPreview.CreateAction(wnd,JrGotoName.GOTO_NEXT); 
			actPreview[3] = JrActionPreview.CreateAction(wnd,JrGotoName.GOTO_LAST); 
			for(i = 0; i < JrGotoName.GOTO_COUNT; i++) 
				actionArray.add(actPreview[i]);
		}
		JToolBar toolBar = new JToolBar();
		for(i = 0; i < JrPageDisplayerName.PAGE_DISPLAYER_COUNT; i++)
			toolBar.add(createButton(actPageDisplayer[i]));
		toolBar.addSeparator();
		for(i = 0; i < JrGotoName.GOTO_COUNT; i++)
			toolBar.add(createButton(actPreview[i]));
		toolBar.setFloatable(false);
		return toolBar;
	}

	public JToolBar createCaseToolBar() {
		int i;
		JToolBar toolBar = new JToolBar();
		for(i = 0; i < JrMapName.MAP_NAME_COUNT; i++)
			if (i != JrMapName.MAP_OBJECTS_NAME)
				toolBar.add(createButton(actMap[i]));
		toolBar.setFloatable(false);
		return toolBar;
	}

	public JToolBar getToolbarMap(int name) {
		return toolbars[name];
	}
	
	public JToolBar createMiniToolbar(JrActionMenu a1,JrActionMenu a2,JrActionMenu a3) {
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(createButton(a1));
		if (a2 != null) bar.add(createButton(a2));
		if (a3 != null) bar.add(createButton(a3));
		return bar;
	}

	public JToolBar createMiniToolbarTxt(JrActionMenu a1,JrActionMenu a2,JrActionMenu a3) {
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(new JButton(a1));
		if (a2 != null) bar.add(new JButton(a2));
		if (a3 != null) bar.add(new JButton(a3));
		return bar;
	}
	
	public JToolBar createTitleToolbar(String title) {
		JToolBar bar = new JToolBar();
		bar.setFloatable(false);
		bar.add(new JLabel(GetWord(title)));
		return bar;		
	}
	
	public JToolBar createToolbarCase(boolean rota) {
		JToolBar bar = new JToolBar();
		bar.setOrientation(JToolBar.VERTICAL);
		bar.setFloatable(false);
		bar.setBorder(new EtchedBorder(2));
		bar.add(createTitleToolbar("TxtLaCase"));
		if (rota)
			bar.add(createMiniToolbar(actDataDistance,actDataComment,actDataRota));	
		else
			bar.add(createMiniToolbar(actDataDistance,actDataComment,null));
		bar.add(createMiniToolbar(actDataAlignLeft,actDataAlignCenter,null));
		return bar;
	}
	
	public JToolBar createToolbarTraits(boolean rota) {
		int i,count;
		JToolBar bar = createToolbarCase(rota);
		bar.add(createTitleToolbar("TxtLesTraits"));
		count = JrTraitName.TRAIT_COUNT;
		for(i = 0; i < count; i+=3) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actTrait[i],null,null));
			else
				if ((i+2) >= count)
					bar.add(createMiniToolbar(actTrait[i],actTrait[i+1],null));
				else
					bar.add(createMiniToolbar(actTrait[i],actTrait[i+1],actTrait[i+2]));
		}
		return bar;
	}

	public JToolBar createToolbarPonts() {
		int i,count;
		JToolBar bar = createToolbarCase(true);
		bar.add(createTitleToolbar("TxtLesPonts"));
		count = JrPontName.PONT_COUNT;
		for(i = 0; i < count; i+=3) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actPont[i],null,null));
			else
				if ((i+2) >= count)
					bar.add(createMiniToolbar(actPont[i],actPont[i+1],null));
				else
					bar.add(createMiniToolbar(actPont[i],actPont[i+1],actPont[i+2]));
		}
		return bar;
	}

	public JToolBar createToolbarVilles() {
		int i,count;
		JToolBar bar = createToolbarCase(true);
		bar.add(createTitleToolbar("TxtLesPanneaux"));
		count = JrActionVille.OPE_VILLE_COUNT;
		for(i = 0; i < count; i+=2) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actVille[i],null,null));
			else
				bar.add(createMiniToolbar(actVille[i],actVille[i+1],null));
		}
		return bar;
	}

	public JToolBar createToolbarMillePas() {
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtLesTraits"));
		bar.add(createMiniToolbar(actTrait[JrTraitName.TRAIT_NONE],
				actTrait[JrTraitName.TRAIT_PATH],null));				
		bar.add(createMiniToolbar(actTrait[JrTraitName.TRAIT_PATH_DEST],
				actTrait[JrTraitName.TRAIT_PATH_DESTINV],null));
		bar.add(createMiniToolbar(actTrait[JrTraitName.TRAIT_PATH_SRC],
				actTrait[JrTraitName.TRAIT_PATH_SRCINV],null));
		bar.add(createMiniToolbar(actTrait[JrTraitName.TRAIT_SENTIER],
				actTrait[JrTraitName.TRAIT_SENTIER_DEST],null));		
		bar.add(createMiniToolbar(actTrait[JrTraitName.TRAIT_SENTIER_DESTINV],
				actTrait[JrTraitName.TRAIT_SENTIER_SRC],null));		
		bar.add(createMiniToolbar(actTrait[JrTraitName.TRAIT_SENTIER_SRCINV],null,null));		
		return bar;
	}

	public JToolBar createToolbarArcs() {
		int i, count;
		JToolBar bar = createToolbarCase(true);
		bar.add(createTitleToolbar("TxtLesArcs"));
		count = JrArcName.ARC_COUNT;
		for(i = 0; i < count; i+=3) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actArc[i],null,null));
			else
				if ((i+2) >= count)
					bar.add(createMiniToolbar(actArc[i],actArc[i+1],null));
				else
					bar.add(createMiniToolbar(actArc[i],actArc[i+1],actArc[i+2]));
		}
		return bar;
	}
	public JToolBar createToolbarVector() {
		int i, count;
		JToolBar bar = createToolbarCase(true);
		bar.add(createTitleToolbar("TxtEdition"));
		count = JrVectorOperatorName.VECTOR_OPE_COUNT;
		for(i = 0; i < count; i+=3) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actVect[i],null,null));
			else
				if ((i+2) >= count)
					bar.add(createMiniToolbar(actVect[i],actVect[i+1],null));
				else
					bar.add(createMiniToolbar(actVect[i],actVect[i+1],actVect[i+2]));
		}
		bar.add(createTitleToolbar("TxtLesTraits"));
		count = JrTraitName.TRAIT_COUNT;
		for(i = JrTraitName.TRAIT_PATH; i < count; i+=3) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actTrait[i],null,null));
			else
				if ((i+2) >= count)
					bar.add(createMiniToolbar(actTrait[i],actTrait[i+1],null));
				else
					bar.add(createMiniToolbar(actTrait[i],actTrait[i+1],actTrait[i+2]));
		}
		return bar;
	}
	public JToolBar createToolbarCap() {
		int i;
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtLesCaps"));
		for(i = 0; i < 24; i+=3) {
			bar.add(createMiniToolbarTxt(actCap[i],actCap[i+1],actCap[i+2]));
		}
		return bar;
	}
	public JToolBar createToolbarClock() {
		int i;
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtLesHeures"));
		for(i = 0; i < 12; i+=2) {
			bar.add(createMiniToolbarTxt(actClock[i],actClock[i+1],null));
		}
		return bar;
	}
	public JToolBar createToolbarClipart() {
		int i,count;
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtLesCliparts"));
		count = JrClipartName.CLIPART_COUNT;
		for(i = 0; i < count; i+=2) {
			if ((i+1) < JrClipartName.CLIPART_COUNT)
				bar.add(createMiniToolbarTxt(actClipart[i],actClipart[i+1],null));
			else
				bar.add(createMiniToolbarTxt(actClipart[i],null,null));
		}
		return bar;
	}
	public JToolBar createToolbarGif() {
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtImage"));
		int count = JrImgOperatorName.IMG_OPE_COUNT;
		for(int i = 0; i < count; i+=2) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actEditImg[i],null,null));
			else
				bar.add(createMiniToolbar(actEditImg[i],actEditImg[i+1],null));
		}
		return bar;
	}
	public JToolBar createToolbarObject() {
		int i, count;
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtLesObjets"));
		count = actObject.length;
		for(i = 0; i < count; i+=3) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actObject[JrObjName.GetOrder(i)],null,null));
			else
				if ((i+2) >= count)
					bar.add(createMiniToolbar(actObject[JrObjName.GetOrder(i)],
							actObject[JrObjName.GetOrder(i+1)],null));
				else
					bar.add(createMiniToolbar(actObject[JrObjName.GetOrder(i)],
							actObject[JrObjName.GetOrder(i+1)],
							actObject[JrObjName.GetOrder(i+2)]));
		}
		return bar;
	}
	public JToolBar createToolbarBorne() {
		int i,count;
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtLesBornes"));
		count = JrBorneName.BORNE_COUNT;
		for(i = 0; i < count; i+=2) {
			if ((i+1) >= count)
				bar.add(createMiniToolbar(actBorne[i],null,null));
			else
				bar.add(createMiniToolbar(actBorne[i],actBorne[i+1],null));
		}
		return bar;
	}

	public JToolBar createToolbarText() {
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtEditionText"));
		bar.add(createMiniToolbar(actEditTxt[0],actEditTxt[1],null));
		bar.add(createMiniToolbar(actEditTxt[2],actEditTxt[3],actEditTxt[4]));
		return bar;
	}
	public JToolBar createToolbarLineText() {
		int i,count;
		JToolBar bar = createToolbarCase(false);
		bar.add(createTitleToolbar("TxtEditionText"));
		for(i = 0; i < JrActionEditLineText.OPE_LINETEXT_COUNT; i += 2)
			if (i < (JrActionEditLineText.OPE_LINETEXT_COUNT-1))
				bar.add(createMiniToolbar(actLineText[i],actLineText[i+1],null));
			else
				bar.add(createMiniToolbar(actLineText[i],null,null));
		return bar;
	}
}
