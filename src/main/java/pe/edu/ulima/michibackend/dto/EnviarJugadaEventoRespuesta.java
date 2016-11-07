
package pe.edu.ulima.michibackend.dto;

public class EnviarJugadaEventoRespuesta {
    private int evento;
    private String msg;

    public EnviarJugadaEventoRespuesta() {
    }

    public EnviarJugadaEventoRespuesta(int evento, String msg) {
        this.evento = evento;
        this.msg = msg;
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
    
    
}
