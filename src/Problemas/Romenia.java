package Problemas;

import java.util.Collections;
import java.util.LinkedList;
import Estruturas.*;

public class Romenia implements Problema {

	public LinkedList<Estado> romenia = new LinkedList<>();
	public LinkedList<Custo> custos = new LinkedList<>();

	public String nomeEstadoInicial;
	public String nomeObjetivo;

	public Estado estadoInicial;
	public Estado objetvo;

	public Estado Arad = new Estado();
	public Estado Timissoara = new Estado();
	public Estado Zerind = new Estado();
	public Estado Sibiu = new Estado();
	public Estado Lugoj = new Estado();
	public Estado Oradea = new Estado();
	public Estado Fagaras = new Estado();
	public Estado Rimnieu_Vilcea = new Estado();
	public Estado Pitesti = new Estado();
	public Estado Bucareste = new Estado();
	public Estado Mehadia = new Estado();
	public Estado Dobreta = new Estado();
	public Estado Craiova = new Estado();
	public Estado Giurgiu = new Estado();
	public Estado Urziceni = new Estado();
	public Estado Hirsova = new Estado();
	public Estado Eforie = new Estado();
	public Estado Vaslui = new Estado();
	public Estado Iasi = new Estado();
	public Estado Neamt = new Estado();

	/* Construtor */
	public Romenia(String nomeEstadoInicial, String nomeObjetivo) {
		inicializarProblema();
		this.estadoInicial = verificaEstado(nomeEstadoInicial);
		this.objetvo = verificaEstado(nomeObjetivo);
		this.nomeEstadoInicial = nomeEstadoInicial;
		this.nomeObjetivo = nomeObjetivo;
	}

