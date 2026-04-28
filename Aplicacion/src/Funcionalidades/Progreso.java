package Funcionalidades;

public class Progreso {
	private String fechaHora;
	private int nivel;
	
	// Constructores
	public Progreso() {}
	public Progreso(String fechaHora, int nivel) {
		this.fechaHora = fechaHora;
		this.nivel = nivel;
	}
	
	// Getters - Setters
	public String getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	
	// Método para comprobar si ha subido de nivel
	public boolean haSubidoNivel(int nivelAnterior) {
		if(nivel > nivelAnterior) {
			return true;
		}
		return false;
	}
	
	public String toString() {
		return "Nivel: "+nivel+" - Desde: "+fechaHora;
	}
}
