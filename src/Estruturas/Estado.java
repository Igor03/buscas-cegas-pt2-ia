package Estruturas;

import java.util.LinkedList;

public class Estado {
	
	public String nome;
	
	/* Representa as acoes associadas a certo estado */
	public LinkedList<Estado> acoes = new LinkedList<>();

}