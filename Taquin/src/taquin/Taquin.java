package taquin;

import java.util.*;
import java.util.Map.Entry;

public class Taquin implements Jeu {
	private ArrayList<Integer> damier = new ArrayList<Integer>();
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
		for(int i = 0; i < Math.pow(taille, 2); i++) damier.add(i);
		do melanger();
		while(!estResolvable());
		
		//On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'),new Integer(1));
		tabCorrespondance.put(new Character('s'),new Integer(0));
		tabCorrespondance.put(new Character('q'),new Integer(3));
		tabCorrespondance.put(new Character('d'),new Integer(2));
		
	}
	
	public Taquin(int taille,ArrayList<Integer> d){
		this.taille=taille;
		for(int i=0;i<d.size();i++) this.damier.add(d.get(i)); 
		
		//On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'),new Integer(1));
		tabCorrespondance.put(new Character('s'),new Integer(0));
		tabCorrespondance.put(new Character('q'),new Integer(3));
		tabCorrespondance.put(new Character('d'),new Integer(2));
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
		int vide = damier.indexOf(0);
		switch(direction) {
		case 0: inverser(vide, vide - taille); break;
		case 1: inverser(vide, vide + taille); break;
		case 2: inverser(vide, vide - 1); break;
		case 3: inverser(vide, vide + 1); break;
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
		int nbCarreaux = damier.size(), vide = damier.indexOf(0);
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
		if(damier.get(nbCarreaux-1)!=0) return false;
		for(int i = 0; i < nbCarreaux - 2; i++) if(damier.get(i) != i+1) return false;
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
			if ((int) t[i] != i) {
				for(int j = i + 1; j < t.length; j++) {
					if((int) t[j] == i) {
						t[j] = t[i];
						t[i] = i;
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
		int carreau1 = damier.get(index1), carreau2 = damier.get(index2);
		damier.remove(index1);
		damier.add(index1, carreau2);
		damier.remove(index2);
		damier.add(index2, carreau1);
	}
	/**
	 * Melange la grille de jeu
	 * @return
	 * Retourne la grille de jeu, permet de melanger successivement
	 */
	public ArrayList<Integer> melanger() {
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
			int n = damier.get(i);
			if(n == 0) s += "   ";
			else if(n < 10) s += " " + n + " ";
			else s += n + " ";
			if((i + 1) % taille == 0) s += "\n";
		}
		return s;
	}
	public ArrayList<Integer> getDamier() {
		return damier;
	}
	public int getTaille() {
		return taille;
	}

	public Object getSituationFinale() {
		return null;
	}

	public int[] getCoupPossible() {
		return null;
	}

	public void executeSerieCoups(Stack<Integer> pSerieCoups) {
		for (int i=0; i<pSerieCoups.size(); i++){
			try {
				deplacement(pSerieCoups.pop());
			} catch (ImpossibleMoveException e) {
				e.printStackTrace();
			}
		}
	}

	public String methode2Resolution() throws NoCombinaisonException{
		TreeSet<String> solutions=new TreeSet<String>();
		try {
			this.parcoursProgressif(solutions, this.damier, this.taille, "", 10);
		}catch (StackOverflowError e) {
			
		} catch (TabException e) {
			System.out.println(e.getMessage());
		}
		System.out.println(solutions);
		if(solutions.isEmpty()) throw new NoCombinaisonException("Pas de solutions sur ce taquin !");
		else return solutions.first();
	}
	
	public void parcoursProgressif(TreeSet<String> solutions, ArrayList<Integer> damier,int taille, String chemin,int profMannathan) throws TabException{
		//La profondeur de mannathan va etre decrementer a chaque passage de noeud
		if(profMannathan>0){
			boolean cheminTrouver=false;
			System.out.println("chemin :"+chemin+"\nSolution optimale trouver pour le moment : "+(solutions.isEmpty()?"aucune":solutions.first()));
			System.out.println(damier);
			//Cas de base je regarde si le taquin est resolu
			Taquin p=new Taquin(taille,damier);
			if(!p.estResolu()){
				//Je parcours tout d'abord en largeur les 4 directions pour savoir s'il y a une solution
				for(int i=0;i<4;i++){
					Taquin t=new Taquin(taille,damier);
					try {
						t.deplacement(i);
						// - S'il y en a bien une pas besoin de parcourir un autre noeud sur cette branche puisque ce chemin sera forcement plus petit
						if(t.estResolu()){
							chemin+=ajouterDirection(i);
							solutions.add(chemin);
							cheminTrouver=true;
							break;
						}
					} catch (ImpossibleMoveException e) {
						
					}
				}
				// - S'il n'y en a pas je me lance sur une des 4 directions et je fais un parcours en profondeur
				if(!cheminTrouver){
					for(int i=0;i<4;i++){
						try {
							//NB: ce que j'appelle ici redondance directe c'est par exemple le fait d'avoir fais haut le coup d'avantet que là je fasse bas
							boolean redondanceDirecte=false;
							if(chemin!=""){
								switch(i){
									case 0:
										redondanceDirecte=(chemin.charAt(chemin.length()-1) == 'z');
										break;
									case 1:
										redondanceDirecte=(chemin.charAt(chemin.length()-1) == 's');
										break;
									case 2:
										redondanceDirecte=(chemin.charAt(chemin.length()-1) == 'q');
										break;
									case 3:
										redondanceDirecte=(chemin.charAt(chemin.length()-1) == 'd');
										break;
								}
							}
							// S'il n'y a pas de redondance directe je peux donc parcourir cette branche si c'est possible
							if(!redondanceDirecte){
								Taquin t=new Taquin(taille,damier);
								t.deplacement(i);
								this.parcoursProgressif(solutions, t.damier, taille, chemin+this.ajouterDirection(i), profMannathan-1);
							}
						}catch (ImpossibleMoveException e) {
							
						}
					}
				}
			}	
		}
	}
	
	private String ajouterDirection(int i) throws TabException {
		Iterator<Entry<Character, Integer>> it=this.tabCorrespondance.entrySet().iterator();
		while(it.hasNext()){
			Entry<Character, Integer> e=it.next();
			if(e.getValue()==i){
				return e.getKey()+"";
			}
		}
		throw new TabException("Il y a une erreur dans la table de correspondance !");
	}
	
}
