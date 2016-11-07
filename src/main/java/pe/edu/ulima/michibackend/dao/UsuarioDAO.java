
package pe.edu.ulima.michibackend.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.ulima.michibackend.beans.Usuario;

public class UsuarioDAO {
    private Connection mConnection;
    
    public void setConnection(Connection conn){
        mConnection = conn;
    }
    public Connection getConnection(){
        return mConnection;
    }
    public void abrirConexion() throws SQLException{
        mConnection = DriverManager.getConnection(
                Configuracion.URL_BD, 
                Configuracion.USER_BD, 
                Configuracion.PWD_BD);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void registrar(Usuario usuario) throws SQLException{
        if (mConnection == null){
            abrirConexion();
        }
        String sql = "INSERT INTO usuario (usuario, password) VALUES (?, ?)";
        PreparedStatement ps = mConnection.prepareStatement(sql);
        ps.setString(1, usuario.getUsuario());
        ps.setString(2, usuario.getPassword());
        
        ps.execute();
        
    }
    public Usuario obtener(String username) throws SQLException{
        if (mConnection == null){
            abrirConexion();
        }
        String sql = "SELECT * FROM usuario WHERE usuario=?";
        PreparedStatement ps = mConnection.prepareStatement(sql);
        ps.setString(1, username);
        
        Usuario usuario = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            usuario = new Usuario(
                    rs.getString("usuario"),
                    rs.getString("password")
            );
        }
        return usuario;
    }
    
    public Usuario existe(Usuario usuario) throws SQLException{
        String sql = "SELECT * FROM usuario WHERE usuario=? and password=?";
        PreparedStatement ps = mConnection.prepareStatement(sql);
        ps.setString(1, usuario.getUsuario());
        ps.setString(2, usuario.getPassword());
        
        Usuario usu = null;
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            usu = new Usuario(
                    rs.getString("usuario"),
                    rs.getString("password")
            );
        }
        return usu;
    }
}
