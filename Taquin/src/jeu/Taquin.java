package jeu;

import java.util.*;


public class Taquin implements Jeu {
	
	private int[][] damier;
	public HashMap<Integer, Integer[]> damierFin;
	private TreeMap<Character, Integer> tabCorrespondance = new TreeMap<Character, Integer>();
	private int hauteur;
	private int longueur;

	/**
	 * Constructeur d'un Taquin
	 * 
	 * @param les dimensions du taquin
	 * <p>
	 * Taille de la matrice
	 * </p>
	 */
	public Taquin(int pHauteur, int pLongueur) {
		this.hauteur = pHauteur;
		this.longueur = pLongueur;
		this.damier= new int[longueur][hauteur];
		this.damierFin= new HashMap<Integer, Integer[]>();
		
		int numero=1;
		for(int i=0; i<longueur;i++){
			for(int j=0; j<hauteur; j++){
				int indiceFin=0;
				if(i!=longueur-1 || j!=hauteur-1){
					indiceFin=numero;
					damier[i][j]=numero;
				}else damier[i][j]=0;
				Integer[]t={i,j};
				damierFin.put(indiceFin, t);
				numero++;
			}
		}

		for(int i=0; i<90; i++)
			melanger();
		

		// On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'), new Integer(0));
		tabCorrespondance.put(new Character('s'), new Integer(1));
		tabCorrespondance.put(new Character('q'), new Integer(2));
		tabCorrespondance.put(new Character('d'), new Integer(3));

	}
	/**
	 * Melange la grille de jeu
	 * 
	 * @return Retourne la grille de jeu, permet de melanger successivement
	 */
	public int[][] melanger() {
		try{
			int entier=(int) (Math.random() * 4);
			deplacement(entier);
			//System.out.println(entier);
		}catch(IndexOutOfBoundsException e){}
		return this.damier;
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
	 * Methode pour savoir si le jeu est resolu
	 * 
	 * @return Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		for(int i=0; i<damier.length;i++){
			for(int j=0; j<damier[0].length-1;j++){
				Integer[] t=damierFin.get(damier[i][j]);
				if(t[0]!=i || t[1]!=j)
					return false;
			}
		}
		return true;
	}
	/**
	 * Retourne les coordonnees d'une case particuliere
	 * @return
	 * Un tableau d'entier avec abscisse et ordonnee
	 */
	private int[] indexOf(int nb){
		int[] t=new int[2];
		for(int i=0; i<longueur;i++){
			for(int j=0; j<hauteur; j++){
				if (damier[i][j]==nb){
					t[0]=i;t[1]=j;
					return t;
				}
			}
		}
		return null;
	}
	/**
	 * Methode qui determine la distance entre la position initiale
	 * de la case vide que l'on nommera ini et sa position finale
	 * pour que le taquin puisse pretendre etre resolu dont on 
	 * nommera fin : Dman=|Xfin-Xini|+|Yfin-Yini|
	 * @return un entier qui sera la distance dite de Mannathan
	 */
	public int distanceManhattan() {
		int[]pos0=this.indexOf(0);
		double Yini=pos0[0], Xini=pos0[1], Yfin=longueur-1, Xfin=hauteur-1;
		return (int)(Math.sqrt(Math.pow(Xfin-Xini, 2))+Math.sqrt(Math.pow(Yfin-Yini, 2)));
	}
	/**
	 * 
	 */
	public TreeMap<Character, Integer> getTabCorrespondance() {
		return tabCorrespondance;
	}
	/**
	 * 
	 * @return
	 */
	public int nbPermutFin(){
		int[][] ini=this.clone();
		int indice=0,permut=0;
		while(!this.estResolu()){
			int [] debut=indexOf(indice);
			Integer [] fin=damierFin.get(indice);
			if(debut[0]!=fin[0] || debut[1]!=fin[1]){
				int nb=damier[fin[0]][fin[1]];
				damier[fin[0]][fin[1]]=damier[debut[0]][debut[1]];
				damier[debut[0]][debut[1]]=nb;
				permut++;
			}
			indice++;
		}
		this.setDamier(ini);
		return permut;
	}
	/**
	 * Methode qui permet de determiner si ce taquin
	 * est resolvable ou non en regardant si le nombre de 
	 * permutation permettant d avoir un taquin resolu est identique
	 * a la distance de Mannathan
	 *  @return vrai si le taquin est resolvable, faux sinon
	 */
	public boolean estResolvable(){
		return this.distanceManhattan()%2==this.nbPermutFin()%2;
	}
	/**
	 * @return un clone du tableau nommer damier
	 */
	public int[][] clone(){
		int[][]t=new int[longueur][hauteur];
		for(int i=0;i<this.damier.length;i++){
			for(int j=0;j<this.damier[0].length;j++){
				t[i][j]=damier[i][j];
			}
		}
		return t;
	}
	/**
	 * Affichage du jeu
	 */
	public String toString() {
		String s="";
		for(int i=0; i<longueur; i++){
			for(int j=0; j<hauteur;j++){
				s+=damier[i][j]+"\t";
			}
			s+="\n";
		}
		return s;
	}
	
	private void setDamier(int[][] d){
		for(int i=0;i<d.length;i++){
			for(int j=0;j<d[0].length;j++){
				this.damier[i][j]=d[i][j];
			}
		}
	}
	/**
	 * 
	 */
	public void deplacement(int direction) throws IndexOutOfBoundsException{
		int temp[]=indexOf(0);
		//System.out.println(temp);
		int valADeplacer;
		switch(direction){
		case 0:
			valADeplacer=damier[temp[0]-1][temp[1]];
			damier[temp[0]-1][temp[1]]=0;
			damier[temp[0]][temp[1]]=valADeplacer;
			break;
		case 1:
			valADeplacer=damier[temp[0]+1][temp[1]];
			damier[temp[0]+1][temp[1]]=0;
			damier[temp[0]][temp[1]]=valADeplacer;
			break;
		case 2:
			valADeplacer=damier[temp[0]][temp[1]-1];
			damier[temp[0]][temp[1]-1]=0;
			damier[temp[0]][temp[1]]=valADeplacer;
			break;
		case 3:
			valADeplacer=damier[temp[0]][temp[1]+1];
			damier[temp[0]][temp[1]+1]=0;
			damier[temp[0]][temp[1]]=valADeplacer;
			break;
		}
	}

}
