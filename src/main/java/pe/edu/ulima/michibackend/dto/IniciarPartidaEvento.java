
package pe.edu.ulima.michibackend.dto;

public class IniciarPartidaEvento {
    private int evento;
    private String usuario;

    public IniciarPartidaEvento() {
    }

    public IniciarPartidaEvento(int evento, String usuario) {
        this.evento = evento;
        this.usuario = usuario;
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
    
    
}
