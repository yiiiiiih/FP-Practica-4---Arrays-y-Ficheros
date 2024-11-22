import java.util.Scanner;

public class Teclado {

	private static final String COLORES_VALIDOS = "RVAP";
	private static Scanner entrada = new Scanner(System.in);

	public static int leerEntero(int menor, int mayor, String mensaje) {
		int resultado = 0;
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			if (entrada.hasNextInt()) {
				resultado = entrada.nextInt();
				correcto = resultado >= menor && resultado <= mayor;
				if (!correcto) {
					System.out.println("Por favor, introduce un número entre " + menor + " y " + mayor + ".");
				}
			} else {
				System.out.println("Entrada no válida. Introduce un número entero.");
			}
			entrada.nextLine(); // Limpiar buffer
		} while (!correcto);
		return resultado;
	}

	public static String leerString(String mensaje) {
		System.out.print(mensaje);
		String resultado = entrada.next();
		entrada.nextLine(); // Limpiar buffer
		return resultado;
	}

	public static char leerSiNo(String mensaje) {
		char resultado = ' ';
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			String cadena = entrada.nextLine().toUpperCase();
			if (cadena.length() == 1 && (cadena.charAt(0) == 'S' || cadena.charAt(0) == 'N')) {
				resultado = cadena.charAt(0);
				correcto = true;
			} else {
				System.out.println("Por favor, introduce 'S' para Sí o 'N' para No.");
			}
		} while (!correcto);
		return resultado;
	}

	public static String leerJugadaGuardar(int longitud, String mensaje) {
		String resultado = "";
		boolean correcto = false;
		do {
			System.out.print(mensaje);
			String cadena = entrada.nextLine().toUpperCase();
			if (cadena.equals("G")) {
				return "G";
			} else if (cadena.length() == longitud) {
				boolean esJugada = true;
				for (int i = 0; i < longitud && esJugada; i++) {
					if (!COLORES_VALIDOS.contains(String.valueOf(cadena.charAt(i)))) {
						esJugada = false;
					}
				}
				if (esJugada) {
					correcto = true;
					resultado = cadena;
				} else {
					System.out.println("La jugada contiene colores no válidos. Usa únicamente: R, V, A, P.");
				}
			} else {
				System.out.println("La jugada debe tener exactamente " + longitud + " caracteres.");
			}
		} while (!correcto);
		return resultado;
	}
}