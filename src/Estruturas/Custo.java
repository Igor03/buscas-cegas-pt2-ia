package Estruturas;

public class Custo {

	public Estado estadoInicial;
	public Estado estadoFinal;
	public Integer custoDeIda;
	
	public Custo(Estado estadoInicial, Estado estadoFinal, Integer custoDeIda) {
		super();
		this.estadoInicial = estadoInicial;
		this.estadoFinal = estadoFinal;
		this.custoDeIda = custoDeIda;
	}
}