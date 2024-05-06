package es.unican.is2.tiendasonarqube;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import fundamentos.Lectura;

/**
 * Clase que representa una tienda con un conjunto de vendedores. Gestiona las
 * ventas realizadas y las comisiones asignadas a cada vendedor. Los datos de la
 * tienda se almacenan en un fichero de texto que se pasa como parametro al
 * crear la tienda
 */
public class Tienda {

	private static final double COMISION_JUNIOR = 0.005;
	private static final double COMISION_SENIOR = 0.01;

	private List<Vendedor> lista = new LinkedList<Vendedor>();
	private String direccion;
	private String nombreTienda;
	private String datos;

	/**
	 * Crea la tienda cargando los datos desde el fichero indicado
	 * @param datos Path absoluto del fichero de datos
	 */
	public Tienda(String datos) { // WMC+1
		this.datos = datos;
	}

	/**
	 * Retorna la direccion de la tienda
	 * @return Direccion de la tienda
	 */
	public String getDireccion() { // WMC+1
		return direccion;
	}

	/**
	 * Retorna el nombre de la tienda
	 * @return Nombre de la tienda
	 */
	public String getNombreTienda() {	 // WMC+1
		return nombreTienda;
	}

	/**
	 * Anhade un nuevo vendedor a la tienda
	 * @param nuevo El vendedor a anhadir
	 * @return true si el vendedor se ha anhadido 
	 *         false si ya existe el vendedor
	 */
	public boolean anhadeVendedor(Vendedor nuevo) throws DataAccessException { // WMC+1
		Vendedor v = buscaVendedor(nuevo.getId());
		if (v != null) { // WMC+1 //Ccog+1
			return false;
		}
		lista.add(nuevo);
		vuelcaDatos();
		return true;
	}

	/**
	 * Elimina el vendedor cuyo id se pasa como argumento
	 * @param id
	 * @return true si se elimina el vendedor false si no existe el vendedor
	 */
	public boolean eliminaVendedor(String id) throws DataAccessException { // WMC+1
		Vendedor v = buscaVendedor(id);
		if (v == null) { // WMC+1 //Ccog+1
			return false;
		}
		lista.remove(v);
		vuelcaDatos();
		return true;
	}

	/**
	 * Anhade una venta a un vendedor
	 * @param id      Id del vendedor
	 * @param importe Importe de la venta
	 * @return true si se anhade la venta false si no se encuentra el vendedor
	 */
	public boolean anhadeVenta(String id, double importe) throws DataAccessException { // WMC+1
		Vendedor v = buscaVendedor(id);
		if (v == null) { //WMC+1 //Ccog+1
			return false;
		}
		double comision = 0;
		if (v instanceof VendedorEnPlantilla) {	 // WMC+1 //Ccog+1
			switch (((VendedorEnPlantilla) v).getTipo()) { //Ccog+2
			case JUNIOR:	// WMC+1
				comision = importe * COMISION_JUNIOR;
				break;
			case SENIOR:	// WMC+1
				comision = importe * COMISION_SENIOR;
				break;
			}
		}
		v.anhadeVenta(importe);
		v.setComision(v.getComision()+comision);
		vuelcaDatos();
		return true;
	}

	/**
	 * Retorna el vendedor con el id indicado
	 * 
	 * @param id Id del vendedor
	 * @return vendedor con ese dni o null si no existe ninguno
	 */
	public Vendedor buscaVendedor(String id) throws DataAccessException {	// WMC+1
		List<Vendedor> vendedores = obtenerVendedores();

		for (Vendedor v : vendedores) {	// WMC+1 //Ccog+1
			if (v.getId().equals(id)) {	// WMC+1 //Ccog+2
				return v;
			}
		}
		return null;
	}

	/**
	 * Retorna la lista de vendedores actuales de la tienda
	 * 
	 * @return La lista de vendedores
	 */
	public List<Vendedor> obtenerVendedores() throws DataAccessException {	// WMC+1
		return leeDatosDeArchivo();
	}

