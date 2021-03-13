package book;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Vector;

import names.JrCaseDisplayerName;
import names.JrColorName;
import names.JrFontSize;
import names.JrGotoName;
import names.JrPageDisplayerName;
import names.JrPageRegionName;
import names.JrPenName;
import names.JrRegionFontName;
import names.JrRegionName;
import names.JrViewName;

import application.JrApplicationOption;
import application.dialogs.util.JrSearchMessageList;
import application.dialogs.util.JrStatModel;
import application.window.util.JrCheckMessageList;
import book.displayer.JrCaseDisplayer;
import book.displayer.JrCaseDisplayerDefault;
import book.displayer.JrPageDisplayer;
import book.displayer.JrPageDisplayerDefault;
import book.filter.JrFilter;

import tools.JrColorBook;
import tools.JrContext;
import tools.JrDrawTools;
import tools.JrMath;
import tools.JrPageRegions;
import tools.JrRegions;
import tools.files.JrFileRead;
import tools.files.JrFileSave;
import tools.files.JrFileUtil;

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
public class JrBook {
	private static JrFilter NoFilter = new JrFilter();
	private static int maxCases = 0;
	private String title = new String();
	private String filename = new String();
	private String copyright = new String();
	private boolean modified = false;
	private Vector cases = new Vector();
	private ArrayList listeners = new ArrayList();
	private Vector filters = new Vector();
	private int currentCase = 0;
	private boolean showIndex = true;
	private boolean hautVersBas = true;
	private int columnCaseCount = 5;
	private int scalePath = 100;
	private boolean editionMode = true;
	private int fontSize = JrFontSize.FONT_NORMAL_SIZE;
	private boolean autoParaph = true; 
	private JrColorBook colors = null;
	private int displayMeters = 0;
	
	private JrContext context = new JrContext();
	private JrCaseDisplayer caseDisplayer = new JrCaseDisplayerDefault();  
	private JrPageDisplayer pageDisplayer = new JrPageDisplayerDefault();  	
	
	public JrBook(String title,String copyright) {
		int ce = JrApplicationOption.GetCode();
		if (maxCases == 0) {
			maxCases = ((ce != 129834765) && (ce != 127895623))? 20 : 1000;
		}		
		this.title = title;
		this.copyright = copyright;
		modified = true;
		colors = new JrColorBook();
		cases.add(new JrCase());
	}
	
	public JrBook(JrFileRead file) {
		colors = new JrColorBook();
		int ce = JrApplicationOption.GetCode();
		if (maxCases == 0) {
			maxCases = ((ce != 129834765) && (ce != 127895623))? 20 : 1000;
		}		
		int i,nb,val;
		JrCase cas;
		filename = file.getFilename();
		String ext = JrFileUtil.GetExtension(filename);
		if (ext.compareTo("liv") == 0) {
			nb = Math.min(maxCases,readOldEntete(file));
			filename = "";
			for(i = 0; i < nb; i++) {
				file.setCurrentSection("Case" + i);
				cas = new JrCase();
				cas.readVeryOld(file);
				cases.add(cas);
			}
		}
		else {
			nb = readEntete(file);
			nb = Math.min(maxCases,nb);
			if (ext.compareTo("jrb") == 0) {
				for(i = 0; i < nb; i++) {
					file.setCurrentSection("Case" + i);
					cases.add(new JrCase(file));
				}
			}
			else {
				filename = "";
				for(i = 0; i < nb; i++) {
					file.setCurrentSection("Case" + i);
					cas = new JrCase();
					cas.readOld(file);
					cases.add(cas);
				}				
			}
		}
		updateTotal(false);
		modified = false;
	}
	
	public JrBook whiteCopy() {
		JrBook bk = new JrBook("","");
		bk.colors = new JrColorBook(colors);
		bk.setCaseDisplayer(JrCaseDisplayer.CreateDisplayer(getCaseDisplayer().getName()));
		bk.setPageDisplayer(JrPageDisplayer.CreateDisplayer(getPageDisplayer().getName()));
		bk.fontSize = fontSize;
		bk.setCasePerColumn(getCasePerColumn());
		bk.setShowIndex(isShowIndex());
		int cpt = getCasePerPage() * ((getPageDisplayer().hasTwoPages() == true)? 2 : 1);
		for(int i = 1; i < cpt; i++) {
			bk.addCase(0,false,false,false,0,0,0,0,"");
		}
		return bk;
	}
	
