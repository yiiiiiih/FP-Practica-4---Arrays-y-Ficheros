import java.io.*;
import java.util.Scanner;

public class MasterMind {
    private Tablero tablero;
    private Jugada jugadaOculta;
    private int numFichas;

    // Constructor para nueva partida
    public MasterMind(int numFichas) {
        this.numFichas = numFichas;
        this.tablero = new Tablero();
        this.jugadaOculta = new Jugada(numFichas);
    }

    public MasterMind(String nombreArchivo) {
        try (Scanner sc = new Scanner(new File(nombreArchivo))) {
            String jugadaOc = sc.nextLine();
            this.jugadaOculta = new Jugada(jugadaOc);

            this.numFichas = jugadaOc.length();
            this.tablero = new Tablero();
            while (sc.hasNextLine()) {
                String[] partes = sc.nextLine().split(" ");
                Jugada jugada = new Jugada(partes[0]);
                Pistas pista = new Pistas(Integer.parseInt(partes[1]), Integer.parseInt(partes[2]));
                this.tablero.insertar(jugada, pista);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo.");
            throw new RuntimeException(e);
        }
    }

    public Tablero getTablero() {
        return tablero;
    }

    private void guardarPartida(String nombreArchivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(nombreArchivo))) {
            pw.println(jugadaOculta.toString());

            for (int i = 0; i < tablero.getNumJugadas(); i++) {
                Jugada jugada = tablero.getJugadas()[i];
                Pistas pista = tablero.getPistas()[i];
                pw.println(jugada + " " + pista.getAciertos() + " " + pista.getDescolocados());
            }
            System.out.println("Partida guardada con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar la partida.");
        }
    }

    // Método principal para jugar
    public void jugar() {
        System.out.println("¡Comienza el juego de MasterMind!");
        boolean haGanado = false;

        while (!tablero.completo() && !haGanado) {
            String input = Teclado.leerJugadaGuardar(numFichas,
                    "Introduce jugada o G (guardar la partida).\nR (Rojo), V (Verde), A (Amarillo), P (Púrpura): ");
            if (input.equals("G")) {
                String nombreArchivo = Teclado.leerString("Nombre del archivo: ");
                guardarPartida(nombreArchivo);
                return;
            }

            Jugada jugada = new Jugada(input);
            Pistas pista = jugada.comprobar(jugadaOculta);
            tablero.insertar(jugada, pista);

            tablero.visualizar();
            if (pista.getAciertos() == numFichas) {
                System.out.println("¡ACERTASTE LA JUGADA!");
                haGanado = true;
            }
        }

        if (!haGanado) {
            System.out.println("FIN DE LOS INTENTOS, NO CONSEGUISTE ACERTAR.");
            System.out.print("La jugada oculta era: ");
            jugadaOculta.visualizar();
        }
    }

    // Metodo main
    public static void main(String[] args) {
        MasterMind masterMind;
        if (Teclado.leerSiNo("¿Quieres recuperar una partida? (S/N): ") == 'S') {
            String nombreArchivo = Teclado.leerString("Nombre del archivo: ");
            masterMind = new MasterMind(nombreArchivo);
            masterMind.getTablero().visualizar();
        } else {
            int fichas = Teclado.leerEntero(4, 6, "Número de fichas de las jugadas (4 - 6): ");
            masterMind = new MasterMind(fichas);
        }
        masterMind.jugar();
    }
}