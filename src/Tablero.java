public class Tablero {
    private final static int MAX_JUGADAS = 10;

    private Jugada[] jugadas;
    private Pistas[] pistas;
    private int numJugadas;

    public Tablero() {
       this.jugadas= new Jugada[MAX_JUGADAS];
       this.pistas = new Pistas[MAX_JUGADAS];
       this.numJugadas=0;
    }

    public int getNumJugadas() {
       return numJugadas;
    }

    public Jugada[] getJugadas() {
        return jugadas;
    }

    public Pistas[] getResultados() {
        return  pistas;
    }

    public void insertar(Jugada jugada, Pistas pista) {
        if (numJugadas < MAX_JUGADAS){
            pistas[numJugadas]= pista;
            jugadas[numJugadas]=jugada;
            numJugadas++;
        }else {
            System.out.println("Se ha alcanzado el número máximo de jugadas.");
        }
    }

    public boolean completo() {
        return numJugadas >= MAX_JUGADAS;
    }

    public void visualizar() {
        System.out.println("\nTablero: ");
        for (int i = 0; i < numJugadas; i++) {
            System.out.println("Jugada "+(i+1)+ " : "+ jugadas[i] +"  "+pistas[i]);

        }
    }


}
