package Funcionalidades;
import java.util.ArrayList;

public class Club {

	String nombre;
	int miembros;
	int capacidad;
	String descripcion;
	
	ArrayList<Anuncio> anuncios = new ArrayList<>();
	
	public Club() {
		
	}
	
	public Club(String nombre, int miembros, int capacidad, String descripcion) {
		
		this.nombre = nombre;
		this.miembros = miembros;
		this.capacidad = capacidad;
		this.descripcion = descripcion;	
	}
	
	// Metodo para añadir auncio al club
	
	public void addAnuncio(Anuncio anuncio) {
		anuncios.add(anuncio);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getMiembros() {
		return miembros;
	}

	public void setMiembros(int miembros) {
		this.miembros = miembros;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(ArrayList<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
}