	public int readOldEntete(JrFileRead file) {
		file.setCurrentSection("General");
		title = file.getStringValue("Entete","noname");
		copyright = file.getStringValue("Copy","Techinnovation");
		hautVersBas = (file.getIntegerValue("HautVersBas",1) == 1)? true : false;
		columnCaseCount = file.getIntegerValue("CasesPage",10) / 2;
		showIndex = true;
		cases.add(new JrCase());
		return file.getIntegerValue("Nb",1); 
	}

	public int readEntete(JrFileRead file) {
		file.setCurrentSection("Display");
		int val = file.getIntegerValue("Case",JrCaseDisplayerName.CASE_DISPLAYER_DEFAULT);
		caseDisplayer = JrCaseDisplayer.CreateDisplayer(val);
		val = file.getIntegerValue("Page",JrPageDisplayerName.PAGE_DISPLAYER_DEFAULT);
		pageDisplayer = JrPageDisplayer.CreateDisplayer(val);
		fontSize = file.getIntegerValue("Font",JrFontSize.FONT_NORMAL_SIZE);
		val = file.getIntegerValue("autoParaph",1);
		autoParaph = (val > 0)? true : false;

		file.setCurrentSection("Filter");
		int i;
		int cpt = file.getIntegerValue("Count",0);
		for(i = 0; i < cpt; i++) {
			filters.add(new JrFilter(file,"Filter" + i));
		}
		colors.read(file);
		file.setCurrentSection("General");
		title = file.getStringValue("Entete","noname");
		copyright = file.getStringValue("Copy","Techinnovation");
		hautVersBas = (file.getIntegerValue("HautVersBas",1) == 1)? true : false;
		columnCaseCount = file.getIntegerValue("ColumnCase",5);
		showIndex = (file.getIntegerValue("CaseNumber",1) == 1)? true : false;
		scalePath = file.getIntegerValue("ScalePath",100);
		displayMeters = file.getIntegerValue("Meters",0);
		return file.getIntegerValue("Nb",1); 
	}

	public void insertBook(JrFileRead file,int pos) {
		int i,nb,cpt;
		
		cpt = cases.size();
		file.setCurrentSection("General");
		nb = file.getIntegerValue("Nb",1); 
		if ((cpt + nb) > getMaximumCase()) {
			nb = getMaximumCase() - cpt;
		}
		for(i = 0; i < nb; i++) {
			file.setCurrentSection("Case" + i);
			if (pos == -1)
				cases.add(new JrCase(file));
			else {
				cases.insertElementAt(new JrCase(file),pos);
				pos++;
			}
		}
		updateTotal(false);
		modified = false;
		fireCaseAdded();
	}
	
	public boolean save(JrFileSave file) {
		filename = file.getFilename();
		JrCase cas;
		JrFilter fil;
		int count = cases.size();
		file.addSection("General");
		file.addEntry("Entete",title);
		file.addEntry("Copy",copyright);
		file.addEntry("HautVersBas",hautVersBas);
		file.addEntry("ColumnCase",columnCaseCount);
		file.addEntry("CaseNumber",showIndex);
		file.addEntry("ScalePath",scalePath);
		file.addEntry("Meters",displayMeters);
		file.addEntry("Nb",count);
		colors.save(file);
		int i;
		int cpt = filters.size();
		file.addSection("Filter");
		file.addEntry("Count",cpt);
		for(i = 0; i < cpt; i++) {
			fil = (JrFilter)filters.get(i);
			fil.save(file,"Filter" + i);
		}
				
		file.addSection("Display");
		file.addEntry("Case",caseDisplayer.getName());
		file.addEntry("Page",pageDisplayer.getName());
		file.addEntry("Font",fontSize);
		file.addEntry("autoParaph",autoParaph);
		for(i = 0; i < count; i++) {
			cas = (JrCase)cases.get(i);
			file.addSection("Case" + i);
			cas.save(file);
		}
		setModified(false);
		return true;
	}
	
