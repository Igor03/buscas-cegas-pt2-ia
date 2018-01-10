package Buscas;

import java.util.LinkedList;
import Estruturas.*;
import Problemas.*;

public class Bidirecional {
	
	Busca busca1; // Inicia da origem
	Busca busca2; // Inicia do objetivo
	No noInicial;
	No noObjetivo;
	
	Problema problema;
	
	LinkedList<No> caminho = new LinkedList<>();
	
	public LinkedList<No> busca(Problema problema, String nomeBusca1, String nomeBusca2) {
		// TODO Auto-generated method stub
		
		noInicial = new No(problema.getEstadoInicial());
		noObjetivo = new No(problema.getObejetivo());
		
		busca1 = identificaBusca(nomeBusca1);
		busca2 = identificaBusca(nomeBusca2);
			
		busca1.busca(problema);
		busca2.busca(problema);
		
		gerarCaminho(busca1.getHistorico(), busca2.getHistorico());
		
		return null;
	}

	public LinkedList<No> expandir(No no) {
		// TODO Auto-generated method stub
		return null;
	}

	public void mostrarCaminho() {
		// TODO Auto-generated method stub

	}
	
	/* Identifica um busca a partir de uma String */
	private Busca identificaBusca (String nomeBusca) {
		
		Busca busca;
		
		if (nomeBusca.toUpperCase().equals("LARGURA")) {
			busca = new Largura();
			return busca;
		} else if (nomeBusca.toUpperCase().equals("PROFUNDIDADE")) {
			busca =  new Profundidade();
			return busca;
		}
		
		System.out.println("Busca < "+nomeBusca+" > nao existe");
		return null;
		
	}
	
	public void gerarCaminho (LinkedList<No> hist1, LinkedList<No> hist2) {
		int index1 = 0;
		int index2 = 0;
		//boolean result = false;
		for (int i=0; i<hist1.size(); i++) {
			for (int j=0; j<hist2.size(); j++) {
				if (hist1.get(i).equals(hist2.get(j))) {
					index1 = i; index2 = j;
					System.out.println("Encotrou");
					break;
				}
			}
		}
		for (int i=0; i<index1; i++) {
			caminho.add(hist1.get(i));
		}
		for (int i=0; i<index2-1; i++) {
			caminho.add(hist2.get(i+index1));
		}
		
		System.out.println("-----CAMINHO------");
		for (int i = 0; i < caminho.size(); i++) {
			System.out.println("Entrou");
			System.out.println(caminho.get(i));
		}
	}
	
	public static void main (String[] args) {
		
		Problema problema = new Romenia("Arad", "Bucareste");
		Bidirecional agente = new Bidirecional();
		agente.busca(problema, "Largura", "Largura");
		
	}
	
}