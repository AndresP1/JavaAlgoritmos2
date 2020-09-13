package listas;

public interface ILista<T> extends Iterable<T> {
	
	/**
	 * Inserta un elemento en la lista
	 * @param dato elemento a ingresar
	 */
	public void insertar(T dato);

}
