package Funcionalidades;

public class Cardio extends Entrenamiento{
	private int ritmo;
	
	// Constructor
	public Cardio(String dificultad, int duracion, int ritmo) {
		super(dificultad,duracion);
		this.ritmo = ritmo;
	}

	// Getters - Setters
	public int getRitmo() {
		return ritmo;
	}

	public void setRitmo(int ritmo) {
		this.ritmo = ritmo;
	}
	
	// Método para calcular las calorias
	@Override
	public int calcularCalorias(int edad, double pesoUsuario, double alturaUsuario,boolean esHombre) {
		// MET (Metabolic Equivalent of Task): coste energético relativo de la actividad física
	    double metabolicEquivalent;
	    if (ritmo <= 6) metabolicEquivalent = 6.0;          // Actividad moderada
	    else if (ritmo <= 8) metabolicEquivalent = 8.3;     // Trote ligero
	    else if (ritmo <= 9) metabolicEquivalent = 9.0;     // Trote moderado
	    else if (ritmo <= 10) metabolicEquivalent = 9.8;    // Carrera moderada
	    else if (ritmo <= 11) metabolicEquivalent = 10.5;   // Carrera intensa
	    else metabolicEquivalent = 11.5;                    // Alta intensidad

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
