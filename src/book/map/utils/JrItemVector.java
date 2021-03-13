/*
 * Created on Feb 1, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package book.map.utils;

import names.JrTraitName;
import names.JrVectorTypeName;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrItemVector {
	private int from = -1;
	private int to = -1;
	private int aux = -1;
	private int type = JrVectorTypeName.VECTOR_SEGMENT;
	private int trait = JrTraitName.TRAIT_PATH;
	
	public JrItemVector() {		
	}
	
	public JrItemVector(JrItemVector vect) {
		initialize(vect);
	}
	
	public JrItemVector(int f,int t,int a,int typ,int tra) {
		from = f;
		to = t;
		aux = a;
		type = typ;
		trait = tra;
	}

	public JrItemVector(int f,int t,int tra) {
		from = f;
		to = t;
		trait = tra;
	}

	public JrItemVector(String f,String t,String a,String typ,String tra) {
		from = Integer.parseInt(f);
		to = Integer.parseInt(t);
		aux = Integer.parseInt(a);
		type = Integer.parseInt(typ);
		trait = Integer.parseInt(tra);
	}
	
	public void initialize(JrItemVector vect) {
		from = vect.from;
		to = vect.to;
		aux = vect.aux;
		type = vect.type;
		trait = vect.trait;
	}

	public JrItemVector copy() {
		return new JrItemVector(this);
	}
	
	public void nullify() {
		from = -1;
		to = -1;
		aux = -1;
		type = JrVectorTypeName.VECTOR_SEGMENT;
		trait = JrTraitName.TRAIT_PATH;
	}
	
	public boolean isThis(int val) {
		return ((from == val) || (to == val) || (aux == val))? true : false;
	}
	
	public boolean isEqual(JrItemVector vect) {
		if ((from != vect.from) || (to != vect.to) || (aux != vect.aux))
			return false;
		return ((type == vect.type) && (trait == vect.trait))? true : false;
	}
	
	public boolean isNull() {
		return ((from == -1) || (to == -1))? true : false;
	}
	
	public boolean isValid() {
		if ((from == -1) || (to == -1)) 
			return false;
		return ((to != from) && (to != aux) && (from != aux))? true : false;
	}
	
	public void rotation() {
		int x,y;
		
		if (from != -1) {
			x = from % 11;
			y = from / 11;
			from = (x * 11) + (10 - y);
		}
		if (to != -1) {
			x = to % 11;
			y = to / 11;
			to = (x * 11) + (10 - y);
		}
		if (aux != -1) {
			x = aux % 11;
			y = aux / 11;
			aux = (x * 11) + (10 - y);
		}
	}
	
	public void inverse() {
		int x,y;
		
		if (from != -1)
			from = 120 - from;
		if (to != -1)
			to = 120 - to;
		if (aux != -1)
			aux = 120 - aux;
	}
	
	/**
	 * @return Returns the aux.
	 */
	public int getAux() {
		return aux;
	}
	/**
	 * @param aux The aux to set.
	 */
	public void setAux(int aux) {
		this.aux = aux;
	}
	/**
	 * @return Returns the from.
	 */
	public int getFrom() {
		return from;
	}
	/**
	 * @param from The from to set.
	 */
	public void setFrom(int from) {
		this.from = from;
	}
	/**
	 * @return Returns the to.
	 */
	public int getTo() {
		return to;
	}
	/**
	 * @param to The to to set.
	 */
	public void setTo(int to) {
		this.to = to;
	}
	/**
	 * @return Returns the type.
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type The type to set.
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return Returns the trait.
	 */
	public int getTrait() {
		return trait;
	}
	/**
	 * @param trait The trait to set.
	 */
	public void setTrait(int trait) {
		this.trait = trait;
	}
}
