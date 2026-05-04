package Funcionalidades;

import java.util.ArrayList;

public class Evento {

	private String tipo;
	private String descripcion;
	private String ubicacion;
	private String fechaHora;
	
	private ArrayList<Usuario> participantes = new ArrayList<>();
	
	
	public Evento() {
		
	}
	
	public Evento(String tipo, String descripcion, String ubicacion, String fechaHora) {
		
		this.tipo = tipo;
		this.descripcion = descripcion;
		this.ubicacion = ubicacion;
		this.fechaHora = fechaHora;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

	public ArrayList<Usuario> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<Usuario> participantes) {
		this.participantes = participantes;
	}
	
	public void añadirParticipante(Usuario usuario) {
		
		participantes.add(usuario);
	}
	
	public void eliminarParticipantes(Usuario usuario) {
		
		participantes.remove(usuario);
	}
	
	public int getNumParticipantes() {
		
		return participantes.size();
	}
	
	@Override
	public String toString() {
		
		return "Tipo: " + getTipo() + "\n" +
				"Descripcion: " + getDescripcion() + "\n" +
				"Ubicacion: " + getUbicacion() + "\n" +
				"Fecha y hora: " + getFechaHora();
	}
}
