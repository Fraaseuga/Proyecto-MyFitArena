package Funcionalidades;

public class Elasticidad extends Entrenamiento{
	private int nivelElasticidad;
	
	// Constructor
	public Elasticidad(String dificultad, int duracion, int nivelElasticidad){
		super(dificultad,duracion);
		this.nivelElasticidad = nivelElasticidad;
	}

	// Getters - Setters
	public int getNivelElasticidad() {
		return nivelElasticidad;
	}

	public void setNivelElasticidad(int nivelElasticidad) {
		this.nivelElasticidad = nivelElasticidad;
	}
	
	// Método para comprobar si es avanzado según su nivel de elasticidad
	public boolean esAvanzado() {
		if(nivelElasticidad > 7) {
			return true;
		}
		return false;
	}
	
	// Método para calcular las calorias
	@Override
	public int calcularCalorias(int edad, double pesoUsuario, double alturaUsuario, boolean esHombre) {
	    // MET (Metabolic Equivalent of Task) para actividades de flexibilidad según el Compendium of Physical Activities
	    double metabolicEquivalent;
	    switch (nivelElasticidad) {
	        case 1: metabolicEquivalent = 2.3; break;   // Stretching ligero
	        case 2: metabolicEquivalent = 2.8; break;   // Stretching moderado
	        case 3: metabolicEquivalent = 3.3; break;   // Stretching intenso / yoga activo
	        default: metabolicEquivalent = 2.3;         // Valor por defecto
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
