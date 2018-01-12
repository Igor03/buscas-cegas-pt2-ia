package Buscas;

import java.util.LinkedList;

import Estruturas.*;
import Problemas.*;

public interface Busca {

	public LinkedList<No> buscar(Problema problema, Estado estadoInicial);

	public LinkedList<No> expandir(No no);

	public void mostrarCaminho();

	public LinkedList<No> getBorda();

	public LinkedList<No> getHistorico();
}