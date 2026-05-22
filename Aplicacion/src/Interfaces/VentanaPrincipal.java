package Interfaces;

import dao.ClubDAO;
import dao.UsuarioDAO;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Funcionalidades.Colores;

import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfPeso;
	private JTextField tfAltura;
	private JTable tablaIMC;
	
	public static String propietario = null;
	
	public static void main(String[] args) {
		VentanaPrincipal frame = new VentanaPrincipal();
		frame.setVisible(true);
	}


	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Interfaces/logo.png")));
		setForeground(new Color(64, 0, 64));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1190, 792);
		// Hace que la ventana aparezca en medio de la pantalla
		setLocationRelativeTo(null);
		
		// PANEL PRINCIPAL
		contentPane = new JPanel();
		contentPane.setBackground(Colores.negro);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);		
		
		// BARRA DE MENÚ
		JMenuBar barraMenu = new JMenuBar();
		barraMenu.setBorder(null);
		barraMenu.setBorderPainted(false);
		barraMenu.setBackground(Colores.grisOscuro);
		setJMenuBar(barraMenu);
		
		JMenu mnConfiguracion = new JMenu("Configuración");
		mnConfiguracion.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		mnConfiguracion.setBorder(null);
		mnConfiguracion.setFont(new Font("Tahoma", Font.PLAIN, 25));
		mnConfiguracion.setForeground(Colores.amarilloSuave);
		mnConfiguracion.setBackground(Colores.grisMedio);
		barraMenu.add(mnConfiguracion);
		
		// Si no hay cuenta iniciada saldrán los menus Iniciar sesión y Registrarse
		if(propietario == null) {
			JMenuItem mniIniciarSesion = new JMenuItem("Iniciar sesión");
			mniIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaIniciarSesion vis = new VentanaIniciarSesion();
					vis.setVisible(true);
					VentanaPrincipal.this.setVisible(false);
				}
			});
			mniIniciarSesion.setBorder(null);
			mniIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 16));
			mniIniciarSesion.setForeground(Colores.amarilloSuave);
			mniIniciarSesion.setBackground(Colores.grisMedio);
			mnConfiguracion.add(mniIniciarSesion);
			
			JMenuItem mniRegistrarse = new JMenuItem("Registrarse");
			mniRegistrarse.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VentanaRegistrar vr = new VentanaRegistrar();
					vr.setVisible(true);
					VentanaPrincipal.this.setVisible(false);
				}
			});
			mniRegistrarse.setBorder(null);
			mniRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 16));
			mniRegistrarse.setForeground(Colores.amarilloSuave);
			mniRegistrarse.setBackground(Colores.grisMedio);
			mnConfiguracion.add(mniRegistrarse);			
		}else {
			// Tanto 'Cuenta' y 'Cerrar sesión' aparecerán cuando el usuario tenga la cuenta iniciada
			JMenuItem mniCuenta = new JMenuItem("Cuenta");
			mniCuenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			mniCuenta.setFont(new Font("Rockwell", Font.PLAIN, 16));
			mniCuenta.setForeground(Colores.amarilloSuave);
			mniCuenta.setBackground(Colores.grisMedio);
			mnConfiguracion.add(mniCuenta);
			
			JMenuItem mniCerrarSesion = new JMenuItem("Cerrar sesión");
			mniCerrarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int opcion = JOptionPane.showConfirmDialog(contentPane,
							"¿Seguro que quieres cerrar sesión?",
							"Confirmación",
							JOptionPane.YES_NO_OPTION);
					if(opcion == JOptionPane.YES_OPTION) {
						VentanaPrincipal.propietario = null;
						
						VentanaPrincipal.this.dispose();
						VentanaPrincipal nueva = new VentanaPrincipal();
						nueva.setVisible(true);
					}
				}
			});
			mniCerrarSesion.setFont(new Font("Rockwell", Font.PLAIN, 16));
			mniCerrarSesion.setForeground(Colores.amarilloSuave);
			mniCerrarSesion.setBackground(Colores.grisMedio);
			mnConfiguracion.add(mniCerrarSesion);
		}			
		
		String[] columnas = {"IMC","Estado"};
		DefaultTableModel modeloTabla = new DefaultTableModel(null,columnas);
		
		modeloTabla.addRow(new Object[]{"Menor de 18.5", "Bajo peso"});
		modeloTabla.addRow(new Object[]{"18.5 - 24.9", "Peso normal"});
		modeloTabla.addRow(new Object[]{"25.0 - 29.9", "Sobrepeso"});
		modeloTabla.addRow(new Object[]{"30.0 - 34.9", "Obesidad grado I"});
		modeloTabla.addRow(new Object[]{"35.0 - 39.9", "Obesidad grado II"});
		modeloTabla.addRow(new Object[]{"40 o más", "Obesidad grado III"});
		
		JSeparator separator_2_5_2_1_2_2_1 = new JSeparator();
		separator_2_5_2_1_2_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_5_2_1_2_2_1.setBackground(Color.WHITE);
		separator_2_5_2_1_2_2_1.setBounds(1144, 537, 45, 8);
		contentPane.add(separator_2_5_2_1_2_2_1);
		
		JSeparator separator_2_5_2_1_2_2 = new JSeparator();
		separator_2_5_2_1_2_2.setOrientation(SwingConstants.VERTICAL);
		separator_2_5_2_1_2_2.setBackground(Color.WHITE);
		separator_2_5_2_1_2_2.setBounds(1144, 457, 45, 8);
		contentPane.add(separator_2_5_2_1_2_2);
		
		JSeparator separator_2_5_2_1_2_1 = new JSeparator();
		separator_2_5_2_1_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_5_2_1_2_1.setBackground(Color.WHITE);
		separator_2_5_2_1_2_1.setBounds(701, 537, 45, 8);
		contentPane.add(separator_2_5_2_1_2_1);
		
		JSeparator separator_2_5_2_1_2 = new JSeparator();
		separator_2_5_2_1_2.setOrientation(SwingConstants.VERTICAL);
		separator_2_5_2_1_2.setBackground(Color.WHITE);
		separator_2_5_2_1_2.setBounds(701, 457, 45, 8);
		contentPane.add(separator_2_5_2_1_2);
		
		JSeparator separator_2_5_2_1_1_2 = new JSeparator();
		separator_2_5_2_1_1_2.setBackground(Color.WHITE);
		separator_2_5_2_1_1_2.setBounds(1138, 543, 8, 68);
		contentPane.add(separator_2_5_2_1_1_2);
		
		JSeparator separator_2_5_2_1_1_1 = new JSeparator();
		separator_2_5_2_1_1_1.setBackground(Color.WHITE);
		separator_2_5_2_1_1_1.setBounds(701, 543, 8, 68);
		contentPane.add(separator_2_5_2_1_1_1);
		
		JSeparator separator_2_5_2_1_1 = new JSeparator();
		separator_2_5_2_1_1.setBackground(Color.WHITE);
		separator_2_5_2_1_1.setBounds(1138, 457, 8, 68);
		contentPane.add(separator_2_5_2_1_1);
		
		JSeparator separator_2_5_2_1 = new JSeparator();
		separator_2_5_2_1.setBackground(Color.WHITE);
		separator_2_5_2_1.setBounds(701, 457, 8, 68);
		contentPane.add(separator_2_5_2_1);
		
		JLabel lblneteALos = new JLabel("Únete a los Clubs y lleva tu entrenamiento al siguiente nivel.  ");
		lblneteALos.setForeground(new Color(255, 241, 118));
		lblneteALos.setFont(new Font("Tahoma", Font.ITALIC, 14));
		lblneteALos.setBounds(733, 420, 394, 27);
		contentPane.add(lblneteALos);
		
		JTextArea taClubs = new JTextArea();
		taClubs.setWrapStyleWord(true);
		taClubs.setText("Aquí no entrenas solo: compartes objetivos, retos y progresos con personas que buscan lo mismo que tú. Conecta, compite, aprende y mantén la motivación siempre arriba. Cada club es una comunidad activa que te impulsa a mejorar día tras día.");
		taClubs.setLineWrap(true);
		taClubs.setForeground(new Color(255, 250, 201));
		taClubs.setFont(new Font("Tahoma", Font.PLAIN, 13));
		taClubs.setEditable(false);
		taClubs.setBackground(new Color(22, 22, 22));
		taClubs.setBounds(714, 465, 432, 68);
		contentPane.add(taClubs);
		
		int cantidadClubs = ClubDAO.getCantidadClubs();
		JLabel lblClubsTotales = new JLabel(cantidadClubs+"");
		lblClubsTotales.setHorizontalAlignment(SwingConstants.CENTER);
		lblClubsTotales.setForeground(new Color(255, 215, 0));
		lblClubsTotales.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblClubsTotales.setBounds(1002, 621, 123, 41);
		contentPane.add(lblClubsTotales);
		
		int cantidadUsuarios = UsuarioDAO.getTodosLosUsuariosConClub();
		JLabel lblUsuariosTotales = new JLabel(cantidadUsuarios+"");
		lblUsuariosTotales.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuariosTotales.setForeground(new Color(255, 215, 0));
		lblUsuariosTotales.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblUsuariosTotales.setBounds(724, 621, 123, 41);
		contentPane.add(lblUsuariosTotales);
		
		JButton btnVerClubs = new JButton("Ver clubs");
		btnVerClubs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(propietario == null) {
					JOptionPane.showMessageDialog(contentPane, 
							"Primero tienes que tener una cuenta iniciada",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {					
					VentanaClub vc = new VentanaClub(VentanaPrincipal.this);
					vc.setVisible(true);
					VentanaPrincipal.this.setVisible(false);
				}
			}
		});
		btnVerClubs.setForeground(new Color(255, 215, 0));
		btnVerClubs.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVerClubs.setBorder(null);
		btnVerClubs.setBackground(new Color(51, 51, 51));
		btnVerClubs.setBounds(884, 628, 92, 26);
		contentPane.add(btnVerClubs);
		
		JLabel lblUsuariosActivos = new JLabel("Usuarios activos");
		lblUsuariosActivos.setForeground(new Color(255, 241, 118));
		lblUsuariosActivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuariosActivos.setBounds(721, 568, 129, 27);
		contentPane.add(lblUsuariosActivos);
		
		JLabel lblClubsActivos = new JLabel("Clubs activos");
		lblClubsActivos.setForeground(new Color(255, 241, 118));
		lblClubsActivos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClubsActivos.setBounds(1011, 568, 105, 27);
		contentPane.add(lblClubsActivos);
		
		JSeparator separator_2_5_2 = new JSeparator();
		separator_2_5_2.setBackground(Color.WHITE);
		separator_2_5_2.setBounds(721, 393, 83, 68);
		contentPane.add(separator_2_5_2);
		
		JLabel lblClubs = new JLabel("Clubs");
		lblClubs.setForeground(new Color(255, 215, 0));
		lblClubs.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblClubs.setBounds(721, 321, 184, 81);
		contentPane.add(lblClubs);
		
		JSeparator separator_1_1_1_1_1 = new JSeparator();
		separator_1_1_1_1_1.setBackground(new Color(255, 215, 0));
		separator_1_1_1_1_1.setBounds(695, 692, 471, 12);
		contentPane.add(separator_1_1_1_1_1);
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setBackground(new Color(255, 215, 0));
		separator_1_1_1_1.setBounds(695, 321, 471, 12);
		contentPane.add(separator_1_1_1_1);
		
		JLabel lblResultado = new JLabel("Resultado");
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setForeground(Colores.amarilloSuave);
		lblResultado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResultado.setBounds(815, 260, 83, 15);
		contentPane.add(lblResultado);
		
		JButton btnCalcularIMC = new JButton("Calcular");
		btnCalcularIMC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {					
					double peso = Double.parseDouble(tfPeso.getText());
					double altura = Double.parseDouble(tfAltura.getText());;
					double imc = peso / (altura*altura);
					lblResultado.setText(String.format("%.2f", imc));
				}catch(Exception er){
					JOptionPane.showMessageDialog(contentPane, 
							"El peso y la altura deben de ser un número, si es con decimales se deberá escribir con '.'",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfPeso.setText("");
				tfAltura.setText("");
				lblResultado.setText("Resultado");
			}
		});
		btnReiniciar.setForeground(new Color(255, 215, 0));
		btnReiniciar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnReiniciar.setBorder(null);
		btnReiniciar.setBackground(new Color(51, 51, 51));
		btnReiniciar.setBounds(819, 207, 92, 26);
		contentPane.add(btnReiniciar);
		
		btnCalcularIMC.setForeground(new Color(255, 215, 0));
		btnCalcularIMC.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCalcularIMC.setBorder(null);
		btnCalcularIMC.setBackground(new Color(51, 51, 51));
		btnCalcularIMC.setBounds(709, 207, 92, 26);
		contentPane.add(btnCalcularIMC);
		
		JSeparator separator_2_5_1 = new JSeparator();
		separator_2_5_1.setBackground(Color.WHITE);
		separator_2_5_1.setBounds(825, 285, 62, 68);
		contentPane.add(separator_2_5_1);
		
		tablaIMC = new JTable(modeloTabla);
		tablaIMC.setEnabled(false);
		tablaIMC.setGridColor(Colores.grisClaro);
		tablaIMC.setRowHeight(30);
		tablaIMC.setForeground(Colores.amarilloSuave);
		tablaIMC.setBackground(Colores.grisOscuro);
		tablaIMC.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablaIMC.setBounds(943, 95, 212, 128);
		
		JScrollPane spTabla = new JScrollPane(tablaIMC);
		spTabla.setBorder(null);
		spTabla.setBackground(new Color(255, 0, 255));
		spTabla.setBounds(921, 77, 231, 203);
		contentPane.add(spTabla);
		
		tfPeso = new JTextField();
		tfPeso.setHorizontalAlignment(SwingConstants.CENTER);
		tfPeso.setCaretColor(new Color(255, 255, 255));
		tfPeso.setSelectionColor(new Color(192, 192, 192));
		tfPeso.setBorder(null);
		tfPeso.setForeground(new Color(255, 255, 255));
		tfPeso.setDisabledTextColor(new Color(255, 255, 0));
		tfPeso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfPeso.setBackground(Colores.grisMedio);
		tfPeso.setSelectedTextColor(Colores.amarilloSuave);
		tfPeso.setBounds(801, 111, 110, 26);
		contentPane.add(tfPeso);
		tfPeso.setColumns(10);
		
		tfAltura = new JTextField();
		tfAltura.setHorizontalAlignment(SwingConstants.CENTER);
		tfAltura.setCaretColor(new Color(255, 255, 255));
		tfAltura.setSelectedTextColor(new Color(255, 255, 0));
		tfAltura.setSelectionColor(new Color(192, 192, 192));
		tfAltura.setBorder(null);
		tfAltura.setForeground(new Color(255, 255, 255));
		tfAltura.setDisabledTextColor(Colores.amarilloSuave);
		tfAltura.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tfAltura.setBackground(Colores.grisMedio);
		tfAltura.setBounds(801, 157, 110, 26);
		contentPane.add(tfAltura);
		tfAltura.setColumns(10);
		
		JLabel lblPeso = new JLabel("Peso (kg)");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPeso.setForeground(Colores.amarilloSuave);
		lblPeso.setBounds(709, 111, 83, 27);
		contentPane.add(lblPeso);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEstado.setForeground(Colores.amarilloSuave);
		lblEstado.setBounds(709, 260, 83, 15);
		contentPane.add(lblEstado);
		
		JLabel lblAltura = new JLabel("Altura (m)");
		lblAltura.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAltura.setForeground(Colores.amarilloSuave);
		lblAltura.setBounds(709, 160, 83, 20);
		contentPane.add(lblAltura);
		
		JSeparator separator_2_5 = new JSeparator();
		separator_2_5.setBackground(Color.WHITE);
		separator_2_5.setBounds(721, 77, 83, 68);
		contentPane.add(separator_2_5);
		
		JLabel lblCalculadoraTitulo = new JLabel("Calculadora IMC");
		lblCalculadoraTitulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblCalculadoraTitulo.setForeground(Colores.amarilloVivo);
		lblCalculadoraTitulo.setBounds(721, 10, 184, 81);
		contentPane.add(lblCalculadoraTitulo);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBackground(new Color(255, 215, 0));
		separator_1_1_1.setBounds(695, 310, 471, 12);
		contentPane.add(separator_1_1_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBackground(new Color(255, 215, 0));
		separator_1_1.setBounds(695, 10, 471, 12);
		contentPane.add(separator_1_1);
		
		JSeparator separator_3_2 = new JSeparator();
		separator_3_2.setBorder(null);
		separator_3_2.setBackground(Color.YELLOW);
		separator_3_2.setBounds(414, 420, 62, 57);
		contentPane.add(separator_3_2);
		
		JSeparator separator_3_3 = new JSeparator();
		separator_3_3.setBorder(null);
		separator_3_3.setBackground(Color.YELLOW);
		separator_3_3.setBounds(414, 532, 62, 47);
		contentPane.add(separator_3_3);
		
		JSeparator separator_3_1 = new JSeparator();
		separator_3_1.setBorder(null);
		separator_3_1.setBackground(Color.YELLOW);
		separator_3_1.setBounds(414, 279, 62, 74);
		contentPane.add(separator_3_1);
		
		JSeparator separator_3_4 = new JSeparator();
		separator_3_4.setBorder(null);
		separator_3_4.setBackground(Color.YELLOW);
		separator_3_4.setBounds(414, 664, 62, 47);
		contentPane.add(separator_3_4);
		
		JButton btnElasticidad = new JButton("Elasticidad");
		btnElasticidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(propietario == null) {
					JOptionPane.showMessageDialog(contentPane, 
							"Primero tienes que tener una cuenta iniciada",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					
				}
			}
		});
		btnElasticidad.setForeground(Colores.amarilloVivo);
		btnElasticidad.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnElasticidad.setBorder(null);
		btnElasticidad.setBackground(new Color(51, 51, 51));
		btnElasticidad.setBounds(399, 628, 92, 26);
		contentPane.add(btnElasticidad);
		
		JButton btnCardio = new JButton("Cardio");
		btnCardio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(propietario == null) {
					JOptionPane.showMessageDialog(contentPane, 
							"Primero tienes que tener una cuenta iniciada",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					
				}
			}
		});
		btnCardio.setForeground(Colores.amarilloVivo);
		btnCardio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCardio.setBorder(null);
		btnCardio.setBackground(new Color(51, 51, 51));
		btnCardio.setBounds(399, 496, 92, 26);
		contentPane.add(btnCardio);
		
		JButton btnHipertrofia = new JButton("Hipertrofia");
		btnHipertrofia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(propietario == null) {
					JOptionPane.showMessageDialog(contentPane, 
							"Primero tienes que tener una cuenta iniciada",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					
				}
			}
		});
		btnHipertrofia.setForeground(Colores.amarilloVivo);
		btnHipertrofia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnHipertrofia.setBorder(null);
		btnHipertrofia.setBackground(new Color(51, 51, 51));
		btnHipertrofia.setBounds(399, 243, 92, 26);
		contentPane.add(btnHipertrofia);
		
		JButton btnPowerlifting = new JButton("Powerlifting");
		btnPowerlifting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(propietario == null) {
					JOptionPane.showMessageDialog(contentPane, 
							"Primero tienes que tener una cuenta iniciada",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					
				}
			}
		});
		btnPowerlifting.setForeground(Colores.amarilloVivo);
		btnPowerlifting.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnPowerlifting.setBorder(null);
		btnPowerlifting.setBackground(new Color(51, 51, 51));
		btnPowerlifting.setBounds(399, 381, 92, 26);
		contentPane.add(btnPowerlifting);
		
		JButton btnCalistenia = new JButton("Calistenia");
		btnCalistenia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(propietario == null) {
					JOptionPane.showMessageDialog(contentPane, 
							"Primero tienes que tener una cuenta iniciada",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}else {
					
				}
			}
		});
		btnCalistenia.setBorder(null);
		btnCalistenia.setBackground(Colores.grisMedio);
		btnCalistenia.setForeground(Colores.amarilloVivo);
		btnCalistenia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnCalistenia.setBounds(399, 111, 92, 26);
		contentPane.add(btnCalistenia);
		
		// Estas líneas se utilizan como decoración
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.YELLOW);
		separator.setBounds(206, 692, 479, 155);
		contentPane.add(separator);
				
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Colores.amarilloVivo);
		separator_1.setBounds(206, 10, 479, 12);
		contentPane.add(separator_1);
		
		JSeparator separator_2_4 = new JSeparator();
		separator_2_4.setOrientation(SwingConstants.VERTICAL);
		separator_2_4.setBackground(Color.WHITE);
		separator_2_4.setBounds(220, 561, 16, 57);
		contentPane.add(separator_2_4);
		
		JSeparator separator_2_2 = new JSeparator();
		separator_2_2.setOrientation(SwingConstants.VERTICAL);
		separator_2_2.setBackground(Color.WHITE);
		separator_2_2.setBounds(220, 297, 16, 74);
		contentPane.add(separator_2_2);
		
		JSeparator separator_2_1 = new JSeparator();
		separator_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_2_1.setBackground(Color.WHITE);
		separator_2_1.setBounds(220, 165, 16, 68);
		contentPane.add(separator_2_1);
		
		JSeparator separator_2_3 = new JSeparator();
		separator_2_3.setOrientation(SwingConstants.VERTICAL);
		separator_2_3.setBackground(Color.WHITE);
		separator_2_3.setBounds(220, 429, 16, 57);
		contentPane.add(separator_2_3);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBackground(new Color(255, 255, 255));
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(220, 33, 16, 68);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBorder(null);
		separator_3.setBackground(Color.YELLOW);
		separator_3.setBounds(414, 147, 62, 74);
		contentPane.add(separator_3);
		
