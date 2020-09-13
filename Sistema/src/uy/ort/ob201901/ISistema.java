package uy.ort.ob201901;

// Interfaz del sistema
// No modificar esta clase!!!!!!!!!
public interface ISistema {

	Retorno inicializarSistema (int maxPuntos);
	
	Retorno destruirSistema();
	
	Retorno registrarGestor(String cedula, String nombre, String celular);
	
	Retorno contenedoresDelGestor(String CI);
	
	Retorno listarGestores();
	
	Retorno registrarContenedor (String nombre, String CIgestor, TipoContenedor tipo, Double coordX, Double coordY);
	
	Retorno registrarEsquina(Double coordX, Double coordY);
	
	Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int longitud);
	
	Retorno caminoContenedor(Double coordX, Double coordY, TipoContenedor tipo);
	
	Retorno estadoVias();
	
	Retorno dibujarMapa();
	
}
