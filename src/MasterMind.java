import java.io.*;
import java.util.Scanner;

public class MasterMind {
    private Tablero tablero;
    private Jugada jugadaOculta;
    private int numFichas;

    public MasterMind(int numFichas) {
        this.numFichas=numFichas;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public MasterMind(String nombreArchivo) {
        try(Scanner sc = new Scanner(new File(nombreArchivo))){

            String jugadaOc = sc.nextLine();
            this.jugadaOculta = new Jugada((jugadaOc));

            this.numFichas = Integer.parseInt(sc.nextLine());




            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    }

    private void guardarPartida(String nombreArchivo) {
        try()
        // TODO
    }

    public void jugar() {
        // TODO
    }

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
