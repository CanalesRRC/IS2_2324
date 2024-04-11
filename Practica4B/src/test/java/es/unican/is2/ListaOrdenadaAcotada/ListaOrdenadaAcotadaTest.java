package es.unican.is2.ListaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.listaOrdenadaAcotada.ListaOrdenadaAcotada;


class ListaOrdenadaAcotadaTest {

	ListaOrdenadaAcotada<Integer> lista = null;
	ListaOrdenadaAcotada<Integer> listaNull = null;
	
	@BeforeEach
	public void setUp() {
		lista = new ListaOrdenadaAcotada<Integer>(5);
	}
	
	@Test
	void testGet() {
		
		/* LISTA VACIA */
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
		
		/* LISTA CON UN ELEMENTO */
		lista.add(1);
		assertEquals(1, lista.get(0));
		
		/* LISTA CON ELEMENTOS */
		lista.add(2);
		lista.add(3);
		lista.add(4);
		
		// Primera posicion
		assertEquals(1, lista.get(0));
		// Posicion intermedia
		assertEquals(2, lista.get(1));
		// Ultima posicion
		assertEquals(4, lista.get(lista.size() - 1));
		
		// Casos de prueba no validos
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(10));
		assertThrows(NullPointerException.class, () -> listaNull.get(0));
	}
	
	@Test
	void testAdd() {
		
		/* LISTA VACIA */
		lista.add(1);
		assertEquals(1, lista.size());
		assertEquals(1, lista.get(0));
		
		/* LISTA CON UN ELEMENTO */
		lista.add(2);
		assertEquals(2, lista.size());
		assertEquals(2, lista.get(1));
		
		/* LISTA CON ELEMENTOS */
		lista.add(3);
		lista.add(4);
		assertEquals(4, lista.size());
		assertEquals(4, lista.get(lista.size() - 1));
		
		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> lista.add(null));
		assertThrows(NullPointerException.class, () -> listaNull.add(1));
		lista.add(5);
		assertThrows(IllegalStateException.class, () -> lista.add(6));
	}
	
	@Test
	void testRemove() {
		
		/* LISTA VACIA */
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
		
		/* LISTA CON UN ELEMENTO */
		lista.add(1);
		assertEquals(1, lista.remove(0));
		assertEquals(0, lista.size());
		
		/* LISTA CON ELEMENTOS */
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);	
		// Primera posicion
		assertEquals(1, lista.remove(0)); // quita el uno pero añade un 4 en la ultima pos
		assertEquals(4, lista.size());
		assertEquals(2, lista.get(0));
		assertEquals(5, lista.get(lista.size() - 1));
		
		// Posicion intermedia
		assertEquals(3, lista.remove(1));
		assertEquals(3, lista.size());
		assertEquals(2, lista.get(0));
		// assertEquals(5, lista.get(lista.size() - 1));
		
		// Ultima posicion
		assertEquals(5, lista.remove(lista.size() - 1)); 
		assertEquals(2, lista.size());
		assertEquals(2, lista.get(0));
		assertEquals(4, lista.get(lista.size() - 1));
		
		// Casos de prueba no validos
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(6));
		assertThrows(NullPointerException.class, () -> listaNull.remove(0));
	}
	
	@Test
	void testSize() {
	
		/* LISTA VACIA */
		assertEquals(0, lista.size());
		
		/* LISTA CON UN ELEMENTO */
		lista.add(1);
		assertEquals(1, lista.size());
		
		/* LISTA CON ELEMENTOS */
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		assertEquals(5, lista.size());
		
		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> listaNull.size());
	}
	
	@Test
	void testClear() {
		
		/* LISTA VACIA */
		lista.clear();
		assertEquals(0, lista.size()); // anhade un null a la primera posicion
		
		/* LISTA CON UN ELEMENTO */
		lista.add(1);
		lista.clear();
		assertEquals(0, lista.size()); // no borra el elemento
	
		/* LISTA CON ELEMENTOS */
		lista.add(1);
		lista.add(2);
		lista.add(3);
		lista.add(4);
		lista.add(5);
		lista.clear();	
		assertEquals(0, lista.size()); // deja el elemento mas pequeño
		
		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> listaNull.clear());
		
	}

}