	public void checkCases(JrCheckMessageList list) {
		int cpt;
		int num = 0;
		JrCase cas;
		ListIterator iter = cases.listIterator();
		if (iter.hasNext()) {
			num++;
			cas = (JrCase)(iter.next());
			if (cas != null) {
				cpt = cas.getOriginCount();
				switch(cpt) {
				case 0 : list.addWarning(num,"ErrNoOrigine"); break;
				case 1 : break;
				default: list.addWarning(num,"MsgPlusieursOrigines"); break;
				}
				cpt = cas.getDestinationCount();
				switch(cpt) {
				case 0 : list.addWarning(num,"ErrNoDestination"); break;
				case 1 : break;
				default: list.addWarning(num,"MsgPlusieursDestinations"); break;
				}
				if (cas.hasDistance() == false)
					list.addWarning(num,"ErrNoDistance");
			}			
		}
		while(iter.hasNext()) {
			num++;
			cas = (JrCase)(iter.next());
			if (cas != null) {
				cpt = cas.getOriginCount();
				switch(cpt) {
				case 0 : list.addError(num,"ErrNoOrigine"); break;
				case 1 : break;
				default: list.addWarning(num,"MsgPlusieursOrigines"); break;
				}
				cpt = cas.getDestinationCount();
				switch(cpt) {
				case 0 : list.addError(num,"ErrNoDestination"); break;
				case 1 : break;
				default: list.addWarning(num,"MsgPlusieursDestinations"); break;
				}
				if (cas.hasDistance() == false)
					list.addError(num,"ErrNoDistance");
			}
		}		
	}
	
	public boolean isAutoParaph() {
		return autoParaph;
	}
	
	public void setAutoParaph(boolean autp) {
		autoParaph = autp;
	}
	
	public int getDisplayMeters() {
		return displayMeters;
	}
	
	public void setDisplayMeters(int meters) {
		displayMeters = meters;
	}
	
	public void resumeCases(JrSearchMessageList list) {
		int num = 0;
		int perPage = getCasePerPage();
		JrCase cas;
		ListIterator iter = cases.listIterator();
		while(iter.hasNext()) {
			cas = (JrCase)(iter.next());
			if (cas != null) {
				list.addMsg(num+1,(num/perPage)+1,cas.getDistance(),cas.getTotal(),
						cas.isRaz(),cas.isApprox(),cas.isInconnuDistance(),
						cas.getComments());
				num++;
			}
		}		
	}
	public boolean equals(Object o) {
		if (o instanceof JrBook) {
			JrBook tmp = (JrBook)o;
			if (filename.length() > 0) {
				if (filename.compareTo(tmp.getFilename()) == 0)
					return true;
			}
			else {
				if (title.compareTo(tmp.getTitle()) == 0)
					return true;
			}
		}
		return false;
	}
	
	public void setScalePath(int scale) {
		scalePath = scale;
	}
	
	public int getScalePath() {
		return scalePath;
	}
	
	public void addFilter(JrFilter filter) {
		filters.add(filter);
	}
	
	public void setFilters(Vector list) {
		JrFilter fil;
		
		filters.removeAllElements();
		ListIterator iter = list.listIterator();
		while(iter.hasNext()) {
			fil = (JrFilter)(iter.next());
			filters.add(new JrFilter(fil));
		}
	}
	
	public int getFilterCount() {
		return filters.size();
	}
	
	public JrFilter getFilter(int num) {
		return (JrFilter)(filters.elementAt(num));
	}
	
	public JrFilter getPageFilter(int page) {
		JrFilter fil;
		ListIterator iter = filters.listIterator();
		while(iter.hasNext()) {
			fil = (JrFilter)(iter.next());
			if (fil.getPage() == page)
				return fil;
		}
		return NoFilter;
	}
	
	public static JrFilter GetNoFilter() {
		return NoFilter;
	}
	
	public void drawCase(JrDrawTools dt,int index,int numcase,JrFilter filter) {
		int i;
		dt.setAutoParaph(autoParaph);
		dt.setCurrentFontSize(fontSize);
		dt.setDisplayMeters(displayMeters);
		for(i = 0; i < JrRegionName.REGION_COUNT; i++) {
			if (dt.isNullRegion(i) == false) {
				if (caseDisplayer.isVisible(i,editionMode)) {
					dt.drawRegion(i,true);
				}
			}
		}
		if (numcase == -1)
			getCurrentCase().draw(dt,caseDisplayer,index+1,context.getCurrentView(),filter);
		else {
			JrCase cas = getCase(numcase);
			if (cas != null) {
				cas.draw(dt,caseDisplayer,index+1,JrViewName.VIEW_REAL,filter);
			}
		}
	}

