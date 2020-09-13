package cola;

public interface ICola<T> extends Iterable<T>{

	public void encolar(T dato);
	
	public T desencolar();
	
	public void vaciar();
	
	public int largo();
	
	public boolean esVacia();
	
	public boolean pertenece(T dato);
	
}
