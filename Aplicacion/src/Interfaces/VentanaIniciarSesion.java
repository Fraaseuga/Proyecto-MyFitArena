package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Funcionalidades.Colores;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JButton;

public class VentanaIniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application./
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaIniciarSesion frame = new VentanaIniciarSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VentanaIniciarSesion() {
		//Ajustes de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 700, 550);
		//Hace que la ventana aparezca en medio de la pantalla
		setLocationRelativeTo(null);
		//PANEL PRINCIPAL
		contentPane = new JPanel();
		contentPane.setBackground(Colores.negro);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelSecundario = new JPanel();
		panelSecundario.setForeground(new Color(255, 255, 255));
		panelSecundario.setBackground(new Color(0, 0, 0));
		panelSecundario.setLocation(150, 186);
		panelSecundario.setSize(400, 317);
		contentPane.add(panelSecundario);
		panelSecundario.setLayout(null);
		//Se crea un border con 3 pixeles de ancho
		Border borde = BorderFactory.createLineBorder(Colores.amarilloVivo, 3);
        panelSecundario.setBorder(borde);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario:");
		lblNewLabel_1.setBounds(10, 10, 144, 38);
		panelSecundario.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(Colores.amarilloVivo);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblNewLabel_2 = new JLabel("Contraseña:");
		lblNewLabel_2.setBounds(10, 100, 144, 38);
		panelSecundario.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Colores.amarilloVivo);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setBounds(10, 58, 380, 25);
		panelSecundario.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 148, 380, 25);
		panelSecundario.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBackground(Colores.amarilloVivo);
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciarSesion.setBounds(10, 270, 380, 25);
		panelSecundario.add(btnIniciarSesion);
		
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon("E:\\Proyecto\\Proyecto-MyFitArena-\\Aplicacion\\src\\Interfaces\\logoPequeño.png"));
		lblNewLabel.setBounds(10, 10, 676, 166);
		contentPane.add(lblNewLabel);

	}
}
