import java.util.Random;

enum Color {
    ROJO, VERDE, AMARILLO, PURPURA
}

public class Jugada {
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final char CUADRADO = '\u25A0';

    private Color[] fichas;

    public Jugada(String cadena) {
        fichas = new Color[cadena.length()];
        for (int i = 0; i < cadena.length(); i++) {
            switch (cadena.charAt(i)){
                case 'R' :
                    fichas[i]=Color.ROJO;
                    break;

                case 'v':
                    fichas[i]= Color.VERDE;
                    break;

                case 'A':
                    fichas[i] = Color.AMARILLO;
                    break;
                case 'P':
                    fichas[i] = Color.PURPURA;
                    break;
            }

        }
    }

    public Jugada(int numFichas) {
        Random random = new Random();
        fichas = new Color[numFichas];
        Color[] valores = Color.values();// valores son [rojo,verde,amarillo y purpura]

        for (int i = 0; i < numFichas; i++) {
            fichas[i]=valores [random.nextInt(valores.length)];
        }
    }

    public Pistas comprobar(Jugada oculta){
        int negros=0;
        int blancos=0;

        boolean[]usadosOcultas = new boolean[fichas.length];
        boolean[]usadosJugador = new boolean[fichas.length];

        for (int i = 0; i <fichas.length ; i++) {
            if (fichas[i]==oculta.fichas[i]){
                negros++;
                usadosJugador[i]=true;
                usadosOcultas[i]=true;
            }

        }
        for (int i = 0; i <fichas.length ; i++) {
            if (!usadosJugador[i]){ //??????
                boolean encontrado = false;
                for (int j = 0; j <oculta.fichas.length ; j++) {
                    if (!usadosOcultas[j] && fichas[i] == oculta.fichas[j] ){ // si es false ??????
                        blancos++;
                        usadosOcultas[j]=true;
                        encontrado = true;
                    }

                }
                if (encontrado){
                    usadosJugador[i] = true;
                }
            }
        }
        return new Pistas(negros,blancos);
    }


    public void visualizar() {
        for (int i = 0; i < fichas.length; i++) {
            switch (fichas[i]) {
                case ROJO:
                    System.out.print(ANSI_RED + CUADRADO + " ");
                    break;
                case VERDE:
                    System.out.print(ANSI_GREEN + CUADRADO + " ");
                    break;
                case AMARILLO:
                    System.out.print(ANSI_YELLOW + CUADRADO + " ");
                    break;
                case PURPURA:
                    System.out.print(ANSI_PURPLE + CUADRADO + " ");
                    break;
            }
        }
        System.out.print(ANSI_BLACK);
    }

    public String toString() {
        String resultado = "";
        for (int i = 0; i < fichas.length; i++) {
            switch (fichas[i]) {
                case ROJO:
                    resultado += "R";
                    break;
                case VERDE:
                    resultado += "V";
                    break;
                case AMARILLO:
                    resultado += "A";
                    break;
                case PURPURA:
                    resultado += "P";
                    break;
            }
        }
        return resultado;
    }

}