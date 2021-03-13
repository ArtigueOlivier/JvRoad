/*
 * Created on Feb 2, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map.objets;

import java.io.File;
import java.io.IOException;

import application.JrApplicationOption;
import tools.JrDrawTools;
import tools.files.JrFileRead;
import names.JrObjName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrObjects {
	private static JrObjects singleton = new JrObjects();
	private JrObject[] objects = new JrObject [JrObjName.OBJ_COUNT];
	private int objPersoCount = 0;
	
	private JrObjects() {
		File objopt = new File("objects.ini");
		JrFileRead file = null;
		
		// Dynamic Objects for Version PRO
		if (JrApplicationOption.GetCode() == 129834765) {
			if (objopt.exists()) {
				file = new JrFileRead("objects.ini");
				try {
					if (file.read()) {
						file.setCurrentSection("Objects");
						objPersoCount = file.getIntegerValue("count", 0);
						if (objPersoCount > 0) {
							objects = new JrObject [JrObjName.OBJ_COUNT + objPersoCount];
						}
					}
				} catch (IOException e) {
				}
			} 
		}
		objects[JrObjName.OBJ_NOTHING] = new JrObject();
		objects[JrObjName.OBJ_HOUSE] = new JrObjectBatiment(JrObjName.OBJ_HOUSE);
		objects[JrObjName.OBJ_CROIX] = new JrObjectBatiment(JrObjName.OBJ_CROIX);
		objects[JrObjName.OBJ_CHAPELLE] = new JrObjectBatiment(JrObjName.OBJ_CHAPELLE);
		objects[JrObjName.OBJ_EGLISE] = new JrObjectBatiment(JrObjName.OBJ_EGLISE);
		objects[JrObjName.OBJ_MOSQUEE] = new JrObjectBatiment(JrObjName.OBJ_MOSQUEE);
		objects[JrObjName.OBJ_HOPITAL] = new JrObjectBatiment(JrObjName.OBJ_HOPITAL);
		objects[JrObjName.OBJ_STATION] = new JrObjectBatiment(JrObjName.OBJ_STATION);
		objects[JrObjName.OBJ_PARKING] = new JrObjectBatiment(JrObjName.OBJ_PARKING);
		objects[JrObjName.OBJ_STOP] = new JrObjectPanneau(JrObjName.OBJ_STOP);
		objects[JrObjName.OBJ_CEDER] = new JrObjectPanneau(JrObjName.OBJ_CEDER);
		objects[JrObjName.OBJ_SENSINTER] = new JrObjectPanneau(JrObjName.OBJ_SENSINTER);
		objects[JrObjName.OBJ_FEUX] = new JrObjectPanneau(JrObjName.OBJ_FEUX);
		objects[JrObjName.OBJ_ARBRE] = new JrObjectPanneau(JrObjName.OBJ_ARBRE);
		objects[JrObjName.OBJ_SAPIN] = new JrObjectPanneau(JrObjName.OBJ_SAPIN);
		objects[JrObjName.OBJ_VRAIL] = new JrObjectChemin(JrObjName.OBJ_VRAIL);
		objects[JrObjName.OBJ_HRAIL] = new JrObjectChemin(JrObjName.OBJ_HRAIL);
		objects[JrObjName.OBJ_VRIVER] = new JrObjectChemin(JrObjName.OBJ_VRIVER);
		objects[JrObjName.OBJ_HRIVER] = new JrObjectChemin(JrObjName.OBJ_HRIVER);
		objects[JrObjName.OBJ_VBARRIERE] = new JrObjectChemin(JrObjName.OBJ_VBARRIERE);
		objects[JrObjName.OBJ_HBARRIERE] = new JrObjectChemin(JrObjName.OBJ_HBARRIERE);
		objects[JrObjName.OBJ_CERCLE] = new JrObjectDivers(JrObjName.OBJ_CERCLE);
		objects[JrObjName.OBJ_ROND] = new JrObjectDivers(JrObjName.OBJ_ROND);
		objects[JrObjName.OBJ_HOUSEG] = new JrObjectBatiment(JrObjName.OBJ_HOUSEG);
		objects[JrObjName.OBJ_CP] = new JrObjectBatiment(JrObjName.OBJ_CP);
		if (objPersoCount > 0) {
			//System.out.println("Objects Perso: " + objPersoCount);
			for(int i = 0; i < objPersoCount; i++) {
				objects[JrObjName.OBJ_COUNT+i] = new JrObjectPerso(5000 + i);
			}
		}		
	}
	
	/**
	 * Return true if object needs 2 cases for height
	 * @param name
	 * @return
	 */
	public static boolean IsBigHeight(int name) {
		int ref = (name >= 5000)? (name - 5000 + JrObjName.OBJ_COUNT) : name;
		return singleton.objects[ref].isBigHeight();
	}

	/**
	 * Return true if object needs 2 cases for width
	 * @param name
	 * @return
	 */
	public static boolean IsBigWidth(int name) {
		int ref = (name >= 5000)? (name - 5000 + JrObjName.OBJ_COUNT) : name;
		return singleton.objects[ref].isBigWidth();
	}	
	public static void Draw(int name,JrDrawTools dt,float x,float y,float w,float h,boolean active,int currentView) {
		float dx = w * 0.04f;
		float dy = h * 0.04f;
		int ref = (name >= 5000)? (name - 5000 + JrObjName.OBJ_COUNT) : name;
		singleton.objects[ref].draw(dt,x+dx,y+dy,w-dx-dx,h-dy-dy,active,currentView);
	}	
	
	public static boolean IsDrawOver(int name) {
		int ref = (name >= 5000)? (name - 5000 + JrObjName.OBJ_COUNT) : name;
		return singleton.objects[ref].isDrawOver();
	}

	public static String GetTitle(int name) {
		int ref = (name >= 5000)? (name - 5000 + JrObjName.OBJ_COUNT) : name;
		return singleton.objects[ref].getTextHelp();		
	}
	
	public static String GetImageFilename(int name) {
		int ref = (name >= 5000)? (name - 5000 + JrObjName.OBJ_COUNT) : name;
		return singleton.objects[ref].getImageFilename();		
	}
	
	public static int getObjectCount() {
		return singleton.objPersoCount + JrObjName.OBJ_COUNT;		
	}
	
	public static int getObjectPersoCount() {
		return singleton.objPersoCount;
	}
	
	
}
