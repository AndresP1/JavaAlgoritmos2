package uy.ort.ob201901;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uy.ort.ob201901.Retorno.Resultado;

public class TestEjemplos {

	private ISistema sistema;
	private Retorno retorno;
	
	@Before
	public void setUp() throws Exception {
		sistema = new Sistema();
	}

	@Test
	public void testInicializarYDestruirSistema() {
		assertEquals(Retorno.Resultado.OK, sistema.inicializarSistema(10).resultado);
		assertEquals(Retorno.Resultado.OK, sistema.destruirSistema().resultado);
	}

	@Test
	public void testRegistroGestorOK() {
		sistema.inicializarSistema(10);
		//Datos de la prueba
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("5.555.555-5", "Omar", "099123456").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("9.999.999-9", "Jorge", "093456789").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("1.111.111-1", "Maria", "091234567").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("2.222.222-2", "Ximena", "098444555").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("4.444.444-4", "Analia", "095555333").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("3.333.333-3", "Nicolas", "092222222").resultado);
	}

	@Test
	public void testRegistroGestorError1() {
		sistema.inicializarSistema(10);
		//Formatos de Cedula incorrectos
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("702.515-7", "a",  "099123456").resultado); 
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("1702515-7", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("1.702.51-70", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("1.702.51--0", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("..702.510-0", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("1.zzz.051-7", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				sistema.registrarGestor("           ", "a",  "099123456").resultado);

	}
	
	@Test
	public void testRegistroGestorError2() {
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarGestor("5.555.555-5", "Maria", "").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarGestor("5.555.555-5", "Maria", "123").resultado);
	}
	
	@Test
	public void testRegistroGestorError3() {
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("5.555.555-5", "Maria", "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarGestor("5.555.555-5", "Ana", "099123333").resultado);
	}

	@Test
	public void testContenedoresDelGestor() {
		
		sistema.inicializarSistema(10);
		
		sistema.registrarGestor("4.444.444-4", "Piter", "099444444");
		sistema.registrarGestor("2.222.222-2", "Micaela", "099222222");
		sistema.registrarGestor("6.666.666-6", "Yoni", "099666666");
		sistema.registrarGestor("1.111.111-1", "Maikol", "099111111");
		sistema.registrarGestor("3.333.333-3", "Filomena", "099333333");
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarGestor("7.777.777-7", "Pol", "099777777");
		sistema.registrarGestor("8.888.888-8", "Choni", "099888888");
		sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0);
		sistema.registrarContenedor("Cont 2 5", "5.555.555-5", TipoContenedor.ORGANICO, 4.0, 4.0);
		sistema.registrarContenedor("Cont 3 5", "5.555.555-5", TipoContenedor.ORGANICO, 6.0, 6.0);
		sistema.registrarContenedor("Cont 1 4", "4.444.444-4", TipoContenedor.ORGANICO, 3.0, 3.0);
		sistema.registrarContenedor("Cont 2 4", "4.444.444-4", TipoContenedor.ORGANICO, 2.0, 2.0);
		
		assertEquals(Resultado.ERROR_1, sistema.contenedoresDelGestor("44444444").resultado);
		assertEquals(Resultado.ERROR_2, sistema.contenedoresDelGestor("9.999.999-9").resultado);
		
		retorno = sistema.contenedoresDelGestor("5.555.555-5");
		assertEquals(Resultado.OK, retorno.resultado);
		assertEquals(3, retorno.valorEntero);
		System.out.println(retorno.valorEntero);
		assertTrue(retorno.valorString.equals("Cont 1 5|Cont 2 5|Cont 3 5") || retorno.valorString.equals("Cont 3 5|Cont 2 5|Cont 1 5"));		
		
	}

	@Test
	public void testListarGestores() {

		sistema.inicializarSistema(10);

		sistema.registrarGestor("4.444.444-4", "Piter",		 "099444444");
		sistema.registrarGestor("2.222.222-2", "Micaela",	 "099222222");
		sistema.registrarGestor("6.666.666-6", "Yoni",		 "099666666");
		sistema.registrarGestor("1.111.111-1", "Maikol",	 "099111111");
		sistema.registrarGestor("3.333.333-3", "Filomena",	 "099333333");
		sistema.registrarGestor("5.555.555-5", "Darcis",	 "099555555");
		sistema.registrarGestor("7.777.777-7", "Pol",		 "099777777");
		sistema.registrarGestor("8.888.888-8", "Choni",		 "099888888");
		
		retorno = sistema.listarGestores();
		assertEquals(Resultado.OK, retorno.resultado);
		assertEquals("1.111.111-1;Maikol;099111111|2.222.222-2;Micaela;099222222|3.333.333-3;Filomena;099333333|4.444.444-4;Piter;099444444|5.555.555-5;Darcis;099555555|6.666.666-6;Yoni;099666666|7.777.777-7;Pol;099777777|8.888.888-8;Choni;099888888",retorno.valorString);
		
	}

	@Test
	public void testRegistrarContenedor_OK_Error1() {
		
		sistema.inicializarSistema(2);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		assertEquals(Resultado.OK,sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0).resultado);
		assertEquals(Resultado.OK,sistema.registrarContenedor("Cont 2 5", "5.555.555-5", TipoContenedor.ORGANICO, 4.0, 4.0).resultado);
		assertEquals(Resultado.ERROR_1,sistema.registrarContenedor("Cont 3 5", "5.555.555-5", TipoContenedor.ORGANICO, 3.0, 3.0).resultado);
		
	}

	@Test
	public void testRegistrarContenedorError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		assertEquals(Resultado.OK,sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0).resultado);
		assertEquals(Resultado.ERROR_2,sistema.registrarContenedor("Cont 3 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0).resultado);
		
	}

	@Test
	public void testRegistrarContenedorError3() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		assertEquals(Resultado.ERROR_3,sistema.registrarContenedor("Cont 3 5", "4.444.444-4", TipoContenedor.ORGANICO, 5.0, 5.0).resultado);
		
	}

	@Test
	public void testRegistrarEsquina_OK_Error1() {
		
		sistema.inicializarSistema(2);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		assertEquals(Resultado.OK,sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Resultado.OK,sistema.registrarEsquina(4.0, 4.0).resultado);
		assertEquals(Resultado.ERROR_1,sistema.registrarEsquina(3.0, 3.0).resultado);
		
	}

	@Test
	public void testRegistrarEsquinaError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		assertEquals(Resultado.OK,sistema.registrarEsquina(5.0, 5.0).resultado);
		assertEquals(Resultado.ERROR_2,sistema.registrarEsquina(5.0, 5.0).resultado);
		
	}
	
	@Test
	public void testRegistrarTramoOK() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont 1 6", "5.555.555-5", TipoContenedor.ORGANICO, 6.0, 6.0);
		sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		
		assertEquals(Resultado.OK, sistema.registrarTramo(4.0, 4.0, 3.0, 3.0, 10).resultado);
		assertEquals(Resultado.OK, sistema.registrarTramo(5.0, 5.0, 6.0, 6.0, 5).resultado);
		assertEquals(Resultado.OK, sistema.registrarTramo(4.0, 4.0, 6.0, 6.0, 3).resultado);
	}

	//Peso incorrecto
	@Test
	public void testRegistrarTramoError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont 1 6", "5.555.555-5", TipoContenedor.ORGANICO, 6.0, 6.0);
		sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Resultado.ERROR_1, sistema.registrarTramo(3.0, 3.0, 4.0, 4.0, -1).resultado);
	}
	
	//Punto no existe
	@Test
	public void testRegistrarTramoError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont 1 6", "5.555.555-5", TipoContenedor.ORGANICO, 6.0, 6.0);
		sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		
		assertEquals(Resultado.ERROR_2, sistema.registrarTramo(3.0, 3.0, 7.0, 7.0, 10).resultado);
		assertEquals(Resultado.ERROR_2, sistema.registrarTramo(7.0, 7.0, 3.0, 3.0, 10).resultado);
	}

	// Tramo ya existe
	@Test
	public void testRegistrarTramoError3() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont 1 6", "5.555.555-5", TipoContenedor.ORGANICO, 6.0, 6.0);
		sistema.registrarContenedor("Cont 1 5", "5.555.555-5", TipoContenedor.ORGANICO, 5.0, 5.0);
		sistema.registrarEsquina(4.0, 4.0);
		sistema.registrarEsquina(3.0, 3.0);
		

		assertEquals(Resultado.OK, sistema.registrarTramo(4.0, 4.0, 3.0, 3.0, 10).resultado);
		assertEquals(Resultado.ERROR_3, sistema.registrarTramo(4.0, 4.0, 3.0, 3.0, 10).resultado);
	}

	@Test
	public void testCaminoContenedorOK() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont Pilas", "5.555.555-5", TipoContenedor.PILA, 1.0, 2.0);
		sistema.registrarContenedor("Cont Plást", "5.555.555-5", TipoContenedor.PLASTICO, 2.0, 2.0);
		sistema.registrarContenedor("Cont Vidrio", "5.555.555-5", TipoContenedor.VIDRIO, 3.0, 3.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(1.0, 3.0);
		sistema.registrarEsquina(2.0, 3.0);

		sistema.registrarTramo(1.0, 1.0, 1.0, 2.0, 15);
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 43);
		sistema.registrarTramo(1.0, 2.0, 1.0, 3.0, 13);
		sistema.registrarTramo(1.0, 2.0, 2.0, 2.0, 5);
		sistema.registrarTramo(1.0, 2.0, 2.0, 3.0, 15);
		sistema.registrarTramo(1.0, 3.0, 2.0, 3.0, 1);
		sistema.registrarTramo(2.0, 2.0, 2.0, 3.0, 11);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 32);
		sistema.registrarTramo(2.0, 3.0, 3.0, 3.0, 10);
		
		retorno = sistema.caminoContenedor(1.0, 1.0, TipoContenedor.VIDRIO);
		assertEquals(Resultado.OK, retorno.resultado);
		assertEquals("1.0;1.0|1.0;2.0|1.0;3.0|2.0;3.0|3.0;3.0", retorno.valorString);
		assertEquals(39, retorno.valorEntero);
		
	}

	@Test
	public void testCaminoContenedorError1() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont Pilas", "5.555.555-5", TipoContenedor.PILA, 1.0, 2.0);
		sistema.registrarContenedor("Cont Plást", "5.555.555-5", TipoContenedor.PLASTICO, 2.0, 2.0);
		sistema.registrarContenedor("Cont Vidrio", "5.555.555-5", TipoContenedor.VIDRIO, 3.0, 3.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(1.0, 3.0);
		sistema.registrarEsquina(2.0, 3.0);

		sistema.registrarTramo(1.0, 1.0, 1.0, 2.0, 15);
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 43);
		sistema.registrarTramo(1.0, 2.0, 1.0, 3.0, 13);
		sistema.registrarTramo(1.0, 2.0, 2.0, 2.0, 5);
		sistema.registrarTramo(1.0, 2.0, 2.0, 3.0, 15);
		sistema.registrarTramo(1.0, 3.0, 2.0, 3.0, 1);
		sistema.registrarTramo(2.0, 2.0, 2.0, 3.0, 11);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 32);
		sistema.registrarTramo(2.0, 3.0, 3.0, 3.0, 10);
		
		retorno = sistema.caminoContenedor(1.0, 1.5, TipoContenedor.VIDRIO);
		assertEquals(Resultado.ERROR_1, retorno.resultado);
		
	}

	@Test
	public void testCaminoContenedorError2() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont Pilas", "5.555.555-5", TipoContenedor.PILA, 1.0, 2.0);
		sistema.registrarContenedor("Cont Plást", "5.555.555-5", TipoContenedor.PLASTICO, 2.0, 2.0);
		sistema.registrarContenedor("Cont Vidrio", "5.555.555-5", TipoContenedor.VIDRIO, 3.0, 3.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(1.0, 3.0);
		sistema.registrarEsquina(2.0, 3.0);

		sistema.registrarTramo(1.0, 1.0, 1.0, 2.0, 15);
		sistema.registrarTramo(1.0, 1.0, 2.0, 2.0, 10);
		sistema.registrarTramo(1.0, 1.0, 3.0, 3.0, 43);
		sistema.registrarTramo(1.0, 2.0, 1.0, 3.0, 13);
		sistema.registrarTramo(1.0, 2.0, 2.0, 2.0, 5);
		sistema.registrarTramo(1.0, 2.0, 2.0, 3.0, 15);
		sistema.registrarTramo(1.0, 3.0, 2.0, 3.0, 1);
		sistema.registrarTramo(2.0, 2.0, 2.0, 3.0, 11);
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 32);
		sistema.registrarTramo(2.0, 3.0, 3.0, 3.0, 10);

		sistema.registrarContenedor("Cont Vidrio", "5.555.555-5", TipoContenedor.ORGANICO, 18.91, 18.91);
		
		retorno = sistema.caminoContenedor(1.0, 1.0, TipoContenedor.ORGANICO);
		assertEquals(Resultado.ERROR_2, retorno.resultado);
		
	}