	@Override
	public void inicializarProblema() {
		// TODO Auto-generated method stub
		Arad.nome = "Arad";
		Arad.acoes.add(Sibiu);
		Arad.acoes.add(Zerind);
		Arad.acoes.add(Timissoara);

		Timissoara.nome = "Timissoara";
		Timissoara.acoes.add(Arad);
		Timissoara.acoes.add(Lugoj);

		Oradea.nome = "Oradea";
		Oradea.acoes.add(Zerind);
		Oradea.acoes.add(Sibiu);

		Zerind.nome = "Zerind";
		Zerind.acoes.add(Oradea);
		Zerind.acoes.add(Arad);

		Sibiu.nome = "Sibiu";
		Sibiu.acoes.add(Fagaras);
		Sibiu.acoes.add(Rimnieu_Vilcea);
		Sibiu.acoes.add(Oradea);
		Sibiu.acoes.add(Arad);

		Fagaras.nome = "Fagaras";
		Fagaras.acoes.add(Bucareste);
		Fagaras.acoes.add(Sibiu);

		Bucareste.nome = "Bucareste";
		Bucareste.acoes.add(Urziceni);
		Bucareste.acoes.add(Fagaras);
		Bucareste.acoes.add(Pitesti);
		Bucareste.acoes.add(Giurgiu);

		Lugoj.nome = "Lugoj";
		Lugoj.acoes.add(Timissoara);
		Lugoj.acoes.add(Mehadia);

		Mehadia.nome = "Mehadia";
		Mehadia.acoes.add(Dobreta);
		Mehadia.acoes.add(Lugoj);

		Dobreta.nome = "Dobreta";
		Dobreta.acoes.add(Craiova);
		Dobreta.acoes.add(Mehadia);

		Craiova.nome = "Craiova";
		Craiova.acoes.add(Pitesti);
		Craiova.acoes.add(Rimnieu_Vilcea);
		Craiova.acoes.add(Dobreta);

		Pitesti.nome = "Pitesti";
		Pitesti.acoes.add(Bucareste);
		Pitesti.acoes.add(Rimnieu_Vilcea);
		Pitesti.acoes.add(Craiova);

		Rimnieu_Vilcea.nome = "Rimnieu_Vilcea";
		Rimnieu_Vilcea.acoes.add(Pitesti);
		Rimnieu_Vilcea.acoes.add(Craiova);
		Rimnieu_Vilcea.acoes.add(Sibiu);

		Giurgiu.nome = "Giurgiu";
		Giurgiu.acoes.add(Bucareste);

		Urziceni.nome = "Urziceni";
		Urziceni.acoes.add(Hirsova);
		Urziceni.acoes.add(Vaslui);
		Urziceni.acoes.add(Bucareste);

		Eforie.nome = "Eforie";
		Eforie.acoes.add(Hirsova);

		Vaslui.nome = "Vaslui";
		Vaslui.acoes.add(Urziceni);
		Vaslui.acoes.add(Iasi);

		Iasi.nome = "Iasi";
		Iasi.acoes.add(Neamt);
		Iasi.acoes.add(Vaslui);

		Neamt.nome = "Neamt";
		Neamt.acoes.add(Iasi);

		Hirsova.nome = "Hirsova";
		Hirsova.acoes.add(Eforie);
		Hirsova.acoes.add(Urziceni);

		this.romenia.add(Arad);
		this.romenia.add(Zerind);
		this.romenia.add(Timissoara);
		this.romenia.add(Sibiu);
		this.romenia.add(Lugoj);
		this.romenia.add(Oradea);
		this.romenia.add(Fagaras);
		this.romenia.add(Rimnieu_Vilcea);
		this.romenia.add(Bucareste);
		this.romenia.add(Pitesti);
		this.romenia.add(Craiova);
		this.romenia.add(Mehadia);
		this.romenia.add(Dobreta);
		this.romenia.add(Giurgiu);
		this.romenia.add(Urziceni);
		this.romenia.add(Hirsova);
		this.romenia.add(Eforie);
		this.romenia.add(Vaslui);
		this.romenia.add(Iasi);
		this.romenia.add(Neamt);

		// Custos ARAD
		custos.add(new Custo(Arad, Sibiu, 140));
		custos.add(new Custo(Arad, Zerind, 75));
		custos.add(new Custo(Arad, Timissoara, 118));

		// Custos SIBIU
		custos.add(new Custo(Sibiu, Arad, 140));
		custos.add(new Custo(Sibiu, Oradea, 151));
		custos.add(new Custo(Sibiu, Fagaras, 99));
		custos.add(new Custo(Sibiu, Rimnieu_Vilcea, 80));

		// Custos ZERIND
		custos.add(new Custo(Zerind, Arad, 75));
		custos.add(new Custo(Zerind, Oradea, 71));

		// Custos ORADEA
		custos.add(new Custo(Oradea, Zerind, 71));
		custos.add(new Custo(Oradea, Sibiu, 151));

		// Custos TIMISSOARA
		custos.add(new Custo(Timissoara, Arad, 118));
		custos.add(new Custo(Timissoara, Lugoj, 111));

		// Custos LUGOJ
		custos.add(new Custo(Lugoj, Timissoara, 111));
		custos.add(new Custo(Lugoj, Mehadia, 70));

		// Custos MEHADIA
		custos.add(new Custo(Mehadia, Lugoj, 70));
		custos.add(new Custo(Mehadia, Dobreta, 75));

		// Custos DOBRETA
		custos.add(new Custo(Dobreta, Mehadia, 75));
		custos.add(new Custo(Dobreta, Craiova, 120));

		// Custos CRAIOVA
		custos.add(new Custo(Craiova, Dobreta, 120));
		custos.add(new Custo(Craiova, Rimnieu_Vilcea, 146));
		custos.add(new Custo(Craiova, Pitesti, 138));

		// Custos VILCEA
		custos.add(new Custo(Rimnieu_Vilcea, Sibiu, 80));
		custos.add(new Custo(Rimnieu_Vilcea, Craiova, 146));
		custos.add(new Custo(Rimnieu_Vilcea, Pitesti, 97));

		// Custos PITESTI
		custos.add(new Custo(Pitesti, Rimnieu_Vilcea, 97));
		custos.add(new Custo(Pitesti, Craiova, 138));
		custos.add(new Custo(Pitesti, Bucareste, 101));

		// Custos BUCARESTE
		custos.add(new Custo(Bucareste, Fagaras, 211));
		custos.add(new Custo(Bucareste, Giurgiu, 90));
		custos.add(new Custo(Bucareste, Urziceni, 85));
		custos.add(new Custo(Bucareste, Pitesti, 101));

		// Custos FAGARAS
		custos.add(new Custo(Fagaras, Sibiu, 99));
		custos.add(new Custo(Fagaras, Bucareste, 211));

		// Custos GIURGIU
		custos.add(new Custo(Giurgiu, Bucareste, 90));

		// Custos URZICENI
		custos.add(new Custo(Urziceni, Bucareste, 85));
		custos.add(new Custo(Urziceni, Hirsova, 98));
		custos.add(new Custo(Urziceni, Vaslui, 142));

		// Custos HIRSOVA
		custos.add(new Custo(Hirsova, Urziceni, 98));
		custos.add(new Custo(Hirsova, Eforie, 86));

		// Custos EFORIE
		custos.add(new Custo(Eforie, Hirsova, 86));

		// Custos VASLUI
		custos.add(new Custo(Vaslui, Urziceni, 142));
		custos.add(new Custo(Vaslui, Iasi, 92));

		// Custos IASI
		custos.add(new Custo(Iasi, Vaslui, 92));
		custos.add(new Custo(Iasi, Neamt, 87));

		// Custos NEAMT
		custos.add(new Custo(Neamt, Iasi, 87));
	}