	public void drawCasePreview(JrDrawTools dt,int index,int numcase,JrFilter filter) {
		boolean old = editionMode;
		dt.setAutoParaph(autoParaph);
		dt.setDisplayMeters(displayMeters);
		editionMode = false;
		drawCase(dt,index,numcase,filter);
		editionMode = true;
	}
	
	public void drawCaseMap(JrDrawTools dt,int index,JrFilter filter) {
		int i;
		dt.setAutoParaph(autoParaph);
		dt.setCurrentFontSize(fontSize);
		dt.setDisplayMeters(displayMeters);
		dt.drawRegion(JrRegionName.REGION_MAP,true);
		getCurrentCase().drawMap(dt,caseDisplayer,index+1,context.getCurrentView(),filter);
	}
	
	public void print(JrDrawTools dt,JrPageRegions regions,int page) {
		int i,colno,colnb;
		JrRegions regs;
		Rectangle r = new Rectangle();
		boolean oldEdit = editionMode;
		dt.setAutoParaph(autoParaph);
		dt.setDisplayMeters(displayMeters);
		editionMode = false;
		dt.setPageRegions(regions);
		dt.setCurrentFontSize(fontSize);
		dt.selectDefinedPen(JrPenName.PEN_CADRE_EPAIS,true);
		for(i = 0; i < JrPageRegionName.PAGE_REGION_COUNT; i++) {
			r = regions.get(i);
			if ((r.width > 0) && (r.height > 0))
				dt.drawPageRegion(i,r.x,r.y,r.width,r.height,false,Color.BLACK);				
		}	
		colnb = getPageDisplayer().hasTwoColumns() ? 2 : 1;
		int total = 0;	
		int num;
		int count = getCaseCount();
		int pageCase = Math.min(count - (page * getCasePerPage()),getCasePerPage());
		int first = page * getCasePerPage();
		JrCase cas;
		JrFilter filter = getPageFilter(page);
		if (pageCase > 0) {
			filter.beginOrderCase(pageCase,first,isHautVersBas(),
					getPageDisplayer().hasTwoColumns(),getCasePerColumn());
			for(i = 0; i < pageCase; i++) {
				if ((first+i) < count) {
					num = filter.getNextCase();
					cas = getCase(num);
					total += cas.getDistance();
					regs = regions.getCaseRegions(i);
					dt.setRegions(regs);
					drawCase(dt,num,num,filter);
				}
			}
		}
		filter.endOrderCase();
		dt.selectColor(JrColorName.COLOR_TXT_PAGE);
		dt.selectFont(JrRegionFontName.REGION_FONT_TITRE_PAGE);
		dt.drawText(Integer.toString(page+1),
				    regions.get(JrPageRegionName.PAGE_REGION_NUMBER),1);
		dt.selectColor(JrColorName.COLOR_TXT_TITRE);
		dt.drawText(title,
			    regions.get(JrPageRegionName.PAGE_REGION_TITRE),1);

		dt.setPageRegions(regions);
		dt.selectColor(JrColorName.COLOR_TXT_COPYRIGHT);
		dt.selectFont(JrRegionFontName.REGION_FONT_COPYRIGHT);
		dt.drawText(GetWord("TxtCopyright") + ": " + copyright,
			    regions.get(JrPageRegionName.PAGE_REGION_COPYRIGHT),1);
		
		dt.selectFont(JrRegionFontName.REGION_FONT_TXT_PARTIEL);
		dt.selectColor(JrColorName.COLOR_TXT_COL_PARTIEL);	
		
		if (regions.isNull(JrPageRegionName.PAGE_REGION_PARTIEL1) == false) {
			if (getCaseDisplayer().isSmallPartiel() == true) {
				dt.drawText(GetWord("TxtTotal"),
						regions.get(JrPageRegionName.PAGE_REGION_PARTIEL1),1);
			}
			else
				dt.drawText(GetWord("TxtPartiel"),
						regions.get(JrPageRegionName.PAGE_REGION_PARTIEL1),1);
		}
		if (regions.isNull(JrPageRegionName.PAGE_REGION_PARTIEL2) == false) {
			if (getCaseDisplayer().isSmallPartiel() == true) {
				dt.drawText(GetWord("TxtTotal"),
						regions.get(JrPageRegionName.PAGE_REGION_PARTIEL2),1);
			}
			else
				dt.drawText(GetWord("TxtPartiel"),
						regions.get(JrPageRegionName.PAGE_REGION_PARTIEL2),1);
		}
		dt.selectColor(JrColorName.COLOR_TXT_COL_TOTAL);		
		if (regions.isNull(JrPageRegionName.PAGE_REGION_TOTAL1) == false)
			dt.drawText(GetWord("TxtTotal"),
					regions.get(JrPageRegionName.PAGE_REGION_TOTAL1),1);
		if (regions.isNull(JrPageRegionName.PAGE_REGION_TOTAL2) == false)
			dt.drawText(GetWord("TxtTotal"),
					regions.get(JrPageRegionName.PAGE_REGION_TOTAL2),1);

		String txt = JrMath.formatDistance(total);
		dt.selectFont(JrRegionFontName.REGION_FONT_TOTAL_PAGE);
		dt.selectColor(JrColorName.COLOR_TXT_COL_TOTALPAGE);		
		dt.drawText(GetWord("TxtTotalPage") + ": " + txt + " " + GetWord("TxtKm"),
			    regions.get(JrPageRegionName.PAGE_REGION_TOTALPAGE1),1);
		if (regions.isNull(JrPageRegionName.PAGE_REGION_TOTALPAGE2) == false)
			dt.drawText(GetWord("TxtTotalPage") + ": " + txt + " " + GetWord("TxtKm"),
					regions.get(JrPageRegionName.PAGE_REGION_TOTALPAGE2),1);
		
		editionMode = oldEdit;
	}

