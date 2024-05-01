package es.unican.is2.tiendarefactorizada;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class VendedorEnPlantillaTest {
	
	private static VendedorEnPlantilla sutJunior;
	private static VendedorEnPlantilla sutSenior;

	
	@BeforeEach
	public void setUp(){
		sutJunior = new VendedorEnPlantilla("Ana", "1", "11111111A", TipoVendedor.Junior);
		sutSenior = new VendedorEnPlantilla("Pepe", "2", "222222222A", TipoVendedor.Senior);
	}
	
	@Test
	public void testConstructor() {
		assertEquals(sutJunior.getId(), "1");
		assertEquals(sutJunior.getDni(), "11111111A");
		assertEquals(sutJunior.getNombre(), "Ana");
		assertTrue(sutJunior.getTotalVentas()==0.0);
		assertTrue(sutJunior.getComision()==0.0);
		assertEquals(sutJunior.getTipo(), TipoVendedor.Junior);
		assertEquals(sutSenior.getTipo(), TipoVendedor.Senior);
		
	}

	@Test
	public void testAnhadeVenta() {
	
		sutJunior.anhadeVenta(200);
		assertEquals(sutJunior.getTotalVentas(), 200, 0);
		sutJunior.anhadeVenta(300);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		sutJunior.anhadeVenta(0);
		assertEquals(sutJunior.getTotalVentas(), 500, 0);
		
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 300, 0);
		sutSenior.anhadeVenta(300);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		sutSenior.anhadeVenta(0);
		assertEquals(sutSenior.getTotalVentas(), 600, 0);
		
	}
	
	@Test
	public void testSetTotalVentas() {
		
		sutJunior.setTotalVentas(2000);
		assertEquals(sutJunior.getTotalVentas(), 2000, 0);	
		sutJunior.setTotalVentas(4000);
		assertEquals(sutJunior.getTotalVentas(), 4000, 0);	
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);
		
		sutSenior.setTotalVentas(4500);
		assertEquals(sutSenior.getTotalVentas(), 4500, 0);		
		sutSenior.setTotalVentas(4000);
		assertEquals(sutSenior.getTotalVentas(), 4000, 0);
		sutJunior.setTotalVentas(0);
		assertEquals(sutJunior.getTotalVentas(), 0, 0);	
		
	}
	
	@Test
	public void testSetComision() {
		
		sutJunior.setComision(2000);
		assertEquals(sutJunior.getComision(), 2000, 0);	
		sutJunior.setComision(4000);
		assertEquals(sutJunior.getComision(), 4000, 0);	
		sutJunior.setComision(0);
		assertEquals(sutJunior.getComision(), 0, 0);
		
		sutSenior.setComision(4500);
		assertEquals(sutSenior.getComision(), 4500, 0);		
		sutSenior.setComision(4000);
		assertEquals(sutSenior.getComision(), 4000, 0);
		sutJunior.setComision(0);
		assertEquals(sutJunior.getComision(), 0, 0);	
		
	}

	
	@Test
	public void testEquals() {
		VendedorEnPlantilla igualJunior = new VendedorEnPlantilla("Ana", "1", "11111111A", TipoVendedor.Junior);
		VendedorEnPlantilla distintoIdJunior = new VendedorEnPlantilla("Ana", "2", "11111111A", TipoVendedor.Junior);
		VendedorEnPlantilla distintoDNIJunior = new VendedorEnPlantilla("Ana", "1", "222222222A", TipoVendedor.Junior);
		
		assertTrue(sutJunior.equals(igualJunior));
		assertFalse(sutJunior.equals(distintoIdJunior));
		assertFalse(sutJunior.equals(distintoDNIJunior));
		
		
		VendedorEnPlantilla igualSenior = new VendedorEnPlantilla("Pepe", "2", "222222222A", TipoVendedor.Senior);
		VendedorEnPlantilla distintoIdSenior = new VendedorEnPlantilla("Pepe", "3", "222222222A", TipoVendedor.Senior);
		VendedorEnPlantilla distintoDNISenior = new VendedorEnPlantilla("Pepe", "2", "33333333A", TipoVendedor.Senior);
		
		assertTrue(sutSenior.equals(igualSenior));
		assertFalse(sutSenior.equals(distintoIdSenior));
		assertFalse(sutSenior.equals(distintoDNISenior));
		
		assertFalse(sutSenior.equals(new Object()));
	}
	
	
	
}
