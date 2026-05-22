package Funcionalidades;

public class Hipertrofia extends Entrenamiento {
	private double pesoMedio;
	private String nombre;
	private String musculoPrincipal;
	private String repeticiones;
	private String descripcion;
	
	// Constructor
	public Hipertrofia(String dificultad, int duracion, double pesoMedio, String nombre, String musculoPrincipal, String repeticiones, String descripcion){
		super(dificultad,duracion);
		this.pesoMedio = pesoMedio;
		this.nombre = nombre;
		this.musculoPrincipal = musculoPrincipal;
		this.repeticiones = repeticiones;
		this.descripcion = descripcion;
	}

	// Getters - Setters
	public double getPesoMedio() {
		return pesoMedio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMusculoPrincipal() {
		return musculoPrincipal;
	}

	public void setMusculoPrincipal(String musculoPrincipal) {
		this.musculoPrincipal = musculoPrincipal;
	}

	public String getRepeticiones() {
		return repeticiones;
	}

	public void setRepeticiones(String repeticiones) {
		this.repeticiones = repeticiones;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setPesoMedio(double pesoMedio) {
		this.pesoMedio = pesoMedio;
	}
	
	// Método para calcular el volumen del peso que levanta
	public double calcularVolumen() {
		return 0;
	}
	
	// Método para calcular las calorias
	@Override
	public int calcularCalorias(int edad, double pesoUsuario, double alturaUsuario, boolean esHombre) {
	    
	    //  MET (Metabolic Equivalent of Task) para entrenamiento de fuerza según carga movilizada
	    // Basado en el Compendium of Physical Activities: mayor carga = mayor coste metabólico
	    double metabolicEquivalent;
	    if (pesoMedio <= 20) metabolicEquivalent = 3.0;          // Fuerza ligera
	    else if (pesoMedio <= 40) metabolicEquivalent = 3.5;     // Fuerza moderada
	    else if (pesoMedio <= 60) metabolicEquivalent = 6.0;     // Fuerza intensa
	    else metabolicEquivalent = 8.0;                          // Hipertrofia pesada / alta carga

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
