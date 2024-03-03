package es.unican.is2.franquiciasuc.business;

import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.Empleado;
import es.unican.is2.franquiciasuc.common.IGestionEmpleados;
import es.unican.is2.franquiciasuc.common.OperacionNoValidaException;
import es.unican.is2.franquiciasuc.dao.EmpleadosDAO;
import es.unican.is2.franquiciasuc.dao.TiendasDAO;

public class GestionEmpleados implements IGestionEmpleados {
	
	private TiendasDAO tiendasDAO;
	private EmpleadosDAO empleadosDAO;

	public GestionEmpleados(TiendasDAO tiendasDAO, EmpleadosDAO empleadosDAO) {
		this.tiendasDAO = tiendasDAO;
		this.empleadosDAO = empleadosDAO;
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
