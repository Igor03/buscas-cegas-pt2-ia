package Buscas;

import java.util.Collections;
import java.util.LinkedList;
import Problemas.*;
import Estruturas.*;

public class Profundidade implements Busca {

	public No no;
	public int profunfidadeGeral = -1;
	Problema problema;
	LinkedList<No> borda = new LinkedList<No>();
	LinkedList<No> caminho = new LinkedList<>();
	LinkedList<No> historico = new LinkedList<>();

	@Override
	public LinkedList<No> busca(Problema problema) {

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

			if (problema.testeDeObjetivo(no.estado)) {
				mostrarCaminho();
				System.out.println("-----------------------------------");
				System.err.println("Objetivo alcancado!!!");
				System.out.println("-----------------------------------");
				return caminho;
			}

			for (int i = 0; i < expandir(this.no).size(); i++)
				this.borda.addFirst(expandir(no).get(i));
		}

	}

	@Override
	public LinkedList<No> expandir(No no) {

		LinkedList<No> sucessores = new LinkedList<No>();

		for (int i = 0; i < problema.funcaoSucessora(no.estado).size(); i++) {
			No s = new No(problema.funcaoSucessora(no.estado).get(i));
			s.estado = problema.funcaoSucessora(no.estado).get(i);
			s.pai = no;
			s.acao = problema.funcaoSucessora(no.estado).get(i);
			s.profundidade = profunfidadeGeral + 1;
			sucessores.addFirst(s);
		}
		Collections.shuffle(sucessores); // Para minimizar o problema dos loops
											// inifinitos
		return sucessores;
	}

	@Override
	public void mostrarCaminho() {

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
		System.out.println("Profundidade: " + caminho.size());
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

	/* Funcao de testes */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Busca agente = new Profundidade();
		Problema problema = new Romenia("Arad", "Bucareste");

		agente.busca(problema);

	}
}
