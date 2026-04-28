package Funcionalidades;

public class Ejercicio {
	private String nombreEjercicio;
	private int series,repeticiones;
	
	// Getters - Setters
	public String getNombreEjercicio() {
		return nombreEjercicio;
	}
	public void setNombreEjercicio(String nombreEjercicio) {
		this.nombreEjercicio = nombreEjercicio;
	}
	public int getSeries() {
		return series;
	}
	public void setSeries(int series) {
		this.series = series;
	}
	public int getRepeticiones() {
		return repeticiones;
	}
	public void setRepeticiones(int repeticiones) {
		this.repeticiones = repeticiones;
	}
	
	public String toString() {
		return nombreEjercicio+" - "+repeticiones+" X "+series;
	}
}
