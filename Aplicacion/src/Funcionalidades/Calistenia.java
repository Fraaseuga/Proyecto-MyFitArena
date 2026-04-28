package Funcionalidades;

public class Calistenia extends Entrenamiento{
	private double lastre;

	// Contructor
	public Calistenia(String dificultad, int duracion, double lastre){
		super(dificultad,duracion);
		this.lastre = lastre;
	}
	
	// Getters - Setters
	public double getLastre() {
		return lastre;
	}

	public void setLastre(double lastre) {
		this.lastre = lastre;
	}
	
	// Método para comprobar si está usando lastre
	public boolean usaLastre() {
		if(lastre > 0) {
			return true;
		}
		return false;
	}
	
	// Método para calcular las calorias
	@Override
	public int calcularCalorias(int edad, double pesoUsuario, double alturaUsuario, boolean esHombre) {
	    
	    // MET (Metabolic Equivalent of Task) para ejercicios calisténicos según el Compendium of Physical Activities
	    double metabolicEquivalent;
	    switch ((int)lastre) {
	        case 0: metabolicEquivalent = 3.5; break;   // Calistenia ligera (peso corporal)
	        case 1: metabolicEquivalent = 4.5; break;   // Calistenia moderada con leve resistencia
	        case 2: metabolicEquivalent = 6.0; break;   // Calistenia intensa con carga moderada
	        case 3: metabolicEquivalent = 8.0; break;   // Calistenia vigorosa con lastre elevado
	        default: metabolicEquivalent = 3.5;         // Valor por defecto
	    }

	    // Duración expresada en horas (base fisiológica: kcal = MET × kg × horas)
	    double durationHours = getDuracion() / 60.0;

	    // Gasto energético según la ecuación estándar de equivalentes metabólicos (ACSM)
	    double baseCalories = metabolicEquivalent * pesoUsuario * durationHours;

	    // BMR (Basal Metabolic Rate) según la ecuación de Mifflin–St Jeor (1990)
	    double basalMetabolicRate;
	    if (esHombre) {
	        basalMetabolicRate = 10 * pesoUsuario + 6.25 * alturaUsuario - 5 * edad + 5;
	    } else {
	        basalMetabolicRate = 10 * pesoUsuario + 6.25 * alturaUsuario - 5 * edad - 161;
	    }

	    // Factor de ajuste metabólico relativo al BMR para personalizar el gasto energético
	    double metabolicAdjustmentFactor = basalMetabolicRate / 1500.0;

	    // Gasto calórico total estimado combinando intensidad (MET) y metabolismo individual (BMR)
	    double totalCaloriesBurned = baseCalories * metabolicAdjustmentFactor;

	    return (int) totalCaloriesBurned;
	}

}
