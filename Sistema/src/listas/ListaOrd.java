package listas;

public class ListaOrd<T extends Comparable<T>> extends Lista<T> {

	@Override
	public void insertar(T dato) {
		if(inicio == null || dato.compareTo(inicio.getDato()) <= 0)
		{
			super.insertar(dato);
		}
		else
		{
			insertarRec(dato, inicio);
		}
	}

	private void insertarRec(T dato, NodoLista<T> nodo) {
		if(nodo.getSig() == null || dato.compareTo(nodo.getSig().getDato()) <= 0)
		{
			NodoLista<T> nuevo = new NodoLista<T>(dato, nodo.getSig());
			nodo.setSig(nuevo);
		}
		else
		{
			insertarRec(dato, nodo.getSig());
		}
	}
		
}
