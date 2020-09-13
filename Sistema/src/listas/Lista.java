package listas;

import java.util.Iterator;

public class Lista<T> implements ILista<T> {
	
	protected NodoLista<T> inicio;
	protected int largo;
	
//	public Lista() {
//		this.inicio = null;
//		this.largo = 0;
//	}

	public void insertar(T dato){
		inicio = new NodoLista<T>(dato, inicio);
		largo++;
//		NodoLista nuevo = new NodoLista(dato, inicio);
//		inicio = nuevo;
		
	}
	
	public void mostrar(){
		mostrarRec(inicio);
	}

	private void mostrarRec(NodoLista<T> nodo) {
		if(nodo != null)
		{
			System.out.println(nodo);
			mostrarRec(nodo.getSig());
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			NodoLista<T> aux = inicio;
			
			@Override
			public boolean hasNext() {
				return aux != null;
			}

			@Override
			public T next() {
				T ret = aux.getDato();
				aux = aux.getSig();
				return ret;
			}
			
		};
	}
	
	
}
