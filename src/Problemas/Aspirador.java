package Problemas;

import java.util.Collections;
import java.util.LinkedList;
import Estruturas.*;

public class Aspirador implements Problema {

	public LinkedList<Estado> aspirador = new LinkedList<>();
	public LinkedList<Custo> custos = new LinkedList<>();

	public String nomeEstadoInicial;
	public String nomeObjetivo;

	public Estado estadoInicial;
	public Estado objetivo;

	public Estado ESS = new Estado();
	public Estado DSS = new Estado();
	public Estado ELL = new Estado();
	public Estado DLL = new Estado();
	public Estado ESL = new Estado();
	public Estado DSL = new Estado();
	public Estado ELS = new Estado();
	public Estado DLS = new Estado();

	public Aspirador(String nomeEstadoInicial, String nomeObjetivo) {
		super();
		//System.out.println("Problema do aspirador de po");
		inicializarProblema();
		this.estadoInicial = verificaEstado(nomeEstadoInicial);
		this.objetivo = verificaEstado(nomeObjetivo);
		this.nomeEstadoInicial = nomeEstadoInicial;
		this.nomeObjetivo = nomeObjetivo;
	}

	@Override
	public void inicializarProblema() {
		// TODO Auto-generated method stub
		ESS.nome = "ESS";
		ESS.acoes.add(ELS);
		ESS.acoes.add(DSS);

		DSS.nome = "DSS";
		DSS.acoes.add(ESS);
		DSS.acoes.add(DSL);

		ELL.nome = "ELL";
		ELL.acoes.add(DLL);

		DLL.nome = "DLL";
		DLL.acoes.add(ELL);

		ELS.nome = "ELS";
		ELS.acoes.add(DLS);

		DSL.nome = "DSL";
		DSL.acoes.add(ESL);

		ESL.nome = "ESL";
		ESL.acoes.add(ELL);
		ESL.acoes.add(DSL);

		DLS.nome = "DLS";
		DLS.acoes.add(DLL);
		DLS.acoes.add(ELS);

		aspirador.add(ESS);
		aspirador.add(DSS);
		aspirador.add(ELL);
		aspirador.add(DLL);
		aspirador.add(ESL);
		aspirador.add(DSL);
		aspirador.add(ELS);
		aspirador.add(DLS);

		/* CUSTOS */
		custos.add(new Custo(ESS, ELS, 1));
		custos.add(new Custo(ESS, DSS, 1));

		custos.add(new Custo(DSS, ESS, 1));
		custos.add(new Custo(DSS, DSL, 1));

		custos.add(new Custo(ELL, DLL, 1));

		custos.add(new Custo(DLL, ELL, 1));

		custos.add(new Custo(ELS, DLS, 1));

		custos.add(new Custo(DSL, ESL, 1));

		custos.add(new Custo(ESL, ELL, 1));
		custos.add(new Custo(ESL, DSL, 1));

		custos.add(new Custo(DLS, DLL, 1));
		custos.add(new Custo(DLS, ELS, 1));

	}

	@Override
	public LinkedList<Estado> funcaoSucessora(Estado estado) {
		// TODO Auto-generated method stub
		return estado.acoes;
	}

	@Override
	public boolean testeDeObjetivo(Estado estado) {
		// TODO Auto-generated method stub
		return estado.equals(objetivo);
	}

	@Override
	public Estado verificaEstado(String nomeEstado) {
		// TODO Auto-generated method stub
		for (int i = 0; i < aspirador.size(); i++) {
			if (nomeEstado.equals(aspirador.get(i).nome)) {
				return aspirador.get(i);
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
		return objetivo;
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
		return aspirador;
	}
	
	public static void main (String[] args) {
		
		Problema problema = new Aspirador("ESS", "DLL");
		
		System.out.println(problema.getCustoCaminho(problema.verificaEstado("ELL"), problema.verificaEstado("DLL")));

	}

}