//		mnConfiguracion.add(mniCerrarSesion);
		
		// SECCIÓN PRINCIPAL (Entrenamientos)
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Interfaces/logo.png")));
		lblLogo.setBounds(21, 25, 173, 166);
		contentPane.add(lblLogo);
		
		// Explicaciones de cada entrenamiento junto a su botón para acceder a su entrenamiento
		JTextArea taCardio = new JTextArea();
		taCardio.setEditable(false);
		taCardio.setForeground(Colores.amarilloTexto);
		taCardio.setBackground(Colores.grisMuyOscuro);
		taCardio.setWrapStyleWord(true);
		taCardio.setFont(new Font("Tahoma", Font.PLAIN, 13));
		taCardio.setText("Activa tu cuerpo, mejora tu resistencia y siente cómo tu energía sube de nivel. Correr, nadar, pedalear… el cardio impulsa tu salud y te hace rendir mejor en cualquier disciplina.");
		taCardio.setLineWrap(true);
		taCardio.setBounds(230, 429, 432, 57);
		contentPane.add(taCardio);
		
		JTextArea taCalistenia = new JTextArea();
		taCalistenia.setEditable(false);
		taCalistenia.setForeground(Colores.amarilloTexto);
		taCalistenia.setBackground(Colores.grisMuyOscuro);
		taCalistenia.setWrapStyleWord(true);
		taCalistenia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		taCalistenia.setLineWrap(true);
		taCalistenia.setText("Entrena en cualquier lugar y sin excusas. La calistenia convierte tu cuerpo en la mejor herramienta para ganar fuerza, control y movilidad. Progresiones claras, resultados reales y cero equipamiento. Libertad total para entrenar donde quieras.");
		taCalistenia.setBounds(230, 33, 432, 68);
		contentPane.add(taCalistenia);
		
		JTextArea taPowerlifting = new JTextArea();
		taPowerlifting.setEditable(false);
		taPowerlifting.setForeground(Colores.amarilloTexto);
		taPowerlifting.setBackground(Colores.grisMuyOscuro);
		taPowerlifting.setWrapStyleWord(true);
		taPowerlifting.setFont(new Font("Tahoma", Font.PLAIN, 13));
		taPowerlifting.setText("El entrenamiento para quienes quieren levantar más, mucho más. Sentadilla, press de banca y peso muerto como protagonistas de un sistema pensado para aumentar tu fuerza máxima y superar tus propios récords.");
		taPowerlifting.setLineWrap(true);
		taPowerlifting.setBounds(230, 297, 421, 74);
		contentPane.add(taPowerlifting);
		
		JTextArea taHipertrofia = new JTextArea();
		taHipertrofia.setEditable(false);
		taHipertrofia.setForeground(Colores.amarilloTexto);
		taHipertrofia.setBackground(Colores.grisMuyOscuro);
		taHipertrofia.setWrapStyleWord(true);
		taHipertrofia.setFont(new Font("Tahoma", Font.PLAIN, 13));
		taHipertrofia.setLineWrap(true);
		taHipertrofia.setText("Si tu objetivo es crecer, este es tu camino. Entrenamientos diseñados para maximizar el volumen muscular con cargas desafiantes, técnica precisa y un estímulo que realmente se nota. Construye un físico más grande, fuerte y definido.");
		taHipertrofia.setBounds(230, 165, 432, 68);
		contentPane.add(taHipertrofia);
		
		JTextArea taElasticidad = new JTextArea();
		taElasticidad.setEditable(false);
		taElasticidad.setForeground(Colores.amarilloTexto);
		taElasticidad.setBackground(Colores.grisMuyOscuro);
		taElasticidad.setWrapStyleWord(true);
		taElasticidad.setFont(new Font("Tahoma", Font.PLAIN, 13));
		taElasticidad.setText("Un cuerpo flexible es un cuerpo que se mueve mejor. Mejora tu rango de movimiento, reduce tensiones y gana fluidez con estiramientos, movilidad y técnicas inspiradas en yoga. Ligereza y bienestar en cada sesión.");
		taElasticidad.setLineWrap(true);
		taElasticidad.setBounds(230, 561, 432, 57);
		contentPane.add(taElasticidad);
		
		// Fondo del bloque principal
		JList<String> list = new JList<String>();
		list.setBackground(Colores.grisMuyOscuro);
		list.setBounds(206, 10, 479, 683);
		contentPane.add(list);
		
		JList<String> list_1 = new JList<String>();
		list_1.setBackground(new Color(22, 22, 22));
		list_1.setBounds(695, 8, 471, 303);
		contentPane.add(list_1);
		
		JList<String> list_1_1 = new JList<String>();
		list_1_1.setBackground(new Color(22, 22, 22));
		list_1_1.setBounds(695, 321, 471, 372);
		contentPane.add(list_1_1);
		
	}
}