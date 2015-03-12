package jeu;

import java.util.*;
import java.io.*;

import exceptions.*;

public class Taquin implements Jeu {
	
	private Damier damier;
	private TreeMap<Character, Integer> tabCorrespondance = new TreeMap<Character, Integer>();
	private String deplacementEffectue="";

	/**
	 * Constructeur d'un Taquin
	 * 
	 * @param les dimensions du taquin
	 * <p>
	 * Taille de la matrice
	 * </p>
	 */
	public Taquin(int pHauteur, int pLongueur) {
		// On initialise le tableau des correspondances
		tabCorrespondance.put(new Character('z'), new Integer(1));
		tabCorrespondance.put(new Character('s'), new Integer(0));
		tabCorrespondance.put(new Character('q'), new Integer(3));
		tabCorrespondance.put(new Character('d'), new Integer(2));

	}
	
	
/*	public Taquin(String pNomFichier) throws FileNotFoundException{
		//On complete le chemin d'acces au fichier
		String filePath = "fichiersTaquin/"+pNomFichier;
		//... Petit buffer pour la lecture
		BufferedReader bf = new BufferedReader(new FileReader(filePath));
		StringTokenizer line;
		try {
			//Pas tres propre mais on recupere la hauteur et la largeur
			line = new StringTokenizer(bf.readLine());
			hauteur = Integer.parseInt(line.nextToken());
			line = new StringTokenizer(bf.readLine());
			longueur = Integer.parseInt(line.nextToken());
			
			//On initialise la position finale qu'auront les cases
			initialiseDamierFin();
			
			//On initialise le damier avec les valeurs recuperes
			damier= new int [hauteur][longueur];
			for(int i=0; i<hauteur; i++){
				line = new StringTokenizer(bf.readLine());
				for(int j=0; j<longueur; j++)
					damier[i][j]=Integer.parseInt(line.nextToken());
			}
		//On catch une IOException pour les erreurs d'ecriture
		} catch (IOException e) {
			System.out.println("Erreur : "+ e.toString());
		}
		// On initialise le tableau des correspondance
		tabCorrespondance.put(new Character('z'), new Integer(1));
		tabCorrespondance.put(new Character('s'), new Integer(0));
		tabCorrespondance.put(new Character('q'), new Integer(3));
		tabCorrespondance.put(new Character('d'), new Integer(2));

	}*/
	
	
	/**
	 * Permute deux cases
	 * 
	 * @param index1
	 *            L'indice de la case vide
	 * @param index2
	 *            L'indice de l'autre case
	 */
	/*public void inverser(int case1[], int case2[]) {
		int tVal= damier[case1[0]][case1[1]];
		damier[case1[0]][case1[1]]=damier[case2[0]][case2[1]];
		damier[case2[0]][case2[1]]=tVal;
	}*/
	/**
	 * Methode pour savoir si le jeu est resolu
	 * 
	 * @return Un boolean true si le jeu est resolue, false sinon
	 */
	public boolean estResolu() {
		for(int i=0; i<damier.length;i++){
			for(int j=0; j<damier[0].length-1;j++){
				Integer[] t=damierFin.get(damier[i][j]);
//				System.out.println(damier[i][j]+";"+t[0]+";"+t[1]);
				if(t[0]!=i || t[1]!=j) return false;
			}
		}
		return true;
	}
	public TreeMap<Character, Integer> getTabCorrespondance() {
		return tabCorrespondance;
	}
	/**
	 * Methode qui permet de determiner si ce taquin
	 * est resolvable ou non en regardant si le nombre de 
	 * permutation permettant d avoir un taquin resolu est identique
	 * a la distance de Mannathan
	 *  @return vrai si le taquin est resolvable, faux sinon
	 */
	public boolean estResolvable(){
		return damier.distanceManhattan()%2==damier.nbPermutFin()%2;
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

	public void resolutionA(){
		//On garde de cote le taquin de départ
		int[][] ini =this.clone();
		//On utilise un indice pour suivre l'evolution de la case courante (aPlacer)
		int numero=1;
		
		//On boucle sur toutes les lignes de la grille en laissant les deux dernieres
		for(int i=0;i<damier.length-2;i++){
			//On sort l'indice pour l'utiliser pour le placement de la derniere case de la ligne
			int j;
			//On boucle sur tout les cases de la ligne ormis la derniere
			for(j=0;j<damier[0].length-1;j++){
				//On place la case courante en dessous de sa position finale
				placementCase(i-1, j, numero);
				//On glisse vers le haut
				deplacement(1);
				//On passe a la case suivante
				numero++;
			}
			j++;
			//On place la derniere case de la ligne 2 position en dessous de la position finale
			placementCase(i-2,j,numero);
			//Ramene la case precedement place au dessus de la case courante
			placementCase(i-1,j,numero);
			//Ramene la case blanche
			placementCase(i,j,0);
			//On glisse deux fois vers le haut
			deplacement(1);
			deplacement(1);
			//Ramener la case blanche
			placementCase(i,j-1,0);
			//Deux déplacements
			deplacement(3);
			deplacement(1);
		}
		
		//On boucle sur les colonnes en laissant les 2 dernieres
		for(int j=0; j< damier[0].length-2; j++){
			int i;
			for(i=0; i<damier.length; i++){
				placementCase(i,j+1,numero);
				placementCase(i,j,0);
				deplacement(3);
				numero++;
			}
			i++;
			placementCase(i,j+2,numero);
			placementCase(i,j+1,numero);
			placementCase(i,j,0);
			deplacement(3);
			deplacement(3);
			placementCase(i-1,j,0);
			deplacement(1);
			deplacement(3);
		}
		this.setDamier(ini);
	}
	/**
	 * 
	 */
	private void placementCase(int iFinal, int jFinal, int num){
		//On peut deplacer soit la case vide soit une autre case
		
		//On enregistre la position de la case a déplacer
		int iDepart = indexOf(num)[0];
		int jDepart = indexOf(num)[1];
		
		//On regarde la distance en i et en j
		int decalageVertical = iFinal -iDepart;
		int decalageHorizontal = jFinal -jDepart;
		
		//On traite dans un premier cas le déplacement de la case blanche
		if(num==0){
			//On traite le decalage vertical
			if(decalageVertical!=0){
				if(decalageVertical<0){
					for(int i=0; i<decalageVertical;i++)
						deplacement(0);
				}else{
					for(int i=0; i<decalageVertical;i++)
						deplacement(1);
				}
			}
			//On traite le decalage horizontal
			if(decalageHorizontal!=0){
				if(decalageHorizontal<0){
					for(int i=0; i<decalageHorizontal;i++)
						deplacement(3);
				}else{
					for(int i=0; i<decalageHorizontal;i++)
						deplacement(2);	
				}
			}
		}else{
		}
		
	}

	@Override
	public String getDeplacementEFfectue() {
		return this.deplacementEffectue;
	}
	
}




















