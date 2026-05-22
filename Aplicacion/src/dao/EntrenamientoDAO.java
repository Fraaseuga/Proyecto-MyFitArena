package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Funcionalidades.Calistenia;
import Funcionalidades.Cardio;
import Funcionalidades.Club;
import Funcionalidades.Elasticidad;
import Funcionalidades.Hipertrofia;
import Funcionalidades.Powerlifting;

public class EntrenamientoDAO {
	// Este método devolverá un ArrayList con todos los entrenamientos de calistenia
	public static ArrayList<Calistenia> getEntrenamientoCalistenia(){
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT "
										+ "tipo_ejercicio,dificultad,lastre_kg,enfoque_habilidad,descripcion "
										+ "FROM calistenia");
				ResultSet rs = ps.executeQuery();
		){
			ArrayList<Calistenia> lista = new ArrayList<Calistenia>();
			while(rs.next()) {
				Calistenia c = new Calistenia(
						rs.getString("dificultad"),
						0,
						rs.getDouble("lastre_kg"),
						rs.getString("tipo_ejercicio"),
						rs.getString("enfoque_habilidad"),
						rs.getString("descripcion"));

				lista.add(c);				
			}
			return lista;
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return null;
	}

	// Este método devolverá un ArrayList con todos los entrenamientos de hipertrofia 
	public static ArrayList<Hipertrofia> getEntrenamientoHipertrofia(){
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT "
										+ "tecnica,dificultad,grupo_muscular_principal,rango_repeticiones,descripcion "
										+ "FROM hipertrofia");
				ResultSet rs = ps.executeQuery();
		){
			ArrayList<Hipertrofia> lista = new ArrayList<Hipertrofia>();
			while(rs.next()) {
				Hipertrofia c = new Hipertrofia(
						rs.getString("dificultad"),
						0,
						0,
						rs.getString("tecnica"),
						rs.getString("grupo_muscular_principal"),
						rs.getString("rango_repeticiones"),
						rs.getString("descripcion"));

				lista.add(c);				
			}
			return lista;
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return null;
	}
	
	// Este método devolverá un ArrayList con todos los entrenamientos de powerlifting
	public static ArrayList<Powerlifting> getEntrenamientoPowerlifting(){
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT "
										+ "levantamiento_principal,dificultad,peso_maximo_kg,repeticiones_objetivo,tipo_equipamiento,descripcion "
										+ "FROM powerlifting");
				ResultSet rs = ps.executeQuery();
		){
			ArrayList<Powerlifting> lista = new ArrayList<Powerlifting>();
			while(rs.next()) {
				Powerlifting c = new Powerlifting(
						rs.getString("dificultad"),
						0,
						rs.getDouble("peso_maximo_kg"),
						rs.getString("levantamiento_principal"),
						rs.getString("repeticiones_objetivo"),
						rs.getString("tipo_equipamiento"),
						rs.getString("descripcion"));

				lista.add(c);				
			}
			return lista;
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return null;
	}
	
	// Este método devolverá un ArrayList con todos los entrenamientos de cardio
	public static ArrayList<Cardio> getEntrenamientoCardio(){
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT "
										+ "tipo_cardio,dificultad,tipo_sesion,calorias_quemadas,descripcion "
										+ "FROM cardio");
				ResultSet rs = ps.executeQuery();
		){
			ArrayList<Cardio> lista = new ArrayList<Cardio>();
			while(rs.next()) {
				Cardio c = new Cardio(
						rs.getString("dificultad"),
						0,
						0,
						rs.getString("tipo_cardio"),
						rs.getString("tipo_sesion"),
						rs.getInt("calorias_quemadas"),
						rs.getString("descripcion"));

				lista.add(c);				
			}
			return lista;
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return null;
	}
	
	// Este método devolverá un ArrayList con todos los entrenamientos de elasticidad
	public static ArrayList<Elasticidad> getEntrenamientoElasticidad(){
		try(
				Connection con = Conexion.getConexion();
				PreparedStatement ps = con.prepareStatement("SELECT "
										+ "tipo_estiramiento,dificultad,zona_corporal_principal,objetivo,descripcion "
										+ "FROM elasticidad");
				ResultSet rs = ps.executeQuery();
		){
			ArrayList<Elasticidad> lista = new ArrayList<Elasticidad>();
			while(rs.next()) {
				Elasticidad c = new Elasticidad(
						rs.getString("dificultad"),
						0,
						0,
						rs.getString("tipo_estiramiento"),
						rs.getString("zona_corporal_principal"),
						rs.getString("objetivo"),
						rs.getString("descripcion"));

				lista.add(c);				
			}
			return lista;
		}catch(SQLException er) {
			System.out.println("Hay un error en la consulta");
		}
		return null;
	}
}
