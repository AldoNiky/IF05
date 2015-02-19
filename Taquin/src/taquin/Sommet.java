package taquin;

public class Sommet {

	private int valeur;
	private boolean visiter;
	
	public Sommet(int v) {
		this.valeur=v;
		this.visiter=false;
	}
	
	public void visiter(){
		this.visiter=true;
	}

	public int getValeur() {
		return valeur;
	}

	public boolean HasBeenVisited() {
		return visiter;
	}

	public String toString(){
		return this.valeur+"";
	}
}
