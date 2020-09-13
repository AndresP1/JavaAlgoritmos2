package GrafoMapa;
import GrafoMapa.Contenedor;
import uy.ort.ob201901.TipoContenedor;

//
public class Contenedor extends Punto{
	private String nombre;
	private String ciGestor;
	private TipoContenedor tipo;
	private EstadoContenedor estado;
	
	public Contenedor(String nombre,String ciGestor,TipoContenedor tipo,Double x,Double y) {
		super(x,y);
		this.nombre=nombre;
		this.ciGestor=ciGestor;
		this.tipo=tipo;
		this.estado=EstadoContenedor.Disponible;
	}
	public Contenedor(TipoContenedor tipo) {
		super(1.1,1.1);
		this.tipo=tipo;
		this.estado=EstadoContenedor.Disponible;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCiGestor() {
		return ciGestor;
	}


	public void setCiGestor(String ciGestor) {
		this.ciGestor = ciGestor;
	}


	public TipoContenedor getTipo() {
		return tipo;
	}


	public void setTipo(TipoContenedor tipo) {
		this.tipo = tipo;
	}


	public EstadoContenedor getEstado() {
		return estado;
	}


	public void setEstado(EstadoContenedor estado) {
		this.estado = estado;
	}



	@Override
	public String toString() {
		return "Contenedor [nombre=" + nombre + ", ciGestor=" + ciGestor + ", tipo=" + tipo + ", estado=" + estado
				+ "]";
	}



	public boolean validarTipoDisponiblidad(Contenedor destino) {
		if(destino != null && destino.getTipo()==this.tipo && this.getEstado()==EstadoContenedor.Disponible){
			return true;
		}
		return false;
	}
	
	
}
