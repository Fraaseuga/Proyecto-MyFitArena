package Funcionalidades;
import java.util.ArrayList;

public class Club {

	String nombre;
	String descripcion;
	String fechaCreacion;
	
	ArrayList<Usuario> miembros = new ArrayList<>();
	ArrayList<Anuncio> anuncios = new ArrayList<>();
	
	public Club() {
		
	}
	
	public Club(String nombre, String descripcion, String fechaCreacion) {
		
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		
	}
	
	// Metodo para añadir miembros a un club
	
	public void addMiembro(Usuario usuario) {
		
		miembros.add(usuario);
	}
	
	// Metodo para quitar miembros de un club
	
	public void removeMiembro(Usuario usuario) {
		miembros.remove(usuario);
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

	public String getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public ArrayList<Usuario> getMiembros() {
		return miembros;
	}

	public void setMiembros(ArrayList<Usuario> miembros) {
		this.miembros = miembros;
	}

	public ArrayList<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(ArrayList<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}
}
