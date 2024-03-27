package es.unican.is2.franquiciasuc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import es.unican.is2.franquiciasuc.common.Categoria;
import es.unican.is2.franquiciasuc.common.DataAccessException;
import es.unican.is2.franquiciasuc.common.Empleado;

/**
 * Clase de utilidad que mapea filas de la base de datos a objetos 
 * de tipo Empleado
 */
public class EmpleadoMapper {

	/**
<<<<<<< HEAD
	 * Metodo privado de apoyo. Recibe un ResultSet de un empleado
=======
	 * M�todo privado de apoyo. Recibe un ResultSet de un empleado
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
	 * y devuelve un objeto Empleado con los datos del ResultSet
	 * @param results Fila resultado de una consulta en base de datos
	 * @return Empleado
	 */
	public static Empleado toEmpleado(ResultSet results) throws DataAccessException {

		Empleado emp = new Empleado();
		try {
			emp.setDNI(results.getString("dni"));
			emp.setNombre(results.getString("nombre"));
			emp.setBaja(results.getBoolean("baja"));
			emp.setFechaContratacion(results.getDate("fechaContratacion").toLocalDate());
			emp.setCategoria(Categoria.valueOf(results.getString("categoria")));
		}
		catch (SQLException e) {
			throw new DataAccessException();
		}
		return emp;
	}
}
