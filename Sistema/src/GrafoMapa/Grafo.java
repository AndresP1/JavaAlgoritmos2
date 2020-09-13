package GrafoMapa;


import cola.Cola;
import cola.ICola;
import listas.ILista;
import listas.Lista;
import uy.ort.ob201901.TipoContenedor;

public class Grafo<T> {

	private T[] vertices;
	private Arista[][] matAdy;
	private int tope;
	private int cant;
	private boolean esDirigido;

	public Grafo(int tope, boolean esDirigido) {
		this.tope = tope;
		this.esDirigido = esDirigido;
		// this.cant = 0;

		vertices = (T[]) new Object[tope];
		matAdy = new Arista[tope][tope];
		if (esDirigido) {
			for (int i = 0; i < matAdy.length; i++) {
				for (int j = 0; j < matAdy.length; j++) {
					matAdy[i][j] = new Arista();
				}
			}
		} else {
			for (int i = 0; i < matAdy.length; i++) {
				for (int j = 0; j <= i; j++) {
					matAdy[i][j] = matAdy[j][i] = new Arista();
				}
			}
		}

	}

	// Pre: !estaLleno()
	public void agregarVertice(T vertice) {
		int pos = buscarHueco();
		vertices[pos] = vertice;
		cant++;
	}

	//3.3 registrar tramo
	// Pre: existeVertice(vOrigen) && existeVertice(vDestino)
	public void agregarArista(T vOrigen, T vDestino, int dato) {
		int posOrigen = buscarPos(vOrigen);
		int posDestino = buscarPos(vDestino);

		matAdy[posOrigen][posDestino].setExiste(true);
		matAdy[posOrigen][posDestino].setDato(dato);

	}

	public boolean estaVacio() {
		return cant == 0;
	}

	public boolean estaLleno() {
		return cant == tope;
	}

	public boolean existeVertice(T vertice) {
		Punto p;
		Punto uukkk=(Punto)vertice;
		for (int i = 0; i < vertices.length; i++)
		{
			p =(Punto)vertices[i];
			if(p==null)
				break;
			boolean ffuu=p.equals(uukkk);
			if (vertices[i] != null && ffuu)
							return true;
		}
			return false;
	}

	// Pre: existeVertice(vOrigen) && existeVertice(vDestino)
	public boolean existeArista(T vOrigen, T vDestino) {
		int posOrigen = buscarPos(vOrigen);
		int posDestino = buscarPos(vDestino);

		return matAdy[posOrigen][posDestino].isExiste();
	}

	// Pre: existeArista(vOrigen, vDestino)
	public void borrarArista(T vOrigen, T vDestino) {
		int posOrigen = buscarPos(vOrigen);
		int posDestino = buscarPos(vDestino);

		matAdy[posOrigen][posDestino].setExiste(false);
	}

	// Pre: existeVertice(vertice)
	public void borrarVertice(T vertice) {
		int pos = buscarPos(vertice);
		vertices[pos] = null;
		cant--;

		for (int i = 0; i < matAdy.length; i++) {
			matAdy[pos][i].setExiste(false);
			matAdy[i][pos].setExiste(false);
		}
	}

	private int buscarPos(T vOrigen) {
		int ret = 0;
		while (ret < tope && (vertices[ret] == null || !vertices[ret].equals(vOrigen)))
			ret++;
		return ret == tope ? -1 : ret;
	}

	private int buscarHueco() {
		int ret = 0;
		while (vertices[ret] != null)
			ret++;
		return ret;
	}

	//Pre: !estaVacio()
	private int buscarNoHueco() {
		int ret = 0;
		while (vertices[ret] == null)
			ret++;
		return ret;
	}
	
	public boolean DFS(){ // busqueda en profundidad
		boolean[] vis = new boolean[tope];
		
		
		for (int i = 0; i < vis.length; i++) {
			if(vertices[i] != null )//&& !vis[i])
			{
				DFSRec(i, vis);
			}
		}
		
		//cant de trues en visitados == cant vertices ?
		int visitados=0;
		for(int i=0;i<tope;i++) {
			if(vis[i])
				visitados++;
		}
		
		if(visitados==cant)
			return true;
		else
			return false;
		//System.out.println();
	}
	
