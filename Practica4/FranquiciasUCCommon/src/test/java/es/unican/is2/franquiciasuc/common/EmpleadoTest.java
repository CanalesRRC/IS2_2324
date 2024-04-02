package es.unican.is2.franquiciasuc.common;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmpleadoTest {
	
	Empleado emp1 = null;
	Empleado emp2 = null;
	
	@BeforeEach
	public void setUp() {
		assertDoesNotThrow(() -> {
			emp1 = new Empleado("123A", "Juan", Categoria.ENCARGADO, LocalDate.now());
			emp2 = new Empleado("123B", "Juan", Categoria.ENCARGADO, LocalDate.now());
		});
	}

	@Test
	void testEmpleado() {
		String dni = "123A";
		String nombre = "Empleado";
		LocalDate hoy = LocalDate.now();

		assertDoesNotThrow( () -> {
			Empleado e1 = new Empleado(dni, nombre, Categoria.AUXILIAR, hoy.minusYears(10));   
			assertNotNull(e1);
			assertEquals(dni, e1.getDNI());
			assertEquals(nombre, e1.getNombre());
			assertEquals(Categoria.AUXILIAR, e1.getCategoria());
			assertEquals(hoy.minusYears(10), e1.getFechaContratacion());

			Empleado e2 = new Empleado(dni, nombre, Categoria.VENDEDOR, hoy.minusYears(5));
			assertNotNull(e2);
			assertEquals(dni, e2.getDNI());
			assertEquals(nombre, e2.getNombre());
			assertEquals(Categoria.VENDEDOR, e2.getCategoria());
			assertEquals(hoy.minusYears(5), e2.getFechaContratacion());

			Empleado e3 = new Empleado(dni, nombre, Categoria.ENCARGADO, hoy);
			assertNotNull(e3);
			assertEquals(dni, e3.getDNI());
			assertEquals(nombre, e3.getNombre());
			assertEquals(Categoria.ENCARGADO, e3.getCategoria());
			assertEquals(hoy, e3.getFechaContratacion());
		});

		// Casos de prueba no validos
		assertThrows(NullPointerException.class, () -> new Empleado(null, "Empleado", Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, () -> new Empleado("Empleado", "Empleado", Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(NullPointerException.class, () -> new Empleado("123A", null, Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, () -> new Empleado("123A", "123A", Categoria.AUXILIAR, LocalDate.now()));
		assertThrows(NullPointerException.class, () -> new Empleado("123A", "Empleado", null, LocalDate.now()));
		assertThrows(OperacionNoValidaException.class, () -> new Empleado("123A", "Nombre", Categoria.AUXILIAR, LocalDate.now().plusDays(1)));
		assertThrows(NullPointerException.class, () -> new Empleado("123A", "Empleado", Categoria.AUXILIAR, null));
	}

	@Test
	void testSueldoBruto() {
		
		assertDoesNotThrow(() -> {
			// Casos de prueba validos
			Empleado e1 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now());
			e1.darDeBaja();
			assertEquals(750.0, e1.sueldoBruto());
	
			Empleado e2 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(2));
			assertEquals(1500.0, e2.sueldoBruto());
	
			Empleado e3 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(5));
			e3.darDeBaja();
			assertEquals(1500.0, e3.sueldoBruto());
	
			Empleado e4 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now().minusYears(5).minusDays(1));
			assertEquals(1050.0, e4.sueldoBruto());
	
			Empleado e5 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(7));
			e5.darDeBaja();
			assertEquals(1162.5, e5.sueldoBruto());
			
			Empleado e6 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(10));
			assertEquals(2050.0, e6.sueldoBruto());
	
			Empleado e7 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now().minusYears(10).minusDays(1));
			e7.darDeBaja();
			assertEquals(825.0, e7.sueldoBruto());
	
			Empleado e8 = new Empleado("12345678A", "Empleado", Categoria.VENDEDOR, LocalDate.now().minusYears(15));
			assertEquals(1600.0, e8.sueldoBruto());
	
			Empleado e9 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(20));
			e9.darDeBaja();
			assertEquals(1575.0, e9.sueldoBruto());
	
			Empleado e10 = new Empleado("12345678A", "Empleado", Categoria.AUXILIAR, LocalDate.now().minusYears(20).minusDays(1));
			assertEquals(1200.0, e10.sueldoBruto());
	
			Empleado e11 = new Empleado("12345678A", "Empleado", Categoria.ENCARGADO, LocalDate.now().minusYears(40));
			e11.darDeBaja();
			assertEquals(1650.0, e11.sueldoBruto());
		
		});

		// Casos de prueba no válidos
		emp1.setCategoria(null);
		assertThrows(NullPointerException.class, () -> emp1.sueldoBruto());
		
		emp1.setCategoria(Categoria.AUXILIAR);
		emp1.setFechaContratacion(LocalDate.now().plusDays(1));
		assertThrows(OperacionNoValidaException.class, () -> emp1.sueldoBruto());
		
		emp1.setFechaContratacion(null);
		assertThrows(NullPointerException.class, () -> emp1.sueldoBruto());
	}
	
	@Test
	void testDarDeBaja() {
		Empleado e = new Empleado();
		e.darDeBaja();
		assertTrue(e.getBaja());
	}
	
	@Test
	void testDarDeAlta() {
		Empleado e = new Empleado();
		e.setBaja(true);
		e.darDeAlta();
		assertFalse(e.getBaja());
	}
	
	@Test
    public void testEquals() {
        assertFalse(emp1.equals(emp2));
        
        emp2.setDNI("123A");
        assertTrue(emp1.equals(emp2));
    }
	
	@Test
    public void testHashCode() {
        int hash1 = emp1.hashCode();
        int hash2 = emp2.hashCode();
        assertNotEquals(hash1, hash2);
    }

}
