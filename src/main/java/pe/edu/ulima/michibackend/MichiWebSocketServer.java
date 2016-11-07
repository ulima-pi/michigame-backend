package pe.edu.ulima.michibackend;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import pe.edu.ulima.michibackend.beans.Partida;
import pe.edu.ulima.michibackend.dto.EnviarJugadaEvento;
import pe.edu.ulima.michibackend.dto.IniciarPartidaEvento;
import pe.edu.ulima.michibackend.dto.IniciarPartidaEventoRespuesta;

@WebSocket
public class MichiWebSocketServer {

    private HashMap<Long, Partida> partidas = new HashMap<>();

    @OnWebSocketConnect
    public void connected(Session session) {

    }

    @OnWebSocketClose
    public void closed(Session session, int statusCode, String reason) {
        //sessions.remove(session);
    }

    @OnWebSocketMessage
    public void message(Session session, String message) throws IOException {
        /*
        Eventos:
        1: Iniciar partida
        2: Enviar jugada
        3. Observar
         */
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(message);
        int evento = element.getAsJsonObject().get("evento").getAsInt();

        switch (evento) {
            case 1:
                iniciarJugada(session, message);
                break;
            case 2:
                enviarJugada(message);
                break;
            default:
                observar(message);
                break;
        }

    }

    private void iniciarJugada(Session session, String message) throws IOException {
        Gson gson = new Gson();
        IniciarPartidaEvento iniciarPartidaEvento
                = gson.fromJson(message, IniciarPartidaEvento.class);

        Long idPartida = (Long) partidas.keySet().toArray()[partidas.size() - 1];
        Partida partidaUltima = partidas.get(idPartida);

        IniciarPartidaEventoRespuesta iniciarPartidaEventoRespuesta
                = new IniciarPartidaEventoRespuesta();
        if (partidaUltima.getSes2() == null) {
            // Hay espacio
            partidaUltima.setJugador2(iniciarPartidaEvento.getUsuario());
            partidaUltima.setSes2(session);
            partidaUltima.setTablero(new String[3][3]);
            iniciarPartidaEventoRespuesta.setEvento(1);
            iniciarPartidaEventoRespuesta.setMsg("iniciar");
            iniciarPartidaEventoRespuesta.setIdPartida(idPartida);
        } else {
            // Creamos una nueva partida
            Partida partida = new Partida();
            partida.setJugador1(iniciarPartidaEvento.getUsuario());
            partida.setSes1(session);
            partidas.put(new Date().getTime(), partida);
            iniciarPartidaEventoRespuesta.setEvento(1);
            iniciarPartidaEventoRespuesta.setMsg("esperando");
        }
        session.getRemote().sendString(
                gson.toJson(iniciarPartidaEventoRespuesta)
        );

    }

    private void enviarJugada(String message) {
        /*
        Obtengo jugada
        Marca jugada
        Verifico si con jugada gana;
        enviar con jugada e indicando si gano (si es el caso)
         */
        Gson gson = new Gson();
        EnviarJugadaEvento enviarJugadaEvento = gson.fromJson(message,
                EnviarJugadaEvento.class);
        Partida partida = partidas.get(enviarJugadaEvento.getIdPartida());
        int res = jugarYVerificarPartida(
                partida,
                enviarJugadaEvento.getFila(),
                enviarJugadaEvento.getColumna(),
                enviarJugadaEvento.getUsuario());
    }

    private void observar(String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int jugarYVerificarPartida(Partida partida, int fila, int columna,
            String usuario) {
        String[][] tablero = partida.getTablero();
        tablero[fila][columna] = usuario;

        int longitud = tablero.length;
        int resultado = 0;
        for (int i = 0; i < longitud && resultado == 0; i++) {
            resultado = verificarFila(tablero, i, usuario);
        }

        for (int j = 0; j < longitud && resultado == 0; j++) {
            resultado = verificarColumna(tablero, j, usuario);
        }

        if (resultado == 0) {
            if ((resultado = verificarDiagonalSuperior(tablero, usuario)) == 0) {
                return verificarDiagonalInferior(tablero, usuario);
            }else{
                return resultado;
            }
        }else{
            return resultado;
        }

    }

    private int verificarFila(String[][] tablero, int i, String usuario) {
        return 1;
    }

    private int verificarColumna(String[][] tablero, int j, String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int verificarDiagonalSuperior(String[][] tablero, String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int verificarDiagonalInferior(String[][] tablero, String usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}