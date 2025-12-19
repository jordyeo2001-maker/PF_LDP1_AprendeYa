package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;
import util.Conexion;

/**
 *
 * @author GamingWorld
 */
public class UsuarioDAOimpl implements UsuarioDAO {
    
    Conexion cn ;
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r; 

    @Override
    public Usuario validar(String email, String password) {
        Usuario u = null; 
        String sql = "SELECT * FROM usuario WHERE email=? AND password=? AND estado=1";
        try {
            con = Conexion.getConnection(); 
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario")); 
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));
                u.setEstado(rs.getInt("estado"));
            }
        } catch (Exception e) {
            System.err.println("Error en validar: " + e);
        }
        return u;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM usuario";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));
                u.setEstado(rs.getInt("estado"));
                lista.add(u);
            }
        } catch (Exception e) {
            System.err.println("Error en listar: " + e);
        }
        return lista;
    }

    @Override
    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuario(nombre, email, password, rol, estado) VALUES(?,?,?,?,1)";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error en insertar: " + e);
        }
        return false;
    }

    @Override
    public boolean actualizar(Usuario usuario) {
        // Actualizamos todo EXCEPTO el id
        String sql = "UPDATE usuario SET nombre=?, email=?, password=?, rol=?, estado=? WHERE id_usuario=?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getEstado()); // Aquí permitimos editar el estado manualmente si se quiere
            ps.setInt(6, usuario.getIdUsuario()); // El WHERE necesita el ID
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error en actualizar: " + e);
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE usuario SET estado=0 WHERE id_usuario=?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.err.println("Error en eliminar (baja lógica): " + e);
        }
        return false;
    }

    @Override
    public Usuario obtenerPorId(int id) {
        Usuario u = null;
        String sql = "SELECT * FROM usuario WHERE id_usuario=?";
        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new Usuario();
                u.setIdUsuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setRol(rs.getString("rol"));
                u.setEstado(rs.getInt("estado"));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener por ID: " + e);
        }
        return u;
    }
}
