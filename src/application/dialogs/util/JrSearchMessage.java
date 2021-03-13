/*
 * Created on Feb 8, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package application.dialogs.util;

/**
 * @author artigue
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JrSearchMessage {
	private int indexCase = 0;
	private int page = 0;
	private int partiel = 0;
	private int total;
	private boolean raz;
	private boolean approx;
	private boolean inconnu;
	private String comment = "";
	
	public JrSearchMessage(int index,int page,int partiel,int total,boolean raz,boolean approx,boolean inconnu,String comment) {
		indexCase = index;
		this.page = page;
		this.partiel = partiel;
		this.total = total;
		this.raz = raz;
		this.approx = approx;
		this.inconnu = inconnu;
		this.comment = comment;
	}
	
	public int getIndex() {
		return indexCase;
	}
	public boolean isApprox() {
		return approx;
	}
	public String getComment() {
		return comment;
	}
	public boolean isInconnu() {
		return inconnu;
	}
	public int getPage() {
		return page;
	}
	public int getPartiel() {
		return partiel;
	}
	public boolean isRaz() {
		return raz;
	}
	public int getTotal() {
		return total;
	}
}