	public void printWhite(JrDrawTools dt,JrPageRegions regions,int page) {
		int i,j;
		JrRegions regs;
		Rectangle r = new Rectangle();
		boolean oldEdit = editionMode;
		dt.setAutoParaph(autoParaph);
		dt.setDisplayMeters(displayMeters);
		editionMode = false;
		dt.setPageRegions(regions);
		dt.setCurrentFontSize(fontSize);
		dt.selectDefinedPen(JrPenName.PEN_CADRE_EPAIS,true);
		for(i = 0; i < JrPageRegionName.PAGE_REGION_COUNT; i++) {
			r = regions.get(i);
			if ((r.width > 0) && (r.height > 0))
				dt.drawRectangle(r.x,r.y,r.width,r.height,false);				
		}	
		int col = getCasePerPage();
		int first = page * col;
		int count = getCaseCount();
		int total = 0;
		JrCase cas;
		for(i = 0; i < col; i++) {
			if ((first+i) < count) {
				cas = getCase(first+i);
				total += cas.getDistance();
				regs = regions.getCaseRegions(i);
				dt.setRegions(regs);
				for(j = 0; j < JrRegionName.REGION_COUNT; j++) {
					if (dt.isNullRegion(j) == false) {
						if (caseDisplayer.isVisible(j,editionMode)) {
							dt.drawRegion(j,false);
						}
					}
				}
			}
		}
		dt.selectDefinedPen(JrPenName.PEN_MAP_BLACK,true);
		editionMode = oldEdit;
	}
	
