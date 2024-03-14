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
        
        Empleado e = new Empleado(dni, nombre, categoria, fechaContratacion);
        
        assertNotNull(e);
        assertEquals(dni, e.getDNI());
        assertEquals(nombre, e.getNombre());
        assertEquals(categoria, e.getCategoria());
        assertEquals(fechaContratacion, e.getFechaContratacion());
	}

}
