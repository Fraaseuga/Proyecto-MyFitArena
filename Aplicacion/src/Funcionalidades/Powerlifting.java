package Funcionalidades;

public class Powerlifting extends Entrenamiento{
	private double pesoMaximo;
	private String nombre;
	private String repeticiones;
	private String equipamiento;
	private String descripcion;
	
	// Constructor
	public Powerlifting(String dificultad, int duracion, double pesoMaximo, String nombre, String repeticiones, String equipamiento, String descripcion){
		super(dificultad,duracion);
		this.pesoMaximo = pesoMaximo;
		this.nombre = nombre;
		this.repeticiones = repeticiones;
		this.equipamiento = equipamiento;
		this.descripcion = descripcion;
	}

	// Getters - Setters
	public double getPesoMaximo() {
		return pesoMaximo;
	}

	public void setPesoMaximo(double pesoMaximo) {
		this.pesoMaximo = pesoMaximo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(String repeticiones) {
		this.repeticiones = repeticiones;
	}

	public String getEquipamiento() {
		return equipamiento;
	}

	public void setEquipamiento(String equipamiento) {
		this.equipamiento = equipamiento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	// Comprobar si ha superado si pesoMaximo antiguo
	public boolean esRecord(double antiguo) {
		if(pesoMaximo > antiguo) {
			return true;	
		}
		return false;
	}	
	
	// Método para calcular las calorias
	@Override
	public int calcularCalorias(int edad, double pesoUsuario, double alturaUsuario, boolean esHombre) {
	    
	    // MET (Metabolic Equivalent of Task) para entrenamiento de fuerza máxima
	    // Basado en el Compendium of Physical Activities: mayor carga absoluta = mayor coste metabólico
	    double metabolicEquivalent;
	    if (pesoMaximo <= 60) metabolicEquivalent = 3.0;          // Fuerza ligera
	    else if (pesoMaximo <= 100) metabolicEquivalent = 3.5;    // Fuerza moderada
	    else if (pesoMaximo <= 140) metabolicEquivalent = 6.0;    // Fuerza intensa
	    else metabolicEquivalent = 8.0;                           // Powerlifting pesado (alta intensidad neuromuscular)

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
