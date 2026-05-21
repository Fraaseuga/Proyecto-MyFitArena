package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Funcionalidades.Club;

public class ClubDAO {
	// Este método se utiliza para obtener la cantidad total de clubs de la base de datos
	public static int getCantidadClubs() {
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM club");
				ResultSet rs = ps.executeQuery();
		){
			if(rs.next()) {
				int cantidad = rs.getInt(1);
				return cantidad;
			}
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return 0;
	}
	
	// Este método se utiliza para obtener todos los clubs en un ArrayList
	public static ArrayList<Club> getTodosLosClubs(){
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT nombre,num_miembros,capacidad_miembros,descripcion FROM club");
				ResultSet rs = ps.executeQuery();
		){
			ArrayList<Club> listaClubs = new ArrayList<Club>();
			while(rs.next()) {
				Club c = new Club(
						rs.getString("nombre"),
						rs.getInt("num_miembros"),
						rs.getInt("capacidad_miembros"),
						rs.getString("descripcion"));
				
				listaClubs.add(c);				
			}
			return listaClubs;
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return null;
	}
	
	// Este método se utilizará para poder crear clubs
	public static String crearClub(String nombre, String descripcion, int miembros, int capacidad, String propietario) {

	    try (
	        Connection con = Conexion.getConexion();

	        PreparedStatement psUltimo = con.prepareStatement(
	            "SELECT codclub FROM club ORDER BY codclub DESC LIMIT 1"
	        );

	        ResultSet rs = psUltimo.executeQuery()
	    ) {

	        String ultimo = null;
	        String codClub = "C001";

	        // Aquí se obtiene el código del último club
	        if (rs.next()) {
	            ultimo = rs.getString(1);

	            int numero = 0;

	            // Recorrer carácter a carácter ignorando la 'C'
	            for (int i = 1; i < ultimo.length(); i++) {

	                char c = ultimo.charAt(i);

	                numero = numero * 10 + (c - '0');
	            }

	            numero++;

	            // Creación del código del club
	            codClub = "C";

	            if (numero < 10) {
	                codClub = codClub + "00" + numero;
	            } else if (numero < 100) {
	                codClub = codClub + "0" + numero;
	            } else {
	                codClub = codClub + numero;
	            }
	        }

	        // Inserción del club a la base de datos
	        PreparedStatement psInsert = con.prepareStatement(
	            "INSERT INTO club " +
	            "(codclub, nombre, descripcion, fechaCreacion, num_miembros, capacidad_miembros, dni_propietario) " +
	            "VALUES (?, ?, ?, CURRENT_DATE, ?, ?, ?)"
	        );

	        psInsert.setString(1, codClub);
	        psInsert.setString(2, nombre);
	        psInsert.setString(3, descripcion);
	        psInsert.setInt(4, miembros);
	        psInsert.setInt(5, capacidad);
	        psInsert.setString(6, propietario);

	        psInsert.executeUpdate();

	        return "Club creado correctamente";

	    } catch (Exception e) {
	        e.printStackTrace();
	        return "Error al crear el club";
	    }
	}
}