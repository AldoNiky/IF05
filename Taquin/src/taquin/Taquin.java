package taquin;

import java.util.*;

public class Taquin implements Jeu {
	private ArrayList<Sommet> damier = new ArrayList<Sommet>();
	private TreeMap<Character,Integer> tabCorrespondance = new TreeMap<Character,Integer>();
	private int taille;

	/**
	 * Constructeur d'un Taquin
	 * @param taille
	 * <p>
	 * taille de la matrice
	 * </p>
	 */
	public Taquin(int taille) {
		this.taille = taille;
		for(int i = 0; i < Math.pow(taille, 2); i++) damier.add(new Sommet(i));
		do melanger();
		while(!estResolvable());
		
		//On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'),new Integer(1));
		tabCorrespondance.put(new Character('s'),new Integer(0));
		tabCorrespondance.put(new Character('q'),new Integer(3));
		tabCorrespondance.put(new Character('d'),new Integer(2));
		
	}
	
	public Taquin(Taquin t){
		this.damier=t.damier;
		this.tabCorrespondance=t.tabCorrespondance;
		this.taille=t.taille;
	}
	
	public TreeMap<Character, Integer> getTabCorrespondance() {
		return tabCorrespondance;
	}
	/**
	 * Cette methode deplace la case vide selon la direction donnee en parametre
	 * 0 : Haut
	 * 1 : Bas
	 * 2 : Gauche
	 * 3 : Droite
	 * @param direction
	 * L'entier determinant la direction
	 * @throws ImpossibleMoveException
	 * Une erreur est renvoyee si le deplacement n'est pas possible
	 */
	public void deplacement(int direction) throws ImpossibleMoveException {
		if(estAuBord(direction)) throw new ImpossibleMoveException();
		for(int i=0;i<this.damier.size();i++){
			if(this.damier.get(i).getValeur()==0){
				switch(direction) {
				case 0: inverser(i, i - taille); break;
				case 1: inverser(i, i + taille); break;
				case 2: inverser(i, i - 1); break;
				case 3: inverser(i, i + 1); break;
				}
				break;
			}
		}
	}
	/**
	 * Permet  de savoir si la case est au bord de la grille
	 * @param direction
	 * Determine la direction voulue
	 * @return
	 * Un boolean true si la case blanche est au bord, false sinon
	 */
	public boolean estAuBord(int direction) {
		int nbCarreaux = damier.size(), vide=0;
		for(int i=0;i<this.damier.size();i++){
			if(this.damier.get(i).getValeur()==0) vide=i;
		}
		switch(direction) {
		case 0: return vide < taille;
		case 1: return vide >= nbCarreaux - taille;
		case 2: return vide % taille == 0;
		case 3: return (vide + 1) % taille == 0;
		default: return true;
		}
	}
	/**
	 * Methode pour savoir si le jeu est resolu
	 * @return
	 * Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		int nbCarreaux = damier.size();
		for(int i = 0; i < nbCarreaux - 1; i++) if(damier.get(i).getValeur() != damier.get(i + 1).getValeur() - 1) return false;
		return true;
	}
	/**
	 * Methode pour savoir si le jeu est resoluble
	 * @return
	 * Un bolean true si il est et false sinon
	 */
	public boolean estResolvable() {
		Object[] t = damier.toArray();
		int permutation = 0;
		for (int i = 0; i < t.length; i++) {
			Sommet s=(Sommet)t[i];
			if (s.getValeur() != i) {
				for(int j = i + 1; j < t.length; j++) {
					Sommet s2=(Sommet)t[j];
					if( s2.getValeur() == i) {
						t[j] = s;
						t[i] = s2;
						permutation++;
						break;
					}
				}
			}
		}
		return permutation % 2 == 0;
	}
	/**
	 * Permute deux cases
	 * @param index1
	 * L'indice de la case vide
	 * @param index2
	 * L'indice de l'autre case
	 */
	public void inverser(int index1, int index2) {
		Sommet carreau1=damier.get(index1), carreau2=damier.get(index2);
		damier.remove(index1);
		damier.add(index1,carreau2);
		damier.remove(index2);
		damier.add(index2,carreau1);
	}
	/**
	 * Melange la grille de jeu
	 * @return
	 * Retourne la grille de jeu, permet de melanger successivement
	 */
	public ArrayList<Sommet> melanger() {
		int n, nbCarreaux = damier.size();
		for(int i = 0; i < nbCarreaux; i++) {
			do {
				n = (int) (Math.random() * nbCarreaux);
			} while(i == n);
			inverser(i, n);
		}
		return this.damier;
	}
	/**
	 * Affiche du jeu
	 */
	public String toString() {
		int nbCarreaux = damier.size();
		String s = "";
		for(int i = 0; i < nbCarreaux; i++) {
			int n = damier.get(i).getValeur();
			if(n == 0) s += "   ";
			else if(n < 10) s += " " + n + " ";
			else s += n + " ";
			if((i + 1) % taille == 0) s += "\n";
		}
		return s;
	}
	public ArrayList<Sommet> getDamier() {
		return damier;
	}
	public int getTaille() {
		return taille;
	}
	