	private void DFSRec(int pos, boolean[] vis)
	{
		//System.out.println(vertices[pos]);
		vis[pos] = true;
		int cant=0;
		for (int i = 0; i < tope; i++) {
			if(matAdy[pos][i].isExiste() )
			{
				cant++;
				if( !vis[i])
				DFSRec(i, vis);
			}
		}
		
		if(cant==0)
			vis[pos]=false;
	}
	
	public void BFS() // por amplitud
	{
		boolean[] vis = new boolean[tope];
		for (int i = 0; i < vis.length; i++) {
			if(vertices[i] != null && !vis[i])
			{
				BFSRec(i, vis);				
			}
		}
		System.out.println();
	}

	private void BFSRec(int pos, boolean[] vis) {
		ICola<Integer> cola = new Cola<Integer>();
		cola.encolar(pos);
		while(!cola.esVacia())
		{
			//aca desencola el actual y lo marca
			int posActual = cola.desencolar();
			System.out.println(vertices[posActual]); //aca muestra o hace 
			vis[posActual] = true;
			
			//aca obtiene los de alrrededor
			for (int i = 0; i < tope; i++) {
				if(matAdy[posActual][i].isExiste() && !vis[i])
				{
					cola.encolar(i);
				}
			}
			
			
		}
	}

	
	
	public Grafo<T> prim(){
		Grafo<T> ret = new Grafo<T>(tope, esDirigido);
		// Recorro todos mis vértices y los agrego en el grafo a retornar
		for (int i = 0; i < tope; i++) {
			if(vertices[i] != null)
			{
				ret.agregarVertice(vertices[i]);
			}
		}
		if(!ret.estaVacio())
		{
			// Visito un vértice existente
			boolean[] vis = new boolean[tope];
			int pos = buscarNoHueco();
			vis[pos] = true;
			int cantAristas = 0;
			
			// Paso a agregar n-1 aristas
			for (int i = 0; i < tope-1 && cantAristas < cant-1; i++) {
				
				int posOrigen = -1;
				int posDestino = -1;
				int menorDistancia = Integer.MAX_VALUE;
				
				// Recorro los visitados
				for (int j = 0; j < tope; j++) {
					if(vis[j])
					{
						// Recorro los no visitados
						for (int k = 0; k < tope; k++) {
							if(!vis[k] && vertices[k] != null)
							{
								int candidato = matAdy[j][k].getDato();
								// Si existe la arista y es mejor que la hasta ahora mejor,
								// 		actualizo a la candidata a ser mejor
								if(matAdy[j][k].isExiste() && candidato < menorDistancia)
								{
									posOrigen = j;                    
									posDestino = k;                   
									menorDistancia = candidato;
								}
							}
						}
					}
				}
				
				// Visito a la nueva arista
				vis[posDestino] = true;
				cantAristas++;
				// Agrego la arista
				ret.agregarArista(vertices[posOrigen], vertices[posDestino], menorDistancia);
			}
		}
		return ret;
	}
	
