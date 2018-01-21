package Buscas;

import java.util.LinkedList;

import Estruturas.*;
import Problemas.*;

public class CustoUniforme implements Busca {

	public No no;
	public int profundidadeGeral = -1;
	public Integer custoTotal = 0;
	public LinkedList<No> borda = new LinkedList<>();
	public LinkedList<No> caminho = new LinkedList<>();
	public LinkedList<Estado> explorados = new LinkedList<>();
	public Problema problema;

	@Override
	public LinkedList<No> buscar(Problema problema, Estado estadoInicial) {
		// TODO Auto-generated method stub
		this.problema = problema;
		this.no = new No(estadoInicial);
		this.no.profundidade = 0;
		this.no.custoDePasso = 0;
		borda.add(no);

		while (true) {
			if (borda.isEmpty()) {
				System.err.println("ERRO!");
				return null;
			}
			no = borda.remove();
			no.profundidade = ++profundidadeGeral;
			explorados.add(no.estado);

			if (problema.testeDeObjetivo(no.estado)) {
				mostrarCaminho();
				System.out.println();
				return caminho;
			}

			LinkedList<No> expandidos = expandir(no);

			for (int i = 0; i < expandidos.size(); i++) {
				if (!this.explorados.contains(expandidos.get(i).estado)) {
					this.borda.add(expandir(no).get(i));
					ordenarPorCusto(borda);
					/*
					 * Parte finald o algoritmo do livro. Pra mim nao faz o
					 * menor sentido
					 */
				} else if (borda.contains(expandidos.get(i)))
					for (int j = 0; j < borda.size(); j++)
						if (borda.get(i).equals(expandidos.get(i))
								&& borda.get(i).custoDePasso > expandidos.get(i).custoDePasso) {
							borda.set(i, expandidos.get(i));
							ordenarPorCusto(borda);
							break;
						}
			}
		}
	}

	@Override
	public LinkedList<No> expandir(No no) {
		// TODO Auto-generated method stub
		LinkedList<No> sucessores = new LinkedList<>();

		for (int i = 0; i < problema.funcaoSucessora(no.estado).size(); i++) {
			No s = new No(problema.funcaoSucessora(no.estado).get(i));
			s.estado = problema.funcaoSucessora(no.estado).get(i);
			s.pai = no;
			s.acao = problema.funcaoSucessora(no.estado).get(i);
			s.custoDePasso = problema.getCustoCaminho(no.estado, s.estado) + no.custoDePasso;
			s.profundidade = profundidadeGeral + 1;
			sucessores.add(s);
		}
		return sucessores;
	}

	@Override
	public void mostrarCaminho() {
		// TODO Auto-generated method stub

		No noAux = no;
		LinkedList<No> caminho = new LinkedList<No>();

		while (noAux != null) {
			caminho.addFirst(noAux);
			noAux = noAux.pai;
		}

		System.out.println("INICIO: ---> " + problema.getNomeEstadoInicial());
		System.out.println("OBJETIVO: ---> " + problema.getNomeObjetivo());

		System.out.println("...........................");
		for (int i = 0; i < caminho.size(); i++) {
			if (i + 1 < caminho.size()) {
				System.out.println(caminho.get(i).estado.nome + " --> " + caminho.get(i + 1).estado.nome + " #"
						+ caminho.get(i + 1).custoDePasso);
				custoTotal = custoTotal + problema.getCustoCaminho(caminho.get(i).estado, caminho.get(i + 1).estado);
			}
		}

		System.out.println("...........................");
		System.out.println("OBJETIVO ALCANCADO");
		System.out.println("Custo total: " + this.custoTotal);
	}

	@Override
	public LinkedList<No> getBorda() {
		// TODO Auto-generated method stub
		return borda;
	}

	@Override
	public LinkedList<No> getHistorico() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Este metodo eh exclusivo para busca de custo uniforme
	 */
	private void ordenarPorCusto(LinkedList<No> borda) {
		for (int i = 0; i < borda.size(); i++) {
			for (int j = 0; j < borda.size(); j++) {
				if (borda.get(i).custoDePasso < borda.get(j).custoDePasso) {
					No aux = borda.get(i);
					borda.set(i, borda.get(j));
					borda.set(j, aux);
				}
			}
		}
	}

	public void mostrarBorda(LinkedList<No> borda) {
		for (No no : borda) {
			System.out.println(no.estado.nome + " " + " \t" + no.custoDePasso);
		}
	}

	public static void main(String[] args) {

		Busca agente = new CustoUniforme();
		// Problema problema = new Romenia("Sibiu", "Bucareste");
		Problema problema = new Aspirador("ESS", "ESL");
		agente.buscar(problema, problema.getEstadoInicial());

	}

}