	@Override
	public LinkedList<Estado> funcaoSucessora(Estado estado) {
		// TODO Auto-generated method stub
		return estado.acoes;
	}

	@Override
	public boolean testeDeObjetivo(Estado estado) {
		// TODO Auto-generated method stub
		return estado.equals(objetvo);
	}

	@Override
	public Estado verificaEstado(String nomeEstado) {
		// TODO Auto-generated method stub
		for (int i = 0; i < romenia.size(); i++) {
			if (nomeEstado.equals(romenia.get(i).nome)) {
				return romenia.get(i);
			}
		}
		System.err.println("Nao existe <" + nomeEstado + "> neste problema!");
		return null;
	}

	@Override
	public Integer getCustoCaminho(Estado estadoAtual, Estado estadoFinal) {
		// TODO Auto-generated method stub
		Collections.shuffle(custos);
		if (estadoAtual.equals(estadoFinal))
			return 0;
		else if (estadoAtual.acoes.contains(estadoFinal)) {
			for (int i = 0; i < custos.size(); i++) {
				if (custos.get(i).estadoInicial.equals(estadoAtual) && custos.get(i).estadoFinal.equals(estadoFinal))
					return custos.get(i).custoDeIda;
			}
		}
		System.err.println("Ocorreu um erro");
		return null;
	}

	@Override
	public Estado getEstadoInicial() {
		// TODO Auto-generated method stub
		return estadoInicial;
	}

	@Override
	public Estado getObejetivo() {
		// TODO Auto-generated method stub
		return objetvo;
	}

	@Override
	public String getNomeEstadoInicial() {
		// TODO Auto-generated method stub
		return nomeEstadoInicial;
	}

	@Override
	public String getNomeObjetivo() {
		// TODO Auto-generated method stub
		return nomeObjetivo;
	}

	@Override
	public LinkedList<Estado> getEstados() {
		// TODO Auto-generated method stub
		return romenia;
	}

	/* Funcao de testes */
	public static void main(String[] args) {

		Problema problema = new Romenia("Arad", "Bucareste");
		System.out.println(problema.getEstados().get(3).nome);
		
		System.out.println(problema.getCustoCaminho(problema.verificaEstado("Arad"), problema.verificaEstado("Arad")));

	}
}