package es.unican.is2.franquiciasuc.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


class EmpleadoTest {

	@Test
	void testEmpleado() {
		String dni = "12345678A";
        String nombre = "Pablo";
        Categoria categoria = Categoria.ENCARGADO;
        LocalDate fechaContratacion = LocalDate.of(2018, 3, 14);
        
        assertDoesNotThrow( () -> {
	        Empleado e = new Empleado(dni, nombre, categoria, fechaContratacion);
	        
	        assertNotNull(e);
	        assertEquals(dni, e.getDNI());
	        assertEquals(nombre, e.getNombre());
	        assertEquals(categoria, e.getCategoria());
	        assertEquals(fechaContratacion, e.getFechaContratacion());
        });
        
	        // Casos de prueba no validos
	        assertThrows(NullPointerException.class, () -> new Empleado(null, "Nombre", Categoria.AUXILIAR, LocalDate.now()));
	        assertThrows(NullPointerException.class, () -> new Empleado("12345678A", null, Categoria.AUXILIAR, LocalDate.now()));
	        assertThrows(NullPointerException.class, () -> new Empleado("12345678A", "Nombre", null, LocalDate.now()));
	        assertThrows(NullPointerException.class, () -> new Empleado("12345678A", "Nombre", Categoria.AUXILIAR, null));
	        assertThrows(OperacionNoValidaException.class, () -> new Empleado("12345678A", "Nombre", Categoria.AUXILIAR, LocalDate.of(2025, 3, 20)));
	}
	
	@Test
	void testSueldoBruto() throws OperacionNoValidaException {
		Empleado e1 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now());
		e1.darDeBaja();
		assertEquals(750.0, e1.sueldoBruto());
		
		Empleado e2 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now().minusYears(2));
		assertEquals(1000.0, e2.sueldoBruto());
		
		Empleado e3 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(5));
		e3.darDeBaja();
		assertEquals(1125.0, e3.sueldoBruto());
		
		Empleado e4 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(5).minusDays(1));
		assertEquals(1550.0, e4.sueldoBruto());
		
		Empleado e5 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(7));
		e5.darDeBaja();
		assertEquals(1537.5, e5.sueldoBruto());
		
		Empleado e6 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(10));
		assertEquals(2050.0, e6.sueldoBruto());
		
		Empleado e7 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now().minusYears(10).minusDays(1));
		e7.darDeBaja();
		assertEquals(825.0, e7.sueldoBruto());
	
		Empleado e8 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now().minusYears(15));
		assertEquals(1100.0, e8.sueldoBruto());
		
		Empleado e9 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(20));
		e9.darDeBaja();
		assertEquals(1200.0, e9.sueldoBruto());
		
		Empleado e10 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(20).minusDays(1));
		assertEquals(1700.0, e10.sueldoBruto());
		
		Empleado e11 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(40));
		e11.darDeBaja();
		assertEquals(1650.0, e11.sueldoBruto());
		
		
		// Casos de prueba no válidos
		// Empleado e = new Empleado("12345678A", "Empleado", Categoria.SEGURIDAD, LocalDate.now());
		
	}

}