	/**
	 * Lee los datos del archivo especificado y carga la información de los vendedores en la lista.
	 * @return La lista de vendedores leida desde el archivo.
	 * @throws DataAccessException Si hay algún problema de acceso a los datos.
	 */
	private List<Vendedor> leeDatosDeArchivo() throws DataAccessException { // WMC+1
		lista = new LinkedList<Vendedor>();

		try (Scanner in = new Scanner(new FileReader(datos))) {
			in.useLocale(Locale.ENGLISH);
			nombreTienda = in.nextLine();
			direccion = in.nextLine();
			in.next();
			Vendedor vendedor = null;
			while (in.hasNext() && !in.next().equals("Junior")) { // WMC+2 //Ccog+2
				vendedor = leerVendedorEnPlantilla(in, TipoVendedor.SENIOR);
				lista.add(vendedor);
			}
			while (in.hasNext() && !in.next().equals("Practicas")) { // WMC+2 //Ccog+2
				vendedor = leerVendedorEnPlantilla(in, TipoVendedor.JUNIOR);
				lista.add(vendedor);
			}
			while (in.hasNext()) { // WMC+1 //Ccog+1
				vendedor = leerVendedorEnPracticas(in);
				lista.add(vendedor);
			}
		} catch (FileNotFoundException e) { // WMC+1 //Ccog+1
			throw new DataAccessException();
		} 
		return lista;
	}

	/**
	 * Lee los datos de un vendedor en plantilla desde un objeto Scanner y devuelve un objeto Vendedor correspondiente.
	 * @param in El objeto Scanner que contiene los datos del vendedor.
	 * @param tipo El tipo de vendedor (Junior o Senior).
	 * @return El objeto VendedorEnPlantilla creado con los datos leidos.
	 */
	private Vendedor leerVendedorEnPlantilla(Scanner in, TipoVendedor tipo) { // WMC+1
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni = in.next();
		in.next();
		double totalVentas = in.nextDouble();
		in.next();
		double totalComision = in.nextDouble();
		Vendedor vendedor = new VendedorEnPlantilla(nombre, idIn, dni, tipo);
		vendedor.setTotalVentas(totalVentas);
		vendedor.setComision(totalComision);

		return vendedor;
	}

	/**
	 * Lee los datos de un vendedor en practicas desde un objeto Scanner y devuelve un objeto Vendedor correspondiente.
	 * @param in El objeto Scanner que contiene los datos del vendedor.
	 * @return El objeto VendedorEnPlantilla creado con los datos leidos.
	 */
	private Vendedor leerVendedorEnPracticas(Scanner in) { // WMC+1
		in.next();
		String nombre = in.next();
		in.next();
		String idIn = in.next();
		in.next();
		String dni = in.next();
		in.next();
		double totalVentas = in.nextDouble();
		Vendedor vendedor = new VendedorEnPracticas(nombre, idIn, dni);
		vendedor.setTotalVentas(totalVentas);

		return vendedor;
	}

	/**
	 * Actualiza el fichero datosTienda.txt con los datos actualizados de
	 * los vendedores
	 */
	private void vuelcaDatos() throws DataAccessException { // WMC+1
		try (PrintWriter out = new PrintWriter(new FileWriter(datos))) {
			out.println(nombreTienda);
			out.println(direccion);
			out.println();

			List<Vendedor> senior = new LinkedList<>();
			List<Vendedor> junior = new LinkedList<>();
			List<Vendedor> practicas = new LinkedList<>();

			for (Vendedor v : lista) { // WMC+1 //Ccog+1
				if (v instanceof VendedorEnPracticas) { // WMC+1 //Ccog+2
					practicas.add(v);
				} else if (v instanceof VendedorEnPlantilla) { // WMC+1 //Ccog+1
					VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
					if (vp.getTipo().equals(TipoVendedor.JUNIOR)) { // WMC+1 //Ccog+3
						junior.add(vp);
					} else { //Ccog+1
						senior.add(vp);
					}   
				}
			}

			escribirVendedoresPlantilla(out, "Senior", senior);
			escribirVendedoresPlantilla(out, "Junior", junior);
			escribirVendedoresPracticas(out, "Practicas", practicas);
		} catch (IOException e) { // WMC+1 //Ccog+1
			throw new DataAccessException();
		}
	}

	/**
	 * Escribe los datos de los vendedores en plantilla en el archivo de salida especificado.
	 * @param out El objeto PrintWriter para escribir en el archivo de salida.
	 * @param tipo El tipo de vendedor (Junior o Senior).
	 * @param vendedores La lista de vendedores en plantilla a escribir.
	 */
	private void escribirVendedoresPlantilla(PrintWriter out, String tipo, List<Vendedor> vendedores) { // WMC+1
		out.println(tipo);
		for (Vendedor v : vendedores) { // WMC+1 //Ccog+1
			VendedorEnPlantilla vp = (VendedorEnPlantilla) v;
			out.println("  Nombre: " + vp.getNombre() + " Id: " + vp.getId() + " DNI: " + vp.getDni()
			+ " TotalVentasMes: " + vp.getTotalVentas() + " TotalComision: " + vp.getComision());
		}
		out.println();
	}

