package GrafoMapa;

public class Arista {

	private boolean existe;
	private int longitud;
	
	public Arista() {
//		this.existe = false;
//		this.dato = 0;
	}

	public Arista(int dato) {
		this.existe = true;
		this.longitud = dato;
	}

	public boolean isExiste() {
		return existe;
	}

	public void setExiste(boolean existe) {
		this.existe = existe;
	}

	public int getDato() {
		return longitud;
	}

	public void setDato(int dato) {
		this.longitud = dato;
	}

	@Override
	public String toString() {
		return existe ? String.format("%2s", longitud).replaceAll(" ", "0") : "--";
	}

}
