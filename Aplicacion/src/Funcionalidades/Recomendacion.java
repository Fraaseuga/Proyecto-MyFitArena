package Funcionalidades;

public class Recomendacion {

	private String mensaje;
	private int prioridad;
			
	public Recomendacion() {
		
	}
	
	public Recomendacion (String mensaje, int prioridad) {
		
		this.mensaje = mensaje;
		this.prioridad = prioridad;
		
	}

	public String getMensaje() {
		return mensaje;
	}
	
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getPrioridad() {
		return prioridad;
	}
	
	public void setPrioridad(int prioridad) {
		this.prioridad = prioridad;
	}
	
	@Override
	public String toString() {
		return "Mensaje: " + getMensaje() + "\n" +
				"Prioridad: " + getPrioridad();
	}
}