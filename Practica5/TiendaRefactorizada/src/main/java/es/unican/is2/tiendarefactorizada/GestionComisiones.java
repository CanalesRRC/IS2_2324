package es.unican.is2.tiendarefactorizada;

import fundamentos.Menu;
import fundamentos.Mensaje;

/**
 * Gestion de las comisiones de vendedores de una tienda
 */
public class GestionComisiones {
	
    private static final int NUEVA_VENTA = 0;
    private static final int VENDEDOR_DEL_MES = 1;
    private static final int VENDEDORES = 2;

	/**
	 * Programa principal basado en menu
	 */
	public static void main(String[] args) { //WMC+1 
		
		// crea la tienda
		Tienda tienda = new Tienda("C:\\temp\\datosTienda.txt");
		
		String mnsj = null;

		// crea la ventana de menu
		Menu menu = new Menu("Comisiones tienda");
		menu.insertaOpcion("Anhadir venta", NUEVA_VENTA);
		menu.insertaOpcion("Vendedor del mes", VENDEDOR_DEL_MES);
		menu.insertaOpcion("Vendedores por ventas", VENDEDORES);
		int opcion;

		// lazo de espera de comandos del usuario
		while (true) { // WMC+1 //Ccog+1
			opcion = menu.leeOpcion();

			// realiza las acciones dependiendo de la opcion elegida
			switch (opcion) { //Ccog+2
			case NUEVA_VENTA: // WMC+1
				mnsj = tienda.procesarNuevaVenta();
				mensaje("NUEVA_VENTA", mnsj);
				break;

			case VENDEDOR_DEL_MES: // WMC+1
				mnsj = tienda.obtenerVendedorDelMes();
				mensaje("VENDEDOR_DEL_MES", mnsj);
				break;

			case VENDEDORES: // WMC+1
				mnsj = tienda.obtenerVendedoresPorVentas();
				mensaje("VENDEDORES", mnsj);
				break;
			}
		}
	}

	/**
	 * Metodo auxiliar que muestra un ventana de mensaje
	 * @param titulo Titulo de la ventana
	 * @param txt    Texto contenido en la ventana
	 */
	private static void mensaje(String titulo, String txt) { // WMC+1
		Mensaje msj = new Mensaje(titulo);
		msj.escribe(txt);

	}

}
