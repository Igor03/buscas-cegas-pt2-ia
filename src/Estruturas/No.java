package Estruturas;

public class No {
	
	public Estado estado;
	public Estado acao;
	public No pai;
	public Integer profundidade;
	public Integer custoDePasso;
	
	public No(Estado estado) {
		super();
		this.estado = estado;
	}
}