package Gestores;

import listas.Lista;
import uy.ort.ob201901.Sistema.Contador;

public class ArbolGestores implements IArbolGestores{
	
	private NodoArbolGestor raiz;
	
	public ArbolGestores() {
		this.raiz=null;
	}
	public NodoArbolGestor getRaiz() {
		return raiz; 
	} 

	@Override
	public boolean esArbolVacio() {
		
		return (raiz==null);
	}
	
	public String aplanar() {
		Lista<String> txt=new Lista<String>();
		aplanarRec(this.raiz,txt);
		String eltexto="";
		
		for(String s:txt) {
			eltexto+=s+"|";
		}
		
		eltexto=eltexto.substring(0,eltexto.length()-1);
		System.out.println(" -----APLANADO-----");
		System.out.println(eltexto);
		return eltexto;
	}
	
	public void aplanarRec(NodoArbolGestor a,Lista<String> txt){
        if (a!=null){
        	
        	
            aplanarRec(a.getDer(),txt);
            String t=a.getCedula()+";"+a.getNombre()+";"+a.getCelular();
            txt.insertar(t);
            aplanarRec(a.getIzq(),txt);
        }
    }
	
    public void mostrarInOrder(){
    	mostrarInOrder(this.raiz);
    }
    
    public void mostrarInOrder(NodoArbolGestor a){
        if (a!=null){
            mostrarInOrder(a.getIzq());
          System.out.print(a.getCedula()+" ; "+a.getNombre()+" ; "+a.getCelular() ); if(a.getDer()!=null) System.out.println("|");          
            mostrarInOrder(a.getDer());
        }
    }
	
    
    
	
	   public void insertar( String s, String a, String b ) {
	        raiz = insertar( s,a,b, raiz );
	    }
	    
	    public NodoArbolGestor insertar( String x, String u, String p, NodoArbolGestor n ) {
	        if( n == null )
	            n = new NodoArbolGestor( x,u,p );

	        else if(x.compareTo(n.getCedula())<0 )
	            n.setIzq(insertar(x,u,p,n.getIzq()));// 
	        else if( x.compareTo(n.getCedula())>0 )
	            n.setDer(insertar(x,u,p,n.getDer()));	
	        return n;
	    }
	    
	    public NodoArbolGestor buscar(String cedula,Contador cont){
	    	return buscar(raiz, cedula,cont);
	    	}
	    
	    	public NodoArbolGestor buscar(NodoArbolGestor a, String dato,Contador cont){
	    	if (a==null) 	
	    		return null;
	    	
	    	
	    	cont.contar();
	    	
	    	if (a.getCedula().equals(dato)) 
	    		return a;
	    	
	    	NodoArbolGestor retorno=buscar(a.getIzq(),dato);
	    	if (retorno==null)
	    	return buscar(a.getDer(),dato);
	    	else
	    	return retorno;
	    	
	    	}
	
		
	   
	    public boolean existe(String e) {
	    	NodoArbolGestor nodo = obtenerElemento(e, raiz);
			
			if(nodo != null) {
				return true;
			} else {
				return false;
			}
		}  
	    
	    public NodoArbolGestor obtenerElemento(String n, NodoArbolGestor nodo) {
			if(nodo == null) {
				return nodo;
			} else {
				if(nodo.getCedula().equals(n) ) {
					return nodo;
				} else if( n.compareTo(nodo.getCedula())<0 ) {
					return obtenerElemento(n, nodo.getIzq());
				} else {
					return obtenerElemento(n, nodo.getDer());
				}
			}
		}
		@Override
		public NodoArbolGestor buscar(String d) {
			return buscar(raiz, d);
		}
		@Override
		public NodoArbolGestor buscar(NodoArbolGestor a, String d) {
			if (a==null) 	
	    		return null;
	    	
	    	if (a.getCedula().equals(d)) 
	    		return a;
	    	
	    	NodoArbolGestor retorno=buscar(a.getIzq(),d);
	    	if (retorno==null)
	    	return buscar(a.getDer(),d);
	    	else
	    	return retorno;
	    	
		}
		
}
