
package pe.edu.ulima.michibackend.dto;

public class UsuarioLoginResponse {
    private String usuario;
    private String msg;

    public UsuarioLoginResponse() {
    }

    public UsuarioLoginResponse(String usuario, String msg) {
        this.msg = msg;
        this.usuario = usuario;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    
}
