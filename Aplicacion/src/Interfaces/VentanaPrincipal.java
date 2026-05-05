package Interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

// PALETA DE COLORES
//	  --negro: rgb(0, 0, 0);
	public Color negro = new Color(0,0,0);
//	  --gris-claro: rgb(77, 77, 77);
	public Color grisClaro = new Color(77,77,77);
//	  --gris-medio: rgb(51, 51, 51); 
	public Color grisMedio = new Color(51,51,51);
//	  --gris-oscuro: rgb(31, 31, 31);
	public Color grisOscuro = new Color(31,31,31);
//	  --gris-muy-oscuro: rgb(22, 22, 22);
	public Color grisMuyOscuro = new Color(22,22,22);
//	  --amarillo-vivo: rgb(255, 215, 0);
	public Color amarilloVivo = new Color(255,215,0);
//	  --amarillo-suave: rgb(255, 241, 118);
	public Color amarilloSuave = new Color(255,241,118);
//	  --amarillo-texto: rgb(255, 250, 201);
	public Color amarilloTexto = new Color(255,250,201);
	
	public static void main(String[] args) {
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
	}

	
	public VentanaPrincipal() {
		setForeground(new Color(64, 0, 64));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 743);
		// Hace que la ventana aparezca en medio de la pantalla
		setLocationRelativeTo(null);

		// PANEL PRINCIPAL
		contentPane = new JPanel();
		contentPane.setBackground(negro);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		// BARRA DE MENÚ
		JMenuBar barraMenu = new JMenuBar();
		barraMenu.setBorderPainted(false);
		barraMenu.setBackground(grisOscuro);
		setJMenuBar(barraMenu);
		
		JMenu mnConfiguracion = new JMenu("Configuración");
		mnConfiguracion.setFont(new Font("Rockwell", Font.PLAIN, 25));
		mnConfiguracion.setForeground(amarilloSuave);
		mnConfiguracion.setBackground(grisMedio);
		barraMenu.add(mnConfiguracion);
		
		boolean tieneCuenta = false;
		
		JMenuItem mniIniciarSesion = new JMenuItem("Iniciar sesión");
		mniIniciarSesion.setFont(new Font("Rockwell", Font.PLAIN, 16));
		mniIniciarSesion.setForeground(amarilloSuave);
		mniIniciarSesion.setBackground(grisMedio);
		mnConfiguracion.add(mniIniciarSesion);
		
		JMenuItem mniRegistrarse = new JMenuItem("Registrarse");
		mniRegistrarse.setFont(new Font("Rockwell", Font.PLAIN, 16));
		mniRegistrarse.setForeground(amarilloSuave);
		mniRegistrarse.setBackground(grisMedio);
		mnConfiguracion.add(mniRegistrarse);
			
			// TANTO 'Cuenta' Y 'Cerrar sesión' APARECERÁN CUANDO SE TENGA LA CUENTA INICIADA
		JMenuItem mniCuenta = new JMenuItem("Cuenta");
		mniCuenta.setFont(new Font("Rockwell", Font.PLAIN, 16));
		mniCuenta.setForeground(amarilloSuave);
		mniCuenta.setBackground(grisMedio);
//		mnConfiguracion.add(mniCuenta);
		
		JMenuItem mniCerrarSesion = new JMenuItem("Cerrar sesión");
		mniCerrarSesion.setFont(new Font("Rockwell", Font.PLAIN, 16));
		mniCerrarSesion.setForeground(amarilloSuave);
		mniCerrarSesion.setBackground(grisMedio);
//		mnConfiguracion.add(mniCerrarSesion);
		
		// SECCIÓN PRINCIPAL
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Interfaces/logoPequeño.png")));
		lblLogo.setBounds(21, 25, 173, 166);
		contentPane.add(lblLogo);
			
			// LÍNEA PARA UTILIZAR COMO BORDE DE LOS BLOQUES
		JSeparator separator = new JSeparator();
		separator.setBackground(amarilloVivo);
		separator.setBounds(213, 641, 479, 155);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(255, 215, 0));
		separator_1.setBounds(213, 25, 479, 12);
		contentPane.add(separator_1);
		
		// 
		JList<String> list = new JList<String>();
		list.setBackground(grisMuyOscuro);
		list.setBounds(213, 25, 479, 618);
		contentPane.add(list);
		
		JTextPane nombreAplicacion = new JTextPane();
		nombreAplicacion.setFont(new Font("Arial Black", Font.PLAIN, 30));
		nombreAplicacion.setForeground(amarilloVivo);
		nombreAplicacion.setBackground(negro);
		nombreAplicacion.setText("My\r\n\r\nFit\r\n\r\nArena");
		nombreAplicacion.setBounds(45, 219, 127, 232);
		contentPane.add(nombreAplicacion);

		
	}
}
