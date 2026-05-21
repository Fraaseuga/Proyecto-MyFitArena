package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Funcionalidades.Colores;
import dao.UsuarioDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaIniciarSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDNI;
	private JTextField tfContraseña;

	public VentanaIniciarSesion() {
		VentanaPrincipal vp = new VentanaPrincipal();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				vp.setVisible(true);
				VentanaIniciarSesion.this.dispose();
			}
		});
		setTitle("Inicio de Sesión");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaIniciarSesion.class.getResource("/Interfaces/logo.png")));
		//Ajustes de la ventana
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
		panelSecundario.setLocation(150, 186);
		panelSecundario.setSize(400, 317);
		contentPane.add(panelSecundario);
		panelSecundario.setLayout(null);
		//Se crea un border con 3 pixeles de ancho
		Border borde = BorderFactory.createLineBorder(Colores.amarilloVivo, 3);
        panelSecundario.setBorder(borde);
		//LABELS
		JLabel lblNombre = new JLabel("DNI");
		lblNombre.setBounds(10, 10, 144, 38);
		panelSecundario.add(lblNombre);
		lblNombre.setForeground(Colores.amarilloVivo);
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setBounds(10, 100, 144, 38);
		panelSecundario.add(lblContraseña);
		lblContraseña.setForeground(Colores.amarilloVivo);
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel LogoMFA = new JLabel("");
		LogoMFA.setHorizontalAlignment(SwingConstants.CENTER);
		LogoMFA.setIcon(new ImageIcon(VentanaIniciarSesion.class.getResource("/Interfaces/logo.png")));
		LogoMFA.setBounds(10, 10, 676, 166);
		contentPane.add(LogoMFA);
		//TEXTFIELDS
		tfDNI = new JTextField();
		tfDNI.setBounds(10, 58, 380, 25);
		panelSecundario.add(tfDNI);
		tfDNI.setColumns(10);
		
		tfContraseña = new JTextField();
		tfContraseña.setBounds(10, 148, 380, 25);
		panelSecundario.add(tfContraseña);
		tfContraseña.setColumns(10);
		//BOTONES
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {		
					
					String existe = UsuarioDAO.comprobarSiExiste(tfDNI.getText(), tfContraseña.getText());
					
					// Si existe la variabale propietario pasa a ser el DNI del usuario
					if(existe != null && !existe.isEmpty()) {
						VentanaPrincipal.propietario = existe;
						JOptionPane.showMessageDialog(contentPane, 
								"Has iniciado sesión",
								"Confirmación",
								JOptionPane.INFORMATION_MESSAGE);
						
						VentanaPrincipal.propietario = existe;
						VentanaIniciarSesion.this.dispose();
						vp.dispose();
						VentanaPrincipal nueva = new VentanaPrincipal();
						nueva.setVisible(true);
					}
					// Si no existe se envía un mensaje indicándolo
					else {
						JOptionPane.showMessageDialog(contentPane, 
								"El nombre o la contraseña son incorrectos",
								"ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				}catch(Exception er) {
					JOptionPane.showMessageDialog(contentPane, 
							"El nombre o la contraseña son incorrectos",
							"ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnIniciarSesion.setBackground(Colores.amarilloVivo);
		btnIniciarSesion.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIniciarSesion.setBounds(10, 270, 380, 25);
		panelSecundario.add(btnIniciarSesion);
		
	}
}
