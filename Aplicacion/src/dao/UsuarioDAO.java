package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
	public static int getTodosLosUsuarios() {
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
}