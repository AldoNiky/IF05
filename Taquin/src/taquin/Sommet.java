package taquin;

public class Sommet implements Cloneable {

	private int valeur;
	private boolean visiter;
	
	public Sommet(int v) {
		this.valeur=v;
		this.visiter=false;
	}
	
	public void visiter(){
		this.visiter=true;
	}
	
	public Sommet clone(){
		Sommet o=null;
		try {
			o=(Sommet)super.clone();
		} catch (CloneNotSupportedException e) {
			
		}
		return o;
	}
	
	public int getValeur() {
		return valeur;
	}

	public boolean HasBeenVisited() {
		return visiter;
	}

	public String toString(){
		return this.valeur+(this.visiter?"T":"F");
	}
}
