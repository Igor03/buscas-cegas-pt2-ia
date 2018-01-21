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

	public LinkedList<No> buscar(Problema problema, String nomeBusca1, String nomeBusca2) {
		// TODO Auto-generated method stub

		this.problema = problema;

		noInicial = new No(problema.getEstadoInicial());
		noObjetivo = new No(problema.getObejetivo());

		busca1 = identificaBusca(nomeBusca1);
		busca2 = identificaBusca(nomeBusca2);

		while (verificaInterseccao(busca1.getHistorico(), busca2.getHistorico())[0] == -1) {

			busca1.buscar(problema, noInicial.estado);
			busca2.buscar(problema, noObjetivo.estado);
		}

		gerarCaminho();
		mostrarCaminho(); // Constroi o caminho

		return caminho;
	}

	public void mostrarCaminho() {
		// TODO Auto-generated method stub

		System.out.println("INICIO: ---> " + problema.getNomeEstadoInicial());
		System.out.println("OBJETIVO: ---> " + problema.getNomeObjetivo());

		System.out.println("...........................");
		for (int i = 0; i < caminho.size(); i++)
			if (i + 1 < caminho.size())
				System.out.println(caminho.get(i).estado.nome + " --> " + caminho.get(i + 1).estado.nome);
		System.out.println("...........................");

		System.out.println("OBJETIVO ALCANCADO");

	}

	/* Identifica uma busca a partir de uma String */
	private Busca identificaBusca(String nomeBusca) {

		Busca busca;

		if (nomeBusca.toUpperCase().equals("LARGURA")) {
			busca = new LarguraMod();
			return busca;
		} else if (nomeBusca.toUpperCase().equals("PROFUNDIDADE")) {
			busca = new ProfundidadeMod();
			return busca;
		}

		System.out.println("Busca < " + nomeBusca + " > nao existe");
		return null;

	}

	/*
	 * Esta funcao verica se ha alguma inteseccao de estado nas buscas que estao
	 * sendo realizadas. Se houver, ela retornara os indices dos estados
	 * relacionados aos historicos de n's de ambas as buscas. Caso contrario, o
	 * retorno sera -1.
	 * 
	 */
	private int[] verificaInterseccao(LinkedList<No> borda1, LinkedList<No> borda2) {
		// System.out.println("Testando " + borda1.size() + " " +
		// borda2.size());
		int[] indexes = { -1, -1 };
		for (int i = 0; i < borda1.size(); i++) {
			for (int j = 0; j < borda2.size(); j++) {
				if (borda1.get(i).estado.equals(borda2.get(j).estado)) {
					indexes[0] = i;
					indexes[1] = j;
				}
			}
		}
		return indexes;
	}

	private LinkedList<No> gerarCaminho() {

		No noAux;
		int[] indexes = verificaInterseccao(busca1.getHistorico(), busca2.getHistorico());

		// Primeiro passo
		noAux = busca1.getHistorico().get(indexes[0]);
		while (noAux != null) {
			caminho.addFirst(noAux);
			noAux = noAux.pai;
		}

		// Segundo passo
		noAux = busca2.getHistorico().get(indexes[1]).pai;
		while (noAux != null) {
			caminho.addLast(noAux);
			noAux = noAux.pai;
		}

		return caminho;
	}

	public static void main(String[] args) {

		Problema problema = new Romenia("Oradea", "Neamt");
		// Problema problema = new Aspirador("ESS", "DLL");
		Bidirecional agente = new Bidirecional();
		agente.buscar(problema, "LArgura", "Largura");

	}

}