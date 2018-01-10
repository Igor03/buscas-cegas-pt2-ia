package Buscas;

import java.util.LinkedList;
import Problemas.*;
import Estruturas.*;

public class Largura implements Busca {

	public No no;
	public int profunfidadeGeral = -1;
	Problema problema;
	LinkedList<No> borda = new LinkedList<>();
	LinkedList<No> caminho = new LinkedList<>();
	LinkedList<Estado> explorados = new LinkedList<>();

	LinkedList<No> historico = new LinkedList<>();

	@Override
	public LinkedList<No> busca(Problema problema) {
		// TODO Auto-generated method stub

		this.problema = problema;
		this.borda.add(new No(problema.getEstadoInicial()));

		while (true) {

			if (this.borda.isEmpty()) {
				System.err.println("FALHA");
				return null;
			}

			this.no = this.borda.removeFirst();
			historico.add(no);
			no.profundidade = ++profunfidadeGeral;
			explorados.add(no.estado);

			if (problema.testeDeObjetivo(no.estado)) {
				mostrarCaminho();
				System.out.println("-----------------------------------");
				System.err.println("Objetivo alcancado!!!");
				System.out.println("-----------------------------------");
				return caminho;
			}

			LinkedList<No> expandidos = expandir(no);

			for (int i = 0; i < expandidos.size(); i++)
				if (!this.explorados.contains(expandidos.get(i).estado))
					this.borda.addLast(expandir(no).get(i));
		}

	}

	@Override
	public LinkedList<No> expandir(No no) {
		// TODO Auto-generated method stub

		LinkedList<No> sucessores = new LinkedList<No>();

		for (int i = 0; i < problema.funcaoSucessora(no.estado).size(); i++) {
			No s = new No(problema.funcaoSucessora(no.estado).get(i));
			s.estado = problema.funcaoSucessora(no.estado).get(i);
			s.pai = no;
			s.acao = problema.funcaoSucessora(no.estado).get(i);
			s.profundidade = profunfidadeGeral + 1;
			sucessores.add(s);
		}
		return sucessores;
	}

	@Override
	public void mostrarCaminho() {
		// TODO Auto-generated method stub
		No noAux = no;

		while (noAux != null) {
			caminho.addFirst(noAux);
			noAux = noAux.pai;
		}

		System.out.println("-----------------------------------");
		System.out.println("Estado inicial: " + this.problema.getNomeEstadoInicial());
		System.out.println("Objetivo: " + this.problema.getNomeObjetivo());
		System.out.println("-----------------------------------");

		for (int i = 0; i < caminho.size(); i++) {
			if (i + 1 < caminho.size()) {
				System.out.println(caminho.get(i).estado.nome + " --> " + caminho.get(i + 1).estado.nome);
			}
		}
		System.out.println("Profundidade: " + no.profundidade);

		/* Temporario */

		System.out.println("Historioco\n\n\n");
		for (int i = 0; i < historico.size(); i++) {
			System.out.println(historico.get(i).estado.nome);

		}
	}

	@Override
	public LinkedList<No> getBorda() {
		// TODO Auto-generated method stub
		return borda;
	}
	
	@Override
	public LinkedList<No> getHistorico() {
		// TODO Auto-generated method stub
		return historico;
	}

	/* Funcao para testes */
	public static void main(String[] args) {

		//Problema problema = new Romenia("Arad", "Bucareste");
		Problema problema = new AspiradorDePo("ESS", "DLL");
		Busca agente = new Largura();
		agente.busca(problema);

	}

}