///////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testEstadoVias() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarContenedor("Cont Pilas", "5.555.555-5", TipoContenedor.PILA, 1.0, 2.0);
		sistema.registrarContenedor("Cont Plást", "5.555.555-5", TipoContenedor.PLASTICO, 2.0, 2.0);
		sistema.registrarContenedor("Cont Vidrio", "5.555.555-5", TipoContenedor.VIDRIO, 3.0, 3.0);
		sistema.registrarEsquina(1.0, 1.0);
		sistema.registrarEsquina(1.0, 3.0);
		sistema.registrarEsquina(2.0, 3.0);

		sistema.registrarTramo(1.0, 1.0, 1.0, 2.0, 15);
		sistema.registrarTramo(1.0, 2.0, 1.0, 3.0, 13);
		sistema.registrarTramo(1.0, 3.0, 2.0, 3.0, 1);
		sistema.registrarTramo(2.0, 3.0, 3.0, 3.0, 10);
		
		System.out.println("");
		System.out.println("TEST ESTADO VIAS");
		System.out.println("");
		
		retorno = sistema.estadoVias();
		assertEquals(Resultado.OK, retorno.resultado);
		assertEquals("NO CONEXO", retorno.valorString);
		
		System.out.println(retorno.valorString);
		
		sistema.registrarTramo(2.0, 2.0, 3.0, 3.0, 32);
		
		retorno = sistema.estadoVias();
		System.out.println(retorno.valorString);
		System.out.println("");
		
		
		assertEquals(Resultado.OK, retorno.resultado);
		assertEquals("CONEXO", retorno.valorString);
		
		
	}
