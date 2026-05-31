package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Funcionalidades.Usuario;

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
	
	// Este método devuelve todos los miembros del club menos el propietario
	public static ArrayList<String[]> getMiembrosClub(String codClub){

	    ArrayList<String[]> lista = new ArrayList<>();

	    try(
	        Connection con = Conexion.getConexion();

	        // 1. Obtener el propietario del club
	        PreparedStatement psProp = con.prepareStatement(
	            "SELECT dni_propietario FROM club WHERE codclub = ?"
	        );

	        // 2. Obtener los miembros del club excepto el propietario
	        PreparedStatement ps = con.prepareStatement(
	            "SELECT dni, nombre, apellidos, telefono, correo_electronico " +
	            "FROM usuario " +
	            "WHERE cod_club = ? AND dni <> ?"
	        );
	    ){

	        // Obtener propietario
	        psProp.setString(1, codClub);
	        ResultSet rsProp = psProp.executeQuery();

	        String propietario = null;
	        if (rsProp.next()) {
	            propietario = rsProp.getString(1);
	        }

	        // Obtener miembros excluyendo al propietario
	        ps.setString(1, codClub);
	        ps.setString(2, propietario);

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
	
	// Este método se va a utilizar para comprobar si un usuario ya pertenece a un club
	public static boolean perteneceAClub(String dni) {
		try (
		        Connection con = Conexion.getConexion();
		        PreparedStatement ps = con.prepareStatement("SELECT cod_club FROM usuario WHERE dni = ?");
		    ) {
			ps.setString(1, dni);

		    ResultSet rs = ps.executeQuery();
		        
		    if (rs.next()) {
		       	String valor = rs.getString("cod_club");
		       	if(valor != null) {
		       		return true; // Si que pertence a un club
		       	}
		        return false; // No pertence a un club
		    }
		    return true; // En caso de que no exista el usuario
		} catch (SQLException er) {
		        er.printStackTrace();
		        return false;
		}
	}

	// Este método actualiza el cod_club de un usuario al valor recibido
	public static boolean actualizarClubUsuario(String dni, String nuevoCodClub) {
	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement("UPDATE usuario SET cod_club = ? WHERE dni = ?")
	    ) {

	        ps.setString(1, nuevoCodClub);
	        ps.setString(2, dni);

	        int filas = ps.executeUpdate();

	        return filas > 0; // true si se actualizó, false si no existe el usuario

	    } catch (SQLException er) {
	        er.printStackTrace();
	        return false;
	    }
	}

	// Este método devolverá un Usuario según el DNI que reciba el método
	public static Usuario getUsuarioPorDni(String dni) {

	    String sql = "SELECT dni, nombre, apellidos, telefono, correo_electronico, contrasena " +
	                 "FROM usuario WHERE dni = ?";

	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {

	                // Campos que sí existen en la BBDD
	                String dniBD = rs.getString("dni");
	                String nombre = rs.getString("nombre");
	                String apellidos = rs.getString("apellidos");
	                String contrasena = rs.getString("contrasena");
	                int telefono = 0;
	                try {
	                    telefono = Integer.parseInt(rs.getString("telefono"));
	                } catch (Exception e) {
	                    telefono = 0;
	                }
	                String correo = rs.getString("correo_electronico");

	                // Crear el objeto Usuario
	                return new Usuario(
	                    dniBD,
	                    nombre,
	                    apellidos,
	                    0,
	                    true,
	                    "",
	                    0.0,
	                    0.0,
	                    telefono,
	                    correo,
	                    contrasena
	                );
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null; // No existe el usuario o error
	}

	// Este método modificará los datos de un usuario por los que reciba
	public static boolean actualizarUsuario(
	        String dni,
	        String nombre,
	        String apellidos,
	        String telefono,
	        String correoElectronico,
	        String contrasenaNueva
	) {

	    String sql = "UPDATE usuario SET nombre = ?, apellidos = ?, telefono = ?, correo_electronico = ?, contrasena = ? " +
	                 "WHERE dni = ?";

	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {

	        ps.setString(1, nombre);
	        ps.setString(2, apellidos);
	        ps.setString(3, telefono);
	        ps.setString(4, correoElectronico);
	        ps.setString(5, contrasenaNueva);
	        ps.setString(6, dni);

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	// Este método eliminará un usuario según el dni que reciba
	public static boolean eliminarUsuario(String dni) {

	    String sqlRecomendaciones = 
	        "DELETE FROM recomendaciones " +
	        "WHERE dni_usuario IN (SELECT dni_usuario FROM realizar WHERE dni_usuario = ?)";

	    String sqlParticipar = "DELETE FROM participar WHERE dni_usuario = ?";
	    String sqlAmigos1 = "DELETE FROM tener_amigos WHERE amigo1 = ?";
	    String sqlAmigos2 = "DELETE FROM tener_amigos WHERE amigo2 = ?";
	    String sqlObtener = "DELETE FROM obtener WHERE dni_usuario = ?";
	    String sqlProgreso = "DELETE FROM progreso WHERE dni_usuario = ?";
	    String sqlRealizar = "DELETE FROM realizar WHERE dni_usuario = ?";
	    String sqlClubPropietario = 
	        "UPDATE club SET dni_propietario = NULL WHERE dni_propietario = ?";
	    String sqlUsuario = "DELETE FROM usuario WHERE dni = ?";

	    try (Connection con = Conexion.getConexion()) {

	        // 1. Borrar recomendaciones (depende de REALIZAR)
	        try (PreparedStatement ps = con.prepareStatement(sqlRecomendaciones)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 2. Borrar participaciones en eventos
	        try (PreparedStatement ps = con.prepareStatement(sqlParticipar)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 3. Borrar amistades
	        try (PreparedStatement ps = con.prepareStatement(sqlAmigos1)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }
	        try (PreparedStatement ps = con.prepareStatement(sqlAmigos2)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 4. Borrar logros obtenidos
	        try (PreparedStatement ps = con.prepareStatement(sqlObtener)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 5. Borrar progreso
	        try (PreparedStatement ps = con.prepareStatement(sqlProgreso)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 6. Borrar entrenamientos realizados (REALIZAR)
	        try (PreparedStatement ps = con.prepareStatement(sqlRealizar)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 7. Si es propietario de un club → dejarlo sin propietario
	        try (PreparedStatement ps = con.prepareStatement(sqlClubPropietario)) {
	            ps.setString(1, dni);
	            ps.executeUpdate();
	        }

	        // 8. Finalmente borrar el usuario
	        try (PreparedStatement ps = con.prepareStatement(sqlUsuario)) {
	            ps.setString(1, dni);
	            int filas = ps.executeUpdate();
	            return filas > 0;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	// Este método comprobará si un usuario es dueño de un club
	public static boolean esDuenoDeClub(String dni) {

	    String sql = "SELECT 1 FROM club WHERE dni_propietario = ? LIMIT 1";

	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {

	        ps.setString(1, dni);

	        try (ResultSet rs = ps.executeQuery()) {
	            return rs.next();   // true si existe un club con ese propietario
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}