package Funcionalidades;

public class PlanAlimenticio {

	int caloriasTotales;
	double proteinas;
	double grasas;
	double carbohidratos;
	String objetivo;
	
	public PlanAlimenticio() {
		
	}
	
	public PlanAlimenticio(int cTotales, double proteinas, double grasas, double carbohidratos, String objetivo) {
		
		this.caloriasTotales = cTotales;
		this.proteinas = proteinas;
		this.grasas = grasas;
		this.carbohidratos = carbohidratos;
		this.objetivo = objetivo;
		
	}


	public int getCaloriasTotales() {
		return caloriasTotales;
	}


	public void setCaloriasTotales(int caloriasTotales) {
		this.caloriasTotales = caloriasTotales;
	}


	public double getProteinas() {
		return proteinas;
	}


	public void setProteinas(double proteinas) {
		this.proteinas = proteinas;
	}


	public double getGrasas() {
		return grasas;
	}


	public void setGrasas(double grasas) {
		this.grasas = grasas;
	}


	public double getCarbohidratos() {
		return carbohidratos;
	}


	public void setCarbohidratos(double carbohidratos) {
		this.carbohidratos = carbohidratos;
	}


	public String getObjetivo() {
		return objetivo;
	}


	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	
	@Override
	public String toString() {
		return "Calorias totales: " + getCaloriasTotales() + "\n" +
				"Proteinas: " + getProteinas() + "\n" +
				"Grasas: " + getGrasas() + "\n" +
				"Carbohidratos: " + getCarbohidratos() + "\n" +
				"Objetivo: " + getObjetivo();
	}
	
}
