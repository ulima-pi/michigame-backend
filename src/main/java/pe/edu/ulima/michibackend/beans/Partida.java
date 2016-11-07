
package pe.edu.ulima.michibackend.beans;

import org.eclipse.jetty.websocket.api.Session;

public class Partida {
    private int partida;
    private String jugador1;
    private String jugador2;
    private String resultado;
    
    /*
    Atributos no persistibles
    */
    private Session ses1;
    private Session ses2;
    private String[][] tablero;

    public Partida() {
    }

    public Partida(int partida, String jugador1, String jugador2, String resultado) {
        this.partida = partida;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.resultado = resultado;
    }

    public int getPartida() {
        return partida;
    }

    public void setPartida(int partida) {
        this.partida = partida;
    }
    
    public String getJugador1() {
        return jugador1;
    }

    public void setJugador1(String jugador1) {
        this.jugador1 = jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public void setJugador2(String jugador2) {
        this.jugador2 = jugador2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Session getSes1() {
        return ses1;
    }

    public void setSes1(Session ses1) {
        this.ses1 = ses1;
    }

    public Session getSes2() {
        return ses2;
    }

    public void setSes2(Session ses2) {
        this.ses2 = ses2;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }
    
    
}
