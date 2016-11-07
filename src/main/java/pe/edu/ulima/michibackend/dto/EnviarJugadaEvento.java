
package pe.edu.ulima.michibackend.dto;

public class EnviarJugadaEvento {
    private int evento;
    private String usuario;
    private int fila;
    private int columna;
    private long idPartida;

    public EnviarJugadaEvento(int evento, String usuario, int fila, int columna) {
        this.evento = evento;
        this.usuario = usuario;
        this.fila = fila;
        this.columna = columna;
    }

    public EnviarJugadaEvento(int evento, String usuario, int fila, int columna, long idPartida) {
        this.evento = evento;
        this.usuario = usuario;
        this.fila = fila;
        this.columna = columna;
        this.idPartida = idPartida;
    }

    public EnviarJugadaEvento() {
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(long idPartida) {
        this.idPartida = idPartida;
    }
    
    
    
}
