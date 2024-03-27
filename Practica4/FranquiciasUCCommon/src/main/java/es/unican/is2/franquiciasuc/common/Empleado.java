package es.unican.is2.franquiciasuc.common;

import java.time.LocalDate;
<<<<<<< HEAD
import java.util.Objects;
=======

>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
/**
 * Clase que representa un empleado de la franquicia, 
 * con sus datos personales 
 * y su estado en la franquicia (baja y categoria)
 */
public class Empleado {
	
	private String DNI;
	private String nombre;
	private Categoria categoria;
	private LocalDate fechaContratacion;
	private boolean baja = false;
	
	public Empleado() {	}
	
	/**
	 * Constructor del empleado con DNI, nombre, categoria y fecha de contratacion.
	 * Por defecto, baja se inicializa a false. 
	 * @param DNI
	 * @param nombre
	 * @param categoria
	 * @param fechaContratacion
<<<<<<< HEAD
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws OperacionNoValidaException {
=======
	 * @throws OperacionNoValidaException 
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws OperacionNoValidaException {
		
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		if (DNI == null || nombre == null || categoria == null || fechaContratacion == null) {
			throw new NullPointerException();
		}
		
		if (fechaContratacion.isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("La fecha de contratacion no puede ser posterior a hoy");
		}
		
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 */
	public double sueldoBruto() {
		double sueldoBase = 0;
		double complemento = 0;
		double sueldoBruto = 0;
		
		switch (categoria) {
		case ENCARGADO:
			sueldoBase = 2000;
			break;
			
		case VENDEDOR:
			sueldoBase = 1500;
			break;
			
		case AUXILIAR:
			sueldoBase = 1000;
			break;
<<<<<<< HEAD
=======
			
		default:
			sueldoBase = 0;
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
		}
		
		LocalDate fechaActual = LocalDate.now();
		if ((fechaContratacion.plusYears(5).isBefore(fechaActual) && fechaContratacion.plusYears(10).isAfter(fechaActual)) 
				|| fechaContratacion.plusYears(10).isEqual(fechaActual)) {
			complemento = 50;
		} 
		else if ((fechaContratacion.plusYears(10).isBefore(fechaActual) && fechaContratacion.plusYears(20).isAfter(fechaActual))
				|| fechaContratacion.plusYears(20).isEqual(fechaActual)) {
			complemento = 100;
			
		}
		else if (fechaContratacion.plusYears(20).isBefore(fechaActual)) {
			complemento = 200;
		}
		
		sueldoBruto = sueldoBase + complemento;
		
		if (baja) {
			sueldoBruto *= 0.75;
		}
		
		return sueldoBruto;
	}
	
	/** 
	 * Dar de baja al empleado
	 */
	public void darDeBaja() {
		this.baja=true;
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		this.baja=false;
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
	/**
	 * Retorna el dni del vendedor
	 * @return id
	 */
	public String getDNI() {
		return DNI;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Retorna la categoria del empleado
	 *  @return categoria
	 */
	public Categoria getCategoria () {
		return categoria;
	}
	
	/**
	 * Retorna la fecha de contrato
	 * @return Fecha de contratacion
	 */
	public LocalDate getFechaContratacion() {
		return fechaContratacion;
	}
	
	/**
	 * Retorna si el empleado est� de baja
	 * @return true si esta de baja
	 *         false si no lo esta
	 */
	public boolean getBaja() {
		return baja;
	}
<<<<<<< HEAD
=======
		
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
	
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setFechaContratacion(LocalDate fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}
	
	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
<<<<<<< HEAD
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Empleado empleado = (Empleado) obj;
        return Objects.equals(DNI, empleado.DNI);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(DNI);
	}
	
=======
>>>>>>> 5e47dc0b0fe4867c59ae8d9089dea1d48b19e573
}
