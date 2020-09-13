package uy.ort.ob201901;

import java.util.ArrayList;

import Gestores.ArbolGestores;
import Gestores.NodoArbolGestor;
import GrafoMapa.Contenedor;
import GrafoMapa.Esquina;
import GrafoMapa.Grafo;
import GrafoMapa.Punto;
import listas.ILista;
import listas.Lista;
import uy.ort.ob201901.Retorno.Resultado;

public class Sistema implements ISistema {

	private ArbolGestores gestores;
	private Grafo<Punto> mapa;
	
	
	@Override
	public Retorno inicializarSistema (int maxPuntos) {
		if(maxPuntos<1)
			return new Retorno(Resultado.ERROR_1);
		
		gestores=new ArbolGestores();
		mapa=new Grafo<Punto>(maxPuntos,false);
		
		return new Retorno(Resultado.OK);
	}
	
	@Override
	public Retorno destruirSistema() {
		gestores = null;
		mapa=null;
		
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarGestor(String cedula, String nombre, String celular) {
		// ToDO: Chequea formato CI para retornar error 1
		if(!ciValidacedula(cedula))
			return new Retorno(Resultado.ERROR_1);
		// ToDO: Chequea formato celular para retornar error 2
		if(!celValido(celular))
			return new Retorno(Resultado.ERROR_2);
		// chequea que no exista el gestor
		if(gestores.buscar(cedula)!=null)
			return new Retorno(Resultado.ERROR_3);
		
		
		gestores.insertar(cedula,nombre,celular);
		
		return new Retorno(Resultado.OK);
	}

	private boolean celValido(String celular) {
		return celular.length()==9 && esNum(celular);
	}

	private boolean ciValidacedula(String cedula) {
		boolean retorno = false;
		String[] primeraParte = cedula.split("-");
		if(primeraParte[0].length()==9 && primeraParte[1].length()==1) {
			
			
			if(esNum(primeraParte[1])) {//si el identificador es num continuamos
				
			String[] sp= primeraParte[0].split("[.]");
			
			if(sp[0].length()==1 && sp[1].length()==3 && sp[2].length()==3)
			{
				
				if(esNum(sp[0]) && esNum(sp[1]) && esNum(sp[2]))
					retorno = true;
				
			}
			
			}
			
		}
		
		
		return retorno;
	}

	
	private boolean esNum(String str) {
		int num;
		try {
			num =Integer.parseInt(str);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public Retorno contenedoresDelGestor(String CI) {
		
		if(!ciValidacedula(CI))
			return new Retorno(Resultado.ERROR_1);
		
		Contador contador = new Contador();
		NodoArbolGestor gestor = gestores.buscar(CI,contador);
		
		if(gestor==null)
			return new Retorno(Resultado.ERROR_2);
		
		int sumatoria=0;
		String txt="";
		for(Contenedor c: gestor.getContenedores()) {
			if(txt!="")
				txt+="|";
			sumatoria++;
			txt+=c.getNombre();
		}
		return new Retorno(Resultado.OK,txt,sumatoria);
	}
	
	@Override
	public Retorno listarGestores() {
		return new Retorno(Resultado.OK,gestores.aplanar(),0);
	}

	@Override
	public Retorno registrarContenedor(String nombre, String CIgestor, TipoContenedor tipo, Double coordX, Double coordY) {
		if(mapa.estaLleno())
			return new Retorno(Resultado.ERROR_1);
		if(mapa.existeVertice(new Punto(coordX,coordY)))
			return new Retorno(Resultado.ERROR_2);
		if(!gestores.existe(CIgestor))
			return new Retorno(Resultado.ERROR_3);
		
		Contenedor nuevo = new Contenedor(nombre,CIgestor,tipo,coordX,coordY);
		mapa.agregarVertice(nuevo);
		gestores.buscar(CIgestor).insertarContenedor(nuevo);
		
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarEsquina(Double coordX, Double coordY) {
		if(mapa.estaLleno())
			return new Retorno(Resultado.ERROR_1);
		if(mapa.existeVertice(new Punto(coordX,coordY)))
			return new Retorno(Resultado.ERROR_2);
		
		
		mapa.agregarVertice(new Esquina(coordX,coordY));
		
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno registrarTramo(Double coordXi, Double coordYi, Double coordXf, Double coordYf, int longitud) {
		if(longitud<1)
			return new Retorno(Resultado.ERROR_1);
		Punto origen = new Punto(coordXi,coordYi);
		Punto destino = new Punto(coordXf,coordYf);
		if(!mapa.existeVertice(origen) || !mapa.existeVertice(destino))
				return new Retorno(Resultado.ERROR_2);
		if(mapa.existeArista(origen, destino))
			return new Retorno(Resultado.ERROR_3);
		
		
		mapa.agregarArista(origen, destino, longitud);
		
		return new Retorno(Resultado.OK);
	}

	@Override
	public Retorno caminoContenedor(Double coordX, Double coordY, TipoContenedor tipo) {
		
		Punto ori = new Punto(coordX,coordY);
		if(!mapa.existeVertice(ori))
			return new Retorno(Resultado.ERROR_1);
		
		Lista<Punto> camino= new Lista<Punto>();
		int distanciaAlDestino= mapa.dijkstra(ori,new Contenedor(tipo),camino);
		
		if(distanciaAlDestino==Integer.MAX_VALUE)
			return new Retorno(Resultado.ERROR_2);
		else {
			String caminotxt = "";
			for(Punto p : camino)
				caminotxt += p.toString()+"|";
			return new Retorno(Resultado.OK,caminotxt,distanciaAlDestino);	
		}
		
	}

	@Override
	public Retorno estadoVias() {
		if(mapa.DFS())
			return new Retorno(Resultado.OK,"CONEXO",1);
		else
			return new Retorno(Resultado.OK,"NO CONEXO",0);
	}

	@Override
	public Retorno dibujarMapa() {
		String map = mapa.crearMapa();
		
		return new Retorno(Resultado.OK,map,1);
	}
	
	
	
	
	public class Contador{
		private int valor;

		public void contar() {
			valor+=1;
			
		}
		
		public int getValor() {
			return valor;
		}
	}
}