	/**
	 * Escribe los datos de los vendedores en practicas en el archivo de salida especificado.
	 * @param out El objeto PrintWriter para escribir en el archivo de salida.
	 * @param tipo El tipo de vendedor (Practicas).
	 * @param vendedores La lista de vendedores en practicas a escribir.
	 */
	private void escribirVendedoresPracticas(PrintWriter out, String tipo, List<Vendedor> vendedores) { // WMC+1
		out.println(tipo);
		for (Vendedor v : vendedores) { // WMC+1 //Ccog+1
			VendedorEnPracticas vp = (VendedorEnPracticas) v;
			out.println("  Nombre: " + vp.getNombre() + " Id: " + vp.getId() + " DNI: " + vp.getDni()
			+ " TotalVentasMes: " + vp.getTotalVentas());
		}
		out.println();
	}

	/**
	 * Obtiene el vendedor del mes basado en las ventas totales y devuelve su nombre.
	 * @return El nombre del vendedor del mes.
	 */
	public String obtenerVendedorDelMes() { // WMC+1
		List<Vendedor> vendedores = new LinkedList<Vendedor>();
		String ms = "";

		try {
			vendedores = obtenerVendedores();
			List<Vendedor> resultado = new LinkedList<Vendedor>();
			double maxVentas = 0.0;
			for (Vendedor v : vendedores) {	//WMC+1 //Ccog+1
				if (v.getTotalVentas() > maxVentas) {	//WMC+1 //Ccog+2
					maxVentas = v.getTotalVentas();
					resultado.clear();
					resultado.add(v);
				} else if (v.getTotalVentas() == maxVentas && !resultado.contains(v)) {	//WMC+1 //Ccog+1
					resultado.add(v);
				}
			}
			for (Vendedor vn : resultado) {	//WMC+1 //Ccog+1
				ms += vn.getNombre() + "\n";
			}
			return ms;

		} catch (DataAccessException e) {	//WMC+1 //Ccog+1
			ms = "No se pudo acceder a los datos";
			return ms;
		}
	}

	/**
	 * Obtiene una lista de vendedores ordenada por ventas totales en orden descendente y devuelve un mensaje con sus nombres, IDs y ventas totales.
	 * @return Un mensaje que contiene los nombres, IDs y ventas totales de los vendedores ordenados por ventas.
	 */
	public String obtenerVendedoresPorVentas() { // WMC+1
		List<Vendedor> vendedores = new LinkedList<Vendedor>();
		String msj;

		try {
			vendedores = obtenerVendedores();
			System.out.println(vendedores.size());
			Collections.sort(vendedores, (o1, o2) -> {
				if (o1.getTotalVentas() > o2.getTotalVentas()) // WMC+1 //Ccog+1
					return -1;
				else if (o1.getTotalVentas() < o2.getTotalVentas()) // WMC+1 //Ccog+1
					return 1;
				return 0;
			});
			msj = "";
			for (Vendedor vn : vendedores) { // WMC+1 //Ccog+1
				msj += vn.getNombre() + " (" + vn.getId()+ ") "+vn.getTotalVentas() + "\n";
			}
			return msj;
		} catch (DataAccessException e) { // WMC+1 //Ccog+1
			msj = "No se pudo acceder a los datos";
			return msj;
		}
	}

	/**
	 * Procesa una nueva venta ingresando el ID del vendedor y el importe de la venta,
	 * y devuelve un mensaje indicando si la venta se ha anhadido correctamente o si ha habido algun problema.
	 * @return Un mensaje indicando el resultado del proceso de la venta.
	 */
	public String procesarNuevaVenta() { // WMC+1
		String id;
		Lectura lect;
		String msj = "";

		lect = new Lectura("Datos Venta");
		lect.creaEntrada("ID Vendedor", "");
		lect.creaEntrada("Importe", "");
		lect.esperaYCierra();
		id = lect.leeString("ID Vendedor");
		double importe = lect.leeDouble("Importe");
		try {
			if (!anhadeVenta(id, importe)) { // WMC+1 //Ccog+1
				msj = "El vendedor no existe";
			} else { //Ccog+1
				msj = "Venta añadida";
			}
		} catch (DataAccessException e) { // WMC+1 //Ccog+1
			msj = "No se pudo guardar el cambio";
		}
		return msj;
	}
}
