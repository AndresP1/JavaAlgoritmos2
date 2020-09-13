package Gestores;

public interface IArbolGestores {
	boolean esArbolVacio();
	void insertar(String a, String b, String c);
	NodoArbolGestor insertar( String t, String y, String u, NodoArbolGestor m );
	void mostrarInOrder(NodoArbolGestor a);
	NodoArbolGestor buscar(String d);
	NodoArbolGestor buscar(NodoArbolGestor a, String d);


}
