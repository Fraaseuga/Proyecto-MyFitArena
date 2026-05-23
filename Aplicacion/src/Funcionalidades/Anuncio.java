package Funcionalidades;

public class Anuncio {

	private String titulo;
	private String contenido;
	private String fechaPublicacion;
	
	public Anuncio() {
		
	}
	
	public Anuncio(String titulo, String contenido, String fechaPublicacion) {
		
		this.titulo = titulo;
		this.contenido = contenido;
		this.fechaPublicacion = fechaPublicacion;
		
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}
	
	@Override
	public String toString() {
		
		return "Titulo: " + getTitulo() + "\n" +
				"Contenido: " + getContenido() + "\n" +
				"Fecha y hora: " + getFechaPublicacion();
	}
	
}