	/**
	 * @return Returns the context.
	 */
	public JrContext getContext() {
		return context;
	}
	/**
	 * @param context The context to set.
	 */
	public void setContext(JrContext context) {
		this.context = context;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	
	public void setFontSize(int font) {
		fontSize = font;
	}
	
	/**
	 * @return Returns the caseDisplayer.
	 */
	public JrCaseDisplayer getCaseDisplayer() {
		return caseDisplayer;
	}
	/**
	 * @param caseDisplayer The caseDisplayer to set.
	 */
	public void setCaseDisplayer(JrCaseDisplayer caseDisplayer) {
		this.caseDisplayer = caseDisplayer;
		modified = true;
		fireCurrentViewChanged();
	}

	public JrPageDisplayer getPageDisplayer() {
		return pageDisplayer;
	}
	public void setPageDisplayer(JrPageDisplayer pageDisplayer) {
		this.pageDisplayer = pageDisplayer;
		modified = true;
		fireCopyrightChanged();
	}
	/**
	 * @return Returns the copyright.
	 */
	public String getCopyright() {
		return copyright;
	}
	/**
	 * @param copyright The copyright to set.
	 */
	public void setCopyright(String copyright) {
		if (copyright.compareTo(this.copyright) != 0) {
			this.copyright = copyright;
			modified = true;
			fireCopyrightChanged();
		}
	}
	/**
	 * @return Returns the showIndex.
	 */
	public boolean isShowIndex() {
		return showIndex;
	}
	/**
	 * @param copyright The copyright to set.
	 */
	public void setShowIndex(boolean show) {
		if (showIndex != show) {
			showIndex = show;
			modified = true;
			fireShowIndexChanged();
		}
	}
	/**
	 * @return Returns the hautVersBas.
	 */
	public boolean isHautVersBas() {
		return hautVersBas;
	}
	/**
	 * @param hautVersBas The hautVersBas to set.
	 */
	public void setHautVersBas(boolean hautVersBas) {
		this.hautVersBas = hautVersBas;
	}
	/**
	 * @return Returns the filename.
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename The filename to set.
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return Returns the modified.
	 */
	public boolean isModified() {
		return modified;
	}
	/**
	 * @param modified The modified to set.
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {
		if (title.compareTo(this.title) != 0) {
			this.title = title;
			modified = true;
			fireTitleChanged();
		}
	}

	public void setCurrentView(int view) {
		if (view != context.getCurrentView()) {
			context.setCurrentView(view);
			fireCurrentViewChanged();
		}
	}
	
	public int getCurrentView() {
		return context.getCurrentView();
	}
	
	
	public JrColorBook getColorBook() {
		return colors;
	}
	
	public JrCase getCase(int index) {
		if ((index >= 0) && (index < cases.size()))
			return (JrCase)(cases.get(index));
		return null;
	}

	public JrCase getCurrentCase() {
		return (JrCase)(cases.get(currentCase));
	}
	
	public int getCurrentCaseIndex() {
		return currentCase;
	}
	
	public int getCurrentMapName() {
		return getCurrentCase().getMapName(context.getCurrentView());
	}
	
	public int getCaseCount() {
		return cases.size();
	}
	
	public int getPageCount() {
		int cpt = cases.size();
		int col = getCasePerPage();
		return (cpt / col) + (((cpt % col) == 0)? 0 : 1);
	}
	
	public int getPageCountForPrinting() {
		int cpt = getPageCount();
		if (pageDisplayer.hasTwoPages())
			return (cpt / 2) + (((cpt % 2) == 0)? 0 : 1);
		return cpt;

		/* Previous Code
		int cpt = getPageCount();
		if (pageDisplayer.hasTwoPages())
			return (cpt / 2) + (((cpt % 2) == 0)? 0 : 1);
		return cpt;
		*/
	}
	
	public void setCasePerColumn(int nb) {
		columnCaseCount = ((nb > 0) && (nb < 11))? nb : columnCaseCount;
	}
	public int getCasePerColumn() {
		return columnCaseCount;
	}
	public int getCasePerPage() {
		return columnCaseCount * ((pageDisplayer.hasTwoColumns() == true)? 2 : 1);		
	}
	
	public int getMaximumCase() {
		return maxCases;
	}
	
	public void goToCase(int dep) {
		int old = currentCase;
		switch(dep) {
		case JrGotoName.GOTO_PREVIOUS : 
			currentCase -= (currentCase > 0)? 1 : 0; 
			break;
		case JrGotoName.GOTO_NEXT : 
			currentCase += (currentCase < (cases.size()-1))? 1 : 0; 
			break;
		case JrGotoName.GOTO_LAST :
			currentCase = cases.size() - 1;
			break;
		default :
			currentCase = ((dep >= 0) && (dep < cases.size()))? dep : currentCase;
		}
		if (old != currentCase)
			firePositionChanged();
	}
	
	public void addCase(int dist,boolean raz,boolean approx,boolean inc,int symb1,int symb2,int symb3,int symb4,String txt) {
		if (cases.size() < maxCases) {
			JrCase cas = new JrCase(dist,raz,approx,inc);
			cas.setSymbol(0,symb1);
			cas.setSymbol(1,symb2);
			cas.setSymbol(2,symb3);
			cas.setSymbol(3,symb4);
			cas.setComments(txt);
			cases.add(cas);
			currentCase = cases.size() - 1;
			updateTotal(false);
			fireCaseAdded();
		}
	}
	