	private boolean estAuBord(ArrayList<Sommet> damier,int direction) {
		int nbCarreaux = damier.size(), vide=0;
		for(int i=0;i<damier.size();i++){
			if(damier.get(i).getValeur()==0) vide=i;
		}
		switch(direction) {
		case 0: return vide < taille;
		case 1: return vide >= nbCarreaux - taille;
		case 2: return vide % taille == 0;
		case 3: return (vide + 1) % taille == 0;
		default: return true;
		}
	}
	
	private ArrayList<Sommet> deplacementAuto(ArrayList<Sommet> damier,int direction) throws ImpossibleMoveException {
		ArrayList<Sommet> d=this.clone(damier);
		if(estAuBord(d,direction)) throw new ImpossibleMoveException();
		int changePost=-1;
		for(int i=0;i<damier.size();i++){
			if(damier.get(i).getValeur()==0){
				switch(direction) {
				case 0:
					changePost=i-taille;
					break;
				case 1: 
					changePost=i+taille;
					break;
				case 2:
					changePost=i-1;
					break;
				case 3: 
					changePost=i+1;
					break;
				}
				if(!d.get(changePost).HasBeenVisited()){
					d.get(changePost).visiter();
					inverser(d,i, changePost); 
				}else throw new ImpossibleMoveException();
				break;
			}
		}
		return d;
	}
	
	private void inverser(ArrayList<Sommet> damier,int index1, int index2) {
		Sommet carreau1=damier.get(index1), carreau2=damier.get(index2);
		damier.remove(index1);
		damier.add(index1,carreau2);
		damier.remove(index2);
		damier.add(index2,carreau1);
	}
	
	private boolean estResolu(ArrayList<Sommet> damier) {
		int nbCarreaux = damier.size();
		for(int i = 0; i < nbCarreaux - 1; i++) if(damier.get(i).getValeur() != damier.get(i + 1).getValeur() - 1) return false;
		return true;
	}
	
	private void profondeur(ArrayList<String> s, ArrayList<Sommet> damier,String chemin){
		for(int i=0;i<4;i++){
			System.out.println("Tour "+i+": "+damier);
			try{
				if(this.estResolu(damier)){
					s.add(chemin);
					break;
				}
				else{
					ArrayList<Sommet> d=this.deplacementAuto(damier, i);
					System.out.println("Après modif de la direction "+i+" : "+d);
					this.profondeur(s, d, chemin+i);
				}
			}catch(ImpossibleMoveException e){
				
			}
		}
	}
	
	private ArrayList<Sommet> clone(ArrayList<Sommet> d){
		ArrayList<Sommet> damier=new ArrayList<Sommet>();
		for(int i=0;i<d.size();i++) damier.add(d.get(i).clone());
		return damier;
	}
	
	public String resolution() throws NoCombinaisonException{
		ArrayList<String> solutions=new ArrayList<String>();
		this.profondeur(solutions,damier, "");
		Collections.sort(solutions,new comparerList());
		if(solutions.isEmpty()) throw new NoCombinaisonException("Erreur ! Pas de combinaison !");
		else return solutions.get(0);
	}
	
}
