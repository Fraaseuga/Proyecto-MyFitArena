package Funcionalidades;

public class Usuario {

	private String dni;
	private String nombre;
	private String apellidos;
	private int edad;
	private boolean esHombre;
	private String poblacion;
	private double peso;
	private double estatura;
	private int telefono;
	private String correoElectronico;
	private String contrasena;
	
	public Usuario() {
		
	}
	
	public Usuario(String dni, String nombre, String apellidos, int edad, boolean esHombre, String poblacion, double peso, double estatura, int telefono, String correoElectronico, String contrasena) {
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.edad = edad;
		this.esHombre = esHombre;
		this.poblacion = poblacion;
		this.peso = peso;
		this.estatura = estatura;
		this.telefono = telefono;
		this.correoElectronico = correoElectronico;
		this.contrasena = contrasena;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public boolean isEsHombre() {
		return esHombre;
	}

	public void setEsHombre(boolean esHombre) {
		this.esHombre = esHombre;
	}
	
	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getEstatura() {
		return estatura;
	}

	public void setEstatura(double estatura) {
		this.estatura = estatura;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	@Override
	public String toString() {
		
		return "DNI: " + getDni() + "\n" +
				"Nombre: " + getNombre() + "\n" +
				"Apellidos: " + getApellidos() + "\n" +
				"Edad: " + getEdad() + "\n" +
				"Sexo: " + isEsHombre() + "\n" +
				"Poblacion: " + getPoblacion() + "\n" +
				"Peso: " + getPeso() + "\n" +
				"Estatura: " + getEstatura() + "\n" +
				"Telefono: " + getTelefono() + "\n" +
				"Correo electronico: " + getCorreoElectronico();
	}
}
