package es.unican.is2.franquiciasuc.common;

import java.time.LocalDate;
import java.util.Objects;

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
	 */
	public Empleado(String DNI, String nombre, Categoria categoria, LocalDate fechaContratacion) throws OperacionNoValidaException {
		
		if (DNI == null || nombre == null || categoria == null || fechaContratacion == null) {
			throw new NullPointerException();
		}
		
		if (fechaContratacion.isAfter(LocalDate.now())) {
			throw new OperacionNoValidaException("La fecha de contratacion no puede ser posterior a hoy");
		}
		
		if (DNI.equals(nombre)) {
			throw new OperacionNoValidaException("Los datos introducidos son incorrectos");
		}
		
		this.nombre = nombre;
		this.DNI=DNI;
		this.categoria=categoria;
		this.fechaContratacion=fechaContratacion;
	}
	
	/**
	 * Retorna el sueldo bruto del empleado
	 * @throws OperacionNoValidaException 
	 */
	public double sueldoBruto() throws OperacionNoValidaException {
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
		}
		
		LocalDate fechaActual = LocalDate.now();
		if (fechaContratacion.isAfter(fechaActual)) {
			throw new OperacionNoValidaException("La fecha de contratación es incorrecta.");
		}
		else if (fechaActual.minusYears(20).isAfter(fechaContratacion)) {
			complemento = 200;
		}
		else if (fechaActual.minusYears(10).isAfter(fechaContratacion)) {
			complemento = 100;
		} 
		else if (fechaActual.minusYears(5).isAfter(fechaContratacion)) {
			complemento = 50;
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
		setBaja(true);
	}
	
	/**
	 * Dar de alta al empleado
	 */
	public void darDeAlta() {
		setBaja(false);
	}
	
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

	@Override
	public boolean equals(Object obj) {
        Empleado empleado = (Empleado) obj;
        return baja == empleado.baja &&
                Objects.equals(DNI, empleado.DNI) &&
                Objects.equals(nombre, empleado.nombre) &&
                categoria == empleado.categoria &&
                fechaContratacion.isEqual(empleado.fechaContratacion);
    }
	
	@Override
    public int hashCode() {
        return Objects.hash(DNI);
    }

}
