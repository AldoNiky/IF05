package jeu;

import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeMap;

import exception.ImpossibleMoveException;



public class Taquin implements Jeu {
	private int damier[][];
	private TreeMap<Character, Integer> tabCorrespondance = new TreeMap<Character, Integer>();
	private int hauteur;
	private int largeur;

	/**
	 * Constructeur d'un Taquin
	 * 
	 * @param taille
	 * <p>
	 * Taille de la matrice
	 * </p>
	 */
	public Taquin(int pHauteur, int pLargeur) {
		this.hauteur = pHauteur;
		this.largeur = pLargeur;
		this.damier= new int[largeur][hauteur];
		
		int numero=0;
		for(int i=0; i<largeur;i++){
			for(int j=0; j<hauteur; j++){
				damier[i][j]=numero;
				numero++;
			}
		}
		do
			melanger();
		while (!estResolvable());

		// On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'), new Integer(1));
		tabCorrespondance.put(new Character('s'), new Integer(0));
		tabCorrespondance.put(new Character('q'), new Integer(3));
		tabCorrespondance.put(new Character('d'), new Integer(2));

	}
	/**
	 * Getteurs sur le tableau des correspondance
	 * <p>
	 * Correspondance sur entre les caracteres et les entier correspondant au deplacement
	 * </p>
	 */
	public TreeMap<Character, Integer> getTabCorrespondance() {
		return tabCorrespondance;
	}
	/**
	 * Cette methode deplace la case vide selon la direction donnee en parametre
	 * 0 : Haut 1 : Bas 2 : Gauche 3 : Droite
	 * 
	 * @param direction
	 *            L'entier determinant la direction
	 * @throws ImpossibleMoveException
	 *             Une erreur est renvoyee si le deplacement n'est pas possible
	 */
	public void deplacement(int direction) throws ImpossibleMoveException {
		// Si la case sort de la grille avec le deplacement on retourne une
		// erreur
		if (estAuBord(direction))
			throw new ImpossibleMoveException();
		// On recupere la position de la case vide
		int vide[]=getPositionCaseVide();
		int tADeplacer[]=getPositionCaseVide();
		switch (direction) {
		case 0:
			tADeplacer[1]=vide[1]-1;
			break;
		case 1:
			tADeplacer[1]=vide[1]+1;
			break;
		case 2:
			tADeplacer[0]=vide[0]-1;
			break;
		case 3:
			tADeplacer[0]=vide[0]+1;
			break;
		}
		inverser(vide, tADeplacer);
	}

	/**
	 * Permet de savoir si la case est au bord de la grille
	 * 
	 * @param direction
	 *            Determine la direction voulue
	 * @return Un boolean true si la case blanche est au bord, false sinon
	 */
	public boolean estAuBord(int direction) {
		int temp[]=new int[2];
		temp=getPositionCaseVide();
		switch(direction){
		case 0:
			if(temp[1]==0)
				return false;
			break;
		case 1:
			if(temp[1]==damier[0].length-1)
				return false;
			break;
		case 2:
			if(temp[0]==0)
				return false;			
			break;
		case 3:
			if(temp[0]==damier.length-1)
				return false;			
			break;
		}
		return true;
	}

	/**
	 * Methode pour savoir si le jeu est resolu
	 * 
	 * @return Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		//On verifie la position de la case vide
		if(damier[largeur-1][hauteur-1]!=0)
			return false;
		//On verifie toutes les autres cases
		int indice=1;
		for(int i=0; i<this.largeur;i++){
			for(int j=0; j<this.hauteur;j++){
				if(i==largeur-1 && j==hauteur-1)
					return true;
				if(damier[i][j]!=indice)
					return false;
				indice++;
			}
		}
		return true;
	}

	/**
	 * Methode pour savoir si le jeu est resoluble
	 * 
	 * @return Un bolean true si il est et false sinon
	 */
	public boolean estResolvable() {
		int tPosVide[]=getPositionCaseVide();
		int pariteCaseVide=( ((largeur-1)-tPosVide[0]) + ((hauteur-1)-tPosVide[1])) %2;
		
		int pariteAutreCase=0;
		int nbPermut=0;
		for(int i=0; i<largeur;i++){
			for(int j=0; j<hauteur;j++){
				nbPermut+=nbPermutationsPourPosFinal(i,j);
			}
		}
		pariteAutreCase=nbPermut%2;
		return pariteCaseVide==pariteAutreCase;
	}

	public int nbPermutationsPourPosFinal(int pX, int pY){
		//TODO
		int val=this.damier[pX][pY];
		int posYFinale=(val/largeur)-1;
		int posXFinale=(val%largeur)-1;
		
		return (posXFinale-pX)+(posYFinale-pY);
	}
	
	/**
	 * Permute deux cases
	 * 
	 * @param index1
	 *            L'indice de la case vide
	 * @param index2
	 *            L'indice de l'autre case
	 */
	public void inverser(int case1[], int case2[]) {
		int tVal= damier[case1[0]][case1[1]];
		damier[case1[0]][case1[1]]=damier[case2[0]][case2[1]];
		damier[case2[0]][case2[1]]=tVal;
	}

	/**
	 * Melange la grille de jeu
	 * 
	 * @return Retourne la grille de jeu, permet de melanger successivement
	 */
	public int[][] melanger() {
		int nbPermutation=10;
		
		int x, y, m, n;
		
		int borneXMax=largeur-1;
		int borneYMax=hauteur-1;
		
		for(int i=0; i<nbPermutation;i++){
			System.out.println("On effectue une permutation");
			x=(int) (Math.random() * borneXMax);
			y=(int) (Math.random() * borneYMax);
			int a[]={x,y};
			n=(int) (Math.random() * borneXMax);
			m=(int) (Math.random() * borneYMax);
			int b[]={n,m};
			inverser(a,b);
		}
		return this.damier;
	}

	/**
	 * Affiche du jeu
	 */
	public String toString() {
		String s="";
		for(int i=0; i<largeur; i++){
			for(int j=0; j<largeur;j++){
				s+=damier[i][j]+"\t";
			}
			s+="\n";
		}
		return s;
	}

	/**
	 * Getteur de la grille de jeu
	 * 
	 * @return On retourne la grille de jeu
	 */
	public int[][] getDamier() {
		return damier;
	}
	
	/**
	 * Execute une serie de coups
	 * 
	 * @param pSerieCoups
	 *            La serie de coups a executer
	 */
	public void executeSerieCoups(Stack<Integer> pSerieCoups) {
		for (int i = 0; i < pSerieCoups.size(); i++) {
			try {
				deplacement(pSerieCoups.pop());
			} catch (ImpossibleMoveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retourne les coordonnees de la case vide
	 * @return
	 * Un tableau d'entier avec abscisse et ordonnee
	 */
	public int[] getPositionCaseVide(){
		for(int i=0; i<largeur;i++){
			for(int j=0; i<hauteur; i++){
				if (damier[i][j]==0){
					return new int[]{i,j};
				}
			}
		}
		//On a forcement une case vide dans le damier d'un taquin
		return null;
	}

	@Override
	public void executeSerieCoupsInverse(Stack<Integer> pSerieCoups) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int distanceManhattan() {
		// TODO Auto-generated method stub
		return 0;
	}
}
