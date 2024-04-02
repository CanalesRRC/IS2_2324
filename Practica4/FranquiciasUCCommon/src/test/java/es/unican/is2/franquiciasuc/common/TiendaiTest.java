package es.unican.is2.franquiciasuc.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class TiendaiTest {

	@Test
	void testTienda() {
		Tienda t = new Tienda("Badulaque", "Springfield");
		
		assertNotNull(t);
		assertEquals("Badulaque", t.getNombre());
		assertEquals("Springfield", t.getDireccion());
		
		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> new Tienda(null, "Springfield"));
		assertThrows(NullPointerException.class, () -> new Tienda("Badulaque", null));
	}
	
	@Test
	void testGastoMensualSueldos() {
		Tienda t = new Tienda();
		
		assertDoesNotThrow( () -> {
			// Lista empleados vacia
			assertEquals(0.0, t.gastoMensualSueldos());
			
			// Lista empleados con elementos
			Empleado empleado1 = new Empleado("123A", "Empleado1", Categoria.AUXILIAR, LocalDate.of(2022, 1, 1));
			t.getEmpleados().add(empleado1);
			Empleado empleado2 = new Empleado("123B", "Empleado2", Categoria.VENDEDOR, LocalDate.of(2010, 1, 1));
			t.getEmpleados().add(empleado2);
			Empleado empleado3 = new Empleado("123C", "Empleado3", Categoria.ENCARGADO, LocalDate.of(2000, 1, 1));
			empleado3.setBaja(true);
			t.getEmpleados().add(empleado3);
			
			assertEquals(4250.0, t.gastoMensualSueldos());
		
			// Casos de prueba no validos
			Tienda t1 = new Tienda();
			t1.getEmpleados().add(null);
			assertThrows(NullPointerException.class, () -> t1.gastoMensualSueldos());
		});
	}
}