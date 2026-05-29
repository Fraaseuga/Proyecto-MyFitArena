package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TicketsDAO {
	public static int contarTicketsPorAsunto(String asunto) {

	    String sql = "SELECT COUNT(*) AS total FROM tickets WHERE asunto = ?";

	    try (
	        Connection con = Conexion.getConexion();
	        PreparedStatement ps = con.prepareStatement(sql)
	    ) {

	        ps.setString(1, asunto);

	        try (ResultSet rs = ps.executeQuery()) {

	            if (rs.next()) {
	                return rs.getInt("total"); // Devuelve la cantidad de tickets
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return 0; // Si hay error o no hay coincidencias
	}
}