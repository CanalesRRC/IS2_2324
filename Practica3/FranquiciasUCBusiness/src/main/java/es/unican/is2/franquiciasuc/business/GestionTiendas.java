package es.unican.is2.franquiciasuc.business;

import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.IGestionTiendas;
import es.unican.is2.franquiciasuc.common.OperacionNoValidaException;
import es.unican.is2.franquiciasuc.common.Tienda;
import es.unican.is2.franquiciasuc.dao.TiendasDAO;

public class GestionTiendas implements IGestionTiendas {
	
	private TiendasDAO tiendasDAO;

	public GestionTiendas(TiendasDAO tiendasDAO) {
		this.tiendasDAO = tiendasDAO;
	}

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
