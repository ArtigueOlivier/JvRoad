/*
 * Created on 24 mai 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package names;

/**
 * @author Olivier Artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrFontSize {
	public static final int FONT_SMALL_SIZE = 0;
	public static final int FONT_NORMAL_SIZE = 1;
	public static final int FONT_BIG_SIZE = 2;
	public static final int FONT_SIZE_COUNT = 3;
	
	public static int GetFontSizeCount() {
		return FONT_SIZE_COUNT;
	}
	
	public static String GetFontSizeName(int name) {
		switch(name) {
		case FONT_SMALL_SIZE : return "TxtPetite";
		case FONT_NORMAL_SIZE : return "TxtNormale";
		default : break;
		}
		return "TxtGrande";
	}
}
