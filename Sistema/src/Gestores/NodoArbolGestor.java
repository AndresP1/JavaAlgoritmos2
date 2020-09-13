package Gestores;

import GrafoMapa.Contenedor;
import listas.Lista;

public class NodoArbolGestor {
	 //Atributos
    private String cedula;
    private String nombre;
    private String celular;
	private NodoArbolGestor der ;
	private NodoArbolGestor izq ;
	
	private Lista<Contenedor> contenedores;
	
	public NodoArbolGestor(String x, String u, String p) {
		cedula=x;
		nombre=u;
		celular=p;
		der=null;
		izq=null;
		contenedores = new Lista<Contenedor>();
	}
	
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public NodoArbolGestor getDer() {
		return der;
	}
	public void setDer(NodoArbolGestor der) {
		this.der = der;
	}
	public NodoArbolGestor getIzq() {
		return izq;
	}
	public void setIzq(NodoArbolGestor izq) {
		this.izq = izq;
	}

	public Lista<Contenedor> getContenedores() {
		return contenedores;
	}

	public void insertarContenedor(Contenedor nuevo) {
		this.contenedores.insertar(nuevo);
	}
	
	
	
	// agregar contenedor(), eliminar contenedor()
	
}
