package es.unican.is2.franquiciasuc.business;

import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.Empleado;
import es.unican.is2.franquiciasuc.common.IEmpleadosDAO;
<<<<<<< HEAD
import es.unican.is2.franquiciasuc.common.IGestionAltasBajas;
=======
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
import es.unican.is2.franquiciasuc.common.IGestionEmpleados;
import es.unican.is2.franquiciasuc.common.ITiendasDAO;
import es.unican.is2.franquiciasuc.common.OperacionNoValidaException;
import es.unican.is2.franquiciasuc.common.Tienda;

<<<<<<< HEAD
public class GestionEmpleados implements IGestionEmpleados, IGestionAltasBajas{
	
	private ITiendasDAO intTiendas;
	private IEmpleadosDAO intEmpleados;

	public GestionEmpleados(ITiendasDAO intTiendas, IEmpleadosDAO intEmpleados) {
		this.intTiendas = intTiendas;
		this.intEmpleados = intEmpleados;
=======
public class GestionEmpleados implements IGestionEmpleados{
	
	private ITiendasDAO intTienda;
	private IEmpleadosDAO intEmpleado;

	public GestionEmpleados(ITiendasDAO intTienda, IEmpleadosDAO intEmpleado) {
		this.intTienda = intTienda;
		this.intEmpleado = intEmpleado;
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
	}

	@Override
	public Empleado nuevoEmpleado(Empleado e, String nombre) throws OperacionNoValidaException, DataAccessException {
		
<<<<<<< HEAD
		Tienda t = intTiendas.tiendaPorNombre(nombre);
=======
		Tienda t = intTienda.tiendaPorNombre(nombre);
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		
		// Comprobar si la tienda existe
		if (t == null) {
			return null;
		}
		
		// Comprobar si ya existe el empleado
		if (empleado(e.getDNI()) != null) {
			throw new OperacionNoValidaException("El empleado ya existe");
	    }
		
		// Anhadir empleado al sistema
<<<<<<< HEAD
		intEmpleados.crearEmpleado(e);
=======
		intEmpleado.crearEmpleado(e);
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		
		// Anhadir empleado a la tienda
		t.getEmpleados().add(e);
		
<<<<<<< HEAD
		// Actualizar tienda
		intTiendas.modificarTienda(t);
		
=======
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		// Retornar el empleado anhadido
		return e;
	}

	@Override
	public Empleado eliminarEmpleado(String dni, String nombre) throws OperacionNoValidaException, DataAccessException {

<<<<<<< HEAD
		Tienda t = intTiendas.tiendaPorNombre(nombre);
=======
		Tienda t = intTienda.tiendaPorNombre(nombre);
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
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
		
<<<<<<< HEAD
		// Actualizar tienda
		intTiendas.modificarTienda(t);
		
		// Eliminar empleado del sistema
		intEmpleados.eliminarEmpleado(dni);
=======
		// Eliminar empleado del sistema
		intEmpleado.eliminarEmpleado(dni);
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		
		return e;
	}

	@Override
	public boolean trasladarEmpleado(String dni, String actual, String destino)
			throws OperacionNoValidaException, DataAccessException {
		
<<<<<<< HEAD
		Tienda tActual = intTiendas.tiendaPorNombre(actual);
		Tienda tDestino = intTiendas.tiendaPorNombre(destino);
=======
		Tienda tActual = intTienda.tiendaPorNombre(actual);
		Tienda tDestino = intTienda.tiendaPorNombre(destino);
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
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
		
<<<<<<< HEAD
		// Actualizar tienda actual
		intTiendas.modificarTienda(tActual);
		
		// Actualizar tienda destino
		intTiendas.modificarTienda(tDestino);
		
=======
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		return true;
	}

	@Override
	public Empleado empleado(String dni) throws DataAccessException {
<<<<<<< HEAD
		return intEmpleados.empleado(dni);
    }

	@Override
	public boolean bajaMedica(String dni) throws DataAccessException {
		
		Empleado e = empleado(dni);
		// Comprobar si existe el empleado
		if (e == null) {
			return false;
		}
		
		e.darDeBaja();
		return true;
	}

	@Override
	public boolean altaMedica(String dni) throws DataAccessException {
		
		Empleado e = empleado(dni);
		// Comprobar si existe el empleado
		if (e == null) {
			return false;
		}
		
		e.darDeAlta();
		return true;
	}
=======
		return intEmpleado.empleado(dni);
    }

>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
}
