package es.unican.is2.franquiciasuc.main;

import es.unican.is2.franquiciasuc.business.GestionEmpleados;
import es.unican.is2.franquiciasuc.business.GestionTiendas;
import es.unican.is2.franquiciasuc.dao.EmpleadosDAO;
import es.unican.is2.franquiciasuc.dao.TiendasDAO;
import es.unican.is2.franquiciasuc.gui.VistaGerente;

/**
 * Clase principal que construye la aplicacion de tres capas y lanza su ejecuci�n
 */
public class Runner {

	public static void main(String[] args) {
		// Crear componentes capa DAO
		TiendasDAO tiendasDAO = new TiendasDAO();
		EmpleadosDAO empleadosDAO = new EmpleadosDAO();
		
		// Crear componentes capa negocio
		GestionTiendas gTiendas = new GestionTiendas(tiendasDAO);
		GestionEmpleados gEmpleados = new GestionEmpleados(tiendasDAO, empleadosDAO);
		
		// Crear componentes capa presentacion
		VistaGerente vista = new VistaGerente(gTiendas, gEmpleados);
		
		// Lanzar ejecucion (hacer visible la interfaz)
		vista.setVisible(true);
	}
}
