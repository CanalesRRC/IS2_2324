package es.unican.is2.franquiciasuc.common;


/**
<<<<<<< HEAD
 * Interfaz de negocio para la gestion de altas y bajas medicas
=======
 * Interfaz de negocio para la gesti�n de altas y bajas medicas
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
 */
public interface IGestionAltasBajas {

	/**
	 * Registrar la baja medica de un empleado
	 * @param dni DNI del empleado
	 * @return true si lo da de baja 
	 *         false si no existe el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean bajaMedica(String dni) throws DataAccessException;

	/**
	 * Registrar el alta medica de un empleado
	 * @param dni DNI del empleado
	 * @return true si lo da de alta 
	 *         false si no existe el empleado
	 * @throws DataAccessException Si hay un error en el acceso a los datos
	 */
	public boolean altaMedica(String dni) throws DataAccessException;
}
