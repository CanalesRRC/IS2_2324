package es.unican.is2.tiendarefactorizada;
/**
 * Vendedor de la tienda. 
 * Por cada vendedor se almacenan sus datos personales 
 * y sus datos sobre ventas y comisiones
 */
public abstract class Vendedor {
	
	private String id;
	private String nombre;
	private double comision;
	private double totalVentas;
	private String DNI;
	
	public Vendedor(String nombre, String id, String dni) { //WMC+1
		this.nombre = nombre;
		this.id = id;
		this.DNI = dni;
	}
	
	/**
	 * Retorna el nombre del vendedor
	 * @return nombre
	 */
	public String getNombre() { //WMC+1
		return nombre;
	}
	
	/**
	 * Retorna el id del vendedor
	 * @return id
	 */
	public String getId() { //WMC+1
		return id;
	}
	
    /**
     * Retorna el DNI del vendedor
     * @return DNI del vendedor
     */
    public String getDni() { //WMC+1
        return DNI;
    }
	
	/**
	 * Retorna la comision mensual acumulada
	 * @return Comision total acumulada
	 */
	public double getComision() { //WMC+1
		return comision;
	}
	
	/**
     * Asigna valor al DNI del vendedor
     * @param dni DNI a asignar
     */
    public void setDni(String dni) { //WMC+1
        this.DNI = dni;
    }
	
	/**
	 * Asigna valor a la comision mensual acumulada
	 * @param value comision a asignar
	 */
	public void setComision(double value) { //WMC+1
		this.comision = value;
	}
	
	/**
	 * Retorna el importe total mensual de ventas
	 * @return importe total de ventas acumuladas
	 */
	public double getTotalVentas( ) { //WMC+1
		return totalVentas;
	}
	
	/**
	 * Asigna valor al total de ventas mensual
	 * @param value total de ventas a asignar
	 */
	public void setTotalVentas(double value) { //WMC+1
		totalVentas = value;
	}
	
	/**
	 * Anhade una nueva venta al vendedor
	 * @param importe de la venta
	 */
	public void anhadeVenta(double importe)  { //WMC+1
		totalVentas += importe; 
	}
}
