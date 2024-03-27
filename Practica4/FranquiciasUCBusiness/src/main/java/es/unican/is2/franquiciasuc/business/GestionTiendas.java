package es.unican.is2.franquiciasuc.business;

import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.IGestionTiendas;
import es.unican.is2.franquiciasuc.common.ITiendasDAO;
import es.unican.is2.franquiciasuc.common.OperacionNoValidaException;
import es.unican.is2.franquiciasuc.common.Tienda;

public class GestionTiendas implements IGestionTiendas{
	
	private ITiendasDAO intTiendas;

	public GestionTiendas(ITiendasDAO intTiendas) {
		this.intTiendas = intTiendas;
	}

	@Override
	public Tienda nuevaTienda(Tienda t) throws DataAccessException {
		
		Tienda tienda = intTiendas.tiendaPorNombre(t.getNombre());
		
		// Comprobar si existe la tienda
		if (tienda != null) {
			return null;
		}
		
		return intTiendas.crearTienda(t);
	}

	@Override
	public Tienda eliminarTienda(String nombre) throws OperacionNoValidaException, DataAccessException {

		Tienda tienda = intTiendas.tiendaPorNombre(nombre);
		
		// Comprobar si existe la tienda
		if (tienda == null) {
			return null;
		}
	
		if (!tienda.getEmpleados().isEmpty()) {
            throw new OperacionNoValidaException("La tienda tiene empleados y no puede ser eliminada.");
        }
		
		return intTiendas.eliminarTienda(tienda.getId());
	}

	@Override
	public Tienda tienda(String nombre) throws DataAccessException {
		
		return intTiendas.tiendaPorNombre(nombre);
	}
<<<<<<< HEAD
=======

>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
}
