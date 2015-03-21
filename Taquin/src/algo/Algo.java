package algo;

import java.util.*;
import jeu.*;

public class Algo{
	private ArrayList<Sommet> marque= new ArrayList<>();
	private EnsembleATraiter aTraiter;
	private Sommet initial;
	
	public Algo(Sommet pInit, String pTypeEnsembleATraiter){
		this.initial=pInit;
		switch(pTypeEnsembleATraiter){
		case "Pile":
			aTraiter=new Pile();
			break;
		case "File":
			aTraiter=new File();
			break;
		case "Tas":
			int typeComp=1;
			aTraiter=new Tas(typeComp);
			break;
		}
	}
	
	public void run(){
		marque.add(this.initial);
		ArrayList<Sommet> succ=initial.succ();
		for(Sommet p : succ)
			aTraiter.ajout(p);
		while(aTraiter.nonVide()){
			Sommet pos = aTraiter.prend();
			succ = pos.succ();
			for(Sommet p: succ){
				if(!marque.contains(p)){
					marque.add(p);
					aTraiter.ajout(p);
				}
			}
		}
	}
}
