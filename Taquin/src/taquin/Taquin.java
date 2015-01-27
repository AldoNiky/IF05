package taquin;

import java.util.ArrayList;

public class Taquin {
	private ArrayList<Integer> damier = new ArrayList<Integer>();
	/**
	 * Constructeur d'un Taquin
	 * @param taille
	 * <p>
	 * taille de la matrice
	 * </p>
	 */
	public Taquin(int taille) {
		for(int i = 0; i < Math.pow(taille, 2); i++) damier.add(i);
		do{
			melanger();
		}while(!this.estResolvable());
	
	}
	// 0 : Haut
	// 1 : Bas
	// 2 : Gauche
	// 3 : Droite
	/**
	 * Cette methode deplace la case vide selon la direction donnee en parametre
	 * @param direction
	 * L'entier determinant la direction
	 * @throws ImpossibleMoveException
	 * Une erreur est renvoyé si le déplacement n'est pas possible
	 */
	public void deplacement(int direction) throws ImpossibleMoveException {
		if(estAuBord(direction)) throw new ImpossibleMoveException();
		int taille = (int) Math.sqrt(damier.size()), vide = damier.indexOf(0);
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
		int nbCarreaux = damier.size(), taille = (int) Math.sqrt(damier.size()), vide = damier.indexOf(0);
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
		for(int i = 0; i < nbCarreaux - 1; i++) if(damier.get(i) != damier.get(i + 1) - 1) return false;
		return true;
	}
	/**
	 * Methode pour savoir si le jeu est resoluble
	 * @return
	 * Un bolean true si il est et false sinon
	 */
	public boolean estResolvable(){
		Object[]t=this.damier.toArray();
		int permutation=0;
		for(int i=0;i<t.length;i++){
			if((int)t[i]!=i){
				for(int j=i+1;j<t.length;j++){
					if((int)t[j]==i){
						t[j]=t[i];
						t[i]=i;
						permutation++;
						break;
					}
				}
			}
		}
		return permutation%2==0;
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
		int nbCarreaux = damier.size(), taille = (int) Math.sqrt(damier.size());
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
}
