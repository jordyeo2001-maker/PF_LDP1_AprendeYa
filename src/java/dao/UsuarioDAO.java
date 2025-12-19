/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.Usuario;

/**
 *
 * @author GamingWorld
 */
public interface UsuarioDAO {
    
   
    public Usuario validar(String email, String password);
  
    public List<Usuario> listar();
  
    public boolean insertar(Usuario usuario);
    
    public boolean actualizar(Usuario usuario);

    public boolean eliminar(int id);

    public Usuario obtenerPorId(int id);
    
}