	//Pre: existeVertice(origen) && existeVertice(destino) && destino instanceof Contenedor
	public int dijkstra(T origen, T destino, ILista<T> camino)
	{
		// T destino = vertice del tipo que queremos y esta disponible
		//------- pasos base------------------------------------
		int posOri = buscarPos(origen);
		int posDes = buscarPos(destino);
		
		// Creo los 3 vectores auxiliares
		int[] dist = new int[tope];
		int[] ant = new int[tope];
		boolean[] vis = new boolean[tope];
		
		// Inicializo los vectores
		for (int i = 0; i < tope; dist[i++] = Integer.MAX_VALUE);
		for (int i = 0; i < tope; ant[i++] = -1);
		
		// Primer paso manual, actualizando los adyacentes del origen
		vis[posOri] = true;
		for (int j = 0; j < tope; j++) {
			if(matAdy[posOri][j].isExiste())
			{
				dist[j] = matAdy[posOri][j].getDato();
				ant[j] = posOri;
			}
		}
		//////////////////////////////////////////////////////////
		
		//---------mapea distancias-------------------------------
		dijkstra(dist,vis,ant);
		//////////////////////////////////////////////////////////
		
		//---------encuentro el destino---------------------------
		int min=Integer.MAX_VALUE;
		Contenedor actual;
		Contenedor dest = (Contenedor)destino;
		for (int i = 0; i < tope; i++) {
			if(vertices[i] instanceof Contenedor && vis[i]) {
			actual = (Contenedor)vertices[i];
			
			if(actual.validarTipoDisponiblidad(dest) && min>dist[i]) {
				min=dist[i];
				posDes=i;
			}}
		}
		//////////////////////////////////////////////////////////
		
		//---------crea el camino----------------------------------
		if(posDes!=-1 && dist[posDes]!=Integer.MAX_VALUE){	
			int vertActual = posDes;
			camino.insertar(vertices[vertActual]);//aca ingresa el final
			while(ant[vertActual] != -1)
			{
				vertActual = ant[vertActual];
				camino.insertar(vertices[vertActual]);// aca ingresa de final -1 a 0
			}
		
		return dist[posDes];
		}
		else
			return Integer.MAX_VALUE;// no hay camino
		///////////////////////////////////////////////////////////
		
	}
		
	public void dijkstra(int[] dist,boolean[] vis,int[] ant) {
		// Proceso reiterativo
		
		for (int k = 0; k < cant-1; k++) {
			int posCand = -1;
			int minDist = Integer.MAX_VALUE;
			
			// Hallo el vértice no visitado de menor distancia desde el origen
			for (int i = 0; i < tope; i++) {
				if(!vis[i] && dist[i] < minDist)
				{
					minDist = dist[i];
					posCand = i;
				}
			}

			// Si no tengo más nodos para visitar, me voy
			if(posCand == -1)
				break;
			
			// Visito al próximo nodo (candidato)
			vis[posCand] = true;
			for (int j = 0; j < tope; j++) {
				int sumaTemporal = matAdy[posCand][j].getDato() + dist[posCand];
				if(!vis[j] && matAdy[posCand][j].isExiste() 
						&& sumaTemporal < dist[j])
				{
					dist[j] = sumaTemporal;
					ant[j] = posCand;
				}
			}
		}
		
		
		
		
		
		
	}

	public String crearMapa() {
		String key="AIzaSyAChXAEG1GBIPGDdZOdKFSy4SKK1JLpsWs";
		//center=Montevideo,Uruguay&zoom=13&size=1200x600&
		String mapajpg = "maps.googleapis.com/maps/api/staticmap?maptype=roadmap";
				for(int i=0;i<cant;i++) {
					
					String color="" ;
					String lbl="";
					String x="";
					String y="";
					
					Contenedor cont;
					if(vertices[i] instanceof Contenedor) {
						cont = (Contenedor)vertices[i];
						lbl="C";
						if(cont.getEstado() == EstadoContenedor.Lleno)
							color="yellow";
						if(cont.getEstado() == EstadoContenedor.Disponible)
							color="green";
						if(cont.getEstado() == EstadoContenedor.Roto)
							color="red";
						
						x=cont.getCoordX().toString();
						y=cont.getCoordY().toString();
					}
					else {
						Esquina esq = (Esquina)vertices[i];
						x=esq.getCoordX().toString();
						y=esq.getCoordY().toString();
						lbl="E";
						color="blue";
						
					}
					
					
					
					
					mapajpg+="&markers=color:"+color+"%7Clabel:"+lbl+"%7C"+x+","+y;
				}
				// "&markers=color:red%7Clabel:2%7C-34.91,-56.17&markers=color:green%7Clabel:3%7C-34.905,-56.19";
				
		
		mapajpg+= "&sensor=false&key="+key;
		return mapajpg;
	}

}