	public void insertCase(int dist,boolean raz,boolean approx,boolean inc,int symb1,int symb2,int symb3,int symb4,String txt) {
		if (cases.size() < maxCases) {
			JrCase cas = new JrCase(dist,raz,approx,inc);
			cas.setSymbol(0,symb1);
			cas.setSymbol(1,symb2);
			cas.setSymbol(2,symb3);
			cas.setSymbol(3,symb4);
			cas.setComments(txt);
			cases.insertElementAt(cas,currentCase);
			updateTotal(false);
			fireCaseAdded();
		}
	}
	
	public void insertCase(JrCase cas) {
		if (cases.size() < maxCases) {
			cases.insertElementAt(cas,currentCase);
			updateTotal(false);
			fireCaseAdded();
		}
	}
	
	public void removeCase() {
		if (cases.size() > 1) {
			cases.remove(currentCase);
			if (currentCase >= cases.size())
				currentCase = cases.size() - 1;
			updateTotal(false);
			fireCaseDeleted();
		}
	}
	
	public JrInversionReport inverseCases() {
		Vector tmp = new Vector();
		JrCase cas;
		int i;
		JrInversionReport report = new JrInversionReport();
		JrDistanceParam dist = null;
		JrDistanceParam newdist = new JrDistanceParam();
		for (i = 0; i < cases.size(); i++) {
			cas = (JrCase)cases.get(i);
			tmp.insertElementAt(cas,0);
			cas.inverse();
		}
		cases.removeAllElements();
		cases = tmp;
		for (i = 0; i < cases.size(); i++) {
			cas = (JrCase)cases.get(i);
			dist = cas.getDistanceParameters();
			report.setRaz(dist.isRaz());
			cas.setDistanceParameters(newdist);
			report.setCapMap(cas.hasCapMap());
			report.setImgMap(cas.hasImgMap());
			newdist = dist;
		}
		updateTotal(true);
		return report;
	}
	
	public void updateTotal(boolean fire) {
		int i;
		int total = 0;
		JrCase cas;
		
		if (currentCase > 0) {
			cas = (JrCase)cases.get(currentCase - 1);
			total = cas.getTotal();
		}
		for (i = currentCase; i < cases.size(); i++) {
			cas = (JrCase)cases.get(i);
			if (cas.isRaz())
				total = 0;
			total += cas.getDistance();
			cas.setTotal(total);
		}
		setModified(true);
		if (fire)
			fireCurrentViewChanged();	
	}

	public void collectData(JrStatModel stats) {
		int i,dist;
		int total = 0;
		int shorter = 999999;
		int longer  = 0;
		int raz = 0;
		int approx = 0;
		int inconnue = 0;
		JrCase cas;

		ListIterator iter = cases.listIterator();
		while(iter.hasNext()) {
			cas = (JrCase)(iter.next());
			dist = cas.getDistance();
			total += dist;
			shorter = Math.min(shorter,dist);
			longer = Math.max(longer,dist);
			raz += (cas.isRaz())? 1 : 0;
			approx += (cas.isApprox())? 1 : 0;
			inconnue += (cas.isInconnuDistance())? 1 : 0;			
		}		
		stats.setShorterDistance(shorter);
		stats.setLongerDistance(longer);
		stats.setTotalDistance(total);
		stats.setAverageDistance(total / cases.size());
		stats.setRazCount(raz);
		stats.setApproxCount(approx);
		stats.setInconnueCount(inconnue);
	}
	
	public void addListener(JrBookListener lst) {
		listeners.add(lst);
	}

	public void removeListener(JrBookListener lst) {
		listeners.remove(lst);
	}

	public void fireTitleChanged() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).titleChanged();
		}		
	}
	
	public void fireCopyrightChanged() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).copyrightChanged();
		}		
	}

	public void firePositionChanged() {
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).positionChanged();
		}				
	}

	public void fireCaseAdded() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).caseAdded();
		}				
	}

	public void fireCaseDeleted() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).caseDeleted();
		}				
	}
	
	public void fireCurrentViewChanged() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).currentViewChanged();
		}						
	}
	public void fireCurrentMapChanged() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).currentMapChanged();
		}						
	}
	public void fireShowIndexChanged() {
		modified = true;
		ListIterator iter = listeners.listIterator();
		while(iter.hasNext()) {
			((JrBookListener)(iter.next())).showIndexChanged();
		}						
	}
	
	public static String GetWord(String code) {
		return JrApplicationOption.GetWord(code);
	}
}
