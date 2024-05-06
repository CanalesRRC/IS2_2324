package es.unican.is2.tiendasonarqube;

import java.util.Objects;

public class VendedorEnPracticas extends Vendedor {
		
	/**
	 * Retorna un nuevo vendedor en practicas
	 * @param nombre
	 * @param DNI
	 */
	public VendedorEnPracticas(String nombre, String id, String dni) { //WMC+1
		super(nombre, id, dni);
	}

	@Override
	public boolean equals(Object obj) { //WMC+1
		if (!(obj instanceof VendedorEnPracticas)) //WMC+1 //Ccog+1
			return false;
		VendedorEnPracticas v = (VendedorEnPracticas) obj;
		return (v.getId().equals(getId()) && v.getDni().equals(getDni()));
	}
	
	@Override
	public int hashCode() { //WMC+1
		return Objects.hash(getId(), getDni());
	}
	
	@Override
	public String toString() { //WMC+1
		return "VendedorEnPracticas [id=" + getId() + ", nombre=" + getNombre() + ", dni=" + getDni() + "]";
	}
}
