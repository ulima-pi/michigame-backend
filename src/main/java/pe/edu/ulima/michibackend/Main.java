package pe.edu.ulima.michibackend;

import com.google.gson.Gson;
import pe.edu.ulima.michibackend.beans.Usuario;
import pe.edu.ulima.michibackend.dao.UsuarioDAO;
import pe.edu.ulima.michibackend.dto.UsuarioLoginResponse;
import pe.edu.ulima.michibackend.dto.UsuarioRequest;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.options;
import static spark.Spark.post;
import static spark.Spark.stop;

public class Main {

    public static void main(String[] args) {
        enableCORS("*", "*", "*");
        get("/parar", (req, res) -> {
            stop();
            return "";
        });
        post("/usuarios/login", (req, res) -> {
            Gson gson = new Gson();
            UsuarioRequest peticion
                    = gson.fromJson(req.body(), UsuarioRequest.class);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.abrirConexion();
            Usuario usuario = usuarioDAO.existe(new Usuario(
                    peticion.getUsuario(),
                    peticion.getPassword()
            ));
            usuarioDAO.getConnection().close();
            UsuarioLoginResponse respuesta = new UsuarioLoginResponse();
            if (usuario == null) {
                respuesta.setMsg("No existe el usuario");
            } else {
                respuesta.setUsuario(usuario.getUsuario());
                respuesta.setMsg("");
            }

            return gson.toJson(respuesta);
        });
        post("/usuarios", (req, res) -> {
            Gson gson = new Gson();
            UsuarioRequest peticion
                    = gson.fromJson(req.body(), UsuarioRequest.class);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.abrirConexion();
            try{
                usuarioDAO.registrar(new Usuario(
                        peticion.getUsuario(),
                        peticion.getPassword()
                ));
            }catch(Exception e){
                usuarioDAO.getConnection().close();
                UsuarioLoginResponse respuesta = new UsuarioLoginResponse();
                respuesta.setMsg(e.getMessage());
                return gson.toJson(respuesta);
            }
            usuarioDAO.getConnection().close();
            UsuarioLoginResponse respuesta = new UsuarioLoginResponse();
            respuesta.setMsg("");

            return gson.toJson(respuesta);
        });
    }

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }

}
