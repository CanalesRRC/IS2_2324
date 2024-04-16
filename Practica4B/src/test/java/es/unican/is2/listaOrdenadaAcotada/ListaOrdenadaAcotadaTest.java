package es.unican.is2.listaOrdenadaAcotada;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ListaOrdenadaAcotadaTest {

	ListaOrdenadaAcotada<Integer> lista = null;
	ListaOrdenadaAcotada<Integer> listaNull = null;
	
	@BeforeEach
	public void setUp() {
		lista = new ListaOrdenadaAcotada<Integer>(5);
	}
	
	@Test
	void testGetAddSizeClear() {
		// CP 1
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(0));
		
		// CP 23
		assertThrows(NullPointerException.class, () -> lista.add(null));
		
		// CP 14
		assertEquals(0, lista.size());
		
		// CP 17
		lista.clear();
		assertEquals(0, lista.size());
		
		// CP 6, 2, 15, 18
		lista.add(1);
		assertEquals(1, lista.get(0));
		assertEquals(1, lista.size());
		lista.clear();
		assertEquals(0, lista.size());
		
		// CP 7, 
		lista.add(1);
		lista.add(3);
		assertEquals(2, lista.size());
		assertEquals(3, lista.get(1));
		
		// CP 21
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(lista.size()));
		
		lista.add(4);
		lista.add(7);
		
		// CP 8, 16
		lista.add(4);
		assertEquals(5, lista.size());
		assertEquals(4, lista.get(2));
		assertEquals(4, lista.get(3));
		
		// CP 3, 4, 5
		assertEquals(1, lista.get(0));
		assertEquals(4, lista.get(2));
		assertEquals(7, lista.get(lista.size() - 1));
		
		// CP 24
		assertThrows(IllegalStateException.class, () -> lista.add(6));
		
		// CP 19
		lista.clear();
		assertEquals(0, lista.size());
		
		// CP 20
		lista.add(1);
		assertThrows(IndexOutOfBoundsException.class, () -> lista.get(-1));
		
		// CP 22
		assertThrows(NullPointerException.class, () -> listaNull.get(0));
		
		// CP 25
		assertThrows(NullPointerException.class, () -> listaNull.add(1));
		
		// CP 29
		assertThrows(NullPointerException.class, () -> listaNull.size());
		
		// CP 30
		assertThrows(NullPointerException.class, () -> listaNull.clear());
		
	}
	
	@Test
	void testRemove() {
		// CP 9
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(0));
		
		lista.add(1);
		// CP 10
		lista.remove(0);
		assertEquals(0, lista.size());
		
		lista.add(1);
		lista.add(3);
		lista.add(6);
		lista.add(8);
		lista.add(8);
		// CP 11
		lista.remove(0);
		assertEquals(4, lista.size());
		assertEquals(3, lista.get(0));
		
		// CP 12
		lista.remove(1);
		assertEquals(3, lista.size());
		assertEquals(3, lista.get(0));
		
		// CP 13
		lista.remove(lista.size() - 1);
		assertEquals(2, lista.size());
		assertEquals(3, lista.get(0));
		
		/* CASOS DE PRUEBA NO VALIDOS */
		// CP 26
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
		
		// CP 27
		assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(lista.size()));
		
		// CP 28
		assertThrows(NullPointerException.class, () -> listaNull.remove(0));
	}

}
