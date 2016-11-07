
package pe.edu.ulima.michibackend.dto;

public class IniciarPartidaEventoRespuesta {
    private int evento;
    private String msg;
    private long idPartida;

    public IniciarPartidaEventoRespuesta() {
    }

    public IniciarPartidaEventoRespuesta(int evento, String msg) {
        this.evento = evento;
        this.msg = msg;
    }

    public IniciarPartidaEventoRespuesta(int evento, String msg, long idPartida) {
        this.evento = evento;
        this.msg = msg;
        this.idPartida = idPartida;
    }
    
    

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(long idPartida) {
        this.idPartida = idPartida;
    }
    
    
}
