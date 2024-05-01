package es.unican.is2.tiendarefactorizada;

import java.util.Objects;

public class VendedorEnPlantilla extends Vendedor {
	
	private TipoVendedor tipo;
	
	/**
	 * Retorna un nuevo vendedor en plantilla del tipo que se indica
	 * @param nombre
	 * @param dni
	 * @param tipo
	 */
	public VendedorEnPlantilla(String nombre, String id, String dni, TipoVendedor tipo) { //WMC+1
		super(nombre, id, dni);
		this.tipo = tipo;
	}
	
	/**
	 * Retorna el tipo del vendedor
	 * @return tipo de vendedor
	 */
	public TipoVendedor getTipo() { //WMC+1
		return tipo;
	}
	
	@Override
	public boolean equals(Object obj) { //WMC+1
		if (!(obj instanceof VendedorEnPlantilla)) //WMC+1 //Ccog+1
			return false;
		VendedorEnPlantilla v = (VendedorEnPlantilla) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
	
	@Override
	public int hashCode() { //WMC+1
		return Objects.hash(getId(), getDni());
	}
	
	@Override
	public String toString() { //WMC+1
		return "VendedorEnPlantilla [id=" + getId() + ", nombre=" + getNombre() + ", tipo=" + tipo + ", dni=" + getDni() + "]";
	}
}