////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	public void testDibujarMapa() {
		
		sistema.inicializarSistema(10);
		sistema.registrarGestor("5.555.555-5", "Chinguenguenza", "099555555");
		sistema.registrarEsquina(34.9129446,-56.1484977); // Bar La Esquina
		sistema.registrarContenedor("Contenedor de Gloria", "5.555.555-5", TipoContenedor.PAPEL, 34.7969082,-56.0693009); // Campeón del Siglo 
		sistema.registrarContenedor("Contenedor de bueno, el resto", "5.555.555-5", TipoContenedor.PAPEL, -34.8844009,-56.1609289); // Bueno, el otro 
		
		retorno = sistema.dibujarMapa();
		assertEquals(Resultado.OK, retorno.resultado);
	}

}

/*package uy.ort.ob201901;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEjemplos {

	@Test
	public void testInicializarDestruirSistema() {
		Sistema s = new Sistema();
		assertEquals(Retorno.Resultado.OK, s.inicializarSistema(10).resultado);
		assertEquals(Retorno.Resultado.OK, s.destruirSistema().resultado);
	}
	
	@Test
	public void testRegistroGestorOK() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		//Datos de la prueba
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("5.555.555-5", "Omar", "099123456").resultado);	
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("9.999.999-9", "Jorge", "093456789").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("1.111.111-1", "Maria", "091234567").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("2.222.222-2", "Ximena", "098444555").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("4.444.444-4", "Analia", "095555333").resultado);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("3.333.333-3", "Nicolas", "092222222").resultado);
	}

	@Test
	public void testRegistroGestorError1() {
		ISistema s = new Sistema();
		s.inicializarSistema(10);
		//Formatos de Cedula incorrectos
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("702.515-7", "a",  "099123456").resultado); 
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("1702515-7", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("1.702.51-70", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("1.702.51--0", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("..702.510-0", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("1.zzz.051-7", "a",  "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_1, 
				s.registrarGestor("           ", "a",  "099123456").resultado);

	}
	
	@Test
	public void testRegistroGestorError2() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarGestor("5.555.555-5", "Maria", "").resultado);
		assertEquals(Retorno.Resultado.ERROR_2, sistema.registrarGestor("5.555.555-5", "Maria", "123").resultado);
	}
	
	@Test
	public void testRegistroAfiliadoError3() {
		ISistema sistema = new Sistema();
		sistema.inicializarSistema(10);
		assertEquals(Retorno.Resultado.OK, sistema.registrarGestor("5.555.555-5", "Maria", "099123456").resultado);
		assertEquals(Retorno.Resultado.ERROR_3, sistema.registrarGestor("5.555.555-5", "Ana", "099123333").resultado);
	}
	

}*/