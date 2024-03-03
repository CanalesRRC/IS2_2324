package es.unican.is2.franquiciasuc.business;

import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.IGestionTiendas;
import es.unican.is2.franquiciasuc.common.OperacionNoValidaException;
import es.unican.is2.franquiciasuc.common.Tienda;
import es.unican.is2.franquiciasuc.dao.TiendasDAO;

public class GestionTiendas implements IGestionTiendas{
	
	private TiendasDAO tiendasDAO;

	public GestionTiendas(TiendasDAO tiendasDAO) {
		this.tiendasDAO = tiendasDAO;
	}

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		
		Tienda tienda = tiendasDAO.tiendaPorNombre(t.getNombre());
		
		// Comprobar si existe la tienda
		if (tienda != null) {
			return null;
		}
		
		return tiendasDAO.crearTienda(t);
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {

		Tienda tienda = tiendasDAO.tiendaPorNombre(nombre);
		
		// Comprobar si existe la tienda
		if (tienda == null) {
			return null;
		}
	
		if (!tienda.getEmpleados().isEmpty()) {
            throw new OperacionNoValidaException("La tienda tiene empleados y no puede ser eliminada.");
        }
		
		return tiendasDAO.eliminarTienda(tienda.getId());
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {
		
		return tiendasDAO.tiendaPorNombre(nombre);
	}

}
