/*
 * Created on Dec 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package names;

import java.awt.Color;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrColorName {
	  private final static Color RIVER = new Color(72,211,236);
  	  private final static Color LIGHTRED = new Color(255, 200, 200);
	  private final static Color DARKRED = new Color(255, 100, 100);
	  private final static Color DARKGRAY = new Color(64,64,64);
	
	  public final static int COLOR_TYPE_BACKGROUND = 0;
	  public final static int COLOR_TYPE_TEXTE = 1;
	  public final static int COLOR_TYPE_TRAIT = 2;
	  public final static int COLOR_TYPE_DESSIN = 3;
	  public final static int COLOR_TYPE_CLIPART = 4;
	  public final static int COLOR_TYPE_COUNT = 5;
	
	  public final static int COLOR_BG_PARTIEL = 0;
	  public final static int COLOR_BG_TOTAL = 1;
	  public final static int COLOR_BG_COL_PARTIEL = 2;
	  public final static int COLOR_BG_COL_TOTAL = 3;
	  public final static int COLOR_BG_COL_TOTALPAGE = 4;
	  public final static int COLOR_BG_CASE_NUM = 5;
	  public final static int COLOR_BG_SYMBOLES = 6;
	  public final static int COLOR_BG_COMMENT = 7;
	  public final static int COLOR_BG_MAP = 8;
	  public final static int COLOR_BG_TITRE = 9;
	  public final static int COLOR_BG_PAGE = 10;
	  public final static int COLOR_BG_COPYRIGHT = 11;
	  public final static int COLOR_TXT_PARTIEL = 12;
	  public final static int COLOR_TXT_TOTAL = 13;
	  public final static int COLOR_TXT_COL_PARTIEL = 14;
	  public final static int COLOR_TXT_COL_TOTAL = 15;
	  public final static int COLOR_TXT_COL_TOTALPAGE = 16;
	  public final static int COLOR_TXT_CASE_NUM = 17;
	  public final static int COLOR_TXT_COMMENT = 18;
	  public final static int COLOR_TRT_MAP = 19;
	  public final static int COLOR_TXT_TITRE = 20;
	  public final static int COLOR_TXT_PAGE = 21;
	  public final static int COLOR_TXT_COPYRIGHT = 22;
	  public final static int COLOR_TXT_RAZ = 23;
	  public final static int COLOR_TXT_APPROX = 24;
	  public final static int COLOR_DSS_OBSTACLE_SYMBOL = 25;
	  public final static int COLOR_DSS_TROMPETTE = 26;
	  public final static int COLOR_DSS_POIGNET_TROMPETTE = 27;
	  public final static int COLOR_DSS_CHEVAL = 28;
	  public final static int COLOR_BG_MAP_SELECTION = 29;
	  public final static int COLOR_TRT_MAP_GUIDE = 30;
	  public final static int COLOR_TRT_MAP_INACTIF_CLAIR = 31;
	  public final static int COLOR_TRT_MAP_INACTIF = 32;
	  public final static int COLOR_TRT_MAP_INACTIF_SOMBRE = 33;
	  public final static int COLOR_TRT_MAP_VECTOR = 34;
	  public final static int COLOR_TRT_MAP_ROAD = 35;
	  public final static int COLOR_TRT_MAP_RIVER = 36;
	  public final static int COLOR_DSS_CENTRE_ROND_POINT = 37;
	  public final static int COLOR_CLP_VENT_CADRANT = 38;
	  public final static int COLOR_CLP_VENT_FOND = 39;
	  public final static int COLOR_CLP_VENT_CONTOUR = 40;
	  public final static int COLOR_CLP_VENT_AIGUILLE1 = 41;
	  public final static int COLOR_CLP_VENT_AIGUILLE2 = 42;
	  public final static int COLOR_CLP_TASSE_CONTOUR = 43;
	  public final static int COLOR_CLP_TASSE = 44;
	  public final static int COLOR_CLP_TASSE_TEXTE = 45;
	  public final static int COLOR_CLP_TASSE_INTERIEUR = 46;
	  public final static int COLOR_CLP_TASSE_SOUCOUPE = 47;
	  public final static int COLOR_CLP_REPAS_TEXTE = 48;
	  public final static int COLOR_CLP_REPAS_CONTOUR = 49;
	  public final static int COLOR_CLP_REPAS_FOND = 50;
	  public final static int COLOR_CLP_CP_CONTOUR = 51;
	  public final static int COLOR_CLP_CP_ENCRE = 52;
	  public final static int COLOR_CLP_CP_TEXTE = 53;
	  public final static int COLOR_CLP_CP_TRONC = 54;
	  public final static int COLOR_CLP_CP_DESSUS = 55;
	  public final static int COLOR_CLP_DEP_CONTOUR = 56;
	  public final static int COLOR_CLP_DEP_TEXTE = 57;
	  public final static int COLOR_CLP_DEP_BANDE = 58;
	  public final static int COLOR_CLP_DEP_POTEAU = 59;
	  public final static int COLOR_CLP_DEP_POTEAU_SOMBRE = 60;
	  public final static int COLOR_CLP_DEP_DAMIER_NOIR = 61;
	  public final static int COLOR_CLP_DEP_DAMIER_BLANC = 62;
	  public final static int COLOR_BG_CAP_PLAQUETTE = 63;
	  public final static int COLOR_BG_CAP_COURONNE = 64;
	  public final static int COLOR_BG_CAP_INTERIEUR = 65;
	  public final static int COLOR_TXT_CAP = 66;
	  public final static int COLOR_TRT_CAP_CONTOUR = 67;
	  public final static int COLOR_TRT_CAP_AIGUILLE1 = 68;
	  public final static int COLOR_TRT_CAP_AIGUILLE2 = 69;
	  public final static int COLOR_TRT_CLK_BOUTON = 70;
	  public final static int COLOR_TXT_MAP_TEXT = 71;
	  public final static int COLOR_BG_VILLE = 72;
	  public final static int COLOR_BORDER_VILLE = 73;
	  public final static int COLOR_BARRE_VILLE = 74;
	  public final static int COLOR_TXT_VILLE = 75;
	  
	  public final static int COLOR_COUNT = 76;
	  
	  public static int GetColorType(int name) {
	  	switch(name) {
		  case COLOR_TXT_PARTIEL :
		  case COLOR_TXT_TOTAL :
		  case COLOR_TXT_COL_PARTIEL :
		  case COLOR_TXT_COL_TOTAL :
		  case COLOR_TXT_COL_TOTALPAGE :
		  case COLOR_TXT_CASE_NUM :
		  case COLOR_TXT_COMMENT :
		  case COLOR_TXT_TITRE :
		  case COLOR_TXT_PAGE :
		  case COLOR_TXT_RAZ :
		  case COLOR_TXT_APPROX :
		  case COLOR_TXT_CAP :
		  case COLOR_TXT_MAP_TEXT :
		  case COLOR_TXT_VILLE :
		  case COLOR_TXT_COPYRIGHT : return COLOR_TYPE_TEXTE;
		  case COLOR_TRT_MAP_GUIDE :
		  case COLOR_TRT_MAP_INACTIF_CLAIR :
		  case COLOR_TRT_MAP_INACTIF :
		  case COLOR_TRT_MAP_INACTIF_SOMBRE :
		  case COLOR_TRT_MAP_VECTOR :
		  case COLOR_TRT_MAP_ROAD :
		  case COLOR_TRT_MAP_RIVER :
		  case COLOR_TRT_CAP_CONTOUR :
		  case COLOR_TRT_CAP_AIGUILLE1 :
		  case COLOR_TRT_CAP_AIGUILLE2 :
		  case COLOR_TRT_MAP : return COLOR_TYPE_TRAIT;
		  case COLOR_DSS_CHEVAL : 
		  case COLOR_DSS_TROMPETTE :
		  case COLOR_DSS_POIGNET_TROMPETTE :
		  case COLOR_DSS_CENTRE_ROND_POINT :
		  case COLOR_DSS_OBSTACLE_SYMBOL : return COLOR_TYPE_DESSIN;
		  case COLOR_CLP_VENT_CADRANT :
		  case COLOR_CLP_VENT_FOND :
		  case COLOR_CLP_VENT_CONTOUR :
		  case COLOR_CLP_VENT_AIGUILLE1 :
		  case COLOR_CLP_VENT_AIGUILLE2 :
		  case COLOR_CLP_TASSE_CONTOUR :
		  case COLOR_CLP_TASSE :
		  case COLOR_CLP_TASSE_INTERIEUR :
		  case COLOR_CLP_TASSE_SOUCOUPE :
		  case COLOR_CLP_REPAS_TEXTE :
		  case COLOR_CLP_REPAS_CONTOUR :
		  case COLOR_CLP_REPAS_FOND :
		  case COLOR_CLP_CP_CONTOUR :
		  case COLOR_CLP_CP_ENCRE :
		  case COLOR_CLP_CP_TEXTE :
		  case COLOR_CLP_CP_TRONC :
		  case COLOR_CLP_CP_DESSUS :
		  case COLOR_CLP_DEP_CONTOUR :
		  case COLOR_CLP_DEP_TEXTE :
		  case COLOR_CLP_DEP_BANDE :
		  case COLOR_CLP_DEP_POTEAU :
		  case COLOR_CLP_DEP_POTEAU_SOMBRE :
		  case COLOR_CLP_DEP_DAMIER_NOIR :
		  case COLOR_CLP_DEP_DAMIER_BLANC :
		  case COLOR_BORDER_VILLE :
		  case COLOR_BARRE_VILLE :
		  case COLOR_CLP_TASSE_TEXTE : return COLOR_TYPE_CLIPART;
		  default : break;
	  	}
	  	return COLOR_TYPE_BACKGROUND;
	  }

	  public static Color GetDefaultValue(int name) {
	  	switch(name) {
		  case COLOR_TXT_PARTIEL :
		  case COLOR_TXT_TOTAL :
		  case COLOR_TXT_COL_PARTIEL :
		  case COLOR_TXT_COL_TOTAL :
		  case COLOR_TXT_COL_TOTALPAGE :
		  case COLOR_TXT_CASE_NUM :
		  case COLOR_TXT_COMMENT :
		  case COLOR_TXT_TITRE :
		  case COLOR_TXT_PAGE :
		  case COLOR_TXT_RAZ :
		  case COLOR_TXT_APPROX :
		  case COLOR_TXT_COPYRIGHT : 
		  case COLOR_DSS_TROMPETTE :
		  case COLOR_DSS_CHEVAL :
		  case COLOR_CLP_VENT_CONTOUR :
		  case COLOR_CLP_VENT_AIGUILLE2 :
		  case COLOR_CLP_TASSE_CONTOUR :
		  case COLOR_CLP_TASSE_TEXTE : 
		  case COLOR_CLP_REPAS_TEXTE :
		  case COLOR_CLP_REPAS_CONTOUR :
		  case COLOR_CLP_CP_CONTOUR :
		  case COLOR_CLP_CP_ENCRE :
		  case COLOR_CLP_DEP_CONTOUR :
		  case COLOR_CLP_DEP_TEXTE :
		  case COLOR_CLP_DEP_DAMIER_NOIR :
		  case COLOR_CLP_DEP_POTEAU_SOMBRE :
		  case COLOR_TXT_CAP :
		  case COLOR_TRT_CAP_CONTOUR :
		  case COLOR_TXT_MAP_TEXT :
		  case COLOR_TXT_VILLE :
		  case COLOR_TRT_MAP : return Color.BLACK;
		  case COLOR_TRT_MAP_GUIDE :
		  case COLOR_DSS_CENTRE_ROND_POINT :
		  case COLOR_CLP_VENT_FOND :
		  case COLOR_CLP_TASSE :
		  case COLOR_CLP_REPAS_FOND :
		  case COLOR_CLP_CP_DESSUS :
		  case COLOR_CLP_DEP_BANDE :
		  case COLOR_BG_CAP_INTERIEUR :
		  case COLOR_DSS_OBSTACLE_SYMBOL : return Color.LIGHT_GRAY;
		  case COLOR_CLP_VENT_CADRANT :
		  case COLOR_CLP_TASSE_SOUCOUPE :
		  case COLOR_CLP_TASSE_INTERIEUR :
		  case COLOR_CLP_CP_TRONC :
		  case COLOR_BG_CAP_PLAQUETTE :
		  case COLOR_CLP_DEP_POTEAU : 
		  case COLOR_DSS_POIGNET_TROMPETTE : return Color.GRAY;
		  case COLOR_TRT_MAP_INACTIF_CLAIR : return LIGHTRED;
		  case COLOR_TRT_CAP_AIGUILLE2 :
		  case COLOR_BORDER_VILLE :
		  case COLOR_TRT_MAP_INACTIF : return Color.RED;
		  case COLOR_TRT_MAP_INACTIF_SOMBRE : return DARKRED;
		  case COLOR_CLP_CP_TEXTE :
		  case COLOR_TRT_MAP_VECTOR : return Color.BLUE;
		  case COLOR_TRT_MAP_RIVER : return RIVER;
		  case COLOR_TRT_CLK_BOUTON :
		  case COLOR_BARRE_VILLE : return DARKGRAY;
		  default : break;
	  	}
	  	return Color.WHITE;
	  }
	  
	  public static int GetInactiveColorName(int name) {
	  	switch(name) {
	  	case COLOR_DSS_CENTRE_ROND_POINT : return COLOR_TRT_MAP_INACTIF_CLAIR;
	  	default : break;
	  	}
	  	return name;
	  }
	  
	  public static String GetName(int name) {
	  	return "Color" + name;
	  }
	  
	  public static String GetLabel(int name) {
	  	return "Color" + name;	  	
	  }
	  
	  public static String GetTypeName(int name) {
	  	return "TypeColor" + name;
	  }

	  public static String GetTypeHelp(int name) {
	  	return "TypeColorHlp" + name;
	  }
}
