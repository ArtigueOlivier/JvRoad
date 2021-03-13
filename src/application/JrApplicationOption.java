/*
 * Created on 4 f�vr. 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application;

import java.awt.print.PageFormat;
import java.awt.print.PrinterJob;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javax.print.PrintService;

import tools.files.JrFileSave;

import names.JrPenZoomName;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrApplicationOption {
	private static JrApplicationOption options = new JrApplicationOption();
	private PageFormat pageFormat = null;
	private int editionZoom = JrPenZoomName.ZOOM_VERYBIG;
	private int navigatorZoom = JrPenZoomName.ZOOM_NORMAL;
	private int printingZoom = JrPenZoomName.ZOOM_NORMAL;
	private int version = 150; // 1.20
	private String strVer = "";
	private HashMap words = null;
	private String langFile = "jvroad.lg";	
	
	private int modeReader = 0;
	//private int codePro = 129834765; // Pro
	//private int codePro = 127895623; // Light
	private int codePro = 123569874; // Eval
	
	private JrApplicationOption() {
		PrintService prn [] = PrinterJob.lookupPrintServices();
		if (prn.length > 0)
			pageFormat = PrinterJob.getPrinterJob().defaultPage();
		reload();		
		if (codePro == 129834765) {
			HashMap def = readFile("jvroad.opt");
			if (def != null) {
				langFile = getStringEntry(def,"Langage","...");
			}
		}
		words = readFile(langFile);
	}
	
	public static void Reload() {
		options.reload();
	}
	
	public static boolean Recompute(String s1,String s2) {
		return options.recompute(s1,s2);
	}
	
	public static int GetCode() {
		return options.codePro;
	}
	
	public static int IsReaderMode() {
		return options.modeReader;
	}
	
	public static String GetStrVer() {
		return options.strVer;
	}
	
	public static String GetWord(String code,String def) {
		if (options.words != null) {
			String val = (String)(options.words.get(code));
			if ((val != null) && (val.length() > 0))
				return val;
		}
		return def;
	}
	public static String GetWord(String code) {
		if (options.words != null) {
			String val = (String)(options.words.get(code));
			if ((val != null) && (val.length() > 0))
				return val;
		}
		return code;
	}
	
	public static PageFormat GetPageFormat() {
		return options.pageFormat;
	}	
	public static void SetPageFormat(PageFormat pg) {
		options.pageFormat = pg;
	}

	public static int GetEditionZoom() {
		return options.editionZoom;
	}
	public static void SetEditionZoom(int editionZoom) {
		options.editionZoom = editionZoom;
	}

	public static int GetNavigatorZoom() {
		return options.navigatorZoom;
	}
	public static void SetNavigatorZoom(int navigatorZoom) {
		options.navigatorZoom = navigatorZoom;
	}

	public static int GetPrintingZoom() {
		return options.printingZoom;
	}
	public static void SetPrintingZoom(int printingZoom) {
		options.printingZoom = printingZoom;
	}

	public static void LoadLangueFile(String filename) {
		options.words = options.readFile(filename);
	}
	
	public HashMap readFile(String filename) {		
		FileInputStream file = null;
		try {
			file = new FileInputStream(filename);
		} catch (FileNotFoundException e) {}
		if (file == null)
			return null;
		DataInput di = new DataInputStream(file);
		if (di == null) {
			try {
				file.close();
			} catch (IOException e1) {}
			return null;
		}			
		HashMap entries = new HashMap();
		String line;
		int last,pos;
		try {
			while((line = di.readLine()) != null) {
				last = line.length() - 1;
				if (line.length() > 3) {
					if ((line.charAt(0) != '#') && (line.charAt(last) != '/')) {
						pos = line.indexOf("=");
						if (pos > 0)
							entries.put(line.substring(0,pos),line.substring(pos+1,line.length()));
					}					
				}				
			}
		} catch (IOException e1) {}
		try {
			file.close();
		} catch (IOException e2) {}
		return entries;		
	}
	
	public String getStringEntry(HashMap entries,String name,String def) {
		String val = (String)entries.get(name);
		if ((val != null) && (val.length() > 0))
			return val;
		return def;		
	}
	
	public static String GetLanguageFile() {
		return options.langFile;
	}
	
	public static int GetVersion() {
		return options.version;
	}
	
	private String replaceInt(int src) {
		String s = Integer.toString(src);
		StringBuffer bf = new StringBuffer();
		int tval = src;
		for(int i = 0; i < s.length(); i++) {
			switch(tval % 10) {
			case 1 : bf.insert(0,"UNO"); break;
			case 2 : bf.insert(0,"DOS"); break;
			case 3 : bf.insert(0,"TRE"); break;
			case 4 : bf.insert(0,"QUA"); break;
			case 5 : bf.insert(0,"CIN"); break;
			case 6 : bf.insert(0,"SEX"); break;
			case 7 : bf.insert(0,"MAL"); break;
			case 8 : bf.insert(0,"HUI"); break;
			case 9 : bf.insert(0,"NEU"); break;
			default: bf.insert(0,"ZER"); break;
			}
			tval /= 10;
		}	
		//System.out.println(src + " -> " + bf.toString());
		return replace(bf.toString());
	}
	
	private String replace(String src) {
		int ofs[] = { 19, 4, 2, 7, 9, 21, 17, 14, 0, 3, 8, 13, 14, 21, 0, 19, 8, 14, 13, 14, 0 };
		int val;
		StringBuffer buff = new StringBuffer();
		for(int i = 0; i < src.length(); i++) {
			val = (int)(src.charAt(i));
			if ((val >= 65) && (val < 91)) {
			  val += ofs[i % 21];
			  val -= (val >= 91)? 26 : 0;
			  buff.append((char)val);
			}
		}
		//System.out.println(src + " -> " + buff.toString());
		return buff.toString();
	}
	
	private String inverse(String src) {
		int ofs[] = { 19, 4, 2, 7, 9, 21, 17, 14, 0, 3, 8, 13, 14, 21, 0, 19, 8, 14, 13, 14, 0 };
		int val;
		StringBuffer buff = new StringBuffer();
		for(int i = 0; i < src.length(); i++) {
			val = (int)(src.charAt(i));
			if ((val >= 65) && (val < 91)) {
			  val -= ofs[i % 21];
			  val += (val < 65)? 26 : 0;
			  buff.append((char)val);
			}
		}
		//System.out.println(src + " -> " + buff.toString());
		return buff.toString();		
	}
	
	private int inverseInt(String src) {
		String s1 = src.replaceAll("UNO","1");
	    String s2 = s1.replaceAll("DOS","2");
	    s1 = s2.replaceAll("TRE","3");
	    s2 = s1.replaceAll("QUA","4");
	    s1 = s2.replaceAll("CIN","5");
	    s2 = s1.replaceAll("SEX","6");
	    s1 = s2.replaceAll("MAL","7");
		s2 = s1.replaceAll("HUI","8");
		s1 = s2.replaceAll("NEU","9");
		s2 = s1.replaceAll("ZER","0");
		for(int i = 0; i < s2.length(); i++) {
			if (Character.isDigit(s2.charAt(i)) == false)
				return 0;
		}
		int v = Integer.parseInt(s2);
		//System.out.println(src + " -> " + v);
		return v;
	}
	
	private void reload() {
		HashMap cle = readFile("jvroad.jvl");
		if (cle != null) {
			String s1 = getStringEntry(cle,"jvl1","");
			String s2 = getStringEntry(cle,"jvl2","");
			String s3 = getStringEntry(cle,"jvl3","");
			String s4 = getStringEntry(cle,"jvl4","");
			String s5 = getStringEntry(cle,"jvl5","");
			
			if (s1.compareTo("READER-MODE") == 0) {
				codePro = 129834765;
				modeReader = 1;
			}
			else {
				modeReader = 0;
				if ((s1.length() == 27) && (s2.length() == 27) && (s3.length() == 27) && (s4.length() == 18) && (s5.length() == 18)) {
					StringBuffer bf = new StringBuffer(s4 + s5);
					bf.insert(6,"-");
					bf.insert(13,"-");
					bf.insert(20,"-");
					bf.insert(27,"-");
					bf.insert(34,"-");
					strVer = bf.toString();
					int i1 = inverseInt(inverse(s1));
					int i2 = inverseInt(inverse(s2));
					int i3 = inverseInt(inverse(s3));
					int i4 = inverseInt(inverse(s4));
					int i5 = inverseInt(inverse(s5));
					int l1 = (i1 / 1000) % 1000;
					int c1 = ((i2 / 1000000) * 1000) + (i2 % 1000);
					int v1 = (i2 / 1000) % 1000;
					int c2 = ((i4 / 1000) * 1000) + (i5 % 1000);
					int v2 = i4 % 1000;				
					int l2 = i5 / 1000;
					if ((c1 == c2) && (v1 == v2) && (l1 == l2) && ((i1 + i2) == i3))
						if (version <= v1)
							codePro = i1;
				}
			}
		}		
	}
	
	private boolean recompute(String s1,String s2) {
		if ((s1.length() != 18) || (s2.length() != 18))
			return false;
		int i1 = inverseInt(inverse(s1));
		int i2 = inverseInt(inverse(s2));
		int c2 = ((i1 / 1000) * 1000) + (i2 % 1000);
		int v2 = i1 % 1000;				
		int l2 = i2 / 1000;
		if ((c2 < 126000) || (c2 > 255000) || (v2 < version))
			return false;
		if ((l2 != 834) && (l2 != 895))
			return false;
		codePro = (l2 ==834)? 129834765 : 127895623;		
		return true;
	}
	
	public static boolean Check(String[] values) {
		String s1 = values[0] + values[1] + values[2];
		String s2 = values[3] + values[4] + values[5];
		if ((s1.compareTo("READERJVROADREADER") == 0) && (s2.compareTo("JVROADREADERJVROAD") == 0)) {
			JrFileSave file = new JrFileSave("jvroad.jvl");
			file.addEntry("jvl1","READER-MODE");
			file.addEntry("jvl2","READER-MODE");
			file.addEntry("jvl3","READER-MODE");
			file.addEntry("jvl4","READER-MODE");
			file.addEntry("jvl5","READER-MODE");
			file.close();			
		}
		else {
			int r4 = options.inverseInt(options.inverse(s1));
			int r5 = options.inverseInt(options.inverse(s2));
			int r2 = (r4*1000) + (r5 % 1000);
			int r1 = 123569874;
			if ((r5 / 1000) == 834) r1 = 129834765;
			if ((r5 / 1000) == 895) r1 = 127895623;
			int r3 = r1 + r2;
					
			JrFileSave file = new JrFileSave("jvroad.jvl");
			file.addEntry("jvl1",options.replaceInt(r1));
			file.addEntry("jvl2",options.replaceInt(r2));
			file.addEntry("jvl3",options.replaceInt(r3));
			file.addEntry("jvl4",options.replaceInt(r4));
			file.addEntry("jvl5",options.replaceInt(r5));
			file.close();
		}
		options.codePro = 123569874;
		options.modeReader = 0;
		options.reload();
		return ((options.codePro == 129834765) || (options.codePro == 127895623))? true : false;
	}
}
