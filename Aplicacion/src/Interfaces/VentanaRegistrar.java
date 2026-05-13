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
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNombre;
	private JTextField tfContraseña;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application./
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaRegistrar frame = new VentanaRegistrar();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public VentanaRegistrar() {
		setTitle("Resgistrar Nuevo Usuario");
		setIconImage(Toolkit.getDefaultToolkit().getImage("E:\\Proyecto\\Proyecto-MyFitArena-\\Aplicacion\\src\\Interfaces\\logoPequeño.png"));
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
		//PANEL SECUNDARIO
		JPanel panelSecundario = new JPanel();
		panelSecundario.setForeground(new Color(255, 255, 255));
		panelSecundario.setBackground(new Color(0, 0, 0));
		panelSecundario.setLocation(10, 186);
		panelSecundario.setSize(666, 317);
		contentPane.add(panelSecundario);
		panelSecundario.setLayout(null);
		//Se crea un border con 3 pixeles de ancho
		Border borde = BorderFactory.createLineBorder(Colores.amarilloVivo, 3);
        panelSecundario.setBorder(borde);
		//LABELS
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 10, 144, 38);
		panelSecundario.add(lblNombre);
		lblNombre.setForeground(Colores.amarilloVivo);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblContraseña = new JLabel("DNI/NIE/NIF:");
		lblContraseña.setBounds(10, 165, 144, 38);
		panelSecundario.add(lblContraseña);
		lblContraseña.setForeground(Colores.amarilloVivo);
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel LogoMFA = new JLabel("");
		LogoMFA.setHorizontalAlignment(SwingConstants.CENTER);
		LogoMFA.setIcon(new ImageIcon("E:\\Proyecto\\Proyecto-MyFitArena-\\Aplicacion\\src\\Interfaces\\logoPequeño.png"));
		LogoMFA.setBounds(10, 10, 676, 166);
		contentPane.add(LogoMFA);
		//TEXTFIELDS
		tfNombre = new JTextField();
		tfNombre.setBounds(10, 58, 300, 25);
		panelSecundario.add(tfNombre);
		tfNombre.setColumns(10);
		
		tfContraseña = new JTextField();
		tfContraseña.setBounds(10, 133, 300, 25);
		panelSecundario.add(tfContraseña);
		tfContraseña.setColumns(10);
		//BOTONES
		JButton btnRegistrar = new JButton("Resgistrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrar.setBackground(Colores.amarilloVivo);
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRegistrar.setBounds(512, 282, 144, 25);
		panelSecundario.add(btnRegistrar);
		
		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setForeground(new Color(255, 215, 0));
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblApellidos.setBounds(10, 82, 144, 38);
		panelSecundario.add(lblApellidos);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 213, 300, 25);
		panelSecundario.add(textField);
		
		JLabel lblCorreoElctronico = new JLabel("Correo eléctronico:");
		lblCorreoElctronico.setForeground(new Color(255, 215, 0));
		lblCorreoElctronico.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorreoElctronico.setBounds(356, 10, 144, 38);
		panelSecundario.add(lblCorreoElctronico);
		
		JLabel lblNombre_1_1 = new JLabel("Número de teléfono: ");
		lblNombre_1_1.setForeground(new Color(255, 215, 0));
		lblNombre_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNombre_1_1.setBounds(10, 234, 144, 38);
		panelSecundario.add(lblNombre_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 282, 300, 25);
		panelSecundario.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(356, 58, 300, 25);
		panelSecundario.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(356, 133, 300, 25);
		panelSecundario.add(textField_3);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setForeground(new Color(255, 215, 0));
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContrasea.setBounds(356, 82, 144, 38);
		panelSecundario.add(lblContrasea);
		
		JLabel lblConfirmarContraea = new JLabel("Confirmar contraeña:");
		lblConfirmarContraea.setForeground(new Color(255, 215, 0));
		lblConfirmarContraea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmarContraea.setBounds(356, 165, 144, 38);
		panelSecundario.add(lblConfirmarContraea);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(356, 213, 300, 25);
		panelSecundario.add(textField_4);
		
	}
}
