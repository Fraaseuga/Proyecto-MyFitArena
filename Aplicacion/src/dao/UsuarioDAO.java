package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioDAO {
	// Este método se utiliza para obtener la cantidad de usuarios que pertenecen a clubs
	public static int getTodosLosUsuariosConClub() {
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM usuario WHERE cod_club IS NOT NULL ");
				ResultSet rs = ps.executeQuery();
		){
			if(rs.next()) {
				int cantidad = rs.getInt(1);
				return cantidad;
			}
		}catch(SQLException er) {
			System.out.println("Hay un error con la consulta");
		}
		return 0;
	}
	
	// Este método se utiliza para comprobar si un usuario existe
	public static String comprobarSiExiste(String dni, String contrasena) {

	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement(
	            "SELECT dni FROM usuario WHERE dni = ? AND contrasena = ?"
	        )
	    ) {

	        ps.setString(1, dni);
	        ps.setString(2, contrasena);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            return rs.getString("dni");
	        }

	    } catch (SQLException er) {
	        System.out.println("Hay un error con la consulta");
	        er.printStackTrace();
	    }

	    return null;
	}
	
	// Insertar usuario en la base de datos
	public static String crearUsuario(String dni, String nombre, String apellidos, int telefono, String correo, String contrasena) {

	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement(
	            "INSERT INTO usuario (dni, nombre, apellidos, telefono, correo_electronico, contrasena) " +
	            "VALUES (?, ?, ?, ?, ?, ?)"
	        )
	    ) {

	        ps.setString(1, dni);
	        ps.setString(2, nombre);
	        ps.setString(3, apellidos);
	        ps.setInt(4, telefono);
	        ps.setString(5, correo);
	        ps.setString(6, contrasena);

	        ps.executeUpdate();

	        return "Usuario creado correctamente";

	    } catch (SQLException er) {
	        er.printStackTrace();
	        return "Error al crear el usuario";
	    }
	}
	
	public static ArrayList<String[]> getMiembrosClub(String codClub){

	    ArrayList<String[]> lista = new ArrayList<>();

	    try(
	        Connection con = Conexion.getConexion();

	        PreparedStatement ps = con.prepareStatement(
	            "SELECT dni, nombre, apellidos, telefono, correo_electronico " +
	            "FROM usuario " +
	            "WHERE cod_club = ?"
	        );
	    ){

	        ps.setString(1, codClub);

	        ResultSet rs = ps.executeQuery();

	        while(rs.next()){

	            lista.add(new String[] {
	            	rs.getString("dni"),
	                rs.getString("nombre"),
	                rs.getString("apellidos"),
	                rs.getString("telefono"),
	                rs.getString("correo_electronico")
	            });
	        }

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return lista;
	}
	
	public static boolean eliminarMiembroClub(String dni){

	    try(
	        Connection con = Conexion.getConexion();

	        PreparedStatement ps = con.prepareStatement(
	            "UPDATE usuario " +
	            "SET cod_club = NULL " +
	            "WHERE dni = ?"
	        );
	    ){

	        ps.setString(1, dni);

	        return ps.executeUpdate() > 0;

	    }catch(Exception e){
	        e.printStackTrace();
	    }

	    return false;
	}
}