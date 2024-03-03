package es.unican.is2.franquiciasuc.business;

import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.Empleado;
import es.unican.is2.franquiciasuc.common.IGestionEmpleados;
import es.unican.is2.franquiciasuc.common.OperacionNoValidaException;
import es.unican.is2.franquiciasuc.common.Tienda;
import es.unican.is2.franquiciasuc.dao.EmpleadosDAO;
import es.unican.is2.franquiciasuc.dao.TiendasDAO;

public class GestionEmpleados implements IGestionEmpleados{
	
	private TiendasDAO tiendasDAO;
	private EmpleadosDAO empleadosDAO;

	public GestionEmpleados(TiendasDAO tiendasDAO, EmpleadosDAO empleadosDAO) {
		this.tiendasDAO = tiendasDAO;
		this.empleadosDAO = empleadosDAO;
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		
		Tienda t = tiendasDAO.tiendaPorNombre(nombre);
		
		// Comprobar si la tienda existe
		if (t == null) {
			return null;
		}
		
		// Comprobar si ya existe el empleado
		if (empleado(e.getDNI()) != null) {
			throw new OperacionNoValidaException("El empleado ya existe");
	    }
		
		// Anhadir empleado al sistema
		empleadosDAO.crearEmpleado(e);
		
		// Anhadir empleado a la tienda
		t.getEmpleados().add(e);
		
		// Retornar el empleado anhadido
		return e;
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {

		Tienda t = tiendasDAO.tiendaPorNombre(nombre);
		Empleado e = empleado(dni);
		
		// Comprobar si el empleado y la tienda existen
		if (e == null || t == null) {
			return null;
		}
		
		// Comprobar si el empleado pertenece a la tienda
		if (t.buscaEmpleado(dni) == null) {
			throw new OperacionNoValidaException("El empleado no pertenece a la tienda indicada.");
	    }
		
		// Eliminar empleado de la tienda
		t.getEmpleados().remove(e);
		
		// Eliminar empleado del sistema
		empleadosDAO.eliminarEmpleado(dni);
		
		return e;
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		
		Tienda tActual = tiendasDAO.tiendaPorNombre(actual);
		Tienda tDestino = tiendasDAO.tiendaPorNombre(destino);
		Empleado e = empleado(dni);
		
		// Comprobar si existe el empleado y las tiendas
		if (e == null || tActual == null || tDestino == null) {
			return false;
		}
		
		// Comprobar si el empleado pertenece a la tienda
		if (tActual.buscaEmpleado(dni) == null) {
			throw new OperacionNoValidaException("El empleado no pertenece a la tienda indicada.");
		}
		
		// Eliminar empleado de la tienda actual
		tActual.getEmpleados().remove(e);
		
		// Anhadir empleado a la tienda destino
		tDestino.getEmpleados().add(e);
		
		return true;
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		return empleadosDAO.empleado(dni);
    }

}
