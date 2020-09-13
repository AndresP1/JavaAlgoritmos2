package uy.ort.ob201901;

import Gestores.ArbolGestores;
import Gestores.NodoArbolGestor;
import GrafoMapa.Contenedor;
import GrafoMapa.Grafo;

public class Main {

	public static void main(String[] args) {
		
		ArbolGestores arbolito=new ArbolGestores();
		Grafo<Contenedor> mapa=new Grafo<Contenedor>(8,false);
		Contenedor c1=new Contenedor("juan","2",TipoContenedor.PAPEL, 2.0,4.0 );
		Contenedor c2=new Contenedor("diego","4",TipoContenedor.PILA, 5.0,8.0 );
		Contenedor c3=new Contenedor("carla","1",TipoContenedor.PAPEL, 7.0,6.0 );
		Contenedor c4=new Contenedor("martin","7",TipoContenedor.PAPEL, 8.0,1.0 );
		Contenedor c5=new Contenedor("caro","8",TipoContenedor.VIDRIO, 10.0,3.0 );

		mapa.agregarVertice(c1);
		mapa.agregarVertice(c2);
		mapa.agregarVertice(c3);
		mapa.agregarVertice(c4);
		mapa.agregarVertice(c5);
		 
		mapa.agregarArista(c1, c5, 2);
		mapa.agregarArista(c1, c4, 8);
		mapa.agregarArista(c4, c2, 1);
		mapa.agregarArista(c2, c3, 4);
		mapa.agregarArista(c5, c3, 6);

		System.out.println(mapa.existeArista(c2, c3));
		 
		System.out.println(mapa.existeVertice(c3));
		 
		mapa.BFS();
		
		  
		System.out.println(mapa.estaLleno());
		
		NodoArbolGestor gestor1=new NodoArbolGestor("1", "perez", "0956");
		NodoArbolGestor gestor2=new NodoArbolGestor("2", "diaz", "0956");
		NodoArbolGestor gestor3=new NodoArbolGestor("3", "lado", "0956");
		NodoArbolGestor gestor4=new NodoArbolGestor("4", "lado", "0956");
		NodoArbolGestor gestor5=new NodoArbolGestor("5", "lado", "0956");
		NodoArbolGestor gestor6=new NodoArbolGestor("6", "lado", "0956");
		NodoArbolGestor gestor7=new NodoArbolGestor("7", "lado", "0956");
		
		arbolito.insertar("1", "perez", "0956");
		arbolito.insertar("2", "diaz", "0956");
		arbolito.insertar("3", "lado", "0956");
		arbolito.insertar("4", "lado", "0956");
		arbolito.insertar("5", "lado", "0956");
		arbolito.insertar("6", "lado", "0956");
		arbolito.insertar("7", "lado", "0956");
		 
		mapa.BFS();
		
	}

}
