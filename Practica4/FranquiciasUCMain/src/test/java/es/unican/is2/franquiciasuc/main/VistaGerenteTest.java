package es.unican.is2.franquiciasuc.main;

import org.fest.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.unican.is2.franquiciasuc.business.GestionEmpleados;
import es.unican.is2.franquiciasuc.business.GestionTiendas;
import es.unican.is2.franquiciasuc.dao.EmpleadosDAO;
import es.unican.is2.franquiciasuc.dao.TiendasDAO;
import es.unican.is2.franquiciasuc.gui.VistaGerente;

class VistaGerenteTest {
	
	private FrameFixture demo;

	@BeforeEach
	public void setUp() {
		
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		
		GestionTiendas tiendas = new GestionTiendas(tiendasDAO);
		GestionEmpleados empleados = new GestionEmpleados(tiendasDAO, empleadosDAO);
		
		VistaGerente gui = new VistaGerente(tiendas, empleados);
		demo = new FrameFixture(gui);
		gui.setVisible(true);
	}
	
	@AfterEach
	public void tearDown() {
		demo.cleanUp();
	}
	
	@Test
	void test() {
		demo.button("btnBuscar").requireText("Buscar");
		
		demo.textBox("txtNombreTienda").enterText("Tienda B");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Direccion B");
		demo.textBox("txtTotalContribuyente").requireText("2100.0");
		demo.list("listNombreEmpleados").requireItemCount(1);
		demo.list("listNombreEmpleados").item("Lucia Ibañez");
		demo.textBox("txtNombreTienda").deleteText();

		
		demo.textBox("txtNombreTienda").enterText("Badulaque");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Tienda no existe");
		demo.list("listNombreEmpleados").requireItemCount(0);
		demo.textBox("txtTotalContribuyente").requireText("");
		demo.textBox("txtNombreTienda").deleteText();
	
		
		demo.textBox("txtNombreTienda").enterText("Tienda A");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Direccion A");
		demo.textBox("txtTotalContribuyente").requireText("4362.5");		
		demo.list("listNombreEmpleados").requireItemCount(3);
		demo.list("listNombreEmpleados").item("Juan Perez");
		demo.list("listNombreEmpleados").item("Maria Rodriguez");
		demo.list("listNombreEmpleados").item("Luis Martinez");
		demo.textBox("txtNombreTienda").deleteText();

		
		demo.textBox("txtNombreTienda").enterText("Tienda C");
		demo.button("btnBuscar").click();
		demo.textBox("txtDireccionTienda").requireText("Direccion C");
		demo.textBox("txtTotalContribuyente").requireText("0.0");		
		demo.list("listNombreEmpleados").requireItemCount(0);
	}

}